package connect4;

import java.util.Scanner;


public class Logic
{
    public int yellow = 0;

    public int red = 1;

    public int tie = 2;

    public int yellowCoins = 100;
    public int redCoins = 100;
    private int nextRedBid;
    private int nextRedMove;
    
    ComputerPlayer comp = new ComputerPlayer();


    public void setNextRedBid( int nextBid )
    {
        nextRedBid = nextBid;
    }

    public void setNextRedMove( int nextMove )
    {
        nextRedMove = nextMove;
    }

    public int setRedCoins( int subtract )
    {
        return red;
    }

    public int getRedCoins()
    {
        return redCoins;
    }

    public int getYellowCoins()
    {
        return yellowCoins;
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
//        
//         Scanner scan = new Scanner(System.in);
//        
//         int redPlayer = scan.nextInt();

       int computerRed = comp.bidTokens();
       
        
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
    
    


    public void dropRedPattern( String[][] grid )
    {
        System.out.println( "Drop a red token in column (0 -6): " );

        Scanner scan = new Scanner( System.in );
        nextRedMove = scan.nextInt();

        int changeToOdd = ( 2 * nextRedMove ) + 1;

        for ( int row = 5; row >= 0; row-- )
        {
            if ( grid[row][changeToOdd] == " " )
            {
                grid[row][changeToOdd] = "R";
                break;
            }
        }

    }

    public void dropRedPatternAI( String[][] grid )
    {
        int changeToOdd = ( 2 * nextRedMove ) + 1;

        for ( int row = 5; row >= 0; row-- )
        {
            if ( grid[row][changeToOdd] == " " )
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
    public String checkThreeInaRow(String [][] grid)
    {
        for ( int row = 0; row < 6; row++ )
        {
            for ( int col = 0; col < 7; col += 2 )
            {
                if ( ( grid[row][col + 1] != " " ) && ( grid[row][col + 3] != " " )
                    && ( grid[row][col + 5] != " " )
                    && ( ( grid[row][col + 1] == grid[row][col + 3] )
                        && ( grid[row][col + 3] == grid[row][col + 5])))
                    return grid[row][col + 7];
            }
        }

        for ( int row = 1; row < 15; row += 2 )
        {
            for ( int col = 0; col < 3; col++ )
            {
                if ( ( grid[col][row] != " " ) && ( grid[col + 1][row] != " " )
                    && ( grid[col + 2][row] != " " )
                    && ( ( grid[col][row] == grid[col + 1][row] )
                        && ( grid[col + 1][row] == grid[col + 2][row] )))
                    return grid[col + 3][row];
            }
        }

        for ( int row = 0; row < 3; row++ )
        {
            for ( int col = 1; col < 9; col += 2 )
            {
                if ( ( grid[row][col] != " " ) && ( grid[row + 1][col + 2] != " " )
                    && ( grid[row + 2][col + 4] != " " )
                    && ( ( grid[row][col] == grid[row + 1][col + 2] )
                        && ( grid[row + 1][col + 2] == grid[row + 2][col + 4])))
                    return grid[row + 3][col + 6];
            }
        }

        for ( int row = 0; row < 3; row++ )
        {
            for ( int col = 7; col < 15; col += 2 )
            {
                if ( ( grid[row][col] != " " ) && ( grid[row + 1][col - 2] != " " )
                    && ( grid[row + 2][col - 4] != " " )
                    && ( ( grid[row][col] == grid[row + 1][col - 2] )
                        && ( grid[row + 1][col - 2] == grid[row + 2][col - 4])))
                    return grid[row + 3][col - 6];
            }
        }
        return null;
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
