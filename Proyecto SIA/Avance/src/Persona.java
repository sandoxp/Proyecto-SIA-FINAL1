public class Persona {

    private String nombre;
    private int edad;
    private String RUT;
    private String rol;



    public Persona(){

    }

    public Persona(String nombre, int edad, String RUT, String rol) {
        this.nombre = nombre;
        this.edad = edad;
        this.RUT = RUT;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRUT() {
        return RUT;
    }

    public void setRUT(String RUT) {
        this.RUT = RUT;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", RUT='" + RUT + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }

    public void mostrarSaludo(){
        System.out.println("Hola, " + nombre + "!");
    }

    public void mostrarSaludo(String rol){
        if (rol.equalsIgnoreCase("jefe")){
            System.out.println("Hola Jefe! Buenos dias");
        }
        else{
            System.out.println("Bienvenido compañero " + nombre + "!");
        }
    }



}
