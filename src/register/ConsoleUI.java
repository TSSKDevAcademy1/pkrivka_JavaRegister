package register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Formatter;

/**
 * User interface of the application.
 */
public class ConsoleUI implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** register.Register of persons. */
	private Register register;
	String choice = "";
	int hCount = 0;

	private Registerable reg = new FileRegisterLoader();
	private Registerable regTxt = new TextFileRegisterLoader();
	private Registerable regDat = new DatabaseRegisterLoader();
	/**
	 * In JDK 6 use Console class instead.
	 * 
	 * @see readLine()
	 */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Menu options.
	 */
	private enum Option {
		PRINT, ADD, UPDATE, REMOVE, FIND, EXIT
	};

	public ConsoleUI(Register register) {

		this.register = register;
	}

	public ConsoleUI() throws Exception {
		while (!"1".equals(choice) || !"2".equals(choice) || !"3".equals(choice)) {
			System.out.println("Vyber si zdroj pre nacitanie registra:\n1.) .bin subor\n2.) .txt subor\n3.) databaza");
			choice = readLine();
			if ("1".equals(choice)) {
				this.register = reg.registerLoad();
				return;
			} else if ("2".equals(choice)) {
				this.register = regTxt.registerLoad();
				return;
			} else if ("3".equals(choice)) {
				this.register = regDat.registerLoad();
				return;
			} else
				System.out.println("Zadal si zlu volbu!");
		}
	}

	public void run() throws Exception {
		// reg.registerFill(register);
		while (true) {
			switch (showMenu()) {
			case PRINT:
				printRegister();
				break;
			case ADD:
				addToRegister();
				break;
			case UPDATE:
				updateRegister();
				break;
			case REMOVE:
				removeFromRegister();
				break;
			case FIND:
				findInRegister();
				break;
			case EXIT:
				while (!"1".equals(choice) || !"2".equals(choice) || !"3".equals(choice) || !"4".equals(choice)) {
					System.out.println(
							"Vyber si ciel pre ulozenie registra:\n1.) .bin subor\n2.) .txt subor\n3.) databaza\n4.) ukoncit bez ulozenia");
					choice = readLine();
					if ("1".equals(choice)) {
						reg.writeRegister(register);
						return;
					} else if ("2".equals(choice)) {
						regTxt.writeRegister(register);
						return;
					} else if ("3".equals(choice)) {
						regDat.writeRegister(register);
						return;
					} else if ("4".equals(choice)) {
						System.out.println("Register nebol ulozeny.");
						return;
					} else
						System.out.println("Zadal si zlu volbu!");
				}
			}
		}
	}

	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	private Option showMenu() {
		System.out.println("Menu.");
		for (Option option : Option.values()) {
			System.out.printf("%d. %s%n", option.ordinal() + 1, option);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Option: ");
			selection = Integer.parseInt(readLine());
		} while (selection <= 0 || selection > Option.values().length);

		return Option.values()[selection - 1];
	}

	int highestNumberOfChar() {
		for (int i = 0; i < register.getCount(); i++) {
			int countlength = register.getPerson(i).getName().length();
			if (countlength > hCount) {
				hCount = countlength;
			}
		}
		return hCount;
	}

	/**
	 * - pomocou StringBuildera vypise vsetky zaznamy z registra do konzoly
	 * 
	 * @param sb
	 *            novu objekt stringbuilder
	 * @param f
	 *            novy objekt formatter
	 */
	private void printRegister() {
		if (register.getCount() > 0) {
			StringBuilder sb = new StringBuilder();
			Formatter f = new Formatter(sb);
			int high = highestNumberOfChar();
			line(f);
			f.format("%s", "|   Name:");
			for (int i = 0; i < hCount - 5; i++) {
				f.format("%s", " ");
			}
			f.format("%s", "|  Number:    |\n");
			line(f);
			for (int i = 0; i < register.getCount(); i++) {
				f.format("%3s", "|" + (i + 1) + ". ");
				f.format("%s", register.getPerson(i).getName());
				for (int j = 0; j < high - register.getPerson(i).getName().length() + 3; j++) {
					f.format("%1s", " ");
				}
				f.format("%s", register.getPerson(i).getPhoneNumber() + " |\n");
			}
			line(f);
			System.out.println(sb);
			f.close();
		} else
			System.out.println("Register zatial neobsahuje ziaden zaznam.");
	}

	void line(Formatter f) {
		f.format("%s", " ");
		for (int i = 0; i < hCount + 17; i++) {
			f.format("%s", "-");
		}
		f.format("%s", "\n");
	}

	/**
	 * - precita udaje zo vstupu a vlozi ich do registra ako novu osobu pomocou
	 * premennych
	 * 
	 * @param name
	 *            zadane meno zo vstupu
	 * @param phoneNumber
	 *            zadane telefonne cislo zo vstupu
	 */
	private void addToRegister() {
		System.out.println("Enter Name: ");
		String name = readLine();
		System.out.println("Enter Phone Number: ");
		String phoneNumber = readLine();
		if (register.findPersonByName(name) != null
				&& register.findPersonByName(name).getPhoneNumber().equals(phoneNumber)) {
			System.out.println("Zadany pouzivatel uz existuje!!!");
		} else {
			register.addPerson(new Person(name, phoneNumber));
		}

	}

	/**
	 * - aktualizuje hodnoty v registry
	 * 
	 * @param person
	 *            vybrana osoba z registra
	 * @param meno
	 *            zadane meno zo vstupu
	 * @param cislo
	 *            zadane telefonne cislo zo vstupu
	 */
	private void updateRegister() {
		if (register.getCount() > 0) {
			System.out.println("Enter index: ");
			int index = Integer.parseInt(readLine());
			if (register.getCount() > index - 1) {
				Person person = register.getPerson(index - 1);
				System.out.format("%3s", index + ". " + person + "\n");
				System.out.println("Enter name: ");
				String meno = readLine();
				System.out.println("Enter phone number: ");
				String cislo = readLine();
				person.setName(meno);
				person.setPhoneNumber(cislo);
			} else
				System.out.println("Zadany index presahuje pocet zaznamov v registri!");
		} else
			System.out.println("Nemozes vykonat update. Register neobsahuje ziaden zaznam!");
	}

	/**
	 * - prehladava register podla mena alebo podla cisla
	 * 
	 * @param otazka
	 *            vyber typu vyhladavania
	 * @param meno
	 *            nacitane meno zo vstupu
	 * @param cislo
	 *            nacitane cislo zo vstupu
	 * @param zaznam
	 *            najdeny zaznam v registry podla zadaneho mena alebo cisla
	 */
	private void findInRegister() {
		if (register.getCount() > 0) {
			System.out.println("1 - search by name\n2 - search by number");
			String choice = readLine();
			System.out.println(choice);
			if ("1".equals(choice)) {
				System.out.println("Enter name: ");
				String meno = readLine();
				Person zaznam = register.findPersonByName(meno);
				if (zaznam == null) {
					System.out.println("Osoba s danym menom nie je v databaze!");
				} else {
					System.out.println(zaznam);
				}
			} else if ("2".equals(choice)) {
				System.out.println("Enter phone number: ");
				String cislo = readLine();
				Person zaznam = register.findPersonByPhoneNumber(cislo);
				if (zaznam == null) {
					System.out.println("Osoba s danym cislom nie je v databaze!");
				} else {
					System.out.println(zaznam);
				}
			} else {
				System.out.println("Zadal si zlu volbu!");
			}
		} else
			System.out.println("Nemozes vyhladavat v prazdnom registri!");
	}

	/**
	 * - vymaze osobu z registra na zaklade zadaneho indexu
	 * 
	 * @param index
	 *            vybrany index osoby, ktoru chceme zmazat
	 * @param person
	 *            vybrana osoba v registri podla zadaneho indexu
	 */
	private void removeFromRegister() {
		if (register.getCount() > 0) {
			System.out.println("Zadaj index: ");
			int index = Integer.parseInt(readLine());
			if (register.getCount() > index - 1) {
				Person person = register.getPerson(index - 1);
				register.removePerson(person);
			} else
				System.out.println("Zadany index presahuje pocet zaznamov v registri!");
		} else
			System.out.println("Nemozes vymazavat z prazdneho registra!");
	}
}
