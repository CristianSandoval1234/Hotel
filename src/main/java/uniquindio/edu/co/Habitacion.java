package uniquindio.edu.co;

public class Habitacion {

    /**
     *
     * Atributos de la clase Habitacion
      */

    private int numHabitacion, piso, capacidad;
    private String tipo;
    private double precioNoche;
    private boolean disponible;

    /**
     * Metodo constructor de la clase Habitaci'on
     */
    public Habitacion(int numHabitacion, int piso, String tipo, int capacidad, double precioNoche) {
        this.numHabitacion = numHabitacion;
        this.piso = piso;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.precioNoche = precioNoche;
        this.disponible = true;
    }

    public int getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "numHabitacion=" + numHabitacion +
                ", piso=" + piso +
                ", tipo='" + tipo + '\'' +
                ", capacidad=" + capacidad +
                ", precioNoche=" + precioNoche +
                ", disponible=" + disponible +
                '}';
    }
}

