package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Equipo equipoNBA = CrearArchivo.cargarDatos("datosNBAV3.obj");
        if (equipoNBA == null) {
            equipoNBA = new Equipo();
        }

        Scanner leer = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("¿Qué desea realizar?");
            System.out.println("1-. Mostrar lista jugadores");
            System.out.println("2-. Fichar un jugador");
            System.out.println("3-. Agregar nuevo jugador");
            System.out.println("4-. Mostrar jugadores fichados");
            System.out.println("5-. Rectificar fichaje");
            System.out.println("S-. Salir");

            String leido = leer.nextLine();
            switch (leido) {
                case "1":
                    equipoNBA.imprimirJugadoresDisponibles(); // Corrección: llamando al método correcto
                    break;
                case "2":
                    equipoNBA.ficharJugador(leer);
                    break;
                case "3":
                    equipoNBA.agregarNuevoJugador(leer);
                    break;
                case "4":
                    equipoNBA.imprimirJugadoresFichados(); // Corrección: llamando al método correcto
                    break;
                case "5":
                    equipoNBA.rectificarFichaje(leer);
                    break;
                case "S":
                    CrearArchivo.guardarDatos(equipoNBA, "datosNBAV3.obj"); // Corrección: usando el nombre correcto del archivo
                    flag = false;
                    break;
            }
        }
    }
}