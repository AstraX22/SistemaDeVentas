
package sistemasdeventa.entiidadesdenegicios;
import java.util.ArrayList;

public class Producto {
    
    private int id;
    private int idCategoria;
    private int idUsuario;
    private String nombre;
    private double precio;
    private int top_aux;
    private Categoria categoria;
    private Usuario usuario;

    public Producto() {
    }    
   
    public Producto(int id, int idCategoria, int idUsuario, String nombre, double precio, int top_aux, Categoria categoria, Usuario usuario) {
        this.id = id;
        this.idCategoria = idCategoria;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.precio = precio;
        this.top_aux = top_aux;
        this.categoria = categoria;
        this.usuario = usuario;
    }
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}