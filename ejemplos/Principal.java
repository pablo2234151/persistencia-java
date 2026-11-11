import java.sql.SQLException;

public class Principal {
    public static void main(String[] args) {
        AlumnoDAO dao = new AlumnoDAO();
        try {
            if (args.length == 0 || args[0].equals("listar")) {
                for (Alumno a : dao.listar()) {
                    System.out.println(a.getId() + " - " + a.getNombre());
                }
            } else if (args[0].equals("insertar") && args.length == 2) {
                int id = dao.insertar(args[1]);
                System.out.println("Insertado, id: " + id);
            } else if (args[0].equals("actualizar") && args.length == 3) {
                int filas = dao.actualizar(Integer.parseInt(args[1]), args[2]);
                System.out.println("Filas informadas: " + filas);
            } else if (args[0].equals("eliminar") && args.length == 2) {
                int filas = dao.eliminar(Integer.parseInt(args[1]));
                System.out.println("Eliminados: " + filas);
            } else {
                System.out.println("Usos: listar | insertar nombre | "
                    + "actualizar id nombre | eliminar id");
            }
        } catch (NumberFormatException e) {
            System.err.println("El id debe ser un entero");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            System.err.println("Falló la operación de base de datos");
            e.printStackTrace();
        }
    }
}