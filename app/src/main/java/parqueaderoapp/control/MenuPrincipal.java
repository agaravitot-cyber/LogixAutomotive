package parqueaderoapp.control;
import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner;
    private Autenticacion autenticacion;
    private MenuAdministrador menuAdmin;
    
    public MenuPrincipal() {
        this.scanner = new Scanner(System.in);
        this.autenticacion = new Autenticacion();
        this.menuAdmin = new MenuAdministrador(scanner, autenticacion);
    }
    
    public void ejecutar() {
        int opcion;
        
        do {
            mostrarMenu();
            opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    menuAdmin.mostrar();
                    break;
                case 2:
                    System.out.println("\n--- MENÚ EMPLEADO ---");
                    System.out.println("Funcionalidad en desarrollo...");
                    System.out.println("Presione Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("\n--- MENÚ CLIENTE ---");
                    System.out.println("Funcionalidad en desarrollo...");
                    System.out.println("Presione Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("\n¡Gracias por usar el sistema! Hasta luego.");
                    break;
                default:
                    System.out.println("\n*** ERROR: Opción no válida ***\n");
            }
            
        } while (opcion != 4);
        
        scanner.close();
    }
    
    private void mostrarMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Administrador");
        System.out.println("2. Empleado");
        System.out.println("3. Cliente");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    private int leerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();
            return opcion;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }
}