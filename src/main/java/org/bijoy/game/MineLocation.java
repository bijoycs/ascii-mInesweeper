package org.bijoy.game;

import java.util.Objects;

public class MineLocation {

    private final int row;

    private final int column;

    public MineLocation(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MineLocation that = (MineLocation) o;
        return row == that.row && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
