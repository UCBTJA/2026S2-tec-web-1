package ucb.tecweb.endpoints;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tools.jackson.databind.json.JsonMapper;
import ucb.tecweb.infrastructure.pg.PgProductoRepository;
import ucb.tecweb.model.producto.ProductoDTO;
import ucb.tecweb.model.producto.ProductoRepository;
import ucb.tecweb.model.producto.ProductoService;

@RestController
public class ProductoEndpoint {
    private ProductoService service;

    public ProductoEndpoint() {
        ProductoRepository repo = new PgProductoRepository();
        this.service = new ProductoService(repo);
    }

    @GetMapping(value = "/api/productos", produces = "application/json")
    public String index() {
        List<ProductoDTO> catalogo = this.service.search("");
        return JsonMapper.builder().build().writeValueAsString(catalogo);
    }

    /**
     * curl -X POST http://localhost:8080/api/productos \
     * -H "Content-Type: application/json" \
     * -d '{"nombre":"Tornillo"}'
     */
    @PostMapping(value = "/api/productos", consumes = "application/json")
    public ResponseEntity<Void> create(@RequestBody ProductoDTO producto) {
        boolean creado = this.service.crearProducto(producto.nombre());
        if (!creado) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/api/productos/{id}", produces = "application/json")
    public ResponseEntity<String> getById(@PathVariable int id) {
        ProductoDTO producto = this.service.getProductoById(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(JsonMapper.builder().build().writeValueAsString(producto));
    }

    @PutMapping(value = "/api/productos/{id}", consumes = "application/json")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody ProductoDTO producto) {
        if (this.service.getProductoById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        boolean actualizado = this.service.actualizarProducto(id, producto.nombre());
        if (!actualizado) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/productos/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (this.service.getProductoById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        boolean eliminado = this.service.eliminarProducto(id);
        if (!eliminado) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();
    }
}
