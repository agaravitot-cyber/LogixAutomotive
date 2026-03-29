package parqueaderoapp.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class Ventana extends Application {
    @Override
    public void start(Stage stage) {
        Label etiqueta = new Label("Bienvenido al Parqueadero");
        StackPane root = new StackPane(etiqueta);
        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("LogixAutomotive");
        stage.setScene(scene);
        stage.show();
    }
}
