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

	public String getName(){

		return name;
	}

	public boolean isDown(){
		return isDown;
	}

	public String hairLength(){
		return hairLength;
	}

	public String glasses(){
		return glasses;
	}

	public String facialHair(){
		return facialHair;
	}

	public String eyeColor(){
		return eyeColor;
	}

	public String pimples(){
		return pimples;
	}

	public String hat(){
		return hat;
	}

	public String hairColor(){
		return hairColor;
	}

	public String noseShape(){
		return noseShape;
	}

	public String faceShape(){
		return faceShape;
	}
}
