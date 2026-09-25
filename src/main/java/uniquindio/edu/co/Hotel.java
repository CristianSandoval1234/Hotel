package uniquindio.edu.co;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    // Atributos de la clase Hotel
    public String direccion, nombreComercial, nit, telefono, paginaWeb;

    // Relaciones de la clase Hotel
    private List<Habitacion> listHabitaciones;
    private List<ServicioAdicional> listServicios;
    private List<Reserva> listReservas;
    private List<Huesped> listHuespedes;

    /**
     * Metodo constructor de la clase Hotel
     */
    public Hotel(String nombreComercial, String nit, String direccion,
                 String telefono, String paginaWeb) {

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
     * GESTION DE HUESPEDES
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

    public Huesped buscarHuespedTelefono(String telefono) {

        Huesped encontrado = null;

        String telefonoBuscado =
                telefono.replaceAll("[^0-9]", "");

        for (int i = 0; i < listHuespedes.size(); i++) {

            Huesped huesped = listHuespedes.get(i);

            String telefonoRegistrado =
                    huesped.getTelefono().replaceAll("[^0-9]", "");

            if (telefonoRegistrado.equals(telefonoBuscado)) {
                encontrado = huesped;
                break;
            }
        }

        return encontrado;
    }

    public String registrarHuesped(String genero, String nombre,
                                   String documento, String telefono,
                                   String correo, String pais) {

        String mensaje = "";

        Huesped huesped = buscarHuesped(documento);

        if (huesped == null) {

            Huesped nuevo =
                    new Huesped(genero, nombre, documento,
                            telefono, correo, pais);

            listHuespedes.add(nuevo);

            mensaje = "El huesped " + nombre
                    + " se registro exitosamente";

        } else {

            mensaje = "El huesped con documento "
                    + documento
                    + " ya se encuentra registrado";
        }

        return mensaje;
    }

    public void eliminarHuesped(String documento) {

        Huesped huesped = buscarHuesped(documento);

        if (huesped != null) {
            listHuespedes.remove(huesped);
        }
    }

    /**
     * GESTION DE HABITACIONES
     */

    public Habitacion buscarHabitacion(int numero) {

        Habitacion encontrada = null;

        for (int i = 0; i < listHabitaciones.size(); i++) {

            Habitacion habitacion = listHabitaciones.get(i);

            if (habitacion.getNumHabitacion() == numero) {
                encontrada = habitacion;
                break;
            }
        }

        return encontrada;
    }

    public String registrarHabitacion(int numero, int piso,
                                      String tipo, int capacidad,
                                      double precio) {

        String mensaje = "";

        Habitacion habitacion = buscarHabitacion(numero);

        if (habitacion == null) {

            Habitacion nueva =
                    new Habitacion(numero, piso, tipo,
                            capacidad, precio);

            listHabitaciones.add(nueva);

            mensaje = "Habitacion #" + numero
                    + " guardada en inventario";

        } else {

            mensaje = "La habitacion #"
                    + numero
                    + " ya existe";
        }

        return mensaje;
    }

    /**
     * GESTION DE SERVICIOS ADICIONALES
     */

    public ServicioAdicional buscarServicio(String codigo) {

        ServicioAdicional encontrado = null;

        for (int i = 0; i < listServicios.size(); i++) {

            ServicioAdicional servicio =
                    listServicios.get(i);

            if (servicio.getCodigo().equals(codigo)) {
                encontrado = servicio;
                break;
            }
        }

        return encontrado;
    }

    public String registrarServicio(String codigo, String nombre,
                                    String descripcion, double precio) {

        String mensaje = "";

        ServicioAdicional servicio =
                buscarServicio(codigo);

        if (servicio == null) {

            ServicioAdicional nuevo =
                    new ServicioAdicional(
                            codigo,
                            nombre,
                            descripcion,
                            precio
                    );

            listServicios.add(nuevo);

            mensaje = "Servicio "
                    + codigo
                    + " registrado";

        } else {

            mensaje = "El servicio "
                    + codigo
                    + " ya existe";
        }

        return mensaje;
    }

    /**
     * LOGICA CENTRAL DE RESERVAS
     */

    public Reserva buscarReserva(String codigo) {

        Reserva encontrada = null;

        for (int i = 0; i < listReservas.size(); i++) {

            Reserva reserva = listReservas.get(i);

            if (reserva.getCodigoReserva().equals(codigo)) {
                encontrada = reserva;
                break;
            }
        }

        return encontrada;
    }

    private int convertirFecha(String fecha) {

        String[] partes = fecha.split("/");

        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt(partes[2]);

        return anio * 10000 + mes * 100 + dia;
    }

    private boolean fechasSeCruzan(
            String entrada1,
            String salida1,
            String entrada2,
            String salida2) {

        int e1 = convertirFecha(entrada1);
        int s1 = convertirFecha(salida1);
        int e2 = convertirFecha(entrada2);
        int s2 = convertirFecha(salida2);

        return e1 < s2 && s1 > e2;
    }

    private boolean habitacionDisponibleEnFechas(
            Habitacion habitacion,
            String entrada,
            String salida) {

        if (habitacion.getEstado()
                .equalsIgnoreCase("Mantenimiento")) {

            return false;
        }

        for (int i = 0; i < listReservas.size(); i++) {

            Reserva reserva = listReservas.get(i);

            if (!reserva.getEstado()
                    .equalsIgnoreCase("Cancelada")

                    && !reserva.getEstado()
                    .equalsIgnoreCase("Finalizada")

                    && reserva
                    .getListHabitacionesReservadas()
                    .contains(habitacion)

                    && fechasSeCruzan(
                    entrada,
                    salida,
                    reserva.getFechaEntrada(),
                    reserva.getFechaSalida())) {

                return false;
            }
        }

        return true;
    }

    public String crearYConfirmarReserva(
            String codigo,
            String fechaRealizacion,
            String fechaEntrada,
            String fechaSalida,
            String metodoPago,
            String documentoHuesped,
            int numeroHabitacion,
            int noches,
            double descuento) {

        if (buscarReserva(codigo) != null) {

            return "Error: El codigo de reserva ya existe";
        }

        Huesped huesped =
                buscarHuesped(documentoHuesped);

        if (huesped == null) {

            return "Error: El huesped no existe en el sistema";
        }

        Habitacion habitacion =
                buscarHabitacion(numeroHabitacion);

        if (habitacion == null) {

            return "Error: La habitacion no existe";
        }

        if (convertirFecha(fechaEntrada)
                >= convertirFecha(fechaSalida)) {

            return "Error: Las fechas de la reserva no son validas";
        }

        if (!habitacionDisponibleEnFechas(
                habitacion,
                fechaEntrada,
                fechaSalida)) {

            return "Error: La habitacion #"
                    + numeroHabitacion
                    + " no esta disponible en esas fechas";
        }

        if (noches <= 0
                || descuento < 0
                || descuento > 100) {

            return "Error: Noches o descuento no validos";
        }

        Reserva nuevaReserva =
                new Reserva(
                        codigo,
                        fechaRealizacion,
                        fechaEntrada,
                        fechaSalida,
                        metodoPago,
                        descuento,
                        huesped
                );

        nuevaReserva.setEstado("Confirmada");

        habitacion.setEstado("Reservada");

        nuevaReserva
                .getListHabitacionesReservadas()
                .add(habitacion);

        double costoEstancia =
                habitacion.getPrecioNoche() * noches;

        double costoTotal =
                costoEstancia
                        - (costoEstancia * descuento / 100);

        nuevaReserva.setValorTotal(costoTotal);

        listReservas.add(nuevaReserva);

        return "Reserva "
                + codigo
                + " procesada de forma Exitosa";
    }

    public String agregarHabitacionReserva(
            String codigoReserva,
            int numeroHabitacion,
            int noches) {

        Reserva reserva =
                buscarReserva(codigoReserva);

        Habitacion habitacion =
                buscarHabitacion(numeroHabitacion);

        if (reserva == null || habitacion == null) {

            return "Reserva o habitacion no encontrada";
        }

        if (reserva
                .getListHabitacionesReservadas()
                .contains(habitacion)) {

            return "La habitacion ya pertenece a la reserva";
        }

        if (!habitacionDisponibleEnFechas(
                habitacion,
                reserva.getFechaEntrada(),
                reserva.getFechaSalida())) {

            return "La habitacion no esta disponible en esas fechas";
        }

        reserva
                .getListHabitacionesReservadas()
                .add(habitacion);

        habitacion.setEstado("Reservada");

        double adicional =
                habitacion.getPrecioNoche() * noches;

        adicional =
                adicional
                        - (adicional
                        * reserva.getDescuento() / 100);

        reserva.setValorTotal(
                reserva.getValorTotal() + adicional
        );

        return "Habitacion agregada a la reserva";
    }

    public String agregarServicioReserva(
            String codigoReserva,
            String codigoServicio) {

        Reserva reserva =
                buscarReserva(codigoReserva);

        ServicioAdicional servicio =
                buscarServicio(codigoServicio);

        if (reserva == null || servicio == null) {

            return "Reserva o servicio no encontrado";
        }

        if (!servicio.isDisponible()) {

            return "El servicio no se encuentra disponible";
        }

        reserva
                .getListConsumos()
                .add(servicio);

        double adicional =
                servicio.getPrecio();

        adicional =
                adicional
                        - (adicional
                        * reserva.getDescuento() / 100);

        reserva.setValorTotal(
                reserva.getValorTotal() + adicional
        );

        return "Servicio agregado a la reserva";
    }

    public String cambiarEstadoReserva(
            String codigoReserva,
            String nuevoEstado) {

        Reserva reserva =
                buscarReserva(codigoReserva);

        if (reserva == null) {

            return "Reserva no encontrada";
        }

        if (!nuevoEstado.equalsIgnoreCase("Confirmada")
                && !nuevoEstado.equalsIgnoreCase("En curso")
                && !nuevoEstado.equalsIgnoreCase("Finalizada")
                && !nuevoEstado.equalsIgnoreCase("Cancelada")) {

            return "Estado no valido";
        }

        reserva.setEstado(nuevoEstado);

        for (int i = 0;
             i < reserva
                     .getListHabitacionesReservadas()
                     .size();
             i++) {

            Habitacion habitacion =
                    reserva
                            .getListHabitacionesReservadas()
                            .get(i);

            if (nuevoEstado
                    .equalsIgnoreCase("Confirmada")) {

                habitacion.setEstado("Reservada");

            } else if (nuevoEstado
                    .equalsIgnoreCase("En curso")) {

                habitacion.setEstado("Ocupada");

            } else {

                habitacion.setEstado("Disponible");
            }
        }

        return "Estado de la reserva actualizado";
    }

    /**
     * NUMERO PERFECTO
     */

    public boolean determinarNumeroPerfecto(long valorTelefono) {

        if (valorTelefono <= 1) {
            return false;
        }

        long suma = 1;
        long i = 2;

        while (i * i <= valorTelefono) {

            if (valorTelefono % i == 0) {

                suma += i;

                long otroDivisor =
                        valorTelefono / i;

                if (otroDivisor != i) {

                    suma += otroDivisor;
                }
            }

            i++;
        }

        return suma == valorTelefono;
    }

    public String verificarHuespedTelefonoPerfecto(
            String telefono) {

        String mensaje = "";

        Huesped huesped =
                buscarHuespedTelefono(telefono);

        if (huesped != null) {

            String soloNumeros =
                    huesped
                            .getTelefono()
                            .replaceAll("[^0-9]", "");

            long numero =
                    Long.parseLong(soloNumeros);

            if (determinarNumeroPerfecto(numero)) {

                mensaje =
                        "El huesped "
                                + huesped.getNombre()
                                + " TIENE un numero telefonico perfecto ("
                                + numero
                                + ")";

            } else {

                mensaje =
                        "El huesped "
                                + huesped.getNombre()
                                + " fue encontrado, pero el numero ("
                                + numero
                                + ") NO es perfecto";
            }

        } else {

            mensaje =
                    "No existe un huesped registrado con ese telefono";
        }

        return mensaje;
    }

    /**
     * INGRESOS POR FECHA
     */

    public double obtenerIngresosPorFecha(
            String fechaConsulta) {

        double totalIngresos = 0.0;

        for (int i = 0;
             i < listReservas.size();
             i++) {

            Reserva reserva =
                    listReservas.get(i);

            if (reserva
                    .getFechaRealizacion()
                    .equals(fechaConsulta)) {

                totalIngresos +=
                        reserva.getValorTotal();
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