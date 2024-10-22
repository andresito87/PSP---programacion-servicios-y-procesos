package actividad_2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class Buffer {

    // variable que almacena el valor maximo que tiene que alcanzar la sumatoria para que el programa finalice
    private final int VALOR_MAXIMO_SUMATORIA = 100;

    // array compartido
    private final int[] buffer;
    // variable para guardar la posicion en la que estamos consumiendo o produciendo
    private int siguiente;
    // almacenamos si el array esta vacio
    private boolean estaVacia;
    // almacenamos si el array esta lleno
    private boolean estaLlena;
    // almacenamos el valor de la sumatoria
    private int sumatoria;
    private int valorProducido=0;

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
    
    public int getValorProducido() {
        return this.valorProducido;
    }

    public synchronized int consumir() {
        while (this.estaVacia) {
            try {
                // condicion de parada del bucle
                if (fueValorMaximoSumatoriaAlcanzado()) {
                    // notificamos al resto de hilos
                    notifyAll();
                    // detenemos el bucle, no hay que esperar, el programa va a finalizar
                    break;
                }
                // si el array esta vacio, el hilo consumidor tiene que esperar
                wait();

            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // el productor al insertar un numero, ha aumentado el valor, necesito retroceder para leer el valor anterior
        siguiente--;
        
        // confirmamos que el array no esta lleno, ya que hemos consumido 1
        this.estaLlena = false;

        // sumo el elemento a la sumatoria
        sumatoria += buffer[siguiente];
        
        // notifico al resto de hilos
        notifyAll();
        
        return this.buffer[this.siguiente];
    }

    public synchronized void producir() {
        this.valorProducido = (int) (Math.random() * 11); // Generar un número aleatorio entre 0 y 10
        while (this.estaLlena) {
            try {
                // condicion de parada del bucle, hemos alcanzado el valor maximo de la sumatoria
                if (this.fueValorMaximoSumatoriaAlcanzado()) {
                    // notificamos al resto de hilos
                    notifyAll();
                    // detenemos el bucle, no hay que esperar, el programa va a finalizar
                    break;
                }
                // si el array esta lleno, el hilo productor tiene que esperar
                wait();

            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        buffer[siguiente] = this.valorProducido;
        // si no ha llegado al último elemento del array, avanzo una posición.
        // en este punto nuestro programa se podría mejorar, añadiendo más tiempo para dormir los 
        // hilos productores o aumentar el tamaño del array, ya que nuestro array se llena
        // demasiado rapido y al momento de producir los ultimos numeros estos se insertan en las
        // ultimas posiciones del array, siendo "machacados" los primeros por los ultimos, impidiendo
        // que se aprecie el verdadero comportamiento del llenado del array
        if (siguiente < this.buffer.length) {
            siguiente++;
        }
        
        // hemos insertado un numero, nuestro array no esta vacio, tiene numeros disponibles para su lectura
        this.estaVacia = false;
        
        // tiene numeros para su lectura y hemos llegado a la ultima posicion del array,
        // entonces confirmamos que esta lleno, tendremos que esperar a que se lean numeros antes
        // de poder insertar algunos mas
        if (siguiente == this.buffer.length - 1) {
            this.estaLlena = true;
        }

        // notificamos al resto de hilos
        notifyAll();
    }

    // funcion que nos permite comprobar si hemos alcanzado el valor máximo de la sumatoria
    public boolean fueValorMaximoSumatoriaAlcanzado() {
        return this.sumatoria >= this.VALOR_MAXIMO_SUMATORIA;
    }

    // funcion que nos permite transformar el conjunto de valores del array en un string
    public String recursoCompartidoToString() {
        // almacena una representación del array en forma de cadena
        StringBuilder arrayToStringBuilder = new StringBuilder();

        // iterar sobre los elementos del array que han sido introducidos por el productor
        // simulamos que se usa un array dinamico, arraylist
        for (int i = 0; i < this.siguiente; i++) {
            arrayToStringBuilder.append(this.buffer[i]);  // Añadir el elemento actual

            // Si no es el último elemento, añadir un " - " después del elemento
            if (i < this.siguiente - 1) {
                arrayToStringBuilder.append(" - ");
            }

        }

        return arrayToStringBuilder.toString();  // Convertir el StringBuilder a String y devolverlo
    }
}
