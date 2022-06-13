package org.bijoy.game;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Mines {

    public Set<MineLocation> createMineLocation(int maxRows, int maxColumns) {
        Set<MineLocation> mineLocations = new HashSet<>();

        int min = Math.round(maxRows * maxColumns * 20 / 100);
        int max = Math.round(maxRows * maxColumns * 30 / 100);

        var totalDeployedMine = (int) Math.floor(Math.random() * (max - min + 1) + min);

        final Random randomInt = new Random();
        while (mineLocations.size() < totalDeployedMine) {
            mineLocations.add(new MineLocation(randomInt.nextInt(maxRows), randomInt.nextInt(maxColumns)));
        }
        return mineLocations;
    }
}
