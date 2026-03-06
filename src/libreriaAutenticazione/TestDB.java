package libreriaAutenticazione;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDB {
    private static final String URL = "jdbc:sqlite:db/authlib.db";
    
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public void stampaUtenti() {
        String sql = "SELECT username, email, password FROM Utente";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");

                System.out.println("Username: " + username);
                System.out.println("Email: " + email);
                System.out.println("Password: " + password);
                System.out.println("---------------------------");
            }

        } catch (SQLException e) {
            System.err.println("Errore durante la lettura degli utenti: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TestDB dao = new TestDB();
        dao.stampaUtenti();
    }
}