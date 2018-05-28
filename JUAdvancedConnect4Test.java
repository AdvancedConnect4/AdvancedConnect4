package connect4;
import org.junit.*;

import static org.junit.Assert.*;


public class JUAdvancedConnect4Test {
    
    @Test 
    public void checkTie()
    {
        String input = "Y";
        Logic logic = new Logic();
        String[][] grid = logic.makeGrid();
        String result = "";
        for (int row =0; row < grid.length; row++)
        {
          if (row == 0 || row == 1)
              input = "Y";
          else if (row == 2 || row == 3)
              input = "R";
          for (int col=0; col< grid[row].length; col++)
          {                            
              grid[row][col] = input;
              if (input == "R")
                  input = "Y";
              else input = "R";
              result += String.format("%s ", grid[row][col]);                            
          } 

          result += "\n";
        }
        //System.out.println(result);
        String winner = logic.checkWinner(grid);
        assertEquals(result + "\nTie", "T", winner);
    }
    
    @Test 
    public void checkNoWinPlayer()
    {
        Logic logic = new Logic();
        String[][] grid = logic.makeGrid();
        //String[][] grid = new String[6][7];
        grid[5][0] = "Y";
        grid[5][1] = "Y";
        grid[5][2] = "Y";
        grid[5][3] = "R";

        String winner = logic.checkWinner(grid);
        assertEquals("No win", "N", winner);
    }
    
    @Test 
    public void checkNoWinComputer()
    {
        Logic logic = new Logic();
        String[][] grid = logic.makeGrid();
        //String[][] grid = new String[6][7];
        grid[5][0] = "R";
        grid[5][1] = "R";
        grid[5][2] = "R";
        grid[5][3] = "Y";

        String winner = logic.checkWinner(grid);
        assertEquals("No win", "N", winner);
    }
    
    @Test 
    public void checkHorizontalWinnerForPlayer()
    {
        Logic logic = new Logic();
        String[][] grid = logic.makeGrid();
        //String[][] grid = new String[6][7];
        grid[5][0] = "Y";
        grid[5][1] = "Y";
        grid[5][2] = "Y";
        grid[5][3] = "Y";

        String winner = logic.checkWinner(grid);
        assertEquals("Horizontal YYYY must win", "Y", winner);
    }
    
    @Test 
    public void checkVerticalWinnerForPlayer()
    {
        Logic logic = new Logic();
        String[][] grid = logic.makeGrid();
        //String[][] grid = new String[6][7];
        grid[5][0] = "Y";
        grid[4][0] = "Y";
        grid[3][0] = "Y";
        grid[2][0] = "Y";

        String winner = logic.checkWinner(grid);
        assertEquals("Horizontal YYYY must win", "Y", winner);
    }
    
    @Test 
    public void checkDiagonalLeft2RightUpwardsWinnerForPlayer()
    {
        Logic logic = new Logic();
        String[][] grid = logic.makeGrid();
        //String[][] grid = new String[6][7];
        grid[5][0] = "Y";
        grid[4][1] = "Y";
        grid[3][2] = "Y";
        grid[2][3] = "Y";

        String winner = logic.checkWinner(grid);
        assertEquals("Diagonal YYYY must win", "Y", winner);
    }

    @Test 
    public void checkDiagonalLeft2RightDownwardWinnerForPlayer()
    {
        Logic logic = new Logic();
        String[][] grid = logic.makeGrid();
        //String[][] grid = new String[6][7];
        grid[0][0] = "Y";
        grid[1][1] = "Y";
        grid[2][2] = "Y";
        grid[3][3] = "Y";

        String winner = logic.checkWinner(grid);
        assertEquals("Diagonal YYYY must win", "Y", winner);
    }
    
    @Test 
    public void checkHorizontalWinnerForComputer()
    {
        Logic logic = new Logic();
        String[][] grid = logic.makeGrid();
        //String[][] grid = new String[6][7];
        grid[5][0] = "R";
        grid[5][1] = "R";
        grid[5][2] = "R";
        grid[5][3] = "R";

        String winner = logic.checkWinner(grid);
        assertEquals("Horizontal RRRR must win", "R", winner);
    }
    
    @Test 
    public void checkVerticalWinnerForComputer()
    {
        Logic logic = new Logic();
        String[][] grid = logic.makeGrid();
        //String[][] grid = new String[6][7];
        grid[5][0] = "R";
        grid[4][0] = "R";
        grid[3][0] = "R";
        grid[2][0] = "R";

        String winner = logic.checkWinner(grid);
        assertEquals("Horizontal RRRR must win", "R", winner);
    }
    
    @Test 
    public void checkDiagonalLeft2RightUpwardsWinnerForComputer()
    {
        Logic logic = new Logic();
        String[][] grid = logic.makeGrid();
        //String[][] grid = new String[6][7];
        grid[5][0] = "R";
        grid[4][1] = "R";
        grid[3][2] = "R";
        grid[2][3] = "R";

        String winner = logic.checkWinner(grid);
        assertEquals("Diagonal RRRR must win", "R", winner);
    }

    @Test 
    public void checkDiagonalLeft2RightDownwardWinnerForComputer()
    {
        Logic logic = new Logic();
        String[][] grid = logic.makeGrid();
        //String[][] grid = new String[6][7];
        grid[0][0] = "R";
        grid[1][1] = "R";
        grid[2][2] = "R";
        grid[3][3] = "R";

        String winner = logic.checkWinner(grid);
        assertEquals("Diagonal RRRR must win", "R", winner);
    }
    
    @Test 
    public void checkBettingIsOver()
    {
        BettingHandler bettingHandler = new BettingHandler();
        bettingHandler.startBetting(20);
        bettingHandler.handleWinner("Y");
        boolean betOver = bettingHandler.bettingOver();
        assertEquals(false, betOver);
        //second
        bettingHandler.startBetting(20);
        bettingHandler.handleWinner("Y");
        betOver = bettingHandler.bettingOver();
        assertEquals(false, betOver);
        //third
        bettingHandler.startBetting(20);
        bettingHandler.handleWinner("Y");
        betOver = bettingHandler.bettingOver();
        assertEquals(true, betOver);
    }
    
    
}
