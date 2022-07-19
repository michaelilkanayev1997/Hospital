package q1;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class LoginPage implements ActionListener{

	JFrame frame = new JFrame();
	JButton loginButton = new JButton("Log in");
	JButton resetButton = new JButton("Reset");
	JButton ExitButton = new JButton("Exit");
	JButton CreateNewAccountButton = new JButton("Create a new account");
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("Username:");
	JLabel userPasswordLabel = new JLabel("Password:");
	JLabel messageLabel = new JLabel();
	JLabel HelloLabel = new JLabel("Hello");
	JLabel SignInLabel = new JLabel("Sign in or create an account");

	JSeparator separator = new JSeparator();
	
	LoginPage() {
		
		separator.setBounds(10,80, 385,55);
		
		userIDLabel.setBounds(50, 100,75,25);
		userPasswordLabel.setBounds(50, 150,75,25);
		
		messageLabel.setBounds(115, 250, 250, 40);
		messageLabel.setFont(new Font("Tahoma",Font.BOLD,20));
		
		HelloLabel.setBounds(150,5, 150,35);
		HelloLabel.setFont(new Font("Serif",Font.BOLD,46));
		
		SignInLabel.setBounds(115,55,200,20);
		SignInLabel.setFont(new Font("Tahoma",Font.LAYOUT_LEFT_TO_RIGHT,15));
		
		userIDField.setBounds(125, 100,200, 25);
		userPasswordField.setBounds(125, 150,200, 25);
		
		loginButton.setBounds(70,200,137, 42);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		loginButton.setFont(new Font("Tahoma",Font.BOLD,30));
		
		ExitButton.setBounds(330,350,60,25);
		ExitButton.setFocusable(false);
		ExitButton.addActionListener(this);
		ExitButton.setFont(new Font("Tahoma",Font.BOLD,12));
		
		resetButton.setBounds(220,200,137, 42);
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		resetButton.setFont(new Font("Tahoma",Font.BOLD,30));
		
		CreateNewAccountButton.setBounds(125, 300,170, 30);
		CreateNewAccountButton.setFocusable(false);
		CreateNewAccountButton.addActionListener(this);
		CreateNewAccountButton.setFont(new Font("Tahoma",Font.BOLD,12));
		
		frame.add(userIDLabel);
		frame.add(userPasswordLabel);
		frame.add(messageLabel);
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(loginButton);
		frame.add(resetButton);
		frame.add(CreateNewAccountButton);
		frame.add(ExitButton);
		frame.add(separator);
		frame.add(HelloLabel);
		frame.add(SignInLabel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);//open the window in the center of the screen
		frame.setVisible(true);
		frame.setTitle("Login");
	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==resetButton) {
			userIDField.setText("");
			userPasswordField.setText("");
			
		}
		
		else if(e.getSource()==loginButton) {
			
			String userID = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			
			ExcelFile excelfile = new ExcelFile();
			

			if(userID.equals("michael")) {
				if("123".equals(password)) {
				
					messageLabel.setForeground(Color.green);
					messageLabel.setText("Login successful");
					frame.dispose();
					WelcomePage welcomePage = new WelcomePage(userID);
				}
				else {
					messageLabel.setForeground(Color.red);
					messageLabel.setText("Wrong password");
				}
			}
			else {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("Username not found");
			}
		}
		else if(e.getSource()==CreateNewAccountButton) {
			frame.dispose();
			CreateAccount createAccount = new CreateAccount();
			
		}
		else if(e.getSource()==ExitButton) {
			JFrame ExitFrame = new JFrame ("Exit");
			UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
			if(JOptionPane.showConfirmDialog(ExitFrame, " You want to Exit  ? ","Exit",JOptionPane.YES_NO_OPTION)
					== JOptionPane.YES_NO_OPTION) {
				System.exit(0);
			}
			
		}
	
		
	}
}
