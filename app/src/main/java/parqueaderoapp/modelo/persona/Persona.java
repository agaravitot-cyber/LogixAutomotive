package parqueaderoapp.modelo.persona;

import parqueaderoapp.modelo.vehiculo.Vehiculo;

public abstract class Persona {
    protected String nombreUser;
    protected final long documentoUser;

    public Persona(String nombre, long documento) {
        this.nombreUser = nombre;
        this.documentoUser = documento;
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
