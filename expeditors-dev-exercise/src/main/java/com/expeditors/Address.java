package com.expeditors;

import java.util.Objects;

public class Address{
	private static final String ST = "st";
	private static final String STREET = "street";
	private String street;
	private String city;
	private String state;
	
	public Address(String street, String city, String state) {
		if(street == null || city == null || state == null || street.isEmpty() || city.isEmpty() || state.isEmpty()) {
			throw new IllegalArgumentException("Street, city, state cannot be null or empty.");
		}
		this.setStreet(street);
		this.setCity(city);
		this.setState(state);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		
		if(street == null || street.isEmpty()) {
			throw new IllegalArgumentException("Street cannot be null or empty.");
		}
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		
		if(city == null || city.isEmpty()) {
			throw new IllegalArgumentException("City cannot be null or empty.");
		}
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		
		if(state == null || state.isEmpty()) {
			throw new IllegalArgumentException("State cannot be null or empty.");
		}
		this.state = state;
	}
	
	/**
	 * Override equals method. I'm making the assumption that if two Addresses have the same street, city, state then they are considered the same address. Otherwise they are different
	 */
	@Override
    public boolean equals(Object o) {
 
		/*Comparing object to itself*/
        if (o == this) {
            return true;
        }
        
        /*Check object is instance of Address*/
        if (!(o instanceof Address)) {
            return false;
        }
        
        Address a = (Address) o;
        
        String[] thisStreetArr = street.split(" ");
        String[] aStreetArr = a.street.split(" ");
        
        /*Compare street of Address objects*/
        if(thisStreetArr.length != aStreetArr.length) {
        	return false;
        }
        
        /*Check each word of street*/
        for(int i = 0; i < thisStreetArr.length; i++) {
        	String streetStr = thisStreetArr[i];
        	String aStreetStr = aStreetArr[i];
        	
        	/*Check for Street vs St. I'm making the assumption that St == Street. I would probably also add checks for Ave == Avenue, Blvd == Boulevard*/
        	if(ST.equalsIgnoreCase(streetStr) || STREET.equalsIgnoreCase(streetStr)) {
        		
        		if(!ST.equalsIgnoreCase(aStreetStr) && !STREET.equalsIgnoreCase(aStreetStr)) {
        			return false;
        		}
        	} else {
        		if(!streetStr.equalsIgnoreCase(aStreetStr)) {
        			return false;
        		}
        	}
        	
        	
        }
        
        /*Compare city, state ignoring case*/
        return street.equalsIgnoreCase(a.street) && city.equalsIgnoreCase(a.city) && state.equalsIgnoreCase(a.state);
    }
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(street).append(", ").append(city).append(", ").append(state);
		
		return sb.toString();
	}


	@Override
    public int hashCode() {
        return Objects.hash(street.toLowerCase(), city.toLowerCase(), state.toLowerCase());
    }
}
