public class Parqueadero {
    
    private String nombreNegocio;
    private int tarifa;
    private int numPisos;
    
    public Parqueadero(int nTarifa, String nombre){
        this.tarifa = nTarifa;
        this.nombreNegocio = nombre;
        this.numPisos = 1;
        //secciones en vez de pisos   
    }
    public void setTarifa(int n){
        this.tarifa = n;
    }

    public void setPisos(int nPisos){
        this.numPisos = nPisos;
    }

    public void setNombre(String nuevoNombre){

    }

    public int getTarifa(){
        return this.tarifa;
    }

    public String getNombre(){
        return this.nombreNegocio;
    }

    public int getPisos(){
        return this.numPisos;
    }

    public double calcFactura(int tarifa, int minutos){
        return tarifa * minutos;
    }

}