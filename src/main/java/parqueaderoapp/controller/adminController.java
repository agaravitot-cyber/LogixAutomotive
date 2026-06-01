package parqueaderoapp.controller;

import parqueaderoapp.modelo.persona.Administrador;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parqueaderoapp.modelo.persona.Empleado;
import parqueaderoapp.modelo.parqueadero.Planta;
import parqueaderoapp.main.App;
import parqueaderoapp.modelo.estadisticas.Recibo;
import parqueaderoapp.modelo.parqueadero.Celda;

public class adminController implements escenaGenericos {

    private Administrador admin = App.getAdministrador();

    // Label de bienvenida
    @FXML
    private Label bienvenidoLbl;
    // Panes
    @FXML
    private AnchorPane adminMain;
    @FXML
    private AnchorPane adminEmp;
    @FXML
    private AnchorPane adminPar;
    @FXML
    private AnchorPane adminEst;
    // Botones principal
    @FXML
    private Button gestEmp;
    @FXML
    private Button gestPar;
    @FXML
    private Button onEsta;
    @FXML
    private Button volverBtn;
    @FXML
    private MenuButton openBtn;
    @FXML
    private RadioMenuItem encenderItem;

    @FXML
    private RadioMenuItem apagarItem;

    // Gestion de empleados
    @FXML
    private TextField newName;
    @FXML
    private TextField newDoc;
    @FXML
    private TextField newEmail;
    @FXML
    private TextField newPass;
    @FXML
    private Button regEmpBtn;
    @FXML
    private TextField delDoc;
    @FXML
    private Button delBtn;
    @FXML
    private Accordion empAcc;
    @FXML
    private Button regBtn1;

    // Gestion de parqueadero
    @FXML
    private TextField newTarC;
    @FXML
    private TextField newTarM;
    @FXML
    private Button tarifBtn;
    @FXML
    private Spinner<Integer> numPisoSP;
    @FXML
    private Accordion pisosAcc;
    @FXML
    private Button pisoGenBtn;
    @FXML
    private Button EstBtn;
    @FXML
    private Button regBtn2;

    // Estadisticas
    @FXML
    private Label totalIng;
    @FXML
    private Label carIng;
    @FXML
    private Label motoIng;
    @FXML
    private Label totalNo;
    @FXML
    private Label carNo;
    @FXML
    private Label motoNo;
    @FXML
    private Accordion estAcc;
    @FXML
    private Button updBtn;
    @FXML
    private Button regBtn3;

