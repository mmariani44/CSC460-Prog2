import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Proj1B {
	
	public static void main(String[] args) {
		ArrayList<Record> records = ReadBinaryFile(args[0]);
		if (records.size() >= 15) {
			try {
				System.out.println("First 5 Records:");
				for (int i = 0; i < 5; i++) {
					System.out.println(records.get(i));
				}
				System.out.println("\nMiddle 5 Records:");
				for (int i = ((records.size()-1)/2)-2; i < ((records.size()-1)/2)+3; i++) {
					System.out.println(records.get(i));
				}
				System.out.println("\nLast 5 Records:");
				for (int i = records.size()-5; i < records.size(); i++) {
					System.out.println(records.get(i));
				}
			} 
			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Error printing first, middle or last 5 records.");
			}
			System.out.format("\nNumber of Records:\n%d\n", records.size());
		}
		else {
			System.out.println("Error printing first, middle and last 5 records. Not enough data.");
		}
		
		PrintCommonStates(records);
		InterpolationSearch(args[0]);
	}
	
	private static void InterpolationSearch(String file_path) {
		RandomAccessFile binary_file = null;
        //open binary file
        try {
            binary_file = new RandomAccessFile(file_path, "r");
        } catch (IOException e) {
            System.out.println("Error opening the binary file.");
            System.exit(-1);
        }
        long record_count = 0;

        try {
        	long count = binary_file.length();
            record_count = count / Record.RECORD_LENGTH;
        } catch (IOException e) {
            System.out.println("Error getting the file's length.");
            System.exit(-1);
        }
        
        //get user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 0 or more Player IDs to search seperated by spaces: ");
        String res = scanner.nextLine().strip();
        scanner.close();
        if (res.equals("")) {
        	return;
        }
        String[] player_id_searches = res.split(" ");
        
        
        for (int i = 0; i < player_id_searches.length; i++) {
        	int target = Integer.parseInt(player_id_searches[i]);
        	
        	long probe_index = 0;
        	long low_index = 0;
        	long high_index = record_count-1;
        	

        	while (low_index <= high_index) {
        		
                try {
                    binary_file.seek((Record.RECORD_LENGTH)*low_index);
                } catch (IOException e) {
                    System.out.println("Error resetting file pointer.");
                    System.exit(-1);
                }
            	Record record = new Record(); // create object to hold record
            	record.FetchObject(binary_file);
            	int key_at_low = record.GetPlayerId();
            	
                try {
                    binary_file.seek((Record.RECORD_LENGTH)*high_index);
                } catch (IOException e) {
                    System.out.println("Error resetting file pointer.");
                    System.exit(-1);
                }
                record.FetchObject(binary_file);
            	int key_at_high = record.GetPlayerId();
            	probe_index = (long) (low_index + Math.ceil((target-key_at_low)/(key_at_high - key_at_low) * (high_index - low_index)));        		
        		
                try {
                    binary_file.seek((Record.RECORD_LENGTH)*probe_index);
                } catch (IOException e) {
                    System.out.println("Error resetting file pointer.");
                    System.exit(-1);
                }
                record.FetchObject(binary_file);
        		if (record.GetPlayerId() == target) {
        			System.out.format("player_id:%d " + " name:" + record.GetName() + " hometown_clean:" + record.GetHometownClean() + "\n", record.GetPlayerId());
        			int off = 1;
        			while (true) {
                        try {
                            binary_file.seek((Record.RECORD_LENGTH)*(probe_index-off));
                        } catch (IOException e) {
                        }
                        record.FetchObject(binary_file);
                        if (record.GetPlayerId() == target) {
                        	System.out.format("player_id:%d " + " name:" + record.GetName() + " hometown_clean:" + record.GetHometownClean() + "\n", record.GetPlayerId());
                        }
                        else {
                        	break;
                        }
                        off++;
        			}
        			off = 1;
        			while (true) {
                        try {
                            binary_file.seek((Record.RECORD_LENGTH)*(probe_index+off));
                        } catch (IOException e) {
                        }
                        record.FetchObject(binary_file);
                        if (record.GetPlayerId() == target) {
                        	System.out.format("player_id:%d " + " name:" + record.GetName() + " hometown_clean:" + record.GetHometownClean() + "\n", record.GetPlayerId());
                        }
                        else {
                        	break;
                        }
                        off++;
        			}
        			break;
        		}
        		else if (record.GetPlayerId() < target) {
        			low_index = probe_index+1;
        		}
        		else {
        			high_index = probe_index-1;
        		}
        	}
        }
        
	}
	
	private static void PrintCommonStates(ArrayList<Record> records) {
		//i DEFINETLY should have used a hashtable but i am NOT going back to change it
		ArrayList<String> states_no_dupe = new ArrayList<String>();
		ArrayList<String> states_dupe = new ArrayList<String>();
		for (int i = 0; i < records.size(); i++) {
			if (!states_no_dupe.contains(records.get(i).GetStateClean())) {
				states_no_dupe.add(records.get(i).GetStateClean());
			}
		}
		for (int i = 0; i < records.size(); i++) {
			states_dupe.add(records.get(i).GetStateClean());
		}
		
		//count number of occurrences for each state
		Integer[] frequencies = new Integer[states_no_dupe.size()];
		for (int i = 0; i < states_no_dupe.size(); i++) {
			frequencies[i] = Collections.frequency(states_dupe, states_no_dupe.get(i));
		}
		
		//create mapping between states and their frequencies
		TreeMap<Integer, String> states_frequencies = new TreeMap<Integer, String>(Collections.reverseOrder());
		for (int i = 0; i < frequencies.length; i++) {
			states_frequencies.put(frequencies[i], states_no_dupe.get(i));
		}
		
        Set<Integer> keys = states_frequencies.keySet();
        Iterator<Integer> iterator = keys.iterator();
        
        System.out.println("\n5 most common states:");
        int i = 0;
        while (iterator.hasNext() && i < 5) {
            Integer key = iterator.next();
            System.out.println(key + " " + states_frequencies.get(key));
            i++;
        }
        System.out.print("\n");
	}
	
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
}
