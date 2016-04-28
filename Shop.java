
/**
 * Gets books/cds/movies from the warehouse and sells them
 */
import java.util.List;
import java.util.ArrayList;

public class Shop
{
    // instance variables - replace the example below with your own
    private List<Media> books;
    private List<Media> cds;
    private List<Media> movies;
    
    public List<Media> books2 = new ArrayList<Media>();
    public List<Media> cds2 = new ArrayList<Media>();
    public List<Media> movies2 = new ArrayList<Media>();
    
    public List<Media> rBooks = new ArrayList<Media>();
    public List<Media> rCDs = new ArrayList<Media>();
    public List<Media> rMovies = new ArrayList<Media>();
    Warehouse warehouse = null;

    private int bookSold = 0, cdSold = 0, movieSold = 0;
    private double revenue = 0;

    /**
     * Constructor for objects of class Shop
     */
    public Shop()
    {
        warehouse = new Warehouse();
        books = warehouse.getBooks();
        cds = warehouse.getCDs();
        movies = warehouse.getMovies();                        
    }    
    
    public void updateList()
    {        
        //Books
        for(int i = 0; i < books.size(); ++i){
            if(books.get(i).getAvailable() < 1){
                books2.add(books.get(i));
                books.remove(i);
            }
        }   
        if(books.size() > 0){
            if(books.get(0).getAvailable() < 1){
                books2.add(books.get(0));
                books.remove(0);
            }
        }
        
        //CDs
        for(int i = 0; i < cds.size(); ++i){
            if(cds.get(i).getAvailable() < 1){
                cds2.add(cds.get(i));
                cds.remove(i);
            }
        }   
        if(cds.size() > 0){
            if(cds.get(0).getAvailable() < 1){
                cds2.add(cds.get(0));
                cds.remove(0);
            }
        }
        
        //Movies
        for(int i = 0; i < movies.size(); ++i){
            if(movies.get(i).getAvailable() < 1){
                movies2.add(movies.get(i));
                movies.remove(i);
            }
        }   
        if(movies.size() > 0){
            if(movies.get(0).getAvailable() < 1){
                movies2.add(movies.get(0));
                movies.remove(0);
            }
        }
    }      

    public final List<Media> getBooks()
    {
        return books;
    }

    public final List<Media> getCDs()
    {
        return cds;
    }

    public final List<Media> getMovies()
    {
        return movies;
    }       

    /**
     * sell a book, returns a receipt
     * @param (Iselection)
     * @return (Dprice)
     */
    public double sellBook(int pselection)
    {
        for(int i = 0; i < books.size(); ++i){ //loop through the entire list
            if(pselection == books.get(i).getID()){ //and now we've found the specified item
                books.get(i).sellCopies(1);
                return books.get(i).getPrice();
            }
        }
        return 0;
    }

    /**
     * rates the book
     * @param (Iselection, Irating)
     */
    public void rateBook(int pselection, int prating)
    {
        for(int i = 0; i < books.size(); ++i){ //loop through the entire list
            if(pselection == books.get(i).getID()){ //and now we've found the specified item
                books.get(i).updateRating(prating);
            }
        }
    }

    /** 
     * sells a cd, returns a receipt
     * @param (Iselection)
     * @return (Dprice)
     */
    public double sellCD(int pselection)
    {
        for(int i = 0; i < cds.size(); ++i){ //loop through the entire list
            if(pselection == cds.get(i).getID()){ //and now we've found the specified item
                cds.get(i).sellCopies(1);
                return cds.get(i).getPrice();
            }
        }
        return 0;
    }

    /**
     * @param (Iselection, Irating)
     */
    public void rateCD(int pselection, int prating)
    {
        for(int i = 0; i < cds.size(); ++i){ //loop through the entire list
            if(pselection == cds.get(i).getID()){ //and now we've found the specified item
                cds.get(i).updateRating(prating);
            }
        }
    }

    /** 
     * sells a movie, returns a receipt
     * @param (Iselection)
     * @return (Dprice)
     */
    public double sellMovie(int pselection)
    {
        for(int i = 0; i < movies.size(); ++i){ //loop through the entire list
            if(pselection == movies.get(i).getID()){ //and now we've found the specified item
                movies.get(i).sellCopies(1);
                return movies.get(i).getPrice();
            }
        }
        return 0;
    }

    /**
     * @param (Iselection, Irating)
     */
    public void rateMovie(int pselection, int prating)
    {
        for(int i = 0; i < movies.size(); ++i){ //loop through the entire list
            if(pselection == movies.get(i).getID()){ //and now we've found the specified item
                movies.get(i).updateRating(prating);
            }
        }
    }

    /**
     * displays a string which says ‘Sold x books, y CDs and z movies, total revenue $xxx’.
     */
    public String show()
    {
        String text = "Sold ";
        

        for(int i = 0; i < books.size(); ++i){ //get total of books sold
            bookSold += books.get(i).getSold();
            //find the price, multiply with the amount sold
            revenue += books.get(i).getPrice() * books.get(i).getSold();
        }

        for(int i = 0; i < cds.size(); ++i){ //get total cds sold
            cdSold += cds.get(i).getSold();
            revenue += cds.get(i).getPrice() * cds.get(i).getSold();
        }

        for(int i = 0; i < movies.size(); ++i){ //get total movies sold
            movieSold += movies.get(i).getSold();
            revenue += movies.get(i).getPrice() * movies.get(i).getSold();
        }

        text += bookSold + " books, " + cdSold + " CDs, and " + movieSold + " movies, total revenue $" + revenue + ".";
        return text;
    }   
}
