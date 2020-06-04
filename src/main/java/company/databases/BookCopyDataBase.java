package company.databases;


import company.objects.Book;
import company.objects.BookCopy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.Iterator;

/**
 * Represents the Book Copy DataBase
 * <p>
 * All BookCopies are stored here. After creating the the database, it has to be linked to the bookdatabase
 */

public class BookCopyDataBase {
    private final ArrayList<BookCopy> bookCopyDataBase = new ArrayList<BookCopy>();
    private ArrayList<Book> bookDataBase;


    public void importBookCopies(final String csvFilePath) {
        /*
         * man geht davon aus, dass in der csv Datei die folgende Reihenfolge besteht ;
         * book, id , location
         * d.h die Attribute werden durch , getrennt.
         */
        try (BufferedReader csvFileReader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = csvFileReader.readLine()) != null) {
                final String[] bookCopyAttributes = line.split(",");
                Book book = null;
                // the appropriate book will be through its title searched .
                for (final Book bookToIterate : bookDataBase) {
                    if (bookToIterate.getTitle().equals(bookCopyAttributes[0])) {
                        book = bookToIterate;
                    }
                }
                // if the original copy does not exist, the book copy must not be added.
                if (book != null) {
                    final BookCopy bookCopy = new BookCopy(book, bookCopyAttributes[1], bookCopyAttributes[2], Calendar.getInstance().getTime());
                    this.addBookCopy(bookCopy);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * HILFSMETHODE ZUM TESTEN: Adds Book to the DataBase
     * <p>
     * precondition: bookDataBase != null
     * postcondition: bookCopy is added to the dataBase
     *
     * @param bookCopy
     * @return
     */
    public boolean addBookCopy(final BookCopy bookCopy) {
        if (bookDataBase.contains(bookCopy.getBook())) {
            if (bookCopyDataBase.stream().filter(bookCopy1 -> bookCopy1.getId().equals(bookCopy.getId())).collect(Collectors.toList()).size() == 0) {
                bookCopyDataBase.add(bookCopy);
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes the bookcopy from the database
     * <p>
     * precondition: bookCopyDataBase.contains(bookCopy)
     * postcondition: the book copy was deleted from the database
     *
     * @param bookCopy
     * @return
     */
    public boolean deleteBookCopy(BookCopy bookCopy) {
        if (bookCopyDataBase.contains(bookCopy)) {
            if (!bookCopyIsBorrowed(bookCopy)) {
                bookCopyDataBase.remove(bookCopy);
                return true;
            }

        }
        return false;

    }

    /**
     * Checks if Book Copy is borrowed
     * <p>
     * precondition: bookCopy != null
     * postcondition: loan status is returned
     *
     * @param bookCopy
     * @return
     */
    public boolean bookCopyIsBorrowed(BookCopy bookCopy) {
        return bookCopy.getLoanStatus();
    }

    /**
     * Returns the linked book data base
     * <p>
     * precondition: bookDataBase != null
     * postcondition: bookDataBase is returned
     *
     * @return
     */
    public ArrayList<Book> getBookDataBase() {
        return bookDataBase;
    }

    /**
     * Sets the book data base
     * <p>
     * precondition: bookDataBase != null
     * postcondition: this.bookDataBase = bookDataBase
     *
     * @param bookDataBase
     */
    public void setBookDataBase(ArrayList<Book> bookDataBase) {
        this.bookDataBase = bookDataBase;
    }

    /**
     * Returns the book copy database
     * <p>
     * precondition: True
     * postcondition: bookCopyDataBase is returned
     *
     * @return
     */
    public ArrayList<BookCopy> getBookCopyDataBase() {
        return bookCopyDataBase;
    }
}
