package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

public class CreditWinsDialog extends JDialog {

  private final JPanel contentPanel = new JPanel();
  public JLabel lblNewLabel;
  private final JLabel lblNewLabel_1 = new JLabel("New label");
  /**
   * Launch the application.
   */

  /**
   * Create the dialog.
   */
  public CreditWinsDialog() {
    
    setBounds(100, 100, 200, 150);
    this.setLocationRelativeTo(null); 
    getContentPane().setLayout(new BorderLayout());
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(null);
    {
      lblNewLabel = new JLabel("New label");
      lblNewLabel.setForeground(Color.WHITE);
      lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
      lblNewLabel.setBounds(32, 86, 126, 14);
      contentPanel.add(lblNewLabel);
    }
    {
      lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Luis\\sources\\win.png"));
      lblNewLabel_1.setBounds(0, 0, 184, 111);
      contentPanel.add(lblNewLabel_1);
    }
  }

}
