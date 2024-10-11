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
        
        Buffer buffer = new Buffer(10);
        Productor productor1 = new Productor("Hilo productor 1", buffer);
        Productor productor2 = new Productor("Hilo productor 2", buffer);
        Consumidor c = new Consumidor("Hilo consumidor", buffer);

        c.start();
        productor1.start();
        productor2.start();

    }
}
