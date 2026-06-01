package parqueaderoapp.controller;

import parqueaderoapp.modelo.persona.Administrador;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parqueaderoapp.modelo.persona.Empleado;
import parqueaderoapp.modelo.parqueadero.Planta;
import parqueaderoapp.main.App;
import parqueaderoapp.modelo.parqueadero.Celda;

public class adminController implements escenaGenericos {

    private Administrador admin;

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

        // Texto inicial de bienvenida (luego se puede setear con el nombre del admin)
        bienvenidoLbl.setText("Bienvenido...");
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
        // Regresa al menú principal desde cualquier submenú
        adminMain.setVisible(true);
        adminEmp.setVisible(false);
        adminPar.setVisible(false);
        adminEst.setVisible(false);
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
    }

    @FXML
    private void onDel() {

    }

    @FXML
    private void onTarifa() {
    }

    @FXML
    private void onGenerarPiso() {
    }

    @FXML
    private void onEstructura() {
    }

    @FXML
    private void onUpdate() {
    }

    public void setAdmin(Administrador a) {
        admin = a;
    }
}
