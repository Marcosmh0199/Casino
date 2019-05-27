package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Clase permite establecer conexion con un servidor.
 * 
 * @author Luis
 * @version v4.28.19
 * 
 */
public class Client {
  private String serverName;
  private int port;

  /**
   * Constructor de objetos de clase Cliente.
   * 
   * @param serverName Direccion ip del servidor.
   * @param port Puerto en el que se establecera la conexion.
   */
  public Client(String serverName, int port) {
    this.serverName = serverName;
    this.port = port;
  }

  /**
   * Establece una conexion con el servidor. Permite el intercambio de strings con el servidor.
   * 
   * @param outMensaje Mensaje que sera enviado al servidor.
   * @return Retorna el mensaje recibido del servidor.
   * @throws UnknownHostException
   * @throws IOException
   */
  public String conection(String outMensaje) {
    try {
      Socket socket = new Socket(this.serverName, this.port);
      
      OutputStream outToServer = socket.getOutputStream();
      DataOutputStream out = new DataOutputStream(outToServer);
      out.writeUTF(outMensaje);

      InputStream inFromServer = socket.getInputStream();
      DataInputStream in = new DataInputStream(inFromServer);
      String serverMensaje = in.readUTF();
      socket.close();
      return serverMensaje;
    } catch (UnknownHostException e) {
      return "No es posible encontrar el servidor";
    } catch (IOException e) {
      return "Excepcion con la IO";
    }
  }

  /**
   * Modela la conexion para recibir objetos desde el servidor.
   * 
   * @param outMensaje Comando que indica la peticion al servidor.
   * @return El objeto enviado por el servidor.
   */
  @SuppressWarnings("unchecked")
  public ArrayList<Object> playRequest(String outMensaje) {
    ArrayList<Object> data = new ArrayList<Object>();
    try {
      Socket socket = new Socket(this.serverName, this.port);

      OutputStream outToServer = socket.getOutputStream();
      DataOutputStream out = new DataOutputStream(outToServer);
      out.writeUTF(outMensaje);

      InputStream inFromServer = socket.getInputStream();
      DataInputStream in = new DataInputStream(inFromServer);
      ObjectInputStream ob = new ObjectInputStream(in);
      data = new ArrayList<Object>();
      try {
        data = (ArrayList<Object>) ob.readObject();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }

      socket.close();
    } catch (UnknownHostException e) {

    } catch (IOException e) {

    }
    return data;
  }
}


