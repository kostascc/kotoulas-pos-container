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
package com.zazu.models;

import com.google.gson.annotations.*;

public class CreditOffer {
	
	@Expose(serialize=false)
	public int credit_offer_id;
	
	@Expose
	public float credits_per_unit;
	
	@Expose
	public boolean is_enabled;
	
	@Expose
	public int good_id;
	
	
	public String getApiName() { return "zazu_credit_offer"; }
	
	public String getFields() {return "*"; }
	
	public int getId() { return this.credit_offer_id; }
	
	public void setId(int id) { this.credit_offer_id = id; }
	
	public String getIdName() { return "credit_offer_id"; }
	
}
