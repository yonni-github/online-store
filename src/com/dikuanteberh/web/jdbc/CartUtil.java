package com.dikuanteberh.web.jdbc;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class CartUtil {
	
	private Set<Product> cartItems;
	private Hashtable<Integer, Integer> itemCount;

	public CartUtil() {
		this.cartItems = new HashSet<>();
		this.itemCount = new Hashtable<>();
	}
	
	public void addToCart(Product product) {
		cartItems.add(product);
		
		if(itemCount.containsKey(product.getProductKey())) {
			int oldCount = itemCount.get(product.getProductKey());
			itemCount.replace(product.getProductKey(), oldCount + 1);
		}
		else {
			itemCount.put(product.getProductKey(), 1);
		}
	}
	
	public Set<Product> getCartItems(){
		
		return cartItems;
		
	}
	
	public Hashtable<Integer, Integer> getItemCount(){
		return itemCount;
		
	}

}
