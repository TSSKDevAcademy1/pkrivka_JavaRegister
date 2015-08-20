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

	@Override
	public void writeRegister(Register register) {
		try (PrintWriter out = new PrintWriter(TXTFILE);) {
			for (int i = 0; i < register.getCount(); i++) {
				out.println(register.getPerson(i).getName());
				out.println(register.getPerson(i).getPhoneNumber());
			}
			System.out.println("Register bol uspesne zapisany do .txt suboru.");
		} catch (FileNotFoundException e) {
			System.out.println("Pri zapisovani nastala chyba: " + e.getMessage());
		}
	}

	@Override
	public Register registerLoad() throws Exception {
		String name;
		String phonenumber;
		Register register;
		try (BufferedReader br = new BufferedReader(new FileReader(TXTFILE));) {
//			register = new ArrayRegister(20);
			register=new ListRegister();
			String line;
			while ((line = br.readLine()) != null) {
				name = line.trim();
				phonenumber = br.readLine().trim();
				register.addPerson(new Person(name, phonenumber));
			}
			System.out.println(".txt subor s registrom uspesne nacitany!");
			return register;
		} catch (FileNotFoundException e) {
			System.out.println("Pri nacitavani z .txt suboru nastala chyba: " + e.getMessage());
			try (PrintWriter out = new PrintWriter(TXTFILE);) {
				System.out.println("Vyber si typ registra, ktory chces vytvorit:\n1) ArrayRegister\n2) ListRegister");
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
			} catch (Exception e2) {
				System.out.println("Pri vytvarani .txt suboru nastala chyba: " + e2.getMessage());
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
