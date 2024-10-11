package ejemplolanzador;

/**
 *
 * @author andres
 */
public class HiloThread extends Thread {

    private String nombre;

    // Constructor con un parámetro
    public HiloThread(String nb) {
        this.nombre = nb;
    }

    // Constructor sin parámetros
    public HiloThread() {
        this ("Hilo Thread");
    }

    @Override
    public void run() {
        // Redefinimos run
        for (int i = 1; i <= 5; i++) {
            System.out.println(nombre);
        }
    }
}
