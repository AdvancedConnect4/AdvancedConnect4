package connect4;

import java.util.Scanner;


public class Logic
{
    ComputerPlayer compPlayer = new ComputerPlayer(); 
    public int yellow = 0; //This is for the getNextPlayer method. If the input is 
    public int red = 1;
    private static int humanMovesPlayed =0;
    public int tie = 2;
    private static int[] humanPlayerBids = new int[43];
    public static int yellowCoins = 100;
    public static int redCoins = 100;
    private int nextRedMove;
    
    ComputerPlayer comp = new ComputerPlayer();
    
    public static int getMovesPlayed()
    {
        return humanMovesPlayed;
    }

    public int[] getHumanBids()
    {
        return humanPlayerBids;
    }
    public void setNextRedMove( int nextMove )
    {
        nextRedMove = nextMove;
    }

    public int setRedCoins( int subtract )
    {
        return red;
    }

    public static int getRedCoins()
    {
        return redCoins;
    }

    public static int getYellowCoins()
    {
        return yellowCoins;
    }

    public int[] getHumanPlayerBids()
    {
        return humanPlayerBids;
    }

    public String[][] createGrid()
    {
        String[][] grid = new String[7][15];
        for ( int row = 0; row < grid.length; row++ )
        {
            for ( int col = 0; col < grid[row].length; col++ )
            {
                if ( col % 2 == 0 )
                {
                    grid[row][col] = "|";
                }
                else
                {
                    grid[row][col] = " ";
                }
                if ( row == 6 )
                {
                    grid[row][col] = "-";
                }
            }
        }
        return grid;
    }


    public void printGrid( String[][] grid )
    {
        for ( int row = 0; row < grid.length; row++ )
        {
            for ( int col = 0; col < grid[row].length; col++ )
            {
                System.out.print( grid[row][col] );
            }
            System.out.println();
        }
    }


    public int getNextPlayer()
    {
//         System.out.println("How much to bid(red): "); 
//         Scanner scan = new Scanner(System.in); 
//         int redPlayer = scan.nextInt();

       int computerRed = compPlayer.bidAmount();

        if (computerRed > redCoins )
        {
            computerRed = 0;
        }
        redCoins = getRedCoins() - computerRed;

        System.out.println( "How much to bid(yellow): " );
        Scanner scan = new Scanner( System.in );
        int playerYellow = scan.nextInt();
        
        if ( playerYellow > yellowCoins )
        {
            System.out.println( "You don't have that many coins. This bid is invalid and "
                + "your turn is skipped" );
            playerYellow = 0;
        }
        
        yellowCoins = yellowCoins - playerYellow;
        
        humanPlayerBids[humanMovesPlayed] = playerYellow;
        humanMovesPlayed++;
        
        if ( playerYellow > computerRed )
        {
            return yellow;
        }
        else if ( playerYellow == computerRed )
        {
            return tie;
        }
        else
        {
            return red;
        }

    }
    
    


//    public void dropRedPattern( String[][] grid )
//    {
//        System.out.println( "Drop a red token in column (0 -6): " );
//
//        Scanner scan = new Scanner( System.in );
//        nextRedMove = scan.nextInt();
//
//        int changeToOdd = ( 2 * nextRedMove ) + 1;
//
//        for ( int row = 5; row >= 0; row-- )
//        {
//            if ( grid[row][changeToOdd] == " " )
//            {
//                grid[row][changeToOdd] = "R";
//                break;
//            }
//        }
//
//    }

    public void dropRedPatternAI( String[][] grid )
    {
        int changeToOdd = checkYellowVertical(grid);

        for(int row = 5; row> 0; row--)
        {
            if(grid[row][changeToOdd] == " ")
            {
                grid[row][changeToOdd] = "R";
                break;
            }
        }

    }
    
   


    public void dropYellowPattern( String[][] grid )
    {
        System.out.println( "Drop a yellow disk at column (0–6): " );

        Scanner scan = new Scanner( System.in );

        int changeToOdd = 2 * scan.nextInt() + 1;

        for ( int row = 5; row >= 0; row-- )
        {
            if ( grid[row][changeToOdd] == " " )
            {
                grid[row][changeToOdd] = "Y";
                break;
            }
        }

    }
    
