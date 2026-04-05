package parqueaderoapp.modelo.persona;

public abstract class Persona {
    protected String nombreUser;
    protected int documentoUser;
    protected String emailUser;

    public Persona(String nombre, int documento, String email) {
        this.nombreUser = nombre;
        this.documentoUser = documento;
        this.emailUser = email;
    }

    public abstract void generarRecibo();

    public abstract void verMapa();

    protected double calcFactura(int tarifa, int minutos) {
        return tarifa * minutos;
    }
}
