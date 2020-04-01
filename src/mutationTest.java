import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class mutationTest 
{

	@Test
	void test() 
	{
		//Create test object of genetic operations class
		GeneticOperations test = new GeneticOperations();
		
		//Create test population
		ArrayList<int[][]> population = new ArrayList<>();
		
		//Create result population
		ArrayList<int[][]> newPopulation = new ArrayList<>();
		
		
		//Create two test timetables
		int[][] timetable1 = {{0,1,3},{2,1,4},{3,6,0}};
		
		//Test rows and columns
		int rows = 3;
		int cols = 3;
		
		
		//Add test timetable to test population
		population.add(timetable1);
		
		//Call mutation creating new population
		newPopulation = test.twoPointSwapMutation(population, rows, cols);
		
		//Check that contents are of the same type
		assertThat(newPopulation, is(population));
		//Check that contents are the same size
		assertThat(newPopulation.size(), is(population.size()));
		
	}

}



