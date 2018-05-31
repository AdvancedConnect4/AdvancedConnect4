import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class represents the Advanced Connect 4 GUI. It allows a human player to play
 * against the computer. The players get to play three rounds of the game and the player
 * with the most wins is the overall winner. The GUI launches with a splash screen that 
 * has custom graphics. Each player starts out with 100 coins. The human player is promoted
 * to enter a bet for each of the three. The computer enters its bet. The players go against
 * each other to see who can connect four slots first. The blue slot represents the human 
 * player and the pink slot represents the computer player.
 * 
 * A panel on the left shows the current game being played and the player stats. The GUI 
 * incorporates error checks for invalid bet being entered.
 *
 *  @author  Anika Murthy
 *  @version May 28, 2018
 *  @author  Period: 2
 *  @author  Assignment: FinalProject
 */
public class AdvancedConnect4Gui {
	// Maximum number of games allowed.
	private static final int MAX_ALLOWED_GAMES = 3;

	// The colors used in this GUI
	private static final Color BG_COLOR = new Color(219, 219, 218);
	private static final Color TEXT_COLOR = new Color(85, 85, 85);

	// Fonts used in this GUI
	private static final Font BOLD_FONT = new Font("Courier New", Font.BOLD, 20);
	private static final Font NORMAL_FONT = new Font("Courier New", Font.BOLD, 14);

	// Images required for displaying the GUI
	private static Icon availableSlot = new ImageIcon("images/availableSlot.png");
	private static Icon notYetAvailableSlot = new ImageIcon("images/notYetAvailableSlot.png"); 
	private static Icon pinkSlot = new ImageIcon("images/pinkSlot.png");
	private static Icon pinkWinner = new ImageIcon("images/pinkplayerwins.png");
	private static Icon blueSlot = new ImageIcon("images/blueSlot.png");
	private static Icon blueWinner = new ImageIcon("images/blueplayerwins.png");
	private static Icon logoIcon = new ImageIcon("images/logo.png");
	private static Icon splashScreen = new ImageIcon("images/splashscreen.jpg");

	// Number of rows.
	private static final int NUM_ROWS = 6; 

	// Number of columns.
	private static final int NUM_COLS = 7;

	// Keep track of which player's turn it is.
	private boolean playerPinkTurn = false;
	private boolean playerBlueTurn = false;

	// Keep track of which game is currently being played.
	// Allow three games to be played.
	private int currentGame = 1;

	// Keep track of the current bet for the players.
	int pinkPlayerBet = -1;
	int bluePlayerBet = -1;

	// Keep track of the number of games won by the players.
	int pinkPlayerWins = 0;
	int bluePlayerWins = 0;

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

	private JFrame splashScreenContainer;

	/**
	 * The constructor for the Advanced Connect 4 GUI. It sets up
	 * the UI elements and initializes them 
	 * @param arguments the arguments passed in.
	 */
	public AdvancedConnect4Gui(String[] arguments)
	{
		// Set up the main window.
		JFrame.setDefaultLookAndFeelDecorated(true);
		container = new JFrame("Advanced Connect 4");
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

		// Display a splash page
		splashScreenContainer = new JFrame();
		splashScreenContainer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		splashScreenContainer.setBackground(Color.BLACK);
		splashScreenContainer.getContentPane().setBackground(Color.BLACK);
		splashScreenContainer.setSize(800, 478);
		Object[] options = {"START GAME"};
		JOptionPane.showOptionDialog(
				splashScreenContainer.getContentPane(),
				"",
				"Welcome to Advanced Connect 4",
				JOptionPane.OK_OPTION,
				JOptionPane.PLAIN_MESSAGE,
				splashScreen,
				options,
				options[0]);
		splashScreenContainer.setVisible(false);
		splashScreenContainer.dispose();

		// Initialize a new game.
		initNewGame();      
	}

