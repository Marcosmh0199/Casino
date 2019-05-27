package gui;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

/**
 * Modela el mensaje que se muestra cuando el jackpot es vacio.
 * 
 * @author Vega-Luis
 * @version v19.5.27
 *
 */
public class EmptyJackpotDialog extends JDialog {

  private final JPanel contentPanel = new JPanel();

  /**
   * Create the dialog.
   */
  public EmptyJackpotDialog() {
    setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Luis\\sources\\icon.png"));
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
