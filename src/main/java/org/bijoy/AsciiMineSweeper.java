package org.bijoy;

import org.bijoy.minesweeper.play.UserControl;

public class AsciiMineSweeper {

    public static void main(String[] args) {
        UserControl userControl = new UserControl();
        userControl.start();
        
        System.out.println("Thank you for playing!");
    }
}
