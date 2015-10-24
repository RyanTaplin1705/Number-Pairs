
/**
 * Card Values
 * 
 * @author Ryan Taplin 
 * @version 1.0
 */
public class Card
{
    // 1 = Circle , 2 = Square, 3 = Triangle, 4 = Rectangle
    private int type;

    /**
     * Constructor for objects of class Card
     */
    public Card(int t)
    {
        type = t;
    }
    
    public int getType()
    {
        return type;
    }
}
