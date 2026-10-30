package ucb.tecweb.model.producto;

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

    public boolean actualizarProducto(int id, String nombre) {
        Producto producto = this.productoRepository.getByID(id);
        producto.setNombre(nombre);
        if (producto.isValid()) {
            this.productoRepository.store(producto);
            return true;
        }
        return false;
    }

    public boolean eliminarProducto(int id) {
        Producto producto = this.productoRepository.getByID(id);
        return this.productoRepository.delete(producto.getID());
    }

}
