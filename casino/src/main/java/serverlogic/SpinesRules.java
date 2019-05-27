package serverlogic;

/**
 * Clase que contiene las reglas de spins
 * @author Marcos
 * @version v19.20.05
 *
 */
public class SpinesRules {
  private SpinRule threeItems;
  private SpinRule fourItems;
  private SpinRule fiveItems;
  
  public SpinesRules() {
  }

  public SpinRule getThreeItems() {
    return threeItems;
  }

  public void setThreeItems(SpinRule threeItems) {
    this.threeItems = threeItems;
  }

  public SpinRule getFourItems() {
    return fourItems;
  }

  public void setFourItems(SpinRule fourItems) {
    this.fourItems = fourItems;
  }

  public SpinRule getFiveItems() {
    return fiveItems;
  }

  public void setFiveItems(SpinRule fiveItems) {
    this.fiveItems = fiveItems;
  }
  
  
  
}
