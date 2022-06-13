package org.bijoy;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import org.bijoy.game.Board;
import org.bijoy.game.GamePlay;
import org.bijoy.game.MineLocation;
import org.bijoy.game.Mines;
import org.bijoy.game.UserOption;

public class AsciiMineSweeper {

    private static final int PLAY = 0;

    private static final int REPLAY = 1;

    private static final int QUIT = 2;

    private static final int ROWS = 10;

    private static final int COLUMNS = 10;

    public static void main(String[] args) {
        int userOption = PLAY;
        Set<MineLocation> mineLocationList = null;
        while (userOption != QUIT) {
            if (userOption == PLAY) {
                System.out.println("New game");
                mineLocationList = (new Mines()).createMineLocation(ROWS, COLUMNS);
            } else {
                System.out.println("Replaying game");
            }
            final Board board = new Board(ROWS, COLUMNS, mineLocationList);
            userOption = play(board, mineLocationList.size());
        }
    }

    private static int play(Board board, int totalMines) {
        // DEBUG: uncomment to see the hidden mines
        //board.drawMineBoard(totalMines);

        Scanner in = new Scanner(System.in);

        GamePlay gamePlay = new GamePlay(board, totalMines);
        while (true) {
            board.drawGameBoard();

            System.out.println("What do you want to do?");
            System.out.println("Enter\n 1 for Opening a cell\n 2 for marking mine\n 3 for quitting the game");

            int option = readValidIntegerInput(in);

            if (option == 1) {
                UserOption userOption = chooseRowAndColumnName("open");
                if (!gamePlay.openCellWithoutFail(userOption)) {
                    return steppedOnMine();
                }
            } else if (option == 2) {
                gamePlay.markMine(chooseRowAndColumnName("mark mine"));
            } else if (option == 3) {
                System.out.println("Exiting game");
                return QUIT;
            } else {
                // No option to unmark mine
                System.out.println("Invalid option, exiting game");
                return QUIT;
            }

            if (gamePlay.isComplete()) {
                return completed();
            }
        }
    }

    private static int completed() {
        System.out.println("Congratulations. You have completed the game successfully");

        System.out.println("Enter\n 1 New game\n 2 Exiting the game");
        Scanner in = new Scanner(System.in);
        int choiceMade = readValidIntegerInput(in);
        if (choiceMade == 1) {
            return PLAY;
        } else {
            System.out.println("Exiting game");
            return QUIT;
        }
    }

    private static int steppedOnMine() {
        System.out.println("BOOOOOOOMMMM. You stepped on a mine!!!!");

        System.out.println("Enter\n 1 for Playing again\n 2 New game\n 3 Exiting the game");
        Scanner in = new Scanner(System.in);
        int choiceMade = readValidIntegerInput(in);
        if (choiceMade == 1) {
            return REPLAY;
        } else if (choiceMade == 2) {
            return PLAY;
        } else {
            System.out.println("Exiting game");
            return QUIT;
        }
    }

    private static UserOption chooseRowAndColumnName(String action) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the row number of the cell (0 - " + ROWS + ") you want to " + action);
        int row = readValidIntegerInput(in);

        System.out.println("Enter the column name of the cell (A - J) you want to " + action);
        in.nextLine(); //throw away the \n not consumed by the previous nextInt()
        String columnName = readValidInput(in);  // Validate it is A-J

        System.out.println("Requested cell is (" + row + ", " + columnName + ")");

        return new UserOption(row, columnName);
    }

    private static int readValidIntegerInput(Scanner in) {
        var value = 0;
        while (true) {
            try {
                value = in.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number");
                in.nextLine();  // This is needed here
            }
        }
        return value;
    }

    private static String readValidInput(Scanner in) {
        var value = "";
        while (true) {
            value = in.nextLine();
            if (value.matches("[A-J]+")) {
                break;
            } else {
                System.out.println("Please enter a valid column name [A, B, C, D, E, F, G, H, I, J]");
            }
        }
        return value;
    }
}
