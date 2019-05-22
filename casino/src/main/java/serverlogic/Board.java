package serverlogic;

import java.util.ArrayList;
/**
 * Clase encargada de crear el tablero de juego.
 * @author Vega-Luis
 * @version v19.20.5
 */
public class Board {
  private ArrayList<ArrayList<Item>> board;
  private ArrayList<Item> itemChoice;
  SerializeObject serializer;
  
  /**
   * Constructor de objetos de clase Board.
   * Inicializa los atributos de la clase Board.
   */
  public Board() {
    this.initializateBoard();
    this.itemChoice = new  ArrayList<Item>();
    this.serializer = new SerializeObject();
    this.loadItems();
    this.permute();
  }
  
  public ArrayList<ArrayList<Item>> getBoard() {
    return board;
  }
  
  /**
   * Esta funcion se encarga de permutar el arreglo que contiene los items.
   */
  private void permute() {
    for (int itemIndex = 0; itemIndex < itemChoice.size(); itemIndex++) {
      int newIndex = (int) (Math.random() * itemChoice.size());
      Item item = itemChoice.get(newIndex);
      itemChoice.set(newIndex, itemChoice.get(itemIndex));
      itemChoice.set(itemIndex, item);
    }
  }
  
  /** 
   * Crea el tablero que sera mostrado
   */
  public void generateBoard() {
    for (int row = 0; row < board.size(); row++) {
      for (int column = 0; column < board.get(row).size(); column++) {
        board.get(row).set(column, itemChoice.get((int)(Math.random()*itemChoice.size()+ 0)));
        System.out.print(board.get(row).get(column).getItemType() + " " );
      }
      System.out.println();
    }
  }
  
  /**
   * Carga los items desde los archivos Json.
   */
  private void loadItems() {
    for (ItemTypes item : ItemTypes.values()) { 
    Item auxItem = (Item) serializer.convertFromJson("Items", item.toString(), Item.class );
      for (int cant = 0; cant < auxItem.getProbability(); cant++) {
        itemChoice.add(auxItem);
      }
    }
  }
  
  /**
   * Inicializa el tablero
   */
  private void initializateBoard() {
    this.board = new ArrayList<ArrayList<Item>>();
    for (int row = 0; row < 3; row++) {
      board.add(new ArrayList<Item>());
      for (int column = 0; column < 6; column++) {
        board.get(row).add(null);
      }
      }
  }
}
