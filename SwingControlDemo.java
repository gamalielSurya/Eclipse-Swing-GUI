import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class SwingControlDemo {
	
	private JFrame mainFrame;
	private JLabel headerlabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	
	private JMenuBar menuBar;
	private JMenu mainMenu, subMenu;
	private JMenuItem menuItem;
	
	public SwingControlDemo() {
		prepareGUI();
	}

	public static void main(String[] args) {
		SwingControlDemo swingControlDemo = new SwingControlDemo();
		swingControlDemo.showEventDemo();
	}
	
	private void prepareGUI() {
		mainFrame = new JFrame("Java Frame Example");
		mainFrame.setSize(400, 400);
		
		mainFrame.setLayout(new GridLayout(3,1));
		
		headerlabel = new JLabel("", JLabel.CENTER);
		statusLabel = new JLabel("", JLabel.CENTER);
		statusLabel.setSize(350, 100);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		//mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		
		menuBar = new JMenuBar();
		
		mainMenu = new JMenu("Menu A"); // Menu A Start
		
		menuItem = new JMenuItem("A-1");
		mainMenu.add(menuItem);
		
		menuItem = new JMenuItem("A-2");
		mainMenu.add(menuItem);
		
		mainMenu.addSeparator();
		
		subMenu = new JMenu("Sub Menu A-3");
		menuItem = new JMenuItem("A-3-1");
		subMenu.add(menuItem);
		menuItem = new JMenuItem("A-3-2");
		subMenu.add(menuItem);
		mainMenu.add(subMenu);	
				
		menuBar.add(mainMenu); // Menu A Finish
		
		mainMenu = new JMenu("Menu B"); // Menu B Start
		menuBar.add(mainMenu); //Menu B Finish
		
		mainFrame.setJMenuBar(menuBar); // Set MenuBar to JFrame
		
				
		mainFrame.add(headerlabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setLocationRelativeTo(null); //set window to center of the screen
		mainFrame.setVisible(true);
	}
	
	private void showEventDemo() {
		headerlabel.setText("Control in action : Button");
		
		JButton okButton = new JButton("OK");
		JButton submitButton = new JButton("Submit");
		JButton cancelButton = new JButton("Cancel");
		
		okButton.setActionCommand("OK");
		submitButton.setActionCommand("Submit");
		cancelButton.setActionCommand("Cancel");
		
		okButton.addActionListener(new ButtonClickListener());
		submitButton.addActionListener(new ButtonClickListener());
		cancelButton.addActionListener(new ButtonClickListener());
		
		controlPanel.add(okButton);
		controlPanel.add(submitButton);
		controlPanel.add(cancelButton);
		
		mainFrame.setVisible(true);
	}
	
	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
			if(command.equals("OK")) {
				statusLabel.setText("OK Button Clicked");
			} else if (command.equals("Submit")) {
				statusLabel.setText("Submit Button Clicked");
			} else {
				statusLabel.setText("Cancel Button Clicked");
			}
			
		}
		
	}

}
