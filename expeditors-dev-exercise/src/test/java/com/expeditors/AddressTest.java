package com.expeditors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;



public class AddressTest {
	
	
	private Address address;
	

	@Before public void setup() { 
		address = new Address("1234 Test St", "Seattle", "WA"); 
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullInput() {
		Address address2 = new Address(null, "Seattle", "WA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorEmptyInput() {
		Address address2 = new Address("", "Seattle", "WA");
	}

	@Test
	public void testSetStreetValidInput() {
		address.setStreet("1234 Changed St");
		assertEquals("1234 Changed St", address.getStreet());
	}
	
	@Test
	public void testSetCityValidInput() {
		address.setCity("Shoreline");
		assertEquals("Shoreline", address.getCity());
	}
	
	@Test
	public void testSetStateValidInput() {
		address.setState("OR");
		assertEquals("OR", address.getState());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetStreetNullThrowsException() {
		address.setStreet(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetStreetEmptyThrowsException() {
		address.setStreet("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetCityNullThrowsException() {
		address.setCity(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetCityEmptyThrowsException() {
		address.setCity("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetStateNullThrowsException() {
		address.setState(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetStateEmptyThrowsException() {
		address.setState("");
	}
	
	@Test
	public void testEqualsSameStreetCityState() {
		Address address2 = new Address("1234 Test St", "Seattle", "WA");
		assertEquals(address, address2);
	}
	
	@Test
	public void testEqualsSameStreetCityStateIgnoresCase() {
		Address address2 = new Address("1234 test st", "seattle", "Wa");
		assertEquals(address, address2);
	}
	
	@Test
	public void testSameStreetSameCityDiffStateNotEquals() {
		Address address2 = new Address("1234 Test St", "Seattle", "CA");
		assertNotEquals(address, address2);
	}
	
	@Test
	public void testSameStreetSameStateDiffCityNotEquals() {
		Address address2 = new Address("1234 Test St", "Shoreline", "WA");
		assertNotEquals(address, address2);
	}
	
	@Test
	public void testSameCitySameStateDiffStreetNotEquals() {
		Address address2 = new Address("1234 Not Test St", "Seattle", "WA");
		assertNotEquals(address, address2);
	}
	
	@Test
	public void testEqualsWithPeriodInStreet() {
		String street = Main.cleanString("1234 Test St.");
		Address address2 = new Address(street, "Seattle", "WA");
		assertEquals(address, address2);
	}

}
