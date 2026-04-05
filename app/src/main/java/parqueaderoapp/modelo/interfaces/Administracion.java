package parqueaderoapp.modelo.interfaces;

public interface Administracion {

    void setTarifa(int n);

    void setNombre(String nuevoNombre);

    int getTarifa();

    void crearAdmin(int documento, String Nombre, String email, String contraseña);

    void agregarEmpleado(int documento, String Nombre, String email, String contraseña);

    void agregarPlanta();

    void eliminarPlanta(int indice);

    void eliminarEmpleado(int indice);

}