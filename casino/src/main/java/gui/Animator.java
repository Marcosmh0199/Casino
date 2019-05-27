package gui;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Implementa un hilo que le permite hacer la animacion del tablero.
 * 
 * @author Vega-Luis
 * @version v19.5.26
 */
public class Animator extends Thread implements GameConstants {
  private Game game;
  private int column;
  private ArrayList<String> items;

  /**
   * Construtor de objetos de clase Animator
   * 
   * @param game Instacia de la ventana de principal de juego.
   * @param column Numero de la columna que se esta animando.
   * @param items Arreglo que contiene los items que debe mostrar la columna.
   */
  public Animator(Game game, int column, ArrayList<String> items) {
    this.game = game;
    this.column = column;
    this.items = items;
  }

  /**
   * Metodo para correr el hilo.
   */
  public void run() {
    for (int i = 0; i < 20; i++) {
      game.setIcon(2, this.column,
          (ImageIcon) game.getGameMatrix().get(1).get(this.column).getIcon());
      game.setIcon(1, this.column,
          (ImageIcon) game.getGameMatrix().get(0).get(this.column).getIcon());
      game.setIcon(0, this.column, new ImageIcon(ICONS[(int) (Math.random() * 12)]));
      try {
        Thread.sleep(30);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    for (int row = 0; row < 3; row++) {
      game.setIcon(row, column, new ImageIcon(SOURCE + "\\" + items.get(row) + ".png"));
    }
  }
}
