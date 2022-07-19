package q1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
/**
 * This is the Enter Blood Test Dialog class who extends JDialog and implements ActionListener 
 * and Responsible for manual entry of the patient's blood test.
 * 
 * @authors Michael Ilkanayev - 318216678 , Vladimir Davidzon -  317648632
 */
public class EnterBloodTestDialog extends JDialog implements ActionListener{

	//*********** Initialization of the components ***********
	JLabel lbWBC = new JLabel("WBC :");
	JLabel lbNeut = new JLabel("Neut :");
	JLabel lbLymph = new JLabel("Lymph :");
	JLabel lbRBC = new JLabel("RBC :");
	JLabel lbHCT = new JLabel("HCT :");
	JLabel lbUrea = new JLabel("Urea :");
	JLabel lbHb = new JLabel("Hb :");
	JLabel lbCrtn = new JLabel("Crtn :");
	JLabel lbIron = new JLabel("Iron :");
	JLabel lbHDL = new JLabel("HDL :");
	JLabel lbAP = new JLabel("AP :");
	JLabel IconImage ;
	JLabel lbTitle = new JLabel("Blood Test Values :");
	JLabel lbPatientName = new JLabel("Patient Name:");
	JLabel lbPatientId = new JLabel("Patient Id:");
	
	//***************  Text Field's *************** 
	static JTextField WBCTxt = new JTextField();
	static JTextField NeutTxt = new JTextField();
	static JTextField LymphTxt = new JTextField();
	static JTextField RBCTxt = new JTextField();
	static JTextField HCTTxt = new JTextField();
	static JTextField UreaTxt = new JTextField();
	static JTextField HbTxt = new JTextField();
	static JTextField CrtnTxt = new JTextField();
	static JTextField IronTxt = new JTextField();
	static JTextField HDLTxt = new JTextField();
	static JTextField APTxt = new JTextField();
	
	static String CurrentPath = System.getProperty("user.dir"); //getting Present Project Directory
	
	//***************  Button's  *************** 
	JButton submit = new JButton();
	JButton cancel = new JButton();
	
	Patient patient;
	
	JSeparator separator = new JSeparator();

