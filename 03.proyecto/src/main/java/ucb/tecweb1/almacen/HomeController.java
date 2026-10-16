package ucb.tecweb1.almacen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springfr
import org.springframework.web.bind.annotation.RestController;

import tools.jackson.databind.json.JsonMapper;
import ucb.tecweb1.lib.DB;

@RestController
public class HomeController {

    @GetMapping("/")
    public String index() {
        Connection conn = DB.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from almacen.catalogo");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }
        } catch (Exception e) {
            System.out.println("Error al ejecutar la consulta");
            e.printStackTrace();
        }
        // Realizar operaciones con la conexión
        return "Hola";
    }

    public record Producto(int id, String nombre) {
    }

    @GetMapping(value = "/json", produces = "application/json")
    public String indexJson() {
        List<Producto> catalogo = new ArrayList<>();
        Connection conn = DB.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from almacen.catalogo");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Producto p = new Producto(rs.getInt("id"), rs.getString("nombre"));
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
}
