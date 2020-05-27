
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
    BookDataBase bookDataBase = new BookDataBase();

    ArrayList<String> authors = new ArrayList<>();
    Customer customer = new Customer("Testing", "Tester", "123456", "Home", "1234", "Stuttgart");
    Book borrowedBook = new Book( "Test", authors, "2020", "123","Stuttgart", "TestingTesterWriter", 1);
    BookCopy borrowedBookCopy = new BookCopy(borrowedBook, "111", "Regal 1 Fach 2", new Date());
    ArrayList<BookCopy> bookCopyDataBase = new ArrayList<>();

    /**
     * This method tests to delete book with a borrowed book
     * copy. It is not possible to delete a book
     * without all book copies are available at the library.
     */
    @Test
    public void testDeleteBorrowedBook(){
        bookDataBase.addBook(borrowedBook);
        borrowedBookCopy.setCurrentBorrower(customer);
        bookCopyDataBase.add(borrowedBookCopy);
        bookDataBase.setBookCopyDataBase(bookCopyDataBase);
        assertFalse(bookDataBase.deleteBook(borrowedBook));
        assertTrue(bookDataBase.getBookCopyDataBase().contains(borrowedBookCopy));
    }

    /**
     * All conditions are fulfilled to delete a book.
     * This method tests to delete a book. All book
     * copies are in the library. No one is borrowed.
     */
    @Test
    public void testDeleteNotBorrowedBook(){
        bookDataBase.addBook(borrowedBook);
        bookCopyDataBase.add(borrowedBookCopy);
        bookDataBase.setBookCopyDataBase(bookCopyDataBase);
        assertTrue(bookDataBase.deleteBook(borrowedBook));
    }

    /**
     * This method tests to delete a book that does not
     * exists. The book is not deposited in our data
     * base. It is not able to delete the book.
     */
    @Test
    public void testDeleteNotExcistingBook(){
        assertFalse(bookDataBase.deleteBook(borrowedBook));
    }

    /**
     * This method test to delete a book but not all
     * book copies are in the library. In this case
     * one book copy is borrowed and one is not borrowed.
     * It is not possible to delete a book, if one
     * book copy is borrowed.
     */
    @Test
    public void testDeleteNotAllBookCopiesBorrowed(){
        bookDataBase.addBook(borrowedBook);
        BookCopy thisIsBorrowed = new BookCopy(borrowedBook, "333", " Regal 3 Fach 6", new Date());
        BookCopy thisIsNotBorrowed =new BookCopy(borrowedBook, "334", "Regal 3 Fach 7", new Date());
        thisIsBorrowed.setCurrentBorrower(customer);
        bookCopyDataBase.add(thisIsBorrowed);
        bookCopyDataBase.add(thisIsNotBorrowed);
        bookDataBase.setBookCopyDataBase(bookCopyDataBase);
        assertFalse(bookDataBase.deleteBook(borrowedBook));
    }
}
