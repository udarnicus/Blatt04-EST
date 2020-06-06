import company.controller.Kommandozeile;
import company.objects.BookCopy;
import company.objects.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class TestReturnBookCopy {
    @Test
    public void testBookCopyOk(){
        Kommandozeile.startTestingEnviroment();
        BookCopy bookCopy = Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().get(0);
        Customer customer = Kommandozeile.getCustomerDataBase().getCustomerDataBase().get(0);
        bookCopy.setCurrentBorrower(customer);
        bookCopy.setLoanStatus(true);
        bookCopy.setLoanDate(new Date());
        ArrayList books = new ArrayList<BookCopy>();
        books.add(bookCopy);
        customer.setBooksOnLoan(books);
        assertEquals(2, Kommandozeile.returnBookCopy(bookCopy.getId()));
        assertFalse(customer.getBooksOnLoan().contains(bookCopy));
        assertFalse(bookCopy.getLoanStatus());
        assertNull(bookCopy.getLoanDate());
        assertNull(bookCopy.getCurrentBorrower());

    }

    @Test
    public void testBookCopyLate(){
        Kommandozeile.startTestingEnviroment();
        BookCopy bookCopy = Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().get(0);
        Customer customer = Kommandozeile.getCustomerDataBase().getCustomerDataBase().get(0);
        bookCopy.setCurrentBorrower(customer);
        bookCopy.setLoanStatus(true);

        //Yesterday Date
        bookCopy.setLoanDate(new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L));

        ArrayList books = new ArrayList<BookCopy>();
        books.add(bookCopy);
        customer.setBooksOnLoan(books);
        assertEquals(1, Kommandozeile.returnBookCopy(bookCopy.getId()));
        assertFalse(customer.getBooksOnLoan().contains(bookCopy));
        assertFalse(bookCopy.getLoanStatus());
        assertNull(bookCopy.getLoanDate());
        assertNull(bookCopy.getCurrentBorrower());
        assertTrue(customer.getOverdraftFeeStatus());

    }

    @Test
    public void testBookCopyNotExisting(){
        Kommandozeile.startTestingEnviroment();
        assertEquals(0, Kommandozeile.returnBookCopy("2342342342111"));
    }
}
