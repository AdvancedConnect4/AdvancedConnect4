package connect4;

public class ComputerPlayer extends Player
{
    static Logic test = new Logic();

    static ConnectFour connectfour = new ConnectFour();

    static int computerPlayerCoins;// total coins of the
                                   // computerPlayer-Red(constantly updated)

    static int humanPlayerCoins; // total coins of humanPlayer-Yellow(constantly
                                 // updated)

    static int bidAmount; // amount to return to the logic class

    static int humanMovesToWin; // how many moves it will take for the human aka
                                // how many they have in a row right now

    static int[] previousHumanBids = new int[43]; // keeps track of the previous
                                                  // human bids in an array
                                                  // getPreviousHumanMoves gets
                                                  // the right move. For the
                                                  // most recent move, use the
                                                  // movesPlayed as the
                                                  // parameter

    static int movesPlayed = 0; // how many moves have been played(not each
                                // player but total/2)
    /**
     * 
     * Gets the previous move that the humanPlayer made. The most recent move would be movesPlayed
     * as the parameter. Subtract from movesPlayed to get older moves.
     * @param move
     * @return
     */
   
    
    public int getNextRedMove()
    {
        return test.checkYellowTwoInaRow(connectfour.getGrid());
    }
    
    public int getPreviousHumanMoves( int move )
    {
        previousHumanBids = test.getHumanPlayerBids();
        return previousHumanBids[move];
        
    }

    /**
     * 
     *This method is called to get the correct number of humanPlayerCoins. We need to constantly 
     *call it to make sure that the humanPlayerCoins we are using is correct.
     * @return
     */
    public static int humanPlayerCoins()
    {
        humanPlayerCoins = Logic.getYellowCoins();
        return humanPlayerCoins;
    }

    /**
     * 
     * Gets the amount of coins computerPlayer has.
     * @return
     */
    public static int computerPlayerCoins()
    {
        computerPlayerCoins = Logic.getRedCoins();
        return computerPlayerCoins;
    }


    /**
     * 
     * This method should return the correct column that the computer should
     * play at
     * 
     * @return
//     */
//    public String computerMove()
//    {
//        if ( test.checkYellowThreeInaRow( connectfour.getGrid() ) != null )
//        {
//            return test.checkYellowThreeInaRow( connectfour.getGrid() );
//        }
//        else if ( test.checkYellowTwoInaRow( connectfour.getGrid() ) != null )
//        {
//            return test.checkYellowTwoInaRow( connectfour.getGrid() );
//        }
//        else if ( movesPlayed == 0 )
//        {
//            return connectfour.getMiddlePosition();
//        }
//        else
//        {
//            return null;
//        }
//    }

    /**
     * 
     * how many moves to win is based on how many we have in a row right now.
     * @return
     */
//    public int computerMovesToWin()
//    {
//        if ( test.checkRedThreeInaRow( connectfour.getGrid() ) != null )
//        {
//            return 1;
//        }
//        else if ( test.checkRedTwoInaRow( connectfour.getGrid() ) != null )
//        {
//            return 2;
//        }
//        else
//        {
//            return 4;
//        }
//    }
//    

    /**
     * 
     * This should return how many moves it would take the human to win, based on how many 
     * tokens in a row they have.
     * @return
     */
//    public int humanMovesToWin()
//    {
//        if ( test.checkYellowThreeInaRow( connectfour.getGrid() ) != null )
//        {
//            return 1;
//        }
//        else if ( test.checkYellowTwoInaRow( connectfour.getGrid() ) != null )
//        {
//            return 2;
//        }
//        else
//        {
//            return 4;
//        }
//    }

    /**
     * 
     * This returns the amount that the computer will bid for their next move. This is based on 
     * a variety of factors but mostly on the dominant strategy of nx+1
     * @return
     */
    public int bidAmount()
    {
//        movesPlayed++;
//        if ( ( computerMovesToWin() == 1 ) && ( computerPlayerCoins() > humanPlayerCoins() ) )
//        {
//            return humanPlayerCoins();
//        }
//        else if ( ( computerMovesToWin() == 2 )
//            && ( computerPlayerCoins() > ( ( 2 * humanPlayerCoins() ) + 1 ) ) )
//        {
//            return humanPlayerCoins();
//        }
//        else if ( ( computerMovesToWin() == 3 )
//            && ( computerPlayerCoins() > ( ( 3 * humanPlayerCoins() ) + 1 ) ) )
//        {
//            return humanPlayerCoins();
//        }
//        else if ( ( computerMovesToWin() == 4 )
//            && ( computerPlayerCoins() > ( ( 4 * humanPlayerCoins() ) + 1 ) ) )
//        {
//            return humanPlayerCoins();
//        }
//        else if ( ( humanMovesToWin == 1 ) ) // if the human is one step from winning stop him
//        {
//            if ( computerPlayerCoins() > humanPlayerCoins() )
//            {
//                bidAmount = humanPlayerCoins();
//            }
//            else
//            {
//                bidAmount = computerPlayerCoins() - getPreviousHumanMoves(movesPlayed);
//            }
//        }
//        if ( humanMovesToWin == 2 )//makes sure that human cannot barely beat the computerBid every time
//        {
//            if ( ( getPreviousHumanMoves( movesPlayed )
//                - getPreviousHumanMoves( movesPlayed - 1 ) ) < 5 )
//            {
//                bidAmount = getPreviousHumanMoves( movesPlayed ) + 1;
//            }
//        }
            return getPreviousHumanMoves(movesPlayed);

    }

}
