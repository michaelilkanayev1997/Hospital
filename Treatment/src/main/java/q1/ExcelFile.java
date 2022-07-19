package q1;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * This is the Excel File class who is responsible for writing and reading from an Excel file.
 * 
 * @authors Michael Ilkanayev - 318216678 , Vladimir Davidzon -  317648632
 */
public class ExcelFile {
	static String fileDictName = "";
	Map<String, String> TreatmentsDictionary  = new HashMap<String, String>();
	
	/**
	 * Constructor
	 */
	ExcelFile(){
		AddingTreatmentsToDict();
	}
	
	/**
	 * A function that checks if the user exists in the system (in the excel file).
	 * @param Username - user name.
	 * @param Password - password of the user.
	 * @return 1 if Username and Password are correct/-1 if Username correct and Password is incorrect.
	 * @throws IOException
	 */
	public int ReadExcelFile(String Username,String Password ) throws IOException {
		int resultOfreadingExcel =0;
		String CurrentPath = System.getProperty("user.dir"); 
		
		File excelFile = new File(CurrentPath +"\\Users.xlsx");
		FileInputStream fis = new FileInputStream(excelFile);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		//iterate on rows
		Iterator<Row> rowIt = sheet.iterator();
		
		while(rowIt.hasNext()) {
			
			Row row = rowIt.next();
			
			Iterator<Cell> cellIterator = row.cellIterator();
			
			while(cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				
				if((cell.toString()).equals(Username)) {
					cell = cellIterator.next();

					if((cell.toString()).equals(Password))
						resultOfreadingExcel = 1; // if Username and Password are correct return 1
					else
						resultOfreadingExcel = -1; // if Username correct and Password is incorrect return -1
				}		
			}	
		}
		workbook.close();
		fis.close();
		return resultOfreadingExcel;
	}
	
	/**
	 * A function that checks if the user's ID exists in the system (in the excel file).
	 * @param UserID - user id.
	 * 
	 * @return 1 if User ID exists in the system.
	 * @throws IOException
	 */
	public int ReadExcelFileID(String UserID ) throws IOException {
		int resultOfreadingExcel =0;
		String CurrentPath = System.getProperty("user.dir"); 
		
		File excelFile = new File(CurrentPath +"\\Users.xlsx");
		FileInputStream fis = new FileInputStream(excelFile);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		//iterate on rows
		Iterator<Row> rowIt = sheet.iterator();
		
		while(rowIt.hasNext()) {
			
			Row row = rowIt.next();
			
			XSSFCell cell = (XSSFCell) row.getCell(0); //getting first column
			
			if((cell.toString()).equals(UserID)) {
				resultOfreadingExcel = 1;// if ID exists 
			}	
		}
		workbook.close();
		fis.close();
		return resultOfreadingExcel;
	}