	/**
	 * Initializes the game board and the display panel. Prompts the human player
	 * to enter bet. Gets the computer player's bet. Starts off the game.
	 */
	public void initNewGame()
	{
		if (currentGame > MAX_ALLOWED_GAMES)
		{
			// We have already played the allowed max game. Show game stats to the 
			// players and exit.
			String winner = pinkPlayerWins > bluePlayerWins ? "P" : "B";
			JOptionPane.showMessageDialog(
					container.getContentPane(), 
					bettingHandler.getGameStats(winner), 
					"Game Over",
					JOptionPane.INFORMATION_MESSAGE,
					logoIcon);
			System.exit(0);
		}

		logic = new Logic();

		gameBoardPanel = new JPanel(new GridLayout(NUM_ROWS, NUM_COLS));
		gameBoardPanel.setBackground(BG_COLOR);
		gameBoardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		for (int row = 0; row < NUM_ROWS; row++)
		{
			for (int col = 0; col < NUM_COLS; col++)
			{
				gameBoardButtons[row][col] = new JButton(availableSlot);
				gameBoardButtons[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				gameBoardButtons[row][col].setBackground(BG_COLOR);
				gameBoardButtonListeners[row][col] = new PanelButtonListener(row, col);
				gameBoardButtons[row][col].addActionListener(gameBoardButtonListeners[row][col]);
				gameBoardPanel.add(gameBoardButtons[row][col]);

				// All rows that are not the bottom most row should display the not 
				// yet available slot image and should not be clickable.
				if (row < NUM_ROWS - 1)
				{             
					gameBoardButtons[row][col].setDisabledIcon(notYetAvailableSlot);  
					gameBoardButtons[row][col].setEnabled(false);
				}
			}
		}

		JPanel gameNumberPanel = new JPanel();
		gameNumberPanel.setBackground(BG_COLOR);

		JLabel gameNumberLabel = new JLabel("GAME: " + currentGame + " of 3");
		gameNumberLabel.setForeground(TEXT_COLOR);
		gameNumberLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		gameNumberLabel.setAlignmentY(Component.LEFT_ALIGNMENT);
		gameNumberLabel.setFont(BOLD_FONT);
		gameNumberPanel.add(gameNumberLabel);

		displayPanel = new JPanel();
		displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		displayPanel.setBackground(BG_COLOR);
		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));
		displayPanel.add(gameNumberPanel);

