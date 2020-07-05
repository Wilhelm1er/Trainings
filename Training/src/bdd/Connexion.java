package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//NON UTLISE POUR LE MOMENT

public class Connexion {
	/**
	 * Connect to a sample database
	 */
	public static void connect() {
		Connection conn = null;
		try {
			// db parameters
			String url = "jdbc:sqlite:entrainements.db";
			// create a connection to the database
			conn = DriverManager.getConnection(url);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}