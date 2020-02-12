import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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
		
		for(i = 1; i < rowCount - 2; i++)		//TODO This needs to be fixed, the number of cells in this row is HARD CODED
		{
			r1 = s1.getRow(i);
			c1 = r1.getCell(2);
			System.out.println(c1.getStringCellValue());
			Staff s2 = new Staff(c1.getStringCellValue());
			staff.add(s2);
		}
		
		
		return staff;
	}
	
	public void printTimetable(String[][] timetable) throws IOException
	{
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = workbook.createSheet();
		
		String[] topRow = new String[] {"Student", "Supervisor", "Second R", "Monitor"};
		
		int i = 0;
		
		Row row = sheet.createRow(i);
		
		//Write info on top
		for(int j = 1; j < timetable[0].length + 1; j++)
		{
			Cell cell = row.createCell(j);
			
			cell.setCellValue(topRow[j-1]);
		}
	
		for(i = 1; i < timetable.length + 1; i++)
		{
			Row row1 = sheet.createRow(i);
			
			for(int j = 1; j < timetable[0].length + 1; j++)
			{
				Cell cell = row1.createCell(j);
				
				cell.setCellValue(timetable[i - 1][j - 1]);
			}
		}
		
		//Source: https://howtodoinjava.com/library/readingwriting-excel-files-in-java-poi-tutorial/
		try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Shane\\Documents\\GAoutput.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Timetable written successfully on disk.");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
		//End source
		
		workbook.close();
	
	}
}
