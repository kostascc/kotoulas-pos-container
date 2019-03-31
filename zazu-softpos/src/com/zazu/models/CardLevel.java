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


public class CardLevel extends Model {
	
	@Expose(serialize = false)
	public int card_level_id;
	
	@Expose
	public String title;
	
	@Expose
	public boolean is_enabled;
	
	@Expose
	public boolean is_deleted;
	
	@Expose
	public int position;
	
	
	public String getApiName() { return "zazu_card_level"; }
	
	public String getFields() {return "card_level_id,title,is_enabled,is_deleted,position"; }
	
	public int getId() { return this.card_level_id; }
	
	public void setId(int id) { this.card_level_id = id; }
	
	public String getIdName() { return "card_level_id"; }
	
}
