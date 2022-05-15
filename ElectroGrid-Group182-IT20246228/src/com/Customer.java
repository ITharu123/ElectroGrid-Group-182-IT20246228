package com;
import java.sql.*;

public class Customer
{
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid_it20246228", "root", "hotel*123");
 }
 catch (Exception e)
 {
 e.printStackTrace();
 }
 return con;
 }

	//Read customer
	
	public String readCustomers()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for reading.";
	 }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Customer Name</th>"
			+ "<th>Customer Address</th><th>Customer Email</th>"
			 + "<th>Customer Contact</th>"
			 + "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from customers";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String customerID = Integer.toString(rs.getInt("customerID"));
	 String customerName = rs.getString("customerName");

	 String customerAddress = rs.getString("customerAddress");
	 String customerEmail = rs.getString("customerEmail");
	 String customerContact = rs.getString("customerContact");

	 // Add into the html table
	output += "<tr><td><input id='hidCustomerIDUpdate'"
			+ "name='hidCustomerIDUpdate'"
			+ "type='hidden' value='" + customerID
	 + "'>" + customerName + "</td>";
	 output += "<td>" + customerAddress + "</td>";
	 output += "<td>" + customerEmail + "</td>";
	 output += "<td>" + customerContact + "</td>";

	//buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' "
	+ "class='btnUpdate btn btn-secondary' data-customerid='" + customerID + "'></td>"
	+ "<td><input name='btnRemove' type='button' value='Remove' "
	+ "class='btnRemove btn btn-danger' data-customerid='" + customerID + "'></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the customers.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	//Insert customer
	
	 public String insertCustomer(String name, String address, String email, String contact)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for inserting.";
	 }
	 // create a prepared statement
	 String query = " insert into customers (`customerID`,`customerName`,`customerAddress`,`customerEmail`,`customerContact`)"

	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);

	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, name);
	 preparedStmt.setString(3, address);
	 preparedStmt.setString(4, email);
	 preparedStmt.setString(5, contact);

	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newCustomers = readCustomers();
	 output = "{\"status\":\"success\", \"data\": \"" + newCustomers + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\":\"Error while inserting the customer.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

	
	//update customer
	
	 public String updateCustomer(String ID, String name, String address,String email, String contact)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for updating.";
	 }
	 // create a prepared statement
	 String query = "UPDATE customers SET customerName=?,customerAddress=?,customerEmail=?,customerContact=? WHERE customerID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);

	 // binding values
	 preparedStmt.setString(1, name);
	 preparedStmt.setString(2, address);
	 preparedStmt.setString(3, email);
	 preparedStmt.setString(4, contact);
	 preparedStmt.setInt(5, Integer.parseInt(ID));

	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newCustomers = readCustomers();
	 output = "{\"status\":\"success\", \"data\": \"" +
	 newCustomers + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\":\"Error while updating the customer.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }


	//delete customer
 
	 public String deleteCustomer(String customerID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for deleting.";
	 }
	 // create a prepared statement
	 String query = "delete from customers where customerID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(customerID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newCustomers = readCustomers();
	 output = "{\"status\":\"success\", \"data\": \"" +
	 newCustomers + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\":\"Error while deleting the customer.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	}
