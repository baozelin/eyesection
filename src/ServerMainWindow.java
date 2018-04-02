import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;



public class ServerMainWindow extends JFrame {
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
	

	

	  /**
	   * panel shows all information. panel shows highest value. panel shows lowest
	   * value. panel shows frequency. status of server.
	   * 
	   */
	  private static JPanel contentPane;
	  private static JTextField highestValueTextBox;
	  private static JTextField lowestValueTextBox;
	  private static JTextField frequencyValueTextBox;
	 // private static ToggleButton startStopButton;
	  private boolean isStarted;
	  private static int min, max, frequency;
	  //private Server server;
	  private JLabel indicatorLabel;
	  //private ConsolePanel consolePanel;
	  private Thread sThread;

	  public static void main(String[] args) {
	    ServerMainWindow frame = new ServerMainWindow();
	    frame.setVisible(true);
	  }

	  /**
	   * Creates the main window frame. It displays the highest value, lowest value
	   * frequency and the console.
	   * 
	   */
	  public ServerMainWindow() {
	    min = 0;
	    max = 1024;
	    frequency = 5;
	    isStarted = false;
	    setBounds(new Rectangle(0, 700, 700, 0));
	    setTitle("SERVER");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 502, 500);

	    contentPane = new JPanel();
	    contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
	    contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
	    contentPane.setBounds(new Rectangle(0, 650, 650, 0));
	    contentPane.setForeground(Color.GREEN);
	    contentPane.setBackground(Color.BLUE);
	    contentPane.setBorder(new LineBorder(Color.BLACK, 1, true));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);

	

	    JPanel maxMinFrequencyPanel = new JPanel();
	    maxMinFrequencyPanel.setBackground(Color.BLUE);
	    maxMinFrequencyPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, null));
	    maxMinFrequencyPanel.setBounds(230, 50, 250, 250);
	    contentPane.add(maxMinFrequencyPanel);
	    maxMinFrequencyPanel.setLayout(null);

	    highestValueTextBox = new JTextField();
	    highestValueTextBox.setEditable(false);
	    highestValueTextBox.setBorder(new LineBorder(Color.BLACK));
	    highestValueTextBox.setHorizontalAlignment(SwingConstants.CENTER);
	    highestValueTextBox.setFont(new Font("Dialog", Font.BOLD, 24));
	    highestValueTextBox.setBounds(140, 15, 100, 55);
	    highestValueTextBox.setBackground(Color.PINK);
	    highestValueTextBox.setColumns(10);
	    highestValueTextBox.setEditable(!isStarted);
	    highestValueTextBox.setText(max + "");
	    maxMinFrequencyPanel.add(highestValueTextBox);

	    lowestValueTextBox = new JTextField();
	    lowestValueTextBox.setHorizontalAlignment(SwingConstants.CENTER);
	    lowestValueTextBox.setBorder(new LineBorder(Color.BLACK));
	    lowestValueTextBox.setFont(new Font("Dialog", Font.BOLD, 24));
	    lowestValueTextBox.setColumns(10);
	    lowestValueTextBox.setBackground(Color.BLUE);
	    lowestValueTextBox.setBounds(140, 85, 100, 55);
	    lowestValueTextBox.setEditable(!isStarted);
	    lowestValueTextBox.setText(min + "");
	    maxMinFrequencyPanel.add(lowestValueTextBox);

	    frequencyValueTextBox = new JTextField();
	    frequencyValueTextBox.setHorizontalAlignment(SwingConstants.CENTER);
	    frequencyValueTextBox.setBorder(new LineBorder(Color.BLACK));
	    frequencyValueTextBox.setAlignmentY(Component.TOP_ALIGNMENT);
	    frequencyValueTextBox.setFont(new Font("Dialog", Font.BOLD, 24));
	    frequencyValueTextBox.setColumns(10);
	    frequencyValueTextBox.setBackground(Color.PINK);
	    frequencyValueTextBox.setBounds(140, 155, 100, 55);
	    frequencyValueTextBox.setEditable(!isStarted);
	    frequencyValueTextBox.setText(frequency + "");
	    maxMinFrequencyPanel.add(frequencyValueTextBox);

	    JLabel lowestValue = new JLabel("<html>Lowest<br>Value:</html>", JLabel.CENTER);
	    lowestValue.setFont(new Font("Dialog", Font.BOLD, 16));
	    lowestValue.setBorder(new LineBorder(Color.BLACK));
	    lowestValue.setBackground(Color.PINK);
	    lowestValue.setHorizontalTextPosition(SwingConstants.CENTER);
	    lowestValue.setBounds(15, 85, 100, 55);
	    maxMinFrequencyPanel.add(lowestValue);
	    lowestValue.setOpaque(true);

	    JLabel highestValue = new JLabel("<html>Highest<br>Value:</html>");
	    highestValue.setFont(new Font("Dialog", Font.BOLD, 16));
	    highestValue.setBorder(new LineBorder(Color.BLACK));
	    highestValue.setHorizontalTextPosition(SwingConstants.CENTER);
	    highestValue.setHorizontalAlignment(SwingConstants.CENTER);
	    highestValue.setBounds(15, 15, 100, 55);
	    highestValue.setText("<html>Highest<br>value:</html>");
	    maxMinFrequencyPanel.add(highestValue);
	    highestValue.setBackground(Color.GRAY);

	    JLabel lblFrequency = new JLabel("<html>Frequency<br>(Hz):</html>", SwingConstants.CENTER);
	    lblFrequency.setFont(new Font("Dialog", Font.BOLD, 16));
	    lblFrequency.setBorder(new LineBorder(Color.BLACK));
	    lblFrequency.setHorizontalTextPosition(SwingConstants.CENTER);
	    lblFrequency.setBounds(15, 155, 100, 55);
	    maxMinFrequencyPanel.add(lblFrequency);

	    JPanel statusIndicatorPanel = new JPanel();
	    statusIndicatorPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    statusIndicatorPanel.setBounds(10, 50, 215, 250);
	    statusIndicatorPanel.setBorder(new LineBorder(Color.BLACK));
	    statusIndicatorPanel.setBackground(Color.GREEN);
	    contentPane.add(statusIndicatorPanel);
	    statusIndicatorPanel.setLayout(null);

	    indicatorLabel = new JLabel(Character.toString((char) 0x2022), SwingConstants.CENTER);
	    indicatorLabel.setBounds(10, 20, 200, 200);
	    statusIndicatorPanel.add(indicatorLabel);
	    indicatorLabel.setHorizontalTextPosition(SwingConstants.CENTER);
	    indicatorLabel.setForeground(isStarted ? Color.GREEN : Color.RED);
	    indicatorLabel.setFont(indicatorLabel.getFont().deriveFont(300f));

	  }

	  /**
	
	  /**
	   * Implements the functionality for the start/stop button.
	   * 
	   * @param status
	   *          of server, start or close.
	   */


	
	

	

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
