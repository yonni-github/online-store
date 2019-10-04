package com.dikuanteberh.web.jdbc;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDbUtil productDbUtil;
	private CartUtil cartUtil;
	
	//Define dataSource/connection pool for resource injection
	@Resource(name="jdbc/online_store")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();
		
		//create our Product db util and pass conn pool/ datasource
		try {
			productDbUtil = new ProductDbUtil(dataSource);
			cartUtil = new CartUtil();
		}catch(Exception exc) {
			throw new ServletException(exc);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//read the COMMAND param
			String theCommand = request.getParameter("command");
			
			//if command is missing set it to default
			if(theCommand == null) {
				theCommand = "LIST";
			}
			//route to appropriate method
			switch(theCommand) {
				case "LIST":
					//list the Products in mvc fashion
					listProducts(request, response);
					break;
					
				case "ADD":
					//Add item to cart
					addToCart(request, response);
					break;
				
				case "CART":
					//Add item to cart
					goToCart(request, response);
					break;	
					
				case "UPDATE":
					//update item count in shopping cart
					updateItemCount(request, response);
					break;
					
				case "REMOVE":
					//update item count in shopping cart
					removeItemFromCart(request, response);
					break;	
					
				case "LOAD":
					//load product detail page
					loadProduct(request, response);
					break;	
				
				default:
					//list the Products in mvc fashion
					listProducts(request, response);
			
			}
			
		}catch(Exception e) {
			throw new ServletException(e);		
		}
	}
	
	private void removeItemFromCart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//read Product key from form data
		String theProductKey = request.getParameter("productKey");
				
		//get Product from db
		Product theProduct = productDbUtil.getProduct(theProductKey);
		
		cartUtil.removeFromCart(theProduct);
		goToCart(request, response);
		
	}

	private void updateItemCount(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//read Product key from form data
		int productKey = Integer.parseInt(request.getParameter("productKey"));
		
		//read new quantity from form data
		int newQuantity = Integer.parseInt(request.getParameter("quantity"));
		
		cartUtil.updateItemCount(productKey, newQuantity);
		goToCart(request, response);
		
	}

	private void goToCart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("going to cart");	
		// get cart details from cart util
		Set<Product> products = cartUtil.getCartItems();
		Hashtable <Integer, Integer> itemCount = cartUtil.getItemCount();
		double subtotal = cartUtil.getSubTotal();
		double total = cartUtil.getTotal();
		double VAT = cartUtil.getVAT();
		
		//add products to the request 
		request.setAttribute("PRODUCT_LIST", products);
		//add item count data to request
		request.setAttribute("ITEM_COUNT", itemCount);
		
		request.setAttribute("SUBTOTAL", subtotal);
		request.setAttribute("TOTAL", total);
		request.setAttribute("VAT", VAT);
		
		//send to jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cart-list.jsp");
		dispatcher.forward(request, response);
	}

	private void addToCart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("Item Added to cart");
		//read Product id from form data
		String theProductKey = request.getParameter("productKey");
		
		//get Product from db
		Product theProduct = productDbUtil.getProduct(theProductKey);
		
		//add product to cart
		cartUtil.addToCart(theProduct);
		
		listProducts(request, response);
		
	}

	private void listProducts(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// get Products from db util
		List<Product> products = productDbUtil.getProducts();
		
		//add products to the request 
		request.setAttribute("PRODUCT_LIST", products);
		
		//send to jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-products.jsp");
		dispatcher.forward(request, response);
	}
	
	private void loadProduct(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//read Product id from form data
		String theProductKey = request.getParameter("productKey");
		
		//get Product from db
		Product theProduct = productDbUtil.getProduct(theProductKey);
		
		//place Product in the request attribute
		request.setAttribute("THE_PRODUCT", theProduct);
		
		//send to jsp page : product-detail-page.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/product-detail-page.jsp");
		dispatcher.forward(request, response);
	}

}
