package parqueaderoapp.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import parqueaderoapp.control.App;
import parqueaderoapp.modelo.parqueadero.Celda;
import parqueaderoapp.modelo.parqueadero.Parqueadero;
import parqueaderoapp.modelo.parqueadero.Planta;
import parqueaderoapp.modelo.persona.Administrador;

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
    private TextField tarifaBText;

    @FXML
    private void initialize() {
        formTarifa.setVisible(true);
        formEstruct.setVisible(false);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        numPisoSP.setValueFactory(valueFactory);
        campoNumerico(tarifaCText);
        campoNumerico(tarifaMText);
        campoNumerico(tarifaBText);
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
        int tarifaB = Integer.parseInt(tarifaBText.getText());
        int tarifaM = Integer.parseInt(tarifaMText.getText());

        Parqueadero parqueadero = new Parqueadero(nombre, tarifaC, tarifaM, tarifaB);
        App.parqueadero = parqueadero;
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
            Spinner<Integer> celdasBici = new Spinner<>();

            totalCeldasCB.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    celdasCarro.setValueFactory(
                            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, newVal, 0));
                    celdasMoto.setValueFactory(
                            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, newVal, 0));
                    celdasBici.setValueFactory(
                            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, newVal, 0));
                }
            });

            // Inicializar con valor inicial
            int capacidadInicial = totalCeldasCB.getValue();
            celdasCarro.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, capacidadInicial, 0));
            celdasMoto.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, capacidadInicial, 0));
            celdasBici.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, capacidadInicial, 0));

            contenido.getChildren().addAll(
                    new Label("Capacidad total"), totalCeldasCB,
                    new Label("Carros"), celdasCarro,
                    new Label("Motos"), celdasMoto,
                    new Label("Bicis"), celdasBici);

            TitledPane pisoPane = new TitledPane("Piso " + i, contenido);
            pisosAcc.getPanes().add(pisoPane);
        }
    }

    @FXML
    private void onFinal() {
        for (TitledPane pane : pisosAcc.getPanes()) {
            VBox contenido = (VBox) pane.getContent();

            ComboBox<Integer> totalCeldasCB = null;
            Spinner<Integer> celdasCarro = null;
            Spinner<Integer> celdasMoto = null;
            Spinner<Integer> celdasBici = null;

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
                        case "Bicis":
                            celdasBici = (Spinner<Integer>) node;
                            break;
                    }
                }
            }

            int total = totalCeldasCB.getValue();
            int carros = celdasCarro.getValue();
            int motos = celdasMoto.getValue();
            int bicis = celdasBici.getValue();

            if (carros + motos + bicis != total) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error en distribución");
                alert.setContentText("En " + pane.getText() +
                        " la suma de carros+motos+bicis excede la capacidad (" + total + ")");
                alert.showAndWait();
                return;
            }
            Planta piso = new Planta(total);
            for (int i = 0; i < carros; i++)
                piso.agregar(new Celda("Carro"));
            for (int i = 0; i < motos; i++)
                piso.agregar(new Celda("Moto"));
            for (int i = 0; i < bicis; i++)
                piso.agregar(new Celda("Bici"));

            App.parqueadero.agregarPlanta(piso);
        }

        Alert ok = new Alert(Alert.AlertType.INFORMATION);
        ok.setHeaderText("Parqueadero creado");
        ok.setContentText("Se han configurado todos los pisos correctamente.");
        ok.showAndWait();

    }

    public void setAdmin(Administrador admin) {
        App.parqueadero.agregarEmpleado(admin);
    }
}
