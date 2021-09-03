package com.addressbook_system_workshop;

import java.util.Scanner;

public class AddressBookMain {
	
	
	public void createContact() {
		
		PersonContact contact =new PersonContact();
		Scanner sc= new Scanner(System.in);
		
		System.out.print("\nEnter First Name  : ");  
        String firstName = sc.nextLine();
        contact.setFirstName(firstName);

        System.out.print("\nEnter Last Name  : ");  
        String lastName = sc.nextLine(); 
        contact.setLastName(lastName);

        System.out.print("\nEnter E-mail  : ");
        String email = sc.nextLine();
        contact.setEmail(email);

        System.out.print("\nEnter Address  : ");  
        String address = sc.nextLine(); 
        contact.setAddress(address);

        System.out.print("\nEnter City  : ");  
        String city = sc.nextLine(); 
        contact.setCity(city);

        System.out.print("\nEnter State  : ");  
        String state = sc.nextLine();
        contact.setState(state);
        
        System.out.print("\nEnter Phone Number  : ");   
        long phoneNo = sc.nextLong();
        contact.setPhoneNo(phoneNo);

        System.out.print("\nEnter Zip  : ");  
        int zip = sc.nextInt(); 
        contact.setZip(zip);
        showContact(contact);
	}
	
	public void showContact(PersonContact contact){

        System.out.print("\n-----------------");
        System.out.print("\nFirst Name  : " +  contact.getFirstName());
        System.out.print("\nLast Name   : " +  contact.getLastName());
        System.out.print("\nAddress     : " +  contact.getAddress());
        System.out.print("\nCity        : " +  contact.getCity());
        System.out.print("\nState       : " +  contact.getState());
        System.out.print("\nPone Number : " +  contact.getPhoneNo());
        System.out.print("\nE-mail      : " +  contact.getEmail());
        System.out.print("\nZip         : " +  contact.getZip());
    }
	
    public static void main( String[] args ){
    	
    	AddressBookMain book = new AddressBookMain();
    	book.createContact();
    }
}
