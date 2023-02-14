import java.util.Scanner;

public class Prog2 {
	public static void main(String[] args) {
		BuildTree();
		PromptUser();
	}
	

	
	private static void BuildTree() {
		// TODO Auto-generated method stub
		
	}











	//-----------------------Everything below this point is for user input and printing.------------------------------
	
	
	/*
	 * Gets input from the user. The input is to be player_id suffixes, and the
	 * user is continually prompted.
	 * 
	 * Entering the string "0000000" ends the prompt
	 */
	public static void PromptUser() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
	        //get user input
	        System.out.print("Enter player suffix to search: ");
	        String input = ProcessInput(scanner.nextLine().strip());
	        
	        System.out.print("\n");
	        if (input == "0000000") {
	        	break;
	        }
	        else {
	        	SearchBySuffix(input);
	        	PrintResults();
	        }
	        
		}
		scanner.close();
	}
	
	/*
	 * Processes the user's input into a usable String that can be queried
	 */
	public static String ProcessInput(String input) {
		String fixed_input = ""; //TODO
		return fixed_input;
	}
	
	/*
	 * Performs the search through the data structure to find matches given the suffix
	 */
	public static void SearchBySuffix(String suffix) {
		
	}
	
	/*
	 * Prints the results of the serach to the console
	 */
	public static void PrintResults() {
		
	}
}
