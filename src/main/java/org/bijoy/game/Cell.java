package org.bijoy.game;

public class Cell {

    private CellStatus cellStatus = CellStatus.UNOPENED_WITHOUT_MINE;

    private int neighbouringMines = 0;

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public CellStatus getCellStatus() {
        return this.cellStatus;
    }

    public void setNeighbouringMines(int mines) {
        this.neighbouringMines = mines;
    }

    public String getCellContent() {
        if (cellStatus == CellStatus.OPENED) {
            if (this.neighbouringMines == 0) {
                return "   ";
            }

            return " " + this.neighbouringMines + " ";
        }

        if (cellStatus == CellStatus.MARKED_MINE_CORRECTLY || cellStatus == CellStatus.MARKED_MINE_WRONGLY) {
            return " X ";
        }

        return " ? ";
    }

    public String getCellContentUnmasked() {
        if (cellStatus == CellStatus.UNOPENED_WITH_MINE) {
            return " M ";
        }

        return " ? ";
    }
}
