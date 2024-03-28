package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.function.BiConsumer;

public class CrearArchivo {
    public static void guardarDatos(ArrayList<Jugador> jugadores, double credito, ArrayList<String> posicionesFichadas, String archivo) {
        try {
            FileOutputStream fileOut = new FileOutputStream("datosNBAV4.obj");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(jugadores);
            out.writeDouble(credito);
            out.writeObject(posicionesFichadas); // Save posicionesFichadas
            out.close();
            fileOut.close();
            System.out.println("Datos guardados correctamente en " + archivo);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static ArrayList<Object> cargarDatos(String archivo, ArrayList<String> posicionesFichadas, BiConsumer<String, ArrayList<String>> actualizarPosicionesFichadas) {
        // Your code
        ArrayList<Object> datos = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream("datosNBAV4.obj");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Jugador> jugadores = (ArrayList<Jugador>) in.readObject();
            double credito = in.readDouble();
            datos.add(jugadores);
            datos.add(credito);

            // Update posicionesFichadas based on the loaded player data
            for (Jugador jugador : jugadores) {
                if (jugador.isFichado()) {
                    actualizarPosicionesFichadas.accept(jugador.getPosicion(), posicionesFichadas);
                }
            }
            in.close();
            fileIn.close();
            System.out.println("Datos cargados correctamente desde " + archivo);
        } catch (IOException | ClassNotFoundException i) {
            System.out.println("No se pudo cargar los datos desde " + archivo);
        }
        return datos;
    }

}
