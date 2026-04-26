package parqueaderoapp.modelo.persona;

import parqueaderoapp.modelo.parqueadero.*;

public class Administrador extends Empleado {
    public Administrador(String nombreUser, long documentoUser, String emailUser, String pass) {
        super(nombreUser, documentoUser, emailUser, pass);

    }

    public void cambiarTarifa(Parqueadero parqueadero, int tarifa) {
        parqueadero.setTarifa(tarifa);
    }
    public void agregarTrabajador(Parqueadero parqueadero, Empleado e){
        parqueadero.agregar(e);
    }
}
