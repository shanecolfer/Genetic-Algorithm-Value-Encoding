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
	
	private String inputFile;
	private String outputDirectory;
	
	private int noOfRooms;
	private int noOfDays;
	private int noOfTimeslots;
	
	public int getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public int getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}

	public int getNoOfTimeslots() {
		return noOfTimeslots;
	}

	public void setNoOfTimeslots(int noOfTimeslots) {
		this.noOfTimeslots = noOfTimeslots;
	}

	public ReadExcelFile(String inputFile, String outputDirectory) {
		super();
		this.inputFile = inputFile;
		this.outputDirectory = outputDirectory;
	}
	
	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public String getOutputDirectory() {
		return outputDirectory;
	}

	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}

	public ArrayList<Student> readStudents() throws IOException
	{
		
		ArrayList<Student> students = new ArrayList<>();
		int i = 0; //Counter
		
		Workbook wb = null;			//Initialise work book
		
		try
		{
			FileInputStream fs = new FileInputStream(inputFile);
			
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
		
		for(i = 3; i < 53; i++)		//TODO THIS IS HARD CODED TO 2020 EXCEL SHEET
		{
			r1 = s1.getRow(i);
			c1 = r1.getCell(1);
			c2 = r1.getCell(2);
			c3 = r1.getCell(6);

			//System.out.println(c3.getStringCellValue());
			//System.out.println(c2.getStringCellValue());
			//System.out.println(c1.getStringCellValue());
			Student s2 = new Student(c3.getStringCellValue(), c1.getStringCellValue(), c2.getStringCellValue()); //Name, Supervisor, Second Reader
			students.add(s2);
		}
		
		wb.close();
		return students;
	}
	
	public ArrayList<Staff> readStaff() throws IOException
	{
		ArrayList <String> staffNames = new ArrayList<>();
		
		ArrayList<Staff> staff = new ArrayList<>();
		int i = 0; //Counter
		
		Workbook wb = null;			//Initialise work book
		
		try
		{
			FileInputStream fs = new FileInputStream(inputFile);
			
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
		
		//Read from staff list not insterting duplicates
		for(i = 3; i < 53; i++)		//TODO This needs to be fixed, the number of cells in this row is HARD CODED
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
		
		//Read from supervisor list inserting any that weren't on staff list
		for(i = 3; i < 53; i++)
		{
			r1 = s1.getRow(i);
			c1 = r1.getCell(2);
			Staff s2 = new Staff(c1.getStringCellValue());
			
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
		
		wb.close();
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
            FileOutputStream out = new FileOutputStream(new File(outputDirectory + "\\GAoutput.xlsx"));
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
	
	public void printTimetableBetter(String[][] timetable, double[] averageFitnessArray) throws IOException
	{
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = workbook.createSheet();
		
		String[] topRow = new String[] {"Student", "Supervisor", "Second R", "Monitor"};
		
		String [][] fancyTimetable = new String[100][100];
		
		for(int i = 0; i < fancyTimetable.length; i++)
		{
			for(int j = 0; j < fancyTimetable[0].length; j++)
			{
				fancyTimetable[i][j] = "BLANK";
			}
		}
		
		int i = 0;
		
		Row row = sheet.createRow(i);
		
		//Write info on top
		for(int j = 1; j < timetable[0].length + 1; j++)
		{
			Cell cell = row.createCell(j);
			
			cell.setCellValue(topRow[j-1]);
		}
		
		int j = 1;
		
		//Translate raw timetable into fancy timetable
		int ftRows = 0;
		int ftCols = 0;
		int rows = 0;
		int cols = 0;
		int roomCount = 0;
		int noOfRooms = 2;
		int noOfTimeslots = 8;
		int noOfTimeslotsTemp = noOfTimeslots;
		
		
		
		for(rows = 0; rows < timetable.length; rows++)
		{
			for(cols = 0; cols < timetable[0].length; cols++)
			{
				//If we've filled the number of timeslots
				if(ftRows == noOfTimeslotsTemp)
				{
					//If we've reached the number of rooms
					if(roomCount == noOfRooms)//Move down
					{
						ftRows = ftRows + 3;
						ftCols = 0;
						roomCount = 0;
						noOfTimeslotsTemp = noOfTimeslots + (ftRows);
					}
					else
					{
						ftRows = ftRows - noOfTimeslots;
						ftCols = ftCols + (timetable[0].length + 2);
						roomCount++;
					}
				}
				
				fancyTimetable[ftRows][ftCols] = timetable[rows][cols];
				ftCols++;
			}
			ftRows++;
			ftCols = ftCols - timetable[0].length;
		}
		
		System.out.println(Arrays.deepToString(fancyTimetable));
	
		//Write timetable
		//Write timetable
		for(i = 1; i < fancyTimetable.length + 1; i++)
		{
			Row row1 = sheet.createRow(i);
			
			for(j = 1; j < fancyTimetable[0].length + 1; j++)
			{
				Cell cell = row1.createCell(j);
				
				if(fancyTimetable[i - 1][ j - 1] == "BLANK")
				{
					cell.setCellValue("");
				}
				else
				{
					cell.setCellValue(fancyTimetable[i - 1][j - 1]);
				}
				
			}
		}
		
		Row row1 = sheet.createRow(fancyTimetable.length + 1);
		
		for(i = 0; i < averageFitnessArray.length; i++)
		{
			Cell cell = row1.createCell(i);
			
			cell.setCellValue(averageFitnessArray[i]);
		}
		
		//Source: https://howtodoinjava.com/library/readingwriting-excel-files-in-java-poi-tutorial/
		try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(outputDirectory + "\\GAoutputBetter.xlsx"));
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
