

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

/**
 * This class is used as a general utility for a field needing an increment and decrement
 * with a text box
 * 
 * @author Cephas Armstrong-Mensah
 *
 */

public class UpDownButton extends JPanel {
  private static final long serialVersionUID = 1L;

  private JTextField frequencyTextBox;
  private JLabel incrementButton;
  private JLabel decrementButton;
  private JPanel panel;

  private double step;
  private boolean isFrequency;
  private int newWidth;

  /**
   * @param newWidth - the width for the entire panel
   * @param step - the next step size when button is clicked
   * @param isFrequency - determine if this field is for frequency
   */
  public UpDownButton(int newWidth, double step, boolean isFrequency) {
    this.step = step;
    this.isFrequency = isFrequency;
    this.newWidth = newWidth;

    setLayout(null);
    this.setMinimumSize(new Dimension(70, 30));
    setBounds(0, 0, newWidth < 70 ? 70 : newWidth, 30);

    frequencyTextBox = new JTextField("0.0");
    frequencyTextBox.setBounds(0, 0, newWidth - 30, 30);
    frequencyTextBox.setVisible(true);
    add(frequencyTextBox);
    

    panel = new JPanel();
    panel.setBounds(newWidth - 28, 0, 28, 30);
    add(panel);
    panel.setLayout(null);

    incrementButton = new JLabel(Character.toString((char) 0x028C));
    incrementButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          double x = Double.parseDouble(frequencyTextBox.getText());
          double value = 0.1;
          if (!isFrequency && x + step > 1.0) {
            value = 1.0;
          } else {
            value = x + step;
          }

          formatDoubleFirst(value);
        } catch (NumberFormatException exception) {
          frequencyTextBox.setForeground(Color.RED);
          frequencyTextBox.selectAll();
        }
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        incrementButton.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
      }

      @Override
      public void mouseExited(MouseEvent e) {
        incrementButton.setBorder(null);
      }
    });
    incrementButton.setBounds(0, 0, 26, 15);
    panel.add(incrementButton);
    incrementButton.setVerticalAlignment(SwingConstants.BOTTOM);
    incrementButton.setHorizontalTextPosition(SwingConstants.CENTER);
    incrementButton.setHorizontalAlignment(SwingConstants.CENTER);

    decrementButton = new JLabel("v");
    decrementButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          double x = Double.parseDouble(frequencyTextBox.getText());

          double value = 0.1;
          if (x - step > 0.0) {
            value = x - step;
          }

          formatDoubleFirst(value);
        } catch (NumberFormatException exception) {
          frequencyTextBox.setForeground(Color.RED);
          frequencyTextBox.selectAll();
        }
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        decrementButton.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
      }

      @Override
      public void mouseExited(MouseEvent e) {
        decrementButton.setBorder(null);
      }
    });
    decrementButton.setBounds(0, 15, 26, 15);
    panel.add(decrementButton);
    decrementButton.setVerticalAlignment(SwingConstants.TOP);
    decrementButton.setHorizontalTextPosition(SwingConstants.CENTER);
    decrementButton.setHorizontalAlignment(SwingConstants.CENTER);
    
    panel.add(frequencyTextBox);
  }

  private void formatDoubleFirst(double value) {
    DecimalFormat df = new DecimalFormat("0.00");
    this.frequencyTextBox.setText("" + df.format(value));
    this.frequencyTextBox.setForeground(Color.BLACK);
  }
  
  
  
  public String getText() {
	  return frequencyTextBox.getText();
  }
  
  
  public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame j = new JFrame();
					UpDownButton i = new UpDownButton(30,0.5,true);
					j.setVisible(true);
					j.add(i);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
