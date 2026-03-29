package parqueaderoapp.modelo.persona;

public abstract class Persona {
    protected String nombreUser;
    protected int documentoUser;
    
    public Persona(String nombre, int documento){
        this.nombreUser = nombre;
        this.documentoUser = documento;
    }
    
}
