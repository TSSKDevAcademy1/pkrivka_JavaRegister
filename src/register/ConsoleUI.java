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
	private static final long serialVersionUID = 1L;
	/** register.Register of persons. */
	private Register register;
	String choice = "";
	int hCount = 0;

	private Registerable reg = new FileRegisterLoader();
	private Registerable regTxt = new TextFileRegisterLoader();
	private Registerable regDat = new DatabaseRegisterLoader();
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

	/**
	 * Konstruktor nacita zvoleny register.
	 */
	public ConsoleUI() {
		while (!"1".equals(choice) || !"2".equals(choice) || !"3".equals(choice)) {
			System.out.println("Select source to load the register:\n1.) .bin file\n2.) .txt file\n3.) database");
			choice = readLine();
			try {
				if ("1".equals(choice)) {
					this.register = reg.registerLoad();
					return;
				} else if ("2".equals(choice)) {
					this.register = regTxt.registerLoad();
					return;
				} else if ("3".equals(choice)) {
					this.register = regDat.registerLoad();
					return;
				} else {
					System.out.println("Wrong selection!");
				}
			} catch (Exception e) {
				System.out.println("Error occured while loading register: " + e.getMessage());
			}
		}
	}

	/**
	 * Metoda zobrazi menu pre pouzivatela.
	 */
	public void run() {
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
							"Select destination to save the register:\n1.) .bin file\n2.) .txt file\n3.) database\n4.) exit without saving");
					choice = readLine();
					try {
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
							System.out.println("Exit...");
							return;
						} else {
							System.out.println("Wrong selection!");
						}
					} catch (Exception e) {
						System.out.println("Error occured while saving register: " + e.getMessage());
					}
				}
			}
		}
	}

	/**
	 * Metoda nacitava vstup od pouzivatela.
	 * 
	 * @return
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Metoda, ktora vytvori menu.
	 * 
	 * @return
	 * 
	 */
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

	/**
	 * Metoda zisti dlzku najdlhsieho mena v registri.
	 * 
	 * @return hCount dlzka mena
	 */
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
	 * Metoda pomocou StringBuildera vypise vsetky zaznamy z registra.
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
			System.out.println("Register is empty.");
	}

	void line(Formatter f) {
		f.format("%s", " ");
		for (int i = 0; i < hCount + 17; i++) {
			f.format("%s", "-");
		}
		f.format("%s", "\n");
	}

	/**
	 * Metoda precita udaje zo vstupu od pouzivatela a vlozi ich do registra ako
	 * novu osobu pomocou.
	 */
	private void addToRegister() {
		System.out.println("Enter name: ");
		String name = readLine();
		System.out.println("Enter phone number (format:0908123456): ");
		String phoneNumber = readLine();
		if (register.findPersonByName(name) != null
				&& register.findPersonByName(name).getPhoneNumber().equals(phoneNumber)) {
			System.out.println("This user already exists.");
		} else {
			Person person = new Person(name, phoneNumber);
			if (person.getPhoneNumber() != null) {
				register.addPerson(person);
				System.out.println(
						"Person: [" + name + "]-[" + phoneNumber + "] was successfully added to the database.");
			}
		}
	}

	/**
	 * Metoda aktualizuje hodnoty v registry
	 */
	private void updateRegister() {
		if (register.getCount() > 0) {
			System.out.println("Enter index of person: ");
			int index = Integer.parseInt(readLine());
			if (register.getCount() > index - 1) {
				Person person = register.getPerson(index - 1);
				System.out.format("%3s", index + ". " + person + "\n");
				while (!"1".equals(choice) || !"2".equals(choice)) {
					System.out.println("Which part do you want to update?\n1.) name\n2.) phone number");
					choice = readLine();
					if ("1".equals(choice)) {
						System.out.println("Enter name: ");
						String meno = readLine();
						person.setName(meno);
						System.out.println("Name updated!");
						return;
					} else if ("2".equals(choice)) {
						System.out.println("Enter phone number (format:0908123456): ");
						String cislo = readLine();
						person.setPhoneNumber(cislo);
						System.out.println("Phone number updated!");
						return;
					} else
						System.out.println("Wrong selection!");
				}
			} else
				System.out.println("Inserted index is bigger than size of the register!");
		} else
			System.out.println("Wrong operation. Register is empty!");
	}

	/**
	 * Metoda vyhladava zaznamy v registeri podla mena alebo cisla.
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
					System.out.println("Register doesn't contain person with this name!");
				} else {
					System.out.println(zaznam);
				}
			} else if ("2".equals(choice)) {
				System.out.println("Enter phone number: ");
				String cislo = readLine();
				Person zaznam = register.findPersonByPhoneNumber(cislo);
				if (zaznam == null) {
					System.out.println("Register doesn't contain person with this phone number!");
				} else {
					System.out.println(zaznam);
				}
			} else {
				System.out.println("Wrong selection!");
			}
		} else
			System.out.println("Wrong operation. Register is empty!");
	}

	/**
	 * Metoda vymaze osobu z registra na zaklade zadaneho indexu.
	 */
	private void removeFromRegister() {
		if (register.getCount() > 0) {
			System.out.println(register.getCount());
			while (!"1".equals(choice) || !"2".equals(choice)) {
				System.out.println("What do you want to remove:\n1.) single record\n2.) all records in register");
				choice = readLine();
				if ("1".equals(choice)) {
					System.out.println("Select index: ");
					int index = Integer.parseInt(readLine());
					if (register.getCount() > index - 1) {
						Person person = register.getPerson(index - 1);
						register.removePerson(person);
					} else
						System.out.println("Inserted index is bigger than size of the register!");
					return;
				} else if ("2".equals(choice)) {
					for (int i = 0; i < register.getCount(); i++) {
						register.removePerson(register.getPerson(i));
						i--;
					}
					System.out.println("All records deleted!");
					return;
				}
			}
		} else
			System.out.println("Wrong operation. Register is empty!");
	}
}
