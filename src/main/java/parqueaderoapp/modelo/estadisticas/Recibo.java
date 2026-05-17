package parqueaderoapp.modelo.estadisticas;

import parqueaderoapp.modelo.persona.Cliente;
import parqueaderoapp.modelo.vehiculo.Vehiculo;

public class Recibo {
    private int  contador = 0;
    private final String id;
    private final String placaCopia;
    private final String tipoCopia;
    private final String nombreCliente;
    private final String documentoCliente;
    private final String fechaEntrada;
    private final String fechaSalida;
    private final double monto;

    public Recibo(Vehiculo v, Cliente c, double monto){
        this.id = generarId();
        this.placaCopia = v.getPlaca();
        this.tipoCopia = v.getTipo();
        this.nombreCliente = c.getNombre();
        this.documentoCliente = c.getDocumento();
        this.fechaEntrada = v.getEntrada();
        this.fechaSalida = v.getSalida();
        this.monto = monto;
    }

    public String generarId(){
        return Integer.toHexString(contador).toUpperCase();
    }

    public double getMonto() {
        return monto;
    }

    public String generarTexto() {
        return "Recibo #" + id +
               "\nCliente: " + nombreCliente + " (" + documentoCliente + ")" +
               "\nVehículo: " + placaCopia + " - " + tipoCopia +
               "\nEntrada: " + fechaEntrada +
               "\nSalida: " + fechaSalida +
               "\nMonto: $" + monto;
    }
}