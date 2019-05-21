package apl;
import serverlogic.*;

public class MarkMain {
  public static void main(String[] args) {
    Item item = new Item(3);
    
    item.setItemType(ItemTypes.BITCOIN);
    item.setPrice(Price.SPINE);
    
    SerializeObject serializer = new SerializeObject();
    
    serializer.convertToJson(item, item.getItemType().name(), "Items");
    Item item2 = new Item();
    item2 = (Item) serializer.convertFromJson("Items", item.getItemType().name(),item2.getClass());
    System.out.println(item2.getItemType().name());

  }
}
