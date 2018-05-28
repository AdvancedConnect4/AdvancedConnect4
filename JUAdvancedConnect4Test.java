package connect4;
import org.junit.*;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;


public class JUAdvancedConnect4Test {
    @Test 
    public void checkHorizontalWinner()
    {
        Logic logic = new Logic();
        String[][] grid = logic.makeGrid();
        //String[][] grid = new String[6][7];
        grid[5][0] = "Y";
        grid[5][1] = "Y";
        grid[5][2] = "Y";
        grid[5][3] = "Y";

        String winner = logic.checkWinner(grid);
        System.out.println("winner=" + winner);

        assertEquals("Horizontal YYYY must win", "Y", winner);
    }
    
    @Test 
    public void checkDiagonalLeft2RightUpwardsWinner()
    {
        Logic logic = new Logic();
        String[][] grid = logic.makeGrid();
        //String[][] grid = new String[6][7];
        grid[5][0] = "Y";
        grid[4][1] = "Y";
        grid[3][2] = "Y";
        grid[2][3] = "Y";

        String winner = logic.checkWinner(grid);
        System.out.println("winner=" + winner);

        assertEquals("Diagonal YYYY must win", "Y", winner);
    }

    @Test 
    public void checkDiagonalLeft2RightDownwardWinner()
    {
        Logic logic = new Logic();
        String[][] grid = logic.makeGrid();
        //String[][] grid = new String[6][7];
        grid[0][0] = "Y";
        grid[1][1] = "Y";
        grid[2][2] = "Y";
        grid[3][3] = "Y";

        String winner = logic.checkWinner(grid);
        System.out.println("winner=" + winner);

        assertEquals("Diagonal YYYY must win", "Y", winner);
    }
}