    @FXML
    private void initialize() {
        // Al iniciar, solo se ve el pane principal
        adminMain.setVisible(true);
        adminEmp.setVisible(false);
        adminPar.setVisible(false);
        adminEst.setVisible(false);

        bienvenidoLbl.setText("Bienvenido," + admin.getNombre());

        campoNumerico(newDoc);
        campoNumerico(delDoc);
        ToggleGroup estadoGroup = new ToggleGroup();
        encenderItem.setToggleGroup(estadoGroup);
        apagarItem.setToggleGroup(estadoGroup);
        encenderItem.setSelected(true); // por defecto encendido
        numPisoSP.setValueFactory(
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1)
    );
        refreshEmpAcc();
        refreshParqAcc();
    }

    @FXML
    private void onGestEmp() {
        adminMain.setVisible(false);
        adminEmp.setVisible(true);
        adminPar.setVisible(false);
        adminEst.setVisible(false);
    }

    @FXML
    private void onGestPar() {
        adminMain.setVisible(false);
        adminEmp.setVisible(false);
        adminPar.setVisible(true);
        adminEst.setVisible(false);
    }

    @FXML
    private void onEstadistica() {
        adminMain.setVisible(false);
        adminEmp.setVisible(false);
        adminPar.setVisible(false);
        adminEst.setVisible(true);
    }

    @FXML
    private void onPrev() {
        // Regresa al menú principal desde cualquier submenu
        adminMain.setVisible(true);
        adminEmp.setVisible(false);
        adminPar.setVisible(false);
        adminEst.setVisible(false);
        refreshParqAcc();
    }

    @FXML
    private void onBack() throws Exception {
        Stage stage = (Stage) volverBtn.getScene().getWindow();
        volverInicio(stage);
    }

    @FXML
    private void onEncender() {
        admin.setEstado(true);
    }

    @FXML
    private void onApagar() {
        boolean hayVehiculos = false;

        // Verificar si esta vacio
        for (Planta p : App.getParqueadero().listaPiso()) {
            for (Celda c : p.listaCelda()) {
                if (c.isOcupada()) {
                    hayVehiculos = true;
                    break;
                }
            }
            if (hayVehiculos)
                break;
        }

        if (hayVehiculos) {
            // Confirmación si aún hay vehículos
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirmar cierre");
            confirm.setHeaderText("El parqueadero aún tiene vehículos.");
            confirm.setContentText("¿Está seguro de que desea cerrar?");

            ButtonType siBtn = new ButtonType("Sí", ButtonBar.ButtonData.OK_DONE);
            ButtonType noBtn = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirm.getButtonTypes().setAll(siBtn, noBtn);

            confirm.showAndWait().ifPresent(response -> {
                if (response == siBtn) {
                    App.getParqueadero().setEstado(false);
                    mostrarInfo("Parqueadero cerrado", "El parqueadero ha sido apagado.");
                }
            });
        } else {

            App.getParqueadero().setEstado(false);
            mostrarInfo("Parqueadero cerrado", "El parqueadero ha sido apagado.");
        }
    }

    @FXML
    private void onReg() {
        String nombre = newName.getText().trim();
        String documentoStr = newDoc.getText().trim();
        String correo = newEmail.getText().trim();
        String pass = newPass.getText().trim();

        if (nombre.isEmpty() || documentoStr.isEmpty() || correo.isEmpty() || pass.isEmpty()) {
            mostrarError("Campos incompletos", "Todos los campos deben ser llenados.");
            return;
        }

        long documento;
        try {
            documento = Long.parseLong(documentoStr);
        } catch (NumberFormatException e) {
            mostrarError("Documento inválido", "El documento debe ser numérico.");
            return;
        }

        // Validar duplicados
        for (Empleado emp : App.getParqueadero().getEmpleados()) {
            if (emp.getDocumento().toString().equals(documento)) {
                mostrarError("Documento duplicado", "Ya existe un empleado con ese documento.");
                return;
            }
            if (emp.getEmail().equalsIgnoreCase(correo)) {
                mostrarError("Correo duplicado", "Ya existe un empleado con ese correo.");
                return;
            }
        }

        // Confirmación
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar registro");
        confirm.setHeaderText("¿Desea registrar este empleado?");
        confirm.setContentText(
                "Nombre: " + nombre + "\n" +
                        "Documento: " + documento + "\n" +
                        "Correo: " + correo);

        ButtonType siBtn = new ButtonType("Sí", ButtonBar.ButtonData.OK_DONE);
        ButtonType noBtn = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirm.getButtonTypes().setAll(siBtn, noBtn);

        confirm.showAndWait().ifPresent(response -> {
            if (response == siBtn) {
                Empleado nuevo = new Empleado(nombre, documento, correo, pass);
                App.getParqueadero().agregarEmpleado(nuevo);
                mostrarInfo("Registro exitoso", "Empleado registrado correctamente.");
            }
        });
        refreshEmpAcc();
    }

    @FXML
    private void onDel() {
        String documentoStr = delDoc.getText().trim();

        if (documentoStr.isEmpty()) {
            mostrarError("Campo vacío", "Debe ingresar el documento del empleado a eliminar.");
            return;
        }

        long documento = Long.parseLong(documentoStr);

        Empleado encontrado = App.getParqueadero().getEmpleados().stream()
                .filter(emp -> emp.getDocumento().equals(Long.toString(documento)))
                .findFirst()
                .orElse(null);

        if (encontrado == null) {
            mostrarError("No encontrado", "No existe un empleado con ese documento.");
            return;
        }

        // Confirmacion
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar eliminación");
        confirm.setHeaderText("¿Desea eliminar este empleado?");
        confirm.setContentText("Nombre: " + encontrado.getNombre() + "\nDocumento: " + documento);

        ButtonType siBtn = new ButtonType("Sí", ButtonBar.ButtonData.OK_DONE);
        ButtonType noBtn = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirm.getButtonTypes().setAll(siBtn, noBtn);

        confirm.showAndWait().ifPresent(response -> {
            if (response == siBtn) {
                admin.quitarEmpleado(encontrado);
                mostrarInfo("Eliminación exitosa", "Empleado eliminado correctamente.");
            }
        });
        refreshEmpAcc();
    }

    @FXML
    private void onTarifa() {
        String tarCarroStr = newTarC.getText().trim();
        String tarMotoStr = newTarM.getText().trim();

        boolean cambioCarro = !tarCarroStr.isEmpty();
        boolean cambioMoto = !tarMotoStr.isEmpty();

        if (!cambioCarro && !cambioMoto) {
            mostrarError("Sin cambios", "No se han generado cambios en las tarifas.");
            return;
        }

        // Confirmación
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar cambios");
        confirm.setHeaderText("¿Desea aplicar los cambios de tarifa?");
        StringBuilder sb = new StringBuilder("Cambios:\n");
        if (cambioCarro)
            sb.append("Carro: ").append(tarCarroStr).append("\n");
        if (cambioMoto)
            sb.append("Moto: ").append(tarMotoStr).append("\n");
        confirm.setContentText(sb.toString());

        ButtonType siBtn = new ButtonType("Sí", ButtonBar.ButtonData.OK_DONE);
        ButtonType noBtn = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirm.getButtonTypes().setAll(siBtn, noBtn);

        confirm.showAndWait().ifPresent(response -> {
            if (response == siBtn) {
                if (cambioCarro) {
                    try {
                        int tarifaCarro = Integer.parseInt(tarCarroStr);
                        App.getParqueadero().setTarifa(tarifaCarro, "Carro");
                    } catch (NumberFormatException e) {
                        mostrarError("Error", "La tarifa de carro debe ser numérica.");
                        return;
                    }
                }
                if (cambioMoto) {
                    try {
                        int tarifaMoto = Integer.parseInt(tarMotoStr);
                        App.getParqueadero().setTarifa(tarifaMoto, "Moto");
                        ;
                    } catch (NumberFormatException e) {
                        mostrarError("Error", "La tarifa de moto debe ser numérica.");
                        return;
                    }
                }
                mostrarInfo("Cambios aplicados", "Las tarifas han sido modificadas correctamente.");
            }
        });
    }

    @FXML
    private void onGenerarPiso() {
        // Limpiar el Accordion antes de generar
        pisosAcc.getPanes().clear();

        int numPiso = numPisoSP.getValue();

        for (int i = 1; i <= numPiso; i++) {
            VBox contenido = new VBox(10);

            // ComboBox capacidad total
            ComboBox<Integer> totalCeldasCB = new ComboBox<>();
            for (int j = 1; j <= 100; j++) {
                totalCeldasCB.getItems().add(j);
            }
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
    private void onEstructura() {
        // Reiniciar la estructura: limpiar plantas previas
        App.getParqueadero().limpiarPlantas();

        for (TitledPane pane : pisosAcc.getPanes()) {
            VBox contenido = (VBox) pane.getContent();

            ComboBox<Integer> totalCeldasCB = null;
            Spinner<Integer> celdasCarro = null;
            Spinner<Integer> celdasMoto = null;

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
                        " la suma de carros + motos (" + (carros + motos) +
                        ") no es igual a la capacidad total (" + total + ")");
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
        ok.setHeaderText("Estructura configurada");
        ok.setContentText("Se han configurado todos los pisos correctamente.");
        ok.showAndWait();
    }

    @FXML
    private void onUpdate() {
        double totalIngresos = 0;
        double ingresosCarro = 0;
        double ingresosMoto = 0;

        Set<String> clientesPasados = new HashSet<>();
        int clientesCarroPasados = 0;
        int clientesMotoPasados = 0;

        // Recorrer recibos
        for (Recibo r : App.getParqueadero().listaRecibo()) {
            totalIngresos += r.getMonto();
            if (r.getTipoCopia().equalsIgnoreCase("Carro")) {
                ingresosCarro += r.getMonto();
                clientesCarroPasados++;
            } else if (r.getTipoCopia().equalsIgnoreCase("Moto")) {
                ingresosMoto += r.getMonto();
                clientesMotoPasados++;
            }
            clientesPasados.add(r.getPlacaCopia());
        }

        // Clientes presentes
        int clientesCarroPresentes = 0;
        int clientesMotoPresentes = 0;
        for (Planta p : App.getParqueadero().listaPiso()) {
            for (Celda c : p.listaCelda()) {
                if (c.isOcupada()) {
                    if (c.getTipoCelda().equalsIgnoreCase("Carro")) {
                        clientesCarroPresentes++;
                    } else if (c.getTipoCelda().equalsIgnoreCase("Moto")) {
                        clientesMotoPresentes++;
                    }
                }
            }
        }

        int totalClientes = clientesPasados.size() + clientesCarroPresentes + clientesMotoPresentes;
        int totalCarros = clientesCarroPasados + clientesCarroPresentes;
        int totalMotos = clientesMotoPasados + clientesMotoPresentes;

        // Actualizar labels de ingresos
        totalIng.setText("Total: $" + totalIngresos);
        carIng.setText("Carros: $" + ingresosCarro + " (" + porcentaje(ingresosCarro, totalIngresos) + "%)");
        motoIng.setText("Motos: $" + ingresosMoto + " (" + porcentaje(ingresosMoto, totalIngresos) + "%)");

        // Actualizar labels de clientes
        totalNo.setText("Total: " + totalClientes);
        carNo.setText("Carros: " + totalCarros + " (" + porcentaje(totalCarros, totalClientes) + "%)");
        motoNo.setText("Motos: " + totalMotos + " (" + porcentaje(totalMotos, totalClientes) + "%)");
    }

    // Método auxiliar para calcular porcentajes
    private String porcentaje(double parte, double total) {
        if (total == 0)
            return "0";
        return String.format("%.1f", (parte / total) * 100);
    }

    private void refreshParqAcc() {
        estAcc.getPanes().clear();

        List<Planta> pisos = App.getParqueadero().listaPiso();
        for (int i = 0; i < pisos.size(); i++) {
            Planta piso = pisos.get(i);
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(5);

            int row = 0;
            for (Celda celda : piso.listaCelda()) {
                Label tipoLabel = new Label(celda.getTipoCelda());
                Label estadoLabel = new Label(celda.isOcupada() ? "Ocupado" : "Libre");
                estadoLabel.setStyle(celda.isOcupada() ? "-fx-text-fill: red;" : "-fx-text-fill: green;");

                grid.add(tipoLabel, 0, row);
                grid.add(estadoLabel, 1, row);
                row++;
            }

            TitledPane pisoPane = new TitledPane("Piso " + (i + 1), grid);
            estAcc.getPanes().add(pisoPane);
        }
    }

    private void refreshEmpAcc() {
        empAcc.getPanes().clear();

        List<Empleado> empleados = App.getParqueadero().getEmpleados();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);

        int row = 0;
        for (Empleado emp : empleados) {
            Label nombreLabel = new Label(emp.getNombre());
            Label correoLabel = new Label(emp.getEmail());

            grid.add(nombreLabel, 0, row);
            grid.add(correoLabel, 1, row);
            row++;
        }

        TitledPane empleadosPane = new TitledPane("Empleados registrados", grid);
        empAcc.getPanes().add(empleadosPane);
    }
}
