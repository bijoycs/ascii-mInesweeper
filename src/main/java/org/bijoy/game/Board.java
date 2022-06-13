package org.bijoy.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Board {

    private static final String CELL_WALL = "|";

    private final Map<Integer, Map<Integer, Cell>> gameBoard = new HashMap<>();

    private final int rows;

    private final int columns;

    public Board(int row, int column, Set<MineLocation> mineLocations) {
        this.rows = row;
        this.columns = column;

        for (int index = 0; index < this.rows; index++) {
            createEmptyRow(index);
        }

        deployMines(mineLocations);
    }

    private void createEmptyRow(int row) {
        Map<Integer, Cell> columnMap = new HashMap<>();

        for (int column = 0; column < columns; column++) {
            columnMap.put(column, new Cell());
        }

        gameBoard.put(row, columnMap);
    }

    private void deployMines(Set<MineLocation> mineLocations) {
        mineLocations.forEach(m -> getCell(m.getRow(), m.getColumn()).setCellStatus(CellStatus.UNOPENED_WITH_MINE));
    }

    public Cell getCell(final int row, final int column) {
        return this.gameBoard.get(row)
                .get(column);
    }

    public void drawGameBoard() {
        System.out.println("      | A | B | C | D | E | F | G | H | I | J |");
        System.out.println("      -----------------------------------------");
        gameBoard.forEach((row, columnMap) -> {
            System.out.print(row + "     " + CELL_WALL);
            columnMap.forEach((column, cell) -> System.out.print(cell.getCellContent() + CELL_WALL));
            System.out.print("\n");
        });
    }

    public void drawMineBoard(int totalMines) {
        System.out.println("Total mined deployed: " + totalMines);
        gameBoard.forEach((row, columnMap) -> {
            System.out.print(CELL_WALL);
            columnMap.forEach((column, cell) -> System.out.print(cell.getCellContentUnmasked() + CELL_WALL));
            System.out.print("\n");
        });
    }

    public Map<Integer, Map<Integer, Cell>> getGameBoard() {
        return gameBoard;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
