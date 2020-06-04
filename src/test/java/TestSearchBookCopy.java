import company.controller.Kommandozeile;
import company.databases.BookCopyDataBase;
import company.databases.BookDataBase;
import company.databases.CustomerDataBase;
import company.objects.Book;
import company.objects.BookCopy;
import company.objects.Customer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TestSearchBookCopy {
    Book bookOne = new Book("Test", "Tester", "2020", "123", "Stuttgart", "Testing", 1);
    BookCopy bookCopy = new BookCopy(bookOne, 123, "Regal 4 Fach 5", new Date());

    /**
     * Testing to search for a book that exists.
     */
    @Test
    public void testSearchExistingBook(){
        bookDataBase.addBook(bookOne);
        bookCopyDataBase.add(bookCopy);
        assertEquals(bookCopy, this.searchBookCopy(123));
    }

    /**
     * Testing to search for a book that isn´t existing.
     */
    @Test
    public void testSearchNotExistingBook(){
        assertEquals(null, this.searchBookCopy(999));
    }

}
