package parqueaderoapp.gui;


import java.util.function.UnaryOperator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

}
