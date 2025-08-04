package FormularioBoletos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Boletos {
    Connection con;

  public Connection getConnection() {
        try {
            String url = "jdbc:postgres://localhost/concierto_boleto_db";
            String user = "postgres";
            String pass = "admin";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return con;
    }
}
