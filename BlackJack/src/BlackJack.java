import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Speler speler1 = new Speler();
		Speler bank = new Speler();
		boolean playing = true;
		while(playing) {
			
			Game game = new Game(speler1, bank);
			System.out.print("Geld: €");
			System.out.println(speler1.geld);
			System.out.println("Bet: Bet x, Quit: Q");
			boolean validInput = false;
			int inzet = 0;
			while(!validInput) {
				String input = sc.nextLine();
				if(input.toUpperCase().equals("Q")){
					System.out.println("Bedankt voor het spelen!");
					System.out.println("U gaat met €" + speler1.getGeld() + " naar huis");	
					playing = false;
					validInput = true;
				} else if(input.toUpperCase().startsWith("BET ")) {
					try{
						inzet = Integer.parseInt(input.substring(4, input.length()));
						if(inzet > speler1.getGeld()) {
							System.out.println("Deze inzet is te hoog");
						} else if(inzet < 0) {
							System.out.println("Nee, dat gaan we dus niet doen...");
						} else {
							validInput = true;
						}
					} 
					catch(NumberFormatException nfe) {
						System.out.println("Geen geldige inzet");
					}
				}
				if(!validInput) {
					System.out.println("Geen geldige input");
				}
			}
			game.start();
			if(speler1.getPunten(false) > 21) {
				System.out.println("Helaas, je hebt het spel verloren!");
				speler1.geld -= inzet;
			} else if(bank.getPunten(false) > 21) {
				System.out.println("De bank is gebust, je hebt gewonnen");
				speler1.geld += inzet;
			} else if(bank.getPunten(false) == speler1.getPunten(false)) {
				System.out.println("Gelijkspel, je krijgt je inzet terug");
			} else if(bank.getPunten(false) > speler1.getPunten(false)) {
				System.out.println("Helaas, de bank heeft gewonnen, je bent je inzet kwijt");
				speler1.geld -= inzet;
			} else if(speler1.eigenKaarten.size() == 2 && speler1.getPunten(false) == 21) {
				System.out.println("Blackjack!");
				speler1.geld += Math.floor((inzet * 1.5f));
			
			} else {
				System.out.println("Gefeliciteerd, je hebt gewonnen!");
				speler1.geld += inzet;
			}
			System.out.println();
			for(Kaart k: game.cardDeck.deck) {
				System.out.print(k.getSuit());
				System.out.print(k.getValue());
				System.out.print(" ");
			}
			System.out.println();
			game.cardDeck.shuffleCards();
			speler1.eigenKaarten = new ArrayList<Kaart>();
			bank.eigenKaarten = new ArrayList<Kaart>();
		}
	}
}