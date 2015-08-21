package register;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileRegisterLoader implements Registerable {
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private static final String FILE = "register.bin";

	/**
	 * Metoda zapise vytvoreny register do .bin suboru.
	 */
	@Override
	public void writeRegister(Register register) {
		try (FileOutputStream out = new FileOutputStream(FILE); ObjectOutputStream su = new ObjectOutputStream(out)) {
			su.writeObject(register);
			System.out.println("Register saved to .bin file successfully.");
		} catch (Exception e) {
			System.err.println("Error occured while saving register: " + e.getMessage());
		}
	}

	/**
	 * Metoda nacita register zo zvoleneho suboru.
	 */
	@Override
	public Register registerLoad() {
		Register register = null;
		try (FileInputStream in = new FileInputStream(FILE); ObjectInputStream si = new ObjectInputStream(in)) {
			register = (Register) si.readObject();
			System.out.println(".bin file with register loaded successfully.");
			return register;
		} catch (Exception e) {
			System.out.println("Error occured while loading .bin file: " + e.getMessage());
			System.out.println("Select type of the register, you want to create:\n1) ArrayRegister\n2) ListRegister");
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
