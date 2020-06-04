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
     *
     * Here all conditions are fulfilled to delete a customer.
     * Therefore, the system tests whether the customer database
     * no longer contains the element to be deleted
     */
    @Test
    public void testCustomerOk() {
        //Book bookOk = new Book(titel,autoren, jahr, isbn);
        Customer customerOK = new Customer("Dummy", "Object", "00000000", "Straße", "32232", "Stuttgart");
        customerDataBase.addCustomer(customerOK);
        assertTrue(customerDataBase.deleteCustomer(customerOK));
        assertFalse(customerDataBase.getCustomerDataBase().contains(customerOK));
    }

    /**
     * One of the conditions is not fulfilled in this case. The customer
     * did not even belong to the database. However, an attempt is made
     * here to delete it.  Therefore this will not be done.
     */
    @Test
    public void testCustomerInvalid() {
        Customer customerInvalid = new Customer("Dummy", "Object", "00000000", "Straße", "32232", "Stuttgart");
        assertFalse(customerDataBase.deleteCustomer(customerInvalid));
    }

    /**
     * One of the conditions is not fulfilled in this case. The customer has
     * not paid the overdraft fees. However, an attempt is made here to delete
     * it.  Therefore this will not be done.
     */
    @Test
    public void testCustomerFeesNotPaid() {
        Customer customerFeesNotPaid = new Customer("Dummy", "Object", "00000000", "Straße", "32232", "Stuttgart");
        customerDataBase.addCustomer(customerFeesNotPaid);
        customerFeesNotPaid.setOverdraftFeeStatus(true);
        assertFalse(customerDataBase.deleteCustomer(customerFeesNotPaid));
    }

    /**
     * One of the conditions is not fulfilled in this case. The customer has not
     * returned all the books he has borrowed. However, an attempt is made to
     * delete this one. Therefore this will not be done.
     */
    @Test
    public void testCustomerAllBookNotReturned() {
        Book book = new Book("Dummy", new ArrayList<>(Arrays.asList("Hallo")), "222", "123432", "Stuttgart", "Hueber", 2);
        Customer customerAllBookNotReturned = new Customer("Dummy", "Object", "00000000", "Straße", "32232", "Stuttgart");
        customerDataBase.addCustomer(customerAllBookNotReturned);
        customerAllBookNotReturned.setBooksOnLoan(new ArrayList<>(Arrays.asList(new BookCopy(book, "001", "1. Stock", new Date()))));
        assertFalse(customerDataBase.deleteCustomer(customerAllBookNotReturned));
    }

    @Test
    public void testCustomerIsNull(){
        Customer customer = null;
        assertFalse(customerDataBase.deleteCustomer(customer));
    }


}