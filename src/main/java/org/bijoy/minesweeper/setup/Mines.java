package org.bijoy.minesweeper.setup;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.bijoy.minesweeper.model.LocationOfMine;

public class Mines {

    public Set<LocationOfMine> createMineLocation(int maxRows, int maxColumns) {
        Set<LocationOfMine> locationOfMines = new HashSet<>();

        int min = Math.round((float) (maxRows * maxColumns * 20) / 100);
        int max = Math.round((float) (maxRows * maxColumns * 30) / 100);

        var totalDeployedMine = (int) Math.floor(Math.random() * (max - min + 1) + min);

        final Random randomInt = new Random();
        while (locationOfMines.size() < totalDeployedMine) {
            locationOfMines.add(new LocationOfMine(randomInt.nextInt(maxRows), randomInt.nextInt(maxColumns)));
        }

        return locationOfMines;
    }
}
