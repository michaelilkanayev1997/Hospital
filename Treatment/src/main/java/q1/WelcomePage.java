package q1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * This is the main panel class which exstends JPanel and implements ActionListener.
 * 
 * @authors Michael Ilkanayev - 318216678 , Vladimir Davidzon -  317648632
 */
public class WelcomePage extends JPanel implements ActionListener{
	
	JPanel panel1 = new JPanel();
	JFrame frame = new JFrame();
	protected static BufferedImage image;// Image for background image
	protected static BufferedImage iconPatient;// Patient Icon 
	protected static BufferedImage medicalSnake;//medicalSnake Icon 

	//******************* JLabel's ************************
	JLabel PatientIdLabel = new JLabel("Patient ID:", SwingConstants.CENTER);
	JLabel PatientNameLabel = new JLabel("Patient Name:", SwingConstants.CENTER);
	JLabel PatientAgeLabel = new JLabel("Age:", SwingConstants.CENTER);
	JLabel GenderLabel = new JLabel("Gender:", SwingConstants.CENTER);
	JLabel BloodGroupLabel= new JLabel("Blood Group:", SwingConstants.CENTER);
	JLabel DiseaseLabel = new JLabel("Disease:", SwingConstants.CENTER);
	JLabel AppointmentDateLabel = new JLabel("Appointment Date:");
	JLabel WeightLabel = new JLabel("Weight (Kg):", SwingConstants.CENTER);
	JLabel HeightLabel = new JLabel("Height (M):", SwingConstants.CENTER);
	JLabel PatientDetailsLabel = new JLabel("Patient's Information:", SwingConstants.CENTER);
	JLabel CurrentPatientLabel = new JLabel("Current Patient : ");
    JLabel CurrentDateLabel = new JLabel(getCurrentDateString(), SwingConstants.CENTER);
    JLabel CurrentTimeLabel = new JLabel(getCurrentTimeString(), SwingConstants.CENTER);
	
	//******************* Text Field's ************************
	static JTextField PatientIdTxt = new JTextField();
	static JTextField PatientNameTxt = new JTextField();
	static JTextField PatientAgeTxt = new JTextField();
	static JTextField WeightTxt = new JTextField();
	static JTextField HeightTxt = new JTextField();
	static JTextArea DiseaseTextArea = new JTextArea();
	
	//******************* Combo Box ************************
	private String[] BloodGroup = {"A","B","AB","O"};
	JComboBox<String> CmbBloodGroup = new JComboBox<String>(BloodGroup); // BloodGroup ComboBox 
	
	//******************* Radio Button's ************************
	JRadioButton MaleButton = new JRadioButton("Male");
	JRadioButton FemaleButton = new JRadioButton("Female");
	static ButtonGroup bg=new ButtonGroup();    
	
	//******************* Button's ************************
	JButton AddPatient = new JButton("Add");
	JButton ExitButton = new JButton("Exit");
	JButton ImportBloodTest = new JButton("Import Blood Test");
	JButton PatientQuestions = new JButton("Patient Questions");
	JButton EnterBloodTest = new JButton("Enter a blood test");
	JButton calculateTreatment = new JButton("Calculate Treatment");
	JButton SavingTreatment = new JButton("Save Treatment");
	JButton NewpatientButton = new JButton("New patient");
	JButton HelpButton = new JButton("Help");
	
	Patient patient;
	
	ExcelFile excelfile = new ExcelFile();
	
	String CurrentPath = System.getProperty("user.dir"); //getting Present Project Directory
	
	JDialog diagnosisDialog = new JDialog(); //The diagnosis Dialog
	
	String[] uniqeDiagnosis;
	
	boolean CalculateTreatmentFlag = false;
	boolean PatientQuestionsAnswerd = false;
	
