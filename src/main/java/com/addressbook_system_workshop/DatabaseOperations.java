package com.addressbook_system_workshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

public class DatabaseOperations {

	public void showMenu() {
		
		Scanner sc= new Scanner(System.in);
		System.out.print("\n-------------------------------");
        System.out.print("\n### DataBase Operation Menu ###");
        System.out.print("\n-------------------------------");
        System.out.print("\n1.Retrieve all data");
        System.out.print("\n\nChoose your option for DataBase Operation : ");
        int dbOption = sc.nextInt();
        
        switch(dbOption) {
			case 1 : 
				try {
					retrieveAllData();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			default :
				System.out.print("\nInvalid option");
        }	
	}
	
	private static Connection getSqlConnection() {
		Connection con = null;
	       try {
	    	   
	    	   String dbHostUrl = "jdbc:mysql://localhost:3306/AddressBookService_ws?useSSL=false";
	    	   String userName = "root";
	    	   String passWord = "vismaya";
	    	   
	    	   con = DriverManager.getConnection(dbHostUrl, userName, passWord);
	    	   
	    	   if(con != null) {
	    		   System.out.println("Connection is Established");
	    	   }
	    	   
	       }catch(SQLException sqlException) {
	    	   System.out.println(sqlException.getMessage());
	       }
		return con;
	}
	
	public void retrieveAllData() throws SQLException {
		
		Connection con = getSqlConnection();
		//Retrieve all the data
		if(con != null) {
		   String retrieveQuery = "SELECT * FROM addressBook JOIN person ON addressBook.addressBookId = person.addressBookId";
		   Statement statement = (Statement) con.createStatement();
		   ResultSet resultSet = statement.executeQuery(retrieveQuery);
		   System.out.println("\nContact retrieved..");
		   while(resultSet.next()) {
			   
			   int addressBookId = resultSet.getInt("addressBookId");
			   String addressBookName = resultSet.getString("addressBookName");
			   int personId = resultSet.getInt("personId");
			   String firstName = resultSet.getString("firstName");
			   String lastName = resultSet.getString("lastName");
			   String address = resultSet.getString("address");
			   String city = resultSet.getString("city");
			   String state = resultSet.getString("state");
			   String phoneNo = resultSet.getString("phoneNo");
			   String email = resultSet.getString("email");
			   int zip = resultSet.getInt("zip");
			   
			   String rowData = String.format("\nAddressBook Id : %d \nAddressBook Name : %s \nPerson Id : %d \nFirst Name : %s  \nLast Name : %s \nAddress : %s \nCity : %s \nState : %s \nPhone Number : %s \nE-mail : %s \nZip : %d", addressBookId, addressBookName, personId, firstName,lastName,address,city,state,phoneNo,email,zip);
			   System.out.println(rowData + " \n ");
		   }
		}
	}
}
