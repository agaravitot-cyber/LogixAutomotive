package parqueaderoapp.modelo.persona;

public abstract class Persona {
    protected String nombreUser;
    protected int documentoUser;

    public Persona(String nombre, int documento) {
        this.nombreUser = nombre;
        this.documentoUser = documento;
    }

    public abstract void generarRecibo();

    public abstract void verMapa();

    protected double calcFactura(int tarifa, int minutos) {
        return tarifa * minutos;
    }
}
