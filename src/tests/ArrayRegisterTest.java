package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import register.ArrayRegister;
import register.Person;
import register.Register;

public class ArrayRegisterTest {
	Register register;

	@Before
	public void setUp() {
		register = new ArrayRegister(20);
	}

	@Test
	public void addPersonTest() {
		register.addPerson(new Person("Osoba", "0908147951"));
		assertEquals(1, register.getCount());
	}

	@Test
	public void findPersonByNameTest() {
		Person person = new Person("Osoba", "0908147951");
		register.addPerson(person);
		assertEquals(person, register.findPersonByName("Osoba"));
	}

	@Test
	public void findPersonByNameTestWrong() {
		Person person = new Person("Osoba", "0908147951");
		register.addPerson(person);
		assertNotEquals(person, register.findPersonByName("Janko"));
	}

	@Test
	public void findPersonByPhoneNumber() {
		Person person = new Person("Osoba", "0908147951");
		register.addPerson(person);
		assertEquals(person, register.findPersonByPhoneNumber("0908147951"));
	}

	@Test
	public void findPersonByPhoneNumberWrong() {
		Person person = new Person("Osoba", "0908147951");
		register.addPerson(person);
		assertNotEquals(person, register.findPersonByPhoneNumber("0904123741"));
	}

	@Test
	public void removePersonTest() {
		Person person = new Person("Osoba", "0908147951");
		register.addPerson(person);
		assertEquals(1, register.getCount());
		register.removePerson(person);
		assertEquals(0, register.getCount());

	}
}
