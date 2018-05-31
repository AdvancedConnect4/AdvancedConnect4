import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.*;

public class JUAdvancedConnect4Test {
    
    @Test 
    public void checkNoWinPlayer()
    {
        Logic logic = new Logic();
        logic.dropPattern(5, 0, "B");
        logic.dropPattern(5, 1, "B");
        logic.dropPattern(5, 2, "B");
        logic.dropPattern(5, 3, "P");

        boolean winner = logic.checkBluePlayerWins();
        assertFalse(winner);
    }
    
    @Test 
    public void checkNoWinComputer()
    {
    	Logic logic = new Logic();
        logic.dropPattern(5, 0, "P");
        logic.dropPattern(5, 1, "P");
        logic.dropPattern(5, 2, "P");
        logic.dropPattern(5, 3, "B");

        boolean winner = logic.checkPinkPlayerWins();
        assertFalse(winner);
    }
    
    @Test 
    public void checkHorizontalWinnerForPlayer()
    {
        Logic logic = new Logic();
        logic.dropPattern(5, 0, "B");
        logic.dropPattern(5, 1, "B");
        logic.dropPattern(5, 2, "B");
        logic.dropPattern(5, 3, "B");

        boolean winner = logic.checkBluePlayerWins();
        assertTrue(winner);
    }
    
    @Test 
    public void checkVerticalWinnerForPlayer()
    {
    	Logic logic = new Logic();
        logic.dropPattern(5, 0, "B");
        logic.dropPattern(4, 0, "B");
        logic.dropPattern(3, 0, "B");
        logic.dropPattern(2, 0, "B");

        boolean winner = logic.checkBluePlayerWins();
        assertTrue(winner);
    }
    
    @Test 
    public void checkDiagonalLeft2RightUpwardsWinnerForPlayer()
    {
    	Logic logic = new Logic();
        logic.dropPattern(5, 0, "B");
        logic.dropPattern(4, 1, "B");
        logic.dropPattern(3, 2, "B");
        logic.dropPattern(2, 3, "B");

        boolean winner = logic.checkBluePlayerWins();
        assertTrue(winner);
    }

    @Test 
    public void checkDiagonalLeft2RightDownwardWinnerForPlayer()
    {
    	Logic logic = new Logic();
        logic.dropPattern(0, 0, "B");
        logic.dropPattern(1, 1, "B");
        logic.dropPattern(2, 2, "B");
        logic.dropPattern(3, 3, "B");

        boolean winner = logic.checkBluePlayerWins();
        assertTrue(winner);
    }
    
    @Test 
    public void checkHorizontalWinnerForComputer()
    {
    	Logic logic = new Logic();
        logic.dropPattern(5, 0, "P");
        logic.dropPattern(5, 1, "P");
        logic.dropPattern(5, 2, "P");
        logic.dropPattern(5, 3, "P");

        boolean winner = logic.checkPinkPlayerWins();
        assertTrue(winner);
    }
    
    @Test 
    public void checkVerticalWinnerForComputer()
    {
    	Logic logic = new Logic();
        logic.dropPattern(5, 0, "P");
        logic.dropPattern(4, 0, "P");
        logic.dropPattern(3, 0, "P");
        logic.dropPattern(2, 0, "P");

        boolean winner = logic.checkPinkPlayerWins();
        assertTrue(winner);
    }
    
    @Test 
    public void checkDiagonalLeft2RightUpwardsWinnerForComputer()
    {
    	Logic logic = new Logic();
        logic.dropPattern(5, 0, "P");
        logic.dropPattern(4, 1, "P");
        logic.dropPattern(3, 2, "P");
        logic.dropPattern(2, 3, "P");

        boolean winner = logic.checkPinkPlayerWins();
        assertTrue(winner);
    }

    @Test 
    public void checkDiagonalLeft2RightDownwardWinnerForComputer()
    {
    	Logic logic = new Logic();
        logic.dropPattern(0, 0, "P");
        logic.dropPattern(1, 1, "P");
        logic.dropPattern(2, 2, "P");
        logic.dropPattern(3, 3, "P");

        boolean winner = logic.checkPinkPlayerWins();
        assertTrue(winner);
    }
    
    @Test 
    public void checkTie()
    {
    	Logic logic = new Logic();
    	for ( int row = 0; row < 6; row++ )
		{
			for ( int col = 0; col < 7; col++ )
			{
				logic.dropPattern(row, col, "B");
			}
		}
        
        boolean winner = logic.tie();
        assertTrue(winner);
    }
}
