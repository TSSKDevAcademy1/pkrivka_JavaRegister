package register;

public interface Register {

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getCount()
	 */
	int getCount();

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getSize()
	 */
	int getSize();

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getPerson(int)
	 */
	Person getPerson(int index);

	/**
	 * 
	 * 
	 * @see register.Register#addPerson(register.Person) - ak v registry nie je
	 * 
	 * @param person
	 *            osoba, ktoru chceme vlozit do registra
	 * @param osoba
	 *            osoba v existujucom registry metoda vlozi novy zaznam, inak
	 *            kontroluje, ci nenajde zhodu s menom alebo cislom
	 */
	void addPerson(Person person);

	/**
	 * - hlada zhodu s menom osoby, ulozenej v databaze
	 * 
	 * @see register.Register#findPersonByName(java.lang.String)
	 * 
	 * @return persons vrati osobu na zaklade vyhladavania
	 */
	Person findPersonByName(String name);

	/**
	 * - hlada zhodu s cislom osoby, ulozenej v databaze
	 * 
	 * @see register.Register#findPersonByPhoneNumber(java.lang.String)
	 * @return persons vrati osobu na zaklade vyhladavania
	 */
	Person findPersonByPhoneNumber(String phoneNumber);

	/**
	 * - hlada zhodu s danym zaznamom podla indexu, ak najde zhodu, vsetky
	 * dalsie zaznamy popresuva o jedno miesto nadol, pricom tiez decrementuje
	 * velkost registra
	 * 
	 * @see register.Register#removePerson(register.Person)
	 * @param person
	 *            osoba, ktora sa ma vymazat
	 */
	void removePerson(Person person);

}