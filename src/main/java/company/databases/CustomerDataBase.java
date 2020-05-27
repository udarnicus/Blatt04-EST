package company.databases;



import company.objects.Customer;

import java.util.ArrayList;

/**
 * Represents the customer database
 *
 */
public class CustomerDataBase {
    private final ArrayList<Customer> customerDataBase = new ArrayList<Customer>();


    /**
     * HILFSMETHODE ZUM TESTEN: Adds customer to the database
     *
     * precondition: customer != null && customer is not already in the database
     * postcondition: customer was added to the database
     *
     * @param customer
     * @return
     */
    public boolean addCustomer(Customer customer){
        if (customer == null || customerDataBase.contains(customer)) {
            return false;
        }
        customerDataBase.add(customer);
        return true;
    }

    /**
     * Deletes Customer from the DataBase
     *
     * precondtion: customer returned all books, paid all the fees and the list contains the customer
     * postcondition: customer is no longer in the database
     *
     * @param customer
     * @return
     */
    public boolean deleteCustomer(Customer customer){
        if(!customerDataBase.contains(customer)||!allBooksReturned(customer)||!overdrafFeePayed(customer)){
            return false;
        }
        return customerDataBase.remove(customer);
    }

    /**
     * Checks if the custmer returned all his book copies
     *
     * precondition: customer != null
     * postcondition: returns true if customer is not currently borrowing any books
     *
     * @param customer
     * @return
     */
    private boolean allBooksReturned(final Customer customer){
        return customer.getBooksOnLoan().size() == 0;
    }

    /**
     * Checks if customer payed the fees
     *
     * precondtion: customer != null
     * postcondition: returns true if all the fees were payed
     *
     * @param customer
     * @return
     */
    private boolean overdrafFeePayed(final Customer customer){
        return !customer.hasOverdrafFeeStatus();
    }

    /**
     * Returns Customer database
     *
     * precondition: true
     * postcondition: customerDataBase was returned
     *
     * @return
     */
    public ArrayList<Customer> getCustomerDataBase() {
        return customerDataBase;
    }
}

