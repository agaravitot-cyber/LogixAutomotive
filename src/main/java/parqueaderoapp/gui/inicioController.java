package parqueaderoapp.gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class inicioController {

    @FXML
    private Button clienteBtn;

    @FXML
    private void onLogin(ActionEvent event) {
        try {
            // Cargar la segunda vista
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/vista/segundaVentana.fxml")
            );
            Scene secondScene = new Scene(loader.load());

            // Obtener el Stage actual desde el botón
            Stage stage = (Stage) clienteBtn.getScene().getWindow();
            stage.setScene(secondScene);
            stage.setTitle("Pantalla Secundaria");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

