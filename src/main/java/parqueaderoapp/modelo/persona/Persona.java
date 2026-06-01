package parqueaderoapp.modelo.persona;

import parqueaderoapp.main.App;
import parqueaderoapp.modelo.parqueadero.Parqueadero;

public abstract class Persona {
    protected String nombreUser;
    protected final long documentoUser;
    protected Parqueadero parqueadero;

    public Persona(String nombre, long documento) {
        this.nombreUser = nombre;
        this.documentoUser = documento;
        setParqueadero(App.getParqueadero());
    }

    public void setParqueadero(Parqueadero parqueadero){
        this.parqueadero = parqueadero;
    }

    public String getNombre() {
        return nombreUser;
    }

    public String getDocumento() {
        return Long.toString(documentoUser);
    }
}
