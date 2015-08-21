package register;

import java.io.Serializable;

/**
 * register.Person register.
 */
public class ArrayRegister implements Register, Serializable {
	private static final long serialVersionUID = 1L;

	/** register.Person array. */
	private Person[] persons;
	/** Number of persons in this register. */
	private int count;

	/**
	 * Constructor creates an empty register with maximum size specified.
	 * 
	 * @param size
	 *            maximum size of the register
	 */
	public ArrayRegister(int size) {
		persons = new Person[size];
		count = 0;
	}

	/**
	 * Metoda vlozi osobu person do registra.
	 * 
	 * @param person
	 *            osoba, ktora ma byt vlozena do registra
	 */
	@Override
	public void addPerson(Person person) {
		persons[count] = person;
		count++;
	}

	/**
	 * Metoda vrati osobu z registra podla zadaneho mena.
	 * 
	 * @param name
	 *            meno, podla ktoreho ma vyhladavat
	 * @return persons osoba, ktora bola najdena v registri podla zadaneho mena
	 */
	@Override
	public Person findPersonByName(String name) {
		for (int i = 0; i < count; i++) {
			if (name.equals(persons[i].getName())) {
				return persons[i];
			}
		}
		return null;
	}

	/**
	 * Metoda vrati osobu z registra podla zadaneho telefonneho cisla.
	 * 
	 * @param phoneNumber
	 *            cislo, podla ktoreho ma vyhladavat
	 * @return persons osoba, ktora bola najdena v registri podla zadaneho cisla
	 */
	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		for (int i = 0; i < count; i++) {
			if (phoneNumber.equals(persons[i].getPhoneNumber())) {
				return persons[i];
			}
		}
		return null;
	}

	/**
	 * Metoda vymaze zvolenu osobu z registra.
	 * 
	 * @param person
	 *            zadana osoba v registry, ktora ma byt zmazana
	 */
	@Override
	public void removePerson(Person person) {
		for (int i = 0; i < count; i++) {
			if (person.equals(persons[i])) {
				if (i == count - 1) {
					persons[i] = null;
				} else
					persons[i] = persons[count - 1];
			}
		}
		--count;
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public int getSize() {
		return persons.length;
	}

	@Override
	public Person getPerson(int index) {
		return persons[index];
	}
}
