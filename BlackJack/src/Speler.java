import java.util.ArrayList;
import java.util.List;

class Speler {
	//Speler starts with $500 and an empty hand
	int geld = 500;
	int punten;
	List<Kaart> eigenKaarten = new ArrayList<Kaart>();
	
	//Getters/setters for some fields, also quick method for giving a player a card
	public int getGeld() {
		return geld;
	}
	
	public void setGeld(int geld) {
		this.geld = geld;
	}
	
	public void setPunten(int punten) {
		this.punten = punten;
	}
	
	public int aantalKaarten() {
		return eigenKaarten.size();
	}
	
	public void geefKaart(Kaart kaart) {
		eigenKaarten.add(kaart);
	}
	
	
	//Method for counting and returning punten
	public int punten(boolean hide) {
		punten = 0;
		if(!hide) {                                                                //Count all the cards in the hand
			
			short aces = 0;
			//Count all cards, keep track of the found aces
			for(Kaart kaart : eigenKaarten) {
				if(Character.getNumericValue(kaart.getValue()) < 10) {             //get Numeric value of cards 2-10
					punten += Character.getNumericValue(kaart.getValue());
				} else if(Character.getNumericValue(kaart.getValue()) > 10) {      //Card is a J, Q or K
					punten += 10;
				} else {
					aces ++;                                                       //Else; card is an ace
				}
			}

			punten += aces;
			if(punten <= 11 && aces >= 1) {                                        //If there's an ace and points allow; let ace count as 11
				punten += 10;
			}
			return punten;  
		} else {                                                                   //Count only the first card, as one of the dealer's card is protected
			Kaart kaart = eigenKaarten.get(0); 
			if(Character.getNumericValue(kaart.getValue()) < 10) {                 //Get Numeric value of cards 2-10
				punten += Character.getNumericValue(kaart.getValue());
			} else if(Character.getNumericValue(kaart.getValue()) > 10) {          //Card is a J, Q or K
				punten += 10;
			} else if(Character.getNumericValue(kaart.getValue()) == 10) {         //Card is an ace
				punten += 11;
			}
			return punten;
		}
	}
	
	//Print cards when asked
	public void printKaarten(boolean hide) {                                       //if(hide) makes sure only the first card is printed
		if(hide) {
			System.out.print(eigenKaarten.get(0).getSuit() + "" + eigenKaarten.get(0).getValue() + " ## ");
		}else {
			for(Kaart kaart : eigenKaarten) {
				System.out.print(kaart.getSuit() + "" + kaart.getValue()+ " ");
			}
		}
	}
	

}