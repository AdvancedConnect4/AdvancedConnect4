import java.util.Scanner;

/**
 * 
 *  The logic class makes sure the connect 4 game is working as the rules of connect4 dictate.
 *  This class had many methods created for debugging through the console. In addition, this checks
 *  if there is a winner and if the move is valid. This class also helps place the moves that the
 *  blue player plays.
 *
 *  @author  Albert Su
 *  @author  Anika Murthy
 *  @author  Arnav Gupta
 *  @version May 28, 2018
 *  @author  Period: 2
 *  @author  Assignment: FinalProject
 *
 *  @author  Sources: None
 */

public class Logic
{
	private String[][] grid;
	private ComputerPlayer computerPlayer;

	/**
	 * Constructs a new Logic with a 6x7 grid. The all squares in the grid, except for
	 * the ones in the last row are initialized to "-" to indicate that they are unavailable. 
	 * Squares in the last row are initialized to " " to indicate that they are available.
	 */
	public Logic()
	{
		computerPlayer = new ComputerPlayer();
		grid = new String[6][7];

		// Initialize the grid squares with default values.
		for ( int row = 0; row < grid.length; row++ )
		{
			for ( int col = 0; col < grid[row].length; col++ )
			{
				if ( row == 5 )
				{
					grid[row][col] = " ";
				}
				else
				{
					grid[row][col] = "-";
				}
			}
		}
	}

