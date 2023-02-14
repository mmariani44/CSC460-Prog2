/**
 * Mason Mariani
 * CSC 460
 * Dr. McCann, Tanner Finken, Aayush Pinto
 * 
 * Given a file_name for basketball statistics, the program will sort them in ascending order according
 * to their player ID, and will write those results to a binary file.
 */

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Proj1A {

	public static void main(String[] args) {
		//parse data, sort, and write to binary file
		WriteToBinaryFile(GetNewFileName(args[0]), ParseData(args[0]));
		
	} // main()
	
	private static String GetNewFileName(String file_path_string) {
		//generate new file pathway
		String[] path_array = file_path_string.split("\\\\|////");
		String file_start_name = path_array[path_array.length-1];
		String file_name = "";
		for (int i = 0; i < file_start_name.length(); i++) {
			if (file_start_name.charAt(i) == '.') {
				break;
			}
			file_name = file_name + file_start_name.charAt(i);
		}
		return file_name;
		
	} // GetNewFileName()
	
	private static Record[] ParseData(String start_file_path_string) {
		Path start_file_path = Path.of(start_file_path_string);
		
		//separate each line into an element in an array, then convert to a list
		String file_string = "";
		try {
			file_string = Files.readString(start_file_path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		file_string = file_string.strip();
		String[] lines = file_string.split("\n");
		
		ArrayList<String> lines_list = new ArrayList<String>(Arrays.asList(lines));
		lines_list.remove(0);
		
		for (int i = 0; i < lines_list.size(); i++) {
			String[] line = lines_list.get(i).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			for (int j = 0; j < line.length; j++) {
				line[j] = Normalizer.normalize(line[j], Normalizer.Form.NFKD).replaceAll("[^\\p{ASCII}]", "");
				if (line[j].equals("")) {
					lines_list.remove(i);
					i--;
					break;
				}
			}
		}
		
		//sort
		Collections.sort(lines_list, new Comparator<String>() {
			@Override
			public int compare(String string_1, String string_2) {
				String[] array_1 = string_1.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				String[] array_2 = string_2.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				if (array_1[2].equals("") && array_2[2].equals("")) {
					return 0;
				}
				if (array_1[2].equals("") && !(array_2[2].equals(""))) {
					return -1;
				}
				if (!(array_1[2].equals("")) && array_2[2].equals("")) {
					return 1;
				}
				if (Integer.parseInt(array_1[2]) < Integer.parseInt(array_2[2])) {
					return -1;
				}
				if (Integer.parseInt(array_1[2]) > Integer.parseInt(array_2[2])) {
					return 1;
				}
				return 0;
			}
		});
		
		//turning all of the lines into Record objects
		Record[] records = new Record[lines_list.size()];
		for (int i = 0; i < lines_list.size(); i++) {
			String[] segments = lines_list.get(i).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

			try {
				records[i] = new Record();
				records[i].SetNcaaId(Integer.parseInt(segments[0]));
				records[i].SetTeam(segments[1]);
				records[i].SetPlayerId(Integer.parseInt(segments[2]));
				records[i].SetName(segments[3]);
				records[i].SetJersey(Integer.parseInt(segments[4]));
				records[i].SetHeightFt(Integer.parseInt(segments[5]));
				records[i].SetHeightIn(Integer.parseInt(segments[6]));
				records[i].SetPositionClean(segments[7]);
				records[i].SetYearClean(segments[8]);
				records[i].SetRedshirt(Integer.parseInt(segments[9]));
				records[i].SetHometownClean(segments[10]);
				records[i].SetStateClean(segments[11]);
				records[i].SetCountryClean(segments[12]);
			}
			catch (NumberFormatException e) {
				System.out.println("Failed to add value due to incorrect format.");
			}
		}
		
		return records;
		
	} // ParseData()
	
    private static void WriteToBinaryFile(String file_name, Record[] lines_list)
    {
		File file = null;
		RandomAccessFile bin_file = null;
		
		//delete any file of the same name
		try {
			file = new File(file_name + ".bin");
			if (file.exists()) {
				file.delete();
			}
		} catch (Exception e) {
			System.out.println("Error deleting previous file.");
			System.exit(-1);
		}
		
		//create the binary file
		try {
			bin_file = new RandomAccessFile(file,"rw");
		} catch (IOException e) {
			System.out.println("Error creating the binary file.");
			System.exit(-1);
		}
        
		try {
	        for (int i = 0; i < lines_list.length; i++) {
	        	lines_list[i].DumpObject(bin_file);
	        }
		} catch (Exception e) {}

		
		//close binary file
		try {
			bin_file.close();
		} catch (IOException e) {
			System.out.println("Error closing file.");
			System.exit(-1);
		}

    }  // writeBinaryFile()
	
}
