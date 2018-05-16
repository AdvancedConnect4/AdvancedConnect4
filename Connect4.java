package connect4;

public class ConnectFour
{
    public static int yellow = 0;
    public static int red = 1;
    public static int tie = 2;
    public static int yellowCoins = 100;
    public static int redCoins = 100;
        
static Logic logic = new Logic();
public static void main (String[] args)
{
  String[][] grid = logic.createGrid();
  boolean loop = true;
  logic.printGrid(grid);
  while(loop)
  {
      int playerTurn = logic.getNextPlayer();
      
      if(playerTurn == red)
      {
          logic.dropRedPatternAI(grid);
          System.out.println( "Yellow has " + Integer.valueOf(logic.getYellowCoins()) + " coins left");
          System.out.println( "Red has " + Integer.valueOf(logic.getRedCoins()) + " coins left");
      } 
      else if (playerTurn == yellow) 
      {
         logic.dropYellowPattern(grid);
         System.out.println( "Yellow has " + Integer.valueOf(logic.getYellowCoins()) + " coins left");
         System.out.println( "Red has " + Integer.valueOf(logic.getRedCoins()) + " coins left");
     }
     else
     {
         System.out.println("There was a tie");
         System.out.println( "Yellow has " + Integer.valueOf(logic.getYellowCoins() ) + " coins left");
         System.out.println( "Red has " + Integer.valueOf(logic.getRedCoins()) + " coins left");
     }
     logic.printGrid(grid);
     
     if (logic.checkWinner(grid) != null)
     {
        if (logic.checkWinner(grid) == "R")
        {
            System.out.println("The red player won.");
            
        }           
        else if (logic.checkWinner(grid)== "Y")
        {
            System.out.println("The yellow player won.");
        }
       loop = false;
  }
  }
}



}
