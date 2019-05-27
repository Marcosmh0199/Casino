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

/**
 * Clase para convertir cualquier objeto a .JSON y viceversa
 * @author Marcos
 * @version v19.20.05
 *
 */
public class SerializeObject {
  final private String DIR = System.getProperty("user.home");

  /**
   * Constructor 
   * 
   */
  public SerializeObject(){
    checkDirectories();
  }
  
  /**
   * Método que verifica si existen los directorios necesarios para almacenar los archivos que creara el programa
   */
  private void checkDirectories() {
    if(!(Files.exists(Paths.get(DIR+"\\JSONFiles")))){
      File dir = new File(DIR+"\\JSONFiles");
      dir.mkdir();
    }
    if(!(Files.exists(Paths.get(DIR+"\\JSONFiles\\Items")))){
      File dir = new File(DIR+"\\JSONFiles\\Items");
      dir.mkdir();
    }
    if(!(Files.exists(Paths.get(DIR+"\\JSONFiles\\Rules")))){
      File dir = new File(DIR+"\\JSONFiles \\Rules");
      dir.mkdir();
    }
  }
  
  /**
   * Metodo para convertir un objeto a .json
   * @param item objeto que sera convertido a .json
   * @param name Nombre con el que sera guardado el objeto
   * @param folder carpeta donde se guardara el objeto
   */
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
  
  /**
   * Metodo para cargar un .json y convertirlo a un objeto segun convenga
   * @param folder Carpeta donde se ubica el .json
   * @param name nombre del archivo que sera convetido
   * @param cls Clase a la que se adecuara el objeto
   * @return null si el archivo buscado no existe, sino retorna el objeto del tipo especificado
   */
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
