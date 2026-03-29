package parqueaderoapp.control;

import java.util.Scanner;

import javafx.application.Application;

import java.text.NumberFormat;
import java.util.Locale;
import parqueaderoapp.modelo.persona.*;
import parqueaderoapp.gui.Ventana;
import parqueaderoapp.modelo.parqueadero.*;
public class App {
    public static void main(String[] args){
        Scanner lector = new Scanner(System.in);

        Application.launch(Ventana.class, args);

        String nombre = "hector";
        
        Locale colombia = Locale.forLanguageTag("es-CO");
        NumberFormat formatoCO = NumberFormat.getCurrencyInstance(colombia);
        formatoCO.setMaximumFractionDigits(0);
        
        int n = 3000;
        Parqueadero p1 = new Parqueadero(n, nombre);



        System.out.println("Ingrese el tipo de usuario");
        int usuario = lector.nextInt();

        Administrador A1 = new Administrador("Alberto", 1010);
        switch (usuario) {
            case 1:
                
                System.out.println("Bienvenido Administrador \n Ingrese su contraseña");
                lector.nextLine();
                int contrasena = lector.nextInt();
                
                while( contrasena != A1.getPass()){
                    System.out.println("Contraseña incorrecta");
                    contrasena = lector.nextInt();
                }
                System.out.println("Por favor ingrese una nueva tarifa: ");
                
                int nuevatarifa = lector.nextInt();
                A1.cambiarTarifa(p1, nuevatarifa);
                break;

            default:
                break;
        }
        System.out.println("Bienvenido a " + p1.getNombre() + " ,La tarifa actual es: "+ formatoCO.format(p1.getTarifa()));
        
        lector.close();
    }

}