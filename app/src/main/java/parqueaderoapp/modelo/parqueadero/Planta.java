package parqueaderoapp.modelo.parqueadero;

import java.util.ArrayList;
import java.util.List;

import parqueaderoapp.modelo.interfaces.Gestionar;

public class Planta implements Gestionar<Celda>{
    private List<Celda> celdas;

    public Planta() {
        this.celdas = new ArrayList<>();

    }

    @Override
    public void agregar(Celda c){
        celdas.add(c);
    }

    @Override 
    public void quitar(Celda c){
        celdas.add(c);
    }

}