	/**
	 * A function that Write into existing excel (xlsx) file.
	 * The function receives data of a new user and creates a new user in the excel file.
	 * @param Username
	 * @param Password
	 * @param UserID
	 * @return int 0
	 */
	public int WriteToExcelFile(String Username,String Password ,String UserID) {
		String CurrentPath = System.getProperty("user.dir");

        // Creating file object of existing excel file
        File xlsxFile = new File(CurrentPath +"\\Users.xlsx");

        try {
            //Creating input stream
			FileInputStream inputStream = new FileInputStream(xlsxFile);
             
            //Creating workbook from input stream
            Workbook workbook = WorkbookFactory.create(inputStream);
 
            //Reading first sheet of excel file
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
 
            //Getting the count of existing records
            int rowCount = sheet.getLastRowNum();
            
            //Creating new row from the next row count
            Row row = sheet.createRow(++rowCount);

            
            for(int columnCount = 0;columnCount <3;columnCount++) {
   
	            //Creating new cell and setting the value
	            Cell cell = row.createCell(columnCount);
	            
	            if(columnCount==0)
	            	cell.setCellValue((String)UserID);
	            else if (columnCount==1)
	            	cell.setCellValue((String)Username);
	            else 
	            	cell.setCellValue((String)Password);
            }
            //Close input stream
            inputStream.close();
 
            //Crating output stream and writing the updated workbook
            FileOutputStream os = new FileOutputStream(xlsxFile);
            workbook.write(os);
             
            //Close the workbook and output stream
            workbook.close();
            os.close();
             
            System.out.println("Excel file has been updated successfully.");
            
        } catch (EncryptedDocumentException | IOException e) {
            System.err.println("Exception while updating an existing excel file.");
            e.printStackTrace();
        }
		return 0; 
	}
    /**
     * A function the read the blood test from an excel file .
     * 
     * @param patient - The patient object
     * @return true if succeeded /false if not.
     * @throws IOException
     */
	public boolean ReadExcelBloodTest(Patient patient) throws IOException {
		String[] StringArray = new String[11];
		
		String CurrentPath = importExcelToJtableJava(); 
		
		File excelFile = null;
		
		if(CurrentPath!=null) {
			excelFile = new File(CurrentPath);
			
			FileInputStream fis = new FileInputStream(excelFile);
			
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			//iterate on rows
			Iterator<Row> rowIt = sheet.iterator();
			
			int i = 0;
			while(rowIt.hasNext()) {
				
				Row row = rowIt.next();
				
				Iterator<Cell> cellIterator = row.cellIterator();
				
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					cell = cellIterator.next();
					
					StringArray[i++] =cell.toString();
				
					}		
				}	
			workbook.close();
			fis.close();
			
			patient.WBC = StringArray[0];
			patient.Neut = StringArray[1];
			patient.Lymph = StringArray[2];
			patient.RBC = StringArray[3];
			patient.HCT = StringArray[4];
			patient.Urea = StringArray[5];
			patient.Hb = StringArray[6];
			patient.Crtn = StringArray[7];
			patient.Iron = StringArray[8];
			patient.HDL = StringArray[9];
			patient.AP = StringArray[10];	
			
			return true;
		}
		return false;	
	}
	
	/**
	 * A function that Enters the treatments into the dictionary
	 */
	public void AddingTreatmentsToDict() {
		TreatmentsDictionary.put("anemia","Two 10 mg B12 pills a day for a month.");
		TreatmentsDictionary.put("diet","Schedule an appointment with a nutritionist.");
		TreatmentsDictionary.put("bleeding","To be evacuated urgently to the hospital.");
		TreatmentsDictionary.put("Hyperlipidemia (Fats in the blood)","Schedule an appointment with a nutritionist, a 5 mg pill of Simobil daily for a week.");
		TreatmentsDictionary.put("Disruption of blood / blood cell formation","10 mg pill of B12 a day for a month,5 mg pill of folic acid a day for a month.");
		TreatmentsDictionary.put("Hematological disorder","An injection of a hormone to encourage red blood cell production.");
		TreatmentsDictionary.put("Iron poisoning", "To be evacuated to the hospital.");
		TreatmentsDictionary.put("Dehydration", "Complete rest while lying down, returning fluids by drinking.");
		TreatmentsDictionary.put("Infection", "Dedicated antibiotics.");
		TreatmentsDictionary.put("Vitamin deficiency","Referral for a blood test to identify the missing vitamins.");
		TreatmentsDictionary.put("Viral disease", "Rest at home.");
		TreatmentsDictionary.put("Diseases of the biliary tract", "Referral to surgical treatment.");
		TreatmentsDictionary.put("Heart diseases", "Schedule an appointment with a nutritionist.");
		TreatmentsDictionary.put("Blood disease", "A combination of cyclophosphamide and corticosteroids.");
		TreatmentsDictionary.put("Liver disease","Referral to a specific diagnosis for the purpose of determining the treatment.");
		TreatmentsDictionary.put("Kidney disease", "Balance blood sugar levels.");
		TreatmentsDictionary.put("Iron deficiency", "Two 10 mg B12 pills a day for a month.");
		TreatmentsDictionary.put("Muscle diseases", "Two 5 mg pills of Altman c3 turmeric a day for a month.");
		TreatmentsDictionary.put("Smokers","To stop smoking.");
		TreatmentsDictionary.put("Lung Disease","Stop smoking / Refer to an X-ray of the lungs.");
		TreatmentsDictionary.put("Overactive thyroid gland", "propylthiouracil to reduce thyroid activity.");
		TreatmentsDictionary.put("Adult diabetes","Insulin adjustment for the patient.");
		TreatmentsDictionary.put("Cancer","Entrectinib.");
		TreatmentsDictionary.put("Increased consumption of meat","Schedule an appointment with a nutritionist.");
		TreatmentsDictionary.put("Use of various medications","Referral to a family doctor to match between the medications.");
		TreatmentsDictionary.put("Malnutrition", "Schedule an appointment with a nutritionist.");
	}
	
		/**
		 * A function that Write into existing excel (xlsx) file.
		 * 
		 * @param uniqeDiagnosis - the uniqeDiagnosis array of the patient.
		 * @param patient - the object of the patient.
		 * @return
		 * @throws IOException
		 */
		public int WriteTreatmentToExcel(String[] uniqeDiagnosis,Patient patient) throws IOException {
			String CurrentPath = System.getProperty("user.dir");

	        Workbook wb = new XSSFWorkbook();
	        org.apache.poi.ss.usermodel.Sheet sheet1 = wb.createSheet("Treatment");
	        FileOutputStream fileOut = new FileOutputStream(CurrentPath+"\\Treatments documentation"+"\\"+ patient.Id +"-Treatment.xlsx");
	        wb.write(fileOut);
	        fileOut.close();
	        wb.close();
	
	        
	        // Creating file object of existing excel file
	        File xlsxFile = new File(CurrentPath+"\\Treatments documentation" +"\\"+ patient.Id +"-Treatment.xlsx");

	        try {
	            //Creating input stream
				FileInputStream inputStream = new FileInputStream(xlsxFile);
	             
	            //Creating workbook from input stream
	            Workbook workbook = WorkbookFactory.create(inputStream);
	 
	            //Reading first sheet of excel file
	            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
	            
	            sheet.setDefaultColumnWidth(20);
	 
	            //Creating new row from the next row count
	          
	            int rowCount =0 ;
	            String[] PatientHeadlinesArr = {"Name","Id","Age","Gender",
	            		"BloodGroup","Disease","Weight","Height"};
	            String[] PatientDetailsArr = {patient.Name,patient.Id,patient.Age,patient.Gender,
	            		patient.BloodGroup,patient.Disease,patient.Weight,patient.Height};
	            
	            for(;rowCount< PatientHeadlinesArr.length ;rowCount++) {
	            	
	            	Row row = sheet.createRow(rowCount);
	            	
	            	 for(int columnCount = 0;columnCount <2;columnCount++) {
	 		            //Creating new cell and setting the value
	 		            Cell cell = row.createCell(columnCount);
	 		            
	 		            if(columnCount==0)
	 		            	cell.setCellValue(PatientHeadlinesArr[rowCount]);
	 		            else  
	 		            	cell.setCellValue(PatientDetailsArr[rowCount]);
	 	            } 
	            }

	            String[] BloodTestHeadlinesArr = {"Wbc","Neut","Lymph","RBC",
	            		"HCT","Urea","Hb","Crtn","Iron","HDL","AP"};
	            String[] PatientBloodTestArr = {patient.WBC,patient.Neut,patient.Lymph,patient.RBC,
	            		patient.HCT,patient.Urea,patient.Hb,patient.Crtn,patient.Iron,patient.HDL,patient.AP};
	            
	            rowCount++;
	            int i =0;
	            for(;i < BloodTestHeadlinesArr.length;rowCount++) {
	            	
	            	Row row = sheet.createRow(rowCount);
	            	
	            	 for(int columnCount = 0;columnCount <2;columnCount++) {
	 		            //Creating new cell and setting the value
	 		            Cell cell = row.createCell(columnCount);
	 		            
	 		            if(columnCount==0)
	 		            	cell.setCellValue(BloodTestHeadlinesArr[i]);
	 		            else  
	 		            	cell.setCellValue(PatientBloodTestArr[i]);
	 	            } 
	            	 i++;
	            }
	           
	            rowCount++;
	            i =0;
	            for(;i < 1;rowCount++) {
	            	Row row = sheet.createRow(rowCount);
	            	
	            	 for(int columnCount = 0;columnCount <2;columnCount++) {
	 		            //Creating new cell and setting the value
	 		            Cell cell = row.createCell(columnCount);
	 		            
	 		            if(columnCount==0)
	 		            	cell.setCellValue("Diagnosis");
	 		            else if(uniqeDiagnosis.length !=0) 
	 		            	cell.setCellValue(uniqeDiagnosis[i]);
	 		            else 
	 		            	cell.setCellValue("All values are fine!");
	 	            } 
	            	 i++;
	            }
	            i=1;
	            if(uniqeDiagnosis.length !=0) {
		            for(;i < uniqeDiagnosis.length;rowCount++) {
		            	Row row = sheet.createRow(rowCount);
		            	
		            	int columnCount = 1;
		 		            //Creating new cell and setting the value
		 		        Cell cell = row.createCell(columnCount);
		 		            
		 		        cell.setCellValue(uniqeDiagnosis[i]);
		            	i++;
		            }
	            }
	            
	            rowCount++;
	            i =0;
	            for(;i < 1;rowCount++) {
	            	Row row = sheet.createRow(rowCount);
	            	
	            	 for(int columnCount = 0;columnCount <2;columnCount++) {
	 		            //Creating new cell and setting the value
	 		            Cell cell = row.createCell(columnCount);
	 		            
	 		            if(columnCount==0)
	 		            	cell.setCellValue("Recommendation");
	 		            else if (uniqeDiagnosis.length !=0) 
	 		            	cell.setCellValue(uniqeDiagnosis[i]+" - "+TreatmentsDictionary.get(uniqeDiagnosis[i]));
	 		            else
	 		            	cell.setCellValue("None");
	 	            } 
	            	 i++;
	            }
	            i=1;
	            if(uniqeDiagnosis.length !=0) {
		            for(;i < uniqeDiagnosis.length;rowCount++) {
		            	Row row = sheet.createRow(rowCount);
		            	
		            	int columnCount = 1;
		 		            //Creating new cell and setting the value
		 		        Cell cell = row.createCell(columnCount);
		 		            
		 		        cell.setCellValue(uniqeDiagnosis[i]+" - "+TreatmentsDictionary.get(uniqeDiagnosis[i]));
		            	i++;
		            }
	            }
	            
	            //Close input stream
	            inputStream.close();
	 
	            //Crating output stream and writing the updated workbook
	            FileOutputStream os = new FileOutputStream(xlsxFile);
	            workbook.write(os);
	             
	            //Close the workbook and output stream
	            workbook.close();
	            os.close();
	             
	            System.out.println("\nExcel file has been updated successfully.");
	            
	        } catch (EncryptedDocumentException | IOException e) {
	            System.err.println("\nException while updating an existing excel file.");
	            e.printStackTrace();
	        }
			return 0; 
		}	
		
			/**
			 * A function that responsible for importing an Excel file with blood tests.
			 * 
			 * @return fileDictName - the path string of the file that was imported.
			 * @throws FileNotFoundException
			 * @throws IOException
			 */
		    public String importExcelToJtableJava() throws FileNotFoundException, IOException {
		        XSSFWorkbook workbook;

		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setDialogTitle("Open the file"); //name for chooser
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm"); //filter to show only that
		        fileChooser.setAcceptAllFileFilterUsed(false); //to show or not all other files
		        fileChooser.addChoosableFileFilter(filter);
		        
		        fileChooser.setSelectedFile(new File(fileDictName)); //when you want to show the name of file into the chooser
		        fileChooser.setVisible(true);
		        int result = fileChooser.showOpenDialog(fileChooser);
		        if (result == JFileChooser.APPROVE_OPTION) {
		            fileDictName = fileChooser.getSelectedFile().getAbsolutePath();
		            return fileDictName;
		        }
		        else {
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 15));
					UIManager.put("OptionPane.minimumSize",new Dimension(350,60)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"No Selection ");
		        }
				return null; 
		    }
}
	
	
