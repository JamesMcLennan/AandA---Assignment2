import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * Binary-search based guessing player. This player is for task C.
 *
 * You may implement/extend other interfaces or classes, but ensure ultimately
 * that this class implements the Player interface (directly or indirectly).
 */
public class BinaryGuessPlayer implements Player {

	// VARIABLES FOR USE;
	private Character[] characters;
	protected Character chosen;
	private static ArrayList<String> knownAttr = new ArrayList<String>();
	HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	private static ArrayList<String> valueList = new ArrayList<String>();
	protected static String[] attributes = { "hairLength", "glasses", "facialHair", "eyeColor", "pimples", "hat",
			"hairColor", "noseShape", "faceShape" };
	private static List<String> attList = Arrays.asList(attributes);

	HashMap<String, Integer> countSets = new HashMap<String, Integer>();

	/**
	 * Loads the game configuration from gameFilename, and also store the chosen
	 * person.
	 *
	 * @param gameFilename
	 *            Filename of game configuration.
	 * @param chosenName
	 *            Name of the chosen person for this player.
	 * @throws IOException
	 *             If there are IO issues with loading of gameFilename. Note you
	 *             can handle IOException within the constructor and remove the
	 *             "throws IOException" method specification, but make sure your
	 *             implementation exits gracefully if an IOException is thrown.
	 */
	public BinaryGuessPlayer(String gameFilename, String chosenName) throws IOException {

		// Load all the character data;
		map = Loader.LoadValues(gameFilename);
		characters = Loader.LoadData(gameFilename);

		// Getting the character;
		for (int i = 0; i < characters.length; i++) {

			if ((characters[i].get("name")).equals(chosenName)) {
				chosen = characters[i];
				characters = Loader.LoadData(gameFilename);
				break;
			}
		}
	} // end of BinaryGuessPlayer()

	public Guess guess() {
		int players = 0;

		for (int i = 0; i < characters.length; i++) {
			if (characters[i] != null) {
				players++;
				for(int j = 0; j < attributes.length; j++) {
					String value = attributes[j];
					String attribute = characters[i].get(value);
					String keys = value + " " + attribute;
					if(!countSets.containsKey(keys)) {
						countSets.put(keys, 1);
					}
					else {
						int count = countSets.get(keys);
						count++;
						countSets.put(keys, count);
					}
				}
			}
		}
		
		if(players != 1) {
			//Ideal return
			for (String key : countSets.keySet()) {
				if(countSets.get(key) == players/2 && !knownAttr.contains(key)) {
					String[] split = key.split(" ");
					knownAttr.add(key);
					countSets.clear();
					return new Guess(Guess.GuessType.Attribute, split[0], split[1]);
				}
			}
			
			//if unable to get even split.
			for(String key : countSets.keySet()) {
				if(countSets.get(key) == (players/2) + 1 && !knownAttr.contains(key)) {
					String[] split = key.split(" ");
					knownAttr.add(key);
					countSets.clear();
					return new Guess(Guess.GuessType.Attribute, split[0], split[1]);
				}
			}
		}
		else {
			for(int i = 0; i < characters.length; i++) {
				if(characters[i] != null) {
					countSets.clear();
					return new Guess(Guess.GuessType.Person,"",characters[i].get("name"));
				}
			}
		}

	return new Guess(Guess.GuessType.Person,"","Placeholder");

	} // end of guess()

	public boolean answer(Guess currGuess) {
		if(currGuess.getType().equals(Guess.GuessType.Attribute)) {
			if(currGuess.getValue().equals(chosen.get(currGuess.getAttribute()))) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if(chosen.get("name").equals(currGuess.getValue())) {
				return true;
			}
			else {
				return false;
			}
		}
	} // end of answer()

	public boolean receiveAnswer(Guess currGuess, boolean answer) {
		if(currGuess.getType().equals(Guess.GuessType.Attribute)) {
			if(answer == false) {
				for(int i = 0; i < characters.length; i++) {
					if(characters[i] != null) {
						String attribute = currGuess.getAttribute();
						String value = currGuess.getValue();
						if((characters[i].get(attribute)).equals(value)) {
							characters[i] = null;
						}
					}
				}
			}
			else {
				for(int i = 0; i < characters.length; i++) {
					if(characters[i] != null) {
						if(!currGuess.getValue().equals(characters[i].get(currGuess.getAttribute()))) {
							characters[i] = null;
						}
					}
				}
			}
			return false;
		}
		else {
			if(answer == true) {
				return true;
			}
			else {
				for(int i = 0; i < characters.length; i++) {
					if((characters[i].get("name")).equals(currGuess.getValue())){
						characters[i] = null;
					}

				}
			}
		}
		return false;
	} // end of receiveAnswer()

} // end of class BinaryGuessPlayer