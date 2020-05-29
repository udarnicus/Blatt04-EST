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


public class TestImportBookCopy {
    @Test
    public void testReturnOkBookCopy(){
       Kommandozeile.startTestingEnviroment();
       assertEquals(Kommandozeile.getBookCopyDataBase().getBookCopyDataBase().get(0), Kommandozeile.returnBookCopy(
               "111"));
    }

    @Test
    public void testNotExistingBookCopy(){
        Kommandozeile.startTestingEnviroment();
        assertEquals(null,  Kommandozeile.returnBookCopy(
                "23232"));
    }
}
