package com.dikuanteberh.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//Define database/connection pool for resource injection
	@Resource(name="jdbc/online_store")
	private DataSource dataSource;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set up print writer
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		//set the database connection
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			//create sql statements
			String sql = "select * from product";
			myStmt = myConn.createStatement();
			
			//execute sql query
			myRs = myStmt.executeQuery(sql);
			
			//process the result
			while(myRs.next()) {
				String name = myRs.getString("name");
				out.println(name);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
