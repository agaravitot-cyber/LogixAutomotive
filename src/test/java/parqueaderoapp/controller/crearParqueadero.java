package parqueaderoapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parqueaderoapp.main.App;
import parqueaderoapp.modelo.parqueadero.Celda;
import parqueaderoapp.modelo.parqueadero.Parqueadero;
import parqueaderoapp.modelo.parqueadero.Planta;

public class crearParqueadero implements escenaGenericos {

    @FXML
    private VBox formTarifa;
    @FXML
    private VBox formEstruct;
    @FXML
    private Spinner<Integer> numPisoSP;
    @FXML
    private Accordion pisosAcc;
    @FXML
    private Button pisoGenBtn;
    @FXML
    private Button finalizarBtn;
    @FXML
    private TextField nombreText;
    @FXML
    private TextField tarifaCText;
    @FXML
    private TextField tarifaMText;

    @FXML
    private void initialize() {
        formTarifa.setVisible(true);
        formEstruct.setVisible(false);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        numPisoSP.setValueFactory(valueFactory);
        campoNumerico(tarifaCText);
        campoNumerico(tarifaMText);
    }

    @FXML
    private void onAnterior() {
        formTarifa.setVisible(true);
        formEstruct.setVisible(false);
    }

    @FXML
    private void onSiguiente() {
        String nombre = nombreText.getText();
        int tarifaC = Integer.parseInt(tarifaCText.getText());
        int tarifaM = Integer.parseInt(tarifaMText.getText());

        if (App.getParqueadero() == null) {
            App.setParqueadero(new Parqueadero(nombre, tarifaC, tarifaM));
            App.getParqueadero().agregarEmpleado(App.getAdministrador());
        } else {
            // Si ya existe, solo actualizas tarifas o nombre si quieres
            App.getParqueadero().setTarifa(tarifaM, "Moto");
            App.getParqueadero().setTarifa(tarifaC, "Carro");
        }

        formTarifa.setVisible(false);
        formEstruct.setVisible(true);

    }

    @FXML
    private void onGenerarPiso() {
        pisosAcc.getPanes().clear();
        int numPiso = numPisoSP.getValue();

        for (int i = 1; i <= numPiso; i++) {
            VBox contenido = new VBox(10);

            // ComboBox capacidad total
            ComboBox<Integer> totalCeldasCB = new ComboBox<>();
            for (int j = 1; j <= 100; j++)
                totalCeldasCB.getItems().add(j);
            totalCeldasCB.setValue(10);

            // Spinners dinámicos
            Spinner<Integer> celdasCarro = new Spinner<>();
            Spinner<Integer> celdasMoto = new Spinner<>();

            totalCeldasCB.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    celdasCarro.setValueFactory(
                            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, newVal, 0));
                    celdasMoto.setValueFactory(
                            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, newVal, 0));
                }
            });

            // Inicializar con valor inicial
            int capacidadInicial = totalCeldasCB.getValue();
            celdasCarro.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, capacidadInicial, 0));
            celdasMoto.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, capacidadInicial, 0));

            contenido.getChildren().addAll(
                    new Label("Capacidad total"), totalCeldasCB,
                    new Label("Carros"), celdasCarro,
                    new Label("Motos"), celdasMoto);

            TitledPane pisoPane = new TitledPane("Piso " + i, contenido);
            pisosAcc.getPanes().add(pisoPane);
        }
    }

    @FXML
    private void onFinal() throws Exception {
        for (TitledPane pane : pisosAcc.getPanes()) {
            VBox contenido = (VBox) pane.getContent();

            ComboBox<Integer> totalCeldasCB = null;
            Spinner<Integer> celdasCarro = null;
            Spinner<Integer> celdasMoto = null;

            // recorrer con for-each
            for (javafx.scene.Node node : contenido.getChildren()) {
                if (node instanceof ComboBox) {
                    totalCeldasCB = (ComboBox<Integer>) node;
                } else if (node instanceof Spinner) {
                    Label etiqueta = (Label) contenido.getChildren()
                            .get(contenido.getChildren().indexOf(node) - 1);
                    switch (etiqueta.getText()) {
                        case "Carros":
                            celdasCarro = (Spinner<Integer>) node;
                            break;
                        case "Motos":
                            celdasMoto = (Spinner<Integer>) node;
                            break;
                    }
                }
            }

            int total = totalCeldasCB.getValue();
            int carros = celdasCarro.getValue();
            int motos = celdasMoto.getValue();

            if (carros + motos != total) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error en distribución");
                alert.setContentText("En " + pane.getText() +
                        " la suma de carros + motos no es igual a: " + total + "");
                alert.showAndWait();
                return;
            }
            Planta piso = new Planta(total);
            for (int i = 0; i < carros; i++)
                piso.agregar(new Celda("Carro"));
            for (int i = 0; i < motos; i++)
                piso.agregar(new Celda("Moto"));

            App.getParqueadero().agregarPlanta(piso);
        }

        Alert ok = new Alert(Alert.AlertType.INFORMATION);
        ok.setHeaderText("Parqueadero creado");
        ok.setContentText("Se han configurado todos los pisos correctamente.");
        ok.showAndWait();

        Stage stage = (Stage) finalizarBtn.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/adminEscena.fxml"));
        Scene adminScene = new Scene(loader.load());

        stage.setScene(adminScene);
        stage.setTitle("Menú Administrador");
        stage.show();
    }

}