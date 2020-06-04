package company.objects;

import java.util.ArrayList;


/**
 * Represents a customer
 *
 */
public class Customer {
    private final String firstName;
    private final String lastName;
    private final String clientId;
    private final String address;
    private final String zipCode;
    private final String city;
    private boolean paymentStatus;
    private boolean overdraftFeeStatus;
    private ArrayList<BookCopy> booksOnLoan = new ArrayList<>();

    public Customer(final String firstName, final String lastName, final String clientId, final String address, final String zipCode, final String city){
        this.firstName = firstName;
        this.lastName = lastName;
        this.clientId = clientId;
        setPaymentStatus(true);
        setOverdraftFeeStatus(false);
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getClientId() {
        return clientId;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public boolean getPaymentStatus() {
        return paymentStatus;
    }

    public ArrayList<BookCopy> getBooksOnLoan() {
        return booksOnLoan;
    }

    public void setBooksOnLoan(ArrayList<BookCopy> booksOnLoan) {
        this.booksOnLoan = booksOnLoan;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean hasOverdraftFeeStatus() {
        return overdraftFeeStatus;
    }

    public void setOverdraftFeeStatus(boolean overdraftFeeStatus) {
        this.overdraftFeeStatus = overdraftFeeStatus;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", clientId='" + clientId + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", paymentStatus=" + paymentStatus +
                ", overdrafFeeStatus=" + overdraftFeeStatus +
                ", booksOnLoan=" + booksOnLoan +
                '}';
    }
}
