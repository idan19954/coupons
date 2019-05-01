package dao;

import java.util.ArrayList;


import lib.model.Customer;

public interface CustomerDao {
	ArrayList<Customer> customers  = new ArrayList<Customer>();

	/*
	 * method that create a customer .
	 */
	void createCustomer();
	/*
	 * method that remove a customer .
	 */
	void removeCustomer();
	/*
	 * method that update details about the customer .
	 */
	void updateCustomer();
	/*
	 * method that get info about the customer .
	 */
	String getCustomer();
	
	/*
	 * method that get a collection of Customer .
	 */
	ArrayList<Customer> getAllCustomers();


}  
