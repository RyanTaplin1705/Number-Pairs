
/**
 * Rank values
 * 
 * @author Ryan Taplin 
 * @version 1.0
 */
public class Rank
{    
    private Player player;
    private String playerName;
    
    public Rank(Player p, String pn)
    {
        player = p;
        playerName = pn;
    }
    
    public String getPlayerName()
    {
        return playerName;
    }
    
    public Player getPlayer()
    {
        return player;
    }

    public void setNew(Player p, String pn)
    {
        player = p;
        playerName = pn;
    }
}
