package sosvet;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author García Espino Enrique Román
 * @author García Velasco Rodrigo
 * @author Enríquez Pascual Ángel Gabriel
 * @author Gastélum Sánchez Ricardo
 * @version 0.1.0
 */
// Grupo:   3IM7
public class SosVet {

    public static ArrayList<Paciente> pacientes = new ArrayList<>();
    // Ejemplo de datos contenidos dentro del Array: {'nombre del cliente', 'nombre
    // del paciente', 'celular', 'raza', 'edad', 'especie' }

    public static ArrayList<Cita> citas = new ArrayList<>();
    // Ejemplo de datos contenidos dentro de Array: {'id paciente' ,'dia', 'hora', 'veterinario asignado'}

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
     * @param arr       El arrayList a ordenar por edad
     * @param creciente Si será creciente o decreciente
     * @return Un arreglo de los pacientes en el orden especificado
     */
    public static Paciente[] ordenarPrueba(ArrayList<Paciente> arr, boolean creciente) {
        int[][] edadesOrdenadas = new int[arr.size()][2];
        // Se creara un arreglo del tamaño de pacientes x 2
        // en el primer valor se guarda la edad,
        // en el segundo valor se guarda el indice.

        for (int i = 0; i < arr.size(); i++) {
            edadesOrdenadas[i][1] = arr.get(i).getEdad();
            edadesOrdenadas[i][0] = i;

        }

        for (int i = 0; i < edadesOrdenadas.length; i++) {
            for (int j = 0; j < edadesOrdenadas.length - 1; j++) {
                int edad = edadesOrdenadas[j][1];
                int edadSig = edadesOrdenadas[j + 1][1];

                if (creciente) {
                    if (edad > edadSig) edadesOrdenadas = swapIntArrays(j, j + 1, edadesOrdenadas);
                } else {
                    if (edad < edadSig) edadesOrdenadas = swapIntArrays(j, j + 1, edadesOrdenadas);
                }


            }


        }

        Paciente[] pacientesOrdenados = new Paciente[arr.size()];

        System.out.printf("%4s %15s %10s \n", "ID", "Paciente", "Edad");
        for (int i = 0; i < edadesOrdenadas.length; i++) {
            pacientesOrdenados[i] = (pacientes.get(edadesOrdenadas[i][0]));

            System.out.printf("%4s %15s %10s \n", edadesOrdenadas[i][0],
                    pacientesOrdenados[i].getNombreCliente(),
                    pacientesOrdenados[i].getEdad());
        }
        return pacientesOrdenados;

    }

    /**
     * @param index1 El primer elemento a swappear
     * @param index2 El segundo elemento a swappear
     * @param arr    El arreglo donde se hará el swapeo
     * @return Nuevo arreglo con el cambio efectuado
     */

    public static int[][] swapIntArrays(int index1, int index2, int[][] arr) {
        int[][] nuevoArr = arr;

        int[] temp1 = arr[index1];
        int[] temp2 = arr[index2];

        nuevoArr[index1] = temp2;
        nuevoArr[index2] = temp1;

        return nuevoArr;
    }

    /**
     * @param id          El ID del paciente a asignar la cita
     * @param dia         El dia en formato DD/MM
     * @param hora        La hora en formato HH:MM
     * @param vetAsignado El nombre del Veteriniario asignado a la cita
     */
    public static void agendarCita(int id, String dia, String hora, String vetAsignado) {
        Cita citaNueva = new Cita();
        citaNueva.setDia(dia);
        citaNueva.setHora(hora);
        citaNueva.setVetAsignado(vetAsignado);
        citaNueva.setPaciente(pacientes.get(id));

        citas.add(citaNueva);
    }

    /**
     * @param indice El indice de la cita a eliminar
     */
    public static void eliminarCita(int indice) {
        citas.remove(indice);
    }

