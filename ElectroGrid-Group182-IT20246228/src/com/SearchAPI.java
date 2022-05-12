package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchAPI
 */
@WebServlet("/SearchAPI")
public class SearchAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
    	Connection con = null; 
		try {
			PrintWriter out = response.getWriter();
//			Payment paymentObj = new Payment();  
			
			String customerID2 = request.getParameter("customerID");
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection( "jdbc:mysql://127.0.0.1:3306/electrogrid_it20246228", "root", "hotel*123");
			
			 String customerID = Integer.toString(getInt("customerID"));
			
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("select *  from customers where customerID =' " + customerID2 + "'" );
			ResultSet rs = (ResultSet) ps.executeQuery();
			
			out.print("<table>");
			 while (rs.next())
			 {
				
				 // Add into the html table
				 out.println("<tr><td>customerID</td>"
				 		+ "<td><td>customerName</td>"
				 		+ "<td><td>customerAddress</td>"
				 		+ "<td><td>customerEmail</td>"
				 		+ "<td><td>customerContact</td></tr>");
		 
				 out.println("<tr><td>"+rs.getString(1)+"</td>"
				 		+ "<td><td>"+rs.getString(2)+"</td>"
				 		+ "<td><td>"+rs.getString(3)+"</td>"
				 		+ "<td><td>"+rs.getString(4)+"</td>"
				 	    + "<td><td>"+rs.getString(5)+"</td></tr>");
				 
			 }
			 con.close();
			 out.println("Customer Details:");
			 out.println("         ");
			 out.println("          ");
			 out.println("</table>");
		}
		catch(Exception e) {
			e.printStackTrace();
			   
		}
	}


	private int getInt(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}