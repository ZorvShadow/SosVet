package sosvet;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author García Espino Enrique Román
 * @author García Velasco Rodrigo
 * @author Enríquez Pascual Ángel Gabriel
 * @author Gastélum Sánchez Ricardo
 * @version 0.0.1b
 */

// grupo 3IM7

public class SosVet {
    public static ArrayList<String[]> pacientes = new ArrayList<>();
    // Ejemplo de datos contenidos dentro del Array: {'nombre del cliente', 'nombre
    // del paciente', 'celular', 'raza', 'edad' }

    public static ArrayList<String[]> citas = new ArrayList<>();
    // Ejemplo de datos contenidos dentro de Array: {'id paciente' ,'dia', 'hora'}

    public static void limpiarPantalla() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static void presionaContinuar() {
        System.out.print("Presiona cualquier tecla para continuar...");
        new Scanner(System.in).nextLine(); // Espera a que el usuario presione Enter
        System.out.println(); // Salto de linea
    }

    /**
     * @param id   El ID del paciente a asignar la cita
     * @param dia  El dia en formato DD/MM
     * @param hora La hora en formato HH:MM
     */
    public static void agendarCita(int id, String dia, String hora) {
        String idStr = "" + id;
        String[] datos = {idStr, dia, hora};
        citas.add(datos);
    }

    /**
     * @param indice El indice de la cita a eliminar
     */
    public static void eliminarCita(int indice) {
        citas.remove(indice);
    }

    /**
     * @param indice El indice de la cita a editar
     * @param dia    El dia en formato DD/MM
     * @param hora   La hora en formato HH:MM
     */
    public static void editarCita(int indice, String dia, String hora) {
        String[] datos = {citas.get(indice)[0], dia, hora};
        citas.set(indice, datos);
    }

    /**
     * @param cliente  Nombre del cliente (dueño de la mascota)
     * @param paciente Nombre del paciente (mascota)
     * @param celular  Número celular del cliente
     * @param raza     Raza de la mascota
     * @param edad     Edad de la mascota
     */
    public static void agregarPaciente(String cliente, String paciente, long celular, String raza, int edad) {
        String edadStr = "" + edad; // Se concatena una string vacia para que se convierta a string
        String numeroStr = "" + celular;
        String[] datos = {cliente, paciente, numeroStr, raza, edadStr};
        pacientes.add(datos);
    }

    /**
     * @param id       Indice dentro del ArrayList del elemento
     * @param cliente  Nombre del cliente (dueño de la mascota)
     * @param paciente Nombre del paciente (mascota)
     * @param celular  Número celular del cliente
     * @param raza     Raza de la mascota
     * @param edad     Edad de la mascota
     */
    public static void editarPaciente(int id, String cliente, String paciente, long celular, String raza, int edad) {
        String edadStr = "" + edad; // Se concatena una string vacia para que se convierta a string
        String numeroStr = "" + celular;
        String[] datos = {cliente, paciente, numeroStr, raza, edadStr};

        pacientes.set(id, datos);
    }

    /**
     * @param id Indice dentro del ArrayList del elemento
     */
    public static void eliminarPaciente(int id) {
        pacientes.remove(id);
    }

    /**
     * Pide un entero al usuario y lo repetirá hasta que se introduzca un número
     * válido
     *
     * @param mensaje El mensaje a mostrar sin salto de línea
     * @param min     El entero minimo o igual permitido
     * @param max     El entero maximo o igual permitido
     * @return El número entero validado
     */

    public static int inputIntValidado(String mensaje, int min, int max) {
        Scanner scanner = new Scanner(System.in);

        boolean valido = false;
        int numero = 0;

        while (!valido) {
            System.out.print(mensaje);

            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();

                valido = numero <= max && numero >= min;

            } else {
                System.out.println("Opción inválida");
                scanner.next(); // Limpiar el buffer del Scanner

            }
        }

