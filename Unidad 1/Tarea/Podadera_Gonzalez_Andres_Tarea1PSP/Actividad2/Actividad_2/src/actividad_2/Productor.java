package actividad_2;

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
        while (true) {
            int valorProducido = (int) (Math.random() * 11); // Generar un número aleatorio entre 0 y 10
            buffer.producir(valorProducido);
            System.out.println(this.getNombre() + ", introduce el valor "
                    + valorProducido + " en la posicion " + buffer.getSiguiente()
                    + ", Array = " + this.buffer.recursoCompartidoToString()
                    +". Suma: "+this.buffer.getSumatoria()
            );

            // Comprobar si se alcanzó el valor máximo en el buffer
            if (buffer.fueValorMaximoSumatoriaAlcanzado()) {
                break; // Salir del bucle y detener el hilo
            }

            try {
                Thread.sleep(100); // Simular tiempo de producción
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

