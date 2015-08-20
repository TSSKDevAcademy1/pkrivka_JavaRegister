package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import register.Person;

public class PersonTest {

	String name = "";
	String phoneNumber = "0908236145";
	Person person;
	// Person person = new Person(name, phoneNumber);

	@Before
	public void setUp() {
		person = new Person(name, phoneNumber);
	}

	//
	@Test(expected=Exception.class)
	public void wrongNumberLength(){
		person.setPhoneNumber("09081247415268");
	}
	
	@Test(expected=Exception.class)
	public void wrongNumberContent(){
		person.setPhoneNumber("adf08745fd");
	}
	
	@Test
	public void wrongNumber() {
		Throwable error = null;
		String[] phoneNumber = new String[4];
		phoneNumber[0] = "09088451";
		phoneNumber[1] = "090884512645665";
		phoneNumber[2] = "0908845a26";
		phoneNumber[3] = "0908365dd+";
		for (int i = 0; i < 4; i++) {
			try {
				person.setPhoneNumber(phoneNumber[i]);
			} catch (RuntimeException e) {
				error = e;
			}
			assertTrue(error instanceof RuntimeException);
			error = null;
		}

	}

	@Test
	public void goodNumber() {
		Throwable error = null;
		String[] phoneNumber = new String[4];
		phoneNumber[0] = "0908845141";
		phoneNumber[1] = "2547123852";
		phoneNumber[2] = "1234567892";
		phoneNumber[3] = "0908321745";
		for (int i = 0; i < 4; i++) {
			try {
				person.setPhoneNumber(phoneNumber[i]);
			} catch (RuntimeException e) {
				error = e;
			}
			assertFalse(error instanceof RuntimeException);
			error = null;
		}

	}

}
