class Game{
	Speler speler1;
	Speler bank;
	
	public Game(Speler speler1, Speler bank) {
		this.speler1 = speler1;
		this.bank = bank;
	}
	
	public Deck cardDeck = new Deck();
	
	public void start() {
		cardDeck.shuffleCards();
		deal("speler1");
		deal("bank");
		deal("speler1");
		deal("bank");
		play();
	}
	
	boolean play() {
		boolean playing = true;
		boolean busted = false;
		while(playing) {
			printScherm(true);
			if(speler1.getPunten(false) >21) {
				busted = true;
			}
			if(!busted) {
				System.out.println("K: Kaart, S: Stand");
				String input = BlackJack.sc.nextLine();
				if(input.toUpperCase().equals("K")){
					deal("speler1");
				} else if (input.toUpperCase().equals("S")){
					playEndgame();
					playing = false;
					
				}
			} else {
				System.out.println();
				System.out.println("Helaas, je bent gebust!");
				System.out.println();
				playing = false;
			}
		}
		return true;
	}
	
	void deal (String id) {
		if(id == "speler1") {	
			speler1.geefKaart(cardDeck.getDeck().get(0));
			cardDeck.getDeck().remove(0);
		} else if (id == "bank") {
			bank.geefKaart(cardDeck.getDeck().get(0));
			cardDeck.getDeck().remove(0);
		}
	}	
	
	void playEndgame() {
		printScherm(false);
		while(bank.getPunten(false)<17) {
			bank.geefKaart(cardDeck.getDeck().get(0));
			cardDeck.getDeck().remove(0);
			printScherm(false);
		}
		
	}
	
	void printScherm(boolean hideBank) {
		System.out.println("############################");
		System.out.println("#                          #");
		System.out.print  ("#          ");
		bank.printKaarten(hideBank);
		for(int i = 0; i < 16-bank.aantalKaarten() * 3; i++){
			System.out.print(" ");
		}
		System.out.println("#");
		System.out.print("#                       ");
		System.out.print(String.format("%02d",  bank.getPunten(hideBank)));
		System.out.println(" #");
		System.out.println("#                          #");
		System.out.println("#                          #");
		System.out.print  ("#          ");
		speler1.printKaarten(false);
		for(int i = 0; i < 16 - speler1.aantalKaarten() * 3; i++) {
			System.out.print(" ");
		}
		System.out.println("#");
		System.out.println("#                          #");
		System.out.print  ("# Geld: ");
		System.out.print(speler1.geld);
		for(int i = 0; i < (int)(15 - (Math.floor(Math.log10(speler1.geld)))); i++) {
			System.out.print(" ");
		}
		System.out.print(String.format("%02d", speler1.getPunten(false)));
		System.out.println(" #");
		System.out.println("############################");	
	}
}