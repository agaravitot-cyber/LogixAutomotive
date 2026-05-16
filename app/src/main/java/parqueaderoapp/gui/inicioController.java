package parqueaderoapp.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class inicioController {
    @FXML
    private Button btnIngresar;

    @FXML
    private void onLogin(ActionEvent event) {
        System.out.println("Botón presionado");
        // Aquí va la lógica que quieras ejecutar
    }
}
