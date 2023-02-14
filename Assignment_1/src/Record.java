/**
 * Mason Mariani
 * CSC 460
 * Dr. McCann, Tanner Finken, Aayush Pinto
 * 
 * Acts as the primary data structure for housing information related to each record.
 * Each object stores a player's ncaa id, their team, player id, name, jersey, height_ft, height_in, position,
 * year, redshirt, hometown, state, and country. There are getters and setters for each field, and helper methods
 * for certain actions to be performed.
 */

import java.io.IOException;
import java.io.RandomAccessFile;

public class Record
{
	//constants
	public static final int TEAM_LENGTH = 50;  
	public static final int NAME_LENGTH = 40;  
	public static final int POSITION_CLEAN_LENGTH = 30;
	public static final int YEAR_CLEAN_LENGTH = 30;
	public static final int HOMETOWN_CLEAN_LENGTH = 60;  
	public static final int STATE_CLEAN_LENGTH = 60;  
	public static final int COUNTRY_CLEAN_LENGTH = 40;  
    
    public static int RECORD_LENGTH = TEAM_LENGTH+NAME_LENGTH+POSITION_CLEAN_LENGTH+YEAR_CLEAN_LENGTH
    		+HOMETOWN_CLEAN_LENGTH+STATE_CLEAN_LENGTH+COUNTRY_CLEAN_LENGTH + (6*4);

    //data fields
    private  int 		ncaa_id;
    private  String 	team;
    private  int 		player_id;
    private  String 	name;
    private  int 		jersey;
    private  int 		height_ft;
    private  int 		height_in;
    private  String 	position_clean;
    private  String 	year_clean;
    private  int 		redshirt;
    private  String 	hometown_clean;
    private  String 	state_clean;
    private  String 	country_clean;
    
    //data getters
    public int 		GetNcaaId() {return(ncaa_id);}
    public String 	GetTeam() {return(team);}
    public int 		GetPlayerId() {return(player_id);}
    public String 	GetName() {return(name);}
    public int 		GetJersey() {return(jersey);}
    public int 		GetHeightFt() {return(height_ft);}
    public int 		GetHeightIn() {return(height_in);}
    public String 	GetPositionClean() {return(position_clean);}
    public String 	GetYearClean() {return(year_clean);}
    public int 		GetRedshirt() {return(redshirt);}
    public String 	GetHometownClean() {return(hometown_clean);}
    public String 	GetStateClean() {return(state_clean);}
    public String 	GetCountryClean() {return(country_clean);}

    //data setters
    public  void 	SetNcaaId(int new_ncaa_id) {ncaa_id = new_ncaa_id;}
    public  void 	SetTeam(String new_team) {team = new_team;}
    public  void 	SetPlayerId(int new_player_id) {player_id = new_player_id;}
    public  void 	SetName(String new_name) {name = new_name;}
    public  void 	SetJersey(int new_jersey) {jersey = new_jersey;}
    public  void 	SetHeightFt(int new_height_ft) {height_ft = new_height_ft;}
    public  void 	SetHeightIn(int new_height_in) {height_in = new_height_in;}
    public  void 	SetPositionClean(String new_position_clean) {position_clean = new_position_clean;}
    public  void 	SetYearClean(String new_year_clean) {year_clean = new_year_clean;}
    public  void 	SetRedshirt(int new_redshirt) {redshirt = new_redshirt;}
    public  void 	SetHometownClean(String new_hometown_clean) {hometown_clean = new_hometown_clean;}
    public  void 	SetStateClean(String new_state_clean) {state_clean = new_state_clean;}
    public  void 	SetCountryClean(String new_country_clean) {country_clean = new_country_clean;}
    
