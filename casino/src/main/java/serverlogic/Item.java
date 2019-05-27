package serverlogic;
/**
 * Clase para crear los items que componen el juego
 * @author Marcos
 * @version 19.5.20
 */
public class Item{
  
  private ItemTypes itemType;
  private PriceTypes price;
  private int probability;
  
  /**
   * Constructor
   * @param probability número que indica la probabilidad de aparecer del item
   */
  public Item(int probability){
    setProbability(probability);
  }
  
  public Item() {
  }
  
  public void setItemType(ItemTypes itemType) {
    this.itemType = itemType;
  }
  
  public ItemTypes getItemType(){
    return this.itemType;
  }
  
  public void setPrice(PriceTypes price) {
    this.price = price;
  }
  
  public PriceTypes getPrice() {
    return this.price;
  }
  
  private void setProbability(int probability) {
    this.probability = probability;
  }
  
  public int getProbability() {
    return this.probability;
  }
  
}
