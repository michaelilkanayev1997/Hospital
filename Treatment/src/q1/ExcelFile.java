package q1;



public class ExcelFile {

	ExcelFile(){
		
	}
	
	public int ReadExcelFile(String Username,String Password ) throws IOException {
		
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
				System.out.print(cell.toString() + " ");
				
			}
			System.out.println();
			
		}
		
		workbook.close();
		fis.close();
		
		return 0;
	}
	
}
