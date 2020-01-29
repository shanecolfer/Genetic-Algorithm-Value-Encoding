import java.awt.List;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Control {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		int i = 0; //Counter
		int j; //Counter
		int numEntities = 13; //Number of entities (8 students, 3 staff)
		int idLength = 4;    //Length of ID (bitstring)
		String temp = "";
		String[][] timetable;
		String[][] translatedTimetable;
		
		double fitness = 0;
		
		//ArrayList to hold student names
		ArrayList<Student> students = new ArrayList<>();
		
		//ArrayList to hold staff names
		ArrayList<Staff> staff = new ArrayList<>();
		
		ReadExcelFile r1 = new ReadExcelFile();
		
		//Returns ArrayList of students
		students = r1.readStudents();
		
		//Returns ArrayList of staff();
		staff = r1.readStaff();
		
		
		////////////////////////////////////////////////////////////////////////////
		
		//Hold id's of entities in the form of bit strings
		ArrayList<String> entityIDs = new ArrayList<String>();
		
		//Generate random bit strings for entities
		while(i < numEntities)
		{
			temp = "";
			
			
			for(j = 0; j < idLength; j++)
			{
				Random generator = new Random();
				int t = generator.nextInt(2) + 1;
				t = t-1;
				temp = temp + t;
			}
			
			if(!entityIDs.contains(temp))
			{
				entityIDs.add(temp);
				i++;
			}
			
		}
		
		System.out.println(entityIDs);
				
		/////////////////////////////////////////////////////////////////////////////
				
		
		//Set student IDs
		for(i = 0; i<students.size(); i++)
		{
			students.get(i).setStudentID(entityIDs.get(i));
		}
		
		//Set staff IDs
		for(j=0;j<staff.size(); i++)
		{
			staff.get(j).setStaffID(entityIDs.get(i));
			j++;
		}
		
		
		///////////////////////////////////////////////////////////////////////////
		
		//Loop through students, matching their supervisor names with supervisor IDs and assigning them
		
		for(i=0; i<students.size(); i++)
		{
			for(j=0; j<staff.size(); j++)
			{
				if(students.get(i).getSupervisorName().equals(staff.get(j).getStaffName()))
				{
					students.get(i).setSupervisorID(staff.get(j).getStaffID());
				}
			}
		}
		
		////////////////////////////////////////////////////////////////////////////
		
		//Printing
		System.out.println(students);
		System.out.println(staff);
		
		///////////////////////////////////////////////////////////////////////////
		
		Timetable t1 = new Timetable();
		timetable = t1.generateTimetable(entityIDs);
		
		//////////////////////////////////////////////////////////////////////////
		
		//Make fitness object
		GeneticOperations g1 = new GeneticOperations(students,staff,timetable);
		
		//Call fitness function :0 ///THIS DOESN't WORK FOR BREAKS, TAKE OUT BREAKS
		fitness = g1.fitnessGrading();
		System.out.println("Fitness of timetable: " + fitness);
		
		//Translate timetable
		translatedTimetable = t1.translateTimetable(timetable,students,staff);
		
		System.out.println(Arrays.deepToString(translatedTimetable));
	}

}
