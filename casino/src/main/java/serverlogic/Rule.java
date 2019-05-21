package serverlogic;

/**
 * Clase para crear reglas que evaluan si se ganó un premio
 * @author Marcos
 * @version 19.5.20
 */
public abstract class Rule {
  protected int aparitionQuantity;
  public Rule(){
    
  }
  
  public void setAparitionQuantity(int aparitionQuantity) {
    this.aparitionQuantity = aparitionQuantity;
  }
  
  public int getAparitionQuantity() {
    return aparitionQuantity;
  }
}
