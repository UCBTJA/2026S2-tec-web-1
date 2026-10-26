package ucb.tecweb.model;

import java.util.List;

public class ProductoService {
    private ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public boolean crearProducto(String nombre) {
        int id = this.productoRepository.nextIdentity();
        Producto producto = new Producto(id, nombre);
        if (producto.isValid()) {
            this.productoRepository.store(producto);
            return true;
        }
        return false;
    }

    public List<ProductoDTO> search(String filtro) {
        return this.productoRepository.search(filtro);
    }
}
