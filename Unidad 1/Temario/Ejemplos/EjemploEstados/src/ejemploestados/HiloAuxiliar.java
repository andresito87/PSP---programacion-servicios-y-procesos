package ejemploestados;

/**
 *
 * @author andres
 */
public class HiloAuxiliar extends Thread {
    
  @Override 
  public void run(){
      for(int i=10; i>1; i--)
          System.out.print(i + ",");
      System.out.println (1);
  }
  
}
