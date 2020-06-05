import company.controller.Kommandozeile;
import company.databases.CustomerDataBase;
import company.objects.Book;
import company.objects.BookCopy;
import company.objects.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


/**
 * Test class to test the method deleteCustomer from the class
 * {@link CustomerDataBase} that contains testing methods when
 * an attempt is made to delete a customer from the database.
 * Many conditions are checked before this happens. Each
 * condition is checked in a test method
 *
 * @version 12.05.2020
 */
public class TestDeleteCustomer {
    CustomerDataBase customerDataBase = new CustomerDataBase();

    /**
     * Here all conditions are fulfilled to delete a customer.
     * Therefore, the system tests whether the customer database
     * no longer contains the element to be deleted
     */
    @Test
    public void testCustomerOk() {
        Kommandozeile.startTestingEnviroment();
        Kommandozeile.deleteCustomerFromDatabase("123456");
        assertFalse(Kommandozeile.getCustomerDataBase().getCustomerDataBase().contains(Kommandozeile.searchCustomer(
                "123456")));
        assertEquals(Kommandozeile.getCustomerDataBase().getCustomerDataBase().size(), 2);
    }

    /**
     * One of the conditions is not fulfilled in this case. The customer
     * did not even belong to the database. However, an attempt is made
     * here to delete it.  Therefore this will not be done.
     */
    @Test
    public void testCustomerInvalid() {
        Kommandozeile.startTestingEnviroment();
        Kommandozeile.deleteCustomerFromDatabase("1234566327345");
        assertFalse(Kommandozeile.getCustomerDataBase().getCustomerDataBase().contains(Kommandozeile.searchCustomer(
                "1234566327345")));
        assertEquals(Kommandozeile.getCustomerDataBase().getCustomerDataBase().size(), 3);
    }

    /**
     * One of the conditions is not fulfilled in this case. The customer has
     * not paid the overdraft fees. However, an attempt is made here to delete
     * it.  Therefore this will not be done.
     */
    @Test
    public void testCustomerFeesNotPaid() {
        Kommandozeile.startTestingEnviroment();
        Kommandozeile.getCustomerDataBase().getCustomerDataBase().get(0).setOverdraftFeeStatus(true);
        Kommandozeile.deleteCustomerFromDatabase("123456");
        assertTrue(Kommandozeile.getCustomerDataBase().getCustomerDataBase().contains(Kommandozeile.searchCustomer(
                "123456")));
        assertEquals(Kommandozeile.getCustomerDataBase().getCustomerDataBase().size(), 3);
    }

    /**
     * One of the conditions is not fulfilled in this case. The customer has not
     * returned all the books he has borrowed. However, an attempt is made to
     * delete this one. Therefore this will not be done.
     */
    @Test
    public void testCustomerAllBookNotReturned() {
        Kommandozeile.startTestingEnviroment();
        Customer customer = Kommandozeile.getCustomerDataBase().getCustomerDataBase().get(0);
        customer.getBooksOnLoan().add(Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().get(0));
          Kommandozeile.deleteCustomerFromDatabase("123456");
        assertTrue(Kommandozeile.getCustomerDataBase().getCustomerDataBase().contains(Kommandozeile.searchCustomer(
                "123456")));
        assertEquals(Kommandozeile.getCustomerDataBase().getCustomerDataBase().size(), 3);
    }

}