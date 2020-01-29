import java.util.ArrayList;

public class GeneticOperations {
	
	private ArrayList<Student> students = new ArrayList<>();		//Stores ArrayList of students.
	private ArrayList<Staff> staff = new ArrayList<>();				//Stores ArrayList of staff members (Possible
																	//	supervisors, second readers, and monitors).
	
	private String[][] timetable;										//Stores 2-D timetable made up of entity IDs.
	
	public double fitnessGrading()
	{
		double fitness = 0;			//Store fitness
		ArrayList<String> studentsGone = new ArrayList<>();		//Store students who have gone before
		ArrayList<String> monitorsGone = new ArrayList<>();		//Store monitors who have gone before
		
		int counter = 0;										//Counter
		
		Object currentEntity;									//Store the current entity
		
							
		int columns = 4;			//MAKE THIS DYNAMIC
		int rows = 18;				//MAKE THIS DYNAMIC
		
		
		//Fitness Loop
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				//If we're on the first column (STUDENT CHECKING)
				if(j==0)
				{
					//CORRECT POSITION CHECK
					
					//For each student in students check if bitstring is there i.e
					//entity is a student
					for (Student student : students) 
					{
						if(student.getStudentID().contentEquals(timetable[i][j]))
						{
							counter++;
							//Entity is a student fitness incremented
							fitness++;
							//Add student to list of students who have already gone
							studentsGone.add(student.getStudentID());
							//Store the current entity as an object for further anaylsis 
							currentEntity = student;
							
							//START SUPERVISOR CHECK
							
							//If student has correct supervisor increment fitness
							if(timetable[i][j+1] == student.getSupervisorID()) // THis should be in other IF?
							{
								fitness++;
							}
							else //Else decrement fitness
							{
								fitness = fitness - 1;
							}
							
							//END SUPERVISOR CHECK
							
							//START GONE ALREADY CHECL
							//MOVE THE ADDITION OF STUDENT TO GONE LIST TO THE BOTTOM OF THIS CODE
							
						}
						
					}
					
					//If loop is exited without a match, entity is not student so decrement fitness
					if(counter == 0)	//Counter here should be either 0 or 1 (NO OTHER VALUE)
					{
						fitness = fitness - 1.5;
					}
					
					//END POSITION CHECK
					
					
					
				}
				
			}
		}
		
		return fitness;
	}
	
	
	
	
	//Constructor
	public GeneticOperations(ArrayList<Student> students, ArrayList<Staff> staff, int[][] timetable) {
		super();
		this.students = students;
		this.staff = staff;
		this.timetable = timetable;
	}
	
	
	//Getters and setters
	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public ArrayList<Staff> getStaff() {
		return staff;
	}

	public void setStaff(ArrayList<Staff> staff) {
		this.staff = staff;
	}

	public int[][] getTimetable() {
		return timetable;
	}

	public void setTimetable(int[][] timetable) {
		this.timetable = timetable;
	}
}
