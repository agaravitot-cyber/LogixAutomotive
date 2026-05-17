package parqueaderoapp.gui;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import parqueaderoapp.control.App;
import parqueaderoapp.modelo.persona.Administrador;

public class crearAdminController {

    @FXML
    private TextField nombreText;

    @FXML
    private TextField documentoText;

    @FXML
    private TextField correoText;

    @FXML
    private PasswordField passText;

    @FXML
    private Button registroBtn;

    @FXML
    private void initialize() {

        documentoText.setTextFormatter(new TextFormatter<>(c -> {

            if (c.getControlNewText().matches("\\d{0,18}")) {
                return c;
            }
            return null;
        }));
        // Validacion de espacios para correo
        correoText.setTextFormatter(new TextFormatter<>(c -> {
            if (c.getText().contains(" ")) {
                return null;

            }
            return c;

        }));

        // Validacion de espacios para contraseña
        passText.setTextFormatter(new TextFormatter<>(c -> {
            if (c.getText().contains(" ")) {
                return null;
            }
            return c;
        }));
    }

    @FXML
    private void onRegistro() throws IOException {
        String nombre = nombreText.getText();
        String documentoStr = documentoText.getText();
        String correo = correoText.getText();
        String pass = passText.getText();
        if (nombre.isBlank() || documentoStr.isBlank() || correo.isBlank() || pass.isBlank()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText(null);
            alerta.setContentText("TODOS los campos deben ser completados");
            alerta.getButtonTypes().setAll(ButtonType.OK);
            alerta.showAndWait();
            return;
        }
        // validacion correo
        if (!correo.matches(".+@.+\\..+")) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText(null);
            alerta.setContentText("Correo no valido. Formato correcto: ejempo@email.com");
            alerta.getButtonTypes().setAll(ButtonType.OK);
            alerta.showAndWait();
            return;
        }

        // confirmacion de datos
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar datos");
        confirmacion.setHeaderText("¿Desea continuar?");
        confirmacion.setContentText(
                "Nombre: " + nombre + "\n" +
                        "Documento: " + documentoStr + "\n" +
                        "Correo: " + correo + "\n" +
                        "Contraseña: " + pass);

        ButtonType botonSi = new ButtonType("Sí", ButtonBar.ButtonData.OK_DONE);
        ButtonType botonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmacion.getButtonTypes().setAll(botonSi, botonNo);

        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == botonSi) {
            Long documento = Long.parseLong(documentoStr);
            Administrador admin = new Administrador(nombre, documento, correo, pass);
            if (App.parqueadero == null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/crearParqueadero.fxml"));
                Scene nuevaEscena = new Scene(loader.load());
                crearParqueadero controller = loader.getController();

                controller.setAdmin(admin);
                Stage stage = (Stage) registroBtn.getScene().getWindow();
                stage.setScene(nuevaEscena);
                stage.setTitle("Creacion de Parqueadero");
                stage.show();
            }
        }

    }

}
