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
		int idLength = 10;    //Length of ID (bitstring)
		String[] monitors = new String[] {"Damian Gordon", "Sean O'Leary", "Svetlana Hensman", "Ciaran Kelly", "Brian Keegan", "Jack O'Neill"}; 
		ArrayList<String> staffNames = new ArrayList<String>(); //Hold staff names
		
		
		String temp = "";
		String[][] timetable;
		String[][] translatedTimetable;
		
		//Population & Generation variables
		int populationSize = 1000;
		int generationSize = 5000;
		
		//Variable for storing how many correct rows are in best timetable
		int correctRows = 0;
		
		//Rows and Column variables
		//These get passed to various table making functions
		//They denote the size of the timetable (x*y)
		int rows = 136;
		int columns = 3;
		
		int mutationCount = 0;
		
		//Crossover rate variables
		double testCross = 0.9;
		double mutationrateTest = 0.1;
		
		double crossOverRate = (int)(populationSize * 0.9);
		double mutationRate = (int)(populationSize * 0.1); //TODO TEST THIS NOW!
		
		System.out.println("Mutation rate: " + mutationRate);
	
		System.out.println("Crossover rate: " + crossOverRate);
		
		/////////////////////////////////////////////////////
		/*
		//Mutation TEST
		
		//Test timetable1
		String[][] parent1 = { {"1","3","9","8"}, {"5","4","7","2"}, {"6","12","11","10"}};
		
		//Test ArrayList
		ArrayList<String[][]> parents = new ArrayList<>();
		
		//Add single timetable to ArrayList
		parents.add(parent1);
		
		//Create object of Genetic Operations
		GeneticOperations g3 = new GeneticOperations();
		
		//Call mutation
		parents = g3.twoPointSwapMutation(parents, rows, columns);
		
		System.out.println(Arrays.deepToString(parents.get(0)));
		*/
		/////////////////////////////////////////////////////
		
		double fitness = 0;
		
		//ArrayList to hold student names
		ArrayList<Student> students = new ArrayList<>();
		
		//ArrayList to hold staff names
		ArrayList<Staff> staff = new ArrayList<>();
		
		//ArrayList to hold population of timetables
		ArrayList<String[][]> population = new ArrayList<>();
		
		//Array to hold fitnesses of candidates in population
		double[] fitnessArray= new double[populationSize];
		
		//ReadExcelFile object
		ReadExcelFile r1 = new ReadExcelFile();
		
		//Returns ArrayList of students
		students = r1.readStudents();
		
		System.out.println(students);
		
		//Returns ArrayList of staff();
		staff = r1.readStaff();
		System.out.println(staff);		//Now we have a list of staff members but it is made up from the supervisor list (Which is fine, there are no monitors outside of this list)
		
		for(i = 0; i < staff.size(); i++)
		{
			System.out.println(staff.get(i).getStaffName());
		}
		
		System.out.println("Students size: " + students.size());
		System.out.println("Staff size: " + staff.size());
		
		int numEntities = students.size() + staff.size(); //Number of entities for ID generation
		System.out.println("Number of Entities: " + numEntities);
		
		
		//THIS BLOCK OF CODE CAN BE USED TO ADD MONITORS IF THEY ARE NOT IN THE SUPERVISOR LIST
		/*
		for(i = 0; i < staff.size(); i++)
		{
			staffNames.add(staff.get(i).getStaffName());
		}
	
		for(i = 0; i < monitors.length; i++)
		{
			if(staffNames.contains(monitors[i]))				//If the staff members name is already on the list, do nothing AVOIDING DUPLICATED
			{
				
			}
			else												//If the staff members name is not on the list, add them!
			{
				Staff s1 = new Staff(monitors[i]);
				staff.add(s1);
			}
		}
		*/
		//END OF USELESS BLOCK OF CODE
		
		//Array of fitnesses
		double averageFitnessArray[] = new double[generationSize];
		
		//Holds total fitness of generation
		double totalFitness = 0;
		
		//Holds average fitness of generation
		double averageFitness = 0;
		
		//Best timetable
		String[][] bestTimetable = null;
		
		//Best fitness
		double bestFitness = 0;
		
		
		////////////////////////////////////////////////////////////////////////////
		
		//Hold id's of entities in the form of bit strings
		ArrayList<String> entityIDs = new ArrayList<String>();
		
		System.out.println("Before Loop NumEntities :" + numEntities);
		
		//Reset i to 0
		i = 0;
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
		
		for(i = 0; i < entityIDs.size(); i++)
		{
			System.out.println(entityIDs.get(i));
		}
		
		
				
		/////////////////////////////////////////////////////////////////////////////
				
		System.out.println(entityIDs.size());
		
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
		
		//Loop through students, matching their second reader names with second reader IDs and assinging them
		
		for(i=0; i<students.size(); i++)
		{
			for(j=0; j<staff.size(); j++)
			{
				if(students.get(i).getSecondReaderName().equals(staff.get(j).getStaffName()))
				{
					students.get(i).setSecondReaderID(staff.get(j).getStaffID());
				}
			}
		}
		
		//Go through monitor list and find monitor IDs and replace names
		//with IDs
		for(i=0; i<monitors.length; i++)
		{
			for(j=0; j<staff.size(); j++)
			{
				if(staff.get(j).getStaffName().contentEquals(monitors[i]))
				{
					monitors[i] = staff.get(j).getStaffID();
				}
			}
		}
		
		////////////////////////////////////////////////////////////////////////////
		
		//Printing
		System.out.println(students);
		System.out.println(staff);
		
		///////////////////////////////////////////////////////////////////////////
		
		
		// COMMENTED OUT FOR INPUT TESTING
		
		//Population Loop
		for(i = 0; i < populationSize; i++)
		{
			//Generate random timetable
			Timetable t1 = new Timetable();
			timetable = t1.generateTimetable(entityIDs, monitors, rows, columns);
			//System.out.println(Arrays.deepToString(timetable));
			
			//Add timetable to population
			population.add(timetable);
			
			//Grade timetable
			//Make fitness object
			GeneticOperations g1 = new GeneticOperations(students,staff,timetable);
			
			//Call fitness function
			fitness = g1.fitnessGrading(rows, columns);
			
			//Add fitness to fitness array
			fitnessArray[i] = fitness;
			
			//Translate current timetable
			translatedTimetable = t1.translateTimetable(timetable,students,staff,rows,columns);
			
			//Print timetable
			//System.out.println(Arrays.deepToString(translatedTimetable));
			
			//Print Fitness
			//System.out.println(fitness);
			
		}
		
		System.out.println("Fitness array before proportional selection");
		System.out.println(Arrays.toString(fitnessArray));
		
		//Generation Loop
		for(i = 0; i < generationSize; i++)
		{
			//Create genetic operations object
			GeneticOperations g1 = new GeneticOperations();
			
			//Proportionally select returning new population
			population = g1.proportionalSelection(fitnessArray, population, populationSize);
			
			
			//Call crossover returning new population
			
			for (int x = 0; x < crossOverRate; x++)
			{
				population = g1.twoDimensionalSubstringCrossover(population, rows, columns);
			}
			
			
			//Call mutation
			
			for (int x = 0; x < mutationRate; x++)
			{
				population = g1.twoPointSwapMutation(population, rows, columns);
				mutationCount++;
				//System.out.println("Mutation Initiated");
			}
			
			
			//Grade new population
			for (j = 0; j < population.size(); j++)
			{
				//Create new object for each loop with each timetable in population
				GeneticOperations g2 = new GeneticOperations(students,staff,population.get(j));
				//Grade fitness of individual timetable
				fitness = g2.fitnessGrading(rows, columns);
				
				//Check for best fitness (OVERALL)
				if(fitness > bestFitness)
				{
					bestFitness = fitness;
					
					//Assign best timetable (OVERALL)
					bestTimetable = population.get(j);
				}
				
				//Add this timetables fitness to the fitness array (OVERWRITING)
				fitnessArray[j] = fitness;
				totalFitness = totalFitness + fitness;
			}
			
			averageFitness = totalFitness / populationSize;
			
			//if (i % 100 == 0)
			//{
				System.out.println("Generation: " + (i) + " // Fitness: " + (averageFitness - 500));
			//}
			averageFitnessArray[i] = averageFitness - 500;
			totalFitness = 0;
			
			//crossOverRate = crossOverRate - 0.0001;
			//mutationRate = mutationRate + 0.0001;
			
		}
		
		
		
		//////////////////////////////////////////////////////////////////////////
		
		Timetable t1 = new Timetable();
		//Translate timetable ( BEST OVERALL ) 
		translatedTimetable = t1.translateTimetable(bestTimetable,students,staff,rows,columns);
		
		//Check how many correct rows are in best timetable
		for(i = 0; i < rows; i++)
		{
			for(j = 0; j < columns; j++)
			{
				if(j==0)
				{
					for (Student student : students) 
					{
						if(student.getStudentID().contentEquals(bestTimetable[i][j]))
						{
							
							//START CORRECT ROW CHECK
							//If student has correct supervisor and second reader increment correct row
							
							if(bestTimetable[i][j+1] == student.getSupervisorID() && bestTimetable[i][j+2] == student.getSecondReaderID()) // THis should be in other IF? //INCORRECT
							{
								correctRows++;
							}
							//END CORRECT ROW CHECK
						}
					}
				}
			}
		}
		//End checking how many correct rows are in best timetable
		
		//Print timetable
		System.out.println(Arrays.deepToString(translatedTimetable));
		System.out.println("Best Fitness: " + (bestFitness - 500));
		
		//Print how many rows were correct in best timetable
		System.out.println("Correct row count: " + correctRows);
		
		//Write timetable to file
		r1.printTimetable(translatedTimetable, averageFitnessArray);
		
		//BEST FITNESS DOES NOT SEEM TO MATCH THE GIVEN BEST TIMETABLE?
		//TEST THIS WITH A VERY SMALL POP SIZE AND GEN SIZE AND DEBUG, MAKE SURE THIS IS WORKING CORRECTLY!!!
		
		System.out.println("Mutation count: " + mutationCount);
		
		 //COMMENTED OUT FOR INPUT TESTING
	}
}
