package com.zazu.utils;

import com.zazu.models.*;
import com.zazu.views.LoginView;
import com.zazu.views.MainView;

/**
 * Checks for important objects (mostly in the 
 * Main View) and makes sure everything exists and
 * is valid.
 * Also Checks wether the Manager is allowed to
 * be logged in.
 * In the event of an unsuccessful check, it
 * sends the user back to login.
 * 
 * Note: Not all of those functions happen automatically.
 * The correct functions have to be called.
 */
public class ImportantObjectChecker {
	
	private Manager manager;
	private Device device;
	private Store store;
	private Customer customer;
	
	private boolean checkManager;
	private boolean checkDevice;
	private boolean checkStore;
	private boolean checkCustomer;
	
	private boolean onFailSendToLogin;
	
	private boolean response;
	
	private MainView mainView;
	
	
	public ImportantObjectChecker(){
		this.checkManager = false;
		this.checkDevice = false;
		this.checkStore = false;
		this.checkCustomer = false;
		
		this.onFailSendToLogin = false;
		
		this.response = true;
	}
	
	public ImportantObjectChecker check() {
		checkManager();
		checkDevice();
		checkStore();
		checkCustomer();
		
		if (!this.response) {
			System.out.println("ImportantObjectChecker: Response Failed, activating onFail() functions.");
			onFailSendToLogin();
		}
		
		return this;
	}
	
	private void checkManager() {
		if ( this.manager==null || this.manager.manager_id<=0 ) 
			this.response = false;
	}
	
	private void checkDevice() {
		
	}
	
	private void checkStore() {
		
	}
	
	private void checkCustomer() {
		
	}
	
	public ImportantObjectChecker setManager(Manager manager) {
		this.manager = manager;
		this.checkManager = true;
		return this;
	}
	
	private void onFailSendToLogin() {
		if ( onFailSendToLogin ) {
			// Run
			if (mainView!=null) 
				mainView.logout();
		}
	}
	
	public ImportantObjectChecker setMainView(MainView mainView) { this.mainView = mainView; return this; }
	
	public ImportantObjectChecker setOnFailSendToLogin(boolean onFailSendToLogin) { this.onFailSendToLogin = onFailSendToLogin; return this; }
	
}
