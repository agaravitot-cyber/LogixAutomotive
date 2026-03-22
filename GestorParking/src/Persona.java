public class Persona {
    protected String nombreUser;
    protected int documentoUser;
    
    public Persona(String nombre, int documento){
        this.nombreUser = nombre;
        this.documentoUser = documento;
    }
    
}

class Administrador extends Persona{

    private int contrasena = 1234;

    public Administrador(String nombreUser, int documentoUser){
        super(nombreUser, documentoUser);
        contrasena = 1234;
    }

    public void cambiarTarifa( Parqueadero parqueadero, int tarifa){
        parqueadero.setTarifa(tarifa);
    }

    public int getPass(){
        return contrasena;
    }
}

class Empleado extends Persona{
    public Empleado(String nombreUser, int documentoUser){
        super(nombreUser, documentoUser);
    }
}

class Cliente extends Persona{
    public Cliente(String nombreUser, int documentoUser){
        super(nombreUser, documentoUser);
    }
}