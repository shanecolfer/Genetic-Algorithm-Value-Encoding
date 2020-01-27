import java.awt.List;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class Control {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int i = 0; //Counter
		int j; //Counter
		int numEntities = 11; //Number of entities (8 students, 3 staff)
		int idLength = 4;    //Length of ID (bitstring)
		String temp = "";
		
		//String[] entityIDs = new String[numEntities];			//Hold id's of students in the form of bit strings
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
		
	}

}
