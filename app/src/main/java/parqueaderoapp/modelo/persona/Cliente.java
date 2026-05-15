package parqueaderoapp.modelo.persona;

import parqueaderoapp.modelo.vehiculo.Vehiculo;

public class Cliente extends Persona {

    private Vehiculo vehiculo;
    public Cliente(String nombreUser, int documentoUser) {
        super(nombreUser, documentoUser);
        this.vehiculo = vehiculo;
    }

    @Override
    public void generarRecibo(Vehiculo v) {
        vehiculo.get
    }

    @Override
    public void verMapa() {

    }
}
