package parqueaderoapp.modelo.parqueadero;

import parqueaderoapp.modelo.vehiculo.Vehiculo;

public class Celda {
    private boolean ocupado;
    private Vehiculo vehiculo;
    private final String tipoCelda;
    private final String ubicacion;

    public Celda(String tipo, String ubicacion) {
        this.vehiculo = null;
        this.ocupado = false;
        this.tipoCelda = tipo;
        this.ubicacion = ubicacion;
    }

    public boolean asignar(Vehiculo v) {
        if (vehiculo != null)
            return false;
        if (!v.getTipo().equalsIgnoreCase(tipoCelda))
            return false;
        this.vehiculo = v;
        this.ocupado = true;
        return true;
    }

    public void desocupar() {
        this.vehiculo = null;
        this.ocupado = false;
    }

    public boolean isOcupada() {
        return ocupado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public String getUbicacion() {
        return ubicacion;
    }
}