   public int checkRedTwoInaRow(String [][] grid)
   {
       for ( int row = 0; row < 6; row++ )
       {
           for ( int col = 0; col < 7; col += 2 )
           {
                if ((grid[row][col + 1] != " ") && (grid[row][col + 3] != " ")
                        && (grid[row][col + 5] == " ")
                   && ( ( grid[row][col + 1] == "R")) && (grid[row][col + 3] == "R" ))
                   return col +5;
           }
       }

       for ( int row = 1; row < 15; row += 2 )
       {
           for ( int col = 0; col < 3; col++ )
           {
                if ((grid[col][row] != " ") && (grid[col + 1][row] != " ")
                        && (grid[col + 2][row] == " ")
                   && ((grid[col][row] == "R")) && (grid[col + 1][row] == "R"))
                   return col + 2;
           }
       }

       for ( int row = 0; row < 3; row++ )
       {
           for ( int col = 1; col < 9; col += 2 )
           {
                if ((grid[row][col] != " ") && (grid[row + 1][col + 2] != " ")
                        && (grid[row + 2][col + 4] == " ")
                   && ( ( grid[row][col] == "R")) && (grid[row + 1][col + 2] == "R"))
                   return col + 4;
           }
       }

       for ( int row = 0; row < 3; row++ )
       {
           for ( int col = 7; col < 15; col += 2 )
           {
                if ((grid[row][col] != " ") && (grid[row + 1][col - 2] != " ")
                        && (grid[row + 2][col - 4] == " ")
                        && ((grid[row][col] == "R")) && (grid[row + 1][col - 2] == "R"))
                   return col - 4;
           }
       }
       return 8;
   }
//   
//   /**
//    * What is the row that the move will be returned at?
//    * TODO Write your method description here.
//    * @param grid
//    * @return
//    */
//   public int checkRedTwoInaRowMove(String [][] grid)
//   {
//       for ( int row = 0; row < 6; row++ )
//       {
//           for ( int col = 0; col < 7; col += 2 )
//           {
//                if ((grid[row][col + 1] != " ") && (grid[row][col + 3] != " ")
//                        && (grid[row][col + 5] == " ")
//                   && ( ( grid[row][col + 1] == "R")) && (grid[row][col + 3] == "R" ))
//                   return ;
//           }
//       }
//
//       for ( int row = 1; row < 15; row += 2 )
//       {
//           for ( int col = 0; col < 3; col++ )
//           {
//                if ((grid[col][row] != " ") && (grid[col + 1][row] != " ")
//                        && (grid[col + 2][row] == " ")
//                   && ((grid[col][row] == "R")) && (grid[col + 1][row] == "R"))
//                   return grid[col + 2][row];
//           }
//       }
//
//       for ( int row = 0; row < 3; row++ )
//       {
//           for ( int col = 1; col < 9; col += 2 )
//           {
//                if ((grid[row][col] != " ") && (grid[row + 1][col + 2] != " ")
//                        && (grid[row + 2][col + 4] == " ")
//                   && ( ( grid[row][col] == "R")) && (grid[row + 1][col + 2] == "R"))
//                   return grid[row + 2][col + 4];
//           }
//       }
//
//       for ( int row = 0; row < 3; row++ )
//       {
//           for ( int col = 7; col < 15; col += 2 )
//           {
//                if ((grid[row][col] != " ") && (grid[row + 1][col - 2] != " ")
//                        && (grid[row + 2][col - 4] == " ")
//                        && ((grid[row][col] == "R")) && (grid[row + 1][col - 2] == "R"))
//                   return grid[row + 2][col - 4];
//           }
//       }
//       return null;
//   }
//   
 
