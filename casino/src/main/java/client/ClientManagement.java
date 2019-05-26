package client;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ArrayBlockingQueue;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import gui.Game;
import gui.GameConstants;
import gui.Login;
import gui.Proceso;

public class ClientManagement implements GameConstants {
  private Player player;
  private Game window;
  private Client client;
  public ClientManagement() {
    this.startGame();
  }
  /**
   * Prepara los datos para el juego.
   */
  private void startGame() {
    this.client = new Client("172.26.38.32",60000);
    Keeper.checkDirectory();
    if(Keeper.convertFromJson() != null) {
      this.player = Keeper.convertFromJson();
      this.verifyDailyBonus();
      this.loadGame(player.getCredits());
    } else {
      this.loadLogin();
    }
  }
  
  @SuppressWarnings({"deprecation", "unused"})
  private void verifyDailyBonus() {
    if (player.getLastLogin().getDay() < Calendar.getInstance().getTime().getDay() &&
        player.getLastLogin().getHours() <= Calendar.getInstance().getTime().getHours()) {
      this.player.setCredits(this.player.getCredits() + 1000);
    }
  }
  
  /**
   * carga la ventana del login.
   */
  private void loadLogin() {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          final Login window = new Login();
          window.getFrame().setVisible(true);
          window.getLoginButton().addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
              player = new Player(window.getPlayerName(), window.getPasswordField());
              Keeper.convertToJson(player);
              loadGame(player.getCredits());
            }
          });
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
  
  private void loadGame(final int credit) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        window = new Game();
        window.setLblJackpot(credit + "");
        window.setLblBet("1000");
        window.getFrame().setVisible(true);
        window.btnSpine.addActionListener(new ActionListener() {
          
          public void actionPerformed(ActionEvent arg0) {

            
              animation();
              ArrayList<Object> j = new ArrayList<Object>();
              j = client.ask("-p");
              int jp = (Integer) j.get(0);
              window.setLblJackpot(jp + window.getLblJackpot()+"");
              System.out.println(j);
              ArrayList<ArrayList<String>> st = new ArrayList<ArrayList<String>>();
              st = (ArrayList<ArrayList<String>>) j.get(2);
              for (int i = 0; i< st.size(); i++) {
                for(int x = 0; x < st.get(i).size(); x++) {
                window.getGameMatrix().get(i).get(x).setIcon(new ImageIcon(SOURCE + "\\"+st.get(i).get(x)+".png"));
                }
              }

          }
        });
        
        
       window.btnDecreaseBet.addActionListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent arg0) {
          
          window.setLblBet(window.getLblBet()-500 + "");
          System.out.print(client.conection("-c " + window.getLblBet()));
    
        }
      });
       
       window.btnIncreaseBet.addActionListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent e) {
          window.setLblBet(window.getLblBet()+500 + "");
          client.conection("-c " + window.getLblBet());
          
        }
      });
        
      }
    });
  }
  
  public void animation() {

    for (int column = 0; column < 6; column++) {
      Thread t = new Proceso(window, column);
      t.start();
    }
  }
}
