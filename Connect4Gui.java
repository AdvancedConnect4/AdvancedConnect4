import javax.swing.*;
import java.awt.*;
import java.awt.event.*;        

/**
 * 
 *
 *
 */
public class Connect4Gui {
	// Maximum number of games allowed.
	private static final int MAX_ALLOWED_GAMES = 3;

    // Images required for displaying the GUI
    private static Icon availableSlot = new ImageIcon("images/availableSlot.png");
    private static Icon notYetAvailableSlot = new ImageIcon("images/notYetAvailableSlot.png"); 
    private static Icon redSlot = new ImageIcon("images/redSlot.png");
    private static Icon redSlotWinning = new ImageIcon("images/redWinningSlot.png");
    private static Icon yellowSlot = new ImageIcon("images/yellowSlot.png");
    private static Icon yellowSlotWinning = new ImageIcon("images/yellowWinningSlot.png");

    // Number of rows.
    private static final int NUM_ROWS = 6; 

    // Number of columns.
    private static final int NUM_COLS = 7;
    
    // Keep track of which player's turn it is.
    private boolean playerRedTurn = false;
    private boolean playerYellowTurn = false;
    
    // Keep track of which game is currently being played.
    // Allow three games to be played.
    private int currentGame = 1;
    
    // Keep track of the current bet for the players.
    int redPlayerBet = -1;
    int yellowPlayerBet = -1;
    
    // AI classes
    private Logic logic;
    private BettingHandler bettingHandler = new BettingHandler();

    // GUI components.
    private JFrame container;
    private JMenuBar gameMenuBar;
    private JMenu gameMenu;

    private JPanel parentPanel;
    private JPanel displayPanel;
    private JPanel gameBoardPanel;
    private JButton[][] gameBoardButtons;
    private PanelButtonListener[][] gameBoardButtonListeners;
    
    /**
     * 
     * @param arguments
     */
    public Connect4Gui(String[] arguments)
    {
        // Set up the main window.
        JFrame.setDefaultLookAndFeelDecorated(true);
        container = new JFrame("Connect 4 plus bidding");
        container.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Add a menu bar with a menu to the window.
        gameMenuBar = new JMenuBar();                                      
        gameMenu = new JMenu("Menu");
        JMenuItem newGameMenuItem = new JMenuItem("Start a new game");
        JMenuItem quitGameMenuItem = new JMenuItem("Quit this game");
        MenuItemListener newGameMenuItemListener = new MenuItemListener();
        MenuItemListener quitGameMenuItemListener = new MenuItemListener();
        newGameMenuItem.addActionListener(newGameMenuItemListener);
        quitGameMenuItem.addActionListener(quitGameMenuItemListener);
        gameMenu.add(newGameMenuItem);
        gameMenu.add(quitGameMenuItem);
        gameMenuBar.add(gameMenu);
        container.setJMenuBar(gameMenuBar);

        MenuListener gameMenuListener = new MenuListener();
        container.addWindowListener(gameMenuListener);

        gameBoardButtonListeners = new PanelButtonListener[NUM_ROWS][NUM_COLS];
        gameBoardButtons = new JButton[NUM_ROWS][NUM_COLS];
        gameBoardPanel = new JPanel(new GridLayout(NUM_ROWS, NUM_COLS));

        // Initialize a new game.
        initNewGame();      
    }

