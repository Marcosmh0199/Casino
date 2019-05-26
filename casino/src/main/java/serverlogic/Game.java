package serverlogic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game {
  private Board board;
  private BetRules betRules;
  private JackpotRules jackpotRules;
  private SpinesRules spineRules;
  private int currentBet;
  private ArrayList<Object> prices;
  private static final Logger logger = LogManager.getLogger(Game.class);
  private int jackpot;
  
  public void setCurrentBet(int currentBet) {
    this.currentBet = currentBet;
  }
  
  public String getCurrentBet() {
    return String.valueOf(currentBet);
  }
  
  public void setPrices() {
    prices = new ArrayList<Object>();
    prices.add(new Integer(0));
    prices.add(new Integer(0));
    prices.add(board.getBoard());
  }
  public Game(BetRules betRules, JackpotRules jackpotRules, SpinesRules spineRules) {
    this.betRules = betRules;
    this.jackpotRules = jackpotRules;
    this.spineRules = spineRules;
    this.board = new Board();
    setCurrentBet(150);
    try {
      jackpot = getJackpot();
    } catch (IOException e) {
      e.printStackTrace();
    }
    prices = new ArrayList<Object>();
    setPrices();
  }
  
  public ArrayList<ArrayList<String>> generateBoard() {
    ArrayList<ArrayList<String>> itemNames = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<Item>> items = board.getBoard();
    for(int row = 0; row < items.size(); row++) {
      itemNames.add(new ArrayList<String>());
      for(int column = 0; column < items.get(0).size(); column++) {
        itemNames.get(row).add(items.get(row).get(column).getItemType().name());
      }
    }
    return itemNames;
  }
  
  public ArrayList<Object> play() {
    jackpot += currentBet;
    modifyJackpot(String.valueOf(jackpot));
    board.generateBoard();
    prices.set(0,0);
    prices.set(1,0);
    prices.set(2, generateBoard());
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
            checkPrice(itemQuantity,currentItem, totalBitCoins, prices);
            currentItem = items.get(row).get(column);
            itemQuantity = 1;
          }
        }
      }
    }
    checkPrice(itemQuantity,currentItem, totalBitCoins, prices);
    jackpot -= (Integer)prices.get(0);
    modifyJackpot(String.valueOf(jackpot));
    return prices;
  }
  
  private void checkPrice(int itemQuantity,Item item, int totalBitCoins, 
      ArrayList<Object> prices) {
    int price = 0;
    int freeSpins = 0;
    if(item.getPrice().equals(Price.BETPERCETAGE)) {
      price = checkBet(itemQuantity, item);
      prices.set(0, (Integer)prices.get(0)+price);
    }else if(item.getPrice().equals(Price.JACKPOTPERCETAGE)) {
      price = checkJackpot(itemQuantity, item);
      prices.set(0, (Integer)prices.get(0)+price);
    }else {
      freeSpins = checkSpin(itemQuantity);
      prices.set(1, (Integer)prices.get(1)+freeSpins);
    }
  }
  
  private int checkBet(int itemQuantity, Item item) {
    int price = 0;
    if(itemQuantity >= 3) {
      if(itemQuantity == betRules.getTreeItems().getAparitionQuantity()) {
        price = (int) (currentBet * betRules.getTreeItems().getPercentage());       
      }else if(itemQuantity == betRules.getFourItems().getAparitionQuantity()) {
        price = (int) (currentBet * betRules.getFourItems().getPercentage());
      }else{
        price = (int) (currentBet * betRules.getFiveItems().getPercentage());
      }
      String msg = "Gano una apuesta por "+String.valueOf(itemQuantity)+" "+
          item.getItemType().name()+" con un valor de " +String.valueOf(price);
      logger.info(msg);
    }
    return price;
  }
  
  private int checkJackpot(int itemQuantity, Item item) {
    int price = 0;
    if(itemQuantity >= 10 && item.getItemType().equals(ItemTypes.BITCOIN)) {
      logger.info("Gano el Jackpot acumulado con un valor de "+String.valueOf(jackpot));
      modifyJackpot("0");
      return jackpot;
    }
    if(itemQuantity >= 3) {
      logger.info("Gano una apuesta por "+String.valueOf(itemQuantity)+" "+
          item.getItemType().name());
      if(item.getItemType().equals(ItemTypes.LINUX)) {
        price = (int) (jackpot * jackpotRules.getLinuxRule().getPercentage());
      }else if(item.getItemType().equals(ItemTypes.MAC)) {
        price = (int) (jackpot * jackpotRules.getMacRule().getPercentage());
      }else if(item.getItemType().equals(ItemTypes.WINDOWS)) {
        price = (int) (jackpot * jackpotRules.getWindowsRule().getPercentage());
      }
      String msg = "Gano una procentade del acumulado por "+String.valueOf(itemQuantity)+" "+
          item.getItemType().name()+" con un valor de " +String.valueOf(price);
      logger.info(msg);
    }
    return price;
  }
  
  private int checkSpin(int itemQuantity) {
    int price = 0;
    if(itemQuantity >= 3) {
      System.out.println("---------------Gano spin---------------");
      if(itemQuantity == spineRules.getThreeItems().getAparitionQuantity()) {
        price = spineRules.getThreeItems().getFreeSpin();
      }else if(itemQuantity == spineRules.getFourItems().getAparitionQuantity()) {
        price = spineRules.getFourItems().getFreeSpin();
      }else {
        price = spineRules.getFiveItems().getFreeSpin();
      }
      String msg = "Gano juegos gratuitos por "+String.valueOf(itemQuantity)+" "+
          ItemTypes.JAVA.name()+" siendo un total de " +String.valueOf(price);
      logger.info(msg);
    }
    return 0;
  }
  
  private int getJackpot() throws IOException {
    FileReader file;
    try {

      file = new FileReader("C:\\Users\\Larry\\JSONFiles\\JACKPOT.txt");
      Scanner sc = new Scanner(file); 
      while (sc.hasNextLine()) { 
          jackpot = sc.nextInt();
      } 
      //int jackpot = 100000;
      return jackpot;
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
