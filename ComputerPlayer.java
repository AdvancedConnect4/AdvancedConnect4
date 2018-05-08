package connect4;

public class ComputerPlayer extends Player
{
    int computerPlayerCoins;
    int humanPlayerCoins;
    int bidAmount;
    int computerMovesToWin;
    int humanMovesToWin;
    public static int getBidAmount()
    {
        return bidAmount;
        
    }
    public int bidAmount()
    {
        if((computerMovesToWin == 1) && (computerPlayerCoins > humanPlayerCoins))
        {
            bidAmount = humanPlayerCoins + 1;
        }
        
        if((computerMovesToWin == 2) && (computerPlayerCoins > ((2 *humanPlayerCoins) + 1)))
        {
            bidAmount = humanPlayerCoins + 1;
        }
        
        if((computerMovesToWin == 3) && (computerPlayerCoins > ((3 *humanPlayerCoins) + 1)))
        {
            bidAmount = humanPlayerCoins + 1;
        }
        
        if((computerMovesToWin >= 4) && (computerPlayerCoins > ((4 *humanPlayerCoins) + 1)))
        {
            bidAmount = humanPlayerCoins + 1;
        }
        
        if((computerMovesToWin == 2) && (computerPlayerCoins > ((2 *humanPlayerCoins) + 1)))
        {
            bidAmount = humanPlayerCoins + 1;
        }
        
        if ((humanMovesToWin == 1)) //if the human is one step from winning, stop him at all costs!
        {
            if(computerPlayerCoins > humanPlayerCoins)
            {
                bidAmount = humanPlayerCoins;
            }
            else
            {
                bidAmount = computerPlayerCoins;
            }
        }
        
        if (bidAmount > computerPlayerCoins)
        {
            bidAmount = computerPlayerCoins;
        }
        return bidAmount;
    }
    
    public int computerMovesToWin()
    {
        
        return computerMovesToWin;
    }
}
