package connect4;

public class ConnectFour
{
    public static int yellow = 0;
    public static int red = 1;
    public static int tie = 2;
    public static int yellowCoins = 100;
    public static int redCoins = 100;
    static Logic logic = new Logic();
    public static String[][] grid = logic.createGrid();



public static void main (String[] args)
{

  boolean loop = true;
  logic.printGrid(grid);
  while(loop)
  {
      int playerTurn = logic.getNextPlayer();
      
      if(playerTurn == red)
      {
          logic.dropRedPatternAI(grid);
          System.out.println( "Yellow has " + Integer.valueOf(Logic.getYellowCoins()) + " coins left");
          System.out.println( "Red has " + Integer.valueOf(Logic.getRedCoins()) + " coins left");
      } 
      else if (playerTurn == yellow) 
      {
         logic.dropYellowPattern(grid);
         System.out.println( "Yellow has " + Integer.valueOf(Logic.getYellowCoins()) + " coins left");
         System.out.println( "Red has " + Integer.valueOf(Logic.getRedCoins()) + " coins left");
     }
     else
     {
         System.out.println("There was a tie");
         System.out.println( "Yellow has " + Integer.valueOf(Logic.getYellowCoins() ) + " coins left");
         System.out.println( "Red has " + Integer.valueOf(Logic.getRedCoins()) + " coins left");
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

public String[][] getGrid()
{
     return grid;   
}

public String getMiddlePosition()
{
    return grid[0][8];
}



}
