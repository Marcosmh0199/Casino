package client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JDialog;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import gui.Game;
import gui.GameConstants;
import gui.Login;
import gui.Animator;
import gui.FreeSpinesDialog;
import gui.CreditWinsDialog;
import gui.EmptyJackpotDialog;

public class ClientManagement implements GameConstants {
  private Player player;
  private Game window;
  private Client client;
  private int betPos;
  private int spines;

  public ClientManagement() {
    this.startGame();
  }

  /**
   * Prepara los datos para el juego.
   */
  private void startGame() {
    this.client = new Client("192.168.43.188", 60000);
    Keeper.checkDirectory();
    if (Keeper.convertFromJson() != null) {
      this.player = Keeper.convertFromJson();
      this.verifyDailyBonus();
      this.loadGame(player.getCredits());
    } else {
      this.loadLogin();
    }
  }

  @SuppressWarnings({"deprecation", "unused"})
  private void verifyDailyBonus() {
    if (player.getLastLogin().getDay() < Calendar.getInstance().getTime().getDay()
        && player.getLastLogin().getHours() <= Calendar.getInstance().getTime().getHours()) {
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

  /**
   * Carga la ventana principal de juego.
   * 
   * @param credit
   */
  private void loadGame(final int credit) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        window = new Game();
        exitActionListener();
        window.setLblJackpot(String.valueOf(credit));
        window.setLblBet("100");
        window.getFrame().setVisible(true);
        btnSpineActionListener();
        btnIncreaseBetActionListener();
        btnDecreaseBetActionListener();
      }

    });
  }

  /**
   * Asigna action listener al boton girar.
   */
  public void btnSpineActionListener() {
    window.btnSpine.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        if (window.getLblJackpot() - BET_AMOUNT[betPos] >= 0) {
          ArrayList<Object> serverSend = new ArrayList<Object>();
          serverSend = client.playRequest(PLAY);
          if (serverSend == null) {
            showEmpytJackpotDialog();
            return;
          }
          int wonAmount = (Integer) serverSend.get(0);
          if (wonAmount != 0) {
            showWinWindow(wonAmount);
          }
          spines += (Integer) serverSend.get(1);
          if ((Integer) serverSend.get(1) != 0) {
            showFreeSpinesWindow((Integer) serverSend.get(1));
          }
          if (spines == 0) {
            window.setLblJackpot(String.valueOf(window.getLblJackpot() - BET_AMOUNT[betPos]));
          } else {
            spines--;
          }
          window.setLblJackpot(String.valueOf(wonAmount + window.getLblJackpot()));
          System.out.println(serverSend);

          ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();
          board = (ArrayList<ArrayList<String>>) serverSend.get(2);
          refreshBoard(board);
        }
      }
    });
  }

  /**
   * Asigna el action listener del boton incrementar apuesta
   */
  public void btnIncreaseBetActionListener() {
    window.btnIncreaseBet.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        increaseBet();
      }
    });
  }

  /**
   * Asigna action listener al boton decrementar apuesta.
   */
  public void btnDecreaseBetActionListener() {
    window.btnDecreaseBet.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        decreaseBet();
      }
    });
  }

  /**
   * Incrementa la apuesta tanto en el servidor, como en la interfaz.
   */
  public void increaseBet() {
    if (betPos < 5) {
      this.betPos++;
      window.setLblBet(String.valueOf(BET_AMOUNT[betPos]));
      client.conection(CHANGE_BET + BET_AMOUNT[betPos]);
    }
  }

  /**
   * Decrementa la apuesta tanto en el servidor, como en la interfaz.
   */
  public void decreaseBet() {
    if (betPos > 0) {
      this.betPos--;
      window.setLblBet(String.valueOf(BET_AMOUNT[betPos]));
      client.conection(CHANGE_BET + BET_AMOUNT[betPos]);
    }
  }

  /**
   * Realiza la animacion de los iconos y actualiza el tablero.
   * 
   * @param array Arreglo que contiene la board generada por el servidor.
   */
  public void refreshBoard(ArrayList<ArrayList<String>> board) {
    for (int column = 0; column < 6; column++) {
      ArrayList<String> boardColumn = new ArrayList<String>();
      for (int row = 0; row < 3; row++) {
        boardColumn.add(board.get(row).get(column));
      }
      Thread t = new Animator(window, column, boardColumn);
      t.start();
    }
  }

  /**
   * Muestra la ventana de gane.
   * 
   * @param amount Monto que gano el jugador.
   */
  public void showWinWindow(int amount) {
    try {
      CreditWinsDialog dialog = new CreditWinsDialog();
      dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      dialog.setVisible(true);
      dialog.lblNewLabel.setText(String.valueOf(amount));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Muestra la ventana de los freeSpines
   * 
   * @param spines Cantidad de espines ganadas
   */
  public void showFreeSpinesWindow(int spines) {
    FreeSpinesDialog dialog = new FreeSpinesDialog();
    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    dialog.setVisible(true);
    dialog.label.setText(String.valueOf(spines));
  }

  /**
   * Asigna una acciona al salir del juego.
   */
  public void exitActionListener() {
    this.window.getFrame().addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent evt) {
        client.conection(CLOSE);
        player.setCredits(window.getLblJackpot());
        Keeper.convertToJson(player);
        System.exit(0);
      }
    });
  }
  
  public void showEmpytJackpotDialog() {
        EmptyJackpotDialog dialog = new EmptyJackpotDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        }
}