		parentPanel = new JPanel();
		parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.X_AXIS));
		parentPanel.setBackground(BG_COLOR);
		parentPanel.add(displayPanel);
		parentPanel.add(gameBoardPanel);

		container.setLocation(400, 200);
		container.getContentPane().add(parentPanel);
		container.pack();
		container.setVisible(true);

		pinkPlayerBet = -1;
		bluePlayerBet = -1;

		// Get the bets for pink and blue players.
		pinkPlayerBet = bettingHandler.getPinkPlayerBet();
		while (bluePlayerBet == -1)
		{
			String bluePlayerBetString = (String) JOptionPane.showInputDialog(
					container.getContentPane(), 
					"You have " + bettingHandler.getBluePlayerCoins() 
					+ " coins available. Please place your bet.",
					"Enter your bet",
					JOptionPane.PLAIN_MESSAGE,
					logoIcon,
					null,
					null);
			if (bluePlayerBetString == null || bluePlayerBetString.isEmpty())
			{
				// Cancel button was clicked. Exit the game.
				System.exit(0);
			}

			try 
			{
				int parsedBet = Integer.parseInt(bluePlayerBetString);
				if (parsedBet < 0 || parsedBet > bettingHandler.getBluePlayerCoins())
				{
					// Bad input. Display error message and ask again.
					JOptionPane.showMessageDialog(
							container.getContentPane(),
							"That is an invalid entry. Please enter a valid bet.",
							"Invalid entry",
							JOptionPane.ERROR_MESSAGE);
					continue;
				}
				else
				{
					bluePlayerBet = parsedBet;
				}
			} 
			catch (NumberFormatException e)
			{
				// Bad input. Ask again.
				JOptionPane.showMessageDialog(
						container.getContentPane(),
						"That is an invalid entry. Please enter a valid bet.",
						"Invalid entry",
						JOptionPane.ERROR_MESSAGE);
				continue;
			}
		}
		bettingHandler.setBluePlayerBet(bluePlayerBet);

		String bluePlayerDisplay = "<html>BLUE PLAYER: <br/>"
				+ "<ul>Coins remaining: " + bettingHandler.getBluePlayerCoins() + "</ul>"
				+ "<ul>Current bet: " + bluePlayerBet + "</ul>"
				+ "<ul>Total wins: " + bluePlayerWins + "</ul>";
		JLabel bluePlayerCoinsLabel = new JLabel(bluePlayerDisplay);
		bluePlayerCoinsLabel.setForeground(TEXT_COLOR);
		bluePlayerCoinsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		bluePlayerCoinsLabel.setAlignmentY(Component.LEFT_ALIGNMENT);
		bluePlayerCoinsLabel.setFont(NORMAL_FONT);
		JPanel bluePlayersCoinsPanel = new JPanel();
		bluePlayersCoinsPanel.setBackground(BG_COLOR);
		bluePlayersCoinsPanel.add(bluePlayerCoinsLabel);

		String pinkPlayerDisplay = "<html>PINK PLAYER: <br/>"
				+ "<ul>Coins remaining: " + bettingHandler.getPinkPlayerCoins() + "</ul>"
				+ "<ul>Current bet: " + pinkPlayerBet + "</ul>"
				+ "<ul>Total wins: " + pinkPlayerWins + "</ul>";
		JLabel pinkPlayerCoinsLabel = new JLabel(pinkPlayerDisplay);
		pinkPlayerCoinsLabel.setForeground(TEXT_COLOR);
		pinkPlayerCoinsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		pinkPlayerCoinsLabel.setAlignmentY(Component.LEFT_ALIGNMENT);
		pinkPlayerCoinsLabel.setFont(NORMAL_FONT);
		JPanel pinkPlayersCoinsPanel = new JPanel();
		pinkPlayersCoinsPanel.setBackground(BG_COLOR);
		pinkPlayersCoinsPanel.add(pinkPlayerCoinsLabel);

		displayPanel.add(bluePlayersCoinsPanel);
		displayPanel.add(pinkPlayersCoinsPanel);
		displayPanel.invalidate();
		displayPanel.revalidate();
		container.pack();
		displayPanel.repaint();

		// For the very first game, the player with a higher bet gets to play first.
		// For subsequent games (games 2 and 3), the winner of the previous game
		// gets to play first.
		if (currentGame == 1)
		{
			if (bluePlayerBet > pinkPlayerBet)
			{
				playerBlueTurn = true;
				playerPinkTurn = false;
			}
			else 
			{
				playerBlueTurn = false;
				playerPinkTurn = true;
			}
		}

		displayAdvancedConnect4Gui();
	}

	/**
	 * Displays the GUI. Gets the computer player's selection and marks it with a 
	 * pink slot.
	 */
	public void displayAdvancedConnect4Gui()
	{
		container.setEnabled(true);

		// If it is player pink's - that is computer player's turn, have the ComputerPlayer handle
		// the move via the Logic class.
		if (playerPinkTurn){
			// Disable the container so that player blue (i.e, human player) cannot 
			// click any square.
			container.setEnabled(false);

			// Call the logic class to drop the pink pattern in the square picked by the computer.
			logic.dropPinkPattern();
			int row = logic.getPinkPlayerRow();
			int col = logic.getPinkPlayerColumn();
			// Now fire a click for the button in the specified [row, col] location.
			gameBoardButtons[row][col].doClick();
		}
	}

	/**
	 * Class that listens to the menu items in the menu bar. The menu has two items:
	 * 1) Start a new game
	 * 2) Quit
	 */
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

	/**
	 * Class that listens to any action on the main window. This class only 
	 * listens to the window close events and ignores everything else.
	 *
	 */
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

	/**
	 * Class that listens to the button clicks on the game panel of the connect 4 game.
	 */
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
			if (playerBlueTurn)
			{
				gameBoardButtons[row][col].setDisabledIcon(blueSlot);
				logic.dropBluePattern(row, col);

				// Check if blue player is the winner.
				if (logic.checkBluePlayerWins())
				{
					JOptionPane.showMessageDialog(
							container.getContentPane(), 
							"", 
							"Blue Player Wins Game " + currentGame + "!", 
							JOptionPane.PLAIN_MESSAGE,
							blueWinner);
					currentGame += 1;
					playerBlueTurn = true;
					playerPinkTurn = false;
					bluePlayerWins += 1;
					bettingHandler.handleWinner("B");
					container.getContentPane().remove(parentPanel);
					initNewGame();
					return;
				}
			}
			else
			{
				gameBoardButtons[row][col].setDisabledIcon(pinkSlot);
				// Check if pink player is the winner.
				if (logic.checkPinkPlayerWins())
				{
					JOptionPane.showMessageDialog(
							container.getContentPane(), 
							"", 
							"Pink Player Wins Game " + currentGame + "!",
							JOptionPane.PLAIN_MESSAGE,
							pinkWinner);
					currentGame += 1;
					playerBlueTurn = false;
					playerPinkTurn = true;
					pinkPlayerWins += 1;
					bettingHandler.handleWinner("P");
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
			playerPinkTurn = !playerPinkTurn;
			playerBlueTurn = !playerBlueTurn;

			// Continue with the game since we don't have a winner yet.
			displayAdvancedConnect4Gui();
		}
	}
}
