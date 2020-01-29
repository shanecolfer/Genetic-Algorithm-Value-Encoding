import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Timetable {

	public String[][] generateTimetable(ArrayList<String> entityIDs)
	{
		
		int columns;		//Size of x of timetable
		int rows;			//Size of y of timetable
		int x = -1;			//counter
		String gap = "6666";
		columns = 4;
		rows = 18;
		int cellAmount = columns*rows; //Amount of cells on table (timeslots)
		
		String[][] timetable = new String[rows][columns];		//2-D Array to hold timetable
		
		int noOfBlanks = cellAmount - entityIDs.size();			//Store amount of spaces needed to be filled to fill timetable
		System.out.println(noOfBlanks);
		
		
		//Fill space with blanks
		//for (int i = 0; i < noOfBlanks; i++)
		//{
			entityIDs.add(gap);
		//}
		
		//Collections.shuffle(entityIDs);
		
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				Random rand = new Random();
			    String randomElement = entityIDs.get(rand.nextInt(entityIDs.size()));
				timetable[i][j] = randomElement;
			}
		}
		
		
		System.out.println(entityIDs);
		System.out.println(Arrays.deepToString(timetable));
		return timetable;
		
	}
	
}
