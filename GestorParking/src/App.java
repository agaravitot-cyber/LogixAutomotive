import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner lector = new Scanner(System.in);
        String nombre = "hector";
        
        Locale colombia = Locale.forLanguageTag("es-CO");
        NumberFormat formatoCO = NumberFormat.getCurrencyInstance(colombia);
        formatoCO.setMaximumFractionDigits(0);
        
        int n = 3000;
        Parqueadero p1 = new Parqueadero(n, nombre);

        int tipoUsuario = 0;
        /*do {
            System.out.println("\n=== SELECCIONE TIPO DE USUARIO ===");
            System.out.println("[1] Administrador");
            System.out.println("[2] Empleado");
            System.out.println("[3] Cliente");
            tipoUsuario = lector.nextInt();
            
            if (tipoUsuario < 1 || tipoUsuario > 3) {
                System.out.println("pción no válida. Intente nuevamente.");
            }
            
        } while (tipoUsuario < 1 || tipoUsuario > 3);*/
        
        Administrador A1 = new Administrador("Alberto", 1010);

        switch (tipoUsuario) {
            case 1:
                
                System.out.println("Bienvenido Administrador \n Ingrese su contraseña");
                lector.nextLine();
                int contrasena = lector.nextInt();
                
                System.out.print(A1.getPass());
                
                while( contrasena != A1.getPass()){
                    System.out.println("Contraseña incorrecta");
                    contrasena = lector.nextInt();
                }
                /*
                int accion = 0;
                boolean opcionValida = false;

                while (!opcionValida) {
                    System.out.println("Que desea hacer [1] Cambiar tarifas [2] Registrar Trabajador [3] Cambiar Contraseña");
                    accion = lector.nextInt();
                    
                    if (accion == 1 || accion == 2 || accion == 3) {
                        opcionValida = true;
                        Admin ad = new Admin();
                        ad.accionAdmin(accion); 
                    } else {
                        System.out.println("Opción no válida. Intente nuevamente.");
                    }
                }
                               
                */
                break;

            case 2:
                System.out.println("Bienvenido Empleado");
                //Menu para registrar entrada y salida de autos
                break;

            case 3:
                System.out.println("Bienvenido Cliente");
                //Menu para ver el costo que genero su vehiculo
                break;

            default:
                break;
        }
        System.out.println("Bienvenido a " + p1.getNombre() + " ,La tarifa actual es: "+ formatoCO.format(p1.getTarifa()));
        
        lector.close();
    }

}
