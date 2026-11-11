import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class ArchivoDemo {
    public static void main(String[] args) {
        Path archivo = Path.of("datos", "alumnos.txt");
        try {
            Files.createDirectories(archivo.getParent());
            if (args.length > 0 && args[0].equals("agregar")) {
                if (args.length != 2 || args[1].trim().isEmpty()) {
                    System.out.println("Uso: java ArchivoDemo agregar nombre");
                    return;
                }
                Files.writeString(archivo, args[1].trim() + System.lineSeparator(),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                System.out.println("Guardado en " + archivo.toAbsolutePath());
            } else {
                // Solo se captura la ausencia; otros errores se informan.
                try {
                    System.out.print(Files.readString(archivo, StandardCharsets.UTF_8));
                } catch (NoSuchFileException e) {
                    System.out.println("Todavía no hay un archivo de alumnos");
                }
            }
        } catch (IOException e) {
            System.err.println("No se pudo acceder al archivo");
            e.printStackTrace();
        }
    }
}