package com.expeditors;

import java.util.Objects;

public class Person {
	
	private String firstName;
	private String lastName;
	private int age;
	private Address address;
	
	public Person(String firstName, String lastName, int age, Address address) {
		
		/*Check for valid input*/
		if(firstName == null || lastName == null || age < 0 || firstName.isEmpty() || lastName.isEmpty()) {
			throw new IllegalArgumentException("Must provide first name, last name, and an age greater than 0.");
		}
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.address = address;
		
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		/*check for valid input*/
		if(firstName == null || firstName.isEmpty()) {
			throw new IllegalArgumentException("First name cannot be null or empty.");
		}
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		/*check for valid input*/
		if(lastName == null || lastName.isEmpty()) {
			throw new IllegalArgumentException("Last name cannot be null or empty.");
		}
		this.lastName = lastName;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		/*check for valid input*/
		if(age < 0) {
			throw new IllegalArgumentException("Age must be a positive integer");
		}
		this.age = age;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		/*check for valid input*/
		if(address == null) {
			throw new IllegalArgumentException("Address cannot be null.");
		}
		this.address = address;
	}
	
	/**
	 * Override equals method. I'm making the assumption that if two Person objects have the same first/last name, age, and address then they are the same person.
	 */
	@Override
	public boolean equals(Object o) {
		
		/*Comparing object to itself*/
        if (o == this) {
            return true;
        }
        
        /*Check object is instance of Person*/
        if (!(o instanceof Person)) {
            return false;
        }
        
        Person p = (Person) o;
        
        return firstName.equalsIgnoreCase(p.firstName) && lastName.equalsIgnoreCase(p.lastName) && age == p.age && address.equals(p.address);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(firstName).append(", ").append(lastName).append(", ").append(address.toString()).append(", ").append(age);
		
		return sb.toString();
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(firstName.toLowerCase(), lastName.toLowerCase(), age, address);
    }


}
