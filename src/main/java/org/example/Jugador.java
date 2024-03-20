package org.example;
import java.io.Serializable;

public class Jugador implements Serializable {
    private String nombre;
    private String posicion;
    private double salario;
    private boolean fichado;

    // Constructor
    public Jugador(String nombre, String posicion, double salario) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.salario = salario;
        this.fichado = false; // Inicialmente no está fichado
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean isFichado() {
        return fichado;
    }

    public void setFichado(boolean fichado) {
        this.fichado = fichado;
    }


    @Override
    public String toString() {
        return
                "nombre: " + nombre +
                ", posicion: " + posicion +
                ", salario: " + salario ;
    }
}

