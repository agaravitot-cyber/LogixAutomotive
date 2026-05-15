package parqueaderoapp.modelo.persona;

import parqueaderoapp.modelo.estadisticas.Recibo;
import parqueaderoapp.modelo.parqueadero.*;

public class Administrador extends Empleado {
    public Administrador(String nombreUser, long documentoUser, String emailUser, String pass, Parqueadero p) {
        super(nombreUser, documentoUser, emailUser, pass, p);

    }

    public void cambiarTarifa(int tarifa, String tipo) {
        parqueaderoApp.setTarifa(tarifa, tipo);
    }

    public void agregarEmpleado(Empleado e) {
        parqueaderoApp.agregarEmpleado(e);
    }

    public void quitarEmpleado(Empleado e){
        parqueaderoApp.quitarEmpleado(e);
    }

    public void agregarPlanta(Planta p){
        parqueaderoApp.agregarPlanta(p);
    }
    public void agregarCelda(Planta p ,Celda c){
        p.agregar(c);
    }

    public void quitarPlanta(Planta p){
        parqueaderoApp.quitarPlanta(p);
    }
    public void quitarCelda(Planta p, Celda c){
        p.quitar(c);
    }

    public double registroIngreso(){
        double total = 0;
        for(Recibo r : parqueaderoApp.listaRecibo()){
            total += r.getMonto();
        }
        return total;
    }
}
