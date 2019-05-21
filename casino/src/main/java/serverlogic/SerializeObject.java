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

public class SerializeObject {
  final String DIR = System.getProperty("user.home");

  public SerializeObject(){
    if(!(Files.exists(Paths.get(DIR+"\\JSONFiles")))){
      File dir = new File(DIR+"\\JSONFiles");
      dir.mkdir();
    }
  }
  
  public void convertToJson(Object item, String name, String folder) {
    Gson gson = new Gson();
    String json = gson.toJson(item);
    FileWriter writer;
    try {
      writer = new FileWriter(DIR+"\\JSONFiles\\"+folder+"\\"+name+".json");
      writer.write(json);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public Object convertFromJson(String folder, String name, Class<?> cls) {
    Gson gson = new Gson();
    FileReader file;
    try {
      file = new FileReader(DIR+"\\JSONFiles\\"+folder+"\\"+name+".json");
      BufferedReader br = new BufferedReader(file);
      Object object = gson.fromJson(br, cls);
      return object;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
