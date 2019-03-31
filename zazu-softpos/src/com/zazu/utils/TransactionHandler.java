/*******************************************************************************
 * Copyright (C) 2018 Konstantinos Chatzis - All Rights Reserved
 * 
 * Licensed Under:
 * Creative Commons Attribution-NoDerivatives 4.0 International Public License
 *  
 * You must give appropriate credit, provide a link to the license, and indicate 
 * if changes were made. You may do so in any reasonable manner, but not in 
 * any way that suggests the licensor endorses you or your use. If you remix, 
 * transform, or build upon the material, you may not distribute the modified material. 
 * 
 * Konstantinos Chatzis <kachatzis@ece.auth.gr>
 ******************************************************************************/

package com.zazu.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.zazu.models.*;
import com.zazu.utils.Api.GetClient;
import com.zazu.utils.Api.PostClient;

/**
 * Handles a new Transaction, given the 
 * products bought, the customer, the device
 * and the store.
 */
public class TransactionHandler {
	
	private Transaction transaction;
	private List<TransactionGood> transactionGoods;
	
	private Manager manager;
	private Device device;
	private Store store;
	private Customer customer;
	private List<CartProduct> cartList;
	private boolean result;

	public TransactionHandler() {
		this.transaction = new Transaction();
		this.transactionGoods = new ArrayList<TransactionGood>();
		
		this.result = false;
		this.manager = new Manager();
		this.device = new Device();
		this.customer = new Customer();
		this.store = new Store();
		this.cartList = new ArrayList<CartProduct>();
	}
	
	public TransactionHandler setDevice(Device device) { this.device = device; return this; }
	
	public TransactionHandler setStore(Store store) { this.store = store; return this; }
	
	public TransactionHandler setManager(Manager manager) { this.manager = manager; return this; }
	
	public TransactionHandler setCustomer(Customer customer) { this.customer = customer; return this; }
	
	public TransactionHandler setCartList(List<CartProduct> cartList) { this.cartList = cartList; return this; }
	
	public boolean getResult() { return this.result; }
	
	/* TODO: Calculate Credits from Credit Offers,
	 * probably will happen in jCore for non-realtime response */
	
	/**
	 * Handle Transaction
	 */
	public void handle() {
		
		setTransactionManager();
		setTransactionDevice();
		setTransactionCustomer();
		setTransactionStore();
		setRequestDateTime();
		setMoneySpent();
		setCredit();
		setProcessed();
		
		if( checkTransactionValidity() ) {
		
			executeTransaction();
			
			if ( this.transaction.transaction_id>0 ) {
				//updateCustomerCredit();
				setTransactionGoods();
				executeTransactionGoods();
				
				this.result = true;
			} else {
				System.out.println("TransactionHandler: Could not retrieve Transaction ID.");
			}
		} else {
			System.out.println("Transaction Not Valid");
		}
	}
	
	/*private void updateCustomerCredit() {
		
	}*/
	
	/**
	 * Checks Transaction validity, based on credits and cart items
	 */
	private boolean checkTransactionValidity() {
		boolean valid = true;
		
		System.out.println(" Check:");
		
		System.out.println("  Customer Credit: " + this.customer.credit);
		System.out.println("  Transaction Credit: " + this.transaction.credit);
		
		// Credit Availability
		if ( this.customer.credit - this.transaction.credit < 0 ) {
			valid = false;
			System.out.println("TransactionHandler: Customer has not enough credits"
					+ " (Needed:"  + this.transaction.credit + ", Available:" + this.customer.credit + ".");
		}
		
		System.out.println("  Cart Size: " + this.cartList.size());
		
		// Cart Size
		if ( this.cartList.size() < 0 ) {
			valid = false;
			System.out.println("TransactionHandler: Cart is empty.");
		}
		
		// Cart Objects (can't be only gifts)
		int productsNotGifts = 0;
		for ( CartProduct cartProduct : this.cartList ) {
			if ( !cartProduct.good.is_gift )
				productsNotGifts++;
		}
		if ( productsNotGifts < 0 ) {
			valid = false;
			System.out.println("TransactionHandler: Negative Products in cart.");
		}
		
		return valid;
	}
	
