package parqueaderoapp.controller;

import java.util.List;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import parqueaderoapp.main.App;
import parqueaderoapp.modelo.parqueadero.Celda;
import parqueaderoapp.modelo.parqueadero.Planta;
import parqueaderoapp.modelo.persona.Empleado;
import parqueaderoapp.modelo.vehiculo.Vehiculo;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

public class empleadoController implements escenaGenericos{
    private Empleado empleado;

    @FXML
    private TextField entradaPlaca;
    @FXML
    private TextField salidaPlaca;
    @FXML
    private ComboBox<String> tipo;
    @FXML
    private Button regresoBtn;
    @FXML
    private Button entradaBtn;
    @FXML
    private Button salidaBtn;
    @FXML
    private Accordion estadoAccordeon;

    @FXML
    public void initialize() {
        tipo.getItems().addAll("Carro", "Moto", "Bici");
        refreshAccordion();
    }

    @FXML
    private void onBack() throws Exception {
        Stage stage = (Stage) regresoBtn.getScene().getWindow();
        volverInicio(stage);
    }

    @FXML
    private void onRegistrarEntrada() {
        String tipoV = tipo.getValue();

        if (tipoV == null)
            return;

        String placa = entradaPlaca.getText().toUpperCase().trim();
        if (placa.isEmpty() || !validarPlaca(placa, tipoV)) {
            mostrarError("Formato inválido", "La placa ingresada no corresponde al formato de " + tipoV + ".");
            return;
        }

        // 🔎 Verificar si la placa ya existe en alguna celda ocupada
        Vehiculo existente = App.getParqueadero().buscarVehiculoPorPlaca(placa);
        if (existente != null) {
            mostrarError("Placa duplicada",
                    "El vehículo con placa " + placa + " ya está registrado en el parqueadero.");
            return;
        }

        Vehiculo v = new Vehiculo(placa, tipoV);
        Celda temp = null;

        for (Planta p : App.getParqueadero().listaPiso()) {
            for (Celda c : p.listaCelda()) {
                if (!c.isOcupada() && c.getTipoCelda().equalsIgnoreCase(tipoV)) {
                    temp = c;
                    break;
                }
            }
            if (temp != null)
                break;
        }

        boolean asignado = empleado.registrarEntrada(temp, v);
        if (asignado) {
            mostrarInfo("Entrada registrada", "Vehículo " + placa + " asignado correctamente.");
            refreshAccordion();
        } else {
            mostrarError("Sin espacio disponible", "No hay celdas libres para " + tipoV + " en este parqueadero.");
        }
    }

    @FXML
    private void onRegistrarSalida() {
        String placa = salidaPlaca.getText().trim();

        if (placa.isEmpty()) {
            mostrarError("Placa requerida", "Debe ingresar la placa para registrar la salida.");
            return;
        }

        Vehiculo v = App.getParqueadero().buscarVehiculoPorPlaca(placa);
        if (v != null) {
            empleado.registroSalida(v);
            mostrarInfo("Salida registrada", "Vehículo " + placa + " salió correctamente.");
            refreshAccordion();
        } else {
            mostrarError("Vehículo no encontrado", "No se encontró el vehículo " + placa + " en ninguna celda.");
        }
    }

    public void setEmpleado(Empleado e) {
        this.empleado = e;
    }

    private void refreshAccordion() {
        estadoAccordeon.getPanes().clear();

        List<Planta> pisos = App.getParqueadero().listaPiso();
        for (int i = 0; i < pisos.size(); i++) {
            Planta piso = pisos.get(i);
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(5);

            int row = 0;
            for (Celda celda : piso.listaCelda()) {
                // Obtener tipo de celda
                Label tipoLabel = new Label(celda.getTipoCelda());

                // Estado ocupado/libre con color
                Label estadoLabel = new Label(celda.isOcupada() ? "Ocupado" : "Libre");
                estadoLabel.setStyle(celda.isOcupada() ? "-fx-text-fill: red;" : "-fx-text-fill: green;");

                // Agregar columnas: tipo | estado
                grid.add(tipoLabel, 0, row);
                grid.add(estadoLabel, 1, row);

                row++;
            }

            TitledPane pisoPane = new TitledPane("Piso " + (i + 1), grid);
            estadoAccordeon.getPanes().add(pisoPane);
        }
    }

    private boolean validarPlaca(String placa, String tipoV) {
        if (tipoV.equalsIgnoreCase("carro")) {
            return placa.matches("^[A-Z]{3}\\d{3}$");
        } else if (tipoV.equalsIgnoreCase("moto")) {
            return placa.matches("^[A-Z]{3}\\d{2}[A-Z]$");
        }
        return false;
    }
    
}