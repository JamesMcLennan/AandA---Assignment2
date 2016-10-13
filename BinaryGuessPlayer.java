import java.io.*;
import java.util.*;
import java.math.*;

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
    protected static String[] attributes = { "hairLength", "glasses", "facialHair", "eyeColor", "pimples", "hat",
    "hairColor", "noseShape", "faceShape" };
    
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
    protected int totalPlayers = 0;
    
    public BinaryGuessPlayer(String gameFilename, String chosenName) throws IOException {
        
        // Load all the character data;
        map = Loader.LoadValues(gameFilename);
        characters = Loader.LoadData(gameFilename);
        
        // Getting the character;
        for (int i = 0; i < characters.length; i++) {
            if ((characters[i].get("name")).equals(chosenName)) {
                chosen = characters[i];
                characters = Loader.LoadData(gameFilename);
            }
            totalPlayers++;
        }
        
    } // end of BinaryGuessPlayer()
    
    public Guess guess() {
        int players = 0;
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] != null) {
                players++;
                for (int j = 0; j < attributes.length; j++) {
                    String value = attributes[j];
                    String attribute = characters[i].get(value);
                    String keys = value + " " + attribute;
                    if (!countSets.containsKey(keys)) {
                        countSets.put(keys, 1);
                    } else {
                        int count = countSets.get(keys);
                        count++;
                        countSets.put(keys, count);
                    }
                }
            }
        }
        
        // Ideal return
        if (players != 1) {
            
            int[] attribOccur = new int[players]; //Array to store attribute occurences.
            int countValue;
            
            for (String key : countSets.keySet()) {
                countValue = countSets.get(key);
                for(int i = 0; i < attribOccur.length; i++) {
                    if(attribOccur[i] == 0) {
                        attribOccur[i] = countValue; //Store each value into element position in array
                        i = attribOccur.length;
                    }
                }
            }
            
            int target = players/2;
            int distance = Math.abs(attribOccur[0] - target); //distance from number.
            int idx = 0;
            
            //Locate the distance of the number wanted (Closet number to target)
            for(int i = 0; i < attribOccur.length; i++) {
                int cdistance = Math.abs(attribOccur[i] - target);
                if(cdistance < distance) {
                    idx = i;
                    distance = cdistance;
                }
            }
            
            // Ideal return when target is an element.
            for (String key : countSets.keySet()) {
                if (countSets.get(key).equals(target) && !knownAttr.contains(key)) {
                    String[] split = key.split(" ");
                    knownAttr.add(key);
                    countSets.clear();
                    return new Guess(Guess.GuessType.Attribute, split[0], split[1]);
                }
                
            }
            
            //If unable to locate target, find closest number.
            for (String key : countSets.keySet()) {
                if (countSets.get(key).equals(attribOccur[idx]) && !knownAttr.contains(key)) {
                    String[] split = key.split(" ");
                    knownAttr.add(key);
                    countSets.clear();
                    return new Guess(Guess.GuessType.Attribute, split[0], split[1]);
                }
            }
            
        } else {
            for (int i = 0; i < characters.length; i++) {
                if (characters[i] != null) {
                    countSets.clear();
                    return new Guess(Guess.GuessType.Person, "", characters[i].get("name"));
                }
            }
        }
        
        return null;
    } // end of guess()
    
    public boolean answer(Guess currGuess) {
        if (currGuess.getType().equals(Guess.GuessType.Attribute)) {
            if (currGuess.getValue().equals(chosen.get(currGuess.getAttribute()))) {
                return true;
            } else {
                return false;
            }
        } else {
            if (chosen.get("name").equals(currGuess.getValue())) {
                return true;
            } else {
                return false;
            }
        }
    } // end of answer()
    
    public boolean receiveAnswer(Guess currGuess, boolean answer) {
        if (currGuess.getType().equals(Guess.GuessType.Attribute)) {
            if (answer == false) {
                for (int i = 0; i < characters.length; i++) {
                    if (characters[i] != null) {
                        String attribute = currGuess.getAttribute();
                        String value = currGuess.getValue();
                        if ((characters[i].get(attribute)).equals(value)) {
                            characters[i] = null;
                        }
                    }
                }
            } else {
                for (int i = 0; i < characters.length; i++) {
                    if (characters[i] != null) {
                        if (!currGuess.getValue().equals(characters[i].get(currGuess.getAttribute()))) {
                            characters[i] = null;
                        }
                    }
                }
            }
            return false;
        } else {
            if (answer == true) {
                return true;
            } else {
                for (int i = 0; i < characters.length; i++) {
                    if ((characters[i].get("name")).equals(currGuess.getValue())) {
                        characters[i] = null;
                    }
                    
                }
            }
        }
        return false;
    } // end of receiveAnswer()
    
} // end of class BinaryGuessPlayer