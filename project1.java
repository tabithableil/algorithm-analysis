import java.util.*;
import java.io.*;

public class project1 {

	public static void main(String[] args) throws IOException {
		
		Scanner userIn = new Scanner(System.in);
		String inputFile, outputFile;
		int xlessthan = 0;
		LinkedList<Integer> numbers2sort = new LinkedList<>();
		
		System.out.print("Input File Name: "); 
		inputFile = userIn.nextLine();
		
		Scanner fileIn = new Scanner(new File(inputFile));
		while(fileIn.hasNext()){
			numbers2sort.add(fileIn.nextInt());
		}
		
		int queryX = numbers2sort.get(0);
		int value; 
		int holePosition;
		
		// insertion sort
		for(int l = 1; l<numbers2sort.size(); l++){
			value = numbers2sort.get(l); 
			holePosition = l;  
			
			while(holePosition > 0 && numbers2sort.get(holePosition-1) > value){
				numbers2sort.set(holePosition, numbers2sort.get(holePosition-1));
				holePosition--;
			}
			
			numbers2sort.set(holePosition, value);
			
		}
		
		for(int i = 0; i < numbers2sort.size(); i++){
			if(numbers2sort.get(i) <= queryX)
				xlessthan++;		
		}
		
		
		System.out.print("File sorted. \nWhat would you like to name your output file? ");
		outputFile = userIn.nextLine();
		
		FileWriter output = new FileWriter(outputFile);
		BufferedWriter buffered = new BufferedWriter(output);
		
		// writes sorted list to file
		buffered.write(xlessthan + "\n");
		for(int i = 0; i < numbers2sort.size(); i++){
			buffered.write(numbers2sort.get(i) + "\n");
		}
		
		buffered.close();
		
		
	}

}
