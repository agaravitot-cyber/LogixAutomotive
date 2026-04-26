package parqueaderoapp.modelo.persona;

import parqueaderoapp.modelo.parqueadero.Parqueadero;

public class Empleado extends Persona{
    protected String emailUser;
    protected String contrasena;
    protected static Parqueadero parqueaderoApp;

    public Empleado(String nombreUser, long documentoUser, String emailUser, String pass){
        super(nombreUser, documentoUser);
        this.emailUser = emailUser;
        contrasena = pass;
    }
    public void setParqueadero(Parqueadero p){
        parqueaderoApp = p;
    }
    public String getNombre(){
        return this.nombreUser;
    }
    public long getDocumento(){
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
