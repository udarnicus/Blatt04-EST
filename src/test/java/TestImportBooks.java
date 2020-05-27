import company.databases.BookDataBase;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to test the method importBooks from the class
 * {@link BookDataBase} that contains testing methods when
 * an attempt is made to import a book from one csv File.
 *
 * @author Mohamed
 * @version 25.05.2020
 */

public class TestImportBooks {
    /**
     * The books will be imported and then it will be checked, if
     * all books have been imported. Then many attributes will be
     * compared to see whether the books have been correctly
     * imported.
     */
    @Test
    public void importBooks() {
        final BookDataBase bookDataBase = new BookDataBase();
        //check data base empty before importing the books
        assertEquals(bookDataBase.getBookDataBase().size(), 0);

        bookDataBase.importBooks("src\\test\\java\\ressources\\csvFileToTest.csv");

        //check the number of imported books
        assertEquals(bookDataBase.getBookDataBase().size(), 5);

        // check three titles of the first books which have been added.
        assertTrue(bookDataBase.getBookDataBase().get(0).getTitle().equals("Theoretische Informatik"));
        assertTrue(bookDataBase.getBookDataBase().get(1).getTitle().equals("Softwaretechnik"));
        assertTrue(bookDataBase.getBookDataBase().get(2).getTitle().equals("Mathematik"));

        // check the number of authors in 3 different books
        assertEquals(bookDataBase.getBookDataBase().get(0).getAuthors().size(), 2);
        assertEquals(bookDataBase.getBookDataBase().get(2).getAuthors().size(), 1);
        assertEquals(bookDataBase.getBookDataBase().get(3).getAuthors().size(), 1);

        // check that the publisher the same are for two books and not the same for two other books
        assertEquals(bookDataBase.getBookDataBase().get(0).getPublisher(), bookDataBase.getBookDataBase().get(1).getPublisher());
        assertNotEquals(bookDataBase.getBookDataBase().get(0).getPublisher(), bookDataBase.getBookDataBase().get(4).getPublisher());

        //Ausgabe aller Titel
        bookDataBase.getBookDataBase().stream().map(book -> book.getTitle()).forEach(System.out::println);

        // Ausgabe aller Städten, wo die Bücher erstellt wurden
        bookDataBase.getBookDataBase().stream().map(book -> book.getCity()).forEach(System.out::println);

    }
}