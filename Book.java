
/**
 * Representation of a book
 */
public class Book extends Media
{
    // instance variables - replace the example below with your own    

    /**
     * @param (author, title, price, available)
     */
    public Book(String pauthor, String ptitle, double pprice, int pavailable)
    {
        // initialise instance variables
        author = pauthor;
        title = ptitle;
        price = pprice;
        available = pavailable;
    }

    /**
     * @return (string representation)
     */
    public final String toString()
    {
        String text = "";
        text = "'" + title + "' by " + author + ", $" + price + "\n";

        return text;
    }
}
