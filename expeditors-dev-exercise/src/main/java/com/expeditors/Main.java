package com.expeditors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
	
	private static final String INPUT_DATA_PATH = "src/main/resources/household_data.txt";
	private static final String SPLIT_DELIMMITER = "\",\"";
	private static final String REGEX = "[^a-zA-Z0-9\\s]";
	private static final int FIRST_NAME_INDEX = 0;
	private static final int LAST_NAME_INDEX = 1;
	private static final int STREET_INDEX = 2;
	private static final int CITY_INDEX = 3;
	private static final int STATE_INDEX = 4;
	private static final int AGE_INDEX = 5;
	private static final int AGE_THRESHOLD = 18;

	public static void main(String[] args) {
		
		try {

			/*Update household_data.txt file under resources folder to test different inputs*/
            File file = new File(INPUT_DATA_PATH);

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String line = "";
            
            /*Map to keep track of households. Key = Address string, value = set of Persons*/
            Map<Address, Set<Person>> households = new HashMap<>();

            while ((line = bufferedReader.readLine()) != null) {
            	String[] inputLineArr = line.split(SPLIT_DELIMMITER);
            	
            	/*I'm making the assumption that all 6 fields must be present for input to be considered valid (first/last name, street, city, state, age)*/
            	if(inputLineArr.length != 6) {
            		throw new IllegalArgumentException("Input not in expected format.");
            	}
            	
            	for(int i = 0; i < inputLineArr.length; i++) {
            		String cleaned = cleanString(inputLineArr[i]); /*remove any non alphanumeric chars and leading/trailing spaces for easier comparison later*/
            		
            		inputLineArr[i] = cleaned;
   
            	}
            	
            	/*Create Address object. I'm assuming that the order in which name, address, age appears is correct and what we're expecting*/
            	String street = inputLineArr[STREET_INDEX];
            	String city = inputLineArr[CITY_INDEX];
            	String state = inputLineArr[STATE_INDEX];
            	
            	Address address = new Address(street, city, state);
            	
            	/*Create Person object. I'm assuming it's a single person if name, age, and address are the same*/
            	String firstName = inputLineArr[FIRST_NAME_INDEX];
            	String lastName = inputLineArr[LAST_NAME_INDEX];
            	int age = Integer.valueOf(inputLineArr[AGE_INDEX]);
            	
            	Person person = new Person(firstName, lastName, age, address);
            	
            	/*Add person to household. I'm assuming that people who have the same address are part of the same household*/

            	if (households.containsKey(address)) {
            		households.get(address).add(person);
            	} else {
            		households.put(address, new HashSet<>());
            		households.get(address).add(person);
            	} 
				
            }
           
            
            for(Map.Entry<Address, Set<Person>> entry: households.entrySet()) {
        		Set<Person> occupants = entry.getValue();
        		List<Person> occupantsList = new ArrayList<>(occupants);
        		
        		/*Sort occupants by last name then first name*/
        		Collections.sort(occupantsList, new Comparator<Person>() {

        			@Override public int compare(Person p1, Person p2) {

        				int result = p1.getLastName().compareToIgnoreCase(p2.getLastName());

        				if(result != 0){ 
        					return result; 
        				} 

        				return p1.getFirstName().compareToIgnoreCase(p2.getFirstName()); 
        			}

        		});
				 
        		
        		/*
        		 * The expected output format was a little unclear to me. Since a single household can have multiple people with different last names, I used the address to represent household name.
        		 * For number of total occupants, I'm including occupants with age 18 and under as well.
        		 * I'm only printing the names and addresses of occupants over the age of 18.
        		 * Since it wasn't mentioned if output data needs to have the original format of input data (i.e. Periods, commas, quotation marks, etc.), I printed in clean format.
        		 * There is no order in which the households get printed.
        		 * 
        		 */
        		System.out.println("Household address: " + entry.getKey() + ". Number of total occupants: " + occupants.size());
        		System.out.println("List of occupants older than 18: ");
        		
        		for(Person occupant: occupantsList) {
        			if(occupant.getAge() > AGE_THRESHOLD) {
        				System.out.println(occupant.toString());
        			}
        		}
        		System.out.println(); /*Printing a blank line for distinction between households*/
        	}
            
            if(bufferedReader != null) {
            	bufferedReader.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } 
	}
	
	/**
	 * Method to remove non alphanumeric characters from string
	 * @param s
	 * @return
	 */
	public static String cleanString(String s) {
		String cleaned = s.replaceAll(REGEX, "").trim();
		
		return cleaned;
	}
	
	

}
