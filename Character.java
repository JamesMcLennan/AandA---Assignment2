public class Character{

	protected String name;
	protected String hairLength;
	protected String glasses;
	protected String facialHair;
	protected String eyeColor;
	protected String pimples;
	protected String hat;
	protected String hairColor;
	protected String noseShape;
	protected String faceShape;
	protected boolean isDown;
	protected Character[] suspects;
	
	public Character(String name, String hairLength, String glasses, String facialHair, String eyeColor, String pimples, String hat, String
		hairColor, String noseShape, String faceShape){

		// Attributes for the character;
		this.name = name;
		this.hairLength = hairLength;
		this.glasses = glasses;
		this.facialHair = facialHair;
		this.eyeColor = eyeColor;
		this.pimples = pimples;
		this.hat = hat;
		this.hairColor = hairColor;
		this.noseShape = noseShape;
		this.faceShape = faceShape;
	
		// Variables to check if down or up;
		boolean isDown = false;
	}

        public boolean setDown(){
		if(isDown == false){
			isDown = true;
		}

		return true;
	}

	public String get(String attribute){

		switch(attribute){

			case "name":
				return name;
			case "hairLength":
				return hairLength;
			case "glasses":
				return glasses;
			case "facialHair": 
				return facialHair;
			case "eyeColor":
				return eyeColor;
			case "pimples":
				return pimples;
			case "hat":
				return hat;
			case "hairColor":
				return hairColor;
			case "noseShape":
				return noseShape;
			case "faceShape":
				return faceShape;
		}

		return "Unknown";
	}

	public boolean isDown(){
		return isDown;
	}
	
	public void assignSuspects(Character[] array) {
		this.suspects = array;
	}
	
	public Character[] getSuspects() {
		return suspects;
	}
	
	public void removeSuspects(Character[] array, Guess currGuess, boolean answer) {
		if(currGuess.getType().equals(Guess.GuessType.Attribute)) {
			if(answer == false) {
				for(int i = 0; i < array.length; i++) {
					if(array[i] != null) {
						if(currGuess.getValue().equals(array[i].get(currGuess.getAttribute()))) {
							System.out.println("The character " + array[i].get("name") + " has been eliminated because it has guess'd values");
							array[i] = null;
						}
					}
				}
			}
			else {
				for(int i = 0; i < array.length; i++) {
					if(array[i] != null) {
						if(!currGuess.getValue().equals(array[i].get(currGuess.getAttribute()))) {
							System.out.println("The character " + array[i].get("name") + " has been eliminated because it does not have guess'd values");
							array[i] = null;
						}
					}
				}
			}
		}
	}

}