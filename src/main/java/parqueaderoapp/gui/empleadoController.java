package parqueaderoapp.gui;

import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import parqueaderoapp.control.App;
import parqueaderoapp.modelo.parqueadero.Celda;
import parqueaderoapp.modelo.parqueadero.Planta;
import parqueaderoapp.modelo.persona.Empleado;
import parqueaderoapp.modelo.vehiculo.Vehiculo;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

public class empleadoController {
    private Empleado empleado;

    @FXML
    private TextField entradaPlaca;
    @FXML
    private TextField salidaPlaca;
    @FXML
    private TextField documento;
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
    }

    @FXML
    private void onBack() throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/vista/MainView.fxml"));
        Scene inicioScene = new Scene(loader.load());

        Stage stage = (Stage) regresoBtn.getScene().getWindow();
        stage.setScene(inicioScene);
        stage.setTitle("Gestor de Parqueadero");
        stage.show();

    }

    @FXML
    private void onRegistrarEntrada() {
        String placa = entradaPlaca.getText();
        String tipoV = tipo.getValue();
        if (placa != null && !placa.isEmpty() && tipoV != null) {
            Vehiculo v = new Vehiculo(placa, tipoV);
            Celda temp = null;
            for(Planta p : App.parqueadero.listaPiso()){
                for(Celda c : p.listaCelda()){
                    if(!c.isOcupada() && c.getTipoCelda().equalsIgnoreCase(tipoV)){
                        temp = c;
                        break;
                    }
                }if (temp != null) break;
            }
            boolean asignado = empleado.registrarEntrada(temp, v);

            if (asignado) {
                Alert ok = new Alert(Alert.AlertType.INFORMATION);
                ok.setHeaderText("Entrada registrada");
                ok.setContentText("Vehículo " + placa + " asignado correctamente.");
                ok.showAndWait();
            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("Sin espacio disponible");
                error.setContentText("No hay celdas libres para " + tipoV + " en este parqueadero.");
                error.showAndWait();
            }
        }
    }

    @FXML
    private void onRegistrarSalida() {

        String placa = salidaPlaca.getText();
        Vehiculo v = App.parqueadero.buscarVehiculoPorPlaca(placa);
        if (v != null) {
        empleado.registroSalida(v);

        Alert ok = new Alert(Alert.AlertType.INFORMATION);
        ok.setHeaderText("Salida registrada");
        ok.setContentText("Vehículo " + placa + " salió correctamente.");
        ok.showAndWait();
    } else {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Vehículo no encontrado");
        error.setContentText("No se encontró el vehículo " + placa + " en ninguna celda.");
        error.showAndWait();
    }
    }

    public void setEmpleado(Empleado e) {
        this.empleado = e;
    }

    public void refreshAccordion() {
        estadoAccordeon.getPanes().clear();

        List<Planta> pisos = App.parqueadero.listaPiso();
        for (int i = 0; i < pisos.size(); i++) {
            Planta piso = pisos.get(i);
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(5);

            int row = 0;
            for (Celda celda : piso.listaCelda()) {
                Label estadoLabel = new Label(celda.isOcupada() ? "Ocupado" : "Libre");
                estadoLabel.setStyle(celda.isOcupada() ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
                grid.add(estadoLabel, 0, row);
                row++;
            }

            TitledPane pisoPane = new TitledPane("Piso " + (i + 1), grid);
            estadoAccordeon.getPanes().add(pisoPane);
        }
    }
    

}
