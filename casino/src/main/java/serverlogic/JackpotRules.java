package serverlogic;

/**
 * Clase que contiene las reglas referente al jackpot
 * @author Marcos
 * @version v19.20.05
 */
public final class JackpotRules {
  private PercentageRule linuxRule;
  private PercentageRule macRule;
  private PercentageRule windowsRule;
  private PercentageRule bitCoinRule;
  
  public JackpotRules() {
  }

  public PercentageRule getLinuxRule() {
    return linuxRule;
  }

  public void setLinuxRule(PercentageRule linuxRule) {
    this.linuxRule = linuxRule;
  }

  public PercentageRule getMacRule() {
    return macRule;
  }

  public void setMacRule(PercentageRule macRule) {
    this.macRule = macRule;
  }

  public PercentageRule getWindowsRule() {
    return windowsRule;
  }

  public void setWindowsRule(PercentageRule windowsRule) {
    this.windowsRule = windowsRule;
  } 
  
  public PercentageRule getBitCoinRule() {
    return bitCoinRule;
  }

  public void setBitCoinRule(PercentageRule bitCoinRule) {
    this.bitCoinRule = bitCoinRule;
  }
  
}
