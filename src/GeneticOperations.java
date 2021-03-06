import java.util.ArrayList;
import java.util.Arrays;

public class GeneticOperations {
	
	private ArrayList<Student> students = new ArrayList<>();		//Stores ArrayList of students.
	private ArrayList<Staff> staff = new ArrayList<>();				//Stores ArrayList of staff members (Possible
																	//	supervisors, second readers, and monitors).
	
	private int[][] timetable;										//Stores 2-D timetable made up of entity IDs.
	
	public double fitnessGrading(int rows, int columns, int noOfRooms, int noOfTimeslots)
	{
		double fitness = 0;			//Store fitness
		ArrayList<Integer> studentsGone = new ArrayList<>();		//Store students who have gone before
		ArrayList<Integer> monitorsGone = new ArrayList<>();		//Store monitors who have gone before
		
		
		int counter = 0;										//Counter			
		int roomChecker = 0;
		int currentRoom = 0;
		int rowCounter = 0;
		
		
		
		//Fitness Loop
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
			
				
				//If we're on the first column (STUDENT CHECKING)
				if(j==0)
				{
					//CORRECT POSITION CHECK
					
					//For each student in students check if bit string is there i.e
					//entity is a student
					for (Student student : students) 
					{
						if(student.getStudentID() == timetable[i][j])
						{
							counter++;
							//Entity is a student fitness incremented
							fitness++;
							
							//START SUPERVISOR CHECK
							//If student has correct supervisor increment fitness
							if(timetable[i][j+1] == student.getSupervisorID()) 
							{
								fitness = fitness + 3;
								
							}
							else //Else decrement fitness
							{
								fitness = fitness - 3;
							}
							//END SUPERVISOR CHECK
							
							//START SECOND READER CHECK
							//If student has correct second reader increment fitness
							if(timetable[i][j+2] == student.getSecondReaderID())
							{
								fitness = fitness + 3;
							}
							else //Else decrement
							{
								fitness = fitness - 3;
							}
							//END SECOND READER CHECK
							
							//START GONE ALREADY CHECK
							if(studentsGone.contains(student.getStudentID()))
							{
								fitness = fitness - 3;
							}
							else
							{
								//Add student to list of students who have already gone
								studentsGone.add(student.getStudentID());
								
								fitness++;
							}
							//END GONE ALREADY CHECK
							
						}
						
					}
					
					//If loop is exited without a match, entity is not student so decrement fitness
					if(counter == 0)	//Counter here should be either 0 or 1 (NO OTHER VALUE)
					{
						fitness = fitness - 7;
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
						if(staff.getStaffID() == timetable[i][j])
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
							//if(timetable[i][j+2] == staff.getStaffID())
							//{
							//	fitness = fitness - 1.5;	//Second reader and supervisor are the same entity
							//}
							//else
							//{
							//	fitness++;					//They are different entities
							//}
							
						}
					}
					
					//If loop is exited without a match, entity is not a staff member so decrement fitness
					if(counter == 0)	//Counter here should be either 0 or 1 (NO OTHER VALUE)
					{
						fitness = fitness - 2;
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
						if(staff.getStaffID() == timetable[i][j])
						{
							counter++;
							//Entity is in correct position (Staff member)
							fitness++;
							
							//Duplicate entity check is second reader also monitor?
							//if(timetable[i][j+1] == staff.getStaffID())
							//{
							//	fitness = fitness - 1.5;	//Monitor and second reader are the same entity
							//}
							//else
							//{
						//		fitness++;					//They are different entities
						//	}
							
						}
					}
					//End
					
					//If loop is exited without a match, entity is not a staff member so decrement fitness
					if(counter == 0)	//Counter here should be either 0 or 1 (NO OTHER VALUE)
					{
						fitness = fitness - 1;
					}
					
					//Reset counter
					counter = 0;
					
				//END POSITION CHECK
				//END SUPERVISOR CHECK
				}
				
				//START ROOM CLASH CHECKING
				
				
				
				
				
				//If we're past the first room of the day do some checking
				if(roomChecker > noOfTimeslots - 1)
				{
					for(int y = 1; y < currentRoom + 1; y++)
					{
						//If the entity 8 slots above in column 1(same time different room) is the same there is a clash
						if(timetable[(i) - (noOfTimeslots * y)][0] == timetable[i][j]) 
						{
							fitness = fitness - 5;
						}
						else
						{
							fitness = fitness + 0.1;
						}
						
						//If the entity 8 slots above in column 2(same time different room) is the same there is a clash
						if(timetable[(i) - (noOfTimeslots * y)][1] == timetable[i][j]) 
						{
							fitness = fitness - 5;
						}
						else
						{
							fitness = fitness + 0.1;
						}
						
						//If the entity 8 slots above in column 3(same time different room) is the same there is a clash
						if(timetable[(i) - (noOfTimeslots * y)][2] == timetable[i][j]) 
						{
							fitness = fitness - 5;
						}
						else
						{
							fitness = fitness + 0.1;
						}
					}
				}
					
					
				
				//If the row counter is == to timeslots I.E we're in a new room we want to increment the current room integer and make the 
				// row counter 0 again
				if(rowCounter == noOfTimeslots)
				{
					currentRoom++;
					rowCounter = 0;
				}
				
				//If we have reached the next day, we're on the first room so no need to check backwards
				if(roomChecker == noOfTimeslots * noOfRooms)
				{
					roomChecker = 0;
					currentRoom = 0;
				}
				
							
				
				
				
				
				//END ROOM CLASH CHECKING
				
