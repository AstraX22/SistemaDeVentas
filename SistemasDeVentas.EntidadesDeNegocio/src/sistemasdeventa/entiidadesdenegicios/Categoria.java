package sistemasdeventa.entiidadesdenegicios;
public class Categoria {
    
    private int id;
    private String nombre;
    private int top_aux;

    public Categoria(int id, String nombre, int top_aux) {
        this.id = id;
        this.nombre = nombre;
        this.top_aux = top_aux;
    }

    /**
     *
     */
    public Categoria() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
    
    
}