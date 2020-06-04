import company.objects.Book;
import company.objects.BookCopy;
import company.objects.Customer;
import company.controller.Kommandozeile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class TestBorrowBookCopy {
    ArrayList<String> authors = new ArrayList<>();
    Book book = new Book("Test", authors, "2020", "123", "Stuttgart", "Testing", 1);
    BookCopy bookCopy = new BookCopy(book, "123", "Regal 4 Fach 5", new Date());
    BookCopy bookCopy2 = new BookCopy(book, "124", "Regal 4 Fach 5", new Date());
    BookCopy bookCopy3 = new BookCopy(book, "125", "Regal 4 Fach 5", new Date());
    BookCopy bookCopy4 = new BookCopy(book, "126", "Regal 4 Fach 5", new Date());
    BookCopy bookCopy5 = new BookCopy(book, "127", "Regal 4 Fach 5", new Date());
    BookCopy bookCopy6 = new BookCopy(book, "128", "Regal 4 Fach 5", new Date());
    Customer customer = new Customer("Dummy", "Dum", "765", "Dummystreet", "44444", "München");
    ArrayList<BookCopy> bookCopyDataBase = new ArrayList<>();
    ArrayList<Book> bookDataBase = new ArrayList<>();
    ArrayList<Customer> customerDataBase = new ArrayList<>();

    /**
     * This test is testing to borrow a book copy
     * that exists. In this case the customer also
     * exists.
     */
    @Test
    public void testBorrowExistingBookCopy (){
        customerDataBase.add(customer);
        bookDataBase.add(book);
        bookCopyDataBase.add(bookCopy);
        assertEquals(true, Kommandozeile.borrowBookCopy("123", customer));

    }

    /**
     * This test is testing to borrow a book copy
     * that doesn´t exists. The customer exists.
     */
    @Test
    public void testBorrowNotExistingBookCopy (){
        customerDataBase.add(customer);
        assertEquals(false, Kommandozeile.borrowBookCopy("123", customer));
    }

    /**
     * This method is testing to borrow a book copy
     * if the customer has an overdraft fee status.
     */
    @Test
    public void testBorrowBookCopyWithOverdraftFee (){
        customerDataBase.add(customer);
        bookDataBase.add(book);
        bookCopyDataBase.add(bookCopy);
        customer.setOverdraftFeeStatus(true);
        assertEquals(false, Kommandozeile.borrowBookCopy("123", customer));

    }

    /**
     * This method is testing to borrow a book copy
     * if the customer already has more than 5 books.
     */
    @Test
    public void testBorrowBookCopyWithToHighLoanStatus (){
        customerDataBase.add(customer);
        bookDataBase.add(book);
        bookCopyDataBase.add(bookCopy);
        ArrayList <BookCopy> booksOnLoan = new ArrayList<>();
        booksOnLoan.add(bookCopy);
        booksOnLoan.add(bookCopy2);
        booksOnLoan.add(bookCopy3);
        booksOnLoan.add(bookCopy4);
        booksOnLoan.add(bookCopy5);
        customer.setBooksOnLoan(booksOnLoan);
        assertEquals(false, Kommandozeile.borrowBookCopy("128",customer));

    }

    /**
     * This method is testing to borrow a book copy
     * if the customer has an invalid payment status.
     */
    @Test
    public void testBorrowBookCopyWithInvalidPaymentStatus (){
        customerDataBase.add(customer);
        bookDataBase.add(book);
        bookCopyDataBase.add(bookCopy);
        customer.setPaymentStatus(false);
        assertEquals(false, Kommandozeile.borrowBookCopy("123", customer));

    }
}
