package sosvet;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author García Espino Enrique Román
 * @author García Velasco Rodrigo
 * @author Enríquez Pascual Ángel Gabriel
 * @author Gastélum Sánchez Ricardo
 * 
 * @Grupo 3IM7
 */
public class SosVetVersionAntiguaMalaNoAbrir {

    public static int principalActivo = 0;

    public static ArrayList<String[]> pacientes = new ArrayList<String[]>();
    // Ejemplo de datos contenidos dentro del Array: {'nombre del cliente', 'nombre
    // del paciente', 'celular', 'raza', 'edad' }

    public static ArrayList<String[]> citas = new ArrayList<String[]>();
    // Ejemplo de datos contenidos dentro de Array: {'id paciente' ,'dia', 'hora'}

    public static void limpiarPantalla() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * @param mensaje El mensaje a mostrar al pedir el int
     * @param min     El entero minimo o igual válido
     * @param max     El entero maximo o igual válido
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
                scanner.next(); // Limpiar el buffer del Scanner

            }
        }

        return numero;
    }

    public static String inputStringValidado(String mensaje) {
        Scanner scanner = new Scanner(System.in);

        boolean valido = false;
        String inputString = "";

        while (!valido) {
            System.out.print(mensaje);
            inputString = scanner.nextLine();

            valido = inputString.length() != 0;

        }

        return inputString;
    }

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
                scanner.next(); // Limpiar el buffer del Scanner

            }
        }

        return numero;
    }

    public static void menuPrincipal() {
        System.out.println("----- Principal ------");
        System.out.println("  1) Gestión de Citas");
        System.out.println("  2) Registro de Pacientes");
        System.out.println("  3) Salir");

        principalActivo = inputIntValidado("Teclea la opción: ", 1, 3);
        limpiarPantalla();

    }

    public static void agregarPaciente(String cliente, String paciente, long celular, String raza, int edad) {
        String edadStr = "" + edad; // Se concatena una string vacia para que se convierta a string
        String numeroStr = "" + celular;
        String[] datos = { cliente, paciente, numeroStr, raza, edadStr };
        pacientes.add(datos);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        boolean activo = true;

        limpiarPantalla(); // Limpiar la pantalla antes de empezar

        while (activo) {
            menuPrincipal();

            while (principalActivo != 0) {
                switch (principalActivo) {
                    case 1:
                        int gestionActivo = 0;

                        System.out.println("----- Gestión de Citas ------");
                        System.out.println("  1) Programar");
                        System.out.println("  2) Eliminar");
                        System.out.println("  3) Editar");
                        System.out.println("  4) Salir de Gestión de Citas");

                        gestionActivo = inputIntValidado("Teclea la opción: ", 0, 4);

                        while (gestionActivo != 0) {
                            switch (gestionActivo) {
                                case 1: // Programar
                                    System.out.println("---Proporcione la siguiente información---");

                                    String nombrePaciente = inputStringValidado("Nombre del Paciente*:");
                                    String dia = inputStringValidado("Seleccionar un día disponible (DD/MM): ");
                                    String hora = inputStringValidado("Seleccionar una hora (HH:MM): ");
                                    limpiarPantalla();
                                    System.out.printf("%10s %5s %5s", "Nombre", "Día", "Hora");
                                    System.out.println();

                                    System.out.printf("%10s %5s %5s", nombrePaciente, dia, hora);
                                    System.out.println();

                                    gestionActivo = 0;

                                    break;
                                case 2: // Eliminar
                                    System.out.println("2) Uwu");
                                    gestionActivo = 0;

                                    break;
                                case 3: // Editar
                                    System.out.println("3) Uwu");
                                    gestionActivo = 0;

                                    break;
                                case 4: // Salir
                                    gestionActivo = 0;
                                    principalActivo = 0;
                                    break;
                                default:
                                    break;
                            }
                        }

                        break;
                    case 2:
                        int pacientesActivo = 0;

                        limpiarPantalla();
                        System.out.println("----- Registro de Pacientes ------");
                        System.out.println("  1) Registrar paciente nuevo");
                        System.out.println("  2) Editar pacientes");
                        System.out.println("  3) Desplegar pacientes");
                        System.out.println("  4) Desplegar resumen");
                        System.out.println("  5) Salir del Registro de Pacientes");

                        pacientesActivo = inputIntValidado("Teclea la opción: ", 0, 5);

                        while (pacientesActivo != 0) {
                            switch (pacientesActivo) {
                                case 1:
                                    limpiarPantalla();
                                    System.out.println("----- Registrar paciente nuevo ------");
                                    String cliente = inputStringValidado("Ingresar nombre del cliente: ");
                                    String paciente = inputStringValidado("Ingresar nombre del paciente: ");
                                    long celular = inputLongValidado("Ingresar número de celular del cliente: ", 0,
                                            99_99_99_99_99_99l);
                                    System.out.println("Ingresar datos del paciente: ");
                                    String raza = inputStringValidado("    Raza: ");
                                    int edad = inputIntValidado("    Edad: ", 0, 400);

                                    agregarPaciente(cliente, paciente, celular, raza, edad);

                                    System.out.printf("%10s %10s %10s %5s %2s \n", "Cliente", "Paciente", "Celular",
                                            "Raza", "Edad");

                                    System.out.printf("%10s %10s %10s %5s %2s \n", cliente, paciente, celular, raza,
                                            edad);

                                    boolean salir = false;
                                    while (!salir) {
                                        salir = !inputStringValidado("Presione cualquier caracter para continuar")
                                                .isEmpty();
                                    }

                                    pacientesActivo = 0;

                                    break;
                                case 3:

                                    System.out.printf("%10s %10s %10s %5s %2s \n", "Cliente", "Paciente", "Celular",
                                            "Raza", "Edad");
                                    for (int i = 0; i < pacientes.size(); i++) {

                                        System.out.printf("%10s %10s %10s %5s %2s \n",
                                                pacientes.get(i)[0],
                                                pacientes.get(i)[1],
                                                pacientes.get(i)[2],
                                                pacientes.get(i)[3],
                                                pacientes.get(i)[4]);
                                    }

                                    boolean mostrar = false;
                                    while (!mostrar) {
                                        mostrar = !inputStringValidado("Presione cualquier caracter para continuar")
                                                .isEmpty();
                                    }

                                    System.out.println(principalActivo);

                                    pacientesActivo = 0;
                                    break;

                                case 5:
                                    pacientesActivo = 0;
                                    principalActivo = 0;
                                    limpiarPantalla();
                                default:
                                    break;
                            }
                        }

                        break;
                    case 3:
                        System.out.println("Saliendo...");
                        activo = false;
                        principalActivo = 0;
                        break;
                    default:
                        System.out.println("que, como llegaste aqui");
                        break;
                }
            }

        }

    }

}
