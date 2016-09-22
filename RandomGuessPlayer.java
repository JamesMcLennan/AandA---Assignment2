import java.io.*;
import java.util.*;

/**
 * Random guessing player.
 * This player is for task B.
 *
 * You may implement/extend other interfaces or classes, but ensure ultimately
 * that this class implements the Player interface (directly or indirectly).
 */
public class RandomGuessPlayer implements Player
{

    // VARIABLES FOR USE;
    protected Character[] characters;
    protected static String[] attributes = {"hairLength", "glasses", "facialHair", "eyeColor", "pimples",
    		"hat", "hairColor", "noseShape", "faceShape"};
    private static List<String> attList = Arrays.asList(attributes);
    
    //Input Scanner
  	private static final Scanner sc = new Scanner(System.in);

    /**
     * Loads the game configuration from gameFilename, and also store the chosen
     * person.
     *
     * @param gameFilename Filename of game configuration.
     * @param chosenName Name of the chosen person for this player.
     * @throws IOException If there are IO issues with loading of gameFilename.
     *    Note you can handle IOException within the constructor and remove
     *    the "throws IOException" method specification, but make sure your
     *    implementation exits gracefully if an IOException is thrown.
     */
    public RandomGuessPlayer(String gameFilename, String chosenName) throws IOException
    {
	
	// Load all the data;
	characters = Loader.LoadData(gameFilename);	
	//attributes = {"hairLength", };

    } // end of RandomGuessPlayer()


    public Guess guess() {
    	String guess = null;
    	String[] tokens = null;
    	
    	System.out.println("Please enter your guess:\n[!] To guess an attribute: <Attribute> <Value>\n[!] To guess a player: <Player>\n");
    	guess = sc.nextLine();
    	
    	tokens = guess.split(" ");
    	
    	if(attList.contains(tokens[0])) {
    		System.out.println("[!] Player is guessing an Attribute.\n");
    		return new Guess(Guess.GuessType.Attribute, tokens[0], tokens[1]);
    	}
    	else {
    		return new Guess(Guess.GuessType.Person, "", guess);
    	}
    } // end of guess()


    public boolean answer(Guess currGuess) {

        // placeholder, replace
        return false;
    } // end of answer()


	public boolean receiveAnswer(Guess currGuess, boolean answer) {

        // placeholder, replace
        return true;
    } // end of receiveAnswer()

} // end of class RandomGuessPlayer