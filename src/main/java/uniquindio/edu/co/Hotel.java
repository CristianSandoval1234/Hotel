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

    /**
     * LOGICA CENTRAL DEL FLUJO DE RESERVAS Y COSTOS TOTALES
     */

    public Reserva buscarReserva(String codigo) {
        Reserva encontrada = null;
        for (int i = 0; i < listReservas.size(); i++) {
            if (listReservas.get(i).getCodigoReserva().equals(codigo)) {
                encontrada = listReservas.get(i);
                break;
            }
        }
        return encontrada;
    }

    public String crearYConfirmarReserva(String codigo, String fRealizacion, String fEntrada, String fSalida, String pago, String docHuesped, int numHab, int noches) {
        Huesped huesped = buscarHuesped(docHuesped);
        if (huesped == null) {
            return "Error: El huesped no existe en el sistema";
        }

        Habitacion hab = buscarHabitacion(numHab);
        if (hab == null) {
            return "Error: La habitacion no existe";
        }

        if (!hab.isDisponible()) {
            return "Error: La habitacion #" + numHab + " no tiene disponibilidad para esas fechas";
        }

        // Instancia del objeto Reserva asociandole el Huesped
        Reserva nuevaReserva = new Reserva(codigo, fRealizacion, fEntrada, fSalida, pago, huesped);

        // Modificación de estados (Reglas del flujo del proceso)
        nuevaReserva.setEstado("Confirmada");
        hab.setDisponible(false);
        nuevaReserva.getListHabitacionesReservadas().add(hab);

        // Operacion matematica básica de cálculo de costos de estancia
        double costoEstancia = hab.getPrecioNoche() * noches;
        nuevaReserva.setValorTotal(costoEstancia);

        listReservas.add(nuevaReserva);
        return "Reserva " + codigo + " procesada de forma Exitosa";
    }

    /**
     * PROCESO INTERNO 1: ALGORITMO NUMERO PERFECTO (Estilo nombrePalindroma)
     */

    public boolean determinarNumeroPerfecto(int valorTelefono) {
        boolean esPerfecto = false;
        int suma = 0;
        int i = 1;
        while (i <= valorTelefono / 2) {
            if (valorTelefono % i == 0) {
                suma += i;
            }
            i++;
        }
        if (suma == valorTelefono && valorTelefono != 0) {
            esPerfecto = true;
        }
        return esPerfecto;
    }

    public String verificarHuespedTelefonoPerfecto(String documento) {
        String mensaje = "";
        Huesped huesped = buscarHuesped(documento);
        if (huesped != null) {
            // Extrae los digitos del string para la evaluación matemática
            int numConvertido = Integer.parseInt(huesped.getTelefono().replaceAll("[^0-9]", ""));

            if (determinarNumeroPerfecto(numConvertido)) {
                mensaje = "El huesped " + huesped.getNombre() + " TIENE un numero telefonico perfecto (" + numConvertido + ")";
            } else {
                mensaje = "El numero (" + numConvertido + ") NO es un numero perfecto";
            }
        } else {
            mensaje = "Huesped no encontrado en la base de datos";
        }
        return mensaje;
    }

    /**
     * PROCESO INTERNO 2: REPORTE DE INGRESOS POR FECHA (Estilo acumulador)
     */

    public double obtenerIngresosPorFecha(String fechaConsulta) {
        double totalIngresos = 0.0;
        for (int i = 0; i < listReservas.size(); i++) {
            Reserva reserva = listReservas.get(i);
            if (reserva.getFechaRealizacion().equals(fechaConsulta)) {
                totalIngresos += reserva.getValorTotal();
            }
        }
        return totalIngresos;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "nombreComercial='" + nombreComercial + '\'' +
                ", nit='" + nit + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", totalHabitaciones=" + listHabitaciones.size() +
                ", totalReservas=" + listReservas.size() +
                '}';
    }
}