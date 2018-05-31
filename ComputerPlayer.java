/**
 * 
 *  This class decides where the computerPlayer should play.
 *  The methods below detail how the computer chooses.
 *
 *  @author Albert Su
 *  @author Anika Murthy
 *  @author Arnav Gupta
 *  @version May 28, 2018
 *  @author  Period: 2
 *  @author  Assignment: FinalProject
 *
 *  @author  Sources: None
 */
public class ComputerPlayer
{
	private int computerMoveRow;
	private int computerMoveCol;

	public ComputerPlayer()
	{
		// Initialize the row and column that computer will pick to -1 at the start of the game.
		computerMoveRow = -1;
		computerMoveCol = -1;
	}

	/**
	 * 
	 * This method determines the next move for the computer to play. It uses the getComputerTurn
	 * to determine the column for the next move and picks the available [row, column] pair
	 * starting from the bottom-most row.
	 *  
	 * @param f the updated grid.
	 */
	public void determineComputerMove( String[][] f )
	{
		int col = getComputerTurn( f );

		for ( int row = 5; row >= 0; row-- )
		{
			if ( f[row][col] == " " )
			{
				computerMoveRow = row;
				computerMoveCol = col;
				break;

			}
		}
	}

	/**
	 * Returns the row that has been picked by the computer.
	 */
	public int getComputerMoveRow()
	{
		return computerMoveRow;
	}

	/**
	 * Returns the column that has been picked by the computer.
	 */
	public int getComputerMoveColumn()
	{
		return computerMoveCol;
	}

	/**
	 * 
	 * This method returns the move that the computer should play in in order to
	 * win. For example, it starts off by checking the vertical columns because
	 * this is the likeliest possibility for winning. It then follows up by
	 * checking the horizontal possibilities both continuous(already 3 in a row)
	 * and non continuous(ie YY_Y) if the move is playable. It then checks for
	 * the diagonal 4 in a row starting from the bottom. If this move is valid,
	 * then it will return that move.
	 * 
	 * @param f
	 *            the state of the grid.
	 * @return if there is a winning move, then it will return the column that
	 *         the computer will play in, otherwise, it would return the -1.
	 */
	private int checkIfComputerWins( String[][] f )
	{
		for ( int row = 0; row < 6; row++ )
		{
			for ( int col = 0; col < 7; col++ )
			{
				// Check vertical (RRR_ or _RRR)
				if ( row > 1 && f[row][col] == "P" && f[row - 1][col] == "P"
						&& f[row - 2][col] == "P" )
				{
					if ( row > 2 && row < 5 && f[row - 3][col] == " "
							|| row < 5 && f[row + 1][col] == " " )
					{
						return col;
					}
				}

				// Check horizontal, one empty between 4 R (RR_R or R_RR)
				if ( col < 5 && f[row][col] == "P" && f[row][col + 1] == "P" )
				{
					// (RR_R)
					if ( col < 4 && row < 5 && f[row][col + 2] == " " && f[row][col + 3] != " "
							&& f[row + 1][col + 2] != " " )
					{
						return col + 2;
					}
					else if ( col >= 2 && row < 5 && f[row][col - 1] == " "
							&& f[row][col - 2] != " " && f[row + 1][col - 1] != " " )
					{// R_RR
						return col - 1;
					}
				}
				// Check horizontal, 3 R continous(RRR_ or _RRR)
				if ( col < 5 && row < 5 && f[row][col] == "P" && f[row][col + 1] == "P"
						&& f[row][col + 2] == "P" )
				{
					if ( col < 4 && f[row][col + 3] == " " && f[row + 1][col + 3] != " " )
					{
						return col + 3;
					}
					else if ( col >= 1 && f[row][col - 1] == " " && f[row + 1][col - 1] != " " )
					{
						return col - 1;
					}
				}

				// check diagonal bottom up
				if ( col < 5 && row > 1 && f[row][col] == "P" && f[row - 1][col + 1] == "P"
						&& f[row - 2][col + 2] == "P" )
				{

					if ( row > 3 && col < 4 && f[row - 3][col + 3] == " "
							&& f[row - 2][col + 3] != " " )
					{
						return col + 3;
					}
					else if ( col > 1 && row < 4 && f[row + 1][col - 1] == " "
							&& f[row + 2][col - 1] != " " )
					{
						return col + 2;
					}
				}
			}
		}
		return -1; // player chance to play
	}

