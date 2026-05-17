package parqueaderoapp.gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class SecondController {

    @FXML
    private Button regresoBtn;

    @FXML
    private void onBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/vista/MainView.fxml")
            );
            Scene inicioScene = new Scene(loader.load());

            // Obtener el Stage actual desde el botón
            Stage stage = (Stage) regresoBtn.getScene().getWindow();
            stage.setScene(inicioScene);
            stage.setTitle("Gestor de Parqueadero");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


