package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Jugador> jugadores = CrearArchivo.cargarDatos("datosNBAV3.1.obj");
        if (jugadores == null) {
            jugadores = new ArrayList<>();
        }
        Scanner scanner = new Scanner(System.in);

        rellenarDatos(jugadores);
        boolean flag = true;
        while (flag) {
            System.out.println("¿Qué desea realizar?");
            System.out.println("1-. Mostrar lista jugadores");
            System.out.println("2-. Fichar un jugador");
            System.out.println("3-. Agregar nuevo jugador");
            System.out.println("4-. Mostrar jugadores fichados");
            System.out.println("5-. Rectificar fichaje");
            System.out.println("S-. Salir");

            String leido = scanner.nextLine();
            switch (leido) {
                case "1":
                    imprimirJugadoresDisponibles(jugadores, 200); // Coloca el crédito disponible aquí
                    break;
                case "2":
                    ficharJugador(jugadores, 200, scanner); // Coloca el crédito disponible aquí
                    break;
                case "3":
                    agregarNuevoJugador(jugadores, scanner);
                    break;
                case "4":
                    imprimirJugadoresFichados(jugadores);
                    break;
                case "5":
                    rectificarFichaje(jugadores, scanner);
                    break;
                case "S":
                    System.out.println("Saliendo del programa...");
                    flag = false;
                    break;
            }
        }
        CrearArchivo.guardarDatos(jugadores, "datosNBAV3.1.obj");
    }

    private static void rellenarDatos(ArrayList<Jugador> jugadores) {
        add(jugadores, "Kyrie Irving", "Base", 33);
        add(jugadores, "Chris Paul", "Base", 28);
        add(jugadores, "Russell Westbrook", "Base", 34);
        add(jugadores, "Luka Doncic", "Base", 28);

        add(jugadores, "Devin Booker", "Escolta", 29);
        add(jugadores, "Klay Thompson", "Escolta", 31);
        add(jugadores, "Donovan Mitchell", "Escolta", 32);
        add(jugadores, "Jaylen Brown", "Escolta", 28);

        add(jugadores, "Kawhi Leonard", "Alero", 38);
        add(jugadores, "Paul George", "Alero", 35);
        add(jugadores, "Khris Middleton", "Alero", 33);
        add(jugadores, "Jayson Tatum", "Alero", 34);

        add(jugadores, "Giannis Antetokounmpo", "Ala Pivot", 40);
        add(jugadores, "Anthony Davis", "Ala Pivot", 37);
        add(jugadores, "Julius Randle", "Ala Pivot", 32);
        add(jugadores, "John Collins", "Ala Pivot", 28);

        add(jugadores, "Joel Embiid", "Pivot", 37);
        add(jugadores, "Nikola Vucevic", "Pivot", 28);
        add(jugadores, "Rudy Gobert", "Pivot", 34);
        add(jugadores, "Clint Capela", "Pivot", 26);
    }

    private static void add(ArrayList<Jugador> jugadores, String nombre, String posicion, double salario) {
        jugadores.add(new Jugador(nombre, posicion, salario));
    }

    private static void imprimirJugadoresDisponibles(ArrayList<Jugador> jugadores, double credito) {
        System.out.println("JUGADORES DISPONIBLES:");
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            System.out.println((i + 1) + ". " + jugador);
        }
        double total = calcularTotal(jugadores);
        System.out.println("Salario total: $" + total);
        System.out.println("Crédito restante: $" + credito);
        System.out.println();
    }

    private static double calcularTotal(ArrayList<Jugador> jugadores) {
        double total = 0;
        for (Jugador jugador : jugadores) {
            total += jugador.getSalario();
        }
        return total;
    }

    private static void ficharJugador(ArrayList<Jugador> jugadores, double credito, Scanner scanner) {
        System.out.println("Ingrese el número del jugador que desea fichar:");
        int numeroJugador = scanner.nextInt();
        scanner.nextLine();

        if (numeroJugador < 1 || numeroJugador > jugadores.size()) {
            System.out.println("Número de jugador inválido.");
            return;
        }

        Jugador jugadorFichar = jugadores.get(numeroJugador - 1);

        if (jugadorFichar.isFichado()) {
            System.out.println("El jugador ya está fichado.");
            return;
        }

        if (credito < jugadorFichar.getSalario()) {
            System.out.println("No tienes suficiente crédito para fichar a este jugador.");
            return;
        }

        jugadorFichar.setFichado(true);
        jugadorFichar.getCredito() -= jugadorFichar.getSalario();

        System.out.println("Jugador fichado exitosamente.");
    }

    private static void agregarNuevoJugador(ArrayList<Jugador> jugadores, Scanner scanner) {
        System.out.print("Ingrese el nombre del nuevo jugador: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la posición del nuevo jugador: ");
        String posicion = scanner.nextLine();

        System.out.print("Ingrese el salario del nuevo jugador: ");
        double salario = scanner.nextDouble();
        scanner.nextLine();

        jugadores.add(new Jugador(nombre, posicion, salario));
        System.out.println("Nuevo jugador agregado exitosamente.");
    }

    private static void imprimirJugadoresFichados(ArrayList<Jugador> jugadores) {
        System.out.println("JUGADORES FICHADOS:");
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            if (jugador.isFichado()) {
                System.out.println((i + 1) + ". " + jugador);
            }
        }
    }


    private static void rectificarFichaje(ArrayList<Jugador> jugadores, Scanner scanner) {
        System.out.println("Ingrese el número del jugador que desea rectificar su fichaje:");
        int numeroJugador = scanner.nextInt();
        scanner.nextLine();

        if (numeroJugador < 1 || numeroJugador > jugadores.size()) {
            System.out.println("Número de jugador inválido.");
            return;
        }

        Jugador jugadorRectificar = jugadores.get(numeroJugador - 1);

        if (!jugadorRectificar.isFichado()) {
            System.out.println("El jugador no está fichado.");
            return;
        }

        jugadorRectificar.setFichado(false);

        System.out.println("Fichaje de " + jugadorRectificar.getNombre() + " rectificado.");
    }
}