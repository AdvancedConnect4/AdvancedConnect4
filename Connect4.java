package connect4;

public class ConnectFour 
{
    static Logic logic = new Logic();
    static BettingHandler bettingHandler = new BettingHandler();
    static ComputerPlayer computerPlayer = new ComputerPlayer();
    public static String[][] grid = logic.makeGrid();
    public static boolean playerRedturn = false;    
    public static boolean playerYellowturn = false;
    
    public static void main (String[] args)
    {
        String[][] grid = logic.makeGrid();
        boolean loop = true;
        logic.printPatternWithGrid(grid);
        String winner = "R";
        while(loop)
        {            
            //int bid = getBid(count, playerBalance); 
           if (bettingHandler.bettingOver()) {
               System.out.println(bettingHandler.gameOverMessage());
               bettingHandler.displayAllBets();
               loop = false;
               break;
           }
               
           if (winner == "R" || winner == "Y" || winner == "T") {
               playerYellowturn = bettingHandler.startBetting();
               grid = logic.makeGrid();
           }
           
           if (playerYellowturn) { //player plays first 
               logic.dropYellowPattern(grid);
               playerYellowturn = false;
               playerRedturn = true;
           } else {
               computerPlayer.dropRedPattern(grid);
               playerYellowturn = true;
               playerRedturn = false;
           }
           
           logic.printPatternWithGrid(grid);
           winner = logic.checkWinner(grid);
           if (winner == "R" || winner == "Y") {
               bettingHandler.handleWinner(winner);
               if (winner == "R")  {            
                     System.out.println("The red player won.");
               }
               else if (winner== "Y") {            
                    System.out.println("The yellow player won.");
               } else if (winner== "T") {            
                   System.out.println("Tie");
              }
           }
        }

      }
}
