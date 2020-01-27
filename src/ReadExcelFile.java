import java.io.File;  
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;  

public class ReadExcelFile {
	
	public ArrayList<Student> readStudents() throws IOException
	{
		FileInputStream is = new FileInputStream(new File("C:\\Users\\Shane\\Documents\\info.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(is);
		HSSFSheet sheet = wb.getSheetAt(0);
		
		FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
		
		
	}
	
	public ArrayList<Staff> readStaff()
	{
		
	}
}
