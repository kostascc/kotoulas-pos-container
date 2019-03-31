package com.zazu.models;

/**
 * Warning: This is a transitional object,
 * NOT an API model.
 */
public class CartProduct {

	public Good good;
	public float quantity;
		
	public CartProduct(Good good, float quantity){ this.good = good; this.quantity = quantity; }
	
	public String toString() {
		return quantity + good.unit + " x " + good.title;
	}
	
}