				/*
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
					
				}*/
			}
			
			roomChecker++;
			rowCounter++;
		}
		
		//fitness = fitness + 1500; //Make positive
		return fitness;
	}
	
	//Proportional Selection Function
	public ArrayList<int[][]> proportionalSelection(double[] fitnessArray, ArrayList<int[][]> population, int populationSize)
	{
		//ArrayList to store new population
		ArrayList<int[][]> newPopulation = new ArrayList<>();
		
		double s = 0;
		double rand = 0;
		double partialSum = 0;
		
		
		
		//Calculate sum "S" of all fitnesses in population
		for(int i=0; i<fitnessArray.length; i++)
		{
			s = s + fitnessArray[i];
		}
		
		
		
		//Calculate random number "rand" between 0 and s
		rand = (Math.random() * (s)) + 0;
		
				
		int i = 0;
		int j = 0;
		
		//Loop through population and sum fitnesses from 0 - Sum "S"
		while(i < fitnessArray.length)
		{
			partialSum = partialSum + fitnessArray[j];
			
			if (partialSum > rand)
			{
				newPopulation.add(population.get(j));
				//System.out.println(i);
				i++;
				j=0;
				rand = (Math.random() * (s)) + 0;
				partialSum = 0;
			}
			
			if (j < populationSize - 1)
			{
				j++;
			}
		}
		
		return newPopulation;
	}
	
	public ArrayList<int[][]> twoDimensionalSubstringCrossover(ArrayList<int[][]> population, int rows, int columns)
	{	
		
		//ArrayList to hold new pop
		ArrayList<int[][]> newPopulation = new ArrayList<>();
		
		//Make new population old population
		newPopulation = population;
		
		//Random crossover point
		int randRow;
		int randCol;
		
		//Random parent index's
		int randParent1;
		int randParent2;
		
		//Random parent timetables 
		int[][] parent1 = new int[rows][columns];
		int[][] parent2 = new int[rows][columns];
		
		//Child timetables
		int[][] child1 = new int[rows][columns];
		int[][] child2 = new int[rows][columns];
		
		
		//Generate random row between 0 and number of rows
		randRow = (int) (Math.random() * (rows)) + 0;
		
		//Generate random column between 0 and number of column
		randCol = (int) (Math.random() * (columns)) + 0;
		
		//Generate random timetable index from population to be parent 1
		randParent1 = (int) (Math.random() * (population.size())) + 0;
		//Generate random timetable index from population to be parent 2
		randParent2 = (int) (Math.random() * (population.size())) + 0;
		
		//Get parent timetables
		parent1 = population.get(randParent1);
		parent2 = population.get(randParent2);
		
		//Temporary variables for parents
		int[][] tempP1 = parent1;
		int[][] tempP2 = parent2;
		
		//Child 1 maker
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
					//Take first half of p1 and put into c1
					child1[i][j] = tempP1[i][j];
					
					
					//If we reach the crossover make the temp Parent1 variable Parent2
					if(i == randRow && j == randCol)
					{
						tempP1 = parent2;
					}
			}
		}//Child 1 created
		
		//Child 2 maker
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
					//Take first half of p2 and put into c1
					child2[i][j] = tempP2[i][j];
					
					
					//If we reach the crossover make the temp Parent1 variable Parent2
					if(i == randRow && j == randCol)
					{
						tempP2 = parent1;
					}
			}
		}//Child 2 created

		newPopulation.set(randParent1, child1);
		newPopulation.set(randParent2, child2);
		
		return newPopulation;
	}
	
	public ArrayList<int[][]> twoPointSwapMutation(ArrayList<int[][]> population, int rows, int columns)
	{
		//TODO TEST THIS
		//columns--; //To exclude monitors from mutation operations
		
		//ArrayList to hold new pop
		ArrayList<int[][]> newPopulation = new ArrayList<>();
		
		//Make new population old population
		newPopulation = population;
		
		//Hold selected timetable
		int[][] selectedTimetable = new int[rows][columns];
		
		//Hold selected value at index one and two
		int value1;
		int value2;
		
		//Generate random timetable index from population to be mutated
		int randTimetableIndex = (int) (Math.random() * (population.size())) + 0;
		
		//System.out.println(randTimetableIndex);
		
		//Generate rand row between 0 and number of rows to be first swap point
		int randRow1 = (int) (Math.random() * (rows)) + 0;
		
		//Generate rand col between 0 and number of col to be second swap point
		int randCol1 = (int) (Math.random() * (columns)) + 0;
		
		//Generate rand row between 0 and number of rows to be first swap point
		int randRow2 = (int) (Math.random() * (rows)) + 0;
		
		//Generate rand col between 0 and number of col to be second swap point
		int randCol2 = (int) (Math.random() * (columns)) + 0;
		
		//Assign selected timetable
		selectedTimetable = newPopulation.get(randTimetableIndex);
		
		//Retrieve values from selected timetable
		value1 = selectedTimetable[randRow1][randCol1];
		value2 = selectedTimetable[randRow2][randCol2];
		
		//Do swapping
		selectedTimetable[randRow1][randCol1] = value2;
		selectedTimetable[randRow2][randCol2] = value1;
		
		//Re insert into population
		newPopulation.set(randTimetableIndex, selectedTimetable);
		
		return newPopulation;
		
	}
	
	
	
	//Constructor
	public GeneticOperations(ArrayList<Student> students, ArrayList<Staff> staff, int[][] timetable) {
		super();
		this.students = students;
		this.staff = staff;
		this.timetable = timetable;
	}
	
	
	public GeneticOperations() {
		// TODO Auto-generated constructor stub
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
