
/**
 * Player values
 * 
 * @author Ryan Taplin 
 * @version 1.0
 */
public class Player
{
    // instance variables - replace the example below with your own
    private int num;
    private int score;

    /**
     * Constructor for objects of class Player
     */
    public Player(int n)
    {
        num = n;
        score = 0;
    }

    public int getScore()
    {
        return score;
    }
    
    public void setScore()
    {
        score = score + 1;
    }
    
    public void resetScore()
    {
        score = 0;
    }
    
    public int getNum()
    {
        return num;
    }
}
