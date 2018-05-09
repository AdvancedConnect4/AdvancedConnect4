package connect4;
import java.util.Scanner;

public class ConnectFour
{
    public static int yellow = 0;
    public static int red = 1;
    public static int tie = 2;
    public static int yellowCoins = 100;
    public static int redCoins = 100;
    
    public int getYellowCoins()
    {
        return yellowCoins;
    }
    
public static String[][] createGrid()
{
   String[][] grid = new String[7][15];
  for (int i =0;i<grid.length;i++)
  {
     for (int x =0; x<grid[i].length;x++)
    {
      if (x% 2 == 0)
      {
          grid[i][x] ="|";
      }
      else
      {
          grid[i][x] = " ";
      }
      if (i==6) 
      {
          grid[i][x]= "-";
      }
    }
  }
  return grid;
}




public static void printGrid(String[][] grid)
{
  for (int i =0;i< grid.length;i++)
  {
    for (int x=0; x< grid[i].length; x++)
    {
      System.out.print(grid[i][x]);
    }
    System.out.println();
  }
}


public static int getNextPlayer()
{
    System.out.println("How much to bid(red): ");
    Scanner scan = new Scanner(System.in);
    int playerRed = scan.nextInt();
    if (playerRed > redCoins)
    {
        System.out.println( "You don't have that many coins. This bid is invalid and "
            + "your turn is skipped" );
        playerRed = 0;
    }
    redCoins = redCoins - playerRed;
    
    System.out.println("How much to bid(yellow): ");
    int playerYellow = scan.nextInt();
    if (playerYellow > yellowCoins)
    {
        System.out.println( "You don't have that many coins. This bid is invalid and "
                        + "your turn is skipped" );
        playerYellow = 0;
    }
    yellowCoins = yellowCoins - playerYellow;
    
    if(playerYellow > playerRed)
    {
        return yellow;
    }
    else if(playerYellow == playerRed)
    {
        return tie;
    }
    else
    {
        return red;
    }


}

public static void dropRedPattern(String[][] grid)
{
  System.out.println("Drop a red disk at column (0–6): ");
  Scanner scan = new Scanner (System.in);

  int c = 2*scan.nextInt()+1;
  
  for (int i =5;i>=0;i--)
  {
    if (grid[i][c] == " ")
    {
      grid[i][c] = "R";
      break;
    }
  }

}



public static void dropYellowPattern(String[][] grid)
{
  System.out.println("Drop a yellow disk at column (0–6): ");
  Scanner scan = new Scanner (System.in);
  int c = 2*scan.nextInt()+1;
  for (int i =5;i>=0;i--)
  {
    if (grid[i][c] == " ")
    {
      grid[i][c] = "Y";
      break;
    }
  }

}

public static String checkWinner(String[][] grid)
{
  for (int i =0;i<6;i++)
  {
    for (int x=0; x<7; x+=2)
    {
      if ((grid[i][x+1] != " ")
      && (grid[i][x+3] != " ")
      && (grid[i][x+5] != " ")
      && (grid[i][x+7] != " ")
      && ((grid[i][x+1] == grid[i][x+3])
      && (grid[i][x+3] == grid[i][x+5])
      && (grid[i][x+5] == grid[i][x+7])))
      return grid[i][x+1]; 
    }
  }

  for (int i=1;i<15;i+=2)
  {   
      for (int x =0;x<3;x++)
      {
          if((grid[x][i] != " ")
          && (grid[x+1][i] != " ")
          && (grid[x+2][i] != " ")
          && (grid[x+3][i] != " ")
          && ((grid[x][i] == grid[x+1][i])
          && (grid[x+1][i] == grid[x+2][i])
          && (grid[x+2][i] == grid[x+3][i])))
            return grid[x][i]; 
      } 
  }
 
  for (int i=0;i<3;i++)
  {
      for (int x=1;x<9;x+=2)
      {
          if((grid[i][x] != " ")
          && (grid[i+1][x+2] != " ")
          && (grid[i+2][x+4] != " ")
          && (grid[i+3][x+6] != " ")
          && ((grid[i][x] == grid[i+1][x+2])
          && (grid[i+1][x+2] == grid[i+2][x+4])
          && (grid[i+2][x+4] == grid[i+3][x+6])))
            return grid[i][x]; 
    } 
  }
 
  for (int i=0;i<3;i++)
  {
    for (int x=7;x<15;x+=2)
    {
          if((grid[i][x] != " ")
          && (grid[i+1][x-2] != " ")
          && (grid[i+2][x-4] != " ")
          && (grid[i+3][x-6] != " ")
          && ((grid[i][x] == grid[i+1][x-2])
          && (grid[i+1][x-2] == grid[i+2][x-4])
          && (grid[i+2][x-4] == grid[i+3][x-6])))
            return grid[i][x]; 
    } 
  }
  return null;
}



public static void main (String[] args)
{
  String[][] grid = createGrid();
  boolean loop = true;
  printGrid(grid);
  while(loop)
  {
      int playerTurn = getNextPlayer();
      if(playerTurn == red)
      {
          dropRedPattern(grid);
          System.out.println( "Yellow has " + Integer.valueOf( yellowCoins ) + " coins left");
          System.out.println( "Red has " + Integer.valueOf( redCoins ) + " coins left");
      } 
      else if (playerTurn == yellow) 
      {
         dropYellowPattern(grid);
         System.out.println( "Yellow has " + Integer.valueOf( yellowCoins ) + " coins left");
         System.out.println( "Red has " + Integer.valueOf( redCoins ) + " coins left");
     }
     else
     {
         System.out.println("There was a tie");
         System.out.println( "Yellow has " + Integer.valueOf( yellowCoins ) + " coins left");
         System.out.println( "Red has " + Integer.valueOf( redCoins ) + " coins left");
     }
     printGrid(grid);
     
     if (checkWinner(grid) != null)
     {
        if (checkWinner(grid) == "R")
        {
            System.out.println("The red player won.");
            
        }           
        else if (checkWinner(grid)== "Y")
        {
            System.out.println("The yellow player won.");
        }
       loop = false;
  }
  }
}

public int getRedCoins()
{
    return redCoins;
}

}
