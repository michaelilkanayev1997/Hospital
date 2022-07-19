package q1;
import java.util.Arrays;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class CreateAccount implements ActionListener{

	
	JFrame frame = new JFrame();
	JButton SignupButton = new JButton("Sign up");
	JButton ExitButton = new JButton("Exit");
	JTextField UsernameField = new JTextField();
	JPasswordField userPassword = new JPasswordField();
	JPasswordField verificationPassword = new JPasswordField();
	JTextField UserIdtxt = new JTextField();
	JSeparator separator = new JSeparator();
	JLabel UsernameLabel = new JLabel("Username:");
	JLabel userPasswordLabel = new JLabel("Password:");
	JLabel verificationPasswordLabel = new JLabel("Verification:");
	JLabel UserIdLabel = new JLabel("ID:");
	JLabel CreateAnAccountLabel = new JLabel("Create an account");
	
	CreateAccount(){
		CreateAnAccountLabel.setBounds(55,15,400,40);
		CreateAnAccountLabel.setFont(new Font("Serif",Font.BOLD,40));
		
		separator.setBounds(10,80, 385,55);
		
		UsernameLabel.setBounds(50, 150,75,25);
		userPasswordLabel.setBounds(50, 200,75,25);
		verificationPasswordLabel.setBounds(50, 250,75,25);
		UserIdLabel.setBounds(50, 100, 75, 25);
		
		UsernameField.setBounds(125, 150,200, 25);
		userPassword.setBounds(125, 200,200, 25);
		verificationPassword.setBounds(125,250,200, 25);
		UserIdtxt.setBounds(125,100, 200, 25);
		
		SignupButton.setBounds(165,290,120,30);
		SignupButton.setFocusable(false);
		SignupButton.addActionListener(this);
		SignupButton.setFont(new Font("Tahoma",Font.BOLD,20));

		ExitButton.setBounds(335,350,60,25);
		ExitButton.setFocusable(false);
		ExitButton.addActionListener(this);
		ExitButton.setFont(new Font("Tahoma",Font.BOLD,12));
		
		frame.add(UsernameLabel);
		frame.add(userPasswordLabel);
		frame.add(verificationPasswordLabel);
		frame.add(UsernameField);
		frame.add(UserIdLabel);
		frame.add(UserIdtxt);
		frame.add(userPassword);
		frame.add(verificationPassword);
		frame.add(SignupButton);
		frame.add(ExitButton);
		frame.add(separator);
		frame.add(CreateAnAccountLabel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);//open the window in the center of the screen
		frame.setVisible(true);
		frame.setTitle("Create Account");
}
	public int CountLetters(String str) {

		int letterCount = 0;
		char temp;

		for( int i = 0; i < str.length( ); i++ ){
		    temp = str.charAt( i );

		    if( Character.isLetter(temp))
		    	letterCount++;
		}
		System.out.println(letterCount);
		return letterCount;
	}
	
	public int CountNumsInString(String str) {

	    int count=0;
	    for(int i=0;i<str.length();i++){
	    	
	    	if(Character.isDigit(str.charAt(i)))
	  	      count++;	
	    }
	      return count;
	}
	
	public boolean CheckSpecialCharacters(String str) {
	     Pattern p = Pattern.compile("[^A-Za-z0-9]");
	     Matcher m = p.matcher(str);
	    // boolean b = m.matches();
	     boolean b = m.find();
	     if (b) {
	    	 System.out.println("There is a special character in my string ");
	     	 return true;
	     }
	     else {
		     System.out.println("There is no special char");
		     return false;
	     }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==SignupButton) {
			
			String Username = UsernameField.getText();
			String UserID = UserIdtxt.getText();
			String password1 = String.valueOf(userPassword.getPassword());
			String password2 = String.valueOf(verificationPassword.getPassword());
			
			if(!password1.equals(password2)) {
				UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null, "The passwords do not match!\nPlease try again.","ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				
			}
			else if(Username.length()<6 || Username.length() >8 || CountNumsInString(Username)>2 || CheckSpecialCharacters(Username)==true) {
				UIManager.put("OptionPane.minimumSize",new Dimension(385,50)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null, "Username - Should Contain between 6 and 8 characters.\nAt most 2 digits and the rest letters.","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
			}
			else if(password1.length()<8 || password1.length() >10 || CountLetters(password1)<1 || CheckSpecialCharacters(password1)==false ||CountNumsInString(password1)<1) {
				UIManager.put("OptionPane.minimumSize",new Dimension(390,50)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null, "Password -Should Contain between 8 and 10 characters.\nContain at least one letter,one number\nand one special character (!, #, $ Etc).","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
			}
			else if(UserID.length()!=9 || CountNumsInString(UserID)!=9) {
				UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null, "ID - Should Contain 9 digits.\n","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
			}
			else {
				
				
			} 			
		}
		else if(e.getSource()==ExitButton) {
			JFrame ExitFrame = new JFrame ("Exit");
			UIManager.put("OptionPane.minimumSize",new Dimension(300,50)); //setting the preferred size of JOptionPane
			UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
			if(JOptionPane.showConfirmDialog(ExitFrame, " You want to Exit  ? ","Exit",JOptionPane.YES_NO_OPTION)
					== JOptionPane.YES_NO_OPTION) {
				System.exit(0);
			}
			
		}
	}
}




