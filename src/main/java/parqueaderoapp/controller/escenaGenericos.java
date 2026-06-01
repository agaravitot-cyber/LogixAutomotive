package parqueaderoapp.controller;


import java.util.function.UnaryOperator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

public interface escenaGenericos {
    default void volverInicio(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MainView.fxml"));
        Scene inicio = new Scene(loader.load());
        stage.setScene(inicio);
        stage.setTitle("Gestor Parqueadero");
        stage.show();
    }
    default void campoNumerico(TextField field) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                if (newText.isEmpty())
                    return change;
                try {
                    int value = Integer.parseInt(newText);
                    if (value >= 0) {
                        return change;
                    }
                } catch (NumberFormatException e) {

                }
            }
            return null;
        };
        field.setTextFormatter(new TextFormatter<>(filter));
    }
    default void mostrarInfo(String header, String content) {
        Alert ok = new Alert(Alert.AlertType.INFORMATION);
        ok.setHeaderText(header);
        ok.setContentText(content);
        ok.showAndWait();
    }
    default void mostrarError(String header, String content) {
    Alert error = new Alert(Alert.AlertType.ERROR);
    error.setHeaderText(header);
    error.setContentText(content);
    error.showAndWait();
}
}
