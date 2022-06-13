package org.bijoy.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class MinesTest {

    @Test
    void duplicatedMinelocationsAreIgnored() {
        Set<MineLocation> mineLocations = new HashSet<>();
        mineLocations.add(new MineLocation(1, 2));
        mineLocations.add(new MineLocation(1, 2));
        mineLocations.add(new MineLocation(1, 3));

        assertEquals(2, mineLocations.size());
    }
}