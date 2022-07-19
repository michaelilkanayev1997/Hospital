package q1;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * This is the Patient class who is responsible for keeping the details of each patient.
 * 
 * @authors Michael Ilkanayev - 318216678 , Vladimir Davidzon -  317648632
 */
public class Patient {

	String Id; 
	String Name;
	String Age;
	String Gender;
	String BloodGroup;
	String Disease ;
	String AppointmentDate;
	String Weight;
	String Height;
	
	//blood test values:
	protected String WBC,Neut,Lymph,RBC,HCT,Urea,Hb,Crtn,Iron,HDL,AP; 
	//Patient questions:
	protected String Fever,Smoking,EasternTestimony,Pregnant,Diarrhea_Vomiting,Bleeding,Ethiopian,Medications,LungDisease;
	 
	/**
	 * constructor of the Patient
	 * @param Id - The id of the patient
	 * @param Name - The name of the patient
	 * @param Age - The age of the patient
	 * @param Gender - The gender of the patient
	 * @param BloodGroup - The blood group of the patient
	 * @param Disease - The disease of the patient
	 * @param AppointmentDate - The Appointment Date of the patient
	 * @param Weight - The weight of the patient
	 * @param Height - the height of the patient
	 */
	Patient(String Id,String Name,String Age,String Gender,String BloodGroup,String Disease,String AppointmentDate,String Weight,String Height){
		
		this.Id=Id;
		this.Name=Name;
		this.Age=Age;
		this.Gender=Gender;
		this.BloodGroup=BloodGroup;
		this.Disease=Disease;
		this.AppointmentDate=AppointmentDate;
		this.Weight=Weight;
		this.Height=Height;
	}

}
