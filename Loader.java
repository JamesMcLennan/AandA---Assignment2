import java.io.*;
import java.util.*;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import java.lang.String;

public class Loader{

	protected static boolean loaded = false;
	protected static Character[] characters;
	
	public static Character[] LoadData(String gameFilename) throws IOException{

		if(loaded == true){
			
			// For debug purposes.
			System.out.println("[LOADER] Sent array to player 2.");

			return characters;
		}
		
		// Variables for use:
		int lines = 0;
		int counter = 0;
		String line;

		// For debug purposes.
		System.out.println("[LOADER] Loading Data: " + gameFilename);

		// Creating file;
		File config = new File(gameFilename);

		// Checking if file exists;
		if(!(config.exists())){
			
			// For debug purposes.
			System.out.println("[LOADER] Config file: " + gameFilename + " not found."); 
		}

		// For debug purposes.
		System.out.println("[LOADER] " + gameFilename + " found.");
	
		// Creating Readers;
		BufferedReader config_data = new BufferedReader(new FileReader(config));
		BufferedReader loading_data = new BufferedReader(new FileReader(config));

		// For debug purposes.
		System.out.println("[LOADER] Scanning file...");

		// Checking how many characters there are;
		while((line = config_data.readLine()) != null){
			if(lines % 11 == 0 & lines != 11){		
				counter++;
			}

			lines++;
		}

		// For debug purposes.
		System.out.println("[LOADER] " + gameFilename + ": Characters = " + counter + ", Lines = " + lines);

		// Assigning the array length;
		characters = new Character[counter];

		// For debug purposes.
		System.out.println("[LOADER] Array length set.");

		// For debug purposes.
		System.out.println("[LOADER] Loading Characters....");

		// Reseting for next use;
		boolean cleared = false;
		counter = 0;

		// Checking how many characters there are;
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
			
				// For debug purposes.
				//System.out.println(name + ", " + hairlength + ", " + glasses + ", " + facialhair + ", " + 
						   //eyecolor + ", " + pimples + ", " + hat + ", " + haircolor + ", " +
							// noseshape + ", " + faceshape);

				loading_data.readLine();

				

			}else {

				for(int i = 0; i < 9; i++){
		
					loading_data.readLine();
				}

				cleared = true;
			}
		}

		// For debug purposes.
		System.out.println("[LOADER] Load complete.");
		System.out.println("[LOADER] Sent array to player 1.");



		// Closing the reader/file;
		config_data.close();
		loading_data.close();

		loaded = true;
			
		
		return characters;
	}

}
