import java.io.*;
import java.util.*;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import java.lang.String;

public class Loader{
	
	//Array to store attribute types.
	protected static String[] attributes = {"hairLength", "glasses", "facialHair", "eyeColor", "pimples",
    		"hat", "hairColor", "noseShape", "faceShape"};
    private static List<String> attList = Arrays.asList(attributes);
    
	public static Character[] LoadData(String gameFilename) throws IOException{
		
		// Variables for use:
		int lines = 0;
		int counter = 0;
		String line;

		// For storage;
		Character[] characters;
		// Creating file;
		File config = new File(gameFilename);

		// Checking if file exists;
		if(!(config.exists())){
			System.out.println("[!] Game file not found!");
			return null;
		}

		// Creating Readers;
		BufferedReader config_data = new BufferedReader(new FileReader(config));
		BufferedReader loading_data = new BufferedReader(new FileReader(config));

		// Checking how many characters there are;
		while((line = config_data.readLine()) != null){
			if(lines % 11 == 0 & lines != 11){		
				counter++;
			}

			lines++;
		}

		// Assigning the array length;
		characters = new Character[counter];

		// Reseting for next use;
		boolean cleared = false;
		counter = 0;

		// Reading and storing the characters;
		while((line = loading_data.readLine()) != null){
			if(cleared == true){	

				String[] temp;

				// Getting the name;
				String name = line;	
				
				// Getting the hairlength;
				temp = (loading_data.readLine()).split("\\s+");
				String hairlength = temp[1];
	
				// Getting glasses;
				temp = (loading_data.readLine()).split("\\s+");
				String glasses = temp[1];

				// Getting facialhair;
				temp = (loading_data.readLine()).split("\\s+");
				String facialhair = temp[1];	

				// Getting eyecolor;
				temp = (loading_data.readLine()).split("\\s+");
				String eyecolor = temp[1];	

				// Getting pimples;
				temp = (loading_data.readLine()).split("\\s+");
				String pimples = temp[1];	

				// Getting hat;
				temp = (loading_data.readLine()).split("\\s+");
				String hat = temp[1];	

				// Getting haircolor;
				temp = (loading_data.readLine()).split("\\s+");
				String haircolor = temp[1];	

				// Getting noseshape;
				temp = (loading_data.readLine()).split("\\s+");
				String noseshape = temp[1];

				// Getting noseshape;
				temp = (loading_data.readLine()).split("\\s+");
				String faceshape = temp[1];	

				// Creating the actual character;
				Character character = new Character(name, hairlength, glasses, facialhair, eyecolor, pimples, hat,
								haircolor, noseshape, faceshape);

				characters[counter] = character;
				counter++;

				loading_data.readLine();

			}else {

				for(int i = 0; i < 9; i++){
		
					loading_data.readLine();
				}

				cleared = true;
			}
		}

		// Closing the reader/file;
		config_data.close();
		loading_data.close();
		
		return characters;
	}

	public static HashMap<String, ArrayList<String>> LoadValues(String gameFilename) throws IOException {
		// Variables for use:
		String line;
		String key = null;
		boolean players = false;
		HashMap<String, ArrayList<String>> map;

		// Creating file;
		File config = new File(gameFilename);

		// Checking if file exists;
		if(!(config.exists())){
			System.out.println("[LOADER] Config file: " + gameFilename + " not found."); 
		}
		
		map = new HashMap<String, ArrayList<String>>();
	
		// Creating Readers;
		BufferedReader config_data = new BufferedReader(new FileReader(config));
		
		while((line = config_data.readLine()) != null){		
			String[] tokens = line.split(" ");
			ArrayList<String> valueList = new ArrayList<String>();
			
			for(String token : tokens) {
				//If the token scanned is an Attribute, assign key to token.
				if(attList.contains(token)) {
					key = token;
				}
				
				//If player is scanned, stop loader.
				else if(token.equals("P1")) {
					players = true;
					break;
				}
				
				//Add values to list.
				else {
					valueList.add(token);
				}
			}
			
			//Terminate scan as player is loaded into an array.
			if(players == true) {
				break;
			}
			
			//If the map does not already contain the key, put key/value into map.
			if(!map.containsKey(key)) {
				map.put(key, valueList);
			}
		}
		
		config_data.close();
		return map;
	}
}