	/**
	 * constructor of the main panel
	 * @param userID - the id of the user
	 */
	WelcomePage(String userID){
		//setting main Panel
		super();
		setLayout(null);
		 
		MaleButton.setMnemonic(KeyEvent.VK_C);
		MaleButton.setActionCommand("Male");
		MaleButton.setSelected(false);
		MaleButton.setBounds(140,190,80,25);	
		MaleButton.setFont(new Font("Verdana",Font.BOLD,12));
		MaleButton.setFocusable(false);
		
		FemaleButton.setSelected(false);
		FemaleButton.setActionCommand("Female");
		FemaleButton.setBounds(220,190,80,25);
		FemaleButton.setMnemonic(KeyEvent.VK_C);
		FemaleButton.setFont(new Font("Verdana",Font.BOLD,12));
		FemaleButton.setFocusable(false);
		
		PatientDetailsLabel.setBounds(155,7,180,25);
		PatientDetailsLabel.setFont(new Font("Tahoma",Font.BOLD,15));
		PatientDetailsLabel.setForeground(Color.BLACK);
		PatientIdLabel.setBounds(40,40,100,30);
		PatientIdLabel.setFont(new Font("Verdana",Font.BOLD,12));
		PatientIdLabel.setForeground(Color.BLACK);
		PatientIdTxt.setBounds(140,40,160,25);
		PatientIdTxt.setFont(new Font("Ariel",Font.BOLD, 12));
		PatientNameLabel.setBounds(40,90,100,30);
		PatientNameLabel.setFont(new Font("Verdana",Font.BOLD, 12));
		PatientNameLabel.setForeground(Color.BLACK);
		PatientNameTxt.setBounds(140,90,160,25);
		PatientNameTxt.setFont(new Font("Ariel",Font.BOLD, 12));
		PatientAgeLabel.setBounds(40,140,100,30);
		PatientAgeLabel.setFont(new Font("Verdana",Font.BOLD, 12));
		PatientAgeLabel.setForeground(Color.BLACK);
		PatientAgeTxt.setBounds(140,140,160,25);
		PatientAgeTxt.setFont(new Font("Ariel",Font.BOLD, 12));
		GenderLabel.setBounds(40,190,100,30);
		GenderLabel.setFont(new Font("Verdana",Font.BOLD, 12));
		GenderLabel.setForeground(Color.BLACK);
		BloodGroupLabel.setBounds(40,240,100,30);
		BloodGroupLabel.setFont(new Font("Verdana",Font.BOLD, 12));
		BloodGroupLabel.setForeground(Color.BLACK);
		WeightLabel.setBounds(40,290,100,30);
		WeightLabel.setFont(new Font("Verdana",Font.BOLD, 12));
		WeightLabel.setForeground(Color.BLACK);
		WeightTxt.setBounds(140,290,160,25);
		WeightTxt.setFont(new Font("Ariel",Font.BOLD, 12));
		HeightLabel.setBounds(40,340,100,30);
		HeightLabel.setFont(new Font("Verdana",Font.BOLD, 12));
		HeightLabel.setForeground(Color.BLACK);
		HeightTxt.setBounds(140,340,160,25);
		HeightTxt.setFont(new Font("Ariel",Font.BOLD, 12));
		CurrentPatientLabel.setBounds(30,515,900,40);
		CurrentPatientLabel.setFont(new Font("Arial",Font.BOLD,28));
		CurrentPatientLabel.setForeground(Color.BLACK);
		
		CmbBloodGroup.setBounds(140,240,160,25);
		
		AddPatient.setBounds(405,445,63,35);
		AddPatient.setFont(new Font("Serif",Font.BOLD, 13));
		AddPatient.setForeground(Color.BLACK);
		AddPatient.setBackground(Color.lightGray);
		AddPatient.setFocusable(false);
		AddPatient.addActionListener(this);
		
		ExitButton.setBounds(1000,621,70,30);
		ExitButton.setForeground(Color.BLACK);
		ExitButton.setBackground(Color.lightGray);
		ExitButton.setFocusable(false);
		ExitButton.addActionListener(this);
		
		HelpButton.setBounds(900,621,70,30);
		HelpButton.setForeground(Color.BLACK);
		HelpButton.setBackground(Color.lightGray);
		HelpButton.setFocusable(false);
		HelpButton.addActionListener(this);
		
		EnterBloodTest.setFont(new Font("Tahoma",Font.BOLD, 13));
		EnterBloodTest.setBounds(508,39,185,35);
		EnterBloodTest.setForeground(Color.BLACK);
		EnterBloodTest.setBackground(Color.lightGray);
		EnterBloodTest.setFocusable(false);
		EnterBloodTest.addActionListener(this);
		
		ImportBloodTest.setFont(new Font("Tahoma",Font.BOLD, 13));
		ImportBloodTest.setBounds(508,80,185,35);
		ImportBloodTest.setForeground(Color.BLACK);
		ImportBloodTest.setBackground(Color.lightGray);
		ImportBloodTest.setFocusable(false);
		ImportBloodTest.addActionListener(this);
		
		PatientQuestions.setFont(new Font("Tahoma",Font.BOLD, 13));
		PatientQuestions.setBounds(508,120,185,35);
		PatientQuestions.setForeground(Color.BLACK);
		PatientQuestions.setBackground(Color.lightGray);
		PatientQuestions.setFocusable(false);
		PatientQuestions.addActionListener(this);
		
		calculateTreatment.setFont(new Font("Tahoma",Font.BOLD, 13));
		calculateTreatment.setBounds(508,162,185,35);
		calculateTreatment.setForeground(Color.BLACK);
		calculateTreatment.setBackground(Color.lightGray);
		calculateTreatment.setFocusable(false);
		calculateTreatment.addActionListener(this);
		
		SavingTreatment.setFont(new Font("Tahoma",Font.BOLD, 13));
		SavingTreatment.setBounds(508,204,185,35);
		SavingTreatment.setForeground(Color.BLACK);
		SavingTreatment.setBackground(Color.lightGray);
		SavingTreatment.setFocusable(false);
		SavingTreatment.addActionListener(this);
		
		NewpatientButton.setFont(new Font("Tahoma",Font.BOLD, 13));
		NewpatientButton.setBounds(508,246,185,35);
		NewpatientButton.setForeground(Color.BLACK);
		NewpatientButton.setBackground(Color.lightGray);
		NewpatientButton.setFocusable(false);
		NewpatientButton.addActionListener(this);
		
		DiseaseLabel.setBounds(40,385,100,30);
		DiseaseLabel.setFont(new Font("Verdana",Font.BOLD, 12));
		DiseaseLabel.setForeground(Color.BLACK);
	
		DiseaseTextArea.setLineWrap(true); //Does not let go beyond the limits of the text area.
        // Sets JTextArea font and color.
        DiseaseTextArea.setFont(new Font("Ariel",Font.BOLD, 12));
        DiseaseTextArea.setForeground(Color.black);
        JScrollPane scrollPane = new JScrollPane(DiseaseTextArea);
        scrollPane.setBounds(140,385,250,105);
        
        //Creating the Date Label
        CurrentDateLabel.setBounds(800,65,195,45);
        CurrentDateLabel.setFont(new Font("Tahoma",Font.BOLD,29));
        CurrentDateLabel.setForeground(Color.BLACK);
        CurrentDateLabel.setBackground(Color.ORANGE);
        CurrentDateLabel.setOpaque(true);
        
        //Creating the clock Label
        CurrentTimeLabel.setBounds(820,120,160,45);
        CurrentTimeLabel.setFont(new Font("Tahoma",Font.BOLD,30));
        CurrentTimeLabel.setForeground(Color.BLACK);
        CurrentTimeLabel.setBackground(Color.ORANGE);
        CurrentTimeLabel.setOpaque(true);
        
        Thread t = new Thread(runnable);
        t.start();  // Activating of the thread

		JLabel welcomeLabel = new JLabel("Hello  "+ userID,SwingConstants.CENTER);
		welcomeLabel.setBounds(750,10,285,45);
		welcomeLabel.setFont(new Font("Serif",Font.BOLD,36));
		welcomeLabel.setForeground(Color.BLACK);
		welcomeLabel.setBackground(Color.ORANGE);
		welcomeLabel.setOpaque(true);
		
		
		//Adding all components:
		bg.add(MaleButton);bg.add(FemaleButton); 
		add(NewpatientButton);
		add(CurrentDateLabel);
		add(CurrentTimeLabel);
		add(welcomeLabel);
		add(MaleButton);
		add(FemaleButton);
		add(PatientIdLabel);
		add(PatientIdTxt);
		add(PatientNameLabel);
		add(PatientNameTxt);
		add(PatientAgeLabel);
		add(PatientAgeTxt);
		add(GenderLabel);
		add(BloodGroupLabel);
		add(CmbBloodGroup);
		add(PatientDetailsLabel);
		add(DiseaseLabel);
		add(scrollPane);
		add(WeightLabel);
		add(WeightTxt);
		add(HeightLabel);
		add(HeightTxt);
		add(AddPatient);
		add(ExitButton);
		add(ImportBloodTest);
		add(calculateTreatment);
		add(PatientQuestions);
		add(CurrentPatientLabel);
		add(EnterBloodTest);
		add(SavingTreatment);
		add(HelpButton);
		
		try{    
	    	image = ImageIO.read(new File(CurrentPath +"\\src\\test\\resources\\medical.jpg"));//main panel image
	    	iconPatient = ImageIO.read(new File(CurrentPath +"\\src\\test\\resources\\registration.png"));//registration image
	    	medicalSnake =  ImageIO.read(new File(CurrentPath +"\\src\\test\\resources\\MedicalSnake.png"));//medicalSnake image
	    }
	    catch (IOException e1){
	    	UIManager.put("OptionPane.minimumSize",new Dimension(400,50)); //setting the preferred size of JOptionPane
	    	JOptionPane.showMessageDialog(null,"There is a problem with finding the background image"
	    			,"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
	    }
	}
	/**
	 * A function that paints the background of the main panel
	 */
	public void paintComponent(Graphics g){	
	 	super.paintComponent(g);
	 	if(this.image != null) {
            Graphics2D g1 = (Graphics2D) g;
            g1.drawImage(this.image,0,0,getWidth(),getHeight(),this);
            repaint();
	 	}
	 	
	 	g.drawRect(30,30,450,465);//left rectangle
	 	g.drawRect(500,30,200,465);//right rectangle
	 	
        g.drawImage(iconPatient,360,60,90,90, this); //setting the icon of the registration
        g.drawImage(medicalSnake,525,335,150,150, this); //setting the icon of the medicalSnake
	}
	/**
	 * A function that getting the currnet Date in format :"dd/MM/yyyy".
	 * @return DateString - a String with the current date.
	 */
    private String getCurrentDateString() { 
        LocalDateTime ldt = LocalDateTime.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        String DateString = ldt.format(formatter);
        return DateString; 
    } 
	/**
	 * A function that getting the currnet Time in format :"HH:mm:ss".
	 * @return TimeString - a String with the current Time.
	 */
    private String getCurrentTimeString() { 
        LocalDateTime ldt = LocalDateTime.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); 
        String TimeString = ldt.format(formatter);
        return TimeString; 
    } 
    /**
     * A boolean function that checks if the string is numeric.
     * @param str - The string we want to check.
     * @return false if string is not numeric /true if string is numeric.
     */
    public static boolean isNumericArray(String str) {
    	
        if (str == null)
            return false;
        char[] c = str.toCharArray();
        
        for (int i=0;i< c.length;i++ ) {
            if (c[i] < '0')
                return false;
            if (c[i] > '9')
                return false;
        }
        return true;
    }
    /**
     * A void function that resets all fields in the main panel.
     */
    public static void ResetFields() {	
        if (bg.getSelection() != null) {
        	bg.clearSelection();
        }
        PatientIdTxt.setText("");
    	PatientNameTxt.setText("");
    	PatientAgeTxt.setText("");
    	WeightTxt.setText("");
    	HeightTxt.setText("");
    	DiseaseTextArea.setText("");
    }
    
