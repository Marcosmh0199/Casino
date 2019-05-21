package apl;
import serverlogic.*;

public class MarkMain {
  public static void main(String[] args) {
    Item item = new Item(3);
    
    item.setItemType(ItemTypes.GITKRAKEN);
    item.setPrice(Price.SPINE);
    SerializeItem serializer = new SerializeItem();
    serializer.convertToJson(item);
    Item item2 = serializer.convertFromJson(ItemTypes.GITKRAKEN.name());
    System.out.println(item2.getItemType().name()+item2.getProbability());
    

  }
}
