package register;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class TextFileRegisterLoader implements Registerable {
	// private Register register;
	private static final String TXTFILE = "register.txt";

	@Override
	public void writeRegister(Register register) {
		try (PrintWriter out = new PrintWriter(TXTFILE);) {
			for (int i = 0; i < register.getCount(); i++) {
				out.println(register.getPerson(i).getName());
				out.println(register.getPerson(i).getPhoneNumber());
			}
			System.out.println("Register bol uspesne zapisany do TXT suboru.");
		} catch (FileNotFoundException e) {
			System.out.println("Pri zapisovani nastala chyba: " + e.getMessage());
		}
	}

	@Override
	public Register registerLoad() throws Exception {
		String name;
		String phonenumber;
		Register register;
		//
		try (BufferedReader br = new BufferedReader(new FileReader(TXTFILE));) {
			register = new ArrayRegister(20);
			String line;
			while ((line = br.readLine()) != null) {
				name = line.trim();
				phonenumber = br.readLine().trim();
				register.addPerson(new Person(name, phonenumber));
			}
			System.out.println("TXT subor s registrom uspesne nacitany!");
			return register;
		} catch (FileNotFoundException e) {
			System.out.println("Pri nacitavani z TXT suboru nastala chyba: " + e.getMessage());
			try (PrintWriter out = new PrintWriter(TXTFILE);) {
				System.out.println("Bol vytvoreny novy txt subor.");
				register = new ArrayRegister(20);
				return register;
			} catch (Exception e2) {
				System.out.println("Pri vytvarani txt suboru nastala chyba: " + e2.getMessage());
			}
		}
		return null;
	}
}
