package parqueaderoapp.modelo.parqueadero;

public class Celda {
    private boolean espacioCarro;
    private boolean espacioMoto;
    private boolean espacioBici;

    public Celda() {
        this.espacioCarro = false;
        this.espacioMoto = false;
        this.espacioBici = false;
    }

    public boolean ocupado(String tipoVehiculo) {
        switch (tipoVehiculo.toLowerCase()) {
            case "carro":
                if (!espacioCarro) {
                    espacioCarro = true;
                    return true;
                }
                return false;

            case "moto":
                if (!espacioMoto) {
                    espacioMoto = true;
                    return true;
                }
                return false;

            case "bici":
                if (!espacioBici) {
                    espacioBici = true;
                    return true;
                }
                return false;

            default:
                System.out.println("Tipo de vehículo no válido");
                return false;
        }
    }
    
}
