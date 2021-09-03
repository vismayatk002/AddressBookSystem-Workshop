package com.addressbook_system_workshop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AddressBookMain {
	
	List<AddressBook> addrBookList = new ArrayList<>();
	
	public void addContact() {
		
		PersonContact contact =new PersonContact();
		Scanner sc= new Scanner(System.in);
		
		System.out.print("\nEnter First Name  : ");  
        String firstName = sc.nextLine();
        validateName(firstName);
        contact.setFirstName(firstName);

        System.out.print("\nEnter Last Name  : ");  
        String lastName = sc.nextLine(); 
        contact.setLastName(lastName);

        System.out.print("\nEnter E-mail  : ");
        String email = sc.nextLine();
        validateEmail(email);
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
        String phoneNo = sc.nextLine();
        validatePhoneNo(phoneNo);
        contact.setPhoneNo(phoneNo);

        System.out.print("\nEnter Zip  : ");  
        int zip = sc.nextInt();
        validateZip(zip);
        contact.setZip(zip);
        
        showContact(contact);
        
        sc.nextLine();
        System.out.print("\n\nIn which Address Book you want to add your contact? ");
        String addrBookName = sc.nextLine(); 
        
        addInAddrBook(addrBookName,contact);
        showAddrBook();
        System.out.print("\n\nYour contact Added !");
	}
	
	public void validateName(String Name) {
		
		if(Character.isUpperCase(Name.charAt(0))) {
			
			if(Name.length() < 3) {
				
				System.out.println("First name should have minimum 3 letters");
			}
		}
		else {
			System.out.println( "first letter must be caps");
		}
	}
	public void validateEmail(String email) {
		
		String regex = "^(.+)@gmail(.+)$"; 
		Pattern pattern = Pattern.compile(regex); 
		if(!pattern.matcher(email).matches()) {
			System.out.println( "Invalid Email id");
		}
	}

	public void validatePhoneNo(String phoneNo) {
		
		Pattern pattern = Pattern.compile("91[0-9]{10}");
		if(!pattern.matcher(phoneNo).matches()) {
			System.out.println("Invalid Mobile number");
		}
	}
	
	public void validateZip(int zip) {
		
		int length = String.valueOf(zip).length();
		if(length > 7) {
			System.out.println("Invalid Zip");
		}
	}
	
	public void addInAddrBook(String addrBookName,PersonContact contact) {
		
		int flag = 1;
		
		for(AddressBook addrBookObj : addrBookList) {
			
			if(addrBookName.compareTo(addrBookObj.getAddrName()) == 0) {
				
				addrBookObj.addContactToList(contact);
				flag = 0;
			}
		}
		if(flag == 1) {
			
			AddressBook addrBook = new AddressBook(addrBookName);
			addrBook.addContactToList(contact);
			addrBookList.add(addrBook);
		}
	}
	public void showAddrBook() {
		
		for(AddressBook addrBookObj : addrBookList) {
			
			System.out.print("\nAddress Book Name : " + addrBookObj.getAddrName());
			for(PersonContact personObj : addrBookObj.getPersonList()) {
				
				showContact(personObj);
			}
		}
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
    	Scanner sc= new Scanner(System.in);
    	System.out.print("\n-------------");
		System.out.print("\n### Address Book Menu ###");
		System.out.print("\n-------------");
    	System.out.print("\n1.Add Contact");
    	System.out.print("\n\nChoose your option : ");
    	int option = sc.nextInt();
		switch(option) {
			case 1 : 
				book.addContact();
			break;
			default :
				System.out.print("\nInvalid option");
    	}	
    }
}