	/**
	 * constructor
	 * setting the components
	 * 
	 * @param patient - the object of the patient
	 */
	EnterBloodTestDialog(Patient patient){
		
		//settings PatientQuestionsDialog
		setTitle("Blood Test Values");//Set title
		setSize(430,680); //setting size
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

		this.patient=patient;
		
		separator.setBounds(10,70,392,55);
		
		
		Image icon = Toolkit.getDefaultToolkit().getImage(CurrentPath+"\\src\\test\\resources\\health-care_icon.png");    
		setIconImage(icon);
		
		//setting Labels
		lbTitle.setBounds(67,20,300,45);
		lbTitle.setFont(new Font("Tahoma",Font.BOLD,30));
		lbTitle.setForeground(Color.BLACK);
		lbPatientName.setBounds(80,70,400,25);
		lbPatientName.setFont(new Font("Serif",Font.BOLD,19));
		lbPatientName.setForeground(Color.BLACK);
		lbPatientName.setText("Patient Name : " + patient.Name);
		lbPatientId.setBounds(80,90,400,45);
		lbPatientId.setFont(new Font("Serif",Font.BOLD,19));
		lbPatientId.setForeground(Color.BLACK);
		lbPatientId.setText("Patient Id : " + patient.Id);
		lbWBC.setBounds(40,140,100,30);
		lbWBC.setFont(new Font("Verdana",Font.BOLD,14));
		lbWBC.setForeground(Color.BLACK);
		lbNeut.setBounds(40,180,100,30);
		lbNeut.setFont(new Font("Verdana",Font.BOLD,14));
		lbNeut.setForeground(Color.BLACK);
		lbLymph.setBounds(40,220,100,30);
		lbLymph.setFont(new Font("Verdana",Font.BOLD,14));
		lbLymph.setForeground(Color.BLACK);
		lbRBC.setBounds(40,260,100,30);
		lbRBC.setFont(new Font("Verdana",Font.BOLD,14));
		lbRBC.setForeground(Color.BLACK);
		lbHCT.setBounds(40,300,100,30);
		lbHCT.setFont(new Font("Verdana",Font.BOLD,14));
		lbHCT.setForeground(Color.BLACK);
		lbUrea.setBounds(40,340,100,30);
		lbUrea.setFont(new Font("Verdana",Font.BOLD,14));
		lbUrea.setForeground(Color.BLACK);
		lbHb.setBounds(40,380,100,30);
		lbHb.setFont(new Font("Verdana",Font.BOLD,14));
		lbHb.setForeground(Color.BLACK);
		lbCrtn.setBounds(40,420,100,30);
		lbCrtn.setFont(new Font("Verdana",Font.BOLD,14));
		lbCrtn.setForeground(Color.BLACK);
		lbIron.setBounds(40,460,100,30);
		lbIron.setFont(new Font("Verdana",Font.BOLD,14));
		lbIron.setForeground(Color.BLACK);
		lbHDL.setBounds(40,500,100,30);
		lbHDL.setFont(new Font("Verdana",Font.BOLD,14));
		lbHDL.setForeground(Color.BLACK);
		lbAP.setBounds(40,540,100,30);
		lbAP.setFont(new Font("Verdana",Font.BOLD,14));
		lbAP.setForeground(Color.BLACK);
		
		WBCTxt.setBounds(140,140,160,25);
		WBCTxt.setFont(new Font("Ariel",Font.BOLD, 12));
		NeutTxt.setBounds(140,180,160,25);
		NeutTxt.setFont(new Font("Ariel",Font.BOLD, 12));
		LymphTxt.setBounds(140,220,160,25);
		LymphTxt.setFont(new Font("Ariel",Font.BOLD, 12));
		RBCTxt.setBounds(140,260,160,25);
		RBCTxt.setFont(new Font("Ariel",Font.BOLD, 12));
		HCTTxt.setBounds(140,300,160,25);
		HCTTxt.setFont(new Font("Ariel",Font.BOLD, 12));
		UreaTxt.setBounds(140,340,160,25);
		UreaTxt.setFont(new Font("Ariel",Font.BOLD, 12));
		HbTxt.setBounds(140,380,160,25);
		HbTxt.setFont(new Font("Ariel",Font.BOLD, 12));
		CrtnTxt.setBounds(140,420,160,25);
		CrtnTxt.setFont(new Font("Ariel",Font.BOLD, 12));
		IronTxt.setBounds(140,460,160,25);
		IronTxt.setFont(new Font("Ariel",Font.BOLD, 12));
		HDLTxt.setBounds(140,500,160,25);
		HDLTxt.setFont(new Font("Ariel",Font.BOLD, 12));
		APTxt.setBounds(140,540,160,25);
		APTxt.setFont(new Font("Ariel",Font.BOLD, 12));
		
		//Initialize ButtonFields
		submit=new JButton("Submit");
		submit.setFocusable(false);
		submit.setForeground(Color.BLACK);
		submit.setFont(new Font("Tahoma",Font.BOLD,14));
		submit.setBounds(120,600,85,33);
		
		cancel=new JButton("Cancel"); 	
		cancel.setFocusable(false);
		cancel.setForeground(Color.BLACK);
		cancel.setFont(new Font("Tahoma",Font.BOLD,14));
		cancel.setBounds(215,600,85,33);
		
		// adding all components to the JDialog
		add(WBCTxt);
		add(NeutTxt);
		add(LymphTxt);
		add(RBCTxt);
		add(HCTTxt);
		add(UreaTxt);
		add(HbTxt);
		add(CrtnTxt);
		add(IronTxt);
		add(HDLTxt);
		add(APTxt);
		add(separator);
		add(lbTitle);
		add(lbPatientName);
		add(lbPatientId);
		add(lbWBC);
		add(lbNeut);
		add(lbLymph);
		add(lbRBC);
		add(lbHCT);
		add(lbUrea);
		add(lbHb);
		add(lbCrtn);
		add(lbIron);
		add(lbHDL);
		add(lbAP);
		add(submit);
		add(cancel);
		
		// creating button events
		submit.addActionListener(this);
		cancel.addActionListener(this);
	}
	/**
	 * A function that checks if a string is Numeric
	 * @param str - the string we want to check.
	 * @return true if string is numeric/flase if not.
	 */
    public static boolean isNumericArray(String str) {
        if (str == null)
            return false;
        for (char c : str.toCharArray())
            if (c < '0' || c > '9')
                return false;
        return true;
    }

    /**
     * A function that activated when a button is pressed.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==submit) {

			//--------- Saving the user input ---------//
			String PatienWBC = WBCTxt.getText();
			String PatienNeut = NeutTxt.getText();
			String PatientLymph = LymphTxt.getText();
			String PatientRBC = RBCTxt.getText();
			String PatientHCT = HCTTxt.getText();
			String PatientUrea = UreaTxt.getText();
			String PatientHb = HbTxt.getText();
			String PatientCrtn = CrtnTxt.getText();
			String PatientIron = IronTxt.getText();
			String PatientHDL = HDLTxt.getText();
			String PatientAP = APTxt.getText();
			
			try {
				   
				 Float.parseFloat(PatienWBC);
				 Float.parseFloat(PatienNeut);
				 Float.parseFloat(PatientLymph);
				 Float.parseFloat(PatientRBC);
				 Float.parseFloat(PatientHCT);
				 Float.parseFloat(PatientUrea);
				 Float.parseFloat(PatientHb);
				 Float.parseFloat(PatientCrtn);
				 Float.parseFloat(PatientIron);
				 Float.parseFloat(PatientHDL);
				 Float.parseFloat(PatientAP);
				
			  //Setting values of the bloodTest to patient:
				patient.WBC = PatienWBC;
				patient.Neut = PatienNeut;
				patient.Lymph = PatientLymph;
				patient.RBC = PatientRBC;
				patient.HCT = PatientHCT;
				patient.Urea = PatientUrea;
				patient.Hb = PatientHb;
				patient.Crtn = PatientCrtn;
				patient.Iron = PatientIron;
				patient.HDL = PatientHDL;
				patient.AP = PatientAP;
			    
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 15));
				UIManager.put("OptionPane.minimumSize",new Dimension(350,60)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null,patient.Name +" Blood Test has been added, thanks.");
					
				dispose();//Close the dialog window
			}
		    catch (NumberFormatException ex) {
		    	UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
				UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null,"values must be numeric!","ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);    
	        } 	
		}
		else if (e.getSource() == cancel) { //close the window
			dispose(); //Close the dialog window
		}
	}	
}