    /**
     * @param indice      El indice de la cita a editar
     * @param dia         El dia en formato DD/MM
     * @param hora        La hora en formato HH:MM
     * @param vetAsignado El veterinario asignado
     */
    public static void editarCita(int indice, String dia, String hora, String vetAsignado) {
        Cita citaEditada = citas.get(indice);
        citaEditada.setDia(dia);
        citaEditada.setHora(hora);
        citaEditada.setVetAsignado(vetAsignado);

        // citas.set(indice, citaEditada);
        // No es necesario porque ChatGPT me dijo que solo es una referencia al objeto original.
    }

    /**
     * @param cliente  Nombre del cliente (dueño de la mascota)
     * @param paciente Nombre del paciente (mascota)
     * @param celular  Número celular del cliente
     * @param raza     Raza de la mascota
     * @param edad     Edad de la mascota
     */
    public static void agregarPaciente(String cliente, String paciente, long celular, String raza, int edad, String especie) {
        Paciente pacienteNuevo = new Paciente();
        pacienteNuevo.setCelular(celular);
        pacienteNuevo.setEdad(edad);
        pacienteNuevo.setEspecie(especie);
        pacienteNuevo.setNombreCliente(cliente);
        pacienteNuevo.setNombrePaciente(paciente);
        pacienteNuevo.setRaza(raza);

        pacientes.add(pacienteNuevo);
    }

    /**
     * @param id       Indice dentro del ArrayList del elemento
     * @param cliente  Nombre del cliente (dueño de la mascota)
     * @param paciente Nombre del paciente (mascota)
     * @param celular  Número celular del cliente
     * @param raza     Raza de la mascota
     * @param edad     Edad de la mascota
     * @param especie  La especie de la mascota
     */
    public static void editarPaciente(int id, String cliente, String paciente, long celular, String raza, int edad, String especie) {
        Paciente pacienteEditado = pacientes.get(id);
        pacienteEditado.setCelular(celular);
        pacienteEditado.setEdad(edad);
        pacienteEditado.setEspecie(especie);
        pacienteEditado.setNombreCliente(cliente);
        pacienteEditado.setNombrePaciente(paciente);
        pacienteEditado.setRaza(raza);

        pacientes.set(id, pacienteEditado);
        // No es necesario porque ChatGPT me dijo que solo es una referencia al objeto original.
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
        //eleccion = inputIntValidado("Teclea la opción: ", 0, 4); // ejemplo de uso:
        Scanner scanner = new Scanner(System.in);

        boolean valido = false;
        int num = min - 1;
        while (!valido) {
            System.out.print(mensaje);

            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                valido = num <= max && num >= min;

                if (!valido) System.out.println("Respuesta inválida!!");
            } else {
                scanner.next();
                System.out.println("Ingresa un número entero.");
            }

        }

        return num;

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
        long num = min - 1;
        while (!valido) {
            System.out.print(mensaje);

            if (scanner.hasNextLong()) {
                num = scanner.nextInt();
                valido = num <= max && num >= min;

                if (!valido) System.out.println("Respuesta inválida!!");
            } else {
                scanner.next();
                System.out.println("Ingresa un número entero.");
            }

        }

