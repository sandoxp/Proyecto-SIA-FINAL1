public class Evento {
    private String nombreEvento;
    private String descripcionEvento;
    private String etiqueta;
    private String horaEvento;
    private static int contador = 0;
    private int idEvento;

    public Evento() {
    }

    public Evento(String nombreEvento, String descripcionEvento, String etiqueta, String horaEvento) {
        this.idEvento = ++contador;
        this.nombreEvento = nombreEvento;
        this.descripcionEvento = descripcionEvento;
        this.etiqueta = etiqueta;
        this.horaEvento = horaEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public String getHoraEvento() {return horaEvento;}

    public void setHoraEvento(String horaEvento) {this.horaEvento=horaEvento;}


    public String getEtiqueta() {return etiqueta;}

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
    public int getContador() {
        return contador;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "nombre='" + nombreEvento + '\'' +
                ", descripcion='" + descripcionEvento + '\'' +
                ", etiqueta='" + etiqueta + '\'' +
                ", hora='" + horaEvento + '\'' +
                ", Nro Reunion ='" + idEvento + '\'' +
                '}';
    }
}

