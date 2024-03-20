package org.example;

import java.io.*;
import java.util.ArrayList;

public class CrearArchivo {
    public static void guardarDatos(Equipo equipo, String archivo) {
        try {
            FileOutputStream fileOut = new FileOutputStream("datosNBAV3.1.obj");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(equipo);
            out.close();
            fileOut.close();
            System.out.println("Datos guardados correctamente en " + archivo);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Equipo cargarDatos(String archivo) {
        Equipo equipo = null;
        try {
            FileInputStream fileIn = new FileInputStream("datosNBAV3.1.obj");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            equipo = (Equipo) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Datos cargados correctamente desde " + archivo);
        } catch (IOException | ClassNotFoundException i) {
            System.out.println("No se pudo cargar los datos desde " + archivo);
        }
        return equipo;
    }
}
