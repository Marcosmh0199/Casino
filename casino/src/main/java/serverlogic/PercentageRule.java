package serverlogic;

/**
 * Clase para crear reglas que evaluan si se ganó un premio en creditos
 * @author Marcos
 * @version 19.5.20
 */
public final class PercentageRule extends Rule {
  private double percentage;
  
  public PercentageRule() {
  }

  public double getPercentage() {
    return percentage;
  }

  public void setPercentage(double percentage) {
    this.percentage = percentage;
  }
  
}
