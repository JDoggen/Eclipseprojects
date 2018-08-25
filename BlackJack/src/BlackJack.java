import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Speler speler1 = new Speler();															//Create a new player
		Speler bank = new Speler();																//Create a new bank, also of class speler
		boolean playing = true;
		
		while(playing) {
			Game game = new Game(speler1, bank);												//Create a new Game object with the two players
			System.out.print("Geld: €");
			System.out.println(speler1.geld);
			System.out.println("Bet: Bet x, Quit: Q");
			boolean validInput = false;
			int inzet = 0;
			while(!validInput) {
				String input = sc.nextLine();
				if(input.toUpperCase().equals("Q")){											//Player can quit anytime in between games
					System.out.println("Bedankt voor het spelen!");
					System.out.println("U gaat met €" + speler1.getGeld() + " naar huis");	
					playing = false;
					validInput = true;
					//break;   Hier een break toevoegen?
				} else if(input.toUpperCase().startsWith("BET ")) {
					try{																		//If player tries to bet
						inzet = Integer.parseInt(input.substring(4, input.length()));			//Parse anything after index 4 to integer
						if(inzet > speler1.getGeld()) {
							System.out.println("Deze inzet is te hoog");						//Bet can't exceed player money
						} else if(inzet < 0) {
							System.out.println("Nee, dat gaan we dus niet doen...");			//.... Can't bet anything negative
						} else {
							validInput = true;													//No other limitations to betting
						}
					} 
					catch(NumberFormatException nfe) {
						System.out.println("Geen geldige inzet");								//If parsing fails; throw an error, betting can be done again
					}
				}
				if(!validInput) {																//If no bet is received, print
					System.out.println("Geen geldige input");
				}
			}
			game.start();																		//Valid input is received; game is commenced, other code waits untill game ends
			if(speler1.punten(false) > 21) {													//Check the endgame score, if player is busted and if the player beat the bank
				System.out.println("Helaas, je bent gebust");
				speler1.geld -= inzet;
			} else if(bank.punten(false) > 21) {
				System.out.println("De bank is gebust, je hebt gewonnen");
				speler1.geld += inzet;
			} else if(bank.punten(false) == speler1.punten(false)) {
				System.out.println("Gelijkspel, je krijgt je inzet terug");
			} else if(bank.punten(false) > speler1.punten(false)) {
				System.out.println("Helaas, de bank heeft gewonnen, je bent je inzet kwijt");
				speler1.geld -= inzet;
			} else if(speler1.eigenKaarten.size() == 2 && speler1.punten(false) == 21) {
				System.out.println("Blackjack!");
				speler1.geld += Math.floor((inzet * 1.5f));
			
			} else {
				System.out.println("Gefeliciteerd, je hebt gewonnen!");
				speler1.geld += inzet;
			}
			System.out.println();
			for(Kaart k: game.cardDeck.deck) {													//Game ended and the deck should be revealed
				System.out.print(k.getSuit());
				System.out.print(k.getValue());
				System.out.print(" ");
			}
			System.out.println();
			speler1.eigenKaarten = new ArrayList<Kaart>();										//Remove cards from both the bank and the player
			bank.eigenKaarten = new ArrayList<Kaart>();
		}
	}
}