package parqueaderoapp.modelo.parqueadero;

import java.util.ArrayList;
import java.util.List;

import parqueaderoapp.modelo.interfaces.*;
import parqueaderoapp.modelo.persona.Administrador;
import parqueaderoapp.modelo.persona.Empleado;

public class Parqueadero{
    private String nombreNegocio;
    private int tarifa;
    private List<Planta> plantas;
    private List<Empleado> personal;

    public Parqueadero(int nTarifa, String nombre) {
        this.tarifa = nTarifa;
        this.nombreNegocio = nombre;
        plantas = new ArrayList<>();
        personal = new ArrayList<>();
        agregarPlanta();
    }

    @Override
    public void setTarifa(int n) {
        this.tarifa = n;
    }
    @Override
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

    public static boolean getEstado() {
        return existe;
    }

    public Planta getPlanta(int indice) {
        return plantas.get(indice);
    }

    public void crearAdmin(int documento, String Nombre, String email, String contrasena) {
        Administrador temp = new Administrador(Nombre, documento, email, contrasena);
        personal.add(temp);
    }

    public void agregarEmpleado(int documento, String nombre, String email, String contrasena) {
        Empleado e = new Empleado(nombre, documento, email, contrasena);
        personal.add(e);
    }

    public void agregarPlanta() {
        int id = plantas.size() + 1;
        Planta nueva = new Planta(id);
        plantas.add(nueva);
    }

    public void eliminarPlanta(int indice) {

        if (indice >= 0 && indice < plantas.size()) {
            plantas.remove(indice);
        } else {
            System.out.println("Índice inválido, no existe esa planta.");
        }
    }
    public void eliminarEmpleado(int indice){
        if (indice >= 0 && indice < plantas.size()) {
            plantas.remove(indice);
        } else {
            System.out.println("Índice inválido, no existe este empleado.");
        }

    }
}
