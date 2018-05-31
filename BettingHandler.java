import java.util.LinkedList;
import java.util.List;

/**
 * 
 *  This class handles the amount that the player bets against the computer. The winner will get 
 *  the amount that the loser bet.
 *
 *  @author  Arnav Gupta
 *  @author  Anika Murthy
 *  @version May 28, 2018
 *  @author  Period: 2
 *  @author  Assignment: FinalProject
 *
 *  @author  Sources: None
 */
public class BettingHandler
{
	private static final int INITIAL_COINS = 100;

	private static final int MAX_ALLOWED_GAMES = 3;

	private int bluePlayerCoins;

	private int pinkPlayerCoins;

	private int bluePlayerBet;

	private int pinkPlayerBet;

	private int gamesPlayed;


	List<String> betsList;

	/**
	 * Constructor for the class, initializes the values of the variables that keep track of 
	 * betting and the LinkedList!
	 */
	public BettingHandler()
	{
		bluePlayerCoins = INITIAL_COINS;
		pinkPlayerCoins = INITIAL_COINS;
		bluePlayerBet = 0;
		pinkPlayerBet = 0;
		gamesPlayed = 0;
		betsList = new LinkedList<>();
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
	 * Displays the game over sign when someone(human or computer) ran out of money for bets
	 * @return the message(who ran out of coins
	 */
	public String gameOverMessage()
	{
		String msg = "Betting over! ";
		if ( gamesPlayed > MAX_ALLOWED_GAMES )
			msg = "maximum allowed games : " + gamesPlayed + " already played ";
		if ( bluePlayerCoins <= 0 )
			msg += "player ran out of money ";
		if ( pinkPlayerCoins <= 0 )
			msg += "computer ran out of money ";
		return msg;
	}

	/**
	 * If the player already played 3 games, the loop is broken. The loop also breaks if one player 
	 * does not have any coins.
	 * @return if the above statements are true, return false, else, return true and continue
	 *  the game
	 */
	public boolean bettingOver()
	{
		if ( gamesPlayed > MAX_ALLOWED_GAMES 
				|| bluePlayerCoins <= 0
				|| pinkPlayerCoins <= 0 )
			return true;
		return false;
	}

	/**
	 * Sets the blue player's bet to the supplied value.
	 */
	public void setBluePlayerBet(int bet)
	{
		bluePlayerBet = bet;
	}

	/**
	 * Returns the blue player's bet.
	 */
	public int getBluePlayerBet()
	{
		return bluePlayerBet;
	}

	/**
	 * Returns the number of coins that the computer is willing to bet on the current game
	 * in progress.
	 * 
	 * @return returns the computer's bet as number of coins.
	 */
	public int getPinkPlayerBet()
	{
		gamesPlayed++;
		pinkPlayerBet = randomWithRange( 0, pinkPlayerCoins );
		return pinkPlayerBet;
	}

	/**
	 * When the game ends(someone gets 4 in a row), the winner will get the coins the loser bet and 
	 * the loser will lose the coins that they bid
	 * 
	 * @param winner The color of the winner "Y" or "R"
	 */
	public void handleWinner( String winner )
	{
		String winningPlayer;
		if ( winner == "B" )
		{
			bluePlayerCoins += pinkPlayerBet;
			pinkPlayerCoins -= pinkPlayerBet;
			winningPlayer = "Blue Player";
		}
		else
		{
			bluePlayerCoins -= bluePlayerBet;
			pinkPlayerCoins += bluePlayerBet;
			winningPlayer = "Pink Player";
		}

		String currentBetData = String.format(
				"GAME: %d, WINNER: %s, BLUE PLAYER BET: %d, PINK PLAYER BET: %d, BLUE PLAYER REMAINING COINS: %d, PINK PLAYER REMAINING COINS: %d",
				gamesPlayed,
				winningPlayer,
				bluePlayerBet,
				pinkPlayerBet,
				bluePlayerCoins,
				pinkPlayerCoins );
		betsList.add( currentBetData );
	}

	/**
	 * This displays all the previous bets. This is a helper method.
	 */
	public void displayAllBets()
	{
		for ( String betData : betsList )
		{
			System.out.println( betData );
		}
	}

	/**
	 * This displays all the previous bets. This is a helper method.
	 */
	public String getGameStats(String winner)
	{
		String gameStats = "";
		if (winner.equals("P"))
		{
			gameStats += "FINAL WINNER: PINK PLAYER";
			gameStats += "\n";
		}
		else 
		{
			gameStats += "FINAL WINNER: BLUE PLAYER";
			gameStats += "\n";
		}
		for ( String betData : betsList )
		{
			gameStats += betData;
			gameStats += "\n";
		}
		return gameStats;
	}

	public int getBluePlayerCoins()
	{
		return bluePlayerCoins;
	}

	public int getPinkPlayerCoins()
	{
		return pinkPlayerCoins;
	}

}
