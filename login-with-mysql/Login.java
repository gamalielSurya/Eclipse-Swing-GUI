package gama.com;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Wrapper;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class Login {
	
	static final String DB_URL = "jdbc:mysql://127.0.0.1/demo-model";
	static final String USER = "root";
	static final String PASS = "root";
	

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JFrame frmLoginSystem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login Demo");
		lblLogin.setBounds(215, 23, 100, 14);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(39, 76, 68, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(39, 127, 46, 14);
		frame.getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(150, 73, 192, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(150, 124, 192, 20);
		frame.getContentPane().add(txtPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mUsername = txtUsername.getText();
				String mPassword = txtPassword.getText();
				
				/*if(loginValidation(mUsername, mPassword)) {
					txtUsername.setText(null);
					txtPassword.setText(null);
					
					JOptionPane.showMessageDialog(null, "Welcome back "+ mUsername, "Login Success", JOptionPane.INFORMATION_MESSAGE);
					
					//Travelling.main(null);
					//or
					//Travelling travel = new Travelling();
					//travel.main(null);
					
				} else {
					JOptionPane.showMessageDialog(null, "Invalid login !!", "Login Error", JOptionPane.ERROR_MESSAGE);
				}*/
				
				insertUser(mUsername,mPassword);
			}
		});
		btnLogin.setBounds(39, 195, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsername.setText(null);
				txtPassword.setText(null);
			}
		});
		btnReset.setBounds(161, 195, 89, 23);
		frame.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLoginSystem = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm exit ?", "Login System", 
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setBounds(295, 195, 89, 23);
		frame.getContentPane().add(btnExit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 182, 464, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 48, 464, 2);
		frame.getContentPane().add(separator_1);
	}
	
	private void insertUser(String tname, String tpass) {
		try {
			String JDBC_DRIVER = "com.mysql.jdbc.Driver";
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			
			
			String dQuery = "INSERT INTO user (name, password) VALUES ('"+tname+"', '"+MD5Hashing(tpass)+"')";
			Statement st = conn.createStatement();
			
			st.execute(dQuery);
			conn.close();
			JOptionPane.showMessageDialog(null, "Insert Success", "Success", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean loginValidation(String tname, String tpass) {
		boolean isValid = false;
		try {
			String JDBC_DRIVER = "com.mysql.jdbc.Driver";
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			String dQuery = "SELECT name FROM user WHERE name = '"+tname+"' AND password = '"+tpass+"'";
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(dQuery);
			
			while(rs.next()) {
				isValid = true;
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isValid;
	}
	
	private String MD5Hashing(String hPass) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(hPass.getBytes());
			byte byteData[] = md.digest();
			
			StringBuffer hexString = new StringBuffer();
			for(int i=0; i<byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if(hex.length()==1) hexString.append('0');
				hexString.append(hex);
			}
			
			return hexString.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
