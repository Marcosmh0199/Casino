package serverlogic;
/**
 * Clase para crear los items que componen el juego
 * @author Marcos
 * @version 19.5.20
 */
public class Item{
  
  private ItemTypes itemType;
  private Price price;
  private int probability;
  
  public Item(int probability){
    setProbability(probability);
  }
  
  public void setItemType(ItemTypes itemType) {
    this.itemType = itemType;
  }
  
  public ItemTypes getItemType(){
    return this.itemType;
  }
  
  public void setPrice(Price price) {
    this.price = price;
  }
  
  public Price getPrice() {
    return this.price;
  }
  
  private void setProbability(int probability) {
    this.probability = probability;
  }
  
  public int getProbability() {
    return this.probability;
  }
  
}
