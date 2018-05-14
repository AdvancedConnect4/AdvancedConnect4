import javax.swing.*;
import java.awt.*;
import java.awt.event.*;        

public class Connect4Gui {

    // Images required for displaying the GUI
    private static Icon availableSlot = new ImageIcon("images/availableSlot.png”);
    private static Icon notYetAvailableSlot = new ImageIcon("images/notYetAvailableSlot.png”); 
    private static Icon redSlot = new ImageIcon("images/redSlot.png”);
    private static Icon redSlotWinning = new ImageIcon("images/redWinningSlot.png”);
    private static Icon yellowSlot = new ImageIcon("images/yellowSlot.png”);
    private static Icon yellowSlotWinning = new ImageIcon("images/yellowWinningSlot.png”);

    // Number of rows.
    private static final int NUM_ROWS = 6; 

    // Number of columns.
    private static final int NUM_COLS = 7;
    
    private JFrame gameBoard;
    private JMenuBar gameMenuBar;
    private JMenu gameMenu;

    private JPanel gameBoardPanel;
    private JButton[][] panelButtons;
    private ButtonListener[][] panelButtonListeners; 


    public Connect4Gui()
    {
           // Set up the main window.
    JFrame.setDefaultLookAndFeelDecorated(true);
    gameBoard = new JFrame("Connect 4 plus bidding“);
    gameBoard.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    // Add a menu bar with a menu to the window.
    gameMenuBar = new JMenuBar();                                      
    gameMenu = new JMenu(“Menu”);
    JMenuItem newGameMenuItem = new JMenuItem(“Start a new game");
    JMenuItem quitGameMenuItem = new JMenuItem("Quit this game“);
    MenuItemListener newGameMenuItemListener = new MenuItemListener();
    MenuItemListener quitGameMenuItemListener = new MenuItemListener();
    newGameMenuItem.addActionListener(newGameMenuItemListener);
    quitGameMenuItem.addActionListener(quitGameMenuItemListener);
    gameMenu.add(newGameMenuItem);
    gameMenu.add(quitGameMenuItem);
    gameMenuBar.add(gameMenu);
    gameBoard.setJMenuBar(gameMenuBar);

    MenuListener gameMenuListener = new MenuListener();
    gameBoard.addWindowListener(gameMenuListener);

    panelButtonListeners = new PanelButtonListener[NUM_ROWS][NUM_COLS];
    panelButtons = new JButton[NUM_ROWS][NUM_COLS];
    gameBoardPanel = new JPanel(new GridLayout(NUM_ROWS, NUM_COLS));

           // Initialize a new game.
    initNewGame();      
    }

    public void initNewGame()
    {
    gameBoardPanel = new JPanel(new GridLayout(NUM_ROW,  NUM_COLS)); 

    for (int row = 0; row < NUM_ROWS; row++)
           {
        for (int col = 0; col < NUM_COLS; col++)
               {
        panelButtons[row][col] = new JButton(availableSlot);
        panelButtonListeners[row][col] = new PanelButtonListener(row, col);
        panelButtons[row][col].addActionListener(panelButtonListeners[row][col]);
        panelButtons[row][col].setBackground(Color.BLUE);
        gameBoardPanel.add(panelButtons[row][col]);

                     // All rows that are not the bottom most row should display the not yet available slot
                     // image and should not be clickable.
        if (row < NUM_ROWS - 1)
                     {             
            panelButtons[row][col].setDisabledIcon(notYetAvailableSlot);  
            panelButtons[row][col].setEnabled(false);
        }
        }
    }

    gameBoard.getContentPane().add(gameBoardPanel);
    gameBoard.pack();
    gameBoard.setVisible(true);
    gameBoard.setEnabled(true);

    }
    
    // Class that listens to the menu items in the menu bar. The menu has two items: 
    // 1) Start a new game
    // 2) Quit
    class MenuItemListener implements ActionListener
    {
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals(“Start a new Game"))
               {
                      // First clear the game before starting a new one.
        gameBoard.getContentPane().remove(panel);
        initNewGame();
        } 
        else if (selection.equals("Quit this game”))
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
        // TODO(Anika): implement this method.

    }
}
