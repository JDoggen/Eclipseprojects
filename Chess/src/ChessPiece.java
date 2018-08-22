
class ChessPiece {
	
	private String name;
	private String color;
	private String imageURL;
	private Position pos;

	ChessPiece(String name, String color, String pos){
		
		this.name = name;
		this.color = color;
		this.pos = new Position(pos);
		imageURL = "/Images/";
		switch(color) {
			case ("Black") : imageURL += "B";
				break;
			case ("White") : imageURL += "W";
				break;
		}
		
		switch(name) {
			case ("Bisshop") : imageURL += "B";
				break;
			case ("King") : imageURL += "K";
				break;
			case ("Knight") : imageURL += "Kn";
				break;
			case ("Pawn") : imageURL += "P";
				break;
			case ("Queen") : imageURL += "Q";
				break;
			case ("Rook") : imageURL += "R";
				break;
		}
		
		imageURL += ".png";
	}
	
	public Position getPos() {
		return pos;
	}
	public int getXPos() {
		return pos.getXPos();
	}
	public int getYPos() {
		return pos.getYPos();
	}
	public String getImageURL() {
		return imageURL;
	}
	public String getName() {
		return name;
	}
}
