package uniquindio.edu.co;

public class Huesped {

    // Atributos de la clase Huesped
    private String genero, nombre, documento, telefono, correo, paisProcedencia;

    /**
     * Metodo constructor de la clase Huesped
     * @param genero del huesped
     * @param nombre del huesped
     * @param documento del huesped
     * @param telefono del huesped
     * @param correo del huesped
     * @param paisProcedencia del huesped
     */
    public Huesped(String genero, String nombre, String documento, String telefono, String correo, String paisProcedencia) {
        this.genero = genero;
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.correo = correo;
        this.paisProcedencia = paisProcedencia;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPaisProcedencia() {
        return paisProcedencia;
    }

    public void setPaisProcedencia(String paisProcedencia) {
        this.paisProcedencia = paisProcedencia;
    }

    @Override
    public String toString() {
        return "Huesped{" +
                " genero='" + genero + '\'' +
                ", nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", paisProcedencia='" + paisProcedencia + '\'' +
                '}';
    }

}



