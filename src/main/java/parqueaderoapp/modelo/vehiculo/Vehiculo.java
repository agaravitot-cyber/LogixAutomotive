package parqueaderoapp.modelo.vehiculo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import parqueaderoapp.modelo.persona.Cliente;

public class Vehiculo {
    private static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final String tipoVehiculo;
    private final String placa;
    private Cliente conductor;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;

    public Vehiculo(String placa, String tipo) {
        this.placa = placa;
        this.tipoVehiculo = tipo;
    }

    public LocalDateTime registroEntrada() {
        fechaEntrada = LocalDateTime.now();
        return fechaEntrada;
    }

    public LocalDateTime registrarSalida() {
        fechaSalida = LocalDateTime.now();
        return fechaSalida;
    }

    public void setConductor(Cliente c){
        this.conductor = c;
    }
    public long getEstadia() {
        if (fechaEntrada != null && fechaSalida != null) {
            Duration duracion = Duration.between(fechaEntrada, fechaSalida);
            return duracion.toMinutes();
        }
        return 0;
    }

    public String getEntrada() {
        return fechaEntrada.format(formato);
    }
    public LocalDateTime getEntrada(Cliente c){
        return fechaEntrada;
    }

    public String getSalida() {
        return fechaSalida.format(formato);
    }

    public Cliente getConductor() {
        return conductor;
    }

    public String getPlaca() {
        return placa;
    }

    public String getTipo() {
        return tipoVehiculo;
    }
}
