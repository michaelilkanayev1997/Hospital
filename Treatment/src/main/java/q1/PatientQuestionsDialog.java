package q1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * This is the Patient Questions Dialog class who extends JDialog and implements ActionListener 
 * and Responsible for the Patient Questions that allow the system to get to know the patient better.
 * 
 * @authors Michael Ilkanayev - 318216678 , Vladimir Davidzon -  317648632
 */
public class PatientQuestionsDialog extends JDialog implements ActionListener{

	Patient patient;
	private String[] Answer = {"Yes","No"}; 
	
	 // ComboBox Of the answer "No"/"Yes"
	JComboBox<String> CmbAnswer1;
	JComboBox<String> CmbAnswer2;
	JComboBox<String> CmbAnswer3;
	JComboBox<String> CmbAnswer4;
	JComboBox<String> CmbAnswer5;
	JComboBox<String> CmbAnswer6;
	JComboBox<String> CmbAnswer7;
	JComboBox<String> CmbAnswer8;
	JComboBox<String> CmbAnswer9;
	
	//************** JLabel's *******************
	JLabel lbTitle = new JLabel("Patient Questions :");
	JLabel lbFever = new JLabel("Do you currently have a fever ?");
	JLabel lbSmoking = new JLabel("Are you smoking ?");
	JLabel lbEasternTestimony = new JLabel("Do you come from the Eastern testimony ?");
	JLabel lbPregnant = new JLabel("Are you pregnant ?");
	JLabel lbDiarrhea_Vomiting = new JLabel("Do you have diarrhea/vomiting ?");
	JLabel lbBleeding = new JLabel("Are you bleeding ?");
	JLabel lbEthiopian = new JLabel("Are you Ethiopian ?");
	JLabel lbMedications = new JLabel("Are you taking regular medications ?");
	JLabel lbLungDisease = new JLabel("Do you have lung disease ?");
	JLabel IconImage ;
	
	 static String CurrentPath = System.getProperty("user.dir"); //getting Present Project Directory
	
	JButton submit,cancel;
	
	Image backgroundImage;
	
