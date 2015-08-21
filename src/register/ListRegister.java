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

	/**
	 * Metoda vrati osobu z registra podla zadaneho mena.
	 * 
	 * @param name
	 *            meno, podla ktoreho ma vyhladavat
	 * @return p osoba, ktora bola najdena v registri podla zadaneho mena
	 */
	@Override
	public Person findPersonByName(String name) {
		Iterator<Person> iterator = persons.iterator();
		while (iterator.hasNext()) {
			Person p = iterator.next();
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}

	/**
	 * Metoda vrati osobu z registra podla zadaneho telefonneho cisla.
	 * 
	 * @param phoneNumber
	 *            cislo, podla ktoreho ma vyhladavat
	 * @return osoba, ktora bola najdena v registri podla zadaneho cisla
	 */
	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		return persons.stream().filter(s -> s.getPhoneNumber().equals(phoneNumber)).findFirst().get();
	}

	/**
	 * Metoda vymaze zvolenu osobu z registra a znova usporiada register.
	 * 
	 * @param person
	 *            zadana osoba v registry, ktora ma byt zmazana
	 */
	@Override
	public void removePerson(Person person) {
		persons.remove(person);
		Collections.sort(persons);
	}
	/**
	 * Metoda usporiada register.
	 */
	public void sortList() {
		Collections.sort(persons);
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
}
