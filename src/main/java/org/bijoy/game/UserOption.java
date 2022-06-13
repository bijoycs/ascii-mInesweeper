package org.bijoy.game;

public class UserOption {

    private final int rowSelected;

    private final int columnSelected;

    public UserOption(final int rowSelected, final String columnNameSelected) {
        this.rowSelected = rowSelected;
        this.columnSelected = getColumnNumber(columnNameSelected);
    }

    public int getRowSelected() {
        return rowSelected;
    }

    public int getColumnSelected() {
        return this.columnSelected;
    }

    private int getColumnNumber(String columnName) {
        switch (columnName) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            case "G":
                return 6;
            case "H":
                return 7;
            case "I":
                return 8;
            case "J":
                return 9;
            default:
                return 10; // Have to do something
        }
    }
}
