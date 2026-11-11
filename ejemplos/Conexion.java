import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection abrir() throws SQLException {
        String url = System.getenv("DB_URL");
        String usuario = System.getenv("DB_USER");
        String clave = System.getenv("DB_PASSWORD");
        if (url == null || usuario == null || clave == null) {
            throw new IllegalStateException(
                "Configurá DB_URL, DB_USER y DB_PASSWORD");
        }
        return DriverManager.getConnection(url, usuario, clave);
    }
}