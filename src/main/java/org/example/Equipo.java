package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Equipo implements Serializable {
    private ArrayList<Jugador> jugadores;
    private double credito;

    public Equipo() {
        this.jugadores = new ArrayList<>();
        this.credito = 200;
        rellenarDatos();
    }

    public void rellenarDatos() {
        jugadores.clear();
        add("Kyrie Irving", "Base", 33);
        add("Chris Paul", "Base", 28);
        add("Russell Westbrook", "Base", 34);
        add("Luka Doncic", "Base", 28);

        add("Devin Booker", "Escolta", 29);
        add("Klay Thompson", "Escolta", 31);
        add("Donovan Mitchell", "Escolta", 32);
        add("Jaylen Brown", "Escolta", 28);

        add("Kawhi Leonard", "Alero", 38);
        add("Paul George", "Alero", 35);
        add("Khris Middleton", "Alero", 33);
        add("Jayson Tatum", "Alero", 34);

        add("Giannis Antetokounmpo", "Ala Pivot", 40);
        add("Anthony Davis", "Ala Pivot", 37);
        add("Julius Randle", "Ala Pivot", 32);
        add("John Collins", "Ala Pivot", 28);

        add("Joel Embiid", "Pivot", 37);
        add("Nikola Vucevic", "Pivot", 28);
        add("Rudy Gobert", "Pivot", 34);
        add("Clint Capela", "Pivot", 26);
    }

    public void imprimirJugadoresDisponibles() {
        System.out.println("JUGADORES DISPONIBLES:");
        for (Jugador jugador : jugadores) {
            System.out.println(jugador);
        }
        double total = calcularTotal();
        System.out.println("Salario total: $" + total);
        System.out.println("Crédito restante: $" + credito);
        System.out.println();
    }

    public void imprimirJugadoresFichados() {

    }

    public void ficharJugador(Scanner leer) {

    }

    public void agregarNuevoJugador(Scanner leer) {

    }

    public void rectificarFichaje(Scanner leer) {

    }

    private double calcularTotal() {
        double total = 0;
        for (Jugador jugador : jugadores) {
            total += jugador.getSalario();
        }
        return total;
    }

    private void add(String nombre, String posicion, double salario) {
        jugadores.add(new Jugador(nombre, posicion, salario));
    }
}
