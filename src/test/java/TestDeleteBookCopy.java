import company.databases.BookCopyDataBase;
import company.databases.BookDataBase;
import company.objects.Book;
import company.objects.BookCopy;
import company.objects.Customer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to test the method deleteBookCopy from the class
 * {@link BookCopyDataBase} that contains testing methods when
 * an attempt is made to delete a BookCopy from the database.
 * Many conditions are checked before this happens. Each
 * condition is checked in a test method
 *
 * @version 15.05.2020
 */

public class TestDeleteBookCopy {

        BookCopyDataBase bookCopyDataBase = new BookCopyDataBase();
        BookDataBase bookDataBase = new BookDataBase();

    /**
     * Here all conditions are fulfilled to delete a book copy.
     * Therefore, the system tests whether the book copy database
     * no longer contains the element to be deleted
     */
    @Test
    public void testBookCopyOk(){
        Date date = new Date(120, 4, 14);
        Book bookOk = new Book("Dummy", new ArrayList<>(Arrays.asList("Hallo")), "222", "123432", "Stuttgart", "Hueber", 2);

        bookCopyDataBase.setBookDataBase(bookDataBase.getBookDataBase());
        bookDataBase.setBookCopyDataBase(bookCopyDataBase.getBookCopyDataBase());

        bookDataBase.addBook(bookOk);
        BookCopy bookCopyOk = new BookCopy(bookOk, "1", "E78", date);
        bookCopyDataBase.addBookCopy(bookCopyOk);

        assertTrue(bookCopyDataBase.deleteBookCopy(bookCopyOk));
        assertFalse(bookCopyDataBase.getBookDataBase().contains(bookCopyOk));
    }

    /**
     * One of the conditions is not fulfilled in this case. The bookCopy
     * did not even belong to the database. However, an attempt is made
     * here to delete it. Therefore an exception is expected here.
     */
    @Test
    public void testBookCopyInvalid() {
        bookCopyDataBase.setBookDataBase(bookDataBase.getBookDataBase());
        bookDataBase.setBookCopyDataBase(bookCopyDataBase.getBookCopyDataBase());

        Date date = new Date(120, 4, 14);
        Book book = new Book("Titel", new ArrayList<>(Arrays.asList("Author1")), "2020", "1234567891011", "Stuttgart", "Verlag", 2);
        BookCopy bookCopyInvalid = new BookCopy(book, "1", "E78", date);
        assertFalse(bookCopyDataBase.deleteBookCopy(bookCopyInvalid));
    }

    /**
     * One of the conditions is not fulfilled in this case. The bookCopy is already on loan.
     * However, an attempt is made here to delete it.
     * Therefore an exception is expected here.
     */

    @Test
    public void testBookCopyOnLoan() {

        bookCopyDataBase.setBookDataBase(bookDataBase.getBookDataBase());
        bookDataBase.setBookCopyDataBase(bookCopyDataBase.getBookCopyDataBase());

        Date date = new Date(120, 4, 15);
        Book book3 = new Book("Dummy", new ArrayList<>(Arrays.asList("Hallo")), "222", "123432", "Stuttgart", "Hueber", 2);
        BookCopy bookCopyOnLoan = new BookCopy(book3, "1", "E78", date);
        bookDataBase.addBook(book3);
        bookCopyDataBase.addBookCopy(bookCopyOnLoan);


        Customer customer1 = new Customer("Max", "Mustermann", "0123456", "Straße", "32232", "Stuttgart");
        bookCopyOnLoan.setCurrentBorrower(customer1);

        assertFalse(bookCopyDataBase.deleteBookCopy(bookCopyOnLoan));
    }
}


