import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * Creates and provides basic Graphical User Interface.
 * 
 * @author Ryan Taplin 
 * @version 1.0
 */
public class BasicGUI implements GUI
{
    private JFrame frame;
    private JPanel cardGrid, labelHolder, rankingsGrid;
    //private JTextField textField;
    private JLabel labelTurn, labelScore;
    private JButton cardButton;
    private Container contentPane;
    private Game game;
    private JButton firstButton;
    private int sizeX, sizeY;

    /**
     * Constructor for objects of class Basic
     */
    public BasicGUI(int playerAmount)
    {
        game = new Game(playerAmount);
        sizeX = 4;
        sizeY = 4;
        build();
    }

    public void build()
    {
        frame = new JFrame("Number Match");
        frame.setSize(400,400);

        makeMenu(frame);
        makeContent(frame);
        buildCardGrid();

        frame.setVisible(true);
    }

    private void makeContent(JFrame frame)
    {
        contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        labelHolder = new JPanel(new GridLayout(1, 2));
        contentPane.add(labelHolder, BorderLayout.NORTH);

        labelTurn = new JLabel("Player " + "one" + "'s turn");
        labelScore = new JLabel("Score: " + "0");

        labelHolder.add(labelTurn);
        labelHolder.add(labelScore);

        labelScore.setHorizontalAlignment(SwingConstants.RIGHT);
        labelTurn.setHorizontalAlignment(SwingConstants.LEFT);
    }

