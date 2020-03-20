import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ProgressBar;
import javax.swing.JFileChooser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Canvas;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import java.awt.TextArea;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Spinner;

public class GUI {

	protected Shell shlFinalYearDemos;
	private Text generationSizeBox;
	private Text populationSizeBox;
	private Text crossOverRateBox;
	private Label lblMutationRate;
	private Text mutationRateBox;
	private Label lblCrossoverRate;
	private Group grpInputFile;
	private Label lblChooseInputFile;
	private Button chooseInputBtn;
	
	//File variables
	private String selectedFile;
	private String selectedOutPath;
	
	//Genetic Algorithm parameter variables
	private int populationSize;
	private int generationSize;
	private double mutationRate;
	private double crossOverRate;
	
	//Timetable parameters
	private int noOfRoomsInt;
	private int noOfTimeslotsInt;
	private int noOfDaysInt;
	
	//Genetic Algorithm file input and output variables
	private String inputFile;
	private String outputDir;
	
	private StyledText styledText;
	private ProgressBar progressBar;
	
	private Boolean cancelGA = false;
	private Text noOfDays;
	private Text noOfTimeslots;
	private Text noOfRooms;
	
	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public StyledText getStyledText() {
		return styledText;
	}

	public void setStyledText(StyledText styledText) {
		this.styledText = styledText;
	}
	
	

	public int getNoOfRoomsInt() {
		return noOfRoomsInt;
	}

	public void setNoOfRoomsInt(int noOfRoomsInt) {
		this.noOfRoomsInt = noOfRoomsInt;
	}

	public int getNoOfTimeslotsInt() {
		return noOfTimeslotsInt;
	}

	public void setNoOfTimeslotsInt(int noOfTimeslotsInt) {
		this.noOfTimeslotsInt = noOfTimeslotsInt;
	}

	public int getNoOfDaysInt() {
		return noOfDaysInt;
	}