	private void setProcessed() {
		this.transaction.is_rolled_back = false;
		this.transaction.is_processed = false;
		this.transaction.is_canceled = false;
	}
	
	private void executeTransaction() {
		PostClient postClient = new PostClient();
		postClient.setObject( this.transaction );
		postClient.execute();
		
		List<Transaction> tmpTransactionResponse = (List) postClient.getObject();
		if(!tmpTransactionResponse.isEmpty() && postClient.getResponseCode()==200) {
			// POST Successful, set transaction id
			for ( Transaction tmpTransaction : tmpTransactionResponse ) {
				if (tmpTransaction.transaction_id > 0) {
					this.transaction.transaction_id = tmpTransaction.transaction_id;
					System.out.println("TransactionHandler: Transaction ID: " + this.transaction.transaction_id);
				} else {
					System.out.println("TransactionHandler: No valid Transaction ID retrieved, though POST was successful.");
				}
			}
		} else {
			System.out.println("TransactionHandler: Could not POST Transaction.");
		}
		
	}
	
	private void executeTransactionGoods() {
		// POST for each Transaction Good separately
		for (TransactionGood transactionGood : transactionGoods) {
			
			PostClient postClient = new PostClient();
			postClient.setObject( transactionGood );
			postClient.execute();
			
			List<TransactionGood> tmpTransactionGoodResponse = (List) postClient.getObject();
			if(!tmpTransactionGoodResponse.isEmpty() && postClient.getResponseCode()==200) {
				// POST Successful, set Transaction Good id
				for ( TransactionGood tmpTransactionGood : tmpTransactionGoodResponse ) {
					if (tmpTransactionGood.good_bought_id > 0) {
						transactionGood.good_bought_id = tmpTransactionGood.good_bought_id;
					} else {
						System.out.println("TransactionHandler: No valid Transaction Good ID retrieved, though POST was successful.");
					}
				}
			} else {
				System.out.println("TransactionHandler: Could not POST Transaction Good.");
			}
			
		}
	}
	
	private void setTransactionDevice() {
		this.transaction.device_id = this.device.device_id;
	}
	
	private void setTransactionStore() {
		this.transaction.store_id = this.store.store_id;
	}
	
	private void setTransactionManager() {
		this.transaction.manager_id = this.manager.manager_id;
	}
	
	private void setTransactionCustomer() {
		this.transaction.customer_id = this.customer.customer_id;
	}
	
	private void setMoneySpent() {
		float moneySpent = (float) 0.0;
		
		for ( CartProduct cartProduct : cartList ) {
			if ( cartProduct.good.is_gift == false && cartProduct.quantity > 0.0 ) {
				moneySpent += cartProduct.quantity;
			}
		}
		
		this.transaction.money_spent = moneySpent;
	}
	
	private void setCredit() {
		int credit = 0;
		
		for ( CartProduct cartProduct : cartList ) {
			if ( cartProduct.good.is_gift ) {
				credit += cartProduct.good.credits_cost;
			}
		}
		
		this.transaction.previous_card_credit = customer.credit;
		this.transaction.credit = credit;
	}
	
	private void setRequestDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		this.transaction.request_date = dateFormat.format(date);
		
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date time = new Date();
		System.out.println(dateFormat.format(time));
		this.transaction.request_time = timeFormat.format(time);
	}
	
	private void setTransactionGoods() {
		for ( CartProduct cartProduct : cartList) {
			setTransactionGood( cartProduct );
		}
	}
	
	private void setTransactionGood( CartProduct cartProduct ) {
		TransactionGood good = new TransactionGood();
		good.customer_id = this.customer.customer_id;
		good.good_id = cartProduct.good.good_id;
		if (cartProduct.good.is_gift) {
			good.cost = (float) 0.0;
		} else {
			good.cost = cartProduct.quantity;
		}
		good.is_canceled = false;
		good.transaction_id = this.transaction.transaction_id;
		good.is_canceled = false;
		good.is_processed = false;
		transactionGoods.add( good );
	}
	
	
		
}
