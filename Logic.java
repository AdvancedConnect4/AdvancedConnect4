package connect4;

import java.util.Scanner;

/**
 * 
 *  The logic class makes sure the connect 4 game is working as the rules of connect4 dictate.
 *  This class had many methods created for debugging through the console. In addition, this checks
 *  if there is a winner and if the move is valid. This class also helps place the moves that the
 *  yellow player plays.
 *
 *  @author  Albert Su
 *  @author  Arnav Gupta
 *  @version May 28, 2018
 *  @author  Period: 2
 *  @author  Assignment: FinalProject
 *
 *  @author  Sources: None
 */

public class Logic
{
    /**
     * 
     * Makes the grid by changing some squares to make it look like a grid
     * @return the grid[][]
     */
    public String[][] makeGrid()
    {
        String[][] grid = new String[6][7];

        for ( int row = 0; row < grid.length; row++ )
        {
            for ( int col = 0; col < grid[row].length; col++ )
            {
                if ( row == 6 )
                    grid[row][col] = "-";
                else
                    grid[row][col] = " ";
            }
        }
        return grid;
    }

    /**
     * 
     * This method prints the Grid to the console. This was for testing when the GUI didn't work. 
     * It uses two for loops to create the 6 by 15 grid which also includes the lines.
     * 
     * @param grid This is the current state of the grid.
     */
    public void printPatternWithGrid( String[][] grid )
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
        System.out.println( "\n___________________________\n" );

    }

    /**
     * 
     * We used this method to debug. Previously there was an error with the AI because the Grid's 
     * dimensions were actually 6 by 15 even though the playble area was 6 by 7. This did not print
     * the lines on the grid so it looks blank. This is so that the grid size is actually 6 by 7.
     * 
     * @param grid The current state of the grid.
     */
    public void printPatternWithoutGrid( String[][] grid )
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

    /**
     * 
     * Drops the Yellow checker in the right column. The scanner is the way we tested without the 
     * GUI. With the GUI, we would directly change the number variable to the column that we were 
     * to. We needed the for loop to make sure that the checker was dropped at the bottom-most
     * empty square on the grid.
     * 
     * @param grid Updated grid
     */
    public void dropYellowPattern( String[][] grid )
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
        } while ( number < 0 || number > 6 );
        System.out.println( "Thank you! dropping Yellow at : " + number );

        int col = number;// 2*number+1;
        for ( int row = 5; row >= 0; row-- )
        {
            if ( grid[row][col] == " " || grid[row][col] == "|" )
            {
                grid[row][col] = "Y";
                break;
            }
        }
        // scan.close();
    }

    /**
     * 
     * This is the method for the GUI. When the player plays at a certain square, this method drops
     * the checker at the bottom-most square of that column. 
     * 
     * @param grid the updated grid
     * @param number the column that the user wants to play at. 
     */
    public void dropYellowPatternGUI( String[][] grid, int number )
    {
        while ( number < 0 || number > 6 );
        System.out.println( "Thank you! dropping Yellow at : " + number );

        int col = number;// 2*number+1;
        for ( int row = 5; row >= 0; row-- )
        {
            if ( grid[row][col] == " " || grid[row][col] == "|" )
            {
                grid[row][col] = "Y";
                break;
            }
        }
    }

    /**
     * 
     * Checks if there is a winner as a result of that move. It first checks if there is a 4 in a 
     * row horizontally, then vertically, then diagonal left to right the right to left. All of 
     * these methods use for loops to check if there is a 4 in a row starting from each square that
     * is possible(ie when checking horizontal, the last column that it checks at is 4 because 
     * there would be an out-of-bounds error as it would be at most a 3 in a row)
     * 
     * @param f the grid.
     * @return if there is a winner it returns the string of the color of the token on the last 
     * square of the 4 in a row. If there isn't a winner, it returns -1.
     */
    public String checkWinner( String[][] f )

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
        for ( int col = 0; col < 7; col++ )
        {
            for ( int row = 0; row < 3; row++ )
            {
                if ( ( f[row][col] != " " ) && ( f[row + 1][col] != " " )
                    && ( f[row + 2][col] != " " ) && ( f[row + 3][col] != " " )
                    && ( ( f[row][col] == f[row + 1][col] )
                        && ( f[row + 1][col] == f[row + 2][col] )
                        && ( f[row + 2][col] == f[row + 3][col] ) ) )
                {
                    return f[row][col];
                }
            }
        }

        // checks left to right diagonal
        for ( int row = 0; row < 3; row++ )
        {
            for ( int col = 0; col < 4; col++ )
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
        for ( int row = 0; row < 3; row++ )
        {
            for ( int col = 6; col > 2; col-- )
            {
                if ( ( f[row][col] != " " ) && ( f[row + 1][col - 1] != " " )
                    && ( f[row + 2][col - 2] != " " ) && ( f[row + 3][col - 3] != " " )
                    && ( ( f[row][col] == f[row + 1][col - 1] )
                        && ( f[row + 1][col - 1] == f[row + 2][col - 2] )
                        && ( f[row + 2][col - 2] == f[row + 3][col - 3] ) ) )
                {
                    return f[row][col];
                }
            }

        }
        if ( tie( f ) )
            return "T";
        return "N"; // none
    }


    /**
     * 
     * There is a tie if the entire grid if filled up and there is no winner. This method makes 
     * sure that the game keeps going if there is no tie. It checks if there are any empty squares.
     * 
     * @param grid This is the grid that is updated.
     * @return If there is a tie, return true. If there is no tie, return false.
     */
    private boolean tie( String[][] grid )
    {
        for ( int row = 0; row < grid.length; row++ )
        {
            for ( int col = 0; col < grid[row].length; col++ )
            {
                if ( grid[row][col] == " " )
                    return false;
            }
        }
        return true;
    }
}
