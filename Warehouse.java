
/**
 * Stores books, cds and movies
 */
import java.util.ArrayList;

public class Warehouse
{
    private ArrayList<Media> books = new ArrayList<Media>();
    private ArrayList<Media> cds = new ArrayList<Media>();
    private ArrayList<Media> movies = new ArrayList<Media>();

    /**
     * Constructor for objects of class Warehouse
     */
    public Warehouse()
    {
        Book book1 = new Book("James Greene", "The fault in our stars", 15, 10);
        addBook(book1);
        Book book2 = new Book("Edna O'Brien", "A pagan place", 23, 10);
        addBook(book2);
        Book book3 = new Book("Steven Pinker", "Sense of style", 17, 10);
        addBook(book3);
        
        CD cd1 = new CD("Darude", "Sandstorm", 20, 10, "mp3");
        addCD(cd1);
        CD cd2 = new CD("Scandal", "Harukaze", 23, 10, "mp3");
        addCD(cd2);
        CD cd3 = new CD("One Ok Rock", "Zankyo Reference", 30, 10, "mp3");
        addCD(cd3);
        
        Movie mv1 = new Movie("Joss Whedon", "Kevin Feige", "Avengers Assemble", 30, 10, "avi");
        addMovie(mv1);        
        Movie mv2 = new Movie("Hayao Miyazaki", "Toru Hara", "My Neighbor Totoro", 50, 10, "avi");
        addMovie(mv2);
        Movie mv3 = new Movie("Isao Takahata", "Toru Hara", "Grave of The Fireflies", 45, 10, "avi");
        addMovie(mv3);
    }
    
    public void addBook(Book pbook)
    {
        books.add(pbook);
    }
    
    public void addCD(CD pcd)
    {
        cds.add(pcd);
    }
    
    public void addMovie(Movie pmovie)
    {
        movies.add(pmovie);
    }
    
    public final ArrayList<Media> getBooks()
    {
        return books;
    }
    
    public final ArrayList<Media> getCDs()
    {
        return cds;
    }
    
    public final ArrayList<Media> getMovies()
    {
        return movies;
    }        
}
