package org.bijoy.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GamePlayTest {
    //setCountNeighbouringMines
    //markMine

    private Board board;
    private GamePlay gamePlay;

    @BeforeEach
    void setup() {
        Set<MineLocation> mineLocations = new HashSet<>();
        mineLocations.add(new MineLocation(0, 0));

        board = new Board(3, 3, mineLocations);
        gamePlay = new GamePlay(board, 1);
    }

    @Test
    void openCellWithoutFail() {
        UserOption userOption = new UserOption(0, "B");

        assertTrue(gamePlay.openCellWithoutFail(userOption));
        assertTrue(board.getCell(0, 1).getCellStatus() == CellStatus.OPENED);
        assertEquals(" 1 ", board.getCell(0, 1).getCellContent());
    }

    @Test
    void openCellWithFail() {
        UserOption userOption = new UserOption(0, "A");

        assertFalse(gamePlay.openCellWithoutFail(userOption));
    }

    @Test
    void markMineCorrectly() {
        UserOption userOption = new UserOption(0, "A");

        gamePlay.markMine(userOption);

        assertTrue(board.getCell(0, 0).getCellStatus() == CellStatus.MARKED_MINE_CORRECTLY);
    }

    @Test
    void markMineWrongly() {
        UserOption userOption = new UserOption(0, "B");

        gamePlay.markMine(userOption);

        assertTrue(board.getCell(0, 1).getCellStatus() == CellStatus.MARKED_MINE_WRONGLY);
    }
}