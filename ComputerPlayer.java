package connect4;

public class ComputerPlayer extends Player
{
    static ConnectFour test = new ConnectFour();
    static int computerPlayerCoins;
    static int humanPlayerCoins;
    static int bidAmount;
    static int computerMovesToWin;
    static int humanMovesToWin;
    
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
                bidAmount = computerPlayerCoins();
            }
        }
        
        if (bidAmount > computerPlayerCoins())
        {
            bidAmount = computerPlayerCoins();
        }
        return bidAmount;
    }
    
    public int computerMovesToWin()
    {
        
        return computerMovesToWin;
    }
    
}
