package gui;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

/**
 * Modela el mensaje mostrado al ganar spins gratis.
 * 
 * @author Vega-Luis
 * @version v19.5.27
 *
 */
public class FreeSpinesDialog extends JDialog {

  private final JPanel contentPanel = new JPanel();
  public JLabel label;

  /**
   * Create the dialog.
   */
  public FreeSpinesDialog() {
    setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Luis\\sources\\icon.png"));

    setBounds(100, 100, 200, 150);
    this.setLocationRelativeTo(null);
    getContentPane().setLayout(new BorderLayout());
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(null);
    {
      label = new JLabel("99");
      label.setForeground(Color.ORANGE);
      label.setFont(new Font("Tahoma", Font.BOLD, 16));
      label.setBounds(88, 80, 27, 20);
      contentPanel.add(label);
    }

    JLabel lblNewLabel = new JLabel("New label");
    lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Luis\\sources\\freeSpins.png"));
    lblNewLabel.setBounds(0, 0, 184, 111);
    contentPanel.add(lblNewLabel);
  }

}
