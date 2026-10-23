package ucb.tallersis.infrastructure.pg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ucb.tallersis.lib.DB;
import ucb.tallersis.model.Producto;
import ucb.tallersis.model.ProductoDTO;
import ucb.tallersis.model.ProductoRepository;

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
            PreparedStatement stmt = conn.prepareStatement("select nextval('producto_id_seq') as id");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println("Error al ejecutar la consulta");
            e.printStackTrace();
        }
        return -1;
    }

    public ProductoDTO[] search(String filtro) {

    }
}
