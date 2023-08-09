package sistemasdeventa.entiidadesdenegicios;

import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private String Id;
    private int top_aux;
    
    
    
    //Constructor
    public Usuario() {
    }
     
    public Usuario(String nombre, String Id, int top_aux) {
        this.nombre = nombre;
        this.Id = Id;
        this.top_aux = top_aux;
    }
    //Getter y setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    } 
}

   