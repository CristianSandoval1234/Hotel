package uniquindio.edu.co;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        /**
         * datos administrativos del hotel
         */

        Hotel hotel = new Hotel(
                "StayPlus",
                "900-123-456",
                "Armenia, Quindio",
                "31244455",
                "https://stayplus.com"
        );

        /**
         * datos y pruebas para crear habitaciones y huespedes
         */

        hotel.registrarHabitacion(
                101, 1, "Individual", 1, 90000
        );

        hotel.registrarHabitacion(
                201, 2, "Suite", 3, 250000
        );

        hotel.registrarServicio(
                "S1",
                "Restaurante",
                "Almuerzo",
                20000
        );

        hotel.registrarServicio(
                "S2",
                "Lavanderia",
                "Lavado de ropa",
                15000
        );

        /**
         * registro de huesped para probar el numero perfecto
         */

        hotel.registrarHuesped(
                "M",
                "Carlos Restrepo",
                "1094-A",
                "6",
                "carlos@email.com",
                "Colombia"
        );

        hotel.registrarHuesped(
                "F",
                "Ana Maria Espitia",
                "4211-B",
                "12345",
                "ana@email.com",
                "Argentina"
        );

        int opcion = 0;

        while (opcion != 9) {

            opcion = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            null,
                            """
                            MENÚ ADMINISTRATIVO STAYPLUS

                            1. Registrar un Huésped
                            2. Buscar un Huésped por teléfono
                            3. Crear y confirmar una Reserva
                            4. Agregar otra habitación a una Reserva
                            5. Agregar servicio a una Reserva
                            6. Cambiar estado de una Reserva
                            7. Verificar si el teléfono del Huésped es Perfecto
                            8. Reporte de Ingresos por Fecha
                            9. Salir del Sistema

                            Seleccione una opción:
                            """
                    )
            );

            if (opcion == 1) {

                String genero =
                        JOptionPane.showInputDialog(
                                null,
                                "Ingrese el genero (M/F):"
                        );

                String nombre =
                        JOptionPane.showInputDialog(
                                null,
                                "Ingrese el nombre completo:"
                        );

                String doc =
                        JOptionPane.showInputDialog(
                                null,
                                "Ingrese el documento:"
                        );

                String tel =
                        JOptionPane.showInputDialog(
                                null,
                                "Ingrese el telefono:"
                        );

                String correo =
                        JOptionPane.showInputDialog(
                                null,
                                "Ingrese el correo:"
                        );

                String pais =
                        JOptionPane.showInputDialog(
                                null,
                                "Ingrese el pais de procedencia:"
                        );

                String respuesta =
                        hotel.registrarHuesped(
                                genero,
                                nombre,
                                doc,
                                tel,
                                correo,
                                pais
                        );

                JOptionPane.showMessageDialog(
                        null,
                        respuesta
                );

            } else if (opcion == 2) {

                String telefonoBuscar =
                        JOptionPane.showInputDialog(
                                null,
                                "Ingrese el telefono a buscar:"
                        );

                Huesped huesped =
                        hotel.buscarHuespedTelefono(
                                telefonoBuscar
                        );

                if (huesped != null) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Huesped encontrado:\n"
                                    + huesped.toString()
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            null,
                            "El huesped solicitado no existe"
                    );
                }

            } else if (opcion == 3) {

                String cod =
                        JOptionPane.showInputDialog(
                                null,
                                "Asigne un codigo a la reserva:"
                        );

                String fRealizacion =
                        JOptionPane.showInputDialog(
                                null,
                                "Fecha de hoy (DD/MM/AAAA):"
                        );

                String fEntrada =
                        JOptionPane.showInputDialog(
                                null,
                                "Fecha de entrada (DD/MM/AAAA):"
                        );

                String fSalida =
                        JOptionPane.showInputDialog(
                                null,
                                "Fecha de salida (DD/MM/AAAA):"
                        );

                String metodoPago =
                        JOptionPane.showInputDialog(
                                null,
                                "Metodo de pago:"
                        );

                String docHuesped =
                        JOptionPane.showInputDialog(
                                null,
                                "Documento del huesped:"
                        );

                int numHab =
                        Integer.parseInt(
                                JOptionPane.showInputDialog(
                                        null,
                                        "Numero de la habitacion:"
                                )
                        );

                int noches =
                        Integer.parseInt(
                                JOptionPane.showInputDialog(
                                        null,
                                        "Cantidad de noches a quedarse:"
                                )
                        );

                double descuento =
                        Double.parseDouble(
                                JOptionPane.showInputDialog(
                                        null,
                                        "Descuento en porcentaje (0 si no aplica):"
                                )
                        );

                String transaccion =
                        hotel.crearYConfirmarReserva(
                                cod,
                                fRealizacion,
                                fEntrada,
                                fSalida,
                                metodoPago,
                                docHuesped,
                                numHab,
                                noches,
                                descuento
                        );

                JOptionPane.showMessageDialog(
                        null,
                        transaccion
                );

            } else if (opcion == 4) {

                String codReserva =
                        JOptionPane.showInputDialog(
                                null,
                                "Codigo de la reserva:"
                        );

                int numHab =
                        Integer.parseInt(
                                JOptionPane.showInputDialog(
                                        null,
                                        "Numero de la habitacion a agregar:"
                                )
                        );

                int noches =
                        Integer.parseInt(
                                JOptionPane.showInputDialog(
                                        null,
                                        "Cantidad de noches:"
                                )
                        );

                String respuesta =
                        hotel.agregarHabitacionReserva(
                                codReserva,
                                numHab,
                                noches
                        );

                JOptionPane.showMessageDialog(
                        null,
                        respuesta
                );

            } else if (opcion == 5) {

                String codReserva =
                        JOptionPane.showInputDialog(
                                null,
                                "Codigo de la reserva:"
                        );

                String codServicio =
                        JOptionPane.showInputDialog(
                                null,
                                "Codigo del servicio (S1 o S2):"
                        );

                String respuesta =
                        hotel.agregarServicioReserva(
                                codReserva,
                                codServicio
                        );

                JOptionPane.showMessageDialog(
                        null,
                        respuesta
                );

            } else if (opcion == 6) {

                String codReserva =
                        JOptionPane.showInputDialog(
                                null,
                                "Codigo de la reserva:"
                        );

                String estado =
                        JOptionPane.showInputDialog(
                                null,
                                "Nuevo estado: Confirmada, En curso, Finalizada o Cancelada"
                        );

                String respuesta =
                        hotel.cambiarEstadoReserva(
                                codReserva,
                                estado
                        );

                JOptionPane.showMessageDialog(
                        null,
                        respuesta
                );

            } else if (opcion == 7) {

                String telefonoEvaluar =
                        JOptionPane.showInputDialog(
                                null,
                                "Telefono del huesped a evaluar:"
                        );

                String diagnostico =
                        hotel.verificarHuespedTelefonoPerfecto(
                                telefonoEvaluar
                        );

                JOptionPane.showMessageDialog(
                        null,
                        diagnostico
                );

            } else if (opcion == 8) {

                String fechaFiltro =
                        JOptionPane.showInputDialog(
                                null,
                                "Ingrese la fecha a consultar (DD/MM/AAAA):"
                        );

                double recaudado =
                        hotel.obtenerIngresosPorFecha(
                                fechaFiltro
                        );

                JOptionPane.showMessageDialog(
                        null,
                        "Total de ingresos en la fecha "
                                + fechaFiltro
                                + ":\n$"
                                + recaudado
                                + " COP"
                );

            } else if (opcion == 9) {

                JOptionPane.showMessageDialog(
                        null,
                        "Cerrando el sistema del hotel StayPlus. ¡Feliz dia!"
                );
            }
        }
    }
}
