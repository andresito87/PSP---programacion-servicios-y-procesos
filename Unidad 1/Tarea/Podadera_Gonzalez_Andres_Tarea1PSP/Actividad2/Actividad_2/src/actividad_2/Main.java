package actividad_2;

/**
 *
 * @author andres
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // creamos un buffer compartido
        Buffer buffer = new Buffer(10);
        // creamos los dos hilos productores
        Productor productor1 = new Productor("Hilo productor 1", buffer);
        Productor productor2 = new Productor("Hilo productor 2", buffer);
        // creamos el hilo consumidor
        Consumidor c = new Consumidor("Hilo consumidor", buffer);

        // iniciamos los tres hilos creados
        c.start();
        productor1.start();
        productor2.start();

    }
}
