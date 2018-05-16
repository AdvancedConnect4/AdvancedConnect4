package connect4;

public class ComputerPlayer extends Player
{
    static Logic test = new Logic();
    static int computerPlayerCoins;
    static int humanPlayerCoins;
    static int bidAmount;
    static int computerMovesToWin;
    static int humanMovesToWin; //how many moves it will take for the computer to win
    static int[] previousHumanBids = new int[43]; //keeps track of the previous human bids that the human bidded
    static int movesPlayed = 0; // how many moves have been played(not each player but total/2)
    
    public void setPreviousHumanBids()
    {
        previousHumanBids = test.getPreviousHumanBids();
    }
    
    public static int humanPlayerCoins()
    {
        humanPlayerCoins = test.getYellowCoins();
        return humanPlayerCoins;
    }
    
    public static int computerPlayerCoins()
    {
        computerPlayerCoins = test.getRedCoins();
        return computerPlayerCoins;
    }

    public static int bidAmount()
    {
        if((computerMovesToWin == 1) && (computerPlayerCoins() > humanPlayerCoins()))
        {
            bidAmount = humanPlayerCoins() + 1;
        }
        if((computerMovesToWin == 2) && (computerPlayerCoins() > ((2 *humanPlayerCoins()) + 1)))
        {
            bidAmount = humanPlayerCoins() + 1;
        }
        if((computerMovesToWin == 3) && (computerPlayerCoins() > ((3 *humanPlayerCoins()) + 1)))
        {
            bidAmount = humanPlayerCoins() + 1;
        }
        if((computerMovesToWin >= 4) && (computerPlayerCoins() > ((4 *humanPlayerCoins()) + 1)))
        {
            bidAmount = humanPlayerCoins() + 1;
        }
        if((computerMovesToWin == 2) && (computerPlayerCoins() > ((2 *humanPlayerCoins()) + 1)))
        {
            bidAmount = humanPlayerCoins() + 1;
        }
        if ((humanMovesToWin == 1)) //if the human is one step from winning, stop him at all costs!
        {
            if(computerPlayerCoins() > humanPlayerCoins())
            {
                bidAmount = humanPlayerCoins();
            }
            else
            {
                bidAmount = computerPlayerCoins() - 5;
            }
        }
        if(humanMovesToWin == 2)
        {
            if((previousHumanBids[movesPlayed]- previousHumanBids[movesPlayed-1]) < 5)
            {
                bidAmount = previousHumanBids[movesPlayed] + 1;
            }
        }
        if (bidAmount > computerPlayerCoins())
        {
            bidAmount = computerPlayerCoins();
        }
        movesPlayed++;
        return bidAmount;
        }
    
    public int computerMovesToWin()
    {
        
        return computerMovesToWin;
    }
    
}