        return numero;
    }

    /**
     * Pide un long al usuario y lo repetirá hasta que se introduzca un número
     * válido
     *
     * @param mensaje El mensaje a mostrar sin salto de línea
     * @param min     El long minimo o igual permitido
     * @param max     El long maximo o igual permitido
     * @return El número long validado
     */

    public static long inputLongValidado(String mensaje, long min, long max) {
        Scanner scanner = new Scanner(System.in);

        boolean valido = false;
        long numero = 0;

        while (!valido) {
            System.out.print(mensaje);

            if (scanner.hasNextLong()) {
                numero = scanner.nextLong();

                valido = numero <= max && numero >= min;

            } else {
                System.out.println("Opción invalida");
                scanner.next(); // Limpiar el buffer del Scanner

            }
        }

        return numero;
    }

    /**
     * Pide una cadena de texto al usuario y lo repetirá hasta que se introduzca una
     * cadena valida
     *
     * @param mensaje El mensaje a mostrar sin salto de línea
     * @return La cadena de texto no vacía
     */
    public static String inputStringValidado(String mensaje) {
        Scanner scanner = new Scanner(System.in);

        boolean valido = false;
        String inputString = "";

        while (!valido) {
            System.out.print(mensaje);
            inputString = scanner.nextLine();

            valido = !inputString.isEmpty();

        }

        return inputString;
    }

    public static void tablaPacientes() {
        System.out.printf("%4s %15s \n", "ID", "Paciente");

        for (int i = 0; i < pacientes.size(); i++) {
            System.out.printf("%4s %15s \n", i, pacientes.get(i)[1]);
        }
    }

    public static void tablaCitas() {
        System.out.printf("%15s %4s %5s %5s\n", "Paciente", "ID", "Día", "Hora");

        for (int i = 0; i < citas.size(); i++) {
            System.out.printf("%15s %4s %5s %5s\n",
                    pacientes.get(Integer.parseInt(citas.get(i)[0]))[1],
                    i,
                    citas.get(i)[1],
                    citas.get(i)[2]);

        }
    }

    // Comienzan los procedimientos principales

    // Inicia menú de citas

    public static void menuGestion() {
        boolean activo = true;
        while (activo) {
            int eleccion = 0;
            limpiarPantalla();

            System.out.println("----- Gestión de Citas ------");
            System.out.println("  1) Programar");
            System.out.println("  2) Eliminar");
            System.out.println("  3) Editar");
            System.out.println("  4) Salir de Gestión de Citas");

            eleccion = inputIntValidado("Teclea la opción: ", 0, 4);
            limpiarPantalla();

            switch (eleccion) {
                case 1:
                    gestionProgramar();
                    break;
                case 2:
                    eliminarCitaMenu();
                    break;
                case 3:
                    editarCitaMenu();
                    break;
                case 4:
                    activo = false;
                    break;

            }
        }
    }

    public static void gestionProgramar() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes para asignar citas");
            presionaContinuar();
            return;
        }

        System.out.println("Los pacientes disponibles para agendar citas son: ");
        tablaPacientes();

        System.out.println("---Proporcione la siguiente información---");

        int idPaciente = inputIntValidado("Id del Paciente: ", 0, pacientes.size() - 1);
        String dia = inputStringValidado("Seleccionar un día disponible (DD/MM): ");
        String hora = inputStringValidado("Seleccionar una hora (HH:MM): ");

        String nombrePaciente = pacientes.get(idPaciente)[1];

        limpiarPantalla();

        agendarCita(idPaciente, dia, hora);
        System.out.printf("%10s %5s %5s\n", "Nombre", "Día", "Hora");
        System.out.printf("%10s %5s %5s\n", nombrePaciente, dia, hora);

        presionaContinuar();

    }

    public static void eliminarCitaMenu() {
        System.out.println("---Información para eliminar---");

        if (citas.isEmpty()) {
            System.out.println("No existen citas");
            presionaContinuar();
            return;
        }

        System.out.println("Las citas disponibles para eliminar son: ");

        tablaCitas();

        int citaEliminada = inputIntValidado("\nTeclea ID de cita a eliminar: ", 0, citas.size() - 1);

        boolean confirmado = false;

        while (!confirmado) {
            confirmado = inputStringValidado(
                    "Desea la cita con id " + citaEliminada + "? (S/N) ").toLowerCase()
                    .charAt(0) == 's';
        }

        eliminarCita(citaEliminada);
        System.out.println("Cita eliminada");
        presionaContinuar();
    }

    public static void editarCitaMenu() {
        System.out.println("--- Cambiar fecha / hora ---");

        if (citas.isEmpty()) {
            System.out.println("No existen citas");
            presionaContinuar();
            return;
        }

        System.out.println("Las citas disponibles para editar son: ");
        tablaCitas();

        int citaEditada = inputIntValidado("Teclea ID de cita a editar: ", 0, citas.size() - 1);

        String dia = inputStringValidado("Seleccionar un día nuevo (prev. " + citas.get(citaEditada)[1] + "): ");
        String hora = inputStringValidado("Seleccionar una hora nueva (prev. " + citas.get(citaEditada)[2] + "): ");

        editarCita(citaEditada, dia, hora);

        System.out.println("Cita Editada");

        presionaContinuar();
    }
    // Termina menú de citas

    // Inicia menú de pacientes

    public static void menuPacientes() {
        boolean activo = true;

        while (activo) {
            int eleccion = 0;
            limpiarPantalla();

            System.out.println("----- Registro de Pacientes ------");
            System.out.println("  1) Registrar paciente nuevo");
            System.out.println("  2) Editar pacientes");
            System.out.println("  3) Eliminar paciente");
            System.out.println("  4) Desplegar pacientes");
            System.out.println("  5) Desplegar resumen");
            System.out.println("  6) Salir del Registro de Pacientes");

            eleccion = inputIntValidado("Teclea la opción: ", 0, 6);
            limpiarPantalla();

            switch (eleccion) {
                case 1:
                    registrarPacientes();
                    break;
                case 2:
                    editarPacientesMenu();
                    break;
                case 3:
                    eliminarPacientesMenu();
                    break;
                case 4:
                    desplegarPacientes();
                    break;
                case 5:
                    desplegarResumen();
                    break;
                case 6:
                    activo = false;
                    break;

                default:
                    break;
            }
        }

    }

    public static void eliminarPacientesMenu() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes para eliminar");
            presionaContinuar();
            return;
        }
        System.out.println("Pacientes disponibles para eliminar: ");

        tablaPacientes();

        int pacienteEliminar = inputIntValidado("Ingresa el paciente a eliminar: ", 0, pacientes.size() - 1);

        boolean confirmado = false;

        while (!confirmado) {
            confirmado = inputStringValidado(
                    "Desea eliminar al paciente " + pacientes.get(pacienteEliminar)[1] + "? (S/N) ").toLowerCase()
                    .charAt(0) == 's';
        }
        eliminarPaciente(pacienteEliminar);
        System.out.println("Paciente eliminado");
        presionaContinuar();
    }

    public static void registrarPacientes() {
        limpiarPantalla();
        System.out.println("----- Registrar paciente nuevo ------");
        String cliente = inputStringValidado("Ingresar nombre del cliente: ");
        String paciente = inputStringValidado("Ingresar nombre del paciente: ");
        long celular = inputLongValidado("Ingresar número de celular del cliente: ", 0,
                99_99_99_99_99_99L);
        System.out.println("Ingresar datos del paciente: ");
        String raza = inputStringValidado("    Raza: ");
        int edad = inputIntValidado("    Edad: ", 0, 400);

        agregarPaciente(cliente, paciente, celular, raza, edad);

        System.out.printf("%15s %15s %15s %10s %6s \n", "Cliente", "Paciente", "Celular",
                "Raza", "Edad");

        System.out.printf("%15s %15s %15s %10s %6s \n", cliente, paciente, celular, raza,
                edad);

        presionaContinuar();
    }

    public static void editarPacientesMenu() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes para editar");
            presionaContinuar();
            return;
        }
        System.out.println("Pacientes disponibles para editar: ");

        tablaPacientes();

        int pacienteEditar = inputIntValidado("Ingresa el paciente a editar: ", 0, pacientes.size() - 1);

        limpiarPantalla();

        System.out.println("Datos a editar: ");

        // Datos que no cambiaran
        String paciente = pacientes.get(pacienteEditar)[1];
        String raza = pacientes.get(pacienteEditar)[3];

        long numero = inputLongValidado("  Número telefónico (prev. " + pacientes.get(pacienteEditar)[2] + "): ", 0,
                99_99_99_99_99_99L);
        String cliente = inputStringValidado("  Nombre del cliente (prev. " + pacientes.get(pacienteEditar)[0] + "): ");
        int edad = inputIntValidado("  Edad del paciente (prev. " + pacientes.get(pacienteEditar)[4] + "): ", 0, 400);

        editarPaciente(pacienteEditar, cliente, paciente, numero, raza, edad);

        limpiarPantalla();

        System.out.printf("%15s %15s %15s %10s %6s \n", "Cliente", "Paciente", "Celular", "Raza", "Edad");

        System.out.printf("%15s %15s %15s %10s %6s \n", cliente, paciente, numero, raza, edad);

        presionaContinuar();
    }

    public static void desplegarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes para desplegar");
            presionaContinuar();
            return;
        }
        limpiarPantalla();
        System.out.printf("%15s %15s %15s %10s %6s \n", "Cliente", "Paciente", "Celular",
                "Raza", "Edad");

        for (int i = 0; i < pacientes.size(); i++) {
            System.out.printf("%15s %15s %15s %10s %6s \n",
                    pacientes.get(i)[0],
                    pacientes.get(i)[1],
                    pacientes.get(i)[2],
                    pacientes.get(i)[3],
                    pacientes.get(i)[4]);
        }

        presionaContinuar();
    }

    public static void desplegarResumen() {
        System.out.println("--- Desplegar resumen ---");
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes para desplegar resumen");
            presionaContinuar();
            return;
        }

        System.out.println("Los pacientes disponibles son: ");
        tablaPacientes();

        int idPaciente = inputIntValidado("Ingresa la ID del paciente: ", 0, pacientes.size() - 1);

        System.out.println();

        System.out.println("Sus citas previas y futuras son: ");

        System.out.printf("%4s %5s %5s\n", "ID", "Día", "Hora");

        boolean existenCitas = false;

        for (int i = 0; i < citas.size(); i++) {
            int idCita = Integer.parseInt(citas.get(i)[0]);
            if (idCita == idPaciente) {
                System.out.printf("%4s %5s %5s\n", i, citas.get(i)[1], citas.get(i)[2]);
                existenCitas = true;
            }

        }

        if (!existenCitas) {
            System.out.println("No hay citas con el paciente :(");
        }

        presionaContinuar();

    }
    // Termina menú de pacientes

    public static void main(String[] args) {

        boolean activo = true;
        while (activo) {

            int eleccion = 0;

            limpiarPantalla();

            System.out.println("----- Principal ------");
            System.out.println("  1) Gestión de Citas");
            System.out.println("  2) Registro de Pacientes");
            System.out.println("  3) Salir");

            eleccion = inputIntValidado("Teclea la opción: ", 1, 3);
            limpiarPantalla();

            switch (eleccion) {
                case 1:
                    menuGestion();
                    break;
                case 2:
                    menuPacientes();
                    break;
                case 3:
                    System.out.println("Adios...");
                    activo = false;
                    break;

            }
        }
    }

}
