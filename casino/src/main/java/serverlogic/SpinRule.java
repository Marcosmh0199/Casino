package serverlogic;

/**
 * 
 * @author Marcos
 * @version 19.5.20
 */

public class SpinRule extends Rule {
  private int freeSpin;
  
  public SpinRule(){
    
  }
  
  private void setFreeSpin(int freeSpin) {
    this.freeSpin = freeSpin;
  }
  
  public int getFreeSpin() {
    return freeSpin;
  }
}
