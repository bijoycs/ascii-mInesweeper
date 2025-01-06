package org.bijoy.minesweeper.model;

import static org.bijoy.minesweeper.enums.CellStatus.CLOSED_AND_EMPTY;
import static org.bijoy.minesweeper.enums.CellStatus.CLOSED_WITH_MINE;
import static org.bijoy.minesweeper.enums.CellStatus.MARKED_MINE_CORRECTLY;
import static org.bijoy.minesweeper.enums.CellStatus.MARKED_MINE_WRONGLY;
import static org.bijoy.minesweeper.enums.CellStatus.OPENED;

import org.bijoy.minesweeper.enums.CellStatus;

public class Cell {

    private CellStatus cellStatus = CLOSED_AND_EMPTY;

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
        if (cellStatus == OPENED) {
            if (this.neighbouringMines == 0) {
                return "   ";
            }

            return " " + this.neighbouringMines + " ";
        }

        if (cellStatus == MARKED_MINE_CORRECTLY || cellStatus == MARKED_MINE_WRONGLY) {
            return " X ";
        }

        return " ? ";
    }

    public String getCellContentUnmasked() {
        if (cellStatus == CLOSED_WITH_MINE) {
            return " M ";
        }

        return " ? ";
    }
}
