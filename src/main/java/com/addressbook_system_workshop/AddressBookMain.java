package com.addressbook_system_workshop;

import java.util.Scanner;

public class AddressBookMain {

    public static void main( String[] args ){
    	
    	AddressBookOperation book = new AddressBookOperation();
    	Scanner sc= new Scanner(System.in);
    	int continueFlag;
		do {
	    	System.out.print("\n-------------");
			System.out.print("\n### Address Book Menu ###");
			System.out.print("\n-------------");
	    	System.out.print("\n1.Add Contact \n2.Edit Contact \n3.Delete Contact");
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
				case 3 : 
					book.deleteContact();
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
