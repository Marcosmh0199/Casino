package serverlogic;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase que contiene la logica de juego
 * @author Marcos
 * @version v19.24.05
 *
 */
public class Game {
  private Board board;
  private BetRules betRules;
  private JackpotRules jackpotRules;
  private SpinesRules spineRules;
  private int currentBet;
  private ArrayList<Object> prices;
  private static final Logger logger = LogManager.getLogger(Game.class);
  private int jackpot;
  
  /**
   * Constructor
   * @param betRules Reglas del premio por apuesta
   * @param jackpotRules Reglas del premio por jackpot
   * @param spineRules Reglas del promio referentes a las freeSpins
   */
  public Game(BetRules betRules, JackpotRules jackpotRules, SpinesRules spineRules) {
    this.betRules = betRules;
    this.jackpotRules = jackpotRules;
    this.spineRules = spineRules;
    this.board = new Board();
    setCurrentBet(100);
    try {
      jackpot = getJackpot();
    } catch (IOException e) {
      e.printStackTrace();
    }
    prices = new ArrayList<Object>();
    setPrices();
  }
  
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
  
  /**
   * Método para generar el tablero que será usado en el juego, además retorna
   * un tablero en forma de Strings para uso de la GUI
   * @return Array de Arrays que contienen los nombres de los items generados
   */
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
  
  /**
   * Método para jugar, se encarga de toda la lógica de juego
   * @return Array que contiene los premios en efectivo y freeSpins
   */
  public ArrayList<Object> play() {
    if(jackpot < this.currentBet) {
      return null;
    }
    jackpot += currentBet;
    modifyJackpot(String.valueOf(jackpot));
    board.generateBoard();
    prices.set(0,0);
    prices.set(1,0);
    prices.set(2, generateBoard());
    ArrayList<ArrayList<Item>> items = board.getBoard();
    int totalBitCoins = 0;
    Item currentItem = items.get(0).get(0);
    Item compareItem = items.get(0).get(0);
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
          if(currentItem.getPrice().equals(PriceTypes.BETPERCETAGE) && compareItem.getItemType().
              equals(ItemTypes.GITKRAKEN)) {
            itemQuantity++;
          }else {
            checkPrice(itemQuantity,currentItem, totalBitCoins, prices);
            currentItem = items.get(row).get(column);
            itemQuantity = 1;
          }
        }
      }
      checkPrice(itemQuantity,currentItem, totalBitCoins, prices);
    }   
    jackpot -= (Integer)prices.get(0);
    modifyJackpot(String.valueOf(jackpot));
    return prices;
  }
  
  /**
   * Método para comprobar la posibilidad de un cliente ganar un premio
   * @param itemQuantity Cantidad de veces seguidas que apareció el item
   * @param item Item que se va a comprobar
   * @param totalBitCoins Total de items tipo BITCOIN que aparecieron en total
   * @param prices Arrat que almacena los premios
   */
  private void checkPrice(int itemQuantity,Item item, int totalBitCoins, 
      ArrayList<Object> prices) {
    int price = 0;
    int freeSpins = 0;
    if(item.getPrice().equals(PriceTypes.BETPERCETAGE)) {
      price = checkBet(itemQuantity, item);
      prices.set(0, (Integer)prices.get(0)+price);
    }else if(item.getPrice().equals(PriceTypes.JACKPOTPERCETAGE)) {
      price = checkJackpot(itemQuantity, item);
      prices.set(0, (Integer)prices.get(0)+price);
    }else {
      freeSpins = checkSpin(itemQuantity);
      prices.set(1, (Integer)prices.get(1)+freeSpins);
    }
  }
  
  /**
   * Método para comprobar el premio por apuesta
   * @param itemQuantity Cantidad de veces seguidas que salió el item
   * @param item Item que se va a comprobar
   * @return cantidad de dinero ganado
   */
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
  

  /**
   * Método para comprobar el premio por jackpot
   * @param itemQuantity Cantidad de veces seguidas que salió el item
   * @param item Item que se va a comprobar
   * @return cantidad de dinero ganado
   */
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
  
  /**
   * Método para comprobar el premio por freeSpins
   * @param itemQuantityC antidad de veces seguidas que salió el item
   * @return cantidade fresSpins ganadas
   */
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
  
  /**
   * Método para cargar el Jackpot en memoria local
   * @return cantidad del jackpot
   * @throws IOException En caso de no encontrar el archivo
   */
  private int getJackpot() throws IOException {
    FileReader file;
    try {

      file = new FileReader("C:\\Users\\Larry\\JSONFiles\\JACKPOT.txt");
      Scanner sc = new Scanner(file); 
      jackpot = sc.nextInt();
      return jackpot;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return 0;
  }
  
  /**
   * Método para modificar el jackpot en memoria local
   * @param newValue Nuevo valor del jackpot
   */
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
