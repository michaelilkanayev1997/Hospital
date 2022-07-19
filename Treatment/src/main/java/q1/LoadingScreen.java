package q1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

/**
 * This is the LoadingScreen class who extends JFrame 
 * and Responsible for the Loading screen when the system calculates the Treatment.
 * 
 * @authors Michael Ilkanayev - 318216678 , Vladimir Davidzon -  317648632
 */
public class LoadingScreen extends JFrame {

	//*********** Initialization of the components ***********
	JPanel Panel = new JPanel();
	JLabel LoadingLabel = new JLabel("Please wait");
	JLabel LoadingPercentage = new JLabel("0 %");
	JLabel IconImage ;
	String[] uniqeDiagnosis ;
	ExcelFile excelfile;
	
	static String CurrentPath = System.getProperty("user.dir"); //getting Present Project Directory

	/**
	 * constructor
     * setting the components
     * 
	 * @param uniqeDiagnosis - uniqeDiagnosis array .
	 * @param excelfile - excelfile object.
	 */
	public LoadingScreen(String[] uniqeDiagnosis,ExcelFile excelfile) {
		super();
		setLayout(new BorderLayout());
		setUndecorated(true);
		setSize(600,500);
		setLocationRelativeTo(null);//open the window in the center of the screen
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
	    setVisible(true);
	    
	    this.uniqeDiagnosis = uniqeDiagnosis;
	    this.excelfile = excelfile;

		Panel.setLayout(null);
		
		add(Panel);
		
		LoadingLabel.setForeground(Color.BLACK);
		LoadingLabel.setFont(new Font("Segoe UI",Font.BOLD,40));
		LoadingLabel.setBounds(190,30,400,50);

		LoadingPercentage.setForeground(Color.BLACK);
		LoadingPercentage.setFont(new Font("Segoe UI",Font.BOLD,26));
		LoadingPercentage.setBounds(280,450,100,50);


		this.setContentPane(new JLabel(new ImageIcon(CurrentPath+"\\src\\test\\resources\\medicalLoading.gif")));
		

		this.getContentPane().add(LoadingPercentage);
		this.getContentPane().add(LoadingLabel);


		Thread t = new Thread(runnable);
        t.start();//starting the thread
		
	}
	/**
	 * Showing a JOptionPane with the diagnosis and recommendation
	 */
	public void ShowResult() {
		
		String out="* Patient Diagnosis : ";

		 for (int i = 0; i < uniqeDiagnosis.length; i++) {
			 
		        out+="\n"+"           "+uniqeDiagnosis[i] ;
		 }
		 out += "\n\n* Patient recommendation : ";
		 for (int i = 0; i < uniqeDiagnosis.length; i++) {
			 
               out +="\n"+"           "+excelfile.TreatmentsDictionary.get(uniqeDiagnosis[i]);
        }
		 UIManager.put("OptionPane.messageForeground", Color.black);//setting text Color to black
		 JOptionPane.showMessageDialog (null, out,"Patient Diagnosis and recommendation:", JOptionPane.INFORMATION_MESSAGE);
		}
		
	/**
	 * A function that responsible for the running of the loading screen.
	 */
	 Runnable runnable = new Runnable() {
	        @Override
	        public void run() {
	        	 try {
						for(int i =0;i<=100;i++) {
							Thread.sleep(40);
							LoadingPercentage.setText( i + "%" );				
						}	
	            }
	            catch (InterruptedException e) {
	              e.printStackTrace();
	            }
	        	         	 
	        	 setVisible(false);
	        	 ShowResult();
	        	 dispose();
	          }        
	    };	
}
