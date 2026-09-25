package uniquindio.edu.co;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    // Atributos de la clase Hotel
    public String direccion, nombreComercial, nit, telefono, paginaWeb;

    // Relaciones de la clase Hotel (Composición fuerte ♦)
    private List<Habitacion> listHabitaciones;
    private List<ServicioAdicional> listServicios;
    private List<Reserva> listReservas;
    private List<Huesped> listHuespedes;

    /**
     * Metodo constructor de la clase Hotel
     */
    public Hotel(String nombreComercial, String nit, String direccion, String telefono, String paginaWeb) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.paginaWeb = paginaWeb;

        this.listHabitaciones = new ArrayList<>();
        this.listServicios = new ArrayList<>();
        this.listReservas = new ArrayList<>();
        this.listHuespedes = new ArrayList<>();
    }

    /**
     * GESTION DE HUÉSPEDES (Mismo estilo matricularEstudianteV2 de Universidad)
     * @param documento
     * @return
     */
    public Huesped buscarHuesped(String documento) {
        Huesped encontrado = null;
        for (int i = 0; i < listHuespedes.size(); i++) {
            Huesped huesped = listHuespedes.get(i);
            if (huesped.getDocumento().equals(documento)) {
                encontrado = huesped;
                break;
            }
        }
        return encontrado;
    }

    public String registrarHuesped(String genero, String nombre, String documento, String telefono, String correo, String pais) {
        String existe = "";
        Huesped huesped = buscarHuesped(documento);
        if (huesped == null) {
            Huesped nuevo = new Huesped(genero, nombre, documento, telefono, correo, pais);
            listHuespedes.add(nuevo);
            existe = "El huesped " + nombre + " se registro exitosamente";
        } else {
            existe = "El huesped con documento " + documento + " ya se encuentra registrado";
        }
        return existe;
    }

    public void eliminarHuesped(String documento) {
        Huesped huesped = buscarHuesped(documento);
        if (huesped != null) {
            listHuespedes.remove(huesped);
        }
    }

    /**
     * GESTION DE HABITACIONES
     * @param numero
     * @return
     */

    public Habitacion buscarHabitacion(int numero) {
        Habitacion encontrada = null;
        for (int i = 0; i < listHabitaciones.size(); i++) {
            Habitacion hab = listHabitaciones.get(i);
            if (hab.getNumHabitacion() == numero) {
                encontrada = hab;
                break;
            }
        }
        return encontrada;
    }

    public String registrarHabitacion(int numero, int piso, String tipo, int capacidad, double precio) {
        String mensaje = "";
        Habitacion hab = buscarHabitacion(numero);
        if (hab == null) {
            listHabitaciones.add(new Habitacion(numero, piso, tipo, capacidad, precio));
            mensaje = "Habitacion #" + numero + " guardada en inventario";
        } else {
            mensaje = "La habitacion #" + numero + " ya existe";
        }
        return mensaje;
    }


}
