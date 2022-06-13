package org.bijoy.game;

public class GamePlay {

    private final Board board;

    private final int totalDeployedMines;

    private int cellsOpened = 0;

    private int minesMarkedCorrectly = 0;

    public GamePlay(Board board, int totalDeployedMines) {
        this.board = board;
        this.totalDeployedMines = totalDeployedMines;
    }

    public boolean openCellWithoutFail(UserOption userOption) {
        final Cell cell = getCell(userOption.getRowSelected(), userOption.getColumnSelected());

        if (cell.getCellStatus() == CellStatus.UNOPENED_WITH_MINE) {
            return false;
        }

        if (cell.getCellStatus() == CellStatus.UNOPENED_WITHOUT_MINE) {
            setCountNeighbouringMines(userOption);
            cell.setCellStatus(CellStatus.OPENED);
        }

        cellsOpened++;
        return true;
    }

    private Cell getCell(UserOption userOption) {
        return getCell(userOption.getRowSelected(), userOption.getColumnSelected());
    }

    private Cell getCell(final int row, final int column) {
        return this.board.getCell(row, column);
    }

    private void setCountNeighbouringMines(UserOption userOption) {
        getCell(userOption).setNeighbouringMines(
                calculateMinesInTheAboveRow(userOption) + calculateMinesInTheBelowRow(userOption) + calculateMinesToLeftAndRight(userOption.getRowSelected(),
                        userOption.getColumnSelected()));
    }

    private int calculateMinesInTheAboveRow(UserOption userOption) {
        if (userOption.getRowSelected() == 0) {
            return 0;
        }

        // cell directly above and its left and right
        return getMineCountOfCell(userOption.getRowSelected() - 1, userOption.getColumnSelected()) + calculateMinesToLeftAndRight(userOption.getRowSelected() - 1,
                userOption.getColumnSelected());
    }

    private int calculateMinesInTheBelowRow(UserOption userOption) {
        if (userOption.getRowSelected() == this.board.getRows() - 1) {
            return 0;
        }

        // cell directly below and its left and right
        return getMineCountOfCell(userOption.getRowSelected() + 1, userOption.getColumnSelected()) + calculateMinesToLeftAndRight(userOption.getRowSelected() + 1,
                userOption.getColumnSelected());
    }

    private int calculateMinesToLeftAndRight(final int row, final int column) {
        int leftCell = 0;
        int rightCell = 0;

        if (column > 0) {
            leftCell = getMineCountOfCell(row, column - 1);
        }
        if (column < this.board.getColumns() - 1) {
            rightCell = getMineCountOfCell(row, column + 1);
        }
        return leftCell + rightCell;
    }

    private int getMineCountOfCell(final int row, final int column) {
        final Cell cell = getCell(row, column);
        if (cell.getCellStatus() == CellStatus.MARKED_MINE_CORRECTLY || cell.getCellStatus() == CellStatus.UNOPENED_WITH_MINE) {
            return 1;
        }
        return 0;
    }

    public void markMine(UserOption userOption) {
        final Cell cell = getCell(userOption.getRowSelected(), userOption.getColumnSelected());

        if (cell.getCellStatus() != CellStatus.OPENED) {
            if (cell.getCellStatus() == CellStatus.UNOPENED_WITH_MINE) {
                cell.setCellStatus(CellStatus.MARKED_MINE_CORRECTLY);
                minesMarkedCorrectly++;
            } else {
                cell.setCellStatus(CellStatus.MARKED_MINE_WRONGLY);
            }
        }
    }

    public boolean isComplete() {
        return (minesMarkedCorrectly == totalDeployedMines) && ((totalDeployedMines + cellsOpened) == (this.board.getRows() * this.board.getColumns()));
    }
}
