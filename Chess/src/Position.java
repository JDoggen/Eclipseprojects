
public class Position {
	private String pos;
	private int xPos;
	private int yPos;

	Position(String pos){
		char[] positions = {'a', 'b', 'c', 'd','e', 'f', 'g', 'h'};
		for(int i = 0 ; i<8; i++) {
			if(positions[i] == pos.charAt(0)) {
				xPos = i;
				break;				
			}
		}
		
		yPos = Character.getNumericValue(pos.charAt(1)-1);
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
}