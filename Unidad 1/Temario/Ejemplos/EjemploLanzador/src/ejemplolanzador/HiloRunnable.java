package ejemplolanzador;

/**
 *
 * @author andres
 */
public class HiloRunnable implements Runnable {

    @Override
    public void run() {
        //redefinimos run
        for (int i = 1; i <= 5; i++) {
            System.out.println("Hilo Runnable");
        }
    }
}