    /**
     * 
     */
    public void initNewGame()
    {
    	if (currentGame > MAX_ALLOWED_GAMES)
    	{
    		// We have already played the allowed max game. Show game stats to the players and exit.
    		JOptionPane.showMessageDialog(
	    			container.getContentPane(), 
	    			bettingHandler.getGameStats(), 
	    			"Game Over",
	    			JOptionPane.INFORMATION_MESSAGE);
    		System.exit(0);
    	}
    	
    	logic = new Logic();
    	
        gameBoardPanel = new JPanel(new GridLayout(NUM_ROWS, NUM_COLS));
        gameBoardPanel.setBackground(Color.BLUE);
        gameBoardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int row = 0; row < NUM_ROWS; row++)
        {
            for (int col = 0; col < NUM_COLS; col++)
            {
            	gameBoardButtons[row][col] = new JButton(availableSlot);
            	gameBoardButtons[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            	gameBoardButtons[row][col].setBackground(Color.BLUE);
            	gameBoardButtonListeners[row][col] = new PanelButtonListener(row, col);
                gameBoardButtons[row][col].addActionListener(gameBoardButtonListeners[row][col]);
                gameBoardPanel.add(gameBoardButtons[row][col]);

                // All rows that are not the bottom most row should display the not yet available slot
                // image and should not be clickable.
                if (row < NUM_ROWS - 1)
                {             
                	gameBoardButtons[row][col].setDisabledIcon(notYetAvailableSlot);  
                	gameBoardButtons[row][col].setEnabled(false);
                }
            }
        }
        
    	JPanel gameNumberPanel = new JPanel();
    	gameNumberPanel.setBackground(Color.BLUE);
    	
    	JLabel gameNumberLabel = new JLabel("You are playing game " + currentGame + " of 3");
    	gameNumberLabel.setForeground(Color.WHITE);
    	gameNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    	gameNumberLabel.setFont(new Font("Arial", Font.BOLD, 18));
    	gameNumberPanel.add(gameNumberLabel);
    	
    	JLabel yellowPlayerCoinsLabel = new JLabel("Yellow player has " + bettingHandler.getYellowPlayerCoins()  + " coins");
    	yellowPlayerCoinsLabel.setForeground(Color.WHITE);
    	JPanel yellowPlayersCoinsPanel = new JPanel();
    	yellowPlayersCoinsPanel.setBackground(Color.BLUE);
    	yellowPlayersCoinsPanel.add(yellowPlayerCoinsLabel);
    	
    	JLabel redPlayerCoinsLabel = new JLabel("Red player has " + bettingHandler.getRedPlayerCoins() + " coins");
    	redPlayerCoinsLabel.setForeground(Color.WHITE);
    	JPanel redPlayersCoinsPanel = new JPanel();
    	redPlayersCoinsPanel.setBackground(Color.BLUE);
    	redPlayersCoinsPanel.add(redPlayerCoinsLabel);
    	
    	displayPanel = new JPanel();
    	displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    	displayPanel.setBackground(Color.BLUE);
    	displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
    	displayPanel.add(gameNumberPanel);
    	displayPanel.add(yellowPlayersCoinsPanel);
    	displayPanel.add(redPlayersCoinsPanel);
    	
    	parentPanel = new JPanel();
    	parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.X_AXIS));
    	parentPanel.setBackground(Color.BLUE);
    	parentPanel.add(displayPanel);
    	parentPanel.add(gameBoardPanel);

    	container.setLocation(400, 200);
    	container.getContentPane().add(parentPanel);
    	container.pack();
    	container.setVisible(true);
    	
    	redPlayerBet = -1;
        yellowPlayerBet = -1;
    	
    	// Get the bets for red and yellow players.
    	redPlayerBet = bettingHandler.getRedPlayerBet();
    	while (yellowPlayerBet == -1)
    	{
    		String yellowBetAsString = JOptionPane.showInputDialog(
        			container.getContentPane(), 
        			"Please enter a valid bet between 0 and " + bettingHandler.getYellowPlayerCoins() + " for this game");
    		if (yellowBetAsString == null)
    		{
    			continue;
    		}
    		
    		try 
    		{
    			int parsedBet = Integer.parseInt(yellowBetAsString);
    			if (parsedBet < 0 || parsedBet > bettingHandler.getYellowPlayerCoins())
    			{
    				// Bad input. Ask again.
    				continue;
    			}
    			else
    			{
    				yellowPlayerBet = parsedBet;
    			}
    		} 
    		catch (NumberFormatException e)
    		{
    			// Bad input. Ask again.
    			continue;
    		}
    	}
    	bettingHandler.setYellowPlayerBet(yellowPlayerBet);
    	
    	// For the very first game, the player with a higher bet gets to play first.
    	// For subsequent games (games 2 and 3), the winner of the previous game
    	// gets to play first.
    	if (currentGame == 1)
    	{
    		if (yellowPlayerBet > redPlayerBet)
    		{
    			playerYellowTurn = true;
            	playerRedTurn = false;
    		}
    		else 
    		{
    			playerYellowTurn = false;
            	playerRedTurn = true;
    		}
    	}
    	
    	displayConnect4Gui();
    }
    
    /**
     * 
     */
    public void displayConnect4Gui()
    {
    	container.setEnabled(true);
    	
    	// If it is player red's - that is computer player's turn, have the ComputerPlayer handle
    	// the move via the Logic class.
    	if (playerRedTurn){
    		// Disable the container so that player yellow (i.e, human player) cannot click any square.
    		container.setEnabled(false);

    		// Call the logic class to drop the red pattern in the square picked by the computer.
    	    logic.dropRedPattern();
    	    int row = logic.getRedPlayerRow();
    	    int col = logic.getRedPlayerColumn();
    	    // Now fire a click for the button in the specified [row, col] location.
    	    gameBoardButtons[row][col].doClick();
    	}
    }

    // Class that listens to the menu items in the menu bar. The menu has two items: 
    // 1) Start a new game
    // 2) Quit
    class MenuItemListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String actionCommand = e.getActionCommand();
            if (actionCommand.equals("Start a new Game"))
            {
                // First clear the game before starting a new one.
            	container.getContentPane().remove(parentPanel);
            	currentGame = 1;
                initNewGame();
            } 
            else if (actionCommand.equals("Quit this game"))
            {
                System.exit(0);
            }
        }
    }

    // Class that listens to any action on the main window. This class only listens to the window close
    // even and ignores everything else.
    class MenuListener implements WindowListener
    {
        public void windowClosed(WindowEvent we)
        {
            System.exit(0);
        }

        public void windowClosing(WindowEvent we)
        {
            windowClosed(we);
        }

        public void windowActivated(WindowEvent we)
        {
        }

        public void windowDeactivated(WindowEvent we)
        {
        }

        public void windowDeiconified(WindowEvent we)
        {
        }

        public void windowIconified(WindowEvent we)
        {
        }

        public void windowOpened(WindowEvent we)
        {
        }
    }

    // Class that listens to the button clicks on the game panel of the connect 4 game.
    class PanelButtonListener implements ActionListener
    {
        private int row;
        private int col;

        public PanelButtonListener(int row, int col)
        {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e)
        {
        	gameBoardButtons[row][col].setEnabled(false);
        	if (playerYellowTurn)
        	{
        		gameBoardButtons[row][col].setDisabledIcon(yellowSlot);
        		logic.dropYellowPattern(row, col);
        		
        		// Check if yellow player is the winner.
        		if (logic.checkYellowPlayerWins())
        		{
        			JOptionPane.showMessageDialog(
        	    			container.getContentPane(), 
        	    			"Yellow Player Wins Game " + currentGame + "!", 
        	    			"Game " + currentGame + " Over", 
        	    			JOptionPane.PLAIN_MESSAGE);
        	    	currentGame += 1;
        	    	playerYellowTurn = true;
        	    	playerRedTurn = false;
        	    	bettingHandler.handleWinner("Y");
        	    	container.getContentPane().remove(parentPanel);
        	    	initNewGame();
        	    	return;
        		}
        	}
        	else
        	{
        		gameBoardButtons[row][col].setDisabledIcon(redSlot);
        		// Check if red player is the winner.
        		if (logic.checkRedPlayerWins())
        		{
        			JOptionPane.showMessageDialog(
        	    			container.getContentPane(), 
        	    			"Red Player Wins Game " + currentGame + "!", 
        	    			"Game " + currentGame + " Over",
        	    			JOptionPane.PLAIN_MESSAGE);
        	    	currentGame += 1;
        	    	playerYellowTurn = false;
        	    	playerRedTurn = true;
        	    	bettingHandler.handleWinner("R");
        	    	container.getContentPane().remove(parentPanel);
        	    	initNewGame();
        	    	return;
        		}
        	}
        	
        	// Enable button that is above this button, if it is not the first row.
    	    if (row > 0) {
    	    	logic.makeSquareAvailable(row - 1 , col);
    	    	gameBoardButtons[row - 1][col].setEnabled(true);
    	    }
    	    
    	    // Invert the players.
    	    playerRedTurn = !playerRedTurn;
			playerYellowTurn = !playerYellowTurn;
    	    
    	    // Continue with the game since we don't have a winner yet.
	    	displayConnect4Gui();
        }
    }
}