   public Integer checkYellowVertical(String[][] grid)
   {
       for ( int row = 1; row < 15; row += 2 )
       {
           for ( int col = 0; col < 3; col++ )
           {
               if ( ( grid[col][row] != " " ) && ( grid[col + 1][row] != " " )
                   && ( grid[col + 2][row] != " " ) && ( grid[col + 3][row] != " " )
                   && ( ( grid[col][row] == grid[col + 1][row] )
                       && ( grid[col + 1][row] == grid[col + 2][row] )
                       && ( grid[col + 2][row] == grid[col + 3][row] ) ) )
                   return row;
           }
       }
       
       return 5;
   }
   public int checkYellowTwoInaRow(String [][] grid)
   {
       //checks horizontal
       for ( int row = 0; row < 6; row++ )
       {
           for ( int col = 0; col < 7; col += 2 )
           {
                if ((grid[row][col + 1] != " ") && (grid[row][col + 3] != " ")
                        && (grid[row][col + 5] == " ")
                   && ( ( grid[row][col + 1] == "Y")) && (grid[row][col + 3] == "Y" ))
                   return col + 5;
           }
       }

       for ( int row = 1; row < 15; row += 2 )
       {
           for ( int col = 0; col < 3; col++ )
           {
               if ( ( grid[col][row] != " " ) && ( grid[col + 1][row] != " " )
                   && ( grid[col + 2][row] != " " ) && ( grid[col + 3][row] != " " )
                   && ( ( grid[col][row] == grid[col + 1][row] )
                       && ( grid[col + 1][row] == grid[col + 2][row] )
                       && ( grid[col + 2][row] == grid[col + 3][row] ) ) )
                   return row;
           }
       }

       for ( int row = 0; row < 3; row++ )
       {
           for ( int col = 1; col < 9; col += 2 )
           {
                if ((grid[row][col] != " ") && (grid[row + 1][col + 2] != " ")
                        && (grid[row + 2][col + 4] == " ")
                   && ( ( grid[row][col] == "Y")) && (grid[row + 1][col + 2] == "Y"))
                   return col + 4;
           }
       }

       for ( int row = 0; row < 3; row++ )
       {
           for ( int col = 7; col < 15; col += 2 )
           {
                if ((grid[row][col] != " ") && (grid[row + 1][col - 2] != " ")
                        && (grid[row + 2][col - 4] == " ")
                        && ((grid[row][col] == "Y")) && (grid[row + 1][col - 2] == "Y"))
  
                   return col - 4;
           }
       }
       return 8;
   }
   
   
   public String checkRedThreeInaRow(String [][] grid)
   {
       for ( int row = 0; row < 6; row++ )
       {
           for ( int col = 0; col < 7; col += 2 )
           {
               if ( ( grid[row][col + 1] != " " ) && ( grid[row][col + 3] != " " )
                   && ( grid[row][col + 5] != " " ) && ( grid[row][col + 7] == " ")
                   && ( ( grid[row][col + 1] == "R")) && ( grid[row][col + 3] == "R")
                       &&  (grid[row][col + 5] == "R"))
                   return grid[row][col + 7];
           }
       }

       for ( int row = 1; row < 15; row += 2 )
       {
           for ( int col = 0; col < 3; col++ )
           {
               if ( ( grid[col][row] != " " ) && ( grid[col + 1][row] != " " )
                   && ( grid[col + 2][row] != " " ) && (grid[col + 3][row] == " ")
                   && ( ( grid[col][row] == "R"))
                       && ( grid[col + 1][row] == "R") &&(grid[col + 2][row] =="R"))
                   return grid[col + 3][row];
           }
       }

       for ( int row = 0; row < 3; row++ )
       {
           for ( int col = 1; col < 9; col += 2 )
           {
               if ( ( grid[row][col] != " " ) && ( grid[row + 1][col + 2] != " " )
                   && ( grid[row + 2][col + 4] != " " ) && (grid[row + 3][col + 6] == " ")
                   && ( ( grid[row][col] == "R")) &&  (grid[row + 1][col + 2] == "R")
                       && (grid[row + 2][col + 4] == "R"))
                   return grid[row + 3][col + 6];
           }
       }

       for ( int row = 0; row < 3; row++ )
       {
           for ( int col = 7; col < 15; col += 2 )
           {
               if ( ( grid[row][col] != " " ) && ( grid[row + 1][col - 2] != " " )
                   && ( grid[row + 2][col - 4] != " " ) && (grid[row + 3][col - 6] == " ")
                   && ( ( grid[row][col] == "R")) && ( grid[row + 1][col - 2] == "R" )
                       && (grid[row + 2][col - 4]) == "R")
                   return grid[row + 3][col - 6];
           }
       }
       return null;
   }
   