	/**
	 * 
	 * This method prints the Grid to the console. This was for testing when the GUI didn't work. 
	 * It uses two for loops to create the 6 by 15 grid which also includes the lines.
	 * 
	 * @param grid This is the current state of the grid.
	 */
	public void printPatternWithGrid()
	{
		for ( int row = 0; row < grid.length; row++ )
		{
			for ( int col = 0; col < grid[row].length; col++ )
			{
				grid[row][col] = " ";
				if ( col % 2 == 0 )
					grid[row][col] = "|";
				else
					grid[row][col] = grid[row][col / 2];
			}
		}

		for ( int row = 0; row < grid.length; row++ )
		{
			for ( int col = 0; col < grid[row].length; col++ )
			{
				System.out.print( grid[row][col] );
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
	public void printPatternWithoutGrid()
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
	 * Drops the Blue checker in the right column. The scanner is the way we tested without the 
	 * GUI. With the GUI, we would directly change the number variable to the column that we were
	 * to. We needed the for loop to make sure that the checker was dropped at the bottom-most
	 * empty square on the grid.
	 * 
	 * @param grid Updated grid
	 */
	public void dropBluePatternTest()
	{
		Scanner scan = new Scanner( System.in );
		int number;
		do
		{
			System.out.print( "Drop a blue disk at column (0–6): " );
			while ( !scan.hasNextInt() )
			{
				System.out.println( "That's not a number!" );
				scan.next(); // this is important!
			}
			number = scan.nextInt();
		} while ( number < 0 || number > 6 );
		System.out.println( "Thank you! dropping Blue at : " + number );

		int col = number;
		for ( int row = 5; row >= 0; row-- )
		{
			if ( grid[row][col] == " " || grid[row][col] == "|" )
			{
				grid[row][col] = "B";
				break;
			}
		}
		scan.close();
	}

	/**
	 * 
	 * This method is called by the GUI. When the player plays at a certain square, this method drops
	 * the checker at square that matches the supplied row and col.
	 * 
	 * @param row the row of the square picked by the player.
	 * @param col the column of the square picked by the player. 
	 */
	public void dropBluePattern( int row, int col )
	{
		grid[row][col] = "B";
	}
	
	/**
	 * This method is used for JUnit tests only. This method drops the specified
	 * pattern at the square that matches the supplied row and col.
	 * 
	 * @param row the row of the square picked by the player.
	 * @param col the column of the square picked by the player.
	 * @param pattern the pattern to be dropped. 
	 */
	public void dropPattern( int row, int col, String pattern )
	{
		grid[row][col] = pattern;
	}

	/**
	 * 
	 * This method determines the next possible move for the pink player (aka computer player)
	 * by calling the ComputerPlayer class. It updates the grid with setting the selected 
	 * [row, col] pair to "P" to indicate that the square has been taken by the pink player.
	 *
	 */
	public void dropPinkPattern()
	{
		computerPlayer.determineComputerMove(grid);
		int row = computerPlayer.getComputerMoveRow();
		int col = computerPlayer.getComputerMoveColumn();
		grid[row][col] = "P";
	}

	/**
	 * Returns the row that has been picked by the pink player (aka computer).
	 */
	public int getPinkPlayerRow()
	{
		return computerPlayer.getComputerMoveRow();
	}

	/**
	 * Returns the column that has been picked by the pink player (aka computer).
	 */
	public int getPinkPlayerColumn()
	{
		return computerPlayer.getComputerMoveColumn();
	}

	/**
	 * The GUI calls this method to update a square that is directly above the square picked by
	 * the player on the GUI grid. This method updates the square to " " to indicate that the
	 * square is available for the players to pick.
	 *
	 * @param row the row of the square that became available.
	 * @param col the column of the square that became available.
	 */
	public void makeSquareAvailable(int row, int col)
	{
		grid[row][col] = " ";
	}

	/**
	 * Checks if pink player is the winner. Calls checkWinner for handling this.
	 * 
	 * @return true if pink player is the winner, false otherwise.
	 */
	public boolean checkPinkPlayerWins()
	{
		return checkWinner("P");
	}

	/**
	 * Checks if blue player is the winner. Calls checkWinner for handling this.
	 * 
	 * @return true if blue player is the winner, false otherwise.
	 */
	public boolean checkBluePlayerWins()
	{
		return checkWinner("B");
	}

	/**
	 * 
	 * Checks if there is a winner as a result of that move. It first checks if
	 * there is a 4 in a row horizontally, then vertically, then diagonal left to
	 * right the right to left. All of these methods use for loops to check if there
	 * is a 4 in a row starting from each square that is possible(ie when checking
	 * horizontal, the last column that it checks at is 4 because there would be an
	 * out-of-bounds error as it would be at most a 3 in a row)
	 * 
	 * @param player The player that needs to be checked, "P" for pink and "B" for blue.
	 * @return true if player gets four connecting squares horizontally, vertically
	 *         or diagonally. False otherwise.
	 */
	public boolean checkWinner(String player)
	{
		// checks horizontal
		for ( int row = 0; row < 6; row++ )
		{
			for ( int col = 0; col < 4; col++ )
			{
				if (grid[row][col].equals(player) 
						&& grid[row][col + 1].equals(player)
						&& grid[row][col + 2].equals(player)  
						&& grid[row][col + 3].equals(player))
				{
					return true;
				}
			}
		}

		// checks vertical
		for ( int col = 0; col < 7; col++ )
		{
			for ( int row = 0; row < 3; row++ )
			{
				if (grid[row][col].equals(player) 
						&& grid[row + 1][col].equals(player)
						&& grid[row + 2][col].equals(player)  
						&& grid[row + 3][col].equals(player))
				{
					return true;
				}
			}
		}

		// checks left to right diagonal
		for ( int row = 0; row < 3; row++ )
		{
			for ( int col = 0; col < 4; col++ )
			{
				if (grid[row][col].equals(player)
						&& grid[row + 1][col + 1].equals(player)
						&& grid[row + 2][col + 2].equals(player)
						&& grid[row + 3][col + 3].equals(player))
				{
					return true;
				}
			}

		}

		// checks right to left diagonal
		for ( int row = 0; row < 3; row++ )
		{
			for ( int col = 6; col > 2; col-- )
			{
				if (grid[row][col].equals(player)
						&& grid[row + 1][col - 1].equals(player)
						&& grid[row + 2][col - 2].equals(player)
						&& grid[row + 3][col - 3].equals(player))
				{
					return true;
				}
			}

		}

		// Not a winner. Return false.
		return false;
	}


	/**
	 * 
	 * There is a tie if the entire grid if filled up and there is no winner. This method makes 
	 * sure that the game keeps going if there is no tie. It checks if there are any empty squares.
	 * 
	 * @return If there is a tie, return true. Return false otherwise
	 */
	public boolean tie()
	{
		for ( int row = 0; row < grid.length; row++ )
		{
			for ( int col = 0; col < grid[row].length; col++ )
			{
				if (grid[row][col].equals(" "))
				{
					return false;
				}
			}
		}
		return true;
	}
}
