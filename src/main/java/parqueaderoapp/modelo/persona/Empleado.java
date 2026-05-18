package parqueaderoapp.modelo.persona;

import parqueaderoapp.modelo.estadisticas.Recibo;
import parqueaderoapp.modelo.parqueadero.Celda;
import parqueaderoapp.modelo.vehiculo.Vehiculo;

public class Empleado extends Persona {
    protected String emailUser;
    protected String contrasena;

    public Empleado(String nombreUser, long documentoUser, String emailUser, String pass) {
        super(nombreUser, documentoUser);
        this.emailUser = emailUser;
        contrasena = pass;
    }

    public boolean registrarEntrada(Celda c, Vehiculo v) {
        if (c.asignar(v)) {
            v.registroEntrada();
            return true;
        } else {
            return false;
        }
    }

    public void registroSalida(Vehiculo v) {
        v.registrarSalida();
        generarRecibo(v);
        parqueadero.buscarCelda(v).desocupar();
    }

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

}
