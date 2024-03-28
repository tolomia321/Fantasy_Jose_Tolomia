package org.example;

import java.io.*;
import java.util.ArrayList;

public class CrearArchivo {
    public static void guardarDatos(ArrayList<Jugador> jugadores, double credito, String archivo) {
        try {
            FileOutputStream fileOut = new FileOutputStream("datosNBAV3.1.obj");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(jugadores);
            out.writeDouble(credito);
            out.close();
            fileOut.close();
            System.out.println("Datos guardados correctamente en " + archivo);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static ArrayList<Object> cargarDatos(String archivo) {
        ArrayList<Object> datos = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream("datosNBAV3.1.obj");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Jugador> jugadores = (ArrayList<Jugador>) in.readObject();
            double credito = in.readDouble();
            datos.add(jugadores);
            datos.add(credito);
            in.close();
            fileIn.close();
            System.out.println("Datos cargados correctamente desde " + archivo);
        } catch (IOException | ClassNotFoundException i) {
            System.out.println("No se pudo cargar los datos desde " + archivo);
        }
        return datos;
    }
}
