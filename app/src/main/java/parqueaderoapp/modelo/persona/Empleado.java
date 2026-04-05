package parqueaderoapp.modelo.persona;

public class Empleado extends Persona{
    protected String contrasena;

    public Empleado(String nombreUser, int documentoUser, String emailUser, String pass){
        super(nombreUser, documentoUser, emailUser);
        contrasena = pass;
    }
    public String getNombre(){
        return this.nombreUser;
    }
    public int getDocumento(){
        return this.documentoUser;
    }
    public String getPass(){
        return contrasena;
    }
    public String getEmail(){
        return emailUser;
    }
    @Override
    public void generarRecibo(){
        
    }
    @Override
    public void verMapa(){
    }
}