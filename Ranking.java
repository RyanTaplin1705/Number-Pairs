import java.util.*;

/**
 * Ranking mechanics and data
 * 
 * @author Ryan Taplin 
 * @version 1.0
 */
public class Ranking
{
    private Rank rank;
    private ArrayList<Rank> topTen = new ArrayList<Rank>();

    public void setRanking(Player player, String playerName)
    {
        Rank temp = new Rank(player, playerName);
    }

    public ArrayList<Rank> getRanking()
    {
        return topTen;
    }

    public void checkAddRank(Player player, String name)
    {
        if (topTen.size() < 10)
        {
            topTen.add(new Rank(player, name));
            return;
        }
        else
        {
            for (int i = 0; i < topTen.size(); i++)
            {
                if (player.getScore() > topTen.get(i).getPlayer().getScore())
                {
                    topTen.get(i).setNew(player, name);
                    return;
                }
            }
        }
    }
}