	/**
	 * 
	 * This method determines which column to play in. This is the order it checks and determines 
	 * where to play.
	 * 1. If there is a winning move for us, make it.
	 * 2. If there is a 3 in a row horizontally without gaps, play that.
	 * 3. If there is a 3 in a row vertically without gaps, play that.
	 * 4. If there is a 3 in a row diagonally without gaps, play that.
	 * 5. If there is a 3 in a row horizontally with gaps, play that.
	 * 6. If there is a 3 in a row vertically with gaps, play that.
	 * 7. If there is a 3 in a row diagonally with gaps, play that.
	 * 9. If there is a 2 in a row horizontally without gaps, play that.
	 * 10. If there is a 2 in a row vertically without gaps, play that.
	 * 11. If there is a 2 in a row diagonally without gaps, play that.
	 * 12. If there is a 2 in a row horizontally with gaps, play that.
	 * 13. If there is a 2 in a row vertically with gaps, play that.
	 * 14. If there is a 2 in a row diagonally with gaps, play that.
	 * 15. If the middle column is open, play that.(All non vertical four in a row plays need the 
	 * middle column)
	 * 16. Choose a random column to play in :(this is if nothing else works, no column is better 
	 * than another as not enough pieces are on the board yet).
	 * 
	 * @param f the current state of the grid
	 * @return the column to play in.
	 */
	private int getComputerTurn( String[][] f )
	{
		int colCheck = checkIfComputerWins( f );
		if ( colCheck != -1 )
		{
			return colCheck;
		}

		for ( int row = 0; row < 6; row++ )
		{
			for ( int col = 0; col < 7; col++ )
			{
				// Check horizontal RRR
				if ( col < 5 && f[row][col] == "P" && f[row][col + 1] == "P"
						&& f[row][col + 2] == "P" )
				{
					// RRR_
					if ( col < 4 && f[row][col + 3] == " "
							&& ( row == 5 || row <= 5 && f[row + 1][col + 3] != " " ) )
					{
						return col + 3;
					} // _RRR
					else if ( col >= 1 && f[row][col - 1] == " "
							&& ( row == 5 || row < 5 && f[row + 1][col - 1] != " " ) )
					{
						return col - 1;
					}
				}
				else if ( col < 6 && f[row][col] == "P" && f[row][col + 1] == "P" )
				{
					// RR_R
					if ( col < 4 && f[row][col + 2] == " "
							&& ( row == 5 || row < 5 && f[row + 1][col + 2] != " " )
							&& f[row][col + 3] == "P" )
					{
						return col + 2;
					} // R_RR
					else if ( col >= 2 && f[row][col - 1] == " "
							&& ( row == 5 || row < 5 && f[row + 1][col - 1] != " " )
							&& f[row][col - 2] == "P" )
					{
						return col - 1;
					}
				}
				// diagonal RRR_
				if ( col < 3 && row > 2 && f[row][col] == "P" && f[row - 1][col + 1] == "P"
						&& f[row - 2][col + 2] == "P" && f[row - 3][col + 3] == " "
						&& f[row - 2][col + 3] != " " )
				{
					return col + 3;
				} // diagonal RR_R
				else if ( col < 3 && row > 2 && f[row][col] == "P" && f[row - 1][col + 1] == "P"
						&& f[row - 2][col + 2] == " " && f[row - 3][col + 3] == "P"
						&& f[row - 1][col + 2] != " " )
				{
					return col + 2;
				} // diagonal R_RR
				else if ( col < 3 && row > 2 && f[row][col] == "P" && f[row - 1][col + 1] == " "
						&& f[row - 2][col + 2] == "P" && f[row - 3][col + 3] == "P"
						&& f[row][col + 1] != " " )
				{
					return col + 1;
				} // diagonal _RRR
				else if ( col < 3 && row > 2 && f[row][col] == " " && f[row - 1][col + 1] == "P"
						&& f[row - 2][col + 2] == "P" && f[row - 3][col + 3] == "P" )
				{
					return col;
				}

				// Check vertical RRR_
				if ( row > 1 && f[row][col] == "P" && f[row - 1][col] == "P"
						&& f[row - 2][col] == "P" )
				{
					if ( row > 2 && f[row - 3][col] == " " )
					{
						return col;
					}
				}

				// Check horizontal YYY
				if ( col < 5 && f[row][col] == "B" && f[row][col + 1] == "B"
						&& f[row][col + 2] == "B" )
				{
					// YYY_
					if ( col < 4 && f[row][col + 3] == " "
							&& ( row == 5 || row < 5 && f[row + 1][col + 3] != " " ) )
					{
						return col + 3;
					} // _YYY
					else if ( col >= 1 && f[row][col - 1] == " "
							&& ( row == 5 || row < 5 && f[row + 1][col - 1] != " " ) )
					{
						return col - 1;
					}
				}
				// Check vertical YYY_
				if ( row > 1 && f[row][col] == "B" && f[row - 1][col] == "B"
						&& f[row - 2][col] == "B" )
				{
					if ( row > 2 && f[row - 3][col] == " " )
					{
						return col;
					}
				}

				// diagonal YYY_
				if ( col < 3 && row > 2 && f[row][col] == "B" && f[row - 1][col + 1] == "B"
						&& f[row - 2][col + 2] == "B" && f[row - 3][col + 3] == " "
						&& f[row - 2][col + 3] != " " )
				{
					return col + 3;
				} // diagonal YY_Y
				else if ( col < 3 && row > 2 && f[row][col] == "B" && f[row - 1][col + 1] == "B"
						&& f[row - 2][col + 2] == " " && f[row - 3][col + 3] == "B"
						&& f[row - 1][col + 2] != " " )
				{
					return col + 2;
				} // diagonal Y_YY
				else if ( col < 3 && row > 2 && f[row][col] == "B" && f[row - 1][col + 1] == " "
						&& f[row - 2][col + 2] == "B" && f[row - 3][col + 3] == "B"
						&& f[row][col + 1] != " " )
				{
					return col + 1;
				} // diagonal _YYY
				else if ( col < 3 && row > 2 && f[row][col] == " " && f[row - 1][col + 1] == "B"
						&& f[row - 2][col + 2] == "B" && f[row - 3][col + 3] == "B" )
				{
					return col;
				}

				// Check horizontal YY
				if ( col < 6 && f[row][col] == "B" && f[row][col + 1] == "B" )
				{
					// YY_ horizontal
					if ( col < 5 && f[row][col + 2] == " "
							&& ( row == 5 || row < 5 && f[row + 1][col + 2] != " " ) )
					{
						// Y_YY_
						if ( col >= 2 && f[row][col - 1] == " " && f[row][col - 2] == "B"
								&& ( row == 5 || row < 5 && f[row + 1][col - 1] != " " ) )
						{
							return col - 1;
						}
						return col + 2;
					} // _YY horizontal
					else if ( col >= 1 && f[row][col - 1] == " "
							&& ( row == 5 || row < 5 && f[row + 1][col - 1] != " " ) )
					{
						return col - 1;
					}
				}
				// vertical YY_
				if ( row > 0 && f[row][col] == "B" && f[row - 1][col] == "B" )
				{
					if ( row > 1 && f[row - 2][col] == " " || row < 5 && f[row + 1][col] == " " )
					{
						return col;
					}
				}

			}
		}
		// Now check for diagonal YY_(left to right upward)
		for ( int row = 0; row < 6; row++ )
		{
			for ( int col = 0; col < 7; col++ )
			{
				if ( col < 5 && row > 0 && f[row][col] == "B" && f[row - 1][col + 1] == "B"
						&& f[row - 1][col + 2] != " " )
				{
					return col + 2;
				}
			}
		}

		if ( f[4][3] == " " )// makes sure the first move is always is the
			// middle column if given
			// option
		{
			return 3;
		}
		else
		{
			return (int)( Math.random() * 7 );
		}

	}

}
