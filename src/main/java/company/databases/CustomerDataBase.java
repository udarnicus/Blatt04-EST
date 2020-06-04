package company.databases;


import company.objects.Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents the customer database
 */
public class CustomerDataBase {
    private final ArrayList<Customer> customerDataBase = new ArrayList<Customer>();

    public void importCustomers(final String csvFilePath) {
        /*
         * man geht davon aus, dass in der csv Datei die folgende Reihenfolge besteht ;
         * first name, last name, client ID, address , zipCode, city
         * d.h die Attribute werden durch , getrennt.
         */
        try (BufferedReader csvFileReader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = csvFileReader.readLine()) != null) {
                final String[] customerAttributes = line.split(",");
                final Customer customer = new Customer(customerAttributes[0], customerAttributes[1], customerAttributes[2],
                        customerAttributes[3], customerAttributes[4], customerAttributes[5]);
                this.addCustomer(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * HILFSMETHODE ZUM TESTEN: Adds customer to the database
     * <p>
     * precondition: customer != null && customer is not already in the database
     * postcondition: customer was added to the database
     *
     * @param customer
     * @return
     */
    public boolean addCustomer(final Customer customer) {
        if (customerDataBase.stream().filter(customer1 -> customer1.getClientId().equals(customer.getClientId()))
                .collect(Collectors.toList()).isEmpty()) {
            customerDataBase.add(customer);
            return false;
        }
        return false;
    }

    /**
     * Deletes Customer from the DataBase
     * <p>
     * precondtion: customer returned all books, paid all the fees and the list contains the customer
     * postcondition: customer is no longer in the database
     *
     * @param customer
     * @return
     */
    public boolean deleteCustomer(Customer customer) {
        if (!customerDataBase.contains(customer) || !allBooksReturned(customer) || !overdrafFeePayed(customer)) {
            return false;
        }
        return customerDataBase.remove(customer);
    }

    /**
     * Checks if the custmer returned all his book copies
     * <p>
     * precondition: customer != null
     * postcondition: returns true if customer is not currently borrowing any books
     *
     * @param customer
     * @return
     */
    private boolean allBooksReturned(final Customer customer) {
        return customer.getBooksOnLoan().size() == 0;
    }

    /**
     * Checks if customer payed the fees
     * <p>
     * precondtion: customer != null
     * postcondition: returns true if all the fees were payed
     *
     * @param customer
     * @return
     */
    private boolean overdrafFeePayed(final Customer customer) {
        return !customer.hasOverdraftFeeStatus();
    }

    /**
     * Returns Customer database
     * <p>
     * precondition: true
     * postcondition: customerDataBase was returned
     *
     * @return
     */
    public ArrayList<Customer> getCustomerDataBase() {
        return customerDataBase;
    }
}

