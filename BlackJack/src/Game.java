class Game{
	Speler speler1;
	Speler bank;
	
	public Game(Speler speler1, Speler bank) {
		this.speler1 = speler1;
		this.bank = bank;
	}
	
	public Deck cardDeck = new Deck();									//Create new Deck object (Deck.deck is made in the constructor of Deck)
	
	public void start() {
		cardDeck.shuffleCards();										//Shuffle deck.deck
		deal("speler1");												//Bank and player are dealt 2 cards each
		deal("bank");
		deal("speler1");
		deal("bank");
		play();															//Game is commenced
	}
	
	void play() {
		boolean playing = true;											//while playing, the player should get options to play on
		boolean busted = false;   										//When busted, the player should not be allowed to play further
		
		while(playing) {
			printScherm(true);											//Every itteration, the current state is displayed
			if(speler1.punten(false) >21) {								//More than 21 points and the player is busted
				busted = true;
				playing = false;
			}
			if(!busted) {
				System.out.println("K: Kaart, S: Stand");            	//Give the player options when he's not yet busted
				String input = BlackJack.sc.nextLine();
				if(input.toUpperCase().equals("K")){  					//Player is dealt another card
					deal("speler1");
				} else if (input.toUpperCase().equals("S")){			//Player stands; endgame is commenced
					playEndgame();
					playing = false;
					
				}
			}
		}
	}
	
	void deal (String id) {		
		if(id == "speler1") {											//Deal card to the player
			speler1.geefKaart(cardDeck.getDeck().get(0));
			cardDeck.getDeck().remove(0);
		} else if (id == "bank") {										//Deal card to the bank
			bank.geefKaart(cardDeck.getDeck().get(0));				
			cardDeck.getDeck().remove(0);
		}
	}	
	
	//PlayEndgame ensues when the player stands, the bank then draws cards until
	//the bank has >16 points.
	void playEndgame() {
		printScherm(false);												//Second card is now fully visible
		while(bank.punten(false)<17) {									//Deal cards until minimum of 17 is reached
			bank.geefKaart(cardDeck.getDeck().get(0));
			cardDeck.getDeck().remove(0);
			printScherm(false);
		}
		
	}
	
	void printScherm(boolean hideBank) {												//The playing area is printed, including all points and money currently on hand
		System.out.println("############################");
		System.out.println("#                          #");
		System.out.print  ("#          ");
		bank.printKaarten(hideBank);													//Print cards of the bank
		for(int i = 0; i < 16-bank.aantalKaarten() * 3; i++){							//Always print 16 spaces, -3 spaces for every card in the bank's hand
			System.out.print(" ");
		}
		System.out.println("#");
		System.out.print("#                       ");
		System.out.print(String.format("%02d",  bank.punten(hideBank)));				//Print the points of the bank, always 2 spaces wide
		System.out.println(" #");
		System.out.println("#                          #");
		System.out.println("#                          #");
		System.out.print  ("#          ");
		speler1.printKaarten(false);
		for(int i = 0; i < 16 - speler1.aantalKaarten() * 3; i++) {						//Always print 16 space, -3 spaces for every card in the player's hand
			System.out.print(" ");
		}
		System.out.println("#");
		System.out.println("#                          #");
		System.out.print  ("# Geld: ");
		System.out.print(speler1.geld);													//Print the money currently owned by the player
		for(int i = 0; i < (int)(15 - (Math.floor(Math.log10(speler1.geld)))); i++) {   //Calculate howmany spaces should be printed, by taking the log10 of the money
			System.out.print(" ");
		}
		System.out.print(String.format("%02d", speler1.punten(false)));					//Print the points of the player, always fully amount of points (never hide = true)
		System.out.println(" #");
		System.out.println("############################");	
	}
}