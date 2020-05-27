package company.objects;

import java.util.Date;

/**
 * Represents a book copy
 *
 */
public class BookCopy{
    private boolean loanStatus;
    private Customer currentBorrower;
    private Date loanDate;
    private final Date addedToLibrary;
    private final Book book;
    private final String id;
    private final String location;


    public BookCopy(final Book book , final String id, final String location, final Date addedToLibrary) {

        this.setLoanStatus(false);
        this.addedToLibrary = addedToLibrary;
        this.book = book;
        this.id = id;
        this.location = location;
    }

    public boolean getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(boolean loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Customer getCurrentBorrower() {
        return currentBorrower;
    }

    public void setCurrentBorrower(Customer currentBorrower) {
        this.currentBorrower = currentBorrower;
        setLoanStatus(true);
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getAddedToLibrary() {
        return addedToLibrary;
    }

    public Book getBook() {
        return book;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }
}
