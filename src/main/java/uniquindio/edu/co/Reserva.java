package uniquindio.edu.co;

import java.util.ArrayList;
import java.util.List;

public class Reserva {

    /**
     * Atributos de la clase Reserva
     */
    private String codigoReserva, fechaRealizacion, fechaEntrada,
            fechaSalida, estado, metodoPago;

    private double valorTotal, descuento;

    /**
     * Relaciones de la clase Reserva
     */
    private Huesped huesped;
    private List<Habitacion> listHabitacionesReservadas;
    private List<ServicioAdicional> listConsumos;

    /**
     * Metodo constructor de la clase Reserva
     */
    public Reserva(String codigoReserva, String fechaRealizacion,
                   String fechaEntrada, String fechaSalida,
                   String metodoPago, double descuento, Huesped huesped) {

        this.codigoReserva = codigoReserva;
        this.fechaRealizacion = fechaRealizacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.estado = "Pendiente";
        this.metodoPago = metodoPago;
        this.descuento = descuento;
        this.huesped = huesped;

        this.listHabitacionesReservadas = new ArrayList<>();
        this.listConsumos = new ArrayList<>();
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public String getFechaRealizacion() {
        return fechaRealizacion;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public List<Habitacion> getListHabitacionesReservadas() {
        return listHabitacionesReservadas;
    }

    public List<ServicioAdicional> getListConsumos() {
        return listConsumos;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "codigoReserva='" + codigoReserva + '\'' +
                ", fechaRealizacion='" + fechaRealizacion + '\'' +
                ", estado='" + estado + '\'' +
                ", valorTotal=" + valorTotal +
                ", huesped=" + huesped.getNombre() +
                ", habitaciones=" + listHabitacionesReservadas.size() +
                '}';
    }
}
