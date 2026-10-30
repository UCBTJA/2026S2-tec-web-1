package ucb.tecweb.model.producto;

public class Producto {

    private int ID;
    private String nombre;

    public Producto(int ID, String nombre) {
        this.ID = ID;
        this.nombre = nombre;
    }

    public boolean isValid() {
        return this.nombre.length() <= 100;
    }

    public int getID() {
        return this.ID;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
