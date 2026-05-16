package parqueaderoapp.modelo.persona;

import parqueaderoapp.modelo.estadisticas.Recibo;
import parqueaderoapp.modelo.parqueadero.Celda;
import parqueaderoapp.modelo.parqueadero.Parqueadero;
import parqueaderoapp.modelo.vehiculo.Vehiculo;

public class Empleado extends Persona {
    protected String emailUser;
    protected String contrasena;

    public Empleado(String nombreUser, long documentoUser, String emailUser, String pass, Parqueadero p) {
        super(nombreUser, documentoUser, p);
        this.emailUser = emailUser;
        contrasena = pass;
        parqueadero = p;
    }

    public void registrarEntrada(Celda c, Vehiculo v) {
        if (c.asignar(v)) {
            v.registroEntrada();
        } else {
            System.out.println("Celda ocupada");
        }
    }

    public void registroSalida(Vehiculo v) {
        v.registrarSalida();
        generarRecibo(v);
        parqueadero.buscarCelda(v).desocupar();
    }

    @Override
    public void generarRecibo(Vehiculo v) {
        double monto = parqueadero.calcFactura(v);
        parqueadero.agregarRecibo(new Recibo(v, v.getConductor(), monto));
    }

    public String getPass() {
        return contrasena;
    }

    public String getEmail() {
        return emailUser;
    }

    @Override
    public void verMapa() {
    }
}
