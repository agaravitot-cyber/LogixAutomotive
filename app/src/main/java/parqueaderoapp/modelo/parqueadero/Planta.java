package parqueaderoapp.modelo.parqueadero;

import java.util.ArrayList;
import java.util.List;

public class Planta{
    private ArrayList<Celda> celdas;
    private int capacidad;
    private boolean ocupado;

    public Planta(int capacidad) {
        this.celdas = new ArrayList<>();
        this.capacidad = capacidad;
    }

    public boolean pisoOcupado() {
        return ocupado;
    }

    public int celdaLibre() {
        int contador = 0;
        for (Celda c : celdas) {
            if (!c.isOcupada())
                contador++;
        }
        return contador;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean agregar(Celda c) {
        if (celdas.size() == capacidad)
            return false;
        celdas.add(c);
        if (celdas.size() == capacidad)
            ocupado = true;
        return true;
    }

    public void quitar(Celda c) {
        celdas.remove(c);
    }

    public ArrayList<Celda> listaCelda() {
        return celdas;
    }
}