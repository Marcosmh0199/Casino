package apl;
import serverlogic.*;


public class MarkMain {
  public static void main(String[] args) {
    /**
    Item item = new Item(0);
    SerializeObject serializer = new SerializeObject();
    
    item.setItemType(ItemTypes.ECLIPSE);
    item.setPrice(Price.BETPERCETAGE);
    serializer.convertToJson(item, item.getItemType().name(), "Items");
    
    item.setItemType(ItemTypes.NETBEANS);
    serializer.convertToJson(item, item.getItemType().name(), "Items");
    
    item.setItemType(ItemTypes.BLUEJ);
    serializer.convertToJson(item, item.getItemType().name(), "Items");
    
    item.setItemType(ItemTypes.XCODE);
    serializer.convertToJson(item, item.getItemType().name(), "Items");
    
    item.setItemType(ItemTypes.ORACLE);
    serializer.convertToJson(item, item.getItemType().name(), "Items");
    
    item.setItemType(ItemTypes.JCREATOR);
    serializer.convertToJson(item, item.getItemType().name(), "Items");
    
    item.setItemType(ItemTypes.LINUX);
    item.setPrice(Price.JACKPOTPERCETAGE);
    serializer.convertToJson(item, item.getItemType().name(), "Items");
    
    item.setItemType(ItemTypes.MAC);
    serializer.convertToJson(item, item.getItemType().name(), "Items");
    
    item.setItemType(ItemTypes.WINDOWS);
    serializer.convertToJson(item, item.getItemType().name(), "Items");
    
    item.setItemType(ItemTypes.GITKRAKEN);
    item.setPrice(Price.SPINE);
    serializer.convertToJson(item, item.getItemType().name(), "Items");
    
    item.setItemType(ItemTypes.BITCOIN);
    serializer.convertToJson(item, item.getItemType().name(), "Items");
    
    item.setItemType(ItemTypes.JAVA);
    serializer.convertToJson(item, item.getItemType().name(), "Items");

   
    BetRules betRules = new BetRules();
    PercentageRule pRule = new PercentageRule();
    SerializeObject serializer = new SerializeObject();
    pRule.setPercentage(0.5);
    pRule.setAparitionQuantity(3);
    betRules.setTreeItems(pRule);
    pRule.setPercentage(0.75);
    pRule.setAparitionQuantity(4);
    betRules.setFourItems(pRule);
    pRule.setPercentage(1.50);
    pRule.setAparitionQuantity(5);
    betRules.setFiveItems(pRule);
    serializer.convertToJson(betRules, "BETRULES", "Rules");
    
    JackpotRules jackpotRules = new JackpotRules();
    pRule.setPercentage(0.05);
    pRule.setAparitionQuantity(3);
    jackpotRules.setLinuxRule(pRule);
    pRule.setPercentage(0.025);
    jackpotRules.setMacRule(pRule);
    pRule.setPercentage(0.01);
    jackpotRules.setWindowsRule(pRule);
    pRule.setAparitionQuantity(10);
    pRule.setPercentage(0);
    jackpotRules.setBitCoinRule(pRule);
    serializer.convertToJson(jackpotRules, "JACKPOTRULES", "Rules");
    
    SpinRule spinRule = new SpinRule();
    SpinesRules spinesRule = new SpinesRules();
    spinRule.setAparitionQuantity(3);
    spinRule.setFreeSpin(12);
    spinesRule.setThreeItems(spinRule);
    spinRule.setAparitionQuantity(4);
    spinRule.setFreeSpin(15);
    spinesRule.setFourItems(spinRule);
    spinRule.setAparitionQuantity(5);
    spinRule.setFreeSpin(20);
    spinesRule.setFiveItems(spinRule);
    serializer.convertToJson(spinesRule, "SPINESRULE", "Rules");
    **/
    Player player = new Player("Pepe","123");
    for(int i = 0; i < 20; i++) {
      System.out.println();
    }
    
  }
}
