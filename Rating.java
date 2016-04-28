
/**
 * Representation of a rating for a Book/CD/Movie
 */
public class Rating
{
    // instance variables - replace the example below with your own
    private int[] scores = new int[100];
    int numberOfRatings = 0;

    /**
     * Constructor for objects of class Rating
     */
    public Rating()
    {
        // initialise instance variables
    }

    public void updateRating(int prating)
    {
        if(numberOfRatings < 100){
            scores[numberOfRatings] = prating;
            ++numberOfRatings;
            return;
        }        

        //simulate a pop_front, but without the resizing of the array capacity
        for(int i = 0; i < 99; ++i){
            scores[i] = scores[i + 1]; //Oh boy, don't go out of bounds here
        }
        //don't forget to update the last value.
        scores[99] = prating;
    }

    /**
     * @return (the scores array)
     */
    public final int[] getScores()
    {
        return scores;
    }
    
    /**
     * @return (a score by index
     */
    public final int getScoreByIndex(int pindex)
    {
        if(pindex < 100){
            return scores[pindex];
        }
        return -1;
    }
    
    /**
     * @return (Iaverage)
     */
    public final int getAverage()
    {
        int average = 0;
        for(int i = 0; i < scores.length; ++i){
            average += scores[i];
        }
        
        return (average / scores.length);
    }
}