   public int checkYellowThreeInaRow(String [][] grid)
   {
       for ( int row = 0; row < 6; row++ )
       {
           for ( int col = 0; col < 7; col += 2 )
           {
               if ( ( grid[row][col + 1] != " " ) && ( grid[row][col + 3] != " " )
                   && ( grid[row][col + 5] != " " ) && ( grid[row][col + 7] == " ")
                   && ( ( grid[row][col + 1] == "Y")) && ( grid[row][col + 3] == "Y")
                       &&  (grid[row][col + 5] == "Y"))
                   return col + 7;
           }
       }

       for ( int row = 1; row < 15; row += 2 )
       {
           for ( int col = 0; col < 3; col++ )
           {
               if ( ( grid[col][row] != " " ) && ( grid[col + 1][row] != " " )
                   && ( grid[col + 2][row] != " " ) && (grid[col + 3][row] == " ")
                   && ( ( grid[col][row] == "Y"))
                       && ( grid[col + 1][row] == "Y") &&(grid[col + 2][row] =="Y"))
                   return col + 3;
           }
       }

       for ( int row = 0; row < 3; row++ )
       {
           for ( int col = 1; col < 9; col += 2 )
           {
               if ( ( grid[row][col] != " " ) && ( grid[row + 1][col + 2] != " " )
                   && ( grid[row + 2][col + 4] != " " ) && (grid[row + 3][col + 6] == " ")
                   && ( ( grid[row][col] == "Y")) &&  (grid[row + 1][col + 2] == "Y")
                       && (grid[row + 2][col + 4] == "Y"))
                   return col + 6;
           }
       }

       for ( int row = 0; row < 3; row++ )
       {
           for ( int col = 7; col < 15; col += 2 )
           {
               if ( ( grid[row][col] != " " ) && ( grid[row + 1][col - 2] != " " )
                   && ( grid[row + 2][col - 4] != " " ) && (grid[row + 3][col - 6] == " ")
                   && ( ( grid[row][col] == "Y")) && ( grid[row + 1][col - 2] == "Y" )
                       && (grid[row + 2][col - 4]) == "Y")
                   return col - 6;
           }
       }
       return 8;
   }




    public String checkWinner( String[][] grid )
    {
        for ( int row = 0; row < 6; row++ )
        {
            for ( int col = 0; col < 7; col += 2 )
            {
                if ( ( grid[row][col + 1] != " " ) && ( grid[row][col + 3] != " " )
                    && ( grid[row][col + 5] != " " ) && ( grid[row][col + 7] != " " )
                    && ( ( grid[row][col + 1] == grid[row][col + 3] )
                        && ( grid[row][col + 3] == grid[row][col + 5] )
                        && ( grid[row][col + 5] == grid[row][col + 7] ) ) )
                    return grid[row][col + 1];
            }
        }

        for ( int row = 1; row < 15; row += 2 )
        {
            for ( int col = 0; col < 3; col++ )
            {
                if ( ( grid[col][row] != " " ) && ( grid[col + 1][row] != " " )
                    && ( grid[col + 2][row] != " " ) && ( grid[col + 3][row] != " " )
                    && ( ( grid[col][row] == grid[col + 1][row] )
                        && ( grid[col + 1][row] == grid[col + 2][row] )
                        && ( grid[col + 2][row] == grid[col + 3][row] ) ) )
                    return grid[col][row];
            }
        }

        for ( int row = 0; row < 3; row++ )
        {
            for ( int col = 1; col < 9; col += 2 )
            {
                if ( ( grid[row][col] != " " ) && ( grid[row + 1][col + 2] != " " )
                    && ( grid[row + 2][col + 4] != " " ) && ( grid[row + 3][col + 6] != " " )
                    && ( ( grid[row][col] == grid[row + 1][col + 2] )
                        && ( grid[row + 1][col + 2] == grid[row + 2][col + 4] )
                        && ( grid[row + 2][col + 4] == grid[row + 3][col + 6] ) ) )
                    return grid[row][col];
            }
        }

        for ( int row = 0; row < 3; row++ )
        {
            for ( int col = 7; col < 15; col += 2 )
            {
                if ( ( grid[row][col] != " " ) && ( grid[row + 1][col - 2] != " " )
                    && ( grid[row + 2][col - 4] != " " ) && ( grid[row + 3][col - 6] != " " )
                    && ( ( grid[row][col] == grid[row + 1][col - 2] )
                        && ( grid[row + 1][col - 2] == grid[row + 2][col - 4] )
                        && ( grid[row + 2][col - 4] == grid[row + 3][col - 6] ) ) )
                    return grid[row][col];
            }
        }
        return null;
    }

}
