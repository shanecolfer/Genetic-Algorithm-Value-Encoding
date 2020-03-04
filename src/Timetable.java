import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Timetable {

	//This function generates a random timetable from a given arraylist of entity IDs
	public String[][] generateTimetable(ArrayList<String> entityIDs, String[] monitors, int rows, int columns)
	{
		
		int x = -1;			//counter
		//String gap = "6666";
		int cellAmount = columns*rows; //Amount of cells on table (timeslots)
		
		String[][] timetable = new String[rows][columns];		//2-D Array to hold timetable
		int noOfBlanks = cellAmount - entityIDs.size();			//Store amount of spaces needed to be filled to fill timetable
		
		
		//Fill space with blanks								//This block was used previously to add breaks to candidate
		//for (int i = 0; i < noOfBlanks; i++)					//Doesn't really work 
		//{
		//	entityIDs.add(gap);
		//}
		
		//TODO TEST THIS
		
		//Pick random entities for each slot (allowing duplicates) EXLUDING MONITORS
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++) //Columns - 1 because we're leaving out monitors
			{
				Random rand = new Random();
			    String randomElement = entityIDs.get(rand.nextInt(entityIDs.size()));
				timetable[i][j] = randomElement;
			}
		}
		
		//FILLING IN COLUMN 4 with MONITORS
		for(int i = 0; i < rows; i ++)
		{
			Random rand = new Random();
		    String randomElement = monitors[rand.nextInt(monitors.length)];
			timetable[i][3] = randomElement;
		}
		
		
		//System.out.println(entityIDs);
		//System.out.println(Arrays.deepToString(timetable));
		return timetable;
		
	}
	
	//This function translates a binary timetable into a readable timetable
	public String[][] translateTimetable(String[][] timetable, ArrayList<Student> students, ArrayList<Staff> staff, int rows, int columns) 
	{
		
		String[][] translatedTimetable = new String[rows][columns];
		
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				for(Student student : students)
				{
					if(student.getStudentID().contentEquals(timetable[i][j]))
					{
						translatedTimetable[i][j] = student.getStudentName();
					}
				}
				
				for(Staff staff1 : staff)
				{
					if(staff1.getStaffID().contentEquals(timetable[i][j]))
					{
						translatedTimetable[i][j] = staff1.getStaffName();
					}
				}
			}
		}
		
		return translatedTimetable;
	}
	
}
