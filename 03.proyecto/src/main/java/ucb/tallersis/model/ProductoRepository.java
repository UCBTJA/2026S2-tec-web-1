package ucb.tallersis.model;

public interface ProductoRepository {
    public Producto getByID(int id);

    public boolean store(Producto producto);

    public boolean delete(int ID);

    public int nextIdentity();

    public ProductoDTO[] search(String filtro);
}
