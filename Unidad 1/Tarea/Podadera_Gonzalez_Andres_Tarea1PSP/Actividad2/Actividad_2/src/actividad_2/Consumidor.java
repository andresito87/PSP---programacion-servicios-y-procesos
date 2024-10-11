package actividad_2;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class Consumidor extends Thread {

    private final Buffer buffer;
    private final String nombre;

    public Consumidor(String nombre, Buffer buffer) {
        this.nombre = nombre;
        this.buffer = buffer;

    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        while (true) {
            int valorConsumido = buffer.consumir();
            System.out.println(this.getNombre() + ", saca el valor " + valorConsumido
                    + " en la posicion " + buffer.getSiguiente()
                    + ", Array = " + this.buffer.recursoCompartidoToString()
                    +", Suma: "+this.buffer.getSumatoria());

            // Comprobar si se alcanzó el valor máximo en el buffer
            if (buffer.fueValorMaximoSumatoriaAlcanzado()) {
                System.out.println("Resultado final: " + this.buffer.getSumatoria());
                break; // Salir del bucle y detener el hilo
            }

            try {
                Thread.sleep(150); // Simular tiempo de consumo
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

