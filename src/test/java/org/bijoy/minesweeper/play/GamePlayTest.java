package org.bijoy.minesweeper.play;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.HashSet;
import java.util.Set;

import org.bijoy.minesweeper.enums.CellStatus;
import org.bijoy.minesweeper.model.LocationOfMine;
import org.bijoy.minesweeper.model.SelectedCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GamePlayTest {

    private GamePlay gamePlay;

    @BeforeEach
    void setup() {
        Set<LocationOfMine> locationOfMines = new HashSet<>();
        locationOfMines.add(new LocationOfMine(0, 0));

        gamePlay = new GamePlay(3, 3, locationOfMines);
    }

    @Test
    void openCellAndReturnStatus() {
        SelectedCell selectedCell = new SelectedCell(0, 1);

        assertSame(gamePlay.openCellAndReturnStatus(selectedCell), CellStatus.OPENED);
    }

    @Test
    void openCellAndReturnStatusWithFail() {
        SelectedCell selectedCell = new SelectedCell(0, 0);

        assertSame(gamePlay.openCellAndReturnStatus(selectedCell), CellStatus.CLOSED_WITH_MINE);
    }

    @Test
    void markMineCorrectly() {
        SelectedCell selectedCell = new SelectedCell(0, 0);

        assertSame(gamePlay.markMine(selectedCell), CellStatus.MARKED_MINE_CORRECTLY);
    }

    @Test
    void markMineWrongly() {
        SelectedCell selectedCell = new SelectedCell(0, 1);

        assertSame(gamePlay.markMine(selectedCell), CellStatus.MARKED_MINE_WRONGLY);
    }
}