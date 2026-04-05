package parqueaderoapp.modelo.parqueadero;

import java.util.HashMap;
import java.util.Map;

public class Planta {
    private Map<String, Celda> MapaPlanta;

    public Planta(int id) {
        this.MapaPlanta = new HashMap<>();
    }

}