	/**
	 * constructor
	 * setting the components
	 * 
	 * @param patient - the object of the patient
	 */
	PatientQuestionsDialog(Patient patient) {  
		//settings PatientQuestionsDialog
		setTitle("Patient Questions");//Set title
		setSize(430,560); //setting size
		setLayout(null);
		setLocationRelativeTo(null);//Creates the window in the center of the panel
		setVisible(true);
		setResizable(false);
		 // Release all resources when JDialog is closed 
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
        getContentPane().setBackground( Color.LIGHT_GRAY); //setting the Color of the background
        
        // load the image to a imageIcon
		ImageIcon imageIcon = new ImageIcon(CurrentPath+"\\src\\test\\resources\\patient_questions.png"); 
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		IconImage = new JLabel(imageIcon);
		IconImage.setBounds(10,10,60,60);

		
		Image icon = Toolkit.getDefaultToolkit().getImage(CurrentPath+"\\src\\test\\resources\\health-care_icon.png");    
		setIconImage(icon);   
        
		this.patient = patient;

		//Initialize comboBox
		CmbAnswer1 = new JComboBox<String>(Answer);
		CmbAnswer1.setBounds(340,70,65,30);
		CmbAnswer1.setFont(new Font("Verdana",Font.BOLD,14));
		CmbAnswer1.setForeground(Color.BLACK);
		CmbAnswer2 = new JComboBox<String>(Answer);
		CmbAnswer2.setBounds(340,115,65,30);
		CmbAnswer2.setFont(new Font("Verdana",Font.BOLD,14));
		CmbAnswer2.setForeground(Color.BLACK);
		CmbAnswer3 = new JComboBox<String>(Answer);
		CmbAnswer3.setBounds(340,160,65,30);
		CmbAnswer3.setFont(new Font("Verdana",Font.BOLD,14));
		CmbAnswer3.setForeground(Color.BLACK);
		CmbAnswer4 = new JComboBox<String>(Answer);
		CmbAnswer4.setBounds(340,205,65,30);
		CmbAnswer4.setFont(new Font("Verdana",Font.BOLD,14));
		CmbAnswer4.setForeground(Color.BLACK);
		CmbAnswer5 = new JComboBox<String>(Answer);
		CmbAnswer5.setBounds(340,250,65,30);
		CmbAnswer5.setFont(new Font("Verdana",Font.BOLD,14));
		CmbAnswer5.setForeground(Color.BLACK);
		CmbAnswer6 = new JComboBox<String>(Answer);
		CmbAnswer6.setBounds(340,295,65,30);
		CmbAnswer6.setFont(new Font("Verdana",Font.BOLD,14));
		CmbAnswer6.setForeground(Color.BLACK);
		CmbAnswer7 = new JComboBox<String>(Answer);
		CmbAnswer7.setBounds(340,340,65,30);
		CmbAnswer7.setFont(new Font("Verdana",Font.BOLD,14));
		CmbAnswer7.setForeground(Color.BLACK);
		CmbAnswer8 = new JComboBox<String>(Answer);
		CmbAnswer8.setBounds(340,385,65,30);
		CmbAnswer8.setFont(new Font("Verdana",Font.BOLD,14));
		CmbAnswer8.setForeground(Color.BLACK);
		CmbAnswer9 = new JComboBox<String>(Answer);
		CmbAnswer9.setBounds(340,430,65,30);
		CmbAnswer9.setFont(new Font("Verdana",Font.BOLD,14));
		CmbAnswer9.setForeground(Color.BLACK);
		
		//Initialize ButtonFields
		submit=new JButton("Submit");
		submit.setFocusable(false);
		submit.setForeground(Color.BLACK);
		submit.setFont(new Font("Tahoma",Font.BOLD,14));
		submit.setBounds(120,480,85,33);
		
		cancel=new JButton("Cancel"); 	
		cancel.setFocusable(false);
		cancel.setForeground(Color.BLACK);
		cancel.setFont(new Font("Tahoma",Font.BOLD,14));
		cancel.setBounds(215,480,85,33);
		
		lbTitle.setBounds(85,20,300,40);
		lbTitle.setFont(new Font("Tahoma",Font.BOLD,25));
		lbTitle.setForeground(Color.BLACK);
		lbFever.setBounds(10,70,300,30);
		lbFever.setFont(new Font("Verdana",Font.BOLD,12));
		lbFever.setForeground(Color.BLACK);
		lbSmoking.setBounds(10,115,300,30);
		lbSmoking.setFont(new Font("Verdana",Font.BOLD,12));
		lbSmoking.setForeground(Color.BLACK);
		lbEasternTestimony.setBounds(10,160,300,30);
		lbEasternTestimony.setFont(new Font("Verdana",Font.BOLD,12));
		lbEasternTestimony.setForeground(Color.BLACK);
		lbPregnant.setBounds(10,205,300,30);
		lbPregnant.setFont(new Font("Verdana",Font.BOLD,12));
		lbPregnant.setForeground(Color.BLACK);
		lbDiarrhea_Vomiting.setBounds(10,250,300,30);
		lbDiarrhea_Vomiting.setFont(new Font("Verdana",Font.BOLD,12));
		lbDiarrhea_Vomiting.setForeground(Color.BLACK);
		lbBleeding.setBounds(10,295,300,30);
		lbBleeding.setFont(new Font("Verdana",Font.BOLD,12));
		lbBleeding.setForeground(Color.BLACK);
		lbEthiopian.setBounds(10,340,300,30);
		lbEthiopian.setFont(new Font("Verdana",Font.BOLD,12));
		lbEthiopian.setForeground(Color.BLACK);
		lbMedications.setBounds(10,385,300,30);
		lbMedications.setFont(new Font("Verdana",Font.BOLD,12));
		lbMedications.setForeground(Color.BLACK);
		lbLungDisease.setBounds(10,430,300,30);
		lbLungDisease.setFont(new Font("Verdana",Font.BOLD,12));
		lbLungDisease.setForeground(Color.BLACK);
		
		// adding all components to the JDialog
		add(CmbAnswer1);
		add(CmbAnswer2);
		add(CmbAnswer3);
		add(CmbAnswer4);
		add(CmbAnswer5);
		add(CmbAnswer6);
		add(CmbAnswer7);
		add(CmbAnswer8);
		add(CmbAnswer9);
		add(submit);
		add(cancel);
		add(lbTitle);
		add(lbFever);
		add(lbSmoking);
		add(lbEasternTestimony);
		add(lbPregnant);
		add(lbDiarrhea_Vomiting);
		add(lbBleeding);
		add(lbEthiopian);
		add(lbMedications);
		add(IconImage);
		add(lbLungDisease);
		
		// creating button events
		submit.addActionListener(this);
		cancel.addActionListener(this);
    }
    /**
     * A function that activated when a button is pressed.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==submit) {
			//--------- Saving the user input ---------//
			String Fever = (String) CmbAnswer1.getSelectedItem(); 
			String Smoking = (String) CmbAnswer2.getSelectedItem(); 
			String EasternTestimony = (String) CmbAnswer3.getSelectedItem(); 
			String Pregnant = (String) CmbAnswer4.getSelectedItem(); 
			String Diarrhea_Vomiting = (String) CmbAnswer5.getSelectedItem(); 
			String Bleeding = (String) CmbAnswer6.getSelectedItem(); 
			String Ethiopian = (String) CmbAnswer7.getSelectedItem(); 
			String Medications = (String) CmbAnswer8.getSelectedItem(); 
			String LungDisease = (String) CmbAnswer9.getSelectedItem(); 
				
			patient.Fever = Fever;
			patient.Smoking = Smoking;
			patient.EasternTestimony = EasternTestimony;
			patient.Pregnant = Pregnant;
			patient.Diarrhea_Vomiting = Diarrhea_Vomiting;
			patient.Bleeding = Bleeding;
			patient.Ethiopian = Ethiopian;
			patient.Medications = Medications;
			patient.LungDisease = LungDisease;
			
			UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 15));
			UIManager.put("OptionPane.minimumSize",new Dimension(350,60)); //setting the preferred size of JOptionPane
			JOptionPane.showMessageDialog(null,patient.Name +" Your answer has been added, thanks.");
				
			dispose();//Close the dialog window
		}
		else if (e.getSource() == cancel) {
			dispose(); //Close the dialog window
		}	
	}	
}
