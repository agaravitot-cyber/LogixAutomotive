package parqueaderoapp.gui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class empleadoController {

    @FXML
    private Button regresoBtn;

    @FXML
    private void onBack(ActionEvent event) throws Exception{

            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/vista/MainView.fxml")
            );
            Scene inicioScene = new Scene(loader.load());

            Stage stage = (Stage) regresoBtn.getScene().getWindow();
            stage.setScene(inicioScene);
            stage.setTitle("Gestor de Parqueadero");
            stage.show();

    }

     @FXML
    private void onRegistrarEntrada() {
        
    }

    @FXML
    private void onRegistrarSalida() {
        // abrir formulario de salida
        // App.parqueadero.liberarCelda(...)
    }

    @FXML
    private void onRevisarParqueadero() {
       return;
    }
}


