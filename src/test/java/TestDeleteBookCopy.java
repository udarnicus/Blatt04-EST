import company.controller.Kommandozeile;
import company.databases.BookCopyDataBase;
import company.databases.BookDataBase;
import company.objects.Customer;
import org.junit.jupiter.api.Test;

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

    /**
     * Here all conditions are fulfilled to delete a book copy.
     * Therefore, the system tests whether the book copy database
     * no longer contains the element to be deleted
     */
    @Test
    public void testBookCopyOk() {
        Kommandozeile.startTestingEnviroment();
        assertTrue(Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().contains(Kommandozeile.searchBookCopy(
                "111")));
        assertEquals(3, Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().size());
        Kommandozeile.deleteBookCopyFromDataBase("111");
        assertFalse(Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().contains(Kommandozeile.searchBookCopy(
                "111")));
        assertEquals(2, Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().size());

    }

    /**
     * One of the conditions is not fulfilled in this case. The bookCopy
     * did not even belong to the database. However, an attempt is made
     * here to delete it. Therefore an exception is expected here.
     */
    @Test
    public void testBookCopyInvalid() {
        Kommandozeile.startTestingEnviroment();
        assertEquals(3, Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().size());
        assertFalse(Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().contains(Kommandozeile.searchBookCopy(
                "1234456435454")));
        Kommandozeile.deleteBookCopyFromDataBase("1234456435454");
        assertEquals(3, Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().size());
    }

    /**
     * One of the conditions is not fulfilled in this case. The bookCopy is already on loan.
     * However, an attempt is made here to delete it.
     * Therefore an exception is expected here.
     */

    @Test
    public void testBookCopyOnLoan() {
        Kommandozeile.startTestingEnviroment();
        assertEquals(3, Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().size());
        assertTrue(Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().contains(Kommandozeile.searchBookCopy(
                "111")));
        Customer customer = Kommandozeile.getCustomerDataBase().getCustomerDataBase().get(0);
        Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().get(0).setCurrentBorrower(customer);
        Kommandozeile.deleteBookCopyFromDataBase("111");
        assertEquals(Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().size(), 3);
        assertTrue(Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().contains(Kommandozeile.searchBookCopy(
                "111")));

    }
}


