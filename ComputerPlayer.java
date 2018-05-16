package connect4;

import java.util.Scanner;

public class ComputerPlayer 
{
    public static Logic testLogic = new Logic();
    static int computerPlayerCoins;
    static int humanPlayerCoins;
    static int bidAmount;
    static int computerMovesToWin;
    static int humanMovesToWin;
    static int nextMove = 1;

    
    public static int humanPlayerCoins()
    {
        return testLogic.getYellowCoins();

    }
    
    public static int computerPlayerCoins()
    {
        computerPlayerCoins = testLogic.getRedCoins();
        return computerPlayerCoins;
    }
    
    
    public void setNextMove()
    {
        testLogic.setNextRedMove(nextMove);
    }
    
    public int bidTokens() //Math.random()
    {  
        bidAmount = (int) (Math.random() * testLogic.getRedCoins());
        return bidAmount;
        
    }
    
    
    public int computerMovesToWin()
    {
        
        return 2;
    }
    
}

