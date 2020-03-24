import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class crossoverTest {

	@Test
	void test() {
		
		//Create test object of genetic operations class
		GeneticOperations test = new GeneticOperations();
		
		//Create test population
		ArrayList<int[][]> population = new ArrayList<>();
		
		//Create result population
		ArrayList<int[][]> newPopulation = new ArrayList<>();
		
		
		//Create two test timetables
		int[][] timetable1 = {{0,1,3},{2,1,4},{3,6,0}};
		int[][] timetable2 = {{0,2,5},{10,19,1},{9,4,0}};
		int[][] timetable3 = {{0,2,3},{4,2,0},{42,2,5}};
		int[][] timetable4 = {{1,5,24},{85,7,4},{32,2,2}};
		
		//Test rows and columns
		int rows = 3;
		int cols = 3;
		
		
		//Add test timetables to test population
		population.add(timetable1);
		population.add(timetable2);
		population.add(timetable3);
		population.add(timetable4);
		
		//Get new population from crossover function
		newPopulation = test.twoDimensionalSubstringCrossover(population, rows, cols);
		
		//Check that contents are the same size
		assertThat(newPopulation.size(), is(population.size()));
		//Check that contents are of the same type 
		assertThat(newPopulation, is(population));
		
	}

}
