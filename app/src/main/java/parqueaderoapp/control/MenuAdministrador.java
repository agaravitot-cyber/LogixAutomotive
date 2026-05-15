package parqueaderoapp.control;
import java.util.Scanner;

import parqueaderoapp.modelo.parqueadero.Parqueadero;
import parqueaderoapp.modelo.persona.Administrador;
import parqueaderoapp.modelo.persona.Empleado;

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
        
        Parqueadero p = new Parqueadero(0, "test");
        Administrador admin = new Administrador("Johan", 1010965315, "alguien@gmail.com", "Admin123", p);

        do {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Cambiar tarifas");
            System.out.println("2. Registrar trabajador");
            System.out.println("3. Cambiar contraseña");
            System.out.println("4. Calcular cobro");
            System.out.println("5. Ver lista de empleados");
            System.out.println("6. Entrada vehiculo");
            System.out.println("7. Salida vehiculo");
            System.out.println("8. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcion) {
                    case 1:
                        System.out.println("\n--- CAMBIAR TARIFAS ---");
                        System.out.println("La tarifa actual es de: " + p.getTarifa());
                        System.out.print("Por favor ingrese una nueva tarifa : ");
                        int nuevaTarifa = Integer.parseInt(scanner.nextLine());
                        admin.cambiarTarifa(p, nuevaTarifa);
                        System.out.println("La nueva tarifa es de: " + p.getTarifa());
                        System.out.println("Presione Enter para continuar...");
                        scanner.nextLine();
                        break;
                    case 2:
                        System.out.println("\n--- REGISTRAR TRABAJADOR ---");
                        System.out.println("Ingrese los datos del nuevo empleado");
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Documento: ");
                        long documento  = Long.parseLong(scanner.nextLine());
                        System.out.print("Email: ");
                        String correo = scanner.nextLine();
                        Empleado nuevo = new Empleado(nombre, documento, correo, "0000", p);
                        admin.agregarTrabajador(p, nuevo);
                        System.out.printf("Se genero el nuevo empleado: %n%s%nDocumento: %d%nEmail %s%n", nuevo.getNombre(),nuevo.getDocumento(), nuevo.getEmail());
                        System.out.println("Presione Enter para continuar...");
                        scanner.nextLine();
                        break;
                    case 3:
                        cambiarContrasena();
                        break;
                    case 4:
                        System.out.println("Ingrese el tiempo de estadia");
                        int tiempo = Integer.parseInt(scanner.nextLine());
                        System.out.println("El valor del cobro es de: " + p.calcFactura(tiempo));
                        // Simplemente salimos del bucle, no creamos un nuevo MenuPrincipal
                        break;
                    case 5:
                        for(Empleado e : p.getEmpleados()){
                            System.out.printf("Empleado: %s%ndocumento: %d%ncorreo: %s%n%n", e.getNombre(),e.getDocumento(),e.getEmail());
                        }
                        break;
                    case 6:
                        //Poner la entrada del vehiculo
                        break;
                    case 7:
                        //Poner la salida del vehiculo
                        break;
                    case 8:
                        System.out.println("\nVolviendo al menú principal...");
                        
                        break;
                    default:
                        System.out.println("\n*** ERROR: Opción no válida ***\n");
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("\n*** ERROR: Ingrese un valor válido ***\n");
                opcion = 0;
            }
            
        } while (opcion != 6);
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