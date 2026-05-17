package parqueaderoapp.modelo.parqueadero;

import java.util.ArrayList;

import parqueaderoapp.modelo.estadisticas.Recibo;
import parqueaderoapp.modelo.persona.Administrador;
import parqueaderoapp.modelo.persona.Empleado;
import parqueaderoapp.modelo.vehiculo.Vehiculo;

public class Parqueadero {
    private String nombreNegocio;
    private int tarifaC;
    private int tarifaM;
    private int tarifaB;
    private ArrayList<Planta> plantas;
    private ArrayList<Empleado> personal;
    private ArrayList<Recibo> recibos;

    public Parqueadero(String nombre, int c, int m, int b) {
        tarifaB = b;
        tarifaC = c;
        tarifaM = m;
        this.nombreNegocio = nombre;
        plantas = new ArrayList<>();
        personal = new ArrayList<>();
    }

    public void setTarifa(int n, String tipo) {
        switch (tipo) {
            case "Carro":
                tarifaC = n;
                break;
            case "Moto":
                tarifaM = n;
                break;
            case "Bici":
                tarifaB = n;
                break;
            default:
                break;
        }
    }

    public void setNombre(String nuevoNombre) {
        this.nombreNegocio = nuevoNombre;
    }

    public int getTarifa(String tipo) {
        switch (tipo) {
            case "Carro":
                return tarifaC;
            case "Moto":
                return tarifaM;
            case "Bici":
                return tarifaB;
            default:
                return 0;
        }
    }

    public String getNombre() {
        return this.nombreNegocio;
    }

    public int getNumPisos() {
        return plantas.size();
    }

    public ArrayList<Planta> listaPiso() {
        return plantas;
    }

    public Planta getPlanta(int indice) {
        return plantas.get(indice);
    }

    public void crearAdmin(Administrador admin) {
        personal.add(admin);
    }

    public boolean agregarRecibo(Recibo r) {
        if (r == null)
            return false;

        recibos.add(r);
        return true;
    }

    public boolean eliminarRecibo(Recibo r) {
        if (r == null)
            return false;

        recibos.remove(r);
        return true;
    }

    public void resetRecibos() {
        recibos.clear();
    }

    public boolean agregarEmpleado(Empleado e) {
        if (e == null)
            return false;

        personal.add(e);
        return true;
    }

    public boolean agregarPlanta(Planta p) {
        if (p == null)
            return false;

        plantas.add(p);
        return true;
    }

    public boolean quitarPlanta(Planta p) {
        if (p == null)
            return false;

        plantas.add(p);
        return true;
    }

    public boolean quitarEmpleado(Empleado e) {
        if (e == null)
            return false;

        personal.remove(e);
        return true;
    }

    public ArrayList<Empleado> getEmpleados() {
        return this.personal;
    }

    public Celda buscarCelda(Vehiculo v) {
        for (Planta p : plantas) {
            for (Celda celda : p.listaCelda()) {
                if (celda.isOcupada() && celda.getVehiculo().equals(v))
                    return celda;
            }
        }
        return null;
    }

    public double calcFactura(Vehiculo v) {

        return ("Carro".equalsIgnoreCase(v.getTipo()) ? tarifaC
                : "Moto".equalsIgnoreCase(v.getTipo()) ? tarifaM : tarifaB) * v.getEstadia();
    }

    public ArrayList<Recibo> listaRecibo(){
        return recibos;
    }
}
