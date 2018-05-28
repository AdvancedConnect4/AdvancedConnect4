package connect4;

import java.util.Scanner;

public class BettingHandler {
    private static final int INITIAL_PLAYER_COINS = 100; 
    private static final int INITIAL_COMPUTER_COINS = 100;
    private static final int MAX_ALLOWED_GAMES = 3;
    private int playerRemainingCoins;
    private int computerRemainingCoins;
    private int playerBet;
    private int computerBet;
    private int gamesPlayed;
    boolean gameOver;

    public BettingHandler () {
        playerRemainingCoins = INITIAL_PLAYER_COINS;
        computerRemainingCoins = INITIAL_COMPUTER_COINS;
        playerBet = 0;
        computerBet = 0;
        gamesPlayed = 0;
        gameOver = false;
    }
    
    private int randomWithRange(int min, int max)
    {
       int range = (max - min) + 1;     
       return (int)(Math.random() * range) + min;
    }
   
    public String gameOverMessage() {
        String msg = "Betting over! ";
        if (gamesPlayed >= MAX_ALLOWED_GAMES)
            msg = "maximum allowed games : " + MAX_ALLOWED_GAMES + " already played ";
        if (playerRemainingCoins <=0 )
            msg += "player ran out of money ";
        if (computerRemainingCoins <=0 )
            msg += "computer ran out of money ";
        return msg;
    }
    
    public boolean bettingOver() {
        if (gameOver)
            return true;
        if (gamesPlayed <= MAX_ALLOWED_GAMES && playerRemainingCoins > 0 && computerRemainingCoins > 0)
            return false;
        return true;
    }
    
    //return true if player bet is >= computerBet
    public boolean startBetting()
    {
        gamesPlayed++;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("Start your bet [MIN: 0 MAX : " + playerRemainingCoins + "] : ");
            while (!scan.hasNextInt()) {
                System.out.println("That's not a valid bet!");
                scan.next(); // this is important!
            }
            playerBet = scan.nextInt();
      } while (playerBet < 0 || playerBet > playerRemainingCoins);
        
      computerBet = randomWithRange(0, computerRemainingCoins);  
      boolean playerFirst = playerBet >=  computerBet;
      String msg = "";
      if (playerFirst)
          msg = "Player";
      else 
          msg = "Computer";
      System.out.println(msg + " gets first turn for game " + gamesPlayed + " you are betting $" + playerBet + " against computer bet $" + computerBet + "\n");
      return playerFirst;
    }
    
    public void handleWinner(boolean isPlayerWinner) {
        if (isPlayerWinner) {
            playerRemainingCoins += computerBet;
            computerRemainingCoins -= computerBet;
        } else {
            playerRemainingCoins -= playerBet;
            computerRemainingCoins += playerBet;            
        }                    
    }

}
