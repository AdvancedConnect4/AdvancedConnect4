package connect4;

public class ConnectFour
{
    public static int yellow = 0;

    public static int red = 1;

    public static int tie = 2;

    public static int yellowCoins = 100;

    public static int redCoins = 100;

    static Logic logic = new Logic();

    static ComputerPlayer computerPlayer = new ComputerPlayer();

    public static String[][] grid = logic.makeGrid();

    public static boolean playerRedturn = false;
    
    public static boolean playerYellowturn = false;

    public static void main( String[] args )
    {
        String[][] grid = Logic.makeGrid();
        boolean loop = true;

        Logic.printPatternWithoutGrid( grid );

        while ( loop )
        {
            int computerBid = ComputerPlayer.getComputerBid();
            int yellowBid = Logic.getYellowPlayerBid();
            if ( yellowBid > computerBid )
            {
                playerYellowturn = true;
                Logic.dropYellowPattern( grid );
                System.out.println(
                    "Yellow has " + Integer.valueOf( logic.getYellowCoins() ) + " coins left" );
                System.out.println(
                    "Red has " + Integer.valueOf( computerPlayer.getRedCoins() ) + " coins left" );
            }
            if ( yellowBid < computerBid )
            {
                playerRedturn = true;
                computerPlayer.dropRedPattern( grid );
                System.out.println(
                    "Yellow has " + Integer.valueOf( logic.getYellowCoins() ) + " coins left" );
                System.out.println(
                    "Red has " + Integer.valueOf( computerPlayer.getRedCoins() ) + " coins left" );
            }
            if(yellowBid == computerBid)
            {
                System.out.println( "Tie bid, try again" );
                System.out.println(
                    "Yellow has " + Integer.valueOf( logic.getYellowCoins() ) + " coins left" );
                System.out.println(
                    "Red has " + Integer.valueOf( computerPlayer.getRedCoins() ) + " coins left" );
            }
            logic.printPatternWithoutGrid( grid );
            if ( logic.checkWinner( grid ) != null )
            {
                if ( logic.checkWinner( grid ) == "R" )
                {
                    System.out.println( "The red player won." );
                }
                else if ( logic.checkWinner( grid ) == "Y" )
                {
                    System.out.println( "The yellow player won." );
                }
                loop = false;

            }

        }

    }


    public String[][] getGrid()
    {
        return grid;
    }


    public String getMiddlePosition()
    {
        return grid[0][8];
    }

}
