package connect4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 *  This class handles the amount that the player bets against the computer. The winner will get 
 *  the amount that the loser bet.
 *
 *  @author  Arnav Gupta
 *  @version May 28, 2018
 *  @author  Period: 2
 *  @author  Assignment: FinalProject
 *
 *  @author  Sources: None
 */
public class BettingHandler
{
    private static final int INITIAL_PLAYER_COINS = 100;

    private static final int INITIAL_COMPUTER_COINS = 100;

    private static final int MAX_ALLOWED_GAMES = 3;

    private int playerRemainingCoins;

    private int computerRemainingCoins;

    private int playerBet;

    private int computerBet;

    private int gamesPlayed;

    Queue<String> betsQueue;

    /**
     * Constructor for the class, initializes the values of the variables that keep track of 
     * betting and the LinkedList!
     */
    public BettingHandler()
    {
        playerRemainingCoins = INITIAL_PLAYER_COINS;
        computerRemainingCoins = INITIAL_COMPUTER_COINS;
        playerBet = 0;
        computerBet = 0;
        gamesPlayed = 0;
        betsQueue = new LinkedList<>();
    }

    /**
     * 
     * This is a helper method that decides how much the computer should bid. Since we aren't 
     * actually running a casino, we can use random values within a range to make sure that the 
     * player cannot guess how much we can bet. 
     * 
     * @param min The minimum bid that the computer will bet.
     * @param max The maximum bid that the computer will bet.
     * @return the amount of coins that the computer will bet.
     */
    private int randomWithRange( int min, int max )
    {
        int range = ( max - min ) + 1;
        return (int)( Math.random() * range ) + min;
    }

    /**
     * 
     * Displays the game over sign when someone(human or computer) ran out of money for bets
     * @return the message(who ran out of coins
     */
    public String gameOverMessage()
    {
        String msg = "Betting over! ";
        if ( gamesPlayed > MAX_ALLOWED_GAMES )
            msg = "maximum allowed games : " + gamesPlayed + " already played ";
        if ( playerRemainingCoins <= 0 )
            msg += "player ran out of money ";
        if ( computerRemainingCoins <= 0 )
            msg += "computer ran out of money ";
        return msg;
    }

    /**
     * 
     * If the player already played 3 games, the loop is broken. The loop also breaks if one player 
     * does not have any coins.
     * @return if the above statements are true, return false, else, return true and continue
     *  the game
     */
    public boolean bettingOver()
    {
        if ( gamesPlayed > MAX_ALLOWED_GAMES || playerRemainingCoins <= 0
            || computerRemainingCoins <= 0 )
            return true;
        return false;
    }

    public int getPlayerRemainingCoins() {
        return playerRemainingCoins;
    }
    

    /**
     * 
     * When the game starts, this is where the messages come from. The first player to go is the
     * one that bet more on the result of the game.
     * 
     * @return return true if player bet is >= computerBet else return false.
     */
    public boolean startBetting()
    {
        gamesPlayed++;       
        playerBet = playerBetNumber;
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
    
    

    /**
     * 
     * When the game ends(someone gets 4 in a row), the winner will get the coins the loser bet and 
     * the loser will lose the coins that they bid
     * 
     * @param winner The color of the winner "Y" or "R"
     */
    public void handleWinner( String winner )
    {
        String currentBetData = String.format("Game[%d] Winner[%s] PlayerBet[%d] ComputerBet[%d] PlayerBalance[%d] ComputerBalance[%d]",
                gamesPlayed, winner, playerBet, computerBet, playerRemainingCoins, computerRemainingCoins);
        
        betsQueue.add(currentBetData);
        if (winner == "Y") {
            playerRemainingCoins += computerBet;
            computerRemainingCoins -= computerBet;
        } else {
            playerRemainingCoins -= playerBet;
            computerRemainingCoins += playerBet;            
        }            
    }

    /**
     * 
     * This displays all the previous bets. This is a helper method.
     */
    public void displayAllBets()
    {
        for ( String betData : betsQueue )
        {
            System.out.println( betData );
        }
    }

}
