package connect4;

import java.util.Scanner;


public class Logic

{
    public static int humanCoins = 100;
    
  public static String[][] makeGrid()
  {
    String[][] grid = new String[7][15];
    
    for (int row =0; row < grid.length; row++)
    {
      for (int col =0; col <grid[row].length; col++)
      {
        if (col% 2 == 0)
        {
            grid[row][col] ="|";
        }
        else 
        {
            grid[row][col] = " ";
        }
        if (row==6)
        {
            grid[row][col]= "-";
        }
      }


    }

    return grid;

  }

  public static void printPattern(String[][] grid)

  {
    for (int row =0; row < grid.length; row++)
    {
      for (int col=0; col< grid[row].length; col++)
      {
        System.out.print(grid[row][col]);
      }

      System.out.println();

    }

  }


  public static void dropRedPattern(String[][] f)

  {
    System.out.println("Drop a red disk at column (0–6): ");
    Scanner scan = new Scanner (System.in);

    int changeToOdd = 2*scan.nextInt()+1;
    
    for (int row =5; row>=0; row--)
    {
      if (f[row][changeToOdd] == " ")

      {

        f[row][changeToOdd] = "R";

        break;

      }


    }

  }
  public static int getYellowCoins()
  {
      return humanCoins;
  }
  
  
  public static int getYellowPlayerBid()
  {
      System.out.println("How much to bid: ");
      Scanner scan = new Scanner (System.in);
      int bid = scan.nextInt();
      if(bid > humanCoins)
      {
          return 0;
      }
      else if (bid < 0)
      {
          return 0;
      }
      humanCoins -= bid;
      return bid;
  }
  
  
  public static void dropYellowPattern(String[][] grid)
  {
    System.out.println("Drop a yellow disk at column (0–6): ");
    int changeToOdd;
    Scanner scan = new Scanner (System.in);
    changeToOdd = scan.nextInt();
    if (changeToOdd > 6) //if the column to play is not in play
    {
        changeToOdd = 13;
    }
    else if(changeToOdd < 0) // if column is not in play go at 0
    {
        changeToOdd = 1;
    }
    else 
    {
        changeToOdd = 2*scan.nextInt()+1;
    }


    for (int row=5 ; row>=0; row--)
    {
      if (grid[row][changeToOdd] == " ")
      {
        grid[row][changeToOdd] = "Y";
        break;
      }
    }
  }

  public static String checkWinner(String[][] f)

  {
//checks horizontal
    for (int row =0; row<6; row++)

    {
      for (int col =0; col<7; col+=2)
      {
         if ((f[row][col + 1] != " ") && (f[row][col + 3] != " ") && (f[row][col + 5] != " ")
         && (f[row][col + 7] != " ") && ((f[row][col + 1] == f[row][col + 3])
         && (f[row][col + 3] == f[row][col + 5])&& (f[row][col + 5] == f[row][col + 7])))
         {
             return f[row][col+1]; 

         }
      }

    }

    //checks vertical
    for (int row=1; row<15; row+=2)

    {
      for (int col =0; col<3; col++)

      {

            if((f[col][row] != " ") && (f[col+1][row] != " ") && (f[col+2][row] != " ")
            && (f[col+3][row] != " ") && ((f[col][row] == f[col+1][row]) && (f[col+1][row] == f[col+2][row])
            && (f[col+2][row] == f[col+3][row])))
            {
              return f[col][row]; 
            }
      } 
    }

    //checks left to right diagonal
    for (int row=0;row<3;row++)
    {
      for (int col=1;col<9;col+=2)
      {
            if((f[row][col] != " ") && (f[row+1][col+2] != " ")
            && (f[row+2][col+4] != " ") && (f[row+3][col+6] != " ")
            && ((f[row][col] == f[row+1][col+2]) && (f[row+1][col+2] == f[row+2][col+4])
            && (f[row+2][col+4] == f[row+3][col+6])))
            {
              return f[row][col]; 
            }

      } 

    } 
    //checks right to left diagonal
    for (int i=0;i<3;i++)

    {
      for (int j=7;j<15;j+=2)
      {
            if((f[i][j] != " ") && (f[i+1][j-2] != " ")&& (f[i+2][j-4] != " ")
            && (f[i+3][j-6] != " ")  && ((f[i][j] == f[i+1][j-2])
            && (f[i+1][j-2] == f[i+2][j-4]) && (f[i+2][j-4] == f[i+3][j-6])))
            {
              return f[i][j]; 
            }

      } 

    }

    return null;

  }


  
}
