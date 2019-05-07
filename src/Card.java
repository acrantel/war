/* Here are the Card and Deck classes. Note that Deck is not written super 
* efficiently, but it does give an example of HashSet and an iterator. While we 
* didn't quite get to these topics in class, they can be useful and complete 
* understanding of these isn’t essential to complete the project . 
*/

public class Card implements Comparable<Card> {
	private String mySymbol;
	private int myRank;
	private char mySuit;

	public Card(char rank, char suit) {
		myRank = rank;
		mySuit = suit;
		mySymbol = "" + rank + suit;
	}

	public int getRank() {
		return myRank;
	}
	public char getSuit() {
		return mySuit;
	}

 	public String getSymbol() {
		return mySymbol;
	}

	private int convertRank() {
		return 14 - "AKQJT98765432".indexOf(myRank);
	}

	public int compareTo(Card card) {
		return (convertRank() - card.convertRank());
	}

	public boolean equals(Card card) {
	 return (mySymbol.equals(card.getSymbol()));
	}

	public String toString() {
		return mySymbol;
	}
}
