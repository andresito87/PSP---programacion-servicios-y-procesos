
package ejemploestados;

/**
 *
 * @author andres
 */
public class EjemploEstados {

  public static void main(String[] args) {
     //Creamos un nuevo hilo.
     // Está en estado nuevo (NEW)
     HiloAuxiliar hilo1 = new HiloAuxiliar();

     //Obtenemos el estado del thread hilo1
     // y si está vivo o no
     System.out.println("Hilo Auxiliar Nuevo: Estado=" +
            hilo1.getState() + 
            ",¿Vivo? isAlive()=" + hilo1.isAlive());

     // Iniciamos el thread hilo1 
     // y pasa al estado ejecutable
     hilo1.start();

     System.out.println("Hilo Auxiliar Iniciado: Estado="
            + hilo1.getState()
            + ",¿Vivo? isAlive()=" + 
            hilo1.isAlive() );
       
     try {
         // Esperamos a que el thread hilo1 muera
         hilo1.join();
     } catch (InterruptedException e) {
         System.out.println(e.getMessage());
     }
     System.out.println("Hilo Auxiliar Muerto: Estado="
             + hilo1.getState()
             + ",¿Vivo? isAlive()=" + 
             hilo1.isAlive() );
  }
}
