package company.objects;

import java.util.List;

/**
 * Represents a book
 *
 */
public class Book {
    private final String title;
    private final List<String> authors;
    private final String year;
    private final String isbn;
    private final String city;
    private final String publisher;
    private final int edition;


    public Book(final String title, final List<String> authors, final String year, final String isbn, final String city, final String publisher, final int edition){
        this.title = title;
        this.authors = authors;
        this.year = year;
        this.isbn = isbn;
        this.city = city;
        this.publisher = publisher;
        this.edition = edition;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getYear() {
        return year;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCity() {
        return city;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getEdition() {
        return edition;
    }
}
