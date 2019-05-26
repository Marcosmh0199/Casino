package gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Game implements GameConstants {
  private JFrame frame;
  private ArrayList<ArrayList<JLabel>> gameMatrix;
  private JLabel lblJackpot = new JLabel("1.25688897498");
  public JButton btnSpine = new JButton("Spine");
  private JLabel lblBet = new JLabel("");
  public  JButton btnDecreaseBet;
  public JButton btnIncreaseBet;
  private String source;

  /**
   * Create the application.
   */
  public Game() {
    this.source = System.getProperty("user.home") + "\\sources";
    this.gameMatrix = new ArrayList<ArrayList<JLabel>>();
    initialize();
    setLabel();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 625, 462);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);
  }

  private void setLabel() {
    JPanel panel = new JPanel();
    panel.setBackground(new Color(102, 153, 51));
    panel.setBounds(0, 49, 611, 294);
    frame.getContentPane().add(panel);
    panel.setLayout(null);

    int xAxis = 20;
    int yAxis = 10;
    for (int row = 0; row < 3; row++) {
      this.gameMatrix.add(new ArrayList<JLabel>());
      for (int column = 0; column < 6; column++) {
        JLabel label = new JLabel("");
        label.setBounds(xAxis, yAxis, 100, 100);
        label.setIcon(new ImageIcon(ICONS[(int)(Math.random()*12)]));
        panel.add(label);
        this.gameMatrix.get(row).add(label);
        xAxis += 100;
      }
      yAxis += 90;
      xAxis = 20;
    }

    JLabel lblNewLabel_1 = new JLabel("New label");
    lblNewLabel_1.setIcon(new ImageIcon(COLUMN));
    lblNewLabel_1.setBounds(10, 11, 90, 272);
    panel.add(lblNewLabel_1);

    JLabel label = new JLabel("New label");
    label.setIcon(new ImageIcon(COLUMN));
    label.setBounds(110, 11, 90, 272);
    panel.add(label);

    JLabel label_1 = new JLabel("New label");
    label_1.setIcon(new ImageIcon(COLUMN));
    label_1.setBounds(210, 11, 90, 272);
    panel.add(label_1);

    JLabel label_2 = new JLabel("New label");
    label_2.setIcon(new ImageIcon(COLUMN));
    label_2.setBounds(310, 11, 90, 272);
    panel.add(label_2);

    JLabel label_3 = new JLabel("New label");
    label_3.setIcon(new ImageIcon(COLUMN));
    label_3.setBounds(410, 11, 90, 272);
    panel.add(label_3);

    JLabel label_4 = new JLabel("New label");
    label_4.setIcon(new ImageIcon(COLUMN));
    label_4.setBounds(510, 11, 90, 272);
    panel.add(label_4);

    JLabel lblNewLabel = new JLabel("New label");
    lblNewLabel.setIcon(new ImageIcon(BACKGROUND));
    lblNewLabel.setBounds(0, 0, 610, 294);
    panel.add(lblNewLabel);

    JPanel panel_2 = new JPanel();
    panel_2.setBackground(new Color(51, 255, 51));
    panel_2.setBounds(0, 342, 723, 81);
    frame.getContentPane().add(panel_2);
    panel_2.setLayout(null);


    btnSpine.setFont(new Font("Consolas", Font.PLAIN, 16));
    btnSpine.setBounds(362, 23, 185, 41);
    panel_2.add(btnSpine);

    btnIncreaseBet = new JButton("+");
    btnIncreaseBet.setFont(new Font("Consolas", Font.PLAIN, 16));
    btnIncreaseBet.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {}
    });
    btnIncreaseBet.setBounds(205, 23, 48, 41);
    panel_2.add(btnIncreaseBet);

    btnDecreaseBet = new JButton("-");
    btnDecreaseBet.setFont(new Font("Consolas", Font.PLAIN, 16));
    btnDecreaseBet.setBounds(74, 23, 48, 41);
    panel_2.add(btnDecreaseBet);


    lblBet.setForeground(Color.WHITE);
    lblBet.setBounds(125, 41, 76, 23);
    panel_2.add(lblBet);

    JLabel lblTotalBet = new JLabel("Total Bet");
    lblTotalBet.setForeground(new Color(255, 255, 255));
    lblTotalBet.setBackground(new Color(0, 255, 0));
    lblTotalBet.setBounds(132, 23, 69, 23);
    panel_2.add(lblTotalBet);

    JLabel lblNewLabel_3 = new JLabel("");
    lblNewLabel_3.setIcon(new ImageIcon(BET));
    lblNewLabel_3.setBackground(new Color(204, 0, 51));
    lblNewLabel_3.setBounds(118, 23, 89, 41);
    panel_2.add(lblNewLabel_3);

    JLabel lblNewLabel_8 = new JLabel("New label");
    lblNewLabel_8.setIcon(new ImageIcon(DOWN_SIDE));
    lblNewLabel_8.setBounds(0, 0, 616, 81);
    panel_2.add(lblNewLabel_8);

    JPanel panel_1 = new JPanel();
    panel_1.setBackground(new Color(255, 204, 51));
    panel_1.setBounds(0, 0, 723, 49);
    frame.getContentPane().add(panel_1);
    panel_1.setLayout(null);

    lblJackpot.setForeground(new Color(204, 255, 255));
    lblJackpot.setBounds(253, 11, 170, 30);
    panel_1.add(lblJackpot);

    JLabel lblNewLabel_4 = new JLabel("New label");
    lblNewLabel_4.setIcon(new ImageIcon(JACKCOIN));
    lblNewLabel_4.setBounds(213, 11, 30, 30);
    panel_1.add(lblNewLabel_4);

    JLabel lblNewLabel_5 = new JLabel("New label");
    lblNewLabel_5.setForeground(new Color(204, 255, 255));
    lblNewLabel_5.setIcon(new ImageIcon(JACKPOT));
    lblNewLabel_5.setBackground(new Color(255, 153, 0));
    lblNewLabel_5.setBounds(225, 11, 170, 30);
    panel_1.add(lblNewLabel_5);

    JLabel lblNewLabel_7 = new JLabel("New label");
    lblNewLabel_7.setIcon(new ImageIcon(UP_SIDE));
    lblNewLabel_7.setBounds(0, 0, 631, 49);
    panel_1.add(lblNewLabel_7);
  }

  public JFrame getFrame() {
    return this.frame;
  }

  public void setIcon(int row, int column, ImageIcon icon) {
    gameMatrix.get(row).get(column).setIcon(icon);
  }

  public void setLblBet(String text) {
    this.lblBet.setText(text);
  }

  public void setLblJackpot(String text) {
    this.lblJackpot.setText(text);
  }

  public ArrayList<ArrayList<JLabel>> getGameMatrix() {
    return this.gameMatrix;
  }

  public int getLblBet() {
    return Integer.parseInt(lblBet.getText());
  }
  
  public int getLblJackpot() {
    return Integer.parseInt(lblJackpot.getText());
  }


}
