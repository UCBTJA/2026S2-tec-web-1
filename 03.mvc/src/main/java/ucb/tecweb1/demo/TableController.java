package ucb.tecweb1.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TableController {

    private record Usuario(int id, String nombre, String email, String rol) {}

    private final List<Usuario> usuarios = List.of(
            new Usuario(1, "Ana Pérez", "ana.perez@example.com", "Administrador"),
            new Usuario(2, "Luis Gómez", "luis.gomez@example.com", "Editor"),
            new Usuario(3, "María Torres", "maria.torres@example.com", "Lector"),
            new Usuario(4, "Jorge Flores", "jorge.flores@example.com", "Editor"),
            new Usuario(5, "Carla Rojas", "carla.rojas@example.com", "Lector")
    );

    @GetMapping("/tabla")
    public String tabla() {
        StringBuilder filas = new StringBuilder();
        for (Usuario u : usuarios) {
            filas.append("<tr>")
                    .append("<td>").append(u.id()).append("</td>")
                    .append("<td>").append(u.nombre()).append("</td>")
                    .append("<td>").append(u.email()).append("</td>")
                    .append("<td>").append(u.rol()).append("</td>")
                    .append("</tr>");
        }

        return "<!DOCTYPE html>"
                + "<html lang=\"es\">"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Tabla de usuarios</title>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; margin: 2rem; background-color: #f5f5f5; }"
                + "h1 { color: #333; }"
                + "table { border-collapse: collapse; width: 100%; background-color: #fff; box-shadow: 0 1px 3px rgba(0,0,0,0.1); }"
                + "th, td { border: 1px solid #ddd; padding: 0.6rem 1rem; text-align: left; }"
                + "th { background-color: #2c3e50; color: #fff; }"
                + "tr:nth-child(even) { background-color: #f9f9f9; }"
                + "tr:hover { background-color: #eef6ff; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<h1>Tabla de usuarios de ejemplo</h1>"
                + "<table>"
                + "<thead><tr><th>ID</th><th>Nombre</th><th>Email</th><th>Rol</th></tr></thead>"
                + "<tbody>" + filas + "</tbody>"
                + "</table>"
                + "</body>"
                + "</html>";
    }
}
