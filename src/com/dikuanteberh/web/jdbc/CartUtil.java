package com.dikuanteberh.web.jdbc;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class CartUtil {
	
	private Set<Product> cartItems;
	private Hashtable<Integer, Integer> itemCount;
	private double subTotal;
	private double total;
	private double VAT;

	public CartUtil() {
		this.cartItems = new HashSet<>();
		this.itemCount = new Hashtable<>();
		subTotal = 0.0;
		total = 0.0;
		VAT = 0.085;
	}
	
	private void calculateTotal() {
		subTotal = 0.0;
		for(Product p: cartItems){
			subTotal += p.getPrice() * itemCount.get(p.getProductKey());
		}
		
		total = subTotal + subTotal * VAT;
	}
	
	public void updateItemCount(int productKey, int newCount) {
		
		if(itemCount.containsKey(productKey)) {
			itemCount.replace(productKey, newCount);
			calculateTotal();
		}
	}
	
	public void removeFromCart(Product product) {
		cartItems.remove(product);
		itemCount.remove(product.getProductKey());
		calculateTotal();
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
		
		calculateTotal();
	}
	
	public Set<Product> getCartItems(){
		
		return cartItems;
		
	}
	
	public Hashtable<Integer, Integer> getItemCount(){
		return itemCount;
		
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getVAT() {
		return VAT;
	}

	public void setVAT(double vAT) {
		VAT = vAT;
	}

}
