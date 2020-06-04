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

public class TestBorrowBookCopy {
    Book bookOne = new Book("Test", "Tester", "2020", "123", "Stuttgart", "Testing", 1);
    BookCopy bookCopy = new BookCopy(bookOne, 123, "Regal 4 Fach 5", new Date());
    Customer customer = new Customer("Dummy", "Dum", "765", "Dummystreet", "44444", "München");

    /**
     * This test is testing to borrow a book copy
     * that exists. In this case the customer also
     * exists.
     */
    @Test
    public void testBorrowExcistingBookCopy (){
        custumerDataBase.add(customer);
        bookDataBase.add(bookOne);
        bookCopyDataBase.add(bookOne);
        assertEquals(ture, this.borrowBookCopy(bookOne));
    }

    /**
     * This test is testing to borrow a book copy
     * that doesn´t exists. The customer exists.
     */
    @Test
    public void testBorrowNotExcistingBookCopy (){
        customerDataBase.add(customer);
        asssertEquals(false, this.borrowBookCopy(bookOne));
    }

    /**
     * This method is testing to borrow a book copy
     * if the customer has an overdraft fee status.
     */
    @Test
    public void testBorrowBookCopyWithOverdraftFee (){
        custumerDataBase.add(customer);
        bookDataBase.add(bookOne);
        bookCopyDataBase.add(bookOne);
        customer.setOverdrafFeeStatus(true);
        assertEquals(false, this.borrowBookCopy(bookOne));

    }

    /**
     * This method is testing to borrow a book copy
     * if the customer already has more than 5 books.
     */
    @Test
    public void testBorrowBookCopyWithToHighLoanStatus (){
        custumerDataBase.add(customer);
        bookDataBase.add(bookOne);
        bookCopyDataBase.add(bookOne);
        //mehr als 5 bücher in dieser liste
        customer.getBooksOnLoan().size()>5;
        assertEquals(false, this.borrowBookCopy(bookOne));

    }

    /**
     * This method is testing to borrow a book copy
     * if the customer has an invalid payment status.
     */
    @Test
    public void testBorrowBookCopyWithInvalidPaymentStatus (){
        custumerDataBase.add(customer);
        bookDataBase.add(bookOne);
        bookCopyDataBase.add(bookOne);
        customer.setPaymentStatus(false);
        assertEquals(false, this.borrowBookCopy(bookOne));

    }
}
