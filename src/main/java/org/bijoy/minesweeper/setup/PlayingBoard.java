package org.bijoy.minesweeper.setup;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bijoy.minesweeper.enums.CellStatus;
import org.bijoy.minesweeper.model.Board;
import org.bijoy.minesweeper.model.Cell;
import org.bijoy.minesweeper.model.LocationOfMine;

public class PlayingBoard {

    private static final String CELL_WALL = "|";

    private final Board board;

    private final Map<Integer, Map<Integer, Cell>> gameBoard = new HashMap<>();

    public PlayingBoard(final int rows, final int columns, Set<LocationOfMine> locationOfMines) {
        this.board = new Board(rows, columns);

        createNewPlayingBoard();
        deployMines(locationOfMines);
    }

    public void drawGameBoard() {

        printHeader();

        gameBoard.forEach((row, columnMap) -> {
            System.out.print(row + "     " + CELL_WALL);
            columnMap.forEach((column, cell) -> System.out.print(cell.getCellContent() + CELL_WALL));
            System.out.print("\n");
        });
    }

    //used in DEBUG
    public void drawMineBoard(int totalMines) {
        System.out.println("Total mined deployed: " + totalMines);
        gameBoard.forEach((row, columnMap) -> {
            System.out.print(CELL_WALL);
            columnMap.forEach((column, cell) -> System.out.print(cell.getCellContentUnmasked() + CELL_WALL));
            System.out.print("\n");
        });
    }

    public Cell getCell(final int row, final int column) {
        return this.gameBoard.get(row)
                .get(column);
    }

    public int getColumns() {
        return this.board.columns();
    }

    public int getRows() {
        return this.board.rows();
    }

    private Map<Integer, Cell> createColumns() {
        Map<Integer, Cell> columnMap = new HashMap<>();

        for (int column = 0; column < getColumns(); column++) {
            columnMap.put(column, new Cell());
        }
        return columnMap;
    }

    private void createNewPlayingBoard() {
        for (int row = 0; row < getRows(); row++) {
            this.gameBoard.put(row, createColumns());
        }
    }

    private void deployMines(Set<LocationOfMine> locationOfMines) {
        locationOfMines.forEach(location -> getCell(location.row(), location.column()).setCellStatus(CellStatus.CLOSED_WITH_MINE));
    }

    private void printHeader() {
        System.out.print("      |");
        for (int column = 0; column < getColumns(); column++) {
            System.out.print(" " + column + " " + CELL_WALL);
        }
        System.out.print("\n");

        System.out.print("      -");
        for (int column = 0; column < getColumns(); column++) {
            System.out.print("----");
        }
        System.out.print("\n");
    }
}
