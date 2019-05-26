package gui;
import java.applet.AudioClip;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.xml.transform.Source;
import client.ClientManagement;

public class iLikeCHicken implements GameConstants {
  
  private  int cont;
  private Game window;


  public iLikeCHicken() {
    this.cont = 1;
    selectStart();
  }
  
  private void selectStart() {
    if (cont ==0) {
      //this.loadLogin();
    } else {
     // this.loadGame();
    }
  }
  
  /**
   * Carga el login
   */


  
    

  public static void main(String[] args) {
    ClientManagement m = new ClientManagement();
  }
}