	public void setNoOfDaysInt(int noOfDaysInt) {
		this.noOfDaysInt = noOfDaysInt;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try {
			GUI window = new GUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlFinalYearDemos.open();
		shlFinalYearDemos.layout();
		while (!shlFinalYearDemos.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	public void createContents() {
		shlFinalYearDemos = new Shell();
		shlFinalYearDemos.setSize(587, 479);
		shlFinalYearDemos.setText("Final Year Demos Genetic Scheduler");
		
		Group grpInputParameters = new Group(shlFinalYearDemos, SWT.NONE);
		grpInputParameters.setText("Input Parameters");
		grpInputParameters.setBounds(10, 10, 230, 170);
		
		Label lblGenerationSize = new Label(grpInputParameters, SWT.NONE);
		lblGenerationSize.setBounds(10, 22, 88, 15);
		lblGenerationSize.setText("Generation Size");
		
		Label lblPopulationSize = new Label(grpInputParameters, SWT.NONE);
		lblPopulationSize.setBounds(10, 49, 88, 15);
		lblPopulationSize.setText("Population Size");
		
		lblMutationRate = new Label(grpInputParameters, SWT.NONE);
		lblMutationRate.setBounds(10, 76, 88, 15);
		lblMutationRate.setText("Mutation Rate");
		
		lblCrossoverRate = new Label(grpInputParameters, SWT.NONE);
		lblCrossoverRate.setBounds(10, 103, 88, 15);
		lblCrossoverRate.setText("Crossover Rate");
		
		generationSizeBox = new Text(grpInputParameters, SWT.BORDER);
		generationSizeBox.setText("3800");
		generationSizeBox.setBounds(126, 19, 76, 21);
		
		populationSizeBox = new Text(grpInputParameters, SWT.BORDER);
		populationSizeBox.setText("1000");
		populationSizeBox.setBounds(126, 46, 76, 21);
		
		crossOverRateBox = new Text(grpInputParameters, SWT.BORDER);
		crossOverRateBox.setText("0.9");
		crossOverRateBox.setBounds(126, 100, 76, 21);
		
		mutationRateBox = new Text(grpInputParameters, SWT.BORDER);
		mutationRateBox.setText("0.005");
		mutationRateBox.setBounds(126, 73, 76, 21);
		
		Button revertDefault = new Button(grpInputParameters, SWT.NONE);
		revertDefault.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				//Default parameter values
				generationSize = 3800;
				populationSize = 1000;
				crossOverRate = 0.9;
				mutationRate = 0.005;
				
				//Update text fields
				generationSizeBox.setText("3800");
				populationSizeBox.setText("1000");
				crossOverRateBox.setText("0.9");
				mutationRateBox.setText("0.005");
			}
		});
		revertDefault.setBounds(10, 135, 99, 25);
		revertDefault.setText("Revert to default");
		
		progressBar = new ProgressBar(shlFinalYearDemos, SWT.NONE);
		progressBar.setBounds(10, 387, 551, 17);
		
		grpInputFile = new Group(shlFinalYearDemos, SWT.NONE);
		grpInputFile.setText("Input File");
		grpInputFile.setBounds(246, 10, 315, 85);
		
		lblChooseInputFile = new Label(grpInputFile, SWT.NONE);
		lblChooseInputFile.setBounds(10, 21, 99, 15);
		lblChooseInputFile.setText("Choose input file:");
		
		chooseInputBtn = new Button(grpInputFile, SWT.NONE);
		
		
		chooseInputBtn.setBounds(247, 18, 60, 20);
		chooseInputBtn.setText("Go");
		
		StyledText inputPath = new StyledText(grpInputFile, SWT.BORDER);
		inputPath.setEditable(false);
		inputPath.setText("Input:");
		inputPath.setBounds(10, 42, 297, 33);
		
		Group grpOutputDirectory = new Group(shlFinalYearDemos, SWT.NONE);
		grpOutputDirectory.setText("Output Directory");
		grpOutputDirectory.setBounds(246, 98, 315, 82);
		
		StyledText outPutPath = new StyledText(grpOutputDirectory, SWT.BORDER);
		outPutPath.setEditable(false);
		outPutPath.setText("Output:");
		outPutPath.setBounds(10, 39, 295, 33);
		
		Label lblChooseOutputLocationl = new Label(grpOutputDirectory, SWT.NONE);
		lblChooseOutputLocationl.setBounds(10, 18, 154, 15);
		lblChooseOutputLocationl.setText("Choose output location:");
		
		//Choose Output
		Button chooseOutBtn = new Button(grpOutputDirectory, SWT.NONE);
		chooseOutBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				File[] files = FileSelector.main();
				
				if (files != null)
				{
					selectedOutPath = files[0].toString();
					outPutPath.setText("Output: " + selectedOutPath);
				}
				else
				{
					inputPath.setText("Please select a directory!");
				}
			}
		});
		chooseOutBtn.setBounds(245, 13, 60, 20);
		chooseOutBtn.setText("Go");
		
		styledText = new StyledText(shlFinalYearDemos, SWT.BORDER);
		styledText.setText("Running");
		styledText.setBounds(246, 186, 315, 195);
		
		Button runGA = new Button(shlFinalYearDemos, SWT.NONE);
		
		//RUNNING THE GA BUTTON
		runGA.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) 
			{
				
				//Read in parameters
				populationSize = Integer.parseInt(populationSizeBox.getText());
				generationSize = Integer.parseInt(generationSizeBox.getText());
				mutationRate = Double.parseDouble(mutationRateBox.getText());
				crossOverRate = Double.parseDouble(crossOverRateBox.getText());
				
				//Read in timetable parameters
				noOfRoomsInt = Integer.parseInt(noOfRooms.getText());
				noOfDaysInt = Integer.parseInt(noOfDays.getText());
				noOfTimeslotsInt = Integer.parseInt(noOfTimeslots.getText());
				
				//Read in file data
				inputFile = selectedFile;
				outputDir = selectedOutPath;
				
				//Add double slash
				inputFile.replace("\\", "\\\\");
				outputDir.replace("\\", "\\\\");
				
				System.out.println(inputFile);
				System.out.println(outputDir);
				
				//Run the GA
				geneticAlgorithm();
				
			}
		});
		//End run GA
		
		runGA.setBounds(132, 356, 108, 25);
		runGA.setText("Run!");
		
		Label lblNewLabel = new Label(shlFinalYearDemos, SWT.NONE);
		lblNewLabel.setBounds(259, 410, 55, 15);
		lblNewLabel.setText("Progress");
		
		Button btnStopGa = new Button(shlFinalYearDemos, SWT.NONE);
		btnStopGa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				cancelGA = true;
			}
		});
		
		btnStopGa.setBounds(10, 356, 108, 25);
		btnStopGa.setText("Stop!");
		
		Group grpTimetableInformation = new Group(shlFinalYearDemos, SWT.NONE);
		grpTimetableInformation.setText("Timetable Information");
		grpTimetableInformation.setBounds(10, 180, 230, 170);
		
		Label lblNewLabel_1 = new Label(grpTimetableInformation, SWT.NONE);
		lblNewLabel_1.setBounds(10, 17, 69, 15);
		lblNewLabel_1.setText("No. of Days");
		
		Label lblNewLabel_2 = new Label(grpTimetableInformation, SWT.NONE);
		lblNewLabel_2.setBounds(10, 44, 97, 15);
		lblNewLabel_2.setText("No. of Timeslots");
		
		Label lblNoOfRooms = new Label(grpTimetableInformation, SWT.NONE);
		lblNoOfRooms.setBounds(10, 71, 80, 15);
		lblNoOfRooms.setText("No. of Rooms");
		
		noOfDays = new Text(grpTimetableInformation, SWT.BORDER);
		noOfDays.setBounds(126, 14, 76, 21);
		
		noOfTimeslots = new Text(grpTimetableInformation, SWT.BORDER);
		noOfTimeslots.setBounds(126, 41, 76, 21);
		
		noOfRooms = new Text(grpTimetableInformation, SWT.BORDER);
		noOfRooms.setBounds(126, 68, 76, 21);
		
		chooseInputBtn.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				File[] files = FileSelector.main();
				
				if (files != null)
				{
					selectedFile = files[0].toString();
					inputPath.setText("Input: " + selectedFile);
				}
				else
				{
					inputPath.setText("Please select a file!");
				}
				
				
			}
		});
		
		

	}
	
	private void geneticAlgorithm()
	{
	
		SwingWorker<ArrayList<Double>, ArrayList<Double>> worker = new SwingWorker<ArrayList<Double>, ArrayList<Double>>()
		{
			@Override
			protected ArrayList<Double> doInBackground() throws Exception
			{
				int i = 0; //Counter
				int j; //Counter
				//int idLength = 8;    //Length of ID (bitstring)
				String[] monitors = new String[] {"Damian Gordon", "Sean O'Leary", "Svetlana Hensman", "Ciaran Kelly", "Brian Keegan", "Jack O'Neill"}; 
				//ArrayList<String> staffNames = new ArrayList<String>(); //Hold staff names
				
				//Arraylists to store gui output
				ArrayList<Double> newUpdate = new ArrayList<>();
				ArrayList<Double> finishedInfo = new ArrayList<>();
				
				
				//String temp = "";
				int[][] timetable;
				String[][] translatedTimetable;
				
				//Population & Generation variables
				//populationSize = 3800;
				//generationSize = 200;
				
				//Variable for storing how many correct rows are in best timetable
				int correctRows = 0;
				
				//Rows and Column variables
				//These get passed to various table making functions
				//They denote the size of the timetable (x*y)
				int rows = noOfTimeslotsInt * noOfDaysInt; //Hangs after 78 //Multiply number of days * number of timeslots per day to get full timetable size
				int columns = 3;
				
				int mutationCount = 0;
				
				//Crossover rate variables
				//double testCross = 0.9;
				//double mutationrateTest = 0.1;
				
				System.out.println("Mutation rate INPUT: " + mutationRate);
				
				System.out.println("Crossover rate INPUT: " + crossOverRate);
				
				crossOverRate = (int)(populationSize * crossOverRate);
				mutationRate = (int)(populationSize * mutationRate); //TODO TEST THIS NOW!
				
				System.out.println("Mutation rate: " + mutationRate);
			
				System.out.println("Crossover rate: " + crossOverRate);
				
				
				System.out.println(populationSize + " Pop");
				System.out.println(generationSize + " Gen");
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
				ArrayList<int[][]> population = new ArrayList<>();
				
				//Array to hold fitnesses of candidates in population
				double[] fitnessArray= new double[populationSize];
				
				//ReadExcelFile object
				ReadExcelFile r1 = new ReadExcelFile(inputFile, outputDir);
				
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
				int[] entityIDs = new int[numEntities];
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
				int[][] bestTimetable = null;
				
				//Best fitness
				double bestFitness = 0;
				
				
				////////////////////////////////////////////////////////////////////////////
				
				System.out.println("Before Loop NumEntities :" + numEntities);
				
				//Reset i to 0
				i = 0;
				//Generate random bit strings for entities
				/*
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
				*/
				
				/////////////////////////////////////////////////////////////////////////////
				//Set entity IDS
				
				for(i = 0; i < numEntities; i++)
				{
					entityIDs[i] = i;
					System.out.println(i);
				}
				
				//Set student IDs
				for(i = 0; i<students.size(); i++)
				{
					students.get(i).setStudentID(entityIDs[i]);
				}
				
				//Set staff IDs
				for(j=0;j<staff.size(); i++)
				{
					staff.get(j).setStaffID(entityIDs[i]);
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
				
				///////////////////////////////////////////////////////////////////////////
				/* UNCOMMENT IF USING MONITORS
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
				*/
				
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
					//translatedTimetable = t1.translateTimetable(timetable,students,staff,rows,columns);
					
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
					
					
					//System.out.println("AFTER PROPORTIONAL SEL");
					
					//Call crossover returning new population
					
					for (int x = 0; x < crossOverRate; x++)
					{
						population = g1.twoDimensionalSubstringCrossover(population, rows, columns);
					}
					
					
					//Call mutation
					
					for (int x = 0; x < mutationRate; x++)
					{
						population = g1.twoPointSwapMutation(population, rows, columns);
						//mutationCount++;
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
						//System.out.println("Generation: " + (i) + " // Fitness: " + (averageFitness - 500));
					//}
						
					averageFitnessArray[i] = averageFitness - 1500;
					totalFitness = 0;
					
					//If we're on the first generation, add some info into arraylist to send to GUI
					if(i == 0)
					{
						//Add gen number to new update
						newUpdate.add((double)i);
						newUpdate.add(averageFitness - 1500);
						newUpdate.add(bestFitness - 1500);
						newUpdate.add((double)generationSize);
					}
					else //Else replace the already created objects in the arraylist -> so I can keep the same index's 
					{
						newUpdate.set(0, (double)i);
						newUpdate.set(1, averageFitness - 1500);
						newUpdate.set(2, bestFitness - 1500);
					}
					
					publish(newUpdate);
					
					//If the cancel button has been pressed reset it and exit the generation loop
					if(cancelGA == true)
					{
						cancelGA = false;
						break;
					}
					
					
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
								if(student.getStudentID() == (bestTimetable[i][j]))
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
				System.out.println("Best Fitness: " + (bestFitness - 1500));
				
				//Print how many rows were correct in best timetable
				System.out.println("Correct row count: " + correctRows);
				
				//Write timetable to file raw
				r1.printTimetable(translatedTimetable, averageFitnessArray);
				
				//Write timetable to file fancy
				r1.printTimetableBetter(translatedTimetable, averageFitnessArray);
				
				//BEST FITNESS DOES NOT SEEM TO MATCH THE GIVEN BEST TIMETABLE?
				//TEST THIS WITH A VERY SMALL POP SIZE AND GEN SIZE AND DEBUG, MAKE SURE THIS IS WORKING CORRECTLY!!!
				
				System.out.println("Mutation count: " + mutationCount);
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				System.out.println(dateFormat.format(cal.getTime()));
				
				return newUpdate;
			}

			
			
			@Override
			protected void process(java.util.List<ArrayList<Double>> chunks) 
			{
				ArrayList <Double>newUpdate = chunks.get(chunks.size() -1);
				Display.getDefault().asyncExec(() -> styledText.setText("Current Generation: " + Double.toString(newUpdate.get(0))  + "\n" 
						+ "Average Fitness: " + Double.toString(newUpdate.get(1)) +  "\n" + "Best Fitness: " + Double.toString(newUpdate.get(2))));
				
				//System.out.println(Math.round((newUpdate.get(3) / 100)));
				
				//TODO: Sort this out, it's the loading bar logic it's stupid
				Display.getDefault().asyncExec(() -> progressBar.setSelection(progressBar.getSelection() + ((int)Math.round(newUpdate.get(3) / 100))));
			}



			@Override
			protected void done()
			{
				//ArrayList<Double> status = get();
				Display.getDefault().asyncExec(() -> styledText.append("\nAlgorithm Complete!"));
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				
				Display.getDefault().asyncExec(() -> styledText.append(dateFormat.format(cal.getTime())));
				
			}
			
			
		};
		
		//Run the worker (GA) in a new thread
		worker.execute();
	}

	public String getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(String selectedFile) {
		this.selectedFile = selectedFile;
	}

	public String getSelectedOutPath() {
		return selectedOutPath;
	}

	public void setSelectedOutPath(String selectedOutPath) {
		this.selectedOutPath = selectedOutPath;
	}

	public Boolean getCancelGA() {
		return cancelGA;
	}

	public void setCancelGA(Boolean cancelGA) {
		this.cancelGA = cancelGA;
	}
}
