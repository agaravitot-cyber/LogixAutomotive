package parqueaderoapp.modelo.parqueadero;

import java.util.ArrayList;

public class Planta{
    private ArrayList<Celda> celdas;
    private int capacidad;

    public Planta(int capacidad) {
        this.celdas = new ArrayList<>();
        this.capacidad = capacidad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean agregar(Celda c) {
        if (celdas.size() == capacidad)
            return false;

        celdas.add(c);
        return true;
    }

    public void quitar(Celda c) {
        celdas.remove(c);
    }

    public ArrayList<Celda> listaCelda() {
        return celdas;
    }
}