package company.databases;


import company.objects.Book;
import company.objects.BookCopy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Iterator;

/**
 * Represents the Book data base
 * <p>
 * After creating the database, the bookCopyDataBase attribute has to be linked to the bookCopyDataBase
 * <p>
 * TEST TEST TEST
 */
public class BookDataBase {
    private final ArrayList<Book> bookDataBase = new ArrayList<>();
    private ArrayList<BookCopy> bookCopyDataBase;


    /**
     * The csv file will be read and each line, which contains the attributes of each book, will be parsed and the book
     * will be created. Then it will be added to data base. This act will be done until the csv file has been totally
     * read.
     * All the attributes that occur in a row are stored in a table, then the attributes will be assigned to the book.
     * Then the book will be added to the database.
     * Since there can be more than one author, they are  the last elements of the each line in the CSV file and they
     * will be stored in a list.
     *
     * @param csvFilePath file to be read and from which the attributes of each book will be imported
     */
    public void importBooks(final String csvFilePath) {
        /*
         * man geht davon aus, dass in der csv Datei die folgende Reihenfolge besteht ;
         * title, year, isbn, city, publisher, edition, authors
         * d.h die Attribute werden durch , getrennt.
         */
        try (BufferedReader csvFileReader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = csvFileReader.readLine()) != null) {
                final String[] bookAttributes = line.split(",");
                //starting at position 6 in each line there are arbitrarily many authors, which are stored in a list
                final ArrayList<String> authors = new ArrayList<>();
                for (int i = 6; i < bookAttributes.length; i++) {
                    authors.add(bookAttributes[i]);
                }
                final Book book = new Book(bookAttributes[0], authors, bookAttributes[1], bookAttributes[2],
                        bookAttributes[3], bookAttributes[4], Integer.parseInt(bookAttributes[5]));
                //bookDataBase.add(book);
                this.addBook(book);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * HILFSMETHODE ZUM TESTEN: Adds Book to the Database
     * <p>
     * precondition: book != null && book is not already in the database
     * postcondition: book has been added to the database
     *
     * @param book
     * @return
     */
    public boolean addBook(Book book) {
        if (bookDataBase.stream().filter(book1 -> book1.getIsbn().equals(book.getIsbn())).
                collect(Collectors.toList()).isEmpty()) {
            getBookDataBase().add(book);
            return true;
        }
        return false;

    }

    /**
     * Remove Book from the database
     * <p>
     * precondition: Bookcopys of the book are not borrowed, Book exists in datbase and bookCopyDataBase != null
     * postcondition: bookDataBase.contains(book) == false
     *
     * @param book
     * @return
     */

    public boolean deleteBook(Book book) {
        if (getBookDataBase().contains(book)) {
            if (checkBookCopyNotBorrowed(book)) {
                /*Dies habe ich hinzugefügt, damit alle Buchkopien gelöscht werden, nachdem das Buch gelöscht wurde.
                Mit einer for-Schleife tut es nicht, es wurde immer eine Exception geworfen.
                 */
                bookCopyDataBase = (ArrayList<BookCopy>) bookCopyDataBase.stream().filter(bookCopy -> !bookCopy.getBook().equals(book)).collect(Collectors.toList());
                getBookDataBase().remove(book);
                return true;
            }
        }
        return false;

    }

    /**
     * Checks if all the bookcopy of the type book are not currently borrowed
     * <p>
     * precondition: book != null && bookCopyDataBase != null
     * postcondition: returns true if all bookCopys are not borrowed, otherwise it returns false
     *
     * @param book
     * @return
     */
    private boolean checkBookCopyNotBorrowed(Book book) {
        for (BookCopy bookCopy : bookCopyDataBase) {
            if (book.equals(bookCopy.getBook())) {
                if (bookCopy.getLoanStatus()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the book copy database
     * <p>
     * precondition: bookCopyDataBase != null
     * postcondition: bookCopyDataBase is returned
     *
     * @return
     */
    public ArrayList<BookCopy> getBookCopyDataBase() {
        return bookCopyDataBase;
    }


    /**
     * Sets the attribute bookCopyDataBase
     * <p>
     * precondition: bookCopyDataBase != null
     * postcondition: this.bookCopyDataBase = bookCopyDataBase
     *
     * @param bookCopyDataBase
     */
    public void setBookCopyDataBase(ArrayList<BookCopy> bookCopyDataBase) {
        this.bookCopyDataBase = bookCopyDataBase;
    }

    /**
     * Returns the book database
     * <p>
     * precondition: True
     * postcondition: bookDataBase is returned
     *
     * @return
     */
    public ArrayList<Book> getBookDataBase() {
        return bookDataBase;
    }

}
