package parqueaderoapp.control;
public class Sistema {
    private MenuPrincipal menuPrincipal;
    
    public Sistema() {
        this.menuPrincipal = new MenuPrincipal();
    }
    
    public void iniciar() {
        System.out.println("=================================");
        System.out.println("   BIENVENIDO AL SISTEMA");
        System.out.println("=================================");
        System.out.println();
        
        menuPrincipal.ejecutar();
    }
}