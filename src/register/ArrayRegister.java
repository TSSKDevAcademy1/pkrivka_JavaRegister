package register;

import java.io.Serializable;

/**
 * register.Person register.
 */
public class ArrayRegister implements Register,Serializable {
	/**
	 * 
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getCount()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getCount()
	 */
	@Override
	public int getCount() {
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getSize()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getSize()
	 */
	@Override
	public int getSize() {
		return persons.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getPerson(int)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getPerson(int)
	 */
	@Override
	public Person getPerson(int index) {
		return persons[index];
	}

	/**
	 * - vlozi osobu person do registra
	 * 
	 * @param person
	 *            osoba, ktora ma byt vlozena do registra
	 * @see register.Register#addPerson(register.Person)
	 */
	@Override
	public void addPerson(Person person) {
			persons[count] = person;
			count++;
	}

	/**
	 * - vyhladava osobu podla zadaneho mena
	 * 
	 * @param name
	 *            meno, podla ktoreho ma vyhladavat
	 * @return persons osoba, ktora bola najdena v registri podla zadaneho mena
	 * @see register.Register#findPersonByName(java.lang.String)
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
	 * @param phoneNumber
	 *            cislo, podla ktoreho ma vyhladavat
	 * @return persons osoba, ktora bola najdena v registri podla zadaneho cisla
	 * @see register.Register#findPersonByPhoneNumber(java.lang.String)
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
	 * @param person
	 *            zadana osoba v registry, ktora ma byt zmazana
	 * @see register.Register#removePerson(register.Person)
	 */
	@Override
	public void removePerson(Person person) {
		for (int i = 0; i < count; i++) {
			if (person.equals(persons[i])) {
				if (i==count-1){
					persons[i]=null;
				}
				else 
				persons[i]=persons[count-1];				
//				for (int j = i; j < count; j++) {
//					persons[j] = persons[j + 1];
//				}
//				count--;
			}
		}
		--count;
	}
}
