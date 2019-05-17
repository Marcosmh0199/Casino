package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JPasswordField;

public class Login {

  private JFrame frame;
  private JTextField textField;
  private JPasswordField passwordField;

  /**
   * Create the application.
   */
  public Login() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBackground(new Color(255, 255, 255));
    frame.getContentPane().setBackground(new Color(230, 230, 250));
    frame.setBounds(100, 100, 811, 535);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JPanel panel = new JPanel();
    panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    panel.setBackground(new Color(255, 153, 153));
    panel.setBounds(276, 244, 229, 182);
    frame.getContentPane().add(panel);
    panel.setLayout(null);

    textField = new JTextField();
    textField.setBounds(10, 47, 209, 20);
    panel.add(textField);
    textField.setColumns(10);

    JButton btnNewButton = new JButton("Iniciar Seccion");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {}
    });
    btnNewButton.setBounds(52, 136, 124, 23);
    panel.add(btnNewButton);

    JLabel lblNewLabel = new JLabel("Usuario");
    lblNewLabel.setBounds(90, 32, 46, 14);
    panel.add(lblNewLabel);

    JLabel lblNewLabel_1 = new JLabel("Contraseña");
    lblNewLabel_1.setBounds(78, 90, 73, 14);
    panel.add(lblNewLabel_1);

    passwordField = new JPasswordField();
    passwordField.setBounds(10, 105, 209, 20);
    panel.add(passwordField);

    JLabel lblNewLabel_3 = new JLabel("");
    lblNewLabel_3.setBounds(289, 33, 200, 200);
    frame.getContentPane().add(lblNewLabel_3);
    lblNewLabel_3
        .setIcon(new ImageIcon("C:\\Users\\Luis\\Documents\\repos\\Casino\\casino\\logo.png"));

    JLabel lblNewLabel_2 = new JLabel("");
    lblNewLabel_2
        .setIcon(new ImageIcon("C:\\Users\\Luis\\Documents\\repos\\Casino\\casino\\slot.jpg"));
    lblNewLabel_2.setBounds(0, 0, 795, 496);
    frame.getContentPane().add(lblNewLabel_2);
  }

  public JFrame getFrame() {
    return frame;
  }
}
