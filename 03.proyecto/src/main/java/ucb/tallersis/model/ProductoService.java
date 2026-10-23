package ucb.tallersis.model;

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
}
