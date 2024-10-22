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
public class Productor extends Thread {

    private final Buffer buffer;
    private final String nombre;

    public Productor(String nombre, Buffer buffer) {
        this.nombre = nombre;
        this.buffer = buffer;

    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        // creamos un filewriter y su correspondiente flujo para escribir la informacion en el archivo logs.txt
        try (FileWriter fileWriter = new FileWriter("logs.txt", true); PrintWriter fileOutput = new PrintWriter(fileWriter)) {
            while (true) {
                // producimos un numero
                buffer.producir();
                // creamos el mensaje de salida
                String mensaje = this.getNombre() + ", introduce el valor "
                        + this.buffer.getValorProducido() + " en la posicion " + buffer.getSiguiente()
                        + ", Array = " + this.buffer.recursoCompartidoToString()
                        + ". Suma: " + this.buffer.getSumatoria();

                // imprimimos el mensaje por consola
                System.out.println(mensaje);

                // guardamos el mensaje en logs.txt
                fileOutput.println(mensaje);

                // comprobar si se alcanzó el valor máximo en el buffer
                if (buffer.fueValorMaximoSumatoriaAlcanzado()) {
                    break; // salir del bucle y detener el hilo
                }

                try {
                    // dormimos el hilo
                    Thread.sleep(100); // simular tiempo de producción
                } catch (InterruptedException ex) {
                    Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, "Error escribiendo en el archivo logs.txt", ex);
        }
    }

}
