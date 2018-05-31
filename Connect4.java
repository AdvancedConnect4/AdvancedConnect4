import java.util.Scanner;

/**
 * 
 * This runs the advancedconnect4 game from the console. This class calls
 * methods from other classes to run.
 *
 * @author Albert Su
 * @author Arnav Gupta
 * @version May 28, 2018
 * @author Period: 2
 * @author Assignment: FinalProject
 *
 * @author Sources: None
 */
public class Connect4
{
    static Logic logic = new Logic();
    static BettingHandler bettingHandler = new BettingHandler();
    static ComputerPlayer computerPlayer = new ComputerPlayer();
    public static boolean playerPinkTurn = false;    
    public static boolean playerBlueTurn = false;

    /**
     * 
     * The main method initializes the console version and checks who's turn it
     * is. In addition, it uses methods from other classes.
     * 
     * @param args
     */
    public static void main( String[] args )
    {
        Scanner scan = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler();
        boolean loop = true;
        logic.printPatternWithGrid();
        String winner = "P";
        while(loop)
        {            
           if (winner == "P" || winner == "B") {
               if (bettingHandler.bettingOver()) {
                   System.out.println(bettingHandler.gameOverMessage());
                   bettingHandler.displayAllBets();
                   loop = false;
                   break;
               }
               int playerRemainingCoins = bettingHandler.getBluePlayerCoins();
               int bluePlayerBet = inputHandler.getPlayerBet(scan, playerRemainingCoins);
               int pinkPlayerBet = bettingHandler.getPinkPlayerBet();
               playerBlueTurn = bluePlayerBet > pinkPlayerBet;
           }
           if (playerBlueTurn) { //player plays first
               int yellowLocation = inputHandler.getYellowLocation(scan);
               logic.dropBluePattern(yellowLocation, yellowLocation);
               playerBlueTurn = false;
               playerPinkTurn = true;
           } else {
               logic.dropPinkPattern();
               playerBlueTurn = true;
               playerPinkTurn = false;
           }
           
           logic.printPatternWithGrid();
           if (logic.checkPinkPlayerWins()) {
        	   System.out.println("The pink player won.");
           }
           else if (logic.checkBluePlayerWins()) {            
        	   System.out.println("The blue player won.");
           }
        }
        scan.close();
    }
}
