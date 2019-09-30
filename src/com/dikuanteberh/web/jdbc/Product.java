package com.dikuanteberh.web.jdbc;

public class Product {
	private int productKey;
	private String name;
	private String description;
	private String type;
	private int quantity;
	private float price;
	private String imagePath;
	
	public Product(int productKey, String name, String description, String type, int quantity, float price, String imagePath) {
		
		this.productKey = productKey;
		this.name = name;
		this.description = description;
		this.type = type;
		this.quantity = quantity;
		this.price = price;
		this.imagePath = imagePath;
	}

	public int getProductKey() {
		return productKey;
	}

	public void setProductKey(int productKey) {
		this.productKey = productKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productKey;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productKey != other.productKey)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [productKey=" + productKey + ", name=" + name + ", description=" + description + ", type="
				+ type + ", quantity=" + quantity + ", price=" + price + "]";
	}
	
	
}
