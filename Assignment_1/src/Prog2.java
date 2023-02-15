import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class Prog2 {
	public static void main(String[] args) {
		DirectoryNode tree = BuildTree();
		WriteHashFile();
		PromptUser();
	}
	

	
	private static void WriteHashFile() {
		// TODO Auto-generated method stub
		
	}



	private static DirectoryNode BuildTree() {
		//read bin file, build the tree from it
		ArrayList<Record> records = ReadBinaryFile("\\wbbrosters2022.bin");
		
		DirectoryNode tree = new DirectoryNode();
		for (int i = 0; i < records.size(); i++) {
			String id_as_str = String.valueOf(records.get(i).GetPlayerId());
			tree.AddItem(id_as_str, tree, id_as_str.length()-1, i);
		}
		return tree;
	}



	/*
	 * reads the contents of the binary file and stores each entry as a Record object in an arraylist
	 * parameter-a string representing the path to the binary file to read
	 * returns-an arraylist of Record objects
	 */
    private static ArrayList<Record> ReadBinaryFile (String file_path) {
        RandomAccessFile binary_file = null;
        
        //open binary file
        try {
            binary_file = new RandomAccessFile(file_path, "r");
        } catch (IOException e) {
            System.out.println("Error opening the binary file.");
            System.exit(-1);
        }

        //determine the number of Records in the file
        long record_count = 0;

        try {
        	long count = binary_file.length();
        	record_count = count / Record.RECORD_LENGTH;
        } catch (IOException e) {
            System.out.println("Error getting the file's length.");
            System.exit(-1);
        }

        //move file pointer
        try {
            binary_file.seek(0);
        } catch (IOException e) {
            System.out.println("Error resetting file pointer.");
            System.exit(-1);
        }

        //put file contents into an ArrayList
        ArrayList<Record> file_contents = new ArrayList<Record>();
        for (int i = 0; i < record_count; i++) {
        	Record record = new Record(); // create object to hold record
        	record.FetchObject(binary_file);
            file_contents.add(record);
        }

        //close binary file
        try {
            binary_file.close();
        } catch (IOException e) {
            System.out.println("Error closing binary file.");
            System.exit(-1);
        }

        return file_contents;

    }  // readBinaryFile()







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
