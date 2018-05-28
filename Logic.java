package connect4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Logic

{
  public static int humanCoins = 100;
    
  public static String[][] makeGrid()
  {
    String[][] grid = new String[6][7];
    
    for (int row =0; row < grid.length; row++)
    {
        for (int col =0; col <grid[row].length; col++)
        {
            if (row==6)
                grid[row][col]= "-";
            else
                grid[row][col] = " ";
        }
    }
    return grid;
  }

  public static void printPatternWithGrid(String[][] grid)
  {
      String[][] gridArray = new String[6][15];

      for (int row =0; row < gridArray.length; row++)
      {
        for (int col=0; col< gridArray[row].length; col++)            
        {
            gridArray[row][col] = " ";
            if (col % 2 == 0)
                gridArray[row][col] = "|";
            else
                gridArray[row][col] =  grid[row][col/2];
          //System.out.print(grid[row][col]);
        }

        //System.out.println();

      }
      
    for (int row =0; row < gridArray.length; row++)
    {
      for (int col=0; col< gridArray[row].length; col++)
      {
        System.out.print(gridArray[row][col]);
      }

      System.out.println();

    }
     System.out.println("\n_____________________________\n");

  }
  
  public static void printPatternWithoutGrid(String[][] grid)
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

  
  
  public static void dropYellowPattern(String[][] grid)
  {
      Scanner scan = new Scanner(System.in);
      int number;
      do {
          System.out.print("Drop a yellow disk at column (0–6): ");
          while (!scan.hasNextInt()) {
              System.out.println("That's not a number!");
              scan.next(); // this is important!
          }
          number = scan.nextInt();
    } while (number < 0 || number > 6);
    System.out.println("Thank you! dropping Yellow at : " + number);
      
    int changeToOdd = number;//2*number+1;
    for (int row  =5; row>=0; row--)
    {
        if (grid[row][changeToOdd] == " " || grid[row][changeToOdd] == "|")
        {
            grid[row][changeToOdd] = "Y";
            break;
        }
    }
  }
  
  public static void dropYellowPatternGUI( String[][] grid, int number )
  {
      while ( number < 0 || number > 6 );
      System.out.println( "Thank you! dropping Yellow at : " + number );

      int changeToOdd = number;// 2*number+1;
      for ( int row = 5; row >= 0; row-- )
      {
          if ( grid[row][changeToOdd] == " " || grid[row][changeToOdd] == "|" )
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
          for (int col =0; col<4; col++)
          {
              if ((f[row][col] != " ") && (f[row][col + 1] != " ") && (f[row][col + 2] != " ")
                      && (f[row][col + 3] != " ") && ((f[row][col] == f[row][col + 1])
                              && (f[row][col + 1] == f[row][col + 2])&& (f[row][col + 2] == f[row][col + 3])))
              {
                  return f[row][col]; 
              }
          }
      }

      //checks vertical
      for (int col=0; col<7; col++)
      {
          for (int row =0; row<3; row++)
          {
            if((f[row][col] != " ") && (f[row+1][col] != " ") && (f[row+2][col] != " ")
                    && (f[row+3][col] != " ") && ((f[row][col] == f[row+1][col]) && (f[row+1][col] == f[row+2][col])
                            && (f[row+2][col] == f[row+3][col])))
            {
                return f[row][col]; 
            }
          } 
      }

    //checks left to right diagonal
    for (int row=0;row<3;row++)
    {
      for (int col=0;col<4;col++)
      {
            if((f[row][col] != " ") && (f[row+1][col+1] != " ")
            && (f[row+2][col+2] != " ") && (f[row+3][col+3] != " ")
            && ((f[row][col] == f[row+1][col+1]) && (f[row+1][col+1] == f[row+2][col+2])
            && (f[row+2][col+2] == f[row+3][col+3])))
            {
              return f[row][col]; 
            }

      } 

    } 
    //checks right to left diagonal
    for (int row=0; row<3; row++)
    {
        for (int col=6; col>2; col--)
        {
            if((f[row][col] != " ") && (f[row+1][col-1] != " ")&& (f[row+2][col-2] != " ")
                    && (f[row+3][col-3] != " ")  && ((f[row][col] == f[row+1][col-1])
                            && (f[row+1][col-1] == f[row+2][col-2]) && (f[row+2][col-2] == f[row+3][col-3])))
            {
                return f[row][col]; 
            }
      } 

    }
    if (tie(f))
        return "T";
    return "N"; //none
  }
  
  private static boolean tie(String[][] grid) {      
      for (int row =0; row < grid.length; row++)
      {
        for (int col=0; col< grid[row].length; col++)
        {
            if (grid[row][col] == " ")
                return false;
        }
        
      }
      return true;
  }
}
