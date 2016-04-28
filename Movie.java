
/**
 * Representation of a movie
 */
public class Movie extends Media
{
    // instance variables - replace the example below with your own\
    private String director = null;
    private String producer = null;    
    private String fileType = null;

    /**
     * @param (Sdirector, Sproducer, Stitle, Dprice, Iavailable, SfileType)
     */
    public Movie(String pdirector, String pproducer, String ptitle, double pprice,int pavailable, String pfileType)
    {
        director = pdirector;
        producer = pproducer;
        title = ptitle;
        price = pprice;
        available = pavailable;
        fileType = pfileType;
    }    

    /**
     * Returns results in String 
     */
    public final String toString()
    {
        String text = "";
        text += "'" + title + "' directed by " + director + " produced by " + producer+ " $" + price + "\n";
        
        return text;
    }
}
