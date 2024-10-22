package actividad_2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class Consumidor extends Thread {

    private final Buffer buffer;
    private final String nombre;

    public Consumidor(String nombre, Buffer buffer) {
        this.nombre = nombre;
        this.buffer = buffer;

    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        try (FileWriter fileWriter = new FileWriter("logs.txt", true); 
                PrintWriter fileOutput = new PrintWriter(fileWriter)) {
            while (true) {
                // consumimos un numero
                int valorConsumido = buffer.consumir();
                // creamos el mensaje de salida
                String mensaje = this.getNombre() + ", saca el valor " + valorConsumido
                        + " en la posicion " + buffer.getSiguiente()
                        + ", Array = " + this.buffer.recursoCompartidoToString()
                        + ", Suma: " + this.buffer.getSumatoria();

                // imprimimos el mensaje en consola
                System.out.println(mensaje);

                // guardamos el mensaje en el archivo logs.txt
                fileOutput.println(mensaje);

                // comprobar si se alcanzó el valor máximo en el buffer
                if (buffer.fueValorMaximoSumatoriaAlcanzado()) {
                    // creamos el mensaje de que se alcanzo o sobrepaso el valor maximo de la sumatoria
                    String resultadoFinal = "Resultado final: " + this.buffer.getSumatoria();
                    // imprimimos el mensaje en consola
                    System.out.println(resultadoFinal);
                    // gruardamos el resultado final en el archivo
                    fileOutput.println(resultadoFinal);
                    break; // salir del bucle y detener el hilo
                }

                try {
                    // dormimos el hilo
                    Thread.sleep(150); // simular tiempo de consumo
                } catch (InterruptedException ex) {
                    Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, "Error escribiendo en el archivo logs.txt", ex);
        }
    }

}

