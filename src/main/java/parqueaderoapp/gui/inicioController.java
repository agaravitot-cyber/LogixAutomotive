package parqueaderoapp.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import parqueaderoapp.control.App;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class inicioController implements escenaGenericos {

    @FXML
    private Button clienteBtn;
    @FXML
    private Button empleadoBtn;
    @FXML
    private Button adminBtn;

    @FXML
    private void onEmpleado() throws Exception {
        if (App.parqueadero == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText(null);
            alerta.setContentText("No existe ningun parqueadero en el sistema. Debe ser creado por la administración");
            alerta.getButtonTypes().setAll(ButtonType.OK);
            alerta.showAndWait();
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/loginEmpleado.fxml"));
            Scene nuevaEscena = new Scene(loader.load());

            Stage stage = (Stage) empleadoBtn.getScene().getWindow();
            stage.setScene(nuevaEscena);
            stage.setTitle("Menu Empleado");
            stage.show();
        }
    }

    @FXML
    private void onAdmin() throws Exception {

        if (App.parqueadero == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/crearAdmin.fxml"));
            Scene nuevaEscena = new Scene(loader.load());

            Stage stage = (Stage) adminBtn.getScene().getWindow();
            stage.setScene(nuevaEscena);
            stage.setTitle("Registro Admin");
            stage.show();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/loginEmpleado.fxml"));
            Scene nuevaEscena = new Scene(loader.load());

            Stage stage = (Stage) adminBtn.getScene().getWindow();
            stage.setScene(nuevaEscena);
            stage.setTitle("Menu Administración");
            stage.show();
        }
    }

    @FXML
    private void onCliente() throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/clienteEscena.fxml"));
        Scene nuevaEscena = new Scene(loader.load());

        Stage stage = (Stage) clienteBtn.getScene().getWindow();
        stage.setScene(nuevaEscena);
        stage.setTitle("Menu Cliente");
        stage.show();
    }
}
