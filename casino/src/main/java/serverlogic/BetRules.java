package serverlogic;

public class BetRules {
  private PercentageRule treeItems;
  private PercentageRule fourItems;
  private PercentageRule fiveItems;
  
  public BetRules(){
  }

  public PercentageRule getTreeItems() {
    return treeItems;
  }

  public void setTreeItems(PercentageRule treeItems) {
    this.treeItems = treeItems;
  }

  public PercentageRule getFourItems() {
    return fourItems;
  }

  public void setFourItems(PercentageRule fourItems) {
    this.fourItems = fourItems;
  }

  public PercentageRule getFiveItems() {
    return fiveItems;
  }

  public void setFiveItems(PercentageRule fiveItems) {
    this.fiveItems = fiveItems;
  }
  
  
  
}
