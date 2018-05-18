package connect4;

public class ComputerPlayer extends Player {
    static Logic test = new Logic();
    static ConnectFour connectfour = new ConnectFour();
    static int computerPlayerCoins;
    static int humanPlayerCoins;
    static int bidAmount;
    static int humanMovesToWin; // how many moves it will take for the computer
                                // to win
    static int[] previousHumanBids = new int[43]; // keeps track of the previous
                                                  // human bids that the human
                                                  // bidded
    static int movesPlayed = 0; // how many moves have been played(not each
                                // player but total/2)
    
    public void setMovesPlayed() 
    {
        movesPlayed = Logic.getMovesPlayed();
    }
    
    public int getPreviousHumanMoves(int move)
    {
        previousHumanBids = Logic.getHumanPlayerBids();
        return previousHumanBids[move];
    }
    public static int humanPlayerCoins() {
        humanPlayerCoins = Logic.getYellowCoins();
        return humanPlayerCoins;
    }

    public static int computerPlayerCoins() {
        computerPlayerCoins = Logic.getRedCoins();
        return computerPlayerCoins;
    }
    
    public int computerMovesToWin() {
        return 3;
    }

    public String humanMovesToWin() {
        if (test.checkYellowThreeInaRow(connectfour.getGrid()) != null) {
            humanMovesToWin = 1;
            return test.checkYellowThreeInaRow(connectfour.getGrid());
        } 
        else if (test.checkYellowTwoInaRow(connectfour.getGrid()) != null) 
        {
            humanMovesToWin = 2;
            return test.checkYellowTwoInaRow(connectfour.getGrid());
        }
        else{
            humanMovesToWin = 4;
            return connectfour.getMiddlePosition();
        }
    }

    public int bidAmount() 
    {
       if ((computerMovesToWin() == 1)
           && (computerPlayerCoins() > humanPlayerCoins())) 
       {
            bidAmount = humanPlayerCoins();
        }
        if ((computerMovesToWin() == 2)
                && (computerPlayerCoins() > ((2 * humanPlayerCoins()) + 1))) 
        {
            bidAmount = humanPlayerCoins();
        }
        if ((computerMovesToWin() == 3)
                && (computerPlayerCoins() > ((3 * humanPlayerCoins()) + 1))) 
        {
            bidAmount = humanPlayerCoins();
        }
        if ((computerMovesToWin() >= 4)
                && (computerPlayerCoins() > ((4 * humanPlayerCoins()) + 1))) 
        {
            bidAmount = humanPlayerCoins();
        }
        if ((computerMovesToWin() == 2)
                && (computerPlayerCoins() > ((2 * humanPlayerCoins()) + 1))) 
        {
            bidAmount = humanPlayerCoins();
        }
//        if ((humanMovesToWin == 1)) // if the human is one step from winning,
//                                    // stop him at all costs!
//        {
//            if (computerPlayerCoins() > humanPlayerCoins()) {
//                bidAmount = humanPlayerCoins();
//            } else {
//                bidAmount = computerPlayerCoins() - 5;
//            }
//        }
//        if (humanMovesToWin == 2)
//        {
//            if ((getPreviousHumanMoves(movesPlayed)
//                            - getPreviousHumanMoves(movesPlayed - 1)) < 5) 
//                    {
//                        bidAmount = getPreviousHumanMoves(movesPlayed) + 1;
//                    }
//        }

        movesPlayed++;
        return bidAmount;
    }



}
