package ucb.tecweb.almacen;

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
import ucb.tecweb.infrastructure.pg.PgProductoRepository;
import ucb.tecweb.lib.DB;
import ucb.tecweb.model.ProductoDTO;
import ucb.tecweb.model.ProductoRepository;
import ucb.tecweb.model.ProductoService;
import ucb.tecweb.view.ProductoPresenter;

@RestController
public class HomeController {

    @GetMapping("/")
    public String index() {
        ProductoRepository repo = new PgProductoRepository();
        ProductoService service = new ProductoService(repo);
        ProductoPresenter presenter = new ProductoPresenter();

        List<ProductoDTO> catalogo = service.search("");

        return presenter.index(catalogo);
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
        ProductoRepository repo = new PgProductoRepository();
        ProductoService service = new ProductoService(repo);

        service.crearProducto(nombre);
        return redirectToIndex();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestParam int id, @RequestParam String nombre) {
        ProductoRepository repo = new PgProductoRepository();
        ProductoService service = new ProductoService(repo);
        service.actualizarProducto(id, nombre);
        return redirectToIndex();
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam int id) {
        ProductoRepository repo = new PgProductoRepository();
        ProductoService service = new ProductoService(repo);
        service.eliminarProducto(id);
        return redirectToIndex();
    }

    private ResponseEntity<Void> redirectToIndex() {
        return ResponseEntity.status(HttpStatus.SEE_OTHER).location(java.net.URI.create("/")).build();
    }
}
