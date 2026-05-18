package parqueaderoapp.modelo.persona;

import parqueaderoapp.modelo.vehiculo.Vehiculo;

public class Cliente extends Persona {

    private Vehiculo vehiculo;
    public Cliente(String nombreUser, int documentoUser, Vehiculo vehiculo) {
        super(nombreUser, documentoUser);
        this.vehiculo = vehiculo;
    }
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
}
