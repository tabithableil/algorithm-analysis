import java.util.*;
import java.io.*;

public class project2 {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		File file1 = new File(args[0]); 
		File file2 = new File(args[1]);
		
		Scanner scanner = new Scanner(file1);
		String string1 = scanner.nextLine();
		scanner.close();
		Scanner scanner2 = new Scanner(file2);
		String string2 = scanner2.nextLine();
		scanner2.close();
		
		// stack to be used later on for LCS
		Stack<Character> lcs = new Stack<Character>();
		
		int string1length = string1.length();
		int string2length = string2.length();
		int [][] table = new int[string1length+1][string2length+1];
		
		for(int r = 0; r <= string2length; r++){
			table[0][r] = r;	
		}
		
		for(int c = 0; c <= string1length; c++){
			table[c][0] = c;
		}
		
		// build table
		for(int i = 1; i <= string1length; i++){
			for(int j = 1; j <= string2length; j++){
				
				// if two characters are the same, then the value of the cell at
				// table[i][j] = the cell diagonally to the upper left
				if(string1.charAt(i-1)==string2.charAt(j-1)){ 
					table[i][j]=table[i-1][j-1];
				}
				
				// if the characters are not the same, we choose the smaller value
				// from either directly to the left or directly above and add 1 
				// and assign it to the cell table[i][j]
				else{
					if(table[i-1][j] <= table[i][j-1]){
						table[i][j] = table[i-1][j]+1;
					}
					else if(table[i][j-1] <= table[i-1][j]){
						table[i][j] = table[i][j-1]+1;
					}
					
				}
					
			}
		}
		
		// these variables are only used to make the editDistance equation cleaner
		int i = string1length;
		int j = string2length;
		int d = table[s1][s2];
		
		double editDistance = (i+j-(double)d)/(i+j);
		
		// backtracking to find the least common sequence
		while(i > 0 || j > 0){
			// starting at the bottom right cell and the end of both strings,
			// if the characters match, push that character onto the stack
			// and decrement the counters i and j so we move to the upper left cell
			if(string1.charAt(i-1)==string2.charAt(j-1)){
				char matchChar = string2.charAt(j-1);
				lcs.push(matchChar);
				i--;
				j--;
				
			}
			
			else{
				// if the characters do not match, we search for the smaller value
				// between the cell directly above and the cell directly to the left
				// if the cell directly above is smaller, we move up
				if(table[i-1][j] < table[i][j-1] ){
					i= i-1;
				}
				// else, if the cell to the left is smaller or they are equal
				// we move to the left
				else if(table[i][j-1] <= table[i-1][j]){
					
					j=j-1;
				}
					
			}
		};
		
		System.out.println(editDistance);

		while(!lcs.isEmpty()){
			char getChar = lcs.pop();
			System.out.print(getChar + " ");
		}
		
	}
}