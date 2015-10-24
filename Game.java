import java.util.*;

/**
 * Game Mechanics
 * 
 * @author Ryan Taplin 
 * @version 1.0
 */
public class Game
{
    private int numOfPlayers, playerTurn;

    private String firstValue;
    private ArrayList<Player> players = new ArrayList<Player>();
    private Ranking leaderboard;

    /**
     * Constructor for objects of class Game
     */
    public Game(int players)
    {
        if (players > 2)
        {
            System.out.println("Currently can't have more than 2 players");
            numOfPlayers = 2;
        }
        else
        {
            numOfPlayers = players;
        }

        leaderboard = new Ranking();
        testRanks();
        
        playerTurn = 1;
        createPlayers();
    }

    public void testRanks()
    {
        for (int i = 0; i < 10; i++)
        {
            leaderboard.checkAddRank(new Player(1), "Default");
        }
    }

    public Ranking getLeaderboard()
    {
        return leaderboard;
    }

    public Player getCurPlayer()
    {
        return players.get(playerTurn - 1);
    }

    private void createPlayers()
    {
        for (int i = 0; i < numOfPlayers; i++)
        {
            players.add(new Player(i + 1));
        }
    }

    public void setNextPlayerTurn()
    {
        if(playerTurn >= numOfPlayers)
        {
            playerTurn = 0;
        }
        playerTurn++;
    }

    public int getPlayerTurn()
    {
        return playerTurn;
    }

    public String getFirstValue()
    {
        return firstValue;
    }

    public void setFirstValue(String e)
    {
        firstValue = e;
        //System.out.println(e);
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }
}
