package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;
import gui.GameConstants;

/**
 * Clase encargada de guardar la informaciond el los jugadores
 * 
 * @author Vega-Luis
 * @version v19.5.25
 */
public abstract class Keeper implements GameConstants {
  public static final String NAME = "\\players";

  /**
   * Verifica que los directorios existan.
   */
  public static void checkDirectory() {
    if (!(Files.exists(Paths.get(SOURCE + NAME)))) {
      File dir = new File(SOURCE + NAME);
      dir.mkdir();
    }
  }

  /**
   * Guarda los datos del jugador en un json.
   * 
   * @param player
   */
  public static void convertToJson(Player player) {
    Gson gson = new Gson();
    String json = gson.toJson(player);
    FileWriter writer;
    try {
      writer = new FileWriter(SOURCE + NAME + NAME + ".json");
      writer.write(json);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Metodo para cargar un .json y convertirlo a un objeto segun convenga
   * 
   * @param folder Carpeta donde se ubica el .json
   * @param name nombre del archivo que sera convetido
   * @param cls Clase a la que se adecuara el objeto
   * @return null si el archivo buscado no existe, sino retorna el objeto del tipo especificado
   */
  public static Player convertFromJson() {
    Gson gson = new Gson();
    FileReader file;
    try {
      file = new FileReader(SOURCE + NAME + NAME + ".json");
      BufferedReader br = new BufferedReader(file);
      Player player = gson.fromJson(br, Player.class);
      return player;
    } catch (FileNotFoundException e) {
      return null;
    }
  }

}
