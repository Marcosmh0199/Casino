package serverlogic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.io.File; 
import java.util.Scanner; 

public class Game {
  private Board board;
  private BetRules betRules;
  private JackpotRules jackpotRules;
  private SpinesRules spineRules;
  private SerializeObject serializer;
  ArrayList<Integer> prices = new ArrayList<Integer>();
  
  public Game() {
    betRules = new BetRules();
    jackpotRules = new JackpotRules();
    spineRules = new SpinesRules();
    serializer = new SerializeObject();
    betRules = (BetRules) serializer.convertFromJson("Rules", "BETRULES", betRules.getClass());
    jackpotRules = (JackpotRules) serializer.convertFromJson("Rules", "JACKPOTRULES", jackpotRules.getClass());
    spineRules = (SpinesRules) serializer.convertFromJson("Rules", "SPINESRULE", spineRules.getClass());
    this.board = new Board();
  }
  
  public ArrayList<ArrayList<String>> generateBoard() {
    ArrayList<ArrayList<String>> itemNames = new ArrayList<ArrayList<String>>();
    board.generateBoard();
    ArrayList<ArrayList<Item>> items = board.getBoard();
    for(int row = 0; row < items.size(); row++) {
      for(int column = 0; column < items.get(0).size(); column++) {
        itemNames.get(row).add(items.get(row).get(column).getItemType().name());
      }
    }
    return itemNames;
  }
  
  public ArrayList<Integer> play(int bet) {
    prices.set(0,0);
    prices.set(0,0);
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
            checkPrice(itemQuantity,currentItem, totalBitCoins, prices, bet);
            currentItem = items.get(row).get(column);
            itemQuantity = 1;
          }
        }
      }
    }
    checkPrice(itemQuantity,currentItem, totalBitCoins, prices, bet);
    return prices;
  }
  
  private void checkPrice(int itemQuantity,Item item, int totalBitCoins, 
      ArrayList<Integer> prices, int bet) {
    int price = 0;
    int freeSpins = 0;
    if(item.getPrice().equals(Price.BETPERCETAGE)) {
      price = checkBet(itemQuantity,bet, item);
      prices.set(0, prices.get(0)+price);
    }else if(item.getPrice().equals(Price.JACKPOTPERCETAGE)) {
      price = checkJackpot(itemQuantity, item);
      prices.set(0, prices.get(0)+price);
    }else {
      freeSpins = checkSpin(itemQuantity);
      prices.set(0, prices.get(1)+freeSpins);
    }
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
  
  private int checkJackpot(int itemQuantity, Item item) {
    int price = 0;
    int jackpot = getJackpot();
    if(itemQuantity >= 10 && item.getItemType().equals(ItemTypes.BITCOIN)) {
      modifyJackpot("0");
      return jackpot;
    }
    if(itemQuantity >= 3) {
      System.out.println("---------------Gano jack por ---------------"+item.getItemType().name());
      if(item.getItemType().equals(ItemTypes.LINUX)) {
        price = (int) (jackpot * jackpotRules.getLinuxRule().getPercentage());
      }else if(item.getItemType().equals(ItemTypes.MAC)) {
        price = (int) (jackpot * jackpotRules.getMacRule().getPercentage());
      }else if(item.getItemType().equals(ItemTypes.WINDOWS)) {
        price = (int) (jackpot * jackpotRules.getWindowsRule().getPercentage());
      } 
    }
    jackpot -= price;
    modifyJackpot(String.valueOf(jackpot));
    return price;
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
  
  private int getJackpot() {
    FileReader file;
    try {
      file = new FileReader("C:\\Users\\Larry\\JSONFiles\\JACKPOT.txt");
      Scanner sc = new Scanner(file); 
      return sc.nextInt();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return 0;
  }
  
  void modifyJackpot(String newValue) {
    PrintWriter writer;
    try {
      writer = new PrintWriter("C:\\Users\\Larry\\JSONFiles\\JACKPOT.txt", "UTF-8");
      writer.println(newValue);
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

  }
}
