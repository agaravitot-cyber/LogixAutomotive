package parqueaderoapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import parqueaderoapp.main.App;
import parqueaderoapp.modelo.persona.Administrador;
import parqueaderoapp.modelo.persona.Empleado;

public class loginEmpleado implements escenaGenericos {

    
    @FXML
    private TextField usuarioText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Button loginBtn;
    @FXML
    private Button regresoBtn;

    @FXML


    private void initialize() {
        // Solo números, máximo 18 dígitos
        usuarioText.setTextFormatter(new TextFormatter<>(c -> {
            if (c.getControlNewText().matches("\\d{0,18}")) {
                return c;
            }
            return null;
        }));
    }

    @FXML
    private void onLogin() throws Exception {
        String usuario = usuarioText.getText();
        String pass = passwordText.getText();

        Empleado empleadoValido = App.getParqueadero().getEmpleados().stream()
                .filter(e -> String.valueOf(e.getDocumento()).equals(usuario) && e.getPass().equals(pass)).findFirst()
                .orElse(null);

        if (empleadoValido != null) {
            if (empleadoValido instanceof Administrador) {
                abrirMenuAdmin((Administrador) empleadoValido);
            } else {
                abrirMenuEmpleado(empleadoValido);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Credenciales inválidas");
            alert.setContentText("Usuario o contraseña incorrectos.");
            alert.showAndWait();
        }
    }

    private void abrirMenuEmpleado(Empleado e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menuEmpleado.fxml"));
        Scene nuevaEscena = new Scene(loader.load());

        empleadoController controller = loader.getController();
        controller.setEmpleado(e);

        Stage stage = (Stage) loginBtn.getScene().getWindow();
        stage.setScene(nuevaEscena);
        stage.setTitle("Menu Empleado");
        stage.show();
    }

    private void abrirMenuAdmin(Administrador a) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/adminEscena.fxml"));
        Scene nuevaEscena = new Scene(loader.load());

        adminController controller = loader.getController();
        controller.setAdmin(a);

        Stage stage = (Stage) loginBtn.getScene().getWindow();
        stage.setScene(nuevaEscena);
        stage.setTitle("Menu Administrador");
        stage.show();
    }

    @FXML
    public void onBack() throws Exception {
        Stage stage = (Stage) regresoBtn.getScene().getWindow();
        volverInicio(stage);
    }
}
