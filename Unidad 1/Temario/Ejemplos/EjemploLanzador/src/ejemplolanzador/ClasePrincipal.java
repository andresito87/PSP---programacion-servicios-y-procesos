package ejemplolanzador;

/**
 *
 * @author andres
 */
public class ClasePrincipal {

  public static void main(String[] args) {
    // Creamos 2 hilos del tipo HiloThread
    // (usando dos constructores diferentes)
    Thread hilo1= new HiloThread("Hilo 1");
    Thread hilo2= new HiloThread();

     // Creamos un hilo Runnable en un paso
     Thread hilo3= new Thread(new HiloRunnable());

      // Ponemos en marcha los 3 hilos
      hilo1.start();
      hilo2.start();
      hilo3.start();
  }
}
