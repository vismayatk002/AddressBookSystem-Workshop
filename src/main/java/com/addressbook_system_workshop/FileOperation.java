package com.addressbook_system_workshop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class FileOperation {
	
	HashMap<String,AddressBook> addrBookMap;
	
	
	public FileOperation(HashMap<String,AddressBook> addrBookMap) {
		
		this.addrBookMap = addrBookMap;
	}
	
	public void showMenu() {
		
		Scanner sc= new Scanner(System.in);
		System.out.print("\n--------------------------");
        System.out.print("\n***File Operation Menu***");
        System.out.print("\n---------------------------");
        System.out.print("\n1.Write details into File");
        System.out.print("\n\nChoose your option for File Operation : ");
        int fileOption = sc.nextInt();
        
        switch(fileOption) {
			case 1 : 
				writeContactToFile();
	            break;
			default :
				System.out.print("\nInvalid option");
        }	
	}
	
	public void writeContactToFile() {
    	
		StringBuffer contactBuffer = new StringBuffer();
		for(String keyName : addrBookMap.keySet()) {
			
			AddressBook addrBookObj = addrBookMap.get(keyName);
			contactBuffer.append(addrBookObj.getAddrName() + " :");
			
			addrBookObj.getPersonList().forEach((contact) -> {
				String contactString = contact.getFirstName().toString().concat(", ");
	    		contactString += contact.getLastName().toString().concat(", ");
	    		contactString += contact.getAddress().toString().concat(", ");
	    		contactString += contact.getCity().toString().concat(", ");
	    		contactString += contact.getState().toString().concat(", ");
	    		contactString += String.valueOf(contact.getPhoneNo()).concat(", ");
	    		contactString += contact.getEmail().toString().concat(", ");
	    		contactString += String.valueOf(contact.getZip()).concat("\n");
	    		contactBuffer.append(" " + contactString);
	    	});
		}
		try {
    		
    		Files.write(Paths.get("AddrBookFile.txt"),contactBuffer.toString().getBytes());
    		System.out.print("\nDetails written into file");
    	}catch(IOException e) {
    		System.out.print("Unable to write contact into file" + e.getMessage());
    	}
    }
}
