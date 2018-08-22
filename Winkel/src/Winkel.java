public class Winkel{
	Cadeaubon bon;
	Kledingstuk shirt;

	
	public static void main (String[] args) {
		Winkel winkel = new Winkel();
		Cadeaubon bon = new Cadeaubon(24);
		Kledingstuk shirt = new Kledingstuk("wit", "shirt", 25);
		Kledingstuk nieuwShirt = winkel.koopTshirt(bon, shirt);
		
		if(nieuwShirt != null) {
			System.out.println("Ik heb een nieuwe T-shirt gekocht!");
		} else {
			System.out.println("Ik heb geen nieuw T-Shirt kunnen kopen");
		}	
	}
	
	Kledingstuk koopTshirt(Cadeaubon bon, Kledingstuk stuk) {

		if(bon.geldigheid) {
			if(bon.bedrag >= stuk.prijs) {
				System.out.println("Aankoop gelukt!");
				return stuk;
			} else {
				System.out.println("Het aankoopbedrag is te hoog!");
				return null;
			}
		} else {
			System.out.println("De cadeaubon is niet geldig");
			return null;
		}
	}
}


class Cadeaubon {
	boolean geldigheid;
	int bedrag;
	public Cadeaubon(int bedrag) {
		this.bedrag = bedrag;
		this.geldigheid = true;
	}
}

class Kledingstuk{
	String kleur;
	String soort;
	int prijs;
	
	public Kledingstuk(String kleur, String soort, int prijs) {
		this.kleur = kleur;
		this.soort = soort;
		this.prijs = prijs;
	}
}
