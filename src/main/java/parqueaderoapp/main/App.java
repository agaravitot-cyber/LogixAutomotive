package parqueaderoapp.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import parqueaderoapp.modelo.parqueadero.Parqueadero;

public class App extends Application {

    private static Parqueadero parqueadero;

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MainView.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setTitle("Gestor de Parqueadero");
        stage.setScene(scene);
        stage.show();
    }

    public static void setParqueadero(Parqueadero p){
        parqueadero = p;
    }
    public static Parqueadero getParqueadero() {
        return parqueadero;
    }

    public static void main(String[] args) {
        launch(args);
        System.out.println("funciono");
    }
}