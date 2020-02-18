import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
			FileInputStream fs = new FileInputStream("C:\\Users\\Shane\\Documents\\Student-Supervisor-List.xlsx");
			
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
		Cell c3;
		
		r1 = s1.getRow(3);
		c1 = r1.getCell(2);
		
		for(i = 3; i < 139; i++)		//TODO THIS IS HARD CODED TO 2020 EXCEL SHEET
		{
			r1 = s1.getRow(i);
			c1 = r1.getCell(1);
			c2 = r1.getCell(2);
			c3 = r1.getCell(6);

			//System.out.println(c3.getStringCellValue());
			//System.out.println(c2.getStringCellValue());
			//System.out.println(c1.getStringCellValue());
			Student s2 = new Student(c3.getStringCellValue(), c1.getStringCellValue(), c2.getStringCellValue());
			students.add(s2);
		}
		
		
		return students;
	}
	
	public ArrayList<Staff> readStaff()
	{
		ArrayList <String> staffNames = new ArrayList<>();
		
		ArrayList<Staff> staff = new ArrayList<>();
		int i = 0; //Counter
		
		Workbook wb = null;			//Initialise work book
		
		try
		{
			FileInputStream fs = new FileInputStream("C:\\Users\\Shane\\Documents\\Student-Supervisor-List.xlsx");
			
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
		
		r1 = s1.getRow(3);
		c1 = r1.getCell(0);
		
		String currentName;
		
		for(i = 3; i < 139; i++)		//TODO This needs to be fixed, the number of cells in this row is HARD CODED
		{
			r1 = s1.getRow(i);
			c1 = r1.getCell(1);
			Staff s2 = new Staff(c1.getStringCellValue());
			
			
			//System.out.println(staffNames);
			
			if(staffNames.contains(c1.getStringCellValue())) //This is checking has the staff member gone already? 
			{
				//System.out.println("TRUE");
			}
			else											//If not lets add them to list, this is because we don't want duplicates in the staff list
			{
				staff.add(s2);
				staffNames.add(c1.getStringCellValue());
			}
			
		}
		
		
		return staff;
	}
	
	public void printTimetable(String[][] timetable, double[] averageFitnessArray) throws IOException
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
	
		//Write timetable
		for(i = 1; i < timetable.length + 1; i++)
		{
			Row row1 = sheet.createRow(i);
			
			for(int j = 1; j < timetable[0].length + 1; j++)
			{
				Cell cell = row1.createCell(j);
				
				cell.setCellValue(timetable[i - 1][j - 1]);
			}
		}
		
		Row row1 = sheet.createRow(timetable.length + 1);
		
		for(i = 0; i < averageFitnessArray.length; i++)
		{
			Cell cell = row1.createCell(i);
			
			cell.setCellValue(averageFitnessArray[i]);
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