	/**
	 * A function that counts the number of numbers in a string
	 * 
	 * @param str - the string we want to check.
	 * @return count - the numbers count.
	 */
	public int CountNumsInString(String str) {

	    int count=0;
	    for(int i=0;i<str.length();i++){
	    	
	    	if(Character.isDigit(str.charAt(i)))
	  	      count++;	
	    }
	      return count;
	}
	
	/**
	 * A boolean function that checks if a string Contains special letters
	 * 
	 * @param str- the string we want to check.
	 * @return true if str contains special letters/false if not.
	 */
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
    
    /**
     * A function that activated when a button is pressed.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == AddPatient) {

			//--------- Saving the user input ---------//
			String Gender;
			String PatienID = PatientIdTxt.getText();
			String PatienName = PatientNameTxt.getText();
			String PatientAge = PatientAgeTxt.getText();
			String BloodGroup = (String) CmbBloodGroup.getSelectedItem(); 
			String Weight = WeightTxt.getText();
			String Height = HeightTxt.getText();
			String Disease = DiseaseTextArea.getText();
			
			
			// checking if the terms are fine
			try {
				    if(PatienID.equals(""))
				    	throw new Exception("\" Patient ID\" Is a required field!");
				    else if(PatienName.equals(""))
				    	throw new Exception("\" Patient Name\" Is a required field!");
				    else if(PatientAge.equals(""))
				    	throw new Exception("\" Age\" Is a required field!");
				    else if(Integer.parseInt(PatientAge) <0)
				    	throw new Exception("\" Age\" can't be lower that 0 !");
				    else if(bg.getSelection()!=null)
				    	Gender = bg.getSelection().getActionCommand();
					else{
						throw new Exception("\" Gender\" Is a required field!");
					}
				    
				    if (!isNumericArray(PatienID)) {
				    	throw new Exception("\"Patient ID\" must be numeric!");
				    }
				    else if (PatienID.length() != 9 ) {
				    	throw new Exception("\"Patient ID\" must be 9 digits!");
				    }
				    else if(CheckSpecialCharacters(PatienName) || CountNumsInString(PatienName)!=0) {
				    	throw new Exception("\" Patient Name\" Must be letters a - z!");
				    }
				    else if (!isNumericArray(PatientAge)) {
				    	throw new Exception("\" Age\" must be numeric!");
				    }
				    else if (Integer.parseInt(PatientAge) >130) {
				    	throw new Exception("\" Age\" must be between 0 and 130!");
				    }
				   
				    if(!Weight.equals("")) {
					    if (Integer.parseInt(Weight) <0 || Integer.parseInt(Weight) >500 ) {
					    	throw new Exception("\" Weight\" must be between 0 and 500 kg! ");
					    }
				    }
				    else if(!Height.equals("")) {
					    if (Integer.parseInt(Height) <0 || Integer.parseInt(Height) >3 ) {
					    	throw new Exception("\" Height\" must be between 0 and 3 M! ");
					    }
				    }

				    //Creating the object of the patient
					patient= new Patient(PatienID,PatienName,PatientAge,Gender,BloodGroup,Disease,getCurrentDateString(),Weight,Height);
					
					ImageIcon icon = new ImageIcon(CurrentPath+"\\src\\test\\resources\\patient.png");
					Image img = icon.getImage();
					Image newimg = img.getScaledInstance(90,90, java.awt.Image.SCALE_SMOOTH);//resizing the image
					icon = new ImageIcon(newimg);
					
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
					UIManager.put("OptionPane.minimumSize",new Dimension(390,100)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null, "The patient has been added \nsuccessfully to the system.","Message", JOptionPane.INFORMATION_MESSAGE,icon);   
					ResetFields();
					
					//setting the name of the current patient
					CurrentPatientLabel.setText("Current Patient : " +PatienName +" - " + PatienID); 
			}
			catch(Exception e1){
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
				UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null, e1.getMessage(),"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
			}
				
		}
		if(e.getSource() == ImportBloodTest) {

			boolean flag = false;
			if(patient!=null) {
				try {
					flag = excelfile.ReadExcelBloodTest(patient); //Activation of ReadExcelBloodTest with current patient
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				if(flag) {
					JFrame messageFrame = new JFrame ("Message");
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
					UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(messageFrame,"The blood test of "+patient.Name +" \nhas been added successfully");
				}
			}	
			else {
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
				UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null,"A patient should be added first.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (e.getSource()==PatientQuestions) {
			if(patient!=null) {
				// open a dialog to fill the Patient Questions
				PatientQuestionsDialog dialog=new PatientQuestionsDialog(patient); 
				PatientQuestionsAnswerd = true; //Setting the Patient Questions Answerd to true.
			}
			else {
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
				UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null,"A patient should be added first.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (e.getSource()==calculateTreatment) {
			
			if(patient!=null) {
				if(PatientQuestionsAnswerd) {
				
				//--------- Saving the user input ---------//
				int Age_Int = Integer.parseInt(patient.Age);
				float WBC_float = Float.parseFloat(patient.WBC);
				float Neut_float = Float.parseFloat(patient.Neut);
				float Lymph_float = Float.parseFloat(patient.Lymph);
				float RBC_float = Float.parseFloat(patient.RBC);
				float HCT_float = Float.parseFloat(patient.HCT);
				float Urea_float = Float.parseFloat(patient.Urea);
				float Hb_float = Float.parseFloat(patient.Hb);
				float Crtn_float = Float.parseFloat(patient.Crtn);
				float Iron_float = Float.parseFloat(patient.Iron);
				float HDL_float = Float.parseFloat(patient.HDL);
				float AP_float = Float.parseFloat(patient.AP);
				
				List<String> diagnosis = new ArrayList<String>();
				List<String> Rare_cases = new ArrayList<String>();
				
				//Examination of the data from the blood test:
	
				
				//checking white Blood Cells ( WBC ):
				
				if(Age_Int >= 18) {
					
					if(WBC_float > 11000  && patient.Fever.equals("Yes")) {//if Patient has Fever
						diagnosis.add("Infection");
					}
					else if(WBC_float > 11000){
						Rare_cases.add("Blood disease");
						Rare_cases.add("Cancer");
					}
					else if (WBC_float <4500 ) {
						diagnosis.add("Viral disease");
						Rare_cases.add("Cancer");
					}
				}
				if(Age_Int >= 4 && Age_Int <=17) {
					
					if(WBC_float > 15500  && patient.Fever.equals("Yes")) {//if Patient has Fever
						diagnosis.add("Infection");
					}
					else if(WBC_float > 15500){
						Rare_cases.add("Blood disease");
						Rare_cases.add("Cancer");
					}
					else if (WBC_float <5500 ) {
						diagnosis.add("Viral disease");
						Rare_cases.add("Cancer");
					}
				}
	
				if(Age_Int >= 0 && Age_Int <=3) {
					
					if(WBC_float > 17500  && patient.Fever.equals("Yes")) {// if Patient is has Fever
						diagnosis.add("Infection");
					}
					else if(WBC_float > 17500){
						Rare_cases.add("Blood disease");
						Rare_cases.add("Cancer");
					}
					else if (WBC_float <6000 ) {
						diagnosis.add("Viral disease");
						Rare_cases.add("Cancer");
					}
				}
				
				//checking (Neut) Neutrophil:
				
				if(Neut_float >54) {
					diagnosis.add("Infection");
				}
				else if (Neut_float < 28) {
					diagnosis.add("Disruption of blood / blood cell formation");
					diagnosis.add("Infection");
					Rare_cases.add("Cancer");
				}
				
				//checking (Lymph) Lymphocytes:
				
				if(Lymph_float > 52) {
					diagnosis.add("Infection");
					diagnosis.add("Cancer");
				}
				else if (Lymph_float <36) {
					diagnosis.add("Disruption of blood / blood cell formation");
				}
				
				//checking Red Blood Cells (RBC):
				
				if (RBC_float > 6 && patient.LungDisease.equals("Yes")) {// if Patient is LungDisease
					diagnosis.add("Lung Disease");
					diagnosis.add("Disruption of blood / blood cell formation");
				}	
				else if(RBC_float > 6 && (patient.Smoking.equals("Yes"))) {// if Patient is Smoking
					diagnosis.add("Smokers");
					diagnosis.add("Disruption of blood / blood cell formation");
				}
				else if(RBC_float > 6) {
					diagnosis.add("Disruption of blood / blood cell formation");
				}
				else if (RBC_float <4.5 && patient.Bleeding.equals("Yes")) {// if Patient is Bleeding
					diagnosis.add("anemia");
					diagnosis.add("bleeding");
				}
				else if (RBC_float <4.5) {
					diagnosis.add("anemia");				
				}
	
				//checking (HCT):
				if(patient.Gender.equals("Male")) { // if Patient is a Male 
					
					if(HCT_float > 54 && (patient.Smoking.equals("Yes"))) {// if Patient is Smoking
						diagnosis.add("Smokers");
					}
					else if(HCT_float <37 && patient.Bleeding.equals("Yes")) {// if Patient is Bleeding
						diagnosis.add("bleeding");
					}
					else if(HCT_float <37 ) {
						diagnosis.add("anemia");	
					}
				}
				if(patient.Gender.equals("Female")) { // if Patient is a Female
					
					if(HCT_float > 47 && (patient.Smoking.equals("Yes"))) { // if Patient is smoking
						diagnosis.add("Smokers");
					}
					else if(HCT_float <33 && patient.Bleeding.equals("Yes")) { // if Patient is Bleeding
						diagnosis.add("bleeding");
					}	
					else if(HCT_float <33 ) {
						diagnosis.add("anemia");	
					}
				}
				
				//checking (Urea):
				if(Urea_float >43*0.1+43 && patient.EasternTestimony.equals("Yes")) { // if Patient is from the Eastern testimony
					diagnosis.add("Kidney disease");	
					diagnosis.add("Dehydration");	
					diagnosis.add("Increased consumption of meat");	
				}
				else if (Urea_float <17*0.1+17 && patient.EasternTestimony.equals("Yes") &&
						patient.Pregnant.equals("No")) {// if Patient is from the Eastern testimony and Patient is not Pregnant
					
					diagnosis.add("Malnutrition");
					diagnosis.add("diet");
					diagnosis.add("Liver disease");
				}
				else if (Urea_float >43 ) {
					diagnosis.add("Kidney disease");	
					diagnosis.add("Dehydration");	
					diagnosis.add("Increased consumption of meat");	
				}
				else if (Urea_float <17 && patient.Pregnant.equals("No")) { // if Patient is not Pregnant
					diagnosis.add("Malnutrition");
					diagnosis.add("diet");
					diagnosis.add("Liver disease");
				}
				// (Hb) Hemoglobin:
				
				if(patient.Gender.equals("Male") && Age_Int >17) { //if patient is a Male
					
					if(Hb_float <12) {
						diagnosis.add("anemia");	
						diagnosis.add("Hematological disorder");	
						diagnosis.add("Iron deficiency");
						if( patient.Bleeding.equals("Yes")) {//If patient is bleeding
							diagnosis.add("bleeding");
						}
					}			
				}
				else if(patient.Gender.equals("Female") && Age_Int >17 ) { //if patient is a Female
					
					if(Hb_float <12) {
						diagnosis.add("anemia");	
						diagnosis.add("Hematological disorder");	
						diagnosis.add("Iron deficiency");
						if( patient.Bleeding.equals("Yes")) {//If patient is bleeding
							diagnosis.add("bleeding");
						}
					}
				}
				else { //Child (age 0-17)
					
					if(Hb_float < 11.5) {
						diagnosis.add("anemia");	
						diagnosis.add("Hematological disorder");	
						diagnosis.add("Iron deficiency");
						if( patient.Bleeding.equals("Yes")) {//If patient is bleeding
							diagnosis.add("bleeding");
						}
					}
				}
				
				// (Crtn) Creatinine :
				
				if(Age_Int >=0 && Age_Int <=2) { //Baby
					
					if(Crtn_float >0.5 && patient.Diarrhea_Vomiting.equals("No")) { //The patient has no diarrhea or vomiting
						diagnosis.add("Kidney disease");	
						diagnosis.add("Muscle diseases");
						diagnosis.add("Increased consumption of meat");
					}
					else if(Crtn_float < 0.2) {
						diagnosis.add("Malnutrition");
					}
				}
				else if(Age_Int >=3 && Age_Int <=17) { //Teens and childrens
					
					if(Crtn_float >1 && patient.Diarrhea_Vomiting.equals("No")) {//The patient has no diarrhea or vomiting
						diagnosis.add("Kidney disease");
						diagnosis.add("Muscle diseases");
						diagnosis.add("Increased consumption of meat");
					}
					else if(Crtn_float < 0.5) {
						diagnosis.add("Malnutrition");
					}
				}
				else if(Age_Int >=18 && Age_Int <=59) { //Adults
					
					if(Crtn_float >1 && patient.Diarrhea_Vomiting.equals("No")) {//The patient has no diarrhea or vomiting
						diagnosis.add("Kidney disease");
						diagnosis.add("Muscle diseases");
						diagnosis.add("Increased consumption of meat");
					}
					else if(Crtn_float < 0.6) {
						diagnosis.add("Malnutrition");
					}
				}
				else if(Age_Int >=60 ) { //Elderly
					
					if(Crtn_float >1.2 && patient.Diarrhea_Vomiting.equals("No")) {//The patient has no diarrhea or vomiting
						diagnosis.add("Kidney disease");
						diagnosis.add("Muscle diseases");
						diagnosis.add("Increased consumption of meat");
					}
					else if(Crtn_float < 0.6) {
						diagnosis.add("Malnutrition");
					}
				}
				// (Iron): 
				
				if(patient.Gender.equals("Female")) {
					if(Iron_float >160-160*0.2) {
						diagnosis.add("Iron poisoning");	
					}
					else if (Iron_float <60-60*0.2) {
						diagnosis.add("Malnutrition");
						if(patient.Pregnant.equals("Yes")) { //if patient is Pregnant
							diagnosis.add("Iron deficiency");
						}
						if( patient.Bleeding.equals("Yes")){ //if patient is bleeding
							diagnosis.add("Iron deficiency");
							diagnosis.add("bleeding");
						}
					}
				}
				else if(patient.Gender.equals("Male")) {
					if(Iron_float >160) {
						diagnosis.add("Iron poisoning");
					}
					else if (Iron_float <60) {
						diagnosis.add("Malnutrition");
					}
					if( patient.Bleeding.equals("Yes")){ //if patient is bleeding
						diagnosis.add("Iron deficiency");
						diagnosis.add("bleeding");
					}
				}
				
				// High Density Lipoprotein (HDL):
				
				if(patient.Gender.equals("Female")) {//if patient is Female
	
					if (HDL_float <34*0.2+34 && patient.Ethiopian.equals("Yes") ) { //if patient is Ethiopian
						diagnosis.add("Heart diseases");
						diagnosis.add("Hyperlipidemia (Fats in the blood)");
						diagnosis.add("Adult diabetes");
					}
					else if (HDL_float <34) {
						diagnosis.add("Heart diseases");
						diagnosis.add("Hyperlipidemia (Fats in the blood)");
						diagnosis.add("Adult diabetes");
					}	
				}
				else if(patient.Gender.equals("Male")) { //if patient is Male
					
					if (HDL_float <29*0.2+29 && patient.Ethiopian.equals("Yes") ) { //if patient is Ethiopian
						diagnosis.add("Heart diseases");
						diagnosis.add("Hyperlipidemia (Fats in the blood)");
						diagnosis.add("Adult diabetes");
					}
					else if (HDL_float <29) {
						diagnosis.add("Heart diseases");
						diagnosis.add("Hyperlipidemia (Fats in the blood)");
						diagnosis.add("Adult diabetes");
					}
				}
				
				// Alkaline Phosphatase(AP) : 
				
				if(patient.EasternTestimony.equals("Yes")) { //if patient is coming from the Eastern Testimony
					
					if(AP_float > 120) {
						
						if(patient.Medications.equals("Yes")) { //if patient is using Medications
							diagnosis.add("Use of various medications");
						}
						else if(patient.Pregnant.equals("No")) { //if patient is not Pregnant
							diagnosis.add("Liver disease");
							diagnosis.add("Diseases of the biliary tract");
							diagnosis.add("Overactive thyroid gland");
						}
					}
					else if (AP_float <60) {
						diagnosis.add("diet");
						diagnosis.add("Vitamin deficiency");
					}
				}
				else if(patient.EasternTestimony.equals("No")) {//if patient is not coming from the Eastern Testimony
					
					if(AP_float > 90) {
						
						if(patient.Medications.equals("Yes")) { //if patient is using Medications
							diagnosis.add("Use of various medications");
						}
						else if(patient.Pregnant.equals("No")) { //if patient is not Pregnant
							diagnosis.add("Liver disease");
							diagnosis.add("Diseases of the biliary tract");
							diagnosis.add("Overactive thyroid gland");
						}
					}
					else if (AP_float <30) {
						diagnosis.add("diet");
						diagnosis.add("Vitamin deficiency");
					}
				}
				
				
				System.out.println(Rare_cases);
				String[] Rare_casesArray = Rare_cases.toArray(new String[0]);//converting Rare_cases list to array
				
				//Counting the amount of repetitions of a rare case
				int CancerCount =0,BloodDiseaseCount =0;
				for(int i =0;i<Rare_casesArray.length;i++) {
					
					if(Rare_casesArray[i] == "Cancer") {//if the rare_case is Cancer
						CancerCount++;
					}
					else if(Rare_casesArray[i] == "Blood disease") {//if the rare_case is Blood disease
						BloodDiseaseCount++;
					}
				}
				//checking if Rare_casesArray has a Rare_case which is repeated more than once
				if(CancerCount >=2) {
					diagnosis.add("Cancer");//adding Cancer to diagnosis
				}
				else if(BloodDiseaseCount >=2) {
					diagnosis.add("Blood disease");//adding Cancer to Blood disease
				}
				
	
				System.out.println(diagnosis);
	
				String[] diagnosisArray = diagnosis.toArray(new String[0]);//converting diagnosis list to array
				//Creating an array where all the elements are unique
				uniqeDiagnosis = new HashSet<String>(Arrays.asList(diagnosisArray)).toArray(new String[0]);
				
				System.out.println();
				for(int i=0;i<uniqeDiagnosis.length;i++) {
					System.out.print(uniqeDiagnosis[i]+", ");
				}
				
				//Creating Loading screen with a gif
				LoadingScreen loadingscreen = new LoadingScreen(uniqeDiagnosis,excelfile);
				
				CalculateTreatmentFlag = true;
				
				}
				else {
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
					UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"The patient's questions should \nbe answered first!.","Error",JOptionPane.ERROR_MESSAGE);
				   }
				
		}		
		else {
			UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
			UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
			JOptionPane.showMessageDialog(null,"A patient should be added first.","Error",JOptionPane.ERROR_MESSAGE);
		   }
		}
		
		else if(e.getSource() == ExitButton){ //EXIT
			JFrame ExitFrame = new JFrame ("Exit");
			UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
			UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
			if(JOptionPane.showConfirmDialog(ExitFrame, " You want to Exit  ? ","Exit",JOptionPane.YES_NO_OPTION)
					== JOptionPane.YES_NO_OPTION) {
				System.exit(0);
			}
		}
		else if (e.getSource()==EnterBloodTest) {//manually Register of the blood test
			
			if(patient!=null) {
				EnterBloodTestDialog Enterbloodtestdialog = new EnterBloodTestDialog(patient);	
			}
			else {
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
				UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null,"A patient should be added first.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (e.getSource()==SavingTreatment) {//saving the Treatment in excel file
			
			if(patient !=null)	{
				if(CalculateTreatmentFlag) {
					try {
						excelfile.WriteTreatmentToExcel(uniqeDiagnosis,patient);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,15));
					UIManager.put("OptionPane.minimumSize",new Dimension(380,60)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"The diagnostic file and recommendations were\n successfully saved in a new excel file.");
					
				}
				else {
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
					UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"The treatment should be calculated first. ","Error",JOptionPane.ERROR_MESSAGE);
					
				}
			}
			else{
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
				UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null,"A patient should be added first.","Error",JOptionPane.ERROR_MESSAGE);
				}
		}
		else if (e.getSource()==NewpatientButton) {
			
			//******** Cleans patient ********//
			patient = null;
			PatientQuestionsAnswerd = false;
			CalculateTreatmentFlag = false;
			ResetFields();
			
