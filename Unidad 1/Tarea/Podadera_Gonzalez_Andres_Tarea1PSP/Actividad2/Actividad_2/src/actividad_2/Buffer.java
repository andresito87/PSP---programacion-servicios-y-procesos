package actividad_2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class Buffer {

    private final int VALOR_MAXIMO_SUMATORIA = 100;

    private final int[] buffer;
    private int siguiente;
    private boolean estaVacia;
    private boolean estaLlena;
    private int sumatoria;

    public Buffer(int tamanio) {
        this.buffer = new int[tamanio];
        this.siguiente = 0;
        this.estaVacia = true;
        this.estaLlena = false;
        this.sumatoria = 0;
    }

    public int getSiguiente() {
        return siguiente;
    }

    public int getSumatoria() {
        return sumatoria;
    }

    public synchronized int consumir() {
        while (this.estaVacia) {
            try {
                // condicion de parada del bucle
                if (fueValorMaximoSumatoriaAlcanzado()) {
                    notifyAll();
                    break;
                }
                wait();

            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        siguiente--;

        this.estaLlena = false;
        if (siguiente == 0) {
            this.estaVacia = true;
        }

        notifyAll();
        // sumo el elemento a la sumatoria
        sumatoria += buffer[siguiente];

        return this.buffer[this.siguiente];
    }

    public synchronized void producir(int numero) {
        while (this.estaLlena) {
            try {
                // condicion de parada del bucle
                if (this.fueValorMaximoSumatoriaAlcanzado()) {
                    notifyAll();
                    break;
                }
                wait();

            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // si no he llegado al último elemento, 
        if (siguiente < this.buffer.length) {
            buffer[siguiente] = numero;
            siguiente++;
        }
        this.estaVacia = false;
        if (siguiente == this.buffer.length - 1) {
            this.estaLlena = true;
        }

        notifyAll();
    }

    public boolean fueValorMaximoSumatoriaAlcanzado() {
        return this.sumatoria >= this.VALOR_MAXIMO_SUMATORIA;
    }

    public String recursoCompartidoToString() {
        StringBuilder arrayToString = new StringBuilder();

        // Iterar sobre los elementos del array que se han introducido por el productor
        for (int i = 0; i < this.siguiente; i++) {
            arrayToString.append(this.buffer[i]);  // Añadir el elemento actual

            // Si no es el último elemento, añadir un " - " después del elemento
            if (i < this.siguiente - 1) {
                arrayToString.append(" - ");
            }

        }
        
        return arrayToString.toString();  // Convertir el StringBuilder a String y devolverlo
    }
}
