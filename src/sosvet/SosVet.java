package sosvet;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author García Espino Enrique Román
 * @author García Velasco Rodrigo
 * @author Enríquez Pascual Ángel Gabriel
 * @author Gastélum Sánchez Ricardo
 * @version 0.0.3
 */
// Grupo: 3IM7
public class SosVet {
    /*
     * Un arraylist es una lista, eso significa que al contrario de los arreglos
     * comunes,
     * es de tamaño variable.
     * 
     * Es decir, podemos tener un ArrayList con 2434242 elementos o con 2 elementos,
     * se pueden agregar, editar y eliminar elementos a conveniencia.
     */

    public static ArrayList<String[]> pacientes = new ArrayList<>();
    /*
     * Se crea un ArrayList para contener los pacientes, contendrá arreglos de
     * Strings,
     * cada arreglo tendrá un largo de 6, y se guardaran los siguientes datos en
     * orden:
     * 
     * {'nombre del cliente', 'nombre del paciente', 'celular', 'raza', 'edad',
     * 'especie' }
     */

    public static ArrayList<String[]> citas = new ArrayList<>();
    /*
     * Se crea un ArrayList para contener las citas, contendrá arreglos de
     * Strings,
     * cada arreglo tendrá un largo de 4, y se guardaran los siguientes datos en
     * orden:
     * {'id paciente' ,'dia', 'hora', 'veterinario asignado'}
     * 
     * Para acceder a los elementos dentro del ArrayList, se utiliza el método
     * arr.get(indice), este metodo te regresa el elemento en la posicion indice
     * especificada
     */

    /**
     * Imprime una secuencia de caracteres que limpia la pantalla. <br>
     * <br>
     * NOTA: Solamente funciona con terminales que acepten el Código escape ANSI,
     * de lo contario se imprimira la secuencia sin ningún efecto. <br>
     *
     * <a href=
     * "https://en.wikipedia.org/wiki/ANSI_escape_code#CSI_(Control_Sequence_Introducer)_sequences">
     * Tabla de valores de escape ANSI
     * </a>
     */
    public static void limpiarPantalla() {
        System.out.println("\033[H\033[2J");
        /*
         * El primer codigo de escape: [H, mueve el cursor hasta arriba a la izquierda.
         * El segundo, limpia la pantalla entera.
         */

        System.out.flush(); // Se limpia el bufer de escritura de la consola.

    }

    public static void presionaContinuar() {
        System.out.print("Presiona cualquier tecla para continuar...");
        new Scanner(System.in).nextLine(); // Espera a que el usuario presione Enter
        System.out.println(); // Salto de linea
    }

