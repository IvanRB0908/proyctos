package proyecto_ed;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion_bd {
    public static Connection conectar() {
        try {
            String url = "jdbc:postgresql://localhost:5432/hospital";
            String user = "postgres";
            String pass = "admin";
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
            return null;
        }
    }
}
