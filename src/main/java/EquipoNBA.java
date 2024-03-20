
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EquipoNBA implements Serializable {

    private ArrayList<Double> salarios;
    private ArrayList<String> nombres;
    private ArrayList<String> posiciones;
    private ArrayList<Double> salariosFichados;
    private ArrayList<String> nombresFichados;
    private ArrayList<String> posicionesFichadas;
    private double credito;

    public EquipoNBA() {
        this.salarios = new ArrayList<>();
        this.nombres = new ArrayList<>();
        this.posiciones = new ArrayList<>();
        this.salariosFichados = new ArrayList<>();
        this.nombresFichados = new ArrayList<>();
        this.posicionesFichadas = new ArrayList<>();
        this.credito = 200;
        cargarDatos();
    }

    public static void main(String[] args) {
        EquipoNBA equipoNBA = new EquipoNBA();
        equipoNBA.ejecutar();
    }

    private void ejecutar() {
        boolean flag = true;
        Scanner leer = new Scanner(System.in);
        String leido = null;
        int nleido;

        if (salarios.isEmpty()) {
            rellenaDatos();
        }

        rellenaDatos();
        while (flag) {
            System.out.println("¿Qué desea realizar?");
            System.out.println("1-. Mostrar lista jugadores");
            System.out.println("2-. Fichar un jugador");
            System.out.println("3-. Agregar nuevo jugador");
            System.out.println("4-. Mostrar jugadores fichados");
            System.out.println("5-. Rectificar fichaje");
            System.out.println("S-. Salir");

            leido = leer.nextLine();
            switch (leido) {
                case "1":
                    imprimirFichables();
                    break;
                case "2":
                    mostrarJugadoresPorPosicion(leer);
                    System.out.println();
                    System.out.println("Introduce el número del jugador a fichar: ");
                    nleido = leer.nextInt();
                    leer.nextLine();
                    fichar(nleido -1);
                    break;
                case "3":
                    agregarNuevoJugador(leer);
                    break;
                case "4":
                    imprimirFichados();
                    break;
                case "5":
                    rectificarFichaje(leer);
                    break;
                case "S":
                    guardarDatos();
                    flag = false;
                    break;
            }
        }
        guardarDatos();
    }

    private void rellenaDatos() {
        salarios.clear();
        nombres.clear();
        posiciones.clear();
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

    private void imprimirFichables() {
        System.out.println("JUGADORES FICHABLES:");
        for (int i = 0; i < salarios.size(); i++) {
            if (salarios.get(i) <= credito) {
                System.out.println((i + 1) + ". " + posiciones.get(i) + " " + nombres.get(i) + " " + salarios.get(i));
            }
        }
        double total = calcularTotal();
        System.out.println("Salario total: $" + total);
        System.out.println("Crédito restante: $" + credito);
        System.out.println();
    }

    private void imprimirFichados() {
        System.out.println("JUGADORES FICHADOS:");
        for (int i = 0; i < salariosFichados.size(); i++) {
            System.out.println((i + 1) + ". " + posicionesFichadas.get(i) + " " + nombresFichados.get(i) + " " + salariosFichados.get(i));
        }
        System.out.println();
    }

    private double calcularTotal() {
        double total = 0;
        for (double salario : salarios) {
            total += salario;
        }
        return total;
    }

    private void fichar(int n) {
        if (n >= 0 && n < posiciones.size() && salarios.get(n) <= credito) {
            String posicionFichada = posiciones.get(n);
            if (puedeFicharJugadorDePosicion(posicionFichada)) {
                posicionesFichadas.add(posicionFichada);
                nombresFichados.add(nombres.get(n));
                double s = salarios.get(n);
                salariosFichados.add(s);
                credito -= s;

                eliminarJugador(n);
                System.out.println();
                System.out.println("Jugador fichado con éxito. Crédito restante: $" + credito);
                imprimirFichados();
            } else {
                System.out.println();
                System.out.println("Ya has fichado a un jugador de la posición " + posicionFichada +
                        ". Ficha a otro puesto antes de seleccionar otro jugador de la misma posición.");
                System.out.println();
            }
        } else {
            System.out.println("Número de jugador inválido o no tienes suficiente crédito para fichar a este jugador.");
        }
    }


    private boolean puedeFicharJugadorDePosicion(String posicion) {
        // Verificar si ya se ha fichado al menos un jugador de cada posición
        boolean haFichadoCadaPosicion = posicionesFichadas.contains("Base") &&
                posicionesFichadas.contains("Escolta") &&
                posicionesFichadas.contains("Alero") &&
                posicionesFichadas.contains("Ala Pivot") &&
                posicionesFichadas.contains("Pivot");

        // Permitir fichar otro jugador de la misma posición solo si ya has fichado uno de cada posición
        return haFichadoCadaPosicion || !posicionesFichadas.contains(posicion);
    }


    private void rectificarFichaje(Scanner leer) {
        imprimirFichados();
        System.out.println("Introduce el número del jugador a rectificar: ");
        int numeroJugadorRectificar = leer.nextInt();
        leer.nextLine();

        if (numeroJugadorRectificar > 0 && numeroJugadorRectificar <= nombresFichados.size()) {
            int indiceJugadorRectificar = numeroJugadorRectificar - 1;

            // Obtener la información del jugador a rectificar
            String nombreRectificar = nombresFichados.get(indiceJugadorRectificar);
            String posicionRectificar = posicionesFichadas.get(indiceJugadorRectificar);
            double salarioRectificar = salariosFichados.get(indiceJugadorRectificar);

            // Eliminar el jugador de las listas de jugadores fichados
            nombresFichados.remove(indiceJugadorRectificar);
            posicionesFichadas.remove(indiceJugadorRectificar);
            salariosFichados.remove(indiceJugadorRectificar);

            // Devolver el salario al crédito restante
            credito += salarioRectificar;

            // Agregar el jugador de nuevo a las listas de jugadores fichables
            add(nombreRectificar, posicionRectificar, salarioRectificar);

            System.out.println("Fichaje rectificado con éxito. Crédito restante: $" + credito);
            imprimirFichados();
        } else {
            System.out.println("Número de jugador a rectificar inválido.");
        }
    }


    private int contarJugadoresFichadosEnPosicion(String posicion) {
        int contador = 0;
        for (String pos : posicionesFichadas) {
            if (pos.equalsIgnoreCase(posicion)) {
                contador++;
            }
        }
        return contador;
    }

    private void agregarNuevoJugador(Scanner leer) {
        System.out.println();
        System.out.println("Ingrese el nombre del nuevo jugador:");
        String nuevoNombre = leer.nextLine();
        System.out.println();

        System.out.println("Ingrese la posición del nuevo jugador:");
        String nuevaPosicion = leer.nextLine();
        System.out.println();

        System.out.println("Ingrese el salario del nuevo jugador:");
        double nuevoSalario = leer.nextDouble();
        leer.nextLine();

        add(nuevoNombre, nuevaPosicion, nuevoSalario);

        System.out.println("Jugador agregado con éxito.");
        imprimirFichables();
    }

    private void eliminarJugador(int indice) {
        posiciones.remove(indice);
        nombres.remove(indice);
        salarios.remove(indice);
    }

    private void add(String nombre, String posicion, double salario) {
        nombres.add(nombre);
        posiciones.add(posicion);
        salarios.add(salario);
        guardarDatos();
    }

    private void mostrarJugadoresPorPosicion(Scanner leer) {
        System.out.println();
        System.out.println("Ingrese la posición para mostrar jugadores: base, escolta, alero, ala pivot o pivot");
        String posicionSeleccionada = leer.nextLine();
        System.out.println();

        System.out.println("JUGADORES DISPONIBLES EN POSICIÓN " + posicionSeleccionada + ":");
        boolean jugadoresEncontrados = false;
        for (int i = 0; i < posiciones.size(); i++) {
            if (posiciones.get(i).equalsIgnoreCase(posicionSeleccionada) && salarios.get(i) <= credito) {
                jugadoresEncontrados = true;
                System.out.println((i + 1) + ". " + posiciones.get(i) + " " + nombres.get(i) + " " + salarios.get(i));
            }
        }

        if (!jugadoresEncontrados) {
            System.out.println("No hay jugadores disponibles en la posición " + posicionSeleccionada + ".");
            return;
        }
    }

    private void cargarDatos() {
        try {
            FileInputStream fileIn = new FileInputStream("datosNBAV3.obj");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            salarios = (ArrayList<Double>) in.readObject();
            nombres = (ArrayList<String>) in.readObject();
            posiciones = (ArrayList<String>) in.readObject();
            salariosFichados = (ArrayList<Double>) in.readObject();
            nombresFichados = (ArrayList<String>) in.readObject();
            posicionesFichadas = (ArrayList<String>) in.readObject();
            credito = in.readDouble();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            salarios = new ArrayList<>();
            nombres = new ArrayList<>();
            posiciones = new ArrayList<>();
            salariosFichados = new ArrayList<>();
            nombresFichados = new ArrayList<>();
            posicionesFichadas = new ArrayList<>();
        }
    }

    private void guardarDatos() {
        try {
            FileOutputStream fileOut = new FileOutputStream("datosNBAV3.obj");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(salarios);
            out.writeObject(nombres);
            out.writeObject(posiciones);
            out.writeObject(salariosFichados);
            out.writeObject(nombresFichados);
            out.writeObject(posicionesFichadas);
            out.writeDouble(credito);
            out.close();
            fileOut.close();
            System.out.println("Datos guardados correctamente.");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}

