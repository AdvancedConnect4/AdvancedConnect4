package connect4;

import java.util.Scanner;

public class InputHandler {
    public int getYellowLocation(Scanner scan) {
        int number;
        do {
            System.out.print("Drop a yellow disk at column (0–6): ");
            while (!scan.hasNextInt()) {
                System.out.println("That's not a number!");
                scan.next(); // this is important!
            }
            number = scan.nextInt();
      } while (number < 0 || number > 6);
      System.out.println("Thank you! dropping Yellow at : " + number);
      return number;      
    }

    public int getPlayerBet(Scanner scan, int playerRemainingCoins) {
        int playerBet =0;
        do {
            System.out.print("Start your bet [MIN: 0 MAX : " + playerRemainingCoins + "] : ");
            while (!scan.hasNextInt()) {
                System.out.println("That's not a valid bet!");
                scan.next(); // this is important!
                }
        playerBet = scan.nextInt();
        } while (playerBet < 0 || playerBet > playerRemainingCoins);
        return playerBet;
    }
}
