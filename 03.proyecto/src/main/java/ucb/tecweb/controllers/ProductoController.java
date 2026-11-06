package ucb.tecweb.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ucb.tecweb.infrastructure.pg.PgProductoRepository;
import ucb.tecweb.model.producto.ProductoDTO;
import ucb.tecweb.model.producto.ProductoRepository;
import ucb.tecweb.model.producto.ProductoService;

@Controller
public class ProductoController {
    private ProductoService service;

    public ProductoController() {
        ProductoRepository repo = new PgProductoRepository();
        this.service = new ProductoService(repo);
    }

    @GetMapping("/productos")
    public String index(Model model) {
        List<ProductoDTO> catalogo = this.service.search("");
        model.addAttribute("productos", catalogo);
        return "productos/index"; // -> templates/productos/index.html
    }

    @GetMapping("/productos/create")
    public String createForm() {
        return "productos/create"; // -> templates/productos/create.html
    }

    @PostMapping("/productos/create")
    public ResponseEntity<Void> create(@RequestParam String nombre) {
        this.service.crearProducto(nombre);
        return redirectToIndex();
    }

    @GetMapping("/productos/update")
    public String updateForm(@RequestParam int id, Model model) {
        ProductoDTO producto = this.service.getProductoById(id);
        if (producto == null) {
            return "redirect:/productos";
        }
        model.addAttribute("producto", producto);
        return "productos/update"; // -> templates/productos/update.html
    }

    @PostMapping("/productos/update")
    public ResponseEntity<Void> update(@RequestParam int id, @RequestParam String nombre) {
        this.service.actualizarProducto(id, nombre);
        return redirectToIndex();
    }

    @PostMapping("/productos/delete")
    public ResponseEntity<Void> delete(@RequestParam int id) {
        this.service.eliminarProducto(id);
        return redirectToIndex();
    }

    private ResponseEntity<Void> redirectToIndex() {
        return ResponseEntity.status(HttpStatus.SEE_OTHER).location(java.net.URI.create("/productos")).build();
    }
}
