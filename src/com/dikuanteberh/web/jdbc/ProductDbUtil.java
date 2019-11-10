package com.dikuanteberh.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ProductDbUtil {

private DataSource dataSource;
	
	public ProductDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Product> getProducts(String cat, String keyword) throws Exception{
		List<Product> Products = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		PreparedStatement myStmt2 = null;
		ResultSet myRs = null;
		String sql ="";
		
		try {
			//get a connection
			myConn = dataSource.getConnection();
			
			//create sql statement
			if(cat == null || cat.contentEquals("All Products") || cat.equals("")) {
				sql  = "select * from Product order by name";
				myStmt = myConn.createStatement();
				//execute query
				myRs = myStmt.executeQuery(sql);
			}else {
				sql  = "select * from Product where type=?";
				myStmt2 = myConn.prepareStatement(sql);
				
				//set params
				myStmt2.setString(1, cat);
				//execute query
				myRs = myStmt2.executeQuery();
			}
			
			//process result set
			while(myRs.next()) {
				//retrieve data from result set row
				int productKey = myRs.getInt("product_key");
				String name = myRs.getString("name");
				String description = myRs.getString("description");
				String type = myRs.getString("type");
				int quantity = myRs.getInt("quantity");
				float price = myRs.getFloat("price");
				String imagePath = myRs.getString("image-path");
				
				//create new Product object
				Product tempProduct = new Product(productKey, name, description, type, quantity, price, imagePath);
				
				if(keyword == null || keyword.equals("")) {
					//add it to the list of elements
					Products.add(tempProduct);
				}else if(tempProduct.getDescription().contains(keyword) || tempProduct.getName().contains(keyword) || tempProduct.getType().contains(keyword)){
					//add it to the list of elements
					Products.add(tempProduct);
				}
				
			}

			
			return Products;
		}finally {
			//close jdbc objects
			close(myConn, myStmt, myRs);
		}
		
		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if(myRs != null) {
				myRs.close();
			}
			if(myStmt != null) {
				myStmt.close();
			}
			if(myConn != null) {
				myConn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*public void addProduct(Product theProduct) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			//get a connection
			myConn = dataSource.getConnection();
			
			//create sql for insert
			String sql = "insert into Product " +
						"(first_name, last_name, email) " +
						"value(?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			//set the param values for the Product
			myStmt.setString(1, theProduct.getFirstName());
			myStmt.setString(2, theProduct.getLastName());
			myStmt.setString(3, theProduct.getEmail());
			
			//execute sql insert
			myStmt.execute();
		}finally{
		
			//clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}*/

	public Product getProduct(String theProductKey) throws Exception{
		Product theProduct = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int productKey;
		
		try {
			//convert id to int
			productKey = Integer.parseInt(theProductKey);
			
			//get a connection
			myConn = dataSource.getConnection();
			
			//create sql statement
			String sql  = "select * from Product where product_key=?";
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, productKey);
			
			//execute query
			myRs = myStmt.executeQuery();
			
			//retrieve data from result set row
			if(myRs.next()) {
				//retrieve data from result set row
				
				String name = myRs.getString("name");
				String description = myRs.getString("description");
				String type = myRs.getString("type");
				int quantity = myRs.getInt("quantity");
				float price = myRs.getFloat("price");
				String imagePath = myRs.getString("image-path");
				
				//create new Product object
				theProduct = new Product(productKey, name, description, type, quantity, price, imagePath);
				
			}else {
				throw new Exception("Could not find Product Key: " + productKey);
			}
			
			return theProduct;
		}finally {
			//close jdbc objects
			close(myConn, myStmt, myRs);
		}
		
		
	}

	/* public void updateProduct(Product theProduct) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			//get db connection
			myConn = dataSource.getConnection();
			
			//create sql update statement
			String sql  = "update Product set first_name=?, last_name=?, email=? " 
					+ "where id=?";
			
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setString(1, theProduct.getFirstName());
			myStmt.setString(2, theProduct.getLastName());
			myStmt.setString(3, theProduct.getEmail());
			myStmt.setInt(4, theProduct.getId());
			
			//execute sql statement
			myStmt.executeUpdate();
			
		}finally{
			//close jdbc objects
			close(myConn, myStmt, null);
		}
	}*/

	public void deleteProduct(String theProductId) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			//convert id to int
			int ProductId = Integer.parseInt(theProductId);
			
			//get db connection
			myConn = dataSource.getConnection();
			
			//create sql update statement
			String sql  = "delete from Product where product_key=?";
			
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, ProductId);
			
			//execute sql statement
			myStmt.execute();
			
		}finally{
			//close jdbc objects
			close(myConn, myStmt, null);
		}
		
	}
}
