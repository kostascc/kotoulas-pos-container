package com.zazu.models;

import com.google.gson.annotations.*;


public class Store extends Model {

	@Expose(serialize = false)
	public int store_id;
	
	@Expose
	public String code;
	
	@Expose
	public String name;
	
	@Expose
	public boolean is_enabled;
	
	@Expose
	public boolean is_deleted;
	
	
	
	public String getApiName() { return "zazu_store"; }
	
	public String getFields() {return "*"; }
	
	public int getId() { return this.store_id; }
	
	public void setId(int id) { this.store_id = id; }
	
	public String getIdName() { return "store_id"; }
	
}
