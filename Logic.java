package connect4;

import java.util.Scanner;


public class Logic

{
    public static int humanCoins = 100;

    ConnectFour connectfour = new ConnectFour();


    public static String[][] makeGrid()
    {
        // String[][] grid = new String[7][15];
        String[][] grid = new String[6][7];

        for ( int row = 0; row < grid.length; row++ )
        {
            for ( int col = 0; col < grid[row].length; col++ )
            {
                /*
                 * if (col% 2 == 0) { grid[row][col] ="|"; } else {
                 * grid[row][col] = " "; }
                 */
                if ( row == 6 )
                {
                    grid[row][col] = "-";
                }
                else
                    grid[row][col] = " ";
            }

        }

        return grid;

    }


    public static String[][] makeGridOld()
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


    public static void printPatternWithGrid( String[][] grid )

    {
        String[][] gridArray = new String[6][15];

        for ( int row = 0; row < gridArray.length; row++ )
        {
            for ( int col = 0; col < gridArray[row].length; col++ )
            {
                gridArray[row][col] = " ";
                if ( col % 2 == 0 )
                    gridArray[row][col] = "|";
                else
                    gridArray[row][col] = grid[row][col / 2];
                // System.out.print(grid[row][col]);
            }

            // System.out.println();

        }

        for ( int row = 0; row < gridArray.length; row++ )
        {
            for ( int col = 0; col < gridArray[row].length; col++ )
            {
                System.out.print( gridArray[row][col] );
            }

            System.out.println();

        }
        System.out.println( "\n_____________________________\n" );

    }


    public static void printPatternWithoutGrid( String[][] grid )

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




    public static int getYellowCoins()
    {
        return humanCoins;
    }


    public static int getYellowPlayerBid()
    {
        System.out.println( "How much to bid: " );
        Scanner scan = new Scanner( System.in );
        int bid = scan.nextInt();
        if ( bid > humanCoins )
        {
            return 0;
        }
        else if ( bid < 0 )
        {
            return 0;
        }
        humanCoins -= bid;
        return bid;
    }


    public static void dropYellowPattern( String[][] grid )
    {
        Scanner scan = new Scanner( System.in );
        int number;
        do
        {
            System.out.print( "Drop a yellow disk at column (0–6): " );
            while ( !scan.hasNextInt() )
            {
                System.out.println( "That's not a number!" );
                scan.next(); // this is important!
            }
            number = scan.nextInt();
        } 
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

    public static String checkWinner( String[][] f )

    {
        // checks horizontal
        for ( int row = 0; row < 6; row++ )

        {
            for ( int col = 0; col < 4; col++ )
            {
                if ( ( f[row][col] != " " ) && ( f[row][col + 1] != " " )
                    && ( f[row][col + 2] != " " ) && ( f[row][col + 3] != " " )
                    && ( ( f[row][col] == f[row][col + 1] )
                        && ( f[row][col + 1] == f[row][col + 2] )
                        && ( f[row][col + 2] == f[row][col + 3] ) ) )
                {
                    return f[row][col];

                }
            }

        }

        // checks vertical
        for ( int row = 1; row < 7; row += 2 )

        {
            for ( int col = 0; col < 3; col++ )

            {

                if ( ( f[col][row] != " " ) && ( f[col + 1][row] != " " )
                    && ( f[col + 2][row] != " " ) && ( f[col + 3][row] != " " )
                    && ( ( f[col][row] == f[col + 1][row] )
                        && ( f[col + 1][row] == f[col + 2][row] )
                        && ( f[col + 2][row] == f[col + 3][row] ) ) )
                {
                    return f[col][row];
                }
            }
        }

        // checks left to right diagonal
        for ( int row = 0; row < 3; row++ )
        {
            for ( int col = 1; col < 4; col++ )
            {
                if ( ( f[row][col] != " " ) && ( f[row + 1][col + 1] != " " )
                    && ( f[row + 2][col + 2] != " " ) && ( f[row + 3][col + 3] != " " )
                    && ( ( f[row][col] == f[row + 1][col + 1] )
                        && ( f[row + 1][col + 1] == f[row + 2][col + 2] )
                        && ( f[row + 2][col + 2] == f[row + 3][col + 3] ) ) )
                {
                    return f[row][col];
                }

            }

        }
        // checks right to left diagonal
        for ( int i = 0; i < 3; i++ )

        {
            for ( int j = 7; j < 6; j++ )
            {
                if ( ( f[i][j] != " " ) && ( f[i + 1][j - 1] != " " ) && ( f[i + 2][j - 2] != " " )
                    && ( f[i + 3][j - 3] != " " )
                    && ( ( f[i][j] == f[i + 1][j - 1] ) && ( f[i + 1][j - 1] == f[i + 2][j - 2] )
                        && ( f[i + 2][j - 2] == f[i + 3][j - 3] ) ) )
                {
                    return f[i][j];
                }

            }

        }

        return null;

    }

}
