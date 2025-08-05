package FormularioAlumnos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection con;

    public Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/alumnos_db"; // Corrige nombre de BD
            String user = "root";
            String pass = "fernando1234"; // Corrige si usas otra contraseña

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return con;
    }
}
