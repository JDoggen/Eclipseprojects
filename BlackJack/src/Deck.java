import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Deck{
	char[] suits = {'H', 'K', 'R', 'S'};
	char[] values = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
	List<Kaart> deck = new ArrayList<Kaart>();
	
	public Deck() {
		createNewDeck();
	}
	
	public void createNewDeck() {
		deck = new ArrayList<Kaart>();
		for(char c : suits) {
			for(char d : values) {
				deck.add(new Kaart(c, d));
			}
		}
	}
	
	List<Kaart> getDeck(){
		return deck;
	}
	
	public void shuffleCards() {
		Collections.shuffle(deck);
	}	
}