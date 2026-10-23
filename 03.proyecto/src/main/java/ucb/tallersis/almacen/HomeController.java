package ucb.tallersis.almacen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springfr
import org.springframework.web.bind.annotation.RestController;

import tools.jackson.databind.json.JsonMapper;
import ucb.tallersis.lib.DB;
import ucb.tallersis.model.ProductoDTO;

@RestController
public class HomeController {

    private String buildMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>");
        sb.append("<li><a href='/'>Inicio</a></li>");
        sb.append("<li><a href='/create.html'>Crear</a></li>");
        sb.append("<li><a href='/json'>JSON</a></li>");
        sb.append("</ul>");
        return sb.toString();
    }

    @GetMapping("/")
    public String index() {
        Connection conn = DB.getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("<html>")
                .append("<head>")
                .append("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB' crossorigin='anonymous'>")
                .append("<head>")
                .append("<body><h1>Productos</h1><ul>");

        sb.append(buildMenu());
        sb.append("<table class='table table-striped table-bordered'><tr><th>ID</th><th>Nombre</th></tr>");
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "select * from almacen.producto");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sb.append("<tr><td>")
                        .append(rs.getInt("id"))
                        .append("</td><td>")
                        .append(rs.getString("nombre"))
                        .append("</td></tr>");
            }
            sb.append("</table>");
        } catch (Exception e) {
            System.out.println("Error al ejecutar la consulta");
            e.printStackTrace();
        }
        sb.append("</body></html>");
        return sb.toString();
    }

    @GetMapping(value = "/json", produces = "application/json")
    public String indexJson() {
        List<ProductoDTO> catalogo = new ArrayList<>();
        Connection conn = DB.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from almacen.producto");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ProductoDTO p = new ProductoDTO(rs.getInt("id"), rs.getString("nombre"));
                catalogo.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error al ejecutar la consulta");
            e.printStackTrace();
        }

        return JsonMapper.builder().build().writeValueAsString(catalogo);
    }

    @PostMapping("/saludo")
    public String greeting(@RequestParam String name, @RequestParam int edad) {
        return "Hola " + name + ", tienes " + edad + " años";
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestParam String nombre) {
        Connection conn = DB.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into almacen.producto (nombre) values (?)");
            stmt.setString(1, nombre);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al insertar el producto");
            e.printStackTrace();
        }
        return redirectToIndex();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestParam long id, @RequestParam String nombre) {
        Connection conn = DB.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("update almacen.producto set nombre = ? where id = ?");
            stmt.setString(1, nombre);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar el producto");
            e.printStackTrace();
        }
        return redirectToIndex();
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam long id) {
        Connection conn = DB.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("delete from almacen.producto where id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al eliminar el producto");
            e.printStackTrace();
        }
        return redirectToIndex();
    }

    private ResponseEntity<Void> redirectToIndex() {
        return ResponseEntity.status(HttpStatus.SEE_OTHER).location(java.net.URI.create("/")).build();
    }
}
