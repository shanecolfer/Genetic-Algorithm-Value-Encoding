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
		
		//Population & Generation variables
		int populationSize = 900;
		int generationSize = 2000;
		
		//Rows and Column variables
		//These get passed to various table making functions
		//They denote the size of the timetable (x*y)
		int rows = 8;
		int columns = 4;
		
		//Crossover rate variables
		int crossOverRate = (populationSize / 100) * 90;
		int mutationRate = (populationSize / 100) * 1;
	
		System.out.println("Crossover rate: " + crossOverRate);
		
		/////////////////////////////////////////////////////
		//Test timetable1
		//String[][] parent1 = { {"1","3","9","8"}, {"5","4","7","2"}, {"6","12","11","10"}};
		//String[][] parent2 = { {"4","6","11","9"}, {"10","1","5","3"}, {"2","12","7","8"}};
		
		//Test arraylist
		//ArrayList<String[][]> parents = new ArrayList<>();
		//parents.add(parent1);
		//parents.add(parent2);
		
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
		
		//Returns ArrayList of staff();
		staff = r1.readStaff();
		
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
		
		
		//Population Loop
		for(i = 0; i < populationSize; i++)
		{
			//Generate random timetable
			Timetable t1 = new Timetable();
			timetable = t1.generateTimetable(entityIDs, rows, columns);
			
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
			//TODO CHECK IF THIS MUTATION FUNCTION ACTUALLY WORKS
			
			for (int x = 0; x < mutationRate; x++)
			{
				population = g1.twoPointSwapMutation(population, rows, columns);
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
			
			if (i % 100 == 0)
			{
				System.out.println("Generation: " + (i) + " // Fitness: " + (averageFitness - 500));
			}
			averageFitnessArray[i] = averageFitness - 500;
			totalFitness = 0;
		}
		
		
		
		
		//////////////////////////////////////////////////////////////////////////
		
		Timetable t1 = new Timetable();
		//Translate timetable ( BEST OVERALL ) 
		translatedTimetable = t1.translateTimetable(bestTimetable,students,staff,rows,columns);
		//Print timetable
		System.out.println(Arrays.deepToString(translatedTimetable));
		System.out.println(bestFitness);
		
		//Write timetable to file
		r1.printTimetable(translatedTimetable, averageFitnessArray);
		
		//BEST FITNESS DOES NOT SEEM TO MATCH THE GIVEN BEST TIMETABLE?
		//TEST THIS WITH A VERY SMALL POP SIZE AND GEN SIZE AND DEBUG, MAKE SURE THIS IS WORKING CORRECTLY!!!
	}

}
