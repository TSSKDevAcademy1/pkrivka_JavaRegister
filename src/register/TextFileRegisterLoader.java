package register;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TextFileRegisterLoader implements Registerable {
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private static final String TXTFILE = "register.txt";

	/**
	 * Metoda zapise vytvoreny register do .txt suboru.
	 */
	@Override
	public void writeRegister(Register register) {
		try (PrintWriter out = new PrintWriter(TXTFILE);) {
			for (int i = 0; i < register.getCount(); i++) {
				out.println(register.getPerson(i).getName());
				out.println(register.getPerson(i).getPhoneNumber());
			}
			System.out.println("Register saved to .txt file successfully.");
		} catch (FileNotFoundException e) {
			System.out.println("Error occured while saving register: " + e.getMessage());
		}
	}

	/**
	 * Metoda nacita register zo zvoleneho .txt suboru.
	 */
	@Override
	public Register registerLoad() throws Exception {
		String name;
		String phonenumber;
		Register register;
		try (BufferedReader br = new BufferedReader(new FileReader(TXTFILE));) {
			register = new ListRegister();
			String line;
			while ((line = br.readLine()) != null) {
				name = line.trim();
				phonenumber = br.readLine().trim();
				register.addPerson(new Person(name, phonenumber));
			}
			System.out.println(".txt file with register loaded successfully.");
			return register;
		} catch (FileNotFoundException e) {
			System.out.println("Error occured while loading .txt file: " + e.getMessage());
			try (PrintWriter out = new PrintWriter(TXTFILE);) {
				System.out
						.println("Select type of the register, you want to create:\n1) ArrayRegister\n2) ListRegister");
				String choice = "";
				while (!"1".equals(choice) || !"2".equals(choice)) {
					choice = readLine();
					if ("1".equals(choice)) {
						register = new ArrayRegister(20);
						writeRegister(register);
						return register;
					} else if ("2".equals(choice)) {
						register = new ListRegister();
						writeRegister(register);
						return register;
					} else {
						System.out.println(
								"Wrong choice! You must select between 1 or 2:\n1) ArrayRegister\n2) ListRegister");
					}
				}
			} catch (Exception e2) {
				System.out.println("Error occured while creating .txt file: " + e2.getMessage());
			}
		}
		return null;
	}

	/**
	 * Metoda nacitava vstup od pouzivatela.
	 * 
	 * @return
	 */
	private static String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}
}
