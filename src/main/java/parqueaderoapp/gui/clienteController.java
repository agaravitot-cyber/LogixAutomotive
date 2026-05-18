package parqueaderoapp.gui;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import parqueaderoapp.control.App;
import parqueaderoapp.modelo.parqueadero.Celda;
import parqueaderoapp.modelo.parqueadero.Planta;
import parqueaderoapp.modelo.vehiculo.Vehiculo;

public class clienteController implements escenaGenericos{

    @FXML
    private TextField placaCliente;
    @FXML
    private ComboBox<String> tipoCliente;
    @FXML
    private TextField horaSalidaCliente;
    @FXML private Button regresoBtn;
    @FXML
    public void initialize() {
        tipoCliente.getItems().addAll("Carro", "Moto", "Bici");
    }

    @FXML
    private void onPrevisualizarCosto() {
        String placa = placaCliente.getText();
        String tipo = tipoCliente.getValue();

        Vehiculo v = App.parqueadero.buscarVehiculoPorPlaca(placa);
        if (v != null) {
            LocalDateTime salidaSimulada;
            if (horaSalidaCliente.getText().isEmpty()) {
                salidaSimulada = LocalDateTime.now();
            } else {
                try {
                    salidaSimulada = LocalDateTime.parse(horaSalidaCliente.getText(),
                            DateTimeFormatter.ofPattern("HH:mm"))
                            .withYear(LocalDateTime.now().getYear())
                            .withMonth(LocalDateTime.now().getMonthValue())
                            .withDayOfMonth(LocalDateTime.now().getDayOfMonth());
                } catch (DateTimeParseException e) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setHeaderText("Formato inválido");
                    error.setContentText("Ingrese la hora en formato HH:mm (ejemplo: 14:30).");
                    error.showAndWait();
                    return;
                }

            }

            long minutos = Duration.between(v.getEntradaRaw(), salidaSimulada).toMinutes();
            double monto = App.parqueadero.getTarifa(tipo) * minutos;

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Previsualización de costo");
            alert.setContentText("Vehículo " + placa + " (" + tipo + ") → $" + monto);
            alert.showAndWait();
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Vehículo no encontrado");
            error.setContentText("No se encontró el vehículo " + placa + " en el parqueadero.");
            error.showAndWait();
        }
    }

    @FXML
    private void onConsultarDisponibilidad() {
        String tipo = tipoCliente.getValue();
        int libres = 0;

        for (Planta p : App.parqueadero.listaPiso()) {

            for (Celda c : p.listaCelda()) {
                if (!c.isOcupada() && c.getTipoCelda().equalsIgnoreCase(tipo)) {
                    libres++;
                }
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Disponibilidad");
        alert.setContentText("Celdas libres para " + tipo + ": " + libres);
        alert.showAndWait();
    }

    @FXML
    private void onBack() throws Exception {
        Stage stage = (Stage) regresoBtn.getScene().getWindow();
        volverInicio(stage); 
    }
}
