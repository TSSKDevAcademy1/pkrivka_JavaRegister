package register;

public interface Register {

	int getCount();

	int getSize();

	Person getPerson(int index);

	/**
	 * @param person
	 *            osoba, ktoru chceme vlozit do registra
	 */
	void addPerson(Person person);

	/**
	 * Metoda vrati osobu z registra podla zadaneho mena.
	 */
	Person findPersonByName(String name);

	/**
	 * Metoda vrati osobu z registra podla zadaneho telefonneho cisla.
	 */
	Person findPersonByPhoneNumber(String phoneNumber);

	/**
	 * Metoda vymaze zvolenu osobu z registra.
	 * 
	 * @param person
	 *            zadana osoba v registry, ktora ma byt zmazana
	 */
	void removePerson(Person person);

}