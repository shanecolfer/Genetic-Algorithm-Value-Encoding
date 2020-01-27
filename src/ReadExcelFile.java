import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

public class ReadExcelFile {
	
	public ArrayList<Student> readStudents() throws IOException
	{
		
		ArrayList<Student> students = new ArrayList<>();
		int i = 0; //Counter
		
		Workbook wb = null;			//Initialise work book
		
		try
		{
			FileInputStream fs = new FileInputStream("C:\\Users\\Shane\\Documents\\info.xlsx");
			
			//Create workbook object (buffer whole stream into memory)
			wb = new XSSFWorkbook(fs);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException f)
		{
			f.printStackTrace();
		}
		
		//Select sheet
		org.apache.poi.ss.usermodel.Sheet s1 = wb.getSheetAt(0);
		
		int rowCount = s1.getLastRowNum();
		
		//Select Row
		org.apache.poi.ss.usermodel.Row r1;
		//Select Cell
		Cell c1;
		Cell c2;
		
		r1 = s1.getRow(1);
		c1 = r1.getCell(0);
		
		for(i = 1; i < rowCount + 1; i++)
		{
			r1 = s1.getRow(i);
			c1 = r1.getCell(0);
			c2 = r1.getCell(1);
			System.out.println(c1.getStringCellValue());
			Student s2 = new Student(c1.getStringCellValue(), c2.getStringCellValue());
			students.add(s2);
		}
		
		
		return students;
	}
	
	public ArrayList<Staff> readStaff()
	{
		ArrayList<Staff> staff = new ArrayList<>();
		int i = 0; //Counter
		
		Workbook wb = null;			//Initialise work book
		
		try
		{
			FileInputStream fs = new FileInputStream("C:\\Users\\Shane\\Documents\\info.xlsx");
			
			//Create workbook object (buffer whole stream into memory)
			wb = new XSSFWorkbook(fs);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException f)
		{
			f.printStackTrace();
		}
		
		//Select sheet
		org.apache.poi.ss.usermodel.Sheet s1 = wb.getSheetAt(0);
		
		int rowCount = s1.getLastRowNum();
		
		//Select Row
		org.apache.poi.ss.usermodel.Row r1;
		//Select Cell
		Cell c1;
		
		r1 = s1.getRow(1);
		c1 = r1.getCell(0);
		
		for(i = 1; i < rowCount - 2; i++)		//This needs to be fixed, the number of cells in this row is HARD CODED
		{
			r1 = s1.getRow(i);
			c1 = r1.getCell(2);
			System.out.println(c1.getStringCellValue());
			Staff s2 = new Staff(c1.getStringCellValue());
			staff.add(s2);
		}
		
		
		return staff;
	}
}
