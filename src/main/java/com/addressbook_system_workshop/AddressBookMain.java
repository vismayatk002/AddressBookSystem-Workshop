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
        
        System.out.print("\nEnter E-mail  : ");
        String email = sc.nextLine();
        validateEmail(email);
        contact.setEmail(email);
        
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
			
			if(addrBookName.equals(addrBookObj.getAddrName())) {
				
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
	
	public void editContact() {
		
		Scanner sc= new Scanner(System.in);
		System.out.print("\nEnter the Address Book name : ");
		String addrName = sc.nextLine();
		
		if(getAddressBook(addrName) == null ) {
			System.out.print("\nCouldn't find the Address Book..");
		}
		else {
			int flag = 1;
			System.out.print("\nEnter the Person's First name : ");
	        String editName = sc.nextLine();
	        for (PersonContact personObj : getAddressBook(addrName).getPersonList()) {
	            if(editName.equals(personObj.getFirstName())){
	                System.out.print("\n--------------");
	                System.out.print("\n***Update Menu***");
	                System.out.print("\n--------------");
	                System.out.print("\n1.Update Last Name \n2.Update Address \n3.Update City \n4.Update State \n5.Update Phone Number \n6.Update e-mail \n7.Update Zip");
	                System.out.print("\n\nChoose your option for Edit : ");
	                int editOption = sc.nextInt();
	                editDetails(editOption,editName,personObj);
	                flag = 0;
	            }
	        }
	        if(flag == 1){
                System.out.print("\nCouldn't find the contact...");
            }
            else{
                System.out.print("\nYour contact updated !");
            }
		}
	}
	
	public void editDetails(int editOption,String editName,PersonContact personObj) {
		
		Scanner sc= new Scanner(System.in);
		switch(editOption) {
			case 1 : 
				System.out.print("\nEnter your new Last Name : ");
				String firstName = sc.nextLine();
	            personObj.setLastName(firstName);
			break;
			case 2 : 
				System.out.print("\nEnter your new Address : ");
				String address = sc.nextLine();
	            personObj.setAddress(address);
			break;
			case 3 : 
				System.out.print("\nEnter your new City : ");
				String city = sc.nextLine();
	            personObj.setCity(city);
			break;
			case 4 : 
				System.out.print("\nEnter your new State : ");
				String state = sc.nextLine();
	            personObj.setState(state);
			break;
			case 5 :
				System.out.print("\nEnter your new Phone Number : ");
				String phoneNo = sc.nextLine();
	            personObj.setPhoneNo(phoneNo);
			break;
			case 6 :
				System.out.print("\nEnter your new E-mail : ");
				String email = sc.nextLine();
	            personObj.setEmail(email);
			break;
			case 7 :
				System.out.print("\nEnter your new Zip : ");
				int zip = sc.nextInt();
	            personObj.setZip(zip);
			break;
			default :
				System.out.print("\nInvalid option");
		}	
	}
	
	public AddressBook getAddressBook(String addrName){
		
		for(AddressBook addrBookObj : addrBookList) {
			
			if(addrName.equals(addrBookObj.getAddrName())) {
				return addrBookObj;
			}
		}
		return null;
	}
	
	public void showAddrBook() {
		
		for(AddressBook addrBookObj : addrBookList) {
			
			System.out.print("\n\nAddress Book Name : " + addrBookObj.getAddrName());
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
    	int continueFlag;
		do {
	    	System.out.print("\n-------------");
			System.out.print("\n### Address Book Menu ###");
			System.out.print("\n-------------");
	    	System.out.print("\n1.Add Contact \n2.Edit Contact");
	    	System.out.print("\n\nChoose your option : ");
	    	int option = sc.nextInt();
			switch(option) {
				case 1 : 
					book.addContact();
				break;
				case 2 : 
					book.editContact();
					book.showAddrBook();
				break;
				default :
					System.out.print("\nInvalid option");
	    	}	
			System.out.print("\n\nDo you want to continue? Press 1 : ");
			continueFlag = sc.nextInt();
		
		}while(continueFlag == 1);
		
		System.out.print("\nThank you for using Address Book System !!");
		sc.close();
    }
}
