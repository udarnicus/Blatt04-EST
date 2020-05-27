package company.controller;

import company.databases.BookCopyDataBase;
import company.databases.BookDataBase;
import company.databases.CustomerDataBase;
import company.objects.Book;
import company.objects.BookCopy;
import company.objects.Customer;

import java.util.Scanner;

public class Kommandozeile {
	private static BookCopyDataBase bookCopyDataBase;
	private static BookDataBase bookDataBase;
	private static CustomerDataBase customerDataBase;

	public static void main(String[] args) {
		initialize();
		scanForInput();
	}
	/**
	 * Scans and verifies user input
	 * 
	 */

	// test

	// test 2

	private static void scanForInput() {
		String userInput= "";
		boolean userInputOK = false;
		
		
		while(!userInputOK) {
		userInput = getUserInput();
		
	    try {
	       Integer.parseInt(userInput);
	       if(Integer.parseInt(userInput) <15 && Integer.parseInt(userInput) > 0) {
	    	   userInputOK = true;
	       }else {
	    	   System.out.println("Wrong input, please insert number from 1-14");
	       }
	    } catch (NumberFormatException nfe) {
	        System.out.println("Wrong input, please insert number from 1-14");
	  
	    }
		}
	    
	    
	    switch(Integer.parseInt(userInput)){
	        case 1:
	            System.out.println("Action executed succesfully!");
	            break;
	        case 2:
	            System.out.println("Action executed succesfully!");
	            break;
	        case 3:
	        	System.out.println("Action executed succesfully!");
	            break;
	        case 4:
	            System.out.println("Action executed succesfully!");
	            
	            break;
	        case 5:
	            readClientID();
	            readBookISBN();
	            System.out.println("Book borrowed succesfully!");
	            break;
	        case 6:
	            System.out.println("Action executed succesfully!");
	            break;
	        case 7:
	        	String customerID = readClientID();
				for(Customer customer: customerDataBase.getCustomerDataBase()){
					if (customer.getClientId().equals(customerID)){
						if(customerDataBase.deleteCustomer(customer)){
							System.out.println("Customer deleted succesfully!");
							break;
						}else{
							System.out.println("Customer could not be deleted!");
							break;
						}
					}

				}
				System.out.println("Customer could not be found");
	            break;
	        case 8:
				String bookISBN = readBookISBN();
				for(Book book: bookDataBase.getBookDataBase()){
					if (book.getIsbn().equals(bookISBN)){
						if(bookDataBase.deleteBook(book)){
							System.out.println("Book deleted succesfully!");
							break;
						}else{
							System.out.println("Book could not be deleted!");
							break;
						}
					}

				}
				System.out.println("Book could not be found");
				break;
	        case 9:
				String bookCopyID = readBookCopyID();
				for(BookCopy bookCopy: bookCopyDataBase.getBookCopyDataBase()){
					if (bookCopy.getId().equals(bookCopyID)){
						if(bookCopyDataBase.deleteBookCopy(bookCopy)){
							System.out.println("Book copy deleted succesfully!");
							break;
						}else{
							System.out.println("Book copy could not be deleted!");
							break;
						}
					}

				}
				System.out.println("Book copy could not be found");
				break;
	        case 10:
	            System.out.println("Action executed succesfully!");
	            break;
	        case 11:
	            System.out.println("Action executed succesfully!");
	            break;
	        case 12:
	            System.out.println("Action executed succesfully!");
	            break;
	        case 13:
	            System.out.println("Action executed succesfully!");
	            break;
	        case 14:
	            System.out.println("Action executed succesfully!");
	            break;
	        

	    }
	}
	
	/**
	 * Reads Client ID
	 * 
	 */
	private static String readClientID() {
		System.out.println("Please insert the customer ID:");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	/**
	 * Reads Book ISBN
	 */
	private static String readBookISBN() {
		System.out.println("Please insert the book ID:");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	/**
	 * Reads Book Copy ID
	 */
	private static String readBookCopyID() {
		System.out.println("Please insert the book copy ID:");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	/**
	 * Prints menu and returns user input
	 * 
	 * @return
	 */
	private static String getUserInput() {
		Scanner scanner = new Scanner(System.in);


		showFunctions();
	    
	    
	    System.out.println("");
	    System.out.println("Please choose action:");
	    String userInput = scanner.nextLine();
		return userInput;
	}

	/**
	 * Shows the menu
	 */
	private static void showFunctions() {
		System.out.println("1. Import CSV File with books ");
		System.out.println("2. Import CSV File with customers");
		System.out.println("3. Import CSV File with book copies");
		System.out.println("4. Search book copy");
		System.out.println("5. Borrow book copy");
		System.out.println("6. Return book copy");
		System.out.println("7. Delete customer   (Implemented)");
	    System.out.println("8. Delete book   (Implemented)");
	    System.out.println("9. Delete book copy   (Implemented)");
	    System.out.println("10. Print all customers");
	    System.out.println("11. Print all books");
	    System.out.println("12. Print all not borrowed book copies");
	    System.out.println("13. Print all borrowed book copies");
	    System.out.println("14. Print all borrowed book copies of customer");
	}

	private static void initialize(){
		customerDataBase = new CustomerDataBase();
		bookDataBase = new BookDataBase();
		bookCopyDataBase = new BookCopyDataBase();

		bookDataBase.setBookCopyDataBase(bookCopyDataBase.getBookCopyDataBase());
		bookCopyDataBase.setBookDataBase(bookDataBase.getBookDataBase());
	}

	public static BookCopyDataBase getBookCopyDataBase() {
		return bookCopyDataBase;
	}

	public static BookDataBase getBookDataBase() {
		return bookDataBase;
	}

	public static CustomerDataBase getCustomerDataBase() {
		return customerDataBase;
	}

}
