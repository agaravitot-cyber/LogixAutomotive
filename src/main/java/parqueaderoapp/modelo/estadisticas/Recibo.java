package parqueaderoapp.modelo.estadisticas;

import parqueaderoapp.main.App;
import parqueaderoapp.modelo.persona.Empleado;
import parqueaderoapp.modelo.vehiculo.Vehiculo;

public class Recibo {
    private static int contador = 0;
    private final String id;
    private final String placaCopia;
    private final String tipoCopia;
    private final String fechaEntrada;
    private final String fechaSalida;
    private final String nombreEmpleado;
    private final double base;
    private final double monto;

    public Recibo(Vehiculo v, Empleado e, double monto, double base) {
        this.id = generarId();
        this.placaCopia = v.getPlaca();
        this.tipoCopia = v.getTipo();
        this.fechaEntrada = v.getEntrada();
        this.fechaSalida = v.getSalida();
        this.nombreEmpleado = e.getNombre();
        this.base = base;
        this.monto = monto;
    }

    public String generarId() {
        return Integer.toHexString(contador++).toUpperCase();
    }

    public double getMonto() {
        return monto;
    }

    @Override
    public String toString() {
        return App.getParqueadero().getNombre() +
                "\nRecibo #" + id +
                "\nEmpleado encargado: " + "(" + nombreEmpleado + ")" +
                "\nVehículo: " + placaCopia + " - " + tipoCopia +
                "\nEntrada: " + fechaEntrada +
                "\nSalida: " + fechaSalida +
                "\nTotal: $" + monto +
                "\nBase: $" + base +
                "\nIVA: $" + (base * 0.19);
    }

    public String getTipoCopia() {
        return tipoCopia;
    }
}