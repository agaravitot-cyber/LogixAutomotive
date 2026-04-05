package parqueaderoapp.control;
public class Autenticacion {
    private String contrasenaAdmin;
    
    public Autenticacion() {
        this.contrasenaAdmin = "admin123";
    }
    
    public boolean validarContrasena(String contrasena) {
        return contrasena.equals(contrasenaAdmin);
    }
    
    public boolean cambiarContrasena(String actual, String nueva, String confirmacion) {
        // Verificar que la contraseña actual sea correcta
        if (!validarContrasena(actual)) {
            System.out.println("Contraseña actual incorrecta");
            return false;
        }
        
        // Verificar que la nueva contraseña y confirmación coincidan
        if (!nueva.equals(confirmacion)) {
            System.out.println("Las contraseñas nuevas no coinciden");
            return false;
        }
        
        // Verificar que la nueva contraseña no esté vacía
        if (nueva.trim().isEmpty()) {
            System.out.println("La nueva contraseña no puede estar vacía");
            return false;
        }
        
        // Cambiar la contraseña
        this.contrasenaAdmin = nueva;
        System.out.println("Contraseña cambiada exitosamente a: " + nueva);
        return true;
    }
    
    // Método para depuración - muestra la contraseña actual (solo para pruebas)
    public void mostrarContrasenaActual() {
        System.out.println("Contraseña actual es: " + contrasenaAdmin);
    }
}