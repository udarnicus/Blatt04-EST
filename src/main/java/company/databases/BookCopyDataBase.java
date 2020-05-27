package company.databases;


import company.objects.Book;
import company.objects.BookCopy;

import java.util.ArrayList;

/**
 * Reprents the Book Copy DataBase
 *
 * All BookCopys are stored here. After creating the the database, it has to be linked to the bookdatabase
 *
 */

public class BookCopyDataBase {
    private final ArrayList<BookCopy> bookCopyDataBase = new ArrayList<BookCopy>();
    private ArrayList<Book> bookDataBase;


    /**
     * HILFSMETHODE ZUM TESTEN: Adds Book to the DataBase
     *
     * precondition: bookDataBase != null
     * postcondition: bookCopy is added to the dataBase
     *
     * @param bookCopy
     * @return
     */
    public boolean addBookCopy(BookCopy bookCopy){
        if(bookDataBase.contains(bookCopy.getBook())){
            if(!bookCopyDataBase.contains(bookCopy)){
                bookCopyDataBase.add(bookCopy);
                return true;
            }

        }
        return false;
    }

    /**
     * Deletes the bookcopy from the database
     *
     * precondition: bookCopyDataBase.contains(bookCopy)
     * postcondition: the book copy was deleted from the database
     *
     * @param bookCopy
     * @return
     */
    public boolean deleteBookCopy(BookCopy bookCopy){
      if(bookCopyDataBase.contains(bookCopy)){
          if(!bookCopyIsBorrowed(bookCopy)){
              bookCopyDataBase.remove(bookCopy);
              return true;
          }

      }
      return false;

    }

    /**
     * Checks if Book Copy is borrowed
     *
     * precondition: bookCopy != null
     * postcondition: loan status is returned
     *
     *
     * @param bookCopy
     * @return
     */
    public boolean bookCopyIsBorrowed(BookCopy bookCopy){
        return bookCopy.getLoanStatus();
    }

    /**
     * Returns the linked book data base
     *
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
     *
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
     *
     * precondition: True
     * postcondition: bookCopyDataBase is returned
     *
     * @return
     */
    public ArrayList<BookCopy> getBookCopyDataBase() {
        return bookCopyDataBase;
    }
}
