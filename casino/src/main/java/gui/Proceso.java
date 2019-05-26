package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Proceso extends Thread implements GameConstants{

  private Game game;
  private int column;
  public Proceso(Game game, int column){
    this.game = game;
    this.column = column;
  }
  public void run() {

    int cont = 0;
   for (int i = 0; i< 20; i++) {
    game.setIcon(2,this.column, (ImageIcon)game.getGameMatrix().get(1).get(this.column).getIcon());
    game.setIcon(1, this.column, (ImageIcon)game.getGameMatrix().get(0).get(this.column).getIcon());
    game.setIcon(0, this.column, new ImageIcon(ICONS[(int)(Math.random()*12)]));
    try {
      Thread.sleep(50);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

    }
}