    /**
     * Recibe un Arraylist con la misma estructura que el de pacientes.
     *
     * @param arr       El arrayList a ordenar por edad
     * @param creciente Si será creciente o decreciente
     * @author García Espino Enrique Román
     */
    public static void ordenarBurbuja(ArrayList<String[]> arr, boolean creciente) {
        int[][] edadesOrdenadas = new int[arr.size()][2];

        for (int i = 0; i < arr.size(); i++) {
            // Crea un arreglo donde se guarda el valor de la edad y el indice original en
            // otro arreglo.
            // Estructura: { {indice1, edad1}, {indice2, edad2}, ...}
            int edad = Integer.parseInt(arr.get(i)[4]); // Se convierte la string a entero

            edadesOrdenadas[i][0] = i;
            edadesOrdenadas[i][1] = edad;

        }

        for (int i = 0; i < edadesOrdenadas.length; i++) {
            // Se inicia el ordenamiento lento o de burbuja
            /*
             * Según Wikipedia:
             * Funciona revisando cada elemento de la lista que va a ser ordenada con el
             * siguiente,
             * intercambiándolos de posición si están en el orden equivocado. Es necesario
             * revisar
             * varias veces toda la lista hasta que no se necesiten más intercambios, lo
             * cual
             * significa que la lista está ordenada. Este algoritmo obtiene su nombre de la
             * forma con la que suben por la lista los elementos durante los intercambios,
             * como si fueran pequeñas «burbujas». También es conocido como el método del
             * intercambio directo. Dado que solo usa comparaciones para operar elementos,
             * se lo considera un algoritmo de comparación, siendo uno de los más sencillos
             * de implementar.
             * 
             * Fuente: https://w.wiki/8HSF
             */

            // En resumen: Por cada elemento se checa si su elemento siguiente cumple el
            // criterio, si si, lo cambia.
            // se repite hasta que está ordenado el arreglo completo.

            for (int j = 0; j < edadesOrdenadas.length - 1; j++) {
                int edad = edadesOrdenadas[j][1];
                int edadSig = edadesOrdenadas[j + 1][1];

                if (creciente) {
                    if (edad > edadSig)
                        edadesOrdenadas = swapIntArrays(j, j + 1, edadesOrdenadas);
                } else {
                    if (edad < edadSig)
                        edadesOrdenadas = swapIntArrays(j, j + 1, edadesOrdenadas);
                }

            }

        }

        String[][] pacientesNuevos = new String[arr.size()][6];
        /*
         * Se crea un nuevo ArrayList con la misma estructura que
         * el original de pacientes para guardar los datos ordenados.
         */

        System.out.printf("%4s %15s %10s \n", "ID", "Paciente", "Edad");
        for (int i = 0; i < edadesOrdenadas.length; i++) {
            pacientesNuevos[i] = (pacientes.get(edadesOrdenadas[i][0]));
            // Se añaden los valores ordenados

            // Se imprime la ID original, el nombre y la edad.
            System.out.printf("%4s %15s %10s \n", edadesOrdenadas[i][0], pacientesNuevos[i][1],
                    pacientesNuevos[i][4]);
        }

    }

    /**
     * Intercambia dos valores en los indices especificados dentro de arreglos de
     * dos dimensiones
     *
     * @param index1 El primer elemento a intercambiar
     * @param index2 El segundo elemento a intercambiar
     * @param arr    El arreglo donde se hará el intercambio
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
        String idStr = "" + id; // Convierte el entero en una cadena.
        String[] datos = { idStr, dia, hora, vetAsignado };
        citas.add(datos);
    }

    /**
     * @param indice El indice de la cita a eliminar
     */
    public static void eliminarCita(int indice) {
        citas.remove(indice);
    }

    /**
     * @param indice              El indice de la cita a editar
     * @param dia                 El dia en formato DD/MM
     * @param hora                La hora en formato HH:MM
     * @param veterinarioAsignado El veterinario asignado a la cita
     */
    public static void editarCita(int indice, String dia, String hora, String veterinarioAsignado) {
        String[] datos = { citas.get(indice)[0], dia, hora, veterinarioAsignado };
        citas.set(indice, datos);
    }

