
public class Main {
	/** an array of two elements to store the players' hands */
	private Queue<Card>[] hands;
	private Deck deck;
	public Main() {
		// necessary cast because Java doesn't allow generic type arrays
		hands = (Queue<Card>[]) new Queue[2]; 
		hands[0] = new Queue<Card>();
		hands[1] = new Queue<Card>();
		deck = new Deck();
	}
	
	/**
	 * Gives each hand 26 cards
	 */
	public void deal() {
		deck.shuffle();
		for (int i = 0; i < 26; i++) {
			hands[0].add(deck.deal());
			hands[1].add(deck.deal());
		}
	}
	
	/** Plays one round of war. In a round, top cards in each player's
	 * hand are compared; the winner gets both cards and puts them at 
	 * the bottom of their deck. If the two cards are the same, we have 
	 * a War, so we call playWar(). 
	 * Plays one round of war: 
	 */
	public void playRound() {
		System.out.println(hands[0].peek() + " vs " + hands[1].peek());
		if (hands[0].peek().compareTo(hands[1].peek()) > 0) {
			System.out.println("Player 1 gets both cards!");
			hands[0].put(hands[1].remove());
			hands[0].put(hands[0].remove());
		}
		else if (hands[1].peek().compareTo(hands[0].peek()) > 0) {
			System.out.println("Player 2 gets both cards!");
			hands[1].put(hands[0].remove());
			hands[1].put(hands[1].remove());
		} else { // WAR!
			Stack<Card> pile = new Stack<Card>();
			Card curUp1 = hands[0].remove(); // the current up card of player 1
			Card curUp2 = hands[1].remove(); // the current up card of player 2
			// put the current up cards in the pile
			pile.put(curUp1);
			pile.put(curUp2);
			// WAR while the up cards are equal and both hands are not empty
			while (curUp1.compareTo(curUp2) == 0) 
					&& !(hands[0].isEmpty() && hands[1].isEmpty())) {
				System.out.println("WAR!");
				/* each player puts down 4 cards (or less, if they don't 
				 * have 4 cards), with the last one face up */
				for (int i = 0; i < 3; i++) { 
					if (!hands[0].isEmpty()) {
						pile.put(hands[0].peek());
						curUp1 = hands[0].remove();
					} 
					if (!hands[1].isEmpty()) {
						pile.put(hands[1].peek());
						curUp2 = hands[1].remove();
					}
				}
				System.out.println(curUp1 + " vs " + curUp2);
			}
			if (curUp1.compareTo(curUp2) > 0) { // player 1 wins war
				addToHand(pile, hands[0]);
				System.out.println("Player 1 wins the war!");
			} else if (curUp2.compareTo(curUp1) > 0) { // player 2 wins war
				addToHand(pile, hands[1]);
				System.out.println("Player 2 wins the war!");
			}
		}
		System.out.println("Player 1: " + hands[0].size() + " cards," 
				+ "Player 2: " + hands[1].size() + " cards");
		System.out.println();
		
	}
	
	/**
	 * Add all the cards in pile to the hand.
	 * @param pile The pile containing the cards to be added.
	 * @param hand The hand to add the cards in the pile to.
	 */
	private void addToHand(Stack<Card> pile, Queue<Card> hand) {
		for (Card c : pile) {
			hand.put(c);
		}
	}
	
	public void playWar() {
		System.out.println("Welcome to the Game of War!");
		System.out.println("Dealing the cards ...");
		deal();
		System.out.println();
		while (!hands[0].isEmpty() && !hands[1].isEmpty()) {
			playRound();
		}
		// check for winner (or tie)
		if (hands[0].isEmpty() && hands[1].isEmpty()) {
			System.out.println("The winner is... a tie!");
		} else if (hands[0].isEmpty()) {
			System.out.println("The winner is... Player 2!");
		} else if (hands[1].isEmpty()) {
			System.out.println("The winner is... Player 1!");
		}
	}
	
	public static void main(String[] args) {
		Main runner = new Main();
		runner.playWar();
	}
	
}
