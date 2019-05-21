package serverlogic;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;

public class SerializeItem {
  final String DIR = System.getProperty("user.home");

  public SerializeItem(){
    if(!(Files.exists(Paths.get(DIR+"\\ItemsJson")))){
      File dir = new File(DIR+"\\ItemsJson");
      dir.mkdir();
    }
  }
  
  public void convertToJson(Item item) {
    Gson gson = new Gson();
    String json = gson.toJson(item);
    FileWriter writer;
    try {
      writer = new FileWriter(DIR+"\\ItemsJson\\"+item.getItemType().name()+".json");
      writer.write(json);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public Item convertFromJson(String name) {
    Gson gson = new Gson();
    try {
      BufferedReader br = new BufferedReader(new FileReader(DIR+"\\ItemsJson\\"+name+".json"));
      Item item = gson.fromJson(br, Item.class);
      return item;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
