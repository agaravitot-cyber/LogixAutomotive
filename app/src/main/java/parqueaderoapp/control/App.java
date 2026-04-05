package parqueaderoapp.control;

import javafx.application.Application;
import parqueaderoapp.gui.Ventana;

/*import java.util.Scanner;
import javafx.application.Application;
import java.text.NumberFormat;
import java.util.Locale;
import parqueaderoapp.gui.Ventana;
import parqueaderoapp.modelo.parqueadero.*;
*/
public class App {
    public static void main(String[] args) {
        Application.launch(Ventana.class,args);
        Sistema sistema = new Sistema();
        sistema.iniciar();
    }
}