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
		int columns = 4;			//MAKE THIS DYNAMIC
		int rows = 1;				//MAKE THIS DYNAMIC
		
		
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
							
							//START SUPERVISOR CHECK
							
							//If student has correct supervisor increment fitness
							if(timetable[i][j+1] == student.getSupervisorID()) // THis should be in other IF?
							{
								fitness++;
							}
							else //Else decrement fitness
							{
								fitness = fitness - 1.5;
							}
							
							//END SUPERVISOR CHECK
							
							//START GONE ALREADY CHECK
							if(studentsGone.contains(student.getStudentID()))
							{
								fitness = fitness - 1.5;
							}
							else
							{
								//Add student to list of students who have already gone
								studentsGone.add(student.getStudentID());
								
								fitness = fitness++;
							}
							//END GONE ALREADY CHECK
							
						}
						
					}
					
					//If loop is exited without a match, entity is not student so decrement fitness
					if(counter == 0)	//Counter here should be either 0 or 1 (NO OTHER VALUE)
					{
						fitness = fitness - 3;
					}
					
					//Reset counter
					counter = 0;
					//END POSITION CHECK	
				}	//END STUDENT CHECK
				
				else if(j==1)			//If we're on the second column, SUPERVISOR CHECKS
					
				{
					//START POSITION CHECK
					for (Staff staff : staff) 
					{
						if(staff.getStaffID().contentEquals(timetable[i][j]))
						{
							counter++;
							//Entity is in correct position (Staff member)
							fitness++;
							
							//Duplicate entity check (Is supervisor also second reader?)
							if(timetable[i][j+1] == staff.getStaffID())
							{
								fitness = fitness - 1.5;	//Second reader and supervisor are the same entity
							}
							else
							{
								fitness++;					//They are different entities
							}
							
							//Duplicate entity check (Is supervisor also monitor?)
							if(timetable[i][j+2] == staff.getStaffID())
							{
								fitness = fitness - 1.5;	//Second reader and supervisor are the same entity
							}
							else
							{
								fitness++;					//They are different entities
							}
							
						}
					}
					
					//If loop is exited without a match, entity is not a staff member so decrement fitness
					if(counter == 0)	//Counter here should be either 0 or 1 (NO OTHER VALUE)
					{
						fitness = fitness - 3;
					}
					
					//Reset counter
					counter = 0;
					//END POSITION CHECK
				}	//END SUPERVISOR CHECK
				
				else if(j==2)		//If we're on the third column, SECOND READER CHECKS
				
				{
					//START POSITION CHECK
					for (Staff staff : staff) 
					{
						if(staff.getStaffID().contentEquals(timetable[i][j]))
						{
							counter++;
							//Entity is in correct position (Staff member)
							fitness++;
							
							//Duplicate entity check is second reader also monitor?
							if(timetable[i][j+1] == staff.getStaffID())
							{
								fitness = fitness - 1.5;	//Monitor and second reader are the same entity
							}
							else
							{
								fitness++;					//They are different entities
							}
							
						}
					}
					//End
					
					//If loop is exited without a match, entity is not a staff member so decrement fitness
					if(counter == 0)	//Counter here should be either 0 or 1 (NO OTHER VALUE)
					{
						fitness = fitness - 3;
					}
					
					//Reset counter
					counter = 0;
					
				//END POSITION CHECK
				//END SUPERVISOR CHECK
				}
				
				else if(j==3)		//If we're on the 4th column, MONITOR CHECKS
					
				{
					//START POSITION CHECK
					for (Staff staff : staff) 
					{
						if(staff.getStaffID().contentEquals(timetable[i][j]))
						{
							counter++;
							//Entity is in correct position (Staff member)
							fitness++;
							
							//START GONE ALREADY CHECK
							if(monitorsGone.contains(staff.getStaffID()))
							{
								fitness++;		//In this case the more a monitor goes the better
							}
							else
							{
								//Add staff to list of staff who have already gone
								monitorsGone.add(staff.getStaffID());
								fitness = fitness - 1.5;
							}
							//END GONE ALREADY CHECK
						}	
						
					}
					
					//If loop is exited without a match, entity is not a staff member so decrement fitness
					if(counter == 0)	//Counter here should be either 0 or 1 (NO OTHER VALUE)
					{
						fitness = fitness - 3;
					}
					
					//Reset counter
					counter = 0;
					
				}
			}
		}
		return fitness;
}
	
	
	
	//Constructor
	public GeneticOperations(ArrayList<Student> students, ArrayList<Staff> staff, String[][] timetable) {
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

	public String[][] getTimetable() {
		return timetable;
	}

	public void setTimetable(String[][] timetable) {
		this.timetable = timetable;
	}
}
