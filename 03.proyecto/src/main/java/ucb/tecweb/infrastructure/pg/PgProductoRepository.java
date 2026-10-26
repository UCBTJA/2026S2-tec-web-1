package ucb.tecweb.infrastructure.pg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;

import ucb.tecweb.lib.DB;
import ucb.tecweb.model.Producto;
import ucb.tecweb.model.ProductoDTO;
import ucb.tecweb.model.ProductoRepository;

public class PgProductoRepository implements ProductoRepository {
    public Producto getByID(int id) {
        Connection conn = DB.getConnection();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT id, nombre)")
                    .append("FROM almacen.producto")
                    .append("WHERE id = ?");
            PreparedStatement stmt = conn.prepareStatement(sb.toString());
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Producto(rs.getInt("id"), rs.getString("nombre"));
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar el producto");
            e.printStackTrace();
        }
        return null;
    }

    public boolean store(Producto producto) {
        Connection conn = DB.getConnection();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO almacen.producto(id, nombre)")
                    .append("Values(?, ?)")
                    .append("ON CONFLICT(id)")
                    .append("DO UPDATE SET nombre=EXCLUDED.nombre");
            PreparedStatement stmt = conn.prepareStatement(sb.toString());
            stmt.setInt(1, producto.getID());
            stmt.setString(2, producto.getNombre());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar el producto");
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int ID) {
        Connection conn = DB.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("delete from almacen.producto where id = ?");
            stmt.setLong(1, ID);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar el producto");
            e.printStackTrace();
        }
        return false;
    }

    public int nextIdentity() {
        Connection conn = DB.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "select nextval('producto_id_seq')");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error al ejecutar la consulta");
            e.printStackTrace();
        }
        return -1;
    }

    public List<ProductoDTO> search(String filtro) {
        List<ProductoDTO> catalogo = new ArrayList<>();
        Connection conn = DB.getConnection();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("select * from almacen.producto");
            PreparedStatement stmt = conn.prepareStatement(sb.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ProductoDTO p = new ProductoDTO(rs.getInt("id"), rs.getString("nombre"));
                catalogo.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error al ejecutar la consulta");
            e.printStackTrace();
        }
        return catalogo;
    }
}