			//reset of the current patient details Label
			CurrentPatientLabel.setText("Current Patient : "); 
		}
		else if (e.getSource() == HelpButton) { //Helps user to understand the program.
			
			String infoMessage = "The goal of the program is to perform a calculation for a patient's "
					+ "\ndiagnosis and present recommendations for treatment based on a blood test and a "
					+ "\nnumber of questions to the patient."
					+"\n"
					+"\nSteps:"
					+ "\n1.Add a patient to the system with at least 4 details: ID, name, age, and gender."
					+ "\n2.Add the patient's blood test manually or by importing an excel file with blood test values."
					+ "\n3.Add patient questions."
					+ "\n4.Click on the Calculate Treatment button and the system will calculate the diagnosis and the recommendations for the treatment of the patient."
					+ "\n5.To save the diagnosis and recommendations,click Save Treatment and the file will be saved in an Excel file along with the patient's ID."
					+ "\n"
					+ "\n5.Thank you :)"
					;

			UIManager.put("OptionPane.messageForeground", Color.black);//setting text Color to black
			UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
			UIManager.put("OptionPane.minimumSize",new Dimension(1080,350)); //setting the preferred size of JOptionPane
		    JOptionPane.showMessageDialog(null, infoMessage, "HELP", JOptionPane.INFORMATION_MESSAGE);
		}		
  }	
	/**
	 * A function that creats the clock at the main panel.
	 */
    Runnable runnable = new Runnable() { 
        @Override
        public void run() {
          while (true) {

        	  CurrentTimeLabel.setText(getCurrentTimeString());//getting current time every sec.
            try {
              Thread.sleep(1000);//Delay of a second
            }
            catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
    };
}
