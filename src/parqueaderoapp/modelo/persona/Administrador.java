package parqueaderoapp.modelo.persona;

import parqueaderoapp.modelo.parqueadero.*;

public class Administrador extends Persona{

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