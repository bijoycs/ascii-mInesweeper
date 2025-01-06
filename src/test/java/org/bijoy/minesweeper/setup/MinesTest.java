package org.bijoy.minesweeper.setup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.bijoy.minesweeper.model.LocationOfMine;
import org.junit.jupiter.api.Test;

class MinesTest {

    @Test
    void duplicatedMineLocationsAreIgnored() {
        Set<LocationOfMine> locationOfMines = new HashSet<>();
        locationOfMines.add(new LocationOfMine(1, 2));
        locationOfMines.add(new LocationOfMine(1, 2));
        locationOfMines.add(new LocationOfMine(1, 3));

        assertEquals(2, locationOfMines.size());
    }
}