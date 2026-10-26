package ucb.tecweb.model;

import java.util.List;

public interface ProductoRepository {
    public Producto getByID(int id);

    public boolean store(Producto producto);

    public boolean delete(int ID);

    public int nextIdentity();

    public List<ProductoDTO> search(String filtro);
}
