package parqueaderoapp.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import parqueaderoapp.control.App;

public class loginEmpleado {

    @FXML
    private TextField usuarioText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Button loginBtn;

    @FXML
    private void onLogin() throws Exception{
        String usuario = usuarioText.getText();
        String pass = passwordText.getText();

        // Validar contra empleados registrados en el Parqueadero
        boolean valido = App.parqueadero.getEmpleados().stream()
                .anyMatch(e -> e.getDocumento().equals(usuario) && e.getPass().equals(pass));

        if (valido) {
            abrirMenuEmpleado();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Credenciales inválidas");
            alert.setContentText("Usuario o contraseña incorrectos.");
            alert.showAndWait();
        }
    }

    private void abrirMenuEmpleado() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/empleadoController.fxml"));
            Scene nuevaEscena = new Scene(loader.load());

            Stage stage = (Stage) loginBtn.getScene().getWindow();
            stage.setScene(nuevaEscena);
            stage.setTitle("Menu Empleado");
            stage.show();
    }

}
