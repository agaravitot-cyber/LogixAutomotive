package parqueaderoapp.modelo.persona;

import java.time.Duration;
import java.time.LocalDateTime;

import parqueaderoapp.modelo.parqueadero.Parqueadero;
import parqueaderoapp.modelo.vehiculo.Vehiculo;

public class Cliente extends Persona {

    private Vehiculo vehiculo;
    public Cliente(String nombreUser, int documentoUser, Parqueadero p, Vehiculo vehiculo) {
        super(nombreUser, documentoUser, p);
        this.vehiculo = vehiculo;
    }

    @Override
    public void generarRecibo(Vehiculo v) {
        Duration duracion = Duration.between(vehiculo.getEntrada(this), LocalDateTime.now());
        double monto = parqueadero.getTarifa(v.getTipo()) * duracion.toMinutes();
    }

    @Override
    public void verMapa() {

    }
}
