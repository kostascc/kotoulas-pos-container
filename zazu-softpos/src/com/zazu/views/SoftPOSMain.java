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

package com.zazu.views;

import com.zazu.utils.POSUpdater;



public class SoftPOSMain {

	public static void main(String[] args) {
		
		/*System.out.println("Arguments Input: " + args.length);
		System.out.println("Arguments Null: " + args == null);
		System.out.println("0 Argument Null: " + args[0]==null);*/
		
		
		//if ( args.length == 1 ) {
			// Clean Last Update
			POSUpdater.clean();
			
			// Show Login View
			LoginView loginView = new LoginView();
			loginView.setVisible(true);
		/*} else {
			UpdatingView updatingView = new UpdatingView();
			updatingView.setVisible(true);
			
			// Run Update
			POSUpdater.update();
		}*/
		
		
	}

}
