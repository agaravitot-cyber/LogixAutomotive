package parqueaderoapp.modelo.persona;

public class Cliente extends Persona {
    public Cliente(String nombreUser, long documentoUser, String emailUser) {
        super(nombreUser, documentoUser, emailUser);
    }

    @Override
    public void generarRecibo(){

    }
    @Override
    public void verMapa(){
        
    }
}
