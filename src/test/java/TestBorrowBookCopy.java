import company.objects.Book;
import company.objects.BookCopy;
import company.objects.Customer;
import company.controller.Kommandozeile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class TestBorrowBookCopy {

    /**
     * This test is testing to borrow a book copy
     * that exists. In this case the customer also
     * exists.
     */
    @Test
    public void testBorrowExistingBookCopy (){
        Kommandozeile.startTestingEnviroment();
        assertTrue(Kommandozeile.borrowBookCopy("111","123457"));
        assertTrue(Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().get(0).getLoanStatus());
        assertTrue(Kommandozeile.getCustomerDataBase().getCustomerDataBase().get(1).getBooksOnLoan().contains(Kommandozeile.searchBookCopy("111")));


    }

    /**
     * This test is testing to borrow a book copy
     * that doesn´t exists. The customer exists.
     */
    @Test
    public void testBorrowNotExistingBookCopy (){
        Kommandozeile.startTestingEnviroment();
        assertFalse(Kommandozeile.borrowBookCopy("12312332413242134","123457"));

    }

    /**
     * This method is testing to borrow a book copy
     * if the customer has an overdraft fee status.
     */
    @Test
    public void testBorrowBookCopyWithOverdraftFee (){
        Kommandozeile.startTestingEnviroment();
        Kommandozeile.getCustomerDataBase().getCustomerDataBase().get(1).setOverdraftFeeStatus(true);
        assertFalse(Kommandozeile.borrowBookCopy("111" , "123457"));

    }

    /**
     * This method is testing to borrow a book copy
     * if the customer already has more than 5 books.
     */
    @Test
    public void testBorrowBookCopyWithToHighLoanStatus (){
        Kommandozeile.startTestingEnviroment();
        Customer customer = Kommandozeile.getCustomerDataBase().getCustomerDataBase().get(0);
        customer.getBooksOnLoan().add(Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().get(0));
        customer.getBooksOnLoan().add(Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().get(0));
        customer.getBooksOnLoan().add(Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().get(0));
        customer.getBooksOnLoan().add(Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().get(0));
        customer.getBooksOnLoan().add(Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().get(0));
        assertFalse(Kommandozeile.borrowBookCopy("111", "123456"));


    }

    /**
     * This method is testing to borrow a book copy
     * if the customer has an invalid payment status.
     */
    @Test
    public void testBorrowBookCopyWithInvalidPaymentStatus (){
        Kommandozeile.startTestingEnviroment();
        Customer customer = Kommandozeile.getCustomerDataBase().getCustomerDataBase().get(0);
        customer.setPaymentStatus(false);
        assertFalse(Kommandozeile.borrowBookCopy("111", "123456" ));

    }
}
