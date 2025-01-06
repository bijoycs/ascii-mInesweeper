package org.bijoy.minesweeper.play;

import java.util.Set;

import org.bijoy.minesweeper.enums.CellStatus;
import org.bijoy.minesweeper.model.Cell;
import org.bijoy.minesweeper.model.LocationOfMine;
import org.bijoy.minesweeper.model.SelectedCell;
import org.bijoy.minesweeper.setup.PlayingBoard;

public class GamePlay {

    private final PlayingBoard playingBoard;

    private final int totalDeployedMines;

    public GamePlay(int rows, int columns, Set<LocationOfMine> locationOfMineSet) {
        this.playingBoard = new PlayingBoard(rows, columns, locationOfMineSet);
        this.totalDeployedMines = locationOfMineSet.size();
    }

    public void drawGameBoard() {
        this.playingBoard.drawGameBoard();
    }

    //Debug
    public void drawMineBoard(int totalMines) {
        this.playingBoard.drawMineBoard(totalMines);
    }

    public boolean isComplete() {
        int incompleteCell = 0;
        int minesMarkedCorrectly = 0;

        for (int row = 0; row < this.playingBoard.getRows(); row++) {
            for (int column = 0; column < this.playingBoard.getColumns(); column++) {
                switch (getCell(row, column).getCellStatus()) {
                    case MARKED_MINE_WRONGLY, CLOSED_AND_EMPTY, CLOSED_WITH_MINE -> incompleteCell++;
                    case MARKED_MINE_CORRECTLY -> minesMarkedCorrectly++;
                }

                if (incompleteCell > 0) {
                    return false;
                }
            }
        }

        return minesMarkedCorrectly == totalDeployedMines;
    }

    public CellStatus markMine(SelectedCell selectedCell) {
        final Cell cell = getCell(selectedCell);

        if (CellStatus.CLOSED_WITH_MINE == cell.getCellStatus() || CellStatus.MARKED_MINE_CORRECTLY == cell.getCellStatus()) {
            cell.setCellStatus(CellStatus.MARKED_MINE_CORRECTLY);
        } else {
            cell.setCellStatus(CellStatus.MARKED_MINE_WRONGLY);
        }

        return cell.getCellStatus();
    }

    public CellStatus openCellAndReturnStatus(SelectedCell selectedCell) {
        final Cell cell = getCell(selectedCell);

        if (cell.getCellStatus() == CellStatus.CLOSED_AND_EMPTY || cell.getCellStatus() == CellStatus.MARKED_MINE_WRONGLY) {
            setCountNeighbouringMines(selectedCell);
            cell.setCellStatus(CellStatus.OPENED);
        }

        return cell.getCellStatus();
    }

    private int calculateMinesInTheRowAbove(SelectedCell selectedCell) {
        if (selectedCell.rowSelected() == 0) {
            return 0;
        }

        // cell directly above and its left and right
        return getMineCountOfCell(selectedCell.rowSelected() - 1, selectedCell.columnSelected()) + calculateMinesToLeftAndRight(selectedCell.rowSelected() - 1,
                selectedCell.columnSelected());
    }

    private int calculateMinesInTheRowBelow(SelectedCell selectedCell) {
        if (selectedCell.rowSelected() == this.playingBoard.getRows() - 1) {
            return 0;
        }

        // cell directly below and its left and right
        return getMineCountOfCell(selectedCell.rowSelected() + 1, selectedCell.columnSelected()) + calculateMinesToLeftAndRight(selectedCell.rowSelected() + 1,
                selectedCell.columnSelected());
    }

    private int calculateMinesToLeftAndRight(final int row, final int column) {
        int leftCell = 0;
        int rightCell = 0;

        if (column > 0) {
            leftCell = getMineCountOfCell(row, column - 1);
        }
        if (column < this.playingBoard.getColumns() - 1) {
            rightCell = getMineCountOfCell(row, column + 1);
        }
        return leftCell + rightCell;
    }

    private Cell getCell(int row, int column) {
        return this.playingBoard.getCell(row, column);
    }

    private Cell getCell(SelectedCell selectedCell) {
        return getCell(selectedCell.rowSelected(), selectedCell.columnSelected());
    }

    private int getMineCountOfCell(final int row, final int column) {
        final Cell cell = getCell(row, column);
        if (cell.getCellStatus() == CellStatus.MARKED_MINE_CORRECTLY || cell.getCellStatus() == CellStatus.CLOSED_WITH_MINE) {
            return 1;
        }
        return 0;
    }

    private void setCountNeighbouringMines(SelectedCell selectedCell) {
        getCell(selectedCell).setNeighbouringMines(
                calculateMinesInTheRowAbove(selectedCell) + calculateMinesInTheRowBelow(selectedCell) + calculateMinesToLeftAndRight(selectedCell.rowSelected(),
                        selectedCell.columnSelected()));
    }
}