        return num;
    }

    /**
     * Pide una cadena de texto al usuario y lo repetirá hasta que se introduzca
     * una cadena valida
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
            System.out.printf("%4s %15s \n", i, pacientes.get(i).getNombrePaciente());
        }
    }

    public static void tablaCitas() {
        System.out.printf("%15s %4s %5s %5s %15s\n", "Paciente", "ID", "Día", "Hora", "Veterinario asignado");

        for (int i = 0; i < citas.size(); i++) {
            Cita cita = citas.get(i);
            String pacienteNombre = cita.getPaciente().getNombrePaciente();
            String dia = cita.getDia();
            String hora = cita.getHora();
            String vetAsignado = cita.getVetAsignado();

            System.out.printf("%15s %4s %5s %5s %15s\n",
                    pacienteNombre,
                    i,
                    dia,
                    hora,
                    vetAsignado
            );

        }
    }

    // Comienzan los procedimientos principales
    // Inicia menú de citas
    public static void menuGestion() {
        boolean activo = true;
        while (activo) {
            limpiarPantalla();

            System.out.println("----- Gestión de Citas ------");
            System.out.println("  1) Programar");
            System.out.println("  2) Eliminar");
            System.out.println("  3) Editar");
            System.out.println("  4) Salir de Gestión de Citas");

            int eleccion = inputIntValidado("Teclea la opción: ", 0, 4);
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
        String vetAsignado = inputStringValidado("Ingresa el veterinario asignado a la cita: ");

        String nombrePaciente = pacientes.get(idPaciente).getNombrePaciente();

        limpiarPantalla();

        agendarCita(idPaciente, dia, hora, vetAsignado);
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
        Cita cita = citas.get(citaEditada);

        //TODO: Cambiar a printf
        String dia = inputStringValidado("Seleccionar un día nuevo (prev. " + cita.getDia() + "): ");
        String hora = inputStringValidado("Seleccionar una hora nueva (prev. " + cita.getHora() + "): ");
        String vetAsignado = inputStringValidado("Seleccionar una veterinario nuevo (prev. " + cita.getVetAsignado() + "): ");

        editarCita(citaEditada, dia, hora, vetAsignado);

        System.out.println("Cita Editada");

        presionaContinuar();
    }
    // Termina menú de citas

    // Inicia menú de pacientes
    public static void menuPacientes() {
        boolean activo = true;

        while (activo) {
            limpiarPantalla();

            System.out.println("----- Registro de Pacientes ------");
            System.out.println("  1) Registrar paciente nuevo");
            System.out.println("  2) Editar pacientes");
            System.out.println("  3) Eliminar paciente");
            System.out.println("  4) Desplegar pacientes");
            System.out.println("  5) Desplegar resumen");
            System.out.println("  6) Ordenar por criterio");
            System.out.println("  7) Salir del Registro de Pacientes");

            int eleccion = inputIntValidado("Teclea la opción: ", 0, 7);
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
                    ordenarPorCriterio();
                    break;
                case 7:
                    activo = false;
                    break;

                default:
                    break;
            }
        }

    }

    public static void ordenarPorCriterio() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes para ordenar");
            presionaContinuar();
            return;
        }

        char eleccion = inputStringValidado("Criterios disponibles para ordenar Edad (E), Nombre (N): ").toLowerCase().charAt(0);
        switch (eleccion) {
            case 'e':
                char creciente = inputStringValidado("Deseas ordenar de menor a mayor (S/N): ").toLowerCase().charAt(0);

                ordenarPrueba(pacientes, creciente == 's');
                break;
            case 'n':
                System.out.println("Aún no está implementado :(");
                break;
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
            char eleccion = inputStringValidado(
                    "Desea eliminar al paciente " + pacientes.get(pacienteEliminar).getNombrePaciente() + "? (S/N) ").toLowerCase()
                    .charAt(0);
            if (eleccion == 'n') return;
            else confirmado = true;

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
        String especie = inputStringValidado("    Especie: ");
        String raza = inputStringValidado("    Raza: ");
        int edad = inputIntValidado("    Edad: ", 0, 400);

        agregarPaciente(cliente, paciente, celular, raza, edad, especie);

        System.out.printf("%15s %15s %15s %10s %15s %6s \n", "Cliente", "Paciente", "Celular",
                "Raza", "Especie", "Edad");

        System.out.printf("%15s %15s %15s %10s %15s %6s \n", cliente, paciente, celular, raza, especie,
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

        Paciente paciente = pacientes.get(pacienteEditar);

        long numero = inputLongValidado("  Número telefónico (prev. " + paciente.getCelular() + "): ", 0,
                99_99_99_99_99_99L);
        String cliente = inputStringValidado("  Nombre del cliente (prev. " + paciente.getNombreCliente() + "): ");
        int edad = inputIntValidado("  Edad del paciente (prev. " + paciente.getEdad() + "): ", 0, 400);

        editarPaciente(pacienteEditar, cliente, paciente.getNombrePaciente(), numero, paciente.getRaza(), edad, paciente.getEspecie());

        limpiarPantalla();

        System.out.printf("%15s %15s %15s %10s %15s %6s \n", "Cliente", "Paciente", "Celular",
                "Raza", "Especie", "Edad");

        System.out.printf("%15s %15s %15s %10s %15s %6s \n", cliente, paciente, numero, paciente.getRaza(),
                paciente.getEspecie(), edad);

        presionaContinuar();
    }

    public static void desplegarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes para desplegar");
            presionaContinuar();
            return;
        }
        limpiarPantalla();
        System.out.printf("%15s %15s %15s %10s %15s %6s \n", "Cliente", "Paciente", "Celular",
                "Raza", "Especie", "Edad");

        for (int i = 0; i < pacientes.size(); i++) {
            System.out.printf("%15s %15s %15s %10s %15s %6s \n",
                    pacientes.get(i).getNombreCliente(),
                    pacientes.get(i).getNombrePaciente(),
                    pacientes.get(i).getCelular(),
                    pacientes.get(i).getRaza(),
                    pacientes.get(i).getEspecie(),
                    pacientes.get(i).getEdad());
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
        Paciente pacienteSeleccionado = pacientes.get(idPaciente);
        System.out.println();

        System.out.println("Sus citas previas y futuras son: ");

        System.out.printf("%4s %5s %5s\n", "ID", "Día", "Hora");

        boolean existenCitas = false;

        for (int i = 0; i < citas.size(); i++) {
            Paciente pacienteCitas = citas.get(i).getPaciente();

            if (pacienteCitas.equals(pacienteSeleccionado)) {
                System.out.printf("%4s %5s %5s\n", i, citas.get(i).getDia(), citas.get(i).getHora());
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
        agregarPaciente("Pedro", "Max", 123456789, "Labrador", 5, "Perro");
        agregarPaciente("Juan", "Bella", 987654321, "Siames", 6, "Gato");
        agregarPaciente("Ricardo", "Rocky", 555555555, "Bulldog", 7, "Perro");
        agregarPaciente("Rodrigo", "Luna", 123123123, "Persa", 1, "Gato");
        agregarPaciente("Luna", "Charlie", 999888777, "Golden Retriever", 8, "Perro");
        agregarPaciente("Juan 2", "Daisy", 111222333, "Maine Coon", 2, "Gato");
        agregarPaciente("Pedro 2", "Leo", 444555666, "Poodle", 4, "Perro");
        agregarPaciente("Ángel", "Mia", 777777777, "Ragdoll", 8, "Gato");
        agregarPaciente("Juan 3", "Coco", 888777666, "Labrador", 6, "Perro");
        agregarPaciente("Juan 4", "Oreo", 666777888, "Siames", 1, "Gato");
        for (int i = 0; i < 200; i++) {
            agregarPaciente("Juan" + (i+1), "Oreo " + (i+1), 666777888 + i, "Siames " + (i + 1), (int)(Math.random() * (20+i) ), "Gato");
        }

        boolean activo = true;

        while (activo) {
            limpiarPantalla();

            System.out.println("----- Principal ------");
            System.out.println("  1) Gestión de Citas");
            System.out.println("  2) Registro de Pacientes");
            System.out.println("  3) Salir");

            int eleccion = inputIntValidado("Teclea la opción: ", 1, 3);
            limpiarPantalla();

            switch (eleccion) {
                case 1:
                    menuGestion();
                    break;
                case 2:
                    menuPacientes();
                    break;
                case 3:
                    System.out.println("Adios... :(");
                    activo = false;
                    break;
            }
        }
    }

}
