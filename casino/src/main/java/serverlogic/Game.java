package serverlogic;

import java.util.ArrayList;

public class Game {
  private int bet;
  private Player player;
  private Board board;
  private BetRules betRules;
  private JackpotRules jackpotRules;
  private SpinesRules spineRules;
  private SerializeObject serializer;
  private int freeSpins;
  private int jackpot;
  
  public Game(Player player, int bet) {
    betRules = new BetRules();
    jackpotRules = new JackpotRules();
    spineRules = new SpinesRules();
    serializer = new SerializeObject();
    betRules = (BetRules) serializer.convertFromJson("Rules", "BETRULES", betRules.getClass());
    jackpotRules = (JackpotRules) serializer.convertFromJson("Rules", "JACKPOTRULES", jackpotRules.getClass());
    spineRules = (SpinesRules) serializer.convertFromJson("Rules", "SPINESRULE", spineRules.getClass());
    this.player = player;
    this.board = new Board();
    this.jackpot = 10000;
  }
  
  public void play() {
    if(freeSpins > 0) {
      freeSpins--;
    }else {
      this.player.setCredit(-bet);
    }  
    board.generateBoard();
    ArrayList<ArrayList<Item>> items = board.getBoard();
    int totalBitCoins = 0;
    Item currentItem = items.get(0).get(0);
    Item compareItem;
    int itemQuantity = 0;
    for(int row = 0; row < items.size(); row++) {
      itemQuantity = 0;
      for(int column = 0; column < items.get(0).size(); column++) {
        compareItem = items.get(row).get(column);
        if(compareItem.getItemType().equals(ItemTypes.BITCOIN)) {
          totalBitCoins++;
          continue;
        }
        if((compareItem.getItemType() == currentItem.getItemType())) {
          itemQuantity++;
        }else {
          if(currentItem.getPrice().equals(Price.BETPERCETAGE) && compareItem.getItemType().
              equals(ItemTypes.GITKRAKEN)) {
            itemQuantity++;
          }else {
            checkPrice(itemQuantity,currentItem, jackpot, totalBitCoins);
            currentItem = items.get(row).get(column);
            itemQuantity = 1;
          }
        }
      }
    }
    checkPrice(itemQuantity,currentItem, jackpot, totalBitCoins);
  }
  
  private void checkPrice(int itemQuantity,Item item, int jackpot, int totalBitCoins) {
    int price = 0;
    int freeSpins = 0;
    if(item.getPrice().equals(Price.BETPERCETAGE)) {
      price = checkBet(itemQuantity,bet, item);
    }else if(item.getPrice().equals(Price.JACKPOTPERCETAGE)) {
      price = checkJackpot(itemQuantity, item, jackpot);
    }else {
      freeSpins = checkSpin(itemQuantity);
    }
    this.player.setCredit(player.getCredit()+price);
    this.freeSpins += freeSpins;
  }
  
  private int checkBet(int itemQuantity, int bet, Item item) {
    if(itemQuantity >= 3) {
      System.out.println("---------------Gano bet por ----------------"+item.getItemType().name());
      if(itemQuantity == betRules.getTreeItems().getAparitionQuantity()) {
        return  (int) (bet * betRules.getTreeItems().getPercentage());       
      }else if(itemQuantity == betRules.getFourItems().getAparitionQuantity()) {
        return (int) (bet * betRules.getFourItems().getPercentage());
      }else{
        return (int) (bet * betRules.getFiveItems().getPercentage());
      } 
    }else {
      return 0;
    }
  }
  
  private int checkJackpot(int itemQuantity, Item item, int jackpot) {
    if(itemQuantity >= 3) {
      System.out.println("---------------Gano jack por ---------------"+item.getItemType().name());
      if(item.getItemType().equals(ItemTypes.LINUX)) {
        return (int) (jackpot * jackpotRules.getLinuxRule().getPercentage());
      }else if(item.getItemType().equals(ItemTypes.MAC)) {
        return (int) (jackpot * jackpotRules.getMacRule().getPercentage());
      }else if(item.getItemType().equals(ItemTypes.WINDOWS)) {
        return (int) (jackpot * jackpotRules.getWindowsRule().getPercentage());
      } 
    }
    return 0;
  }
  
  private int checkSpin(int itemQuantity) {
    if(itemQuantity >= 3) {
      System.out.println("---------------Gano spin---------------");
      if(itemQuantity == spineRules.getThreeItems().getAparitionQuantity()) {
        return spineRules.getThreeItems().getFreeSpin();
      }else if(itemQuantity == spineRules.getFourItems().getAparitionQuantity()) {
        return spineRules.getFourItems().getFreeSpin();
      }else {
        return spineRules.getFiveItems().getFreeSpin();
      } 
    }
    return 0;
  }
}
