package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class EmptyJackpotDialog extends JDialog {

  private final JPanel contentPanel = new JPanel();
  /**
   * Create the dialog.
   */
  public EmptyJackpotDialog() {
    setBounds(100, 100, 204, 197);
    this.setLocationRelativeTo(null); 
    getContentPane().setLayout(new BorderLayout());
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(null);
    {
      JLabel lblNewLabel = new JLabel("New label");
      lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Luis\\sources\\emptyjackpot.png"));
      lblNewLabel.setBounds(0, 0, 195, 167);
      contentPanel.add(lblNewLabel);
    }
  }

}
