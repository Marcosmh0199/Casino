package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import controller.ServerController;

public class Server {
  private ServerSocket serverSocket;
  private ServerController controller;
  private Socket socket;
  private static final Logger logger = LogManager.getLogger(Server.class);
  private String input; 
  
  public Server(int port, int queueSize) throws IOException {
    serverSocket = new ServerSocket(port, queueSize); 
    controller = new ServerController();
  }
  
  public void executeServer() {
    while(true) {
      try {
        boolean over = true;
        logger.info("Se inicia el servidor");
        while(over) {
          logger.info("Esperando comando");
          socket = serverSocket.accept();   
          DataInputStream in = new DataInputStream(socket.getInputStream());
          input = in.readUTF();
          //DataOutputStream out = new DataOutputStream(socket.getOutputStream());
          //out.writeUTF("Comando aceptado\n");
          logger.info("Comando "+input+" recibido");
          over = executeCommand(input);
        }
        socket.close();
      }catch (SocketTimeoutException s) {
        System.out.println("Socket timed out!");
        break;
      }catch (IOException e) {
        e.printStackTrace();
        break;
       } 
    }
  }
  
  private boolean executeCommand(String inputLine) {
    String command = inputLine.substring(0,2);
    if(command.equals("-p")) { 
      try {
        ArrayList<Object> aux = controller.play();
        logger.info("El cliente hizo una solicitud de juego");
        ObjectOutputStream  oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(aux);
        oos.close();
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return true;
    }else if(command.equals("-c")) {  
      String currentBet = controller.getGame().getCurrentBet();
      String number = inputLine.substring(3, inputLine.length());
      Integer newBet = Integer.parseInt(number);
      logger.info("El cliente cambia la apuesta de "+currentBet+" a: "+ newBet.toString());
      controller.getGame().setCurrentBet(newBet);
      try {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF("Cambio realizado!");
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return true;
    }else {
      logger.info("El cliente cierra la sesion");
      return false;
    }
  }
  
  public static void main(String[] args) {
    int port = Integer.parseInt(args[0]);
    int queue = Integer.parseInt(args[1]);
    try {
       Server server = new Server(port, queue);
       server.executeServer();
    } catch (IOException e) {  
       e.printStackTrace();
    }
 }
}
