package parqueaderoapp.modelo.parqueadero;

import java.util.ArrayList;
import java.util.List;

import parqueaderoapp.modelo.interfaces.*;
import parqueaderoapp.modelo.persona.Administrador;
import parqueaderoapp.modelo.persona.Empleado;

public class Parqueadero implements Gestionar<Planta>{
    private String nombreNegocio;
    private int tarifa;
    private List<Planta> plantas;
    private List<Empleado> personal;

    public Parqueadero(int nTarifa, String nombre) {
        this.tarifa = nTarifa;
        this.nombreNegocio = nombre;
        plantas = new ArrayList<>();
        personal = new ArrayList<>();
        agregar(new Planta());
    }

    public void setTarifa(int n) {
        this.tarifa = n;
    }
    public void setNombre(String nuevoNombre) {
        this.nombreNegocio = nuevoNombre;
    }

    public int getTarifa() {
        return this.tarifa;
    }

    public String getNombre() {
        return this.nombreNegocio;
    }

    public int getPisos() {
        return plantas.size();
    }

    public Planta getPlanta(int indice) {
        return plantas.get(indice);
    }

    public void crearAdmin(int documento, String Nombre, String email, String contrasena) {
        Administrador temp = new Administrador(Nombre, documento, email, contrasena);
        personal.add(temp);
    }

    public void agregar(Empleado e) {
        personal.add(e);
    }

    @Override
    public void agregar(Planta p) {
        plantas.add(p);
    }
    @Override
    public void quitar(Planta p) {
        plantas.remove(p);
    }

    public void quitar(int indice, List<Empleado> e){
        if (indice >= 0 && indice < plantas.size()) {
            plantas.remove(indice);
        } else {
            System.out.println("Índice inválido, no existe este empleado.");
        }

    }
    public List<Empleado> getEmpleados(){
        return this.personal;
    }
    public double calcFactura(int minutos) {
        return tarifa * minutos;
    }
}
