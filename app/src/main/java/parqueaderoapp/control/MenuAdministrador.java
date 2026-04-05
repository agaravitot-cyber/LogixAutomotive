package parqueaderoapp.control;
import java.util.Scanner;

public class MenuAdministrador {
    private Scanner scanner;
    private Autenticacion autenticacion;
    
    public MenuAdministrador(Scanner scanner, Autenticacion autenticacion) {
        this.scanner = scanner;
        this.autenticacion = autenticacion;
    }
    
    public void mostrar() {
        int opcion;
        boolean autenticado = false;
        
        while (!autenticado) {
            System.out.println("\n=== ACCESO ADMINISTRADOR ===");
            System.out.println("1. Ingresar contraseña");
            System.out.println("2. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
                
                if (opcion == 1) {
                    System.out.print("Ingrese la contraseña: ");
                    String contrasena = scanner.nextLine();
                    
                    if (autenticacion.validarContrasena(contrasena)) {
                        autenticado = true;
                        mostrarOpciones();
                    } else {
                        System.out.println("\n*** ERROR: Contraseña incorrecta ***\n");
                    }
                } else if (opcion == 2) {
                    return; // Regresa al menú principal correctamente
                } else {
                    System.out.println("\n*** ERROR: Opción no válida ***\n");
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("\n*** ERROR: Ingrese un número válido ***\n");
            }
        }
    }
    
    private void mostrarOpciones() {
        int opcion;
        
        do {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Cambiar tarifas");
            System.out.println("2. Registrar trabajador");
            System.out.println("3. Cambiar contraseña");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcion) {
                    case 1:
                        System.out.println("\n--- CAMBIAR TARIFAS ---");
                        System.out.println("Funcionalidad en desarrollo...");
                        System.out.println("Presione Enter para continuar...");
                        scanner.nextLine();
                        break;
                    case 2:
                        System.out.println("\n--- REGISTRAR TRABAJADOR ---");
                        System.out.println("Funcionalidad en desarrollo...");
                        System.out.println("Presione Enter para continuar...");
                        scanner.nextLine();
                        break;
                    case 3:
                        cambiarContrasena();
                        break;
                    case 4:
                        System.out.println("\nVolviendo al menú principal...");
                        // Simplemente salimos del bucle, no creamos un nuevo MenuPrincipal
                        break;
                    default:
                        System.out.println("\n*** ERROR: Opción no válida ***\n");
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("\n*** ERROR: Ingrese un valor válido ***\n");
                opcion = 0;
            }
            
        } while (opcion != 4);
    }
    
    private void cambiarContrasena() {
        System.out.println("\n--- CAMBIAR CONTRASEÑA ---");
        System.out.print("Contraseña actual: ");
        String actual = scanner.nextLine();
        System.out.print("Nueva contraseña: ");
        String nueva = scanner.nextLine();
        System.out.print("Confirmar contraseña: ");
        String confirmacion = scanner.nextLine();
        
        if (autenticacion.cambiarContrasena(actual, nueva, confirmacion)) {
            System.out.println("*** Contraseña actualizada exitosamente ***");
        } else {
            System.out.println("*** ERROR: No se pudo cambiar la contraseña ***");
            System.out.println("Verifique que la contraseña actual sea correcta");
        }
        
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine(); 
    }
}