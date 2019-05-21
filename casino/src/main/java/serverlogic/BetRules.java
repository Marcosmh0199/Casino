package serverlogic;

public class BetRules {
  private PercentageRule linuxRule;
  private PercentageRule macRule;
  private PercentageRule windowsRule;
  
  public BetRules(){
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
}