    /*
     * Pads the length of a string with spaces until it is of length: "constant_length"
     * paramaters-a string to be extended, and a length to be extended to
     * returns-a string padded with spaces on the right
     */
    public String PadString(String string, int constant_length) {
    	String return_string = "";
    	for (int i = 0; i < string.length(); i++) {
    		return_string = return_string + string.charAt(i);
    	}
    	int spaces_to_add = constant_length - string.length();
    	if (spaces_to_add > 0) {
    		while (spaces_to_add > 0) {
    			return_string = return_string + " ";
    			spaces_to_add--;
    		}
    	}
    	return return_string;
    }
    
    /*
     * writes the contents of this object to the binary file
     * paramaters-the pointer to the binary file
     */
    public void DumpObject(RandomAccessFile stream)
    {    	
        try { //writing
            	
            stream.writeInt(ncaa_id);
            stream.writeBytes(PadString(team.toString(), TEAM_LENGTH));
            stream.writeInt(player_id);
            stream.writeBytes(PadString(name.toString(), NAME_LENGTH));
            stream.writeInt(jersey);
            stream.writeInt(height_ft);
            stream.writeInt(height_in);
            stream.writeBytes(PadString(position_clean.toString(), POSITION_CLEAN_LENGTH));
            stream.writeBytes(PadString(year_clean.toString(), YEAR_CLEAN_LENGTH));
            stream.writeInt(redshirt);
            stream.writeBytes(PadString(hometown_clean.toString(), HOMETOWN_CLEAN_LENGTH));
            stream.writeBytes(PadString(state_clean.toString(), STATE_CLEAN_LENGTH));
            stream.writeBytes(PadString(country_clean.toString(), COUNTRY_CLEAN_LENGTH));
                
            } catch (IOException e) {
               System.out.println("Error: couldnt write to the binary file.");
               System.exit(-1);
            }
    } //DumpObject()

    /*
     * reads the contents of binary file and stores it in this object
     * paramaters-the pointer to the binary file
     */
    public void FetchObject(RandomAccessFile stream) 
    {
    	byte[] team_byte = new byte[TEAM_LENGTH];
    	byte[] name_byte = new byte[NAME_LENGTH];
    	byte[] position_clean_byte = new byte[POSITION_CLEAN_LENGTH];
    	byte[] year_clean_byte = new byte[YEAR_CLEAN_LENGTH];
    	byte[] hometown_clean_byte = new byte[HOMETOWN_CLEAN_LENGTH];
    	byte[] state_clean_byte = new byte[STATE_CLEAN_LENGTH];
    	byte[] country_clean_byte = new byte[COUNTRY_CLEAN_LENGTH];
        
    	try {
    		ncaa_id = stream.readInt();
    		stream.readFully(team_byte);
    		team = new String(team_byte).strip();
    		player_id = stream.readInt();
    		stream.readFully(name_byte);
    		name = new String(name_byte).strip();
    		jersey = stream.readInt();
    		height_ft = stream.readInt();
    		height_in = stream.readInt();
    		stream.readFully(position_clean_byte);
    		position_clean = new String(position_clean_byte).strip();
    		stream.readFully(year_clean_byte);
    		year_clean = new String(year_clean_byte).strip();
    		redshirt = stream.readInt();
    		stream.readFully(hometown_clean_byte);
    		hometown_clean = new String(hometown_clean_byte).strip();
    		stream.readFully(state_clean_byte);
    		state_clean = new String(state_clean_byte).strip();
    		stream.readFully(country_clean_byte);
    		country_clean = new String(country_clean_byte).strip();
            
    	} catch (IOException e) {
    		System.out.println("Error: couldn't read from binary file.");
    		System.exit(-1);
    	}
    }
    
    public String toString() {
    	return String.valueOf(this.ncaa_id) + "," + this.team + "," + String.valueOf(this.player_id) + "," + this.name + "," + 
    			String.valueOf(this.jersey) + "," + String.valueOf(this.height_ft) + "," + String.valueOf(this.height_in) +
    			"," + this.position_clean + "," + String.valueOf(this.year_clean) + "," + String.valueOf(this.redshirt) +
    			"," + this.hometown_clean + "," + this.state_clean + "," + this.country_clean;
    }
}

