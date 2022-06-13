package org.bijoy.game;

//import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void boardIsCreatedProperly() {
        Set<MineLocation> mineLocations = new HashSet<>();
        mineLocations.add(new MineLocation(0, 0));
        mineLocations.add(new MineLocation(1, 1));

        Board board = new Board(2, 2, mineLocations);

        assertEquals(board.getCell(0, 0)
                .getCellStatus(), CellStatus.UNOPENED_WITH_MINE);
        assertEquals(board.getCell(0, 1)
                .getCellStatus(), CellStatus.UNOPENED_WITHOUT_MINE);
        assertEquals(board.getCell(1, 0)
                .getCellStatus(), CellStatus.UNOPENED_WITHOUT_MINE);
        assertEquals(board.getCell(1, 1)
                .getCellStatus(), CellStatus.UNOPENED_WITH_MINE);
    }

}