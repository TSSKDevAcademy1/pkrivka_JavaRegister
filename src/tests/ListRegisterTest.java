package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import register.ListRegister;
import register.Person;
import register.Register;

public class ListRegisterTest {
	Register register;

	@Test
	public void addPersonTest() {
		register=new ListRegister();
		register.addPerson(new Person("Osoba", "0908147951"));
		assertEquals(1, register.getCount());		
	}
	
	@Test
	public void findPersonByNameTest(){
		register=new ListRegister();
		Person person=new Person("Osoba", "0908147951");
		register.addPerson(person);
		assertEquals(person, register.findPersonByName("Osoba"));
	}
	
	@Test
	public void findPersonByNameTestWrong(){
		register=new ListRegister();
		Person person=new Person("Osoba", "0908147951");
		register.addPerson(person);
		assertNotEquals(person, register.findPersonByName("Janko"));
	}
	
	@Test
	public void findPersonByPhoneNumber(){
		register=new ListRegister();
		Person person=new Person("Osoba", "0908147951");
		register.addPerson(person);
		assertEquals(person, register.findPersonByPhoneNumber("0908147951"));
	}
	
	// @Test
	// public void findPersonByPhoneNumberWrong(){
	// register=new ListRegister();
	// Person person=new Person("Osoba", "0908147951");
	// register.addPerson(person);
	// assertNotEquals(person, register.findPersonByPhoneNumber("0904123741"));
	// }
	
	@Test
	public void removePersonTest(){
		register=new ListRegister();
		Person person=new Person("Osoba", "0908147951");
		register.addPerson(person);
		assertEquals(1, register.getCount());
		register.removePerson(person);
		assertEquals(0, register.getCount());
		
	}

}
