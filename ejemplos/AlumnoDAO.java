import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {
    public int insertar(String nombre) throws SQLException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }
        String sql = "INSERT INTO alumno(nombre) VALUES (?)";
        try (Connection c = Conexion.abrir();
             PreparedStatement ps = c.prepareStatement(
                 sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nombre.trim());
            ps.executeUpdate();
            try (ResultSet claves = ps.getGeneratedKeys()) {
                if (claves.next()) { return claves.getInt(1); }
                throw new SQLException("No se recibió el id generado");
            }
        }
    }

    public List<Alumno> listar() throws SQLException {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT id, nombre FROM alumno ORDER BY id";
        try (Connection c = Conexion.abrir();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                alumnos.add(new Alumno(
                    rs.getInt("id"), rs.getString("nombre")));
            }
        }
        return alumnos;
    }

    public int actualizar(int id, String nombre) throws SQLException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }
        String sql = "UPDATE alumno SET nombre = ? WHERE id = ?";
        try (Connection c = Conexion.abrir();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nombre.trim());
            ps.setInt(2, id);
            return ps.executeUpdate();
        }
    }

    public int eliminar(int id) throws SQLException {
        String sql = "DELETE FROM alumno WHERE id = ?";
        try (Connection c = Conexion.abrir();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }
}