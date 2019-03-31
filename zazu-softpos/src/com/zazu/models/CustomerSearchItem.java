package com.zazu.models;

/**
 * Warning: This is a transitional object,
 * NOT an API model.
 */
public class CustomerSearchItem {

	public Customer customer;
		
	public CustomerSearchItem(Customer customer){ this.customer = customer; }
	
	public String toString() {
		return ( customer.first_name + " " + customer.last_name + " (" + customer.card + ")" );
	}
	
}
