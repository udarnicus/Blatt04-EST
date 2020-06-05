
import company.controller.Kommandozeile;
import company.databases.BookDataBase;
import company.objects.Book;
import company.objects.BookCopy;
import company.objects.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;


/**
 * Test class to test the method deleteBook from the class
 * {@link BookDataBase} that contains testing methods when
 * an attempt is made to delete a book from the database.
 * Many conditions are checked before this happens. Each
 * condition is checked in a test method
 *
 * @version 15.05.2020
 */

public class TestDeleteBook {
    /**
     * This method tests to delete book with a borrowed book
     * copy. It is not possible to delete a book
     * without all book copies are available at the library.
     */
    @Test
    public void testDeleteBorrowedBook() {
        Kommandozeile.startTestingEnviroment();
        Customer customer = Kommandozeile.getCustomerDataBase().getCustomerDataBase().get(0);
        Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().get(0).setCurrentBorrower(customer);
        Kommandozeile.deleteBookFromDataBase("123");
        assertEquals(Kommandozeile.getBookDataBase().getBookDataBase().size(), 3);
        assertTrue(Kommandozeile.getBookDataBase().getBookDataBase().contains(Kommandozeile.searchBook("123")));
        assertTrue(Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().contains(Kommandozeile.searchBookCopy(
                "111")));
    }

    /**
     * All conditions are fulfilled to delete a book.
     * This method tests to delete a book. All book
     * copies are in the library. No one is borrowed.
     * All book copies must be deleted after this book
     * has been deleted
     */
    @Test
    public void testDeleteNotBorrowedBook() {
        Kommandozeile.startTestingEnviroment();
        Kommandozeile.deleteBookFromDataBase("123");
        assertEquals(Kommandozeile.getBookDataBase().getBookDataBase().size(), 2);
        assertFalse(Kommandozeile.getBookDataBase().getBookDataBase().contains(Kommandozeile.searchBook("123")));
        assertFalse(Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().contains(Kommandozeile.searchBookCopy(
                "111")));
    }

    /**
     * This method tests to delete a book that does not
     * exists. The book is not deposited in our data
     * base. It is not able to delete the book.
     */
    @Test
    public void testDeleteNotExcistingBook() {
        Kommandozeile.startTestingEnviroment();
        assertFalse(Kommandozeile.getBookDataBase().getBookDataBase().contains(Kommandozeile.searchBook("12222223")));
        Kommandozeile.deleteBookFromDataBase("12222223");
        assertEquals(Kommandozeile.getBookDataBase().getBookDataBase().size(), 3);

    }

    /**
     * This method test to delete a book but not all
     * book copies are in the library. In this case
     * one book copy is borrowed and one is not borrowed.
     * It is not possible to delete a book, if one
     * book copy is borrowed.
     */
    @Test
    public void testDeleteNotAllBookCopiesBorrowed() {
        Kommandozeile.startTestingEnviroment();
        Customer customer = Kommandozeile.getCustomerDataBase().getCustomerDataBase().get(0);
        Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().get(0).setCurrentBorrower(customer);
        Kommandozeile.deleteBookFromDataBase("123");
        assertEquals(Kommandozeile.getBookDataBase().getBookDataBase().size(), 3);
        assertTrue(Kommandozeile.getBookDataBase().getBookDataBase().contains(Kommandozeile.searchBook("123")));
        assertTrue(Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().contains(Kommandozeile.searchBookCopy(
                "111")));


    }
}

