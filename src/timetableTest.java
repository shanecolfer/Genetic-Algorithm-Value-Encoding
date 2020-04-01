import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class timetableTest 
{

	@Test
	void test() 
	{
		//Create test object of timetable class
		Timetable test = new Timetable();
		
		//Create test entity IDs
		int[] entityIDs = {1,2,3,4,5,6};
		
		//Create test monitors
		String[] monitors = {"Shane", "Colfer"};
		
		//Test rows and cols
		int rows = 50;
		int cols = 3;
		
		int timetable[][] = test.generateTimetable(entityIDs, monitors, rows, cols);
		
		//Check that the timetable is 50 rows long
		assertEquals(rows, timetable.length);
		//Check that the timetable is 3 columns wide
		assertEquals(cols, timetable[0].length);
		
	}

}