    /**
     * @param cliente  Nombre del cliente (dueño de la mascota)
     * @param paciente Nombre del paciente (mascota)
     * @param celular  Número celular del cliente
     * @param raza     Raza de la mascota
     * @param edad     Edad de la mascota
     * @param especie  La especie de la mascota.
     */
    public static void agregarPaciente(String cliente, String paciente, long celular, String raza, int edad,
            String especie) {
        String edadStr = "" + edad; // Se concatena una string vacia para que se convierta a string
        String numeroStr = "" + celular;
        String[] datos = { cliente, paciente, numeroStr, raza, edadStr, especie };
        pacientes.add(datos);
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
    public static void editarPaciente(int id, String cliente, String paciente, long celular, String raza, int edad,
            String especie) {
        String edadStr = "" + edad; // Se concatena una string vacia para que se convierta a string
        String numeroStr = "" + celular;
        String[] datos = { cliente, paciente, numeroStr, raza, edadStr, especie };

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
        // eleccion = inputIntValidado("Teclea la opción: ", 0, 4); // ejemplo de uso:
        Scanner scanner = new Scanner(System.in);

        boolean valido = false;
        int num = min - 1;
        while (!valido) {
            System.out.print(mensaje);

            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                valido = num <= max && num >= min;

                if (!valido)
                    System.out.println("Respuesta inválida!!");
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
                num = scanner.nextLong();
                valido = num <= max && num >= min;

                if (!valido)
                    System.out.println("Respuesta inválida!!");
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
     * @param mensaje El mensaje a mostrar sin salto de línea.
     * @return La cadena de texto no vacía.
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

    /**
     * Imprime la tabla de pacientes, con la id y el nombre
     */
    public static void tablaPacientes() {
        System.out.printf("%4s %15s \n", "ID", "Paciente");

        for (int i = 0; i < pacientes.size(); i++) {
            System.out.printf("%4s %15s \n", i, pacientes.get(i)[1]);
        }
    }

    /**
     * Imprime la tabla de citas, con el paciente, id, dia, hora y vet. asignado.
     */
    public static void tablaCitas() {
        System.out.printf("%15s %4s %5s %5s %15s\n", "Paciente", "ID", "Día", "Hora", "Veterinario asignado");

        for (int i = 0; i < citas.size(); i++) {
            System.out.printf("%15s %4s %5s %5s %15s\n",
                    pacientes.get(Integer.parseInt(citas.get(i)[0]))[1],
                    i,
                    citas.get(i)[1],
                    citas.get(i)[2],
                    citas.get(i)[3]);

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

        int dia = inputIntValidado("Selecciona un día del mes (DD): ", 1, 31);
        int mes = inputIntValidado("Selecciona un mes númerico (MM): ", 1, 31);
        int año = inputIntValidado("Selecciona un año (AAAA) : ", 2023, 3000);
        String fecha = dia + "/" + mes + "/" + año;

        int hora = inputIntValidado("Ingresa la hora en formato de 24 horas (HH): ", 0, 24);
        int minutos = inputIntValidado("Ingresa los minutos (MM): ", 0, 59);

        String horaString = hora + ":" + minutos;

        String vetAsignado = inputStringValidado("Ingresa el veterinario asignado a la cita: ");

        String nombrePaciente = pacientes.get(idPaciente)[1];

        limpiarPantalla();

        agendarCita(idPaciente, fecha, horaString, vetAsignado);
        System.out.printf("%10s %5s %5s\n", "Nombre", "Día", "Hora");
        System.out.printf("%10s %5s %5s\n", nombrePaciente, fecha, hora);

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
            char opcion = inputStringValidado(
                    "Desea la cita con id " + citaEliminada + "? (S/N) ").toLowerCase()
                    .charAt(0);
            if (opcion == 'n')
                return;
            confirmado = opcion == 's';
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

        System.out.printf("Selecciona una fecha nueva (previamente: %s):\n", citas.get(citaEditada)[1]);

        int dia = inputIntValidado("Selecciona un día del mes (DD): ", 1, 31);
        int mes = inputIntValidado("Selecciona un mes númerico (MM): ", 1, 31);
        int año = inputIntValidado("Selecciona un año (AAAA) : ", 2023, 3000);
        String fecha = dia + "/" + mes + "/" + año;

        System.out.println("Seleccionar una hora nueva (prev. " + citas.get(citaEditada)[2] + "): ");
        int hora = inputIntValidado("Ingresa la hora en formato de 24 horas (HH): ", 0, 24);
        int minutos = inputIntValidado("Ingresa los minutos (MM): ", 0, 59);

        String horaString = hora + ":" + minutos;

        System.out.printf("Selecciona un veterinario asignado nuevo (prev. %s): ", citas.get(citaEditada)[3]);
        String vet = inputStringValidado("");

        editarCita(citaEditada, fecha, horaString, vet);

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

        char eleccion = inputStringValidado("Criterios disponibles para ordenar Edad (E): ").toLowerCase()
                .charAt(0);
        if (eleccion == 'e') {
            char creciente = inputStringValidado("Deseas ordenar de menor a mayor (S/N): ").toLowerCase().charAt(0);

            ordenarBurbuja(pacientes, creciente == 's');
            presionaContinuar();
        } else {
            System.out.println("Nonoonoonon q haces ");
            presionaContinuar();
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
                    "Desea eliminar al paciente " + pacientes.get(pacienteEliminar)[1] + "? (S/N) ").toLowerCase()
                    .charAt(0);
            if (eleccion == 'n')
                return;
            else
                confirmado = true;

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

        // Datos que no cambiaran
        String paciente = pacientes.get(pacienteEditar)[1];
        String raza = pacientes.get(pacienteEditar)[3];
        String especie = pacientes.get(pacienteEditar)[5];

        long numero = inputLongValidado("  Número telefónico (prev. " + pacientes.get(pacienteEditar)[2] + "): ", 0,
                99_99_99_99_99_99L);
        String cliente = inputStringValidado("  Nombre del cliente (prev. " + pacientes.get(pacienteEditar)[0] + "): ");
        int edad = inputIntValidado("  Edad del paciente (prev. " + pacientes.get(pacienteEditar)[4] + "): ", 0, 400);

        editarPaciente(pacienteEditar, cliente, paciente, numero, raza, edad, especie);

        limpiarPantalla();

        System.out.printf("%15s %15s %15s %10s %15s %6s \n", "Cliente", "Paciente", "Celular",
                "Raza", "Especie", "Edad");

        System.out.printf("%15s %15s %15s %10s %15s %6s \n", cliente, paciente, numero, raza, especie,
                edad);

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
                    pacientes.get(i)[0],
                    pacientes.get(i)[1],
                    pacientes.get(i)[2],
                    pacientes.get(i)[3],
                    pacientes.get(i)[5],
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

        // Valores de prueba
        agregarPaciente("Pedro", "Max", 66_6777_8888L, "Labrador", 5, "Perro");
        agregarPaciente("Juan", "Bella", 66_6777_8888L, "Siames", 6, "Gato");
        agregarPaciente("Ricardo", "Rocky", 66_6777_8888L, "Bulldog", 7, "Perro");
        agregarPaciente("Rodrigo", "Luna", 66_6777_8888L, "Persa", 1, "Gato");
        agregarPaciente("Luna", "Charlie", 66_6777_8888L, "Golden Retriever", 8, "Perro");
        agregarPaciente("Juan 2", "Daisy", 66_6777_8888L, "Maine Coon", 2, "Gato");
        agregarPaciente("Pedro 2", "Leo", 66_6777_8888L, "Poodle", 4, "Perro");
        agregarPaciente("Ángel", "Mia", 66_6777_8888L, "Ragdoll", 8, "Gato");
        agregarPaciente("Juan 3", "Coco", 66_6777_8888L, "Labrador", 6, "Perro");
        agregarPaciente("Juan 4", "Oreo", 66_6777_8888L, "Siames", 1, "Gato");

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
                    String gato = "\u001B[101m \u001B[96m";
                    // Codigos de escape para poner el fondo en rojo brillante y las letras en cian
                    for (int i = 0; i < 50; i++) {
                        // Agregamos 400 lineas blancas con el fondo para que se vea el fondo.
                        gato += "\n";
                    }
                    gato += " /\\_/\\ \n( o.o )\n > ^ < \u001B[0m\u001B[0m";
                    // Contiene los codigos de escape para resetearlo.
                    // Fuente: https://en.wikipedia.org/wiki/ANSI_escape_code#3-bit_and_4-bit

                    System.out.println(gato);
                    activo = false;
                    break;
            }
        }
    }

}
