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

	@Override
	public void writeRegister(Register register) {
		try (FileOutputStream out = new FileOutputStream(FILE); ObjectOutputStream su = new ObjectOutputStream(out)) {
			su.writeObject(register);
			System.out.println("Register bol uspesne zapisany do suboru.");
		} catch (Exception e) {
			System.err.println("Data sa do suboru ulozit nepodarilo. Chyba: " + e.getMessage());
		}
	}

	@Override
	public Register registerLoad() {
		Register register = null;
		try (FileInputStream in = new FileInputStream(FILE); ObjectInputStream si = new ObjectInputStream(in)) {
			register = (Register) si.readObject();
			System.out.println("Subor s registrom uspesne nacitany!");
			return register;
		} catch (Exception e) {
			System.out.println("Subor s registrom neexistuje. Bude vytvoreny novy subor.");
			System.out.println("Vyber si typ registra, ktory bude dany subor obsahovat:\n1) ArrayRegister\n2) ListRegister");
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
					System.out.println("Zadal si zlu volbu, vyber si medzi moznostou 1 alebo 2:\n1) ArrayRegister\n2) ListRegister");
				}
			}
		}
		return null;
	}

	private static String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}
}
