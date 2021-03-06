package register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseRegisterLoader implements Registerable {

	public static final String URL = "jdbc:mysql://localhost/register";
	public static final String USER = "root";
	public static final String PASSWORD = "admin";
	public static final String QUERYDELETE = "DELETE FROM register";
	public static final String QUERYINSERT = "INSERT INTO register (id, name, phonenumber) VALUES (?, ?, ?)";
	public static final String QUERYSELECT = "SELECT id, name, phonenumber FROM register";
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Metoda vytvori spojenie s databazou a zapise do nej udaje.
	 */
	@Override
	public void writeRegister(Register register) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
			Statement stmt = con.createStatement();
			// final String QUERYCREATE = "CREATE TABLE register (id INT PRIMARY
			// KEY, name VARCHAR(32) NOT NULL, phonenumber VARCHAR(10) NOT
			// NULL)";
			stmt.executeUpdate(QUERYDELETE);
			stmt.close();
			PreparedStatement pstmt = con.prepareStatement(QUERYINSERT);
			int count = register.getCount();
			for (int i = 0; i < count; i++) {
				pstmt.setInt(1, i + 1);
				pstmt.setString(2, register.getPerson(i).getName());
				pstmt.setString(3, register.getPerson(i).getPhoneNumber());
				pstmt.executeUpdate();
			}
			pstmt.close();
			System.out.println("Register saved to database successfully.");
		} catch (SQLException e) {
			System.out.println("Error occured while saving to database: " + e.getMessage());
		}
	}

	/**
	 * Metoda vytvori spojenie s databazou a nacita z nej udaje.
	 * 
	 * @return register nacitany register
	 */
	@Override
	public Register registerLoad() {
		Register register = null;
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(QUERYSELECT)) {
			register = new ListRegister();
			while (rs.next()) {
				register.addPerson(new Person(rs.getString(2), rs.getString(3)));
			}
			System.out.println("Database with register loaded successfully.");
			return register;
		} catch (SQLException e) {
			System.out.println("Error occured while loading database: " + e.getMessage());
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
