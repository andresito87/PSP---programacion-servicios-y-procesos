package crearprocesos;

/* Clases necesarias para poder crear procesos. */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Process;
import java.lang.Runtime;

/**
 *
 * @author andres
 */
public class CrearProcesos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Llamada al método crearNuevoEditor desde el método main
        crearNuevoEditor();
    }

    public static void crearNuevoEditor() {
        Process nuevoProceso; //Definimos una variable de tipo Process
        try {
            //Obtenemos el nombre del SO
            String osName = System.getProperty("os.name");

            if (osName.toUpperCase().contains("WIN")) { //Windows
                nuevoProceso = Runtime.getRuntime().exec("java -jar "
                        + "C:\\Pruebas\\Cadenas.jar 5");
            } else {                                      //Linux
                nuevoProceso = Runtime.getRuntime().exec("java -jar "
                        + "/home/usuario/NetBeansProjects/Editor/dist/DocumentEditor.jar");
            }
            System.out.println("Ejecutado corectamente");
            // Capturar la salida estándar
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(nuevoProceso.getInputStream()));
            // Capturar la salida de error
            BufferedReader stdError = new BufferedReader(new InputStreamReader(nuevoProceso.getErrorStream()));

            // Leer la salida estándar del comando
            String s = null;
            System.out.println("Salida del comando:");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // Leer cualquier error de la ejecución del comando
            System.out.println("\nErrores del comando (si los hay):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

        } catch (SecurityException ex) {
            System.out.println("Ha ocurrido un error de Seguridad."
                    + "No se ha podido crear el proceso por falta de permisos.");
        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error, descripción: "
                    + ex.toString());
        }
    }
}
