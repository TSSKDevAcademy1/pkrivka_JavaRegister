package tests;

import java.security.Permissions;
import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import register.ArrayRegister;
import register.Person;
import register.Register;

@RunWith(Suite.class)
@SuiteClasses({})
public class RegisterTest {
	Register register;
	Person person;
	ArrayRegister array;
	String name = "Janko";
	String phoneNumber = "0908236145";
	
	@Before
	public void setUp(){
		person=new Person(name,phoneNumber);
		array=new ArrayRegister(10);
	}
		
	@Test
	public void addArray(Person person){
		register.addPerson(person);
//		for (int i = 0; i < array.getCount(); i++) {
//			assertArrayEquals(array.getPerson(i), person);
//			assert
//		}
//		assert
	}

}
