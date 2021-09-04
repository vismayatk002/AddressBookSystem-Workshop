package com.addressbook_system_workshop;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;

public class FileOperation {
	
	HashMap<String,AddressBook> addrBookMap;
	
	
	public FileOperation(HashMap<String,AddressBook> addrBookMap) {
		
		this.addrBookMap = addrBookMap;
	}
	
	public void showMenu() {
		
		Scanner sc= new Scanner(System.in);
		System.out.print("\n---------------------------");
        System.out.print("\n### File Operation Menu ###");
        System.out.print("\n---------------------------");
        System.out.print("\n1.Write details into File \n2.Write details into CSV File \n3.Write details into JSON File");
        System.out.print("\n\nChoose your option for File Operation : ");
        int fileOption = sc.nextInt();
        
        switch(fileOption) {
			case 1 : 
				writeContactToFile();
	            break;
			case 2 : 
				try {
					writeContactToCsv();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            break;
			case 3 : 
				writeContactToJson();
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
	
	public void writeContactToCsv() throws IOException {
			
    	//Instantiating the CSVWriter class
        CSVWriter writer = new CSVWriter(new FileWriter("AddrBookFile.csv"));
        String line[] = {"Address Book Name", "First Name", "Last Name", "Address", "City", "State", "Pone Number", "E-mail", "Zip"};
        List contactList = new ArrayList();
        contactList.add(line);
        for(String keyName : addrBookMap.keySet()) {
			AddressBook addrBookObj = addrBookMap.get(keyName);
			
			addrBookObj.getPersonList().forEach((contact) -> {
        	
	    		String line1[] = {addrBookObj.getAddrName(),contact.getFirstName().toString(), contact.getLastName().toString(), contact.getAddress().toString(), contact.getCity().toString(), contact.getState().toString(), String.valueOf(contact.getPhoneNo()), contact.getEmail().toString(), String.valueOf(contact.getZip())};
	    		contactList.add(line1);
    		
			});
        }
        //Writing data to the csv file
        writer.writeAll(contactList);
        writer.flush();
        System.out.println("Details written into CSV");
    }
	 
    public void writeContactToJson() {
    	
	    try {
	    	// create a writer
	        Writer writer = new FileWriter("AddrBookFile.json");
	        // convert map to JSON File
	        new Gson().toJson(addrBookMap, writer);
	        // close the writer
	        writer.close();
	        System.out.println("Details written into JSON File");
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
    }
}