    private void buildCardGrid()
    {
        cardGrid = new JPanel(new GridLayout(sizeX, sizeY));
        contentPane.add(cardGrid, BorderLayout.CENTER);

        //divide the num by pictures equally
        int gridSize = sizeX * sizeY;
        Random random = new Random();

        //Create cards objects        
        for (int i = 0; i < gridSize; i+=2)
        {
            int randomNum = random.nextInt(4) + 1;
            //System.out.println("");
            //System.out.println("New Pair - " + randomNum);
            cards.add(new Card(randomNum));
            //System.out.println("Item1 - " + randomNum);
            cards.add(new Card(randomNum));
            //System.out.println("Item2 - " + randomNum);
        }

        //Place cards in JPanel
        for (int i = 0; cards.size() > 0; i++)
        {
            int randomNum = random.nextInt(cards.size());
            cardButton = new JButton("" + cards.get(randomNum).getType());
            cardButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) { buttonPush(e); }
                });
            cardGrid.add(cardButton);
            cards.remove(randomNum);
        }
    }

    private void buttonPush(ActionEvent e)
    {
        if (game.getFirstValue() != null)
        {
            //System.out.println("" + game.getFirstValue() + "--" + e.getActionCommand());
            JButton removebut = (JButton)e.getSource();
            if (game.getFirstValue().equals(e.getActionCommand()) && firstButton != removebut)
            {
                firstButton.setVisible(false);
                removebut.setVisible(false);
                game.setFirstValue(null);

                int totalScore = 0;
                ArrayList<Player> players = game.getPlayers();
                for (int i = 0; i < players.size(); i++)
                {
                    totalScore += players.get(i).getScore();
                }

                if (totalScore > (((sizeX * sizeY) / 2) - 1))
                {

                    String name = "test";
                    for (int i = 0; i < players.size(); i++)
                    {
                        game.getLeaderboard().checkAddRank(players.get(i), (name + i));
                    }

                    JOptionPane.showMessageDialog(frame, 
                        "This game allows you to play alone or with a friend.\n You must match the numbers on the buttons to get points.\nThe person with the highest score wins!",
                        "Gameplay Help", 
                        JOptionPane.QUESTION_MESSAGE);  
                }
                else
                {
                    nextTurn(true);
                }
            }
            else
            {
                game.setFirstValue(null);
                nextTurn(false);
            }
        }
        else
        {
            firstButton = (JButton)e.getSource(); 
            game.setFirstValue(e.getActionCommand());
        }
    }

    private void nextTurn(boolean incScore)
    {
        if (incScore)
        {
            game.getCurPlayer().setScore();
        }

        game.setNextPlayerTurn();
        setPlayerTurn();
        setPlayerScore();
    }

    private void setPlayerScore()
    {
        labelScore.setText("Score: " + game.getCurPlayer().getScore());
    }

    private void setPlayerTurn()
    {
        labelTurn.setText("Player " + getPlayerToText(game.getPlayerTurn()) + "'s turn");
    }

    private String getPlayerToText(int player)
    {
        String stringPlayer;
        switch(player)
        {
            case 1:
            stringPlayer = "one";
            break;
            case 2:
            stringPlayer = "two";
            break;
            case 3: 
            stringPlayer = "three";
            break;
            default:
            stringPlayer = "four";
            break;
        }
        return stringPlayer;
    }

    private void makeMenu(JFrame frame)
    {
        //Set vars
        JMenuBar menuBar;
        JMenuItem listItem;
        JMenu listFile;

        //Create menu bar and set to top of interface
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        //Create and add "File" JMenu(drop down) to menu bar
        listFile = new JMenu("File");
        menuBar.add(listFile);

        //File >> Close
        listItem = new JMenuItem("Close");
        listItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { Close(); }
            });
        listFile.add(listItem);

        // Call both buildMenuBar methods (for tidier code).
        buildMenuGame(menuBar, listItem);
        buildMenuHelp(menuBar, listItem);
    }

    private void buildMenuGame(JMenuBar menuBar, JMenuItem listItem)
    {
        //Create and add "Game" JMenu(drop down) to menubar
        JMenu listGame, listDifficulty;
        listGame = new JMenu("Game");
        menuBar.add(listGame);

        listDifficulty = new JMenu("Difficulty");
        listGame.add(listDifficulty);

        //Game >> Rankings
        listItem = new JMenuItem("Leaderboard");
        listItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { Leaderboard(); }
            });
        listGame.add(listItem);

        //Game >> Restart
        listItem = new JMenuItem("Restart");
        listItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { Restart(); }
            });
        listGame.add(listItem);

        listItem = new JMenuItem("Small - Easy");
        listItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { newSize(4,4); }
            });
        listDifficulty.add(listItem);

        listItem = new JMenuItem("Medium - Normal");
        listItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { newSize(6,6); }
            });
        listDifficulty.add(listItem);

        listItem = new JMenuItem("Hard - Tedious");
        listItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { newSize(8,8); }
            });
        listDifficulty.add(listItem);
    }

    public void Leaderboard()
    {
        JFrame rankingsFrame = new JFrame("Rankings"); 
        Container rankingPane = rankingsFrame.getContentPane();
        rankingPane.setLayout(new BorderLayout());

        JPanel rankingsGrid = new JPanel(new GridLayout(11, 1));
        rankingPane.add(rankingsGrid , BorderLayout.CENTER);
        rankingsGrid.add(new JLabel("TOP TEN RANKINGS"));

        for (int i = 0; i < game.getLeaderboard().getRanking().size(); i++)
        {
            String rName = game.getLeaderboard().getRanking().get(i).getPlayerName();
            int rScore = game.getLeaderboard().getRanking().get(i).getPlayer().getScore();
            rankingsGrid.add(new JLabel("#" + (i + 1) + " "+ rName + ": " + rScore));
        }    

        rankingsFrame.pack();
        rankingsFrame.setVisible(true);
    }

    public void newSize(int xv, int yv)
    {
        sizeX = xv;
        sizeY = yv;
        Restart();
    }

    private void buildMenuHelp(JMenuBar menuBar, JMenuItem listItem)
    {
        JMenu listHelp;

        //Create and add "Help" JMenu(drop down) to menu bar
        listHelp = new JMenu("Help");
        menuBar.add(listHelp);

        //Help >> Help
        listItem = new JMenuItem("Help");
        listItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { GameplayHelp(); }
            });
        listHelp.add(listItem);

        //Help >> About
        listItem = new JMenuItem("About");
        listItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { GameAboutHelp(); }
            });
        listHelp.add(listItem);
    }

    private void Restart()
    {
        ArrayList<Player> temp = game.getPlayers();
        for (int i = 0; i < temp.size(); i++)
        {
            temp.get(i).resetScore();
        }
        cardGrid.removeAll();
        buildCardGrid();
        cardGrid.revalidate();
    }

    private void Close()
    {
        System.exit(0);
    }

    private void GameplayHelp()
    {
        JOptionPane.showMessageDialog(frame, 
            "This game allows you to play alone or with a friend.\n You must match the numbers on the buttons to get points.\nThe person with the highest score wins!",
            "Gameplay Help", 
            JOptionPane.QUESTION_MESSAGE);       
    }

    private void GameAboutHelp()
    {
        JOptionPane.showMessageDialog(frame, 
            "Made By Ryan Taplin\n Student Of Kent\n March 2015",
            "Number Matching V1.0", 
            JOptionPane.INFORMATION_MESSAGE);             
    }
}
