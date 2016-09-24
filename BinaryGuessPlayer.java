import java.io.*;

/**
 * Binary-search based guessing player.
 * This player is for task C.
 *
 * You may implement/extend other interfaces or classes, but ensure ultimately
 * that this class implements the Player interface (directly or indirectly).
 */
public class BinaryGuessPlayer implements Player
{

    // VARIABLES FOR USE;
    protected Character[] characters;
    protected Character chosen;

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
    public BinaryGuessPlayer(String gameFilename, String chosenName)
        throws IOException
    {
    	// Load all the data;
		characters = Loader.LoadData(gameFilename);
		
		// Getting the character;
		for(int i = 0; i < characters.length; i++) {

			if((characters[i].get("name")).equals(chosenName)) {
				chosen = characters[i];
				break;
			}
		}	
    } // end of BinaryGuessPlayer()


    public Guess guess() {
    	String guess_attribute;
    	String guess_value;
        
        return new Guess(Guess.GuessType.Person, "", "Placeholder");
    } // end of guess()


	public boolean answer(Guess currGuess) {

        // placeholder, replace
        return false;
    } // end of answer()


	public boolean receiveAnswer(Guess currGuess, boolean answer) {

        // placeholder, replace
        return true;
    } // end of receiveAnswer()

} // end of class BinaryGuessPlayer