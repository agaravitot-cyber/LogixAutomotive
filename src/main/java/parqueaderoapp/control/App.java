package parqueaderoapp.control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Cargar el archivo FXML que define la interfaz
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MainView.fxml"));
        Scene scene = new Scene(loader.load());

        // Configurar la ventana principal
        stage.setTitle("Gestor de Parqueadero");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
        System.out.println("pute");
    }
}