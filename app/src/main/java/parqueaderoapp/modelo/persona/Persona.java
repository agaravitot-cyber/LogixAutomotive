package parqueaderoapp.modelo.persona;

public abstract class Persona {
    protected String nombreUser;
    protected long documentoUser;

    public Persona(String nombre, long documento) {
        this.nombreUser = nombre;
        this.documentoUser = documento;
    }

    public abstract void generarRecibo();

    public abstract void verMapa();

}
