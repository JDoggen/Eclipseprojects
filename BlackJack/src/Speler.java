import java.util.ArrayList;
import java.util.List;

class Speler {
	int geld = 500;
	int punten;
	List<Kaart> eigenKaarten = new ArrayList<Kaart>();
	
	
	public int getGeld() {
		return geld;
	}
	
	public void setGeld(int geld) {
		this.geld = geld;
	}
	
	public int getPunten(boolean hide) {
		punten = 0;
		if(!hide) {
			//First, count all non-ace cards
			for(Kaart kaart : eigenKaarten) {
				if(Character.getNumericValue(kaart.getValue()) < 10) {   //chars 2-9 have ASCII codes 50-57
					punten += Character.getNumericValue(kaart.getValue());
				} else if(Character.getNumericValue(kaart.getValue()) > 10) { //Card is a J, Q or K
					punten += 10;
				} 
			}
			
			//Secondly, count all aces
			short aces=0;
			for(Kaart kaart : eigenKaarten) {
				if(Character.getNumericValue(kaart.getValue()) == 10) { //Card is an ace
					aces++;
				}			
			}
			punten += aces;
			if(punten <= 11 && aces >= 1) {    //If there's an ace and points allow; let ace count as 11
				punten += 10;
			}
			return punten;
		} else {
			Kaart kaart = eigenKaarten.get(0);
			if(Character.getNumericValue(kaart.getValue()) < 10) {   //chars 2-9 have ASCII codes 50-57
				punten += Character.getNumericValue(kaart.getValue());
			} else if(Character.getNumericValue(kaart.getValue()) > 10) { //Card is a J, Q or K
				punten += 10;
			} else if(Character.getNumericValue(kaart.getValue()) == 10) {
				punten += 11;
			}
			return punten;
		}
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
	
	public void printKaarten(boolean hide) {
		if(hide) {
			System.out.print(eigenKaarten.get(0).getSuit() + "" + eigenKaarten.get(0).getValue() + " ## ");
		}else {
			for(Kaart kaart : eigenKaarten) {
				System.out.print(kaart.getSuit() + "" + kaart.getValue()+ " ");
			}
		}
	}
	

}