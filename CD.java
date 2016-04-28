
/**
 * Representation of a CD
 */
public class CD extends Media
{
    // instance variables - replace the example below with your own
    private String performer = null;    
    private String fileType = null;


    /**
     * @param (Sperformer, Stitle, Dprice, Iavailability, Sfiletype)
     */
    public CD(String pperformer, String ptitle, double pprice, int pavailable, String pfileType)
    {
        performer = pperformer;
        title = ptitle;
        price = pprice;
        available = pavailable;
        fileType = pfileType;
    }    

    /**
     * @return string representaion
     */
    public final String toString()
    {
        String text = "";
        text += "'" + title + "' by " + performer + ", $" + price + "\n";

        return text;
    }
}
