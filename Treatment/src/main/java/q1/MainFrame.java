package q1;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
/**
 * The main frame class which exstends JFrame and 
 * responsible for running the program.
 * 
 * @authors Michael Ilkanayev - 318216678 , Vladimir Davidzon -  317648632
 *
 */
public class MainFrame extends JFrame{

	WelcomePage mainPanel ;
	/**
	 * constructor of the main frame
	 * @param userID - the id of the user
	 */
	MainFrame(String userID){
		super("Medical system");//title of the window 
		setLayout(new BorderLayout()); //setting layout
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit on close
		setSize(1100,700); //setting size
		setLocationRelativeTo(null);//open the window in the center of the screen
		setResizable(false);
		
		String CurrentPath = System.getProperty("user.dir"); //getting Present Project Directory
		Image icon = Toolkit.getDefaultToolkit().getImage(CurrentPath+"\\src\\test\\resources\\MedicalImage.png");    
		setIconImage(icon);   
				
		mainPanel= new WelcomePage(userID); //activation of the main page
		
		add(mainPanel); //adding main panel to the frame
	}
	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {	
		LoginPage loginpage =new LoginPage();
	}
}
