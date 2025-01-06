package org.bijoy.minesweeper.setup;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.HashSet;
import java.util.Set;

import org.bijoy.minesweeper.enums.CellStatus;
import org.bijoy.minesweeper.model.LocationOfMine;
import org.junit.jupiter.api.Test;

class PlayingBoardTest {

    @Test
    void boardIsCreatedProperly() {
        Set<LocationOfMine> locationOfMines = new HashSet<>();
        locationOfMines.add(new LocationOfMine(0, 0));
        locationOfMines.add(new LocationOfMine(1, 1));

        PlayingBoard playingBoard = new PlayingBoard(2, 2, locationOfMines);

        assertSame(playingBoard.getCell(0, 0)
                .getCellStatus(), CellStatus.CLOSED_WITH_MINE);
        assertSame(playingBoard.getCell(0, 1)
                .getCellStatus(), CellStatus.CLOSED_AND_EMPTY);
        assertSame(playingBoard.getCell(1, 0)
                .getCellStatus(), CellStatus.CLOSED_AND_EMPTY);
        assertSame(playingBoard.getCell(1, 1)
                .getCellStatus(), CellStatus.CLOSED_WITH_MINE);
    }

}