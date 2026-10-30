package ucb.tecweb.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springfr
import org.springframework.web.bind.annotation.RestController;

import ucb.tecweb.endpoints.ProductoJsonPresenter;
import ucb.tecweb.infrastructure.pg.PgProductoRepository;
import ucb.tecweb.model.producto.ProductoDTO;
import ucb.tecweb.model.producto.ProductoRepository;
import ucb.tecweb.model.producto.ProductoService;
import ucb.tecweb.view.ProductoPresenter;

@RestController
public class ProductoController {
    private ProductoService service;

    public ProductoController() {
        ProductoRepository repo = new PgProductoRepository();
        this.service = new ProductoService(repo);
    }

    @GetMapping("/")
    public String index() {
        ProductoPresenter presenter = new ProductoPresenter();
        List<ProductoDTO> catalogo = this.service.search("");
        return presenter.index(catalogo);
    }

    @GetMapping(value = "/json", produces = "application/json")
    public String indexJson() {
        ProductoJsonPresenter presenter = new ProductoJsonPresenter();
        List<ProductoDTO> catalogo = this.service.search("");
        return presenter.index(catalogo);
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
