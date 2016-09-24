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
    protected Character chosen;

    protected String[] attributes = {"hairLength", "glasses", "facialHair", "eyeColor", "pimples",
    		"hat", "hairColor", "noseShape", "faceShape"};

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

	// Getting the character;
	for(int i = 0; i < characters.length; i++){

		if((characters[i].get("name")).equals(chosenName)){

			chosen = characters[i];
			break;
		}

	}	


    } // end of RandomGuessPlayer()


    public Guess guess() {

	// Variables for use;
	String atr_guess;
	String val_guess = null;
	int char_guess;  

	// Random for random choice;
	Random random = new Random();

	// Getting the attribute wanted.
	atr_guess = attributes[random.nextInt(attributes.length) + 1];


	// Truth value;
	boolean validguess = false;

	while(validguess == false){

		// Assigning values;
		char_guess = random.nextInt(characters.length) + 1;
	
		// Check if the character is eliminated;
		if(characters[char_guess].isDown()){

			// Try again for guess;
			continue;
		}else{

			val_guess = characters[char_guess].get(atr_guess);
			validguess = true;
		}
	}
	
        return new Guess(Guess.GuessType.Attribute, atr_guess, val_guess);

    } // end of guess()


    public boolean answer(Guess currGuess) {

        // Checking the guess type;
	// If attribute guess;
	if((currGuess.getType()).equals(Guess.GuessType.Attribute)){

		if((currGuess.getValue()).equals(chosen.get(currGuess.getAttribute()))){
	
			return true;
				
		}else {

			return false;

		}

	}else {

		if((chosen.get("name")).equals(currGuess.getValue())){

			return true;
		
		}else {

			return false;
		}

	}

    } // end of answer()


    public boolean receiveAnswer(Guess currGuess, boolean answer) {

	// Checking type of guess.
	if((currGuess.getType()).equals(Guess.GuessType.Attribute)){

		if(answer == true){

			// Checking all the stuff.
			for(int i = 0; i < characters.length; i++){

				if((characters[i].get(currGuess.getAttribute())).equals(currGuess.getValue())){

					characters[i].setDown();					
				}

			}

		}

		return false;

	}else {

		if((chosen.get("name")).equals(currGuess.getValue())){

			return true;
		
		}else {

			return false;
		}

	}


    } // end of receiveAnswer()

} // end of class RandomGuessPlayer
