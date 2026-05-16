package parqueaderoapp.modelo.persona;

import java.lang.reflect.Parameter;

import parqueaderoapp.modelo.parqueadero.Parqueadero;
import parqueaderoapp.modelo.vehiculo.Vehiculo;

public abstract class Persona {
    protected String nombreUser;
    protected final long documentoUser;
    protected Parqueadero parqueadero;
    public Persona(String nombre, long documento, Parqueadero parqueadero) {
        this.nombreUser = nombre;
        this.documentoUser = documento;
        this.parqueadero = parqueadero;
    }

    public abstract void generarRecibo(Vehiculo v);

    public abstract void verMapa();

    public String getNombre() {
        return nombreUser;
    }

    public String getDocumento() {
        return Long.toString(documentoUser);
    }
}
