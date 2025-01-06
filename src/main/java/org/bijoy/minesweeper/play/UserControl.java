package org.bijoy.minesweeper.play;

import static org.bijoy.minesweeper.enums.Choice.PLAY;
import static org.bijoy.minesweeper.enums.Choice.QUIT;
import static org.bijoy.minesweeper.enums.Choice.REPLAY;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import org.bijoy.minesweeper.enums.CellStatus;
import org.bijoy.minesweeper.enums.Choice;
import org.bijoy.minesweeper.model.LocationOfMine;
import org.bijoy.minesweeper.model.SelectedCell;
import org.bijoy.minesweeper.setup.Mines;

public class UserControl {

    private static final int MAX_ROWS = 100;

    private static final int MAX_COLUMNS = 100;

    private int maxRows;

    private int maxColumns;

    private Scanner in;

    public void start() {
        Set<LocationOfMine> locationOfMineList = null;
        Choice choice = PLAY;
        in = new Scanner(System.in);

        while (choice != QUIT) {
            if (choice == PLAY) {
                locationOfMineList = setUp();
            } else {
                System.out.println("Replaying game");
            }

            choice = play(maxRows, maxColumns, locationOfMineList);
        }
    }

    private Set<LocationOfMine> setUp() {
        System.out.println("Starting new game");
        chooseSizeOfBoard();

        return (new Mines()).createMineLocation(maxRows, maxColumns);
    }

    private Choice play(int rows, int columns, Set<LocationOfMine> locationOfMineList) {
        GamePlay gamePlay = new GamePlay(rows, columns, locationOfMineList);

        // DEBUG: uncomment to see the hidden mines
        //gamePlay.drawMineBoard(totalMines);

        while (true) {
            gamePlay.drawGameBoard();

            int option = getValidNumber("Enter\n 1 for marking cell safe\n 2 for marking mine\n 3 for quitting the game", 1, 3);

            if (option == 1) {
                SelectedCell selectedCell = chooseRowAndColumn("open");

                final CellStatus cellStatus = gamePlay.openCellAndReturnStatus(selectedCell);
                if (cellStatus == CellStatus.CLOSED_WITH_MINE || cellStatus == CellStatus.MARKED_MINE_CORRECTLY) {
                    return steppedOnMine();
                }
            } else if (option == 2) {
                gamePlay.markMine(chooseRowAndColumn("mark mine"));
            } else if (option == 3) {
                System.out.println("Exiting game");
                return QUIT;
            } else {
                System.out.println("Invalid option, exiting game");
                return QUIT;
            }

            if (gamePlay.isComplete()) {
                return completed();
            }
        }
    }

    private Choice completed() {
        System.out.println("Congratulations. You have completed the game successfully");

        return getChoice();
    }

    private Choice steppedOnMine() {
        System.out.println("Game over, you stepped on a mine!!!!");

        return getChoice();
    }

    private Choice getChoice() {
        System.out.println("Enter\n 1 for New game\n 2 for Playing again\n 3 for Exiting the game");

        int choiceMade = readValidIntegerInput();
        if (choiceMade == 1) {
            return PLAY;
        } else if (choiceMade == 2) {
            return REPLAY;
        } else {
            System.out.println("Exiting game");
            return QUIT;
        }
    }

    private SelectedCell chooseRowAndColumn(String action) {
        final int row = getValidNumber("Enter the row number of the cell (0 - " + (maxRows - 1) + ") you want to " + action, 0, maxRows - 1);

        int column = getValidNumber("Enter the column number of the cell (0 - " + (maxColumns - 1) + ") you want to " + action, 0, maxColumns - 1);  // Validate it is A-J

        System.out.println("Requested cell is (" + row + ", " + column + ")");

        return new SelectedCell(row, column);
    }

    private void chooseSizeOfBoard() {
        System.out.println("Choose the size of the board.");

        this.maxRows = getValidNumber("Enter the number of rows (1 - " + MAX_ROWS + "):", 1, MAX_ROWS);
        this.maxColumns = getValidNumber("Enter the number of columns (1 - " + MAX_COLUMNS + "):", 1, MAX_COLUMNS);

        System.out.println("Size is " + this.maxRows + " * " + this.maxColumns);
    }

    private int getValidNumber(String message, int minNumber, int maxNumber) {
        System.out.println(message);

        int input = readValidIntegerInput();

        if (input < minNumber || input > maxNumber) {
            System.out.println("Sorry, invalid number. Enter again between " + minNumber + " and " + maxNumber);
            return getValidNumber(message, minNumber, maxNumber);
        }

        return input;
    }

    private int readValidIntegerInput() {
        while (true) {
            try {
                return this.in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number");
                in.nextLine();  // This is needed here
            }
        }
    }
}
