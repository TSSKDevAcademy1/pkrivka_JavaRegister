package register;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListRegister implements Register, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Person> persons = new ArrayList<>();

	public Iterator<Person> iterator() {
		return persons.iterator();
	}

	/** Number of persons in this register. */

	@Override
	public int getCount() {
		return persons.size();
	}

	@Override
	public int getSize() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Person getPerson(int index) {
		return persons.get(index);
	}

	@Override
	public void addPerson(Person person) {
		persons.add(person);
		Collections.sort(persons);
	}

	@Override
	public Person findPersonByName(String name) {
		// return persons.stream().filter(s ->
		// s.getPhoneNumber().equals(name)).findFirst().get();
		Iterator<Person> iterator = persons.iterator();
		while (iterator.hasNext()) {
			Person p = iterator.next();
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}

	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		return persons.stream().filter(s -> s.getPhoneNumber().equals(phoneNumber)).findFirst().get();
		// Iterator<Person> iterator=persons.iterator();
		// while (iterator.hasNext()){
		// Person p=iterator.next();
		// if (p.getPhoneNumber().equals(phoneNumber)){
		// return p;
		// }
		// }
		// return null;
	}

	@Override
	public void removePerson(Person person) {
		persons.remove(person);
		Collections.sort(persons);
	}
	
	public void sortList(){
		Collections.sort(persons);
	}

}
