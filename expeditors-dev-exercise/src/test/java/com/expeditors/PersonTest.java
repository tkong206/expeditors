package com.expeditors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class PersonTest {
	
	private Person person;
	private Address address;
	
	@Before
	public void setup() {
		address = new Address("1234 Test St", "Seattle", "WA");
		person = new Person("Tung", "Kong", 90, address);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullNameInput() {
		Person person2 = new Person(null, "Kong", 90, address);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullAddressInput() {
		Person person2 = new Person(null, "Kong", 90, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegativeAgeInput() {
		Person person2 = new Person(null, "Kong", -1, address);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorEmptyNameInput() {
		Person person2 = new Person("", "Kong", 90, address);
	}
	
	@Test
	public void testSetFirstNameValidInput() {
		person.setFirstName("Tungsten");
		assertEquals("Tungsten", person.getFirstName());
	}
	
	@Test
	public void testSetLastNameValidInput() {
		person.setLastName("Ferret");
		assertEquals("Ferret", person.getLastName());
	}
	
	@Test
	public void testSetAgeValidInput() {
		person.setAge(15);
		assertEquals(15, person.getAge());
	}
	
	@Test
	public void testSetAddressValidInput() {
		Address address2 = new Address("4321 Test St", "Shoreline", "WA");
		person.setAddress(address2);
		assertEquals("4321 Test St, Shoreline, WA", person.getAddress().toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetFirstNameNullInput() {
		person.setFirstName(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetFirstNameEmptyInput() {
		person.setFirstName("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetLastNameNullInput() {
		person.setLastName(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetLastNameEmptyInput() {
		person.setLastName("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetAgeNegativeInput() {
		person.setAge(-100);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetAddressNullInput() {
		person.setAddress(null);
	}
	
	@Test
	public void testEqualsSameInput() {
		Person person2 = new Person("Tung", "Kong", 90, address);
		assertEquals(person, person2);
	}
	
	@Test
	public void testEqualsSameInputIgnoresCase() {
		Person person2 = new Person("tung", "kong", 90, address);
		assertEquals(person, person2);
	}
	
	@Test
	public void testNotEqualsDiffInput() {
		Person person2 = new Person("tungsten", "kong", 90, address);
		assertNotEquals(person, person2);
	}
	
	@Test
	public void testNotEqualsSameNameAndAddressDiffAge() {
		Person person2 = new Person("Tung", "Kong", 2, address);
		assertNotEquals(person, person2);
	}
	
	@Test
	public void testNotEqualsSameNameAndAgeDiffAddress() {
		Address address2 = new Address("987 Nowhere rd", "Seattle", "WA");
		Person person2 = new Person("Tung", "Kong", 90, address2);
		assertNotEquals(person, person2);
	}
	
	
}
