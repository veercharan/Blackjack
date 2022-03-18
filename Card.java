/*programmer: Charanvir Singh
Class: CS 145
Date 01/29/2022
purpose - Card class represents a playing card
*/

public class Card {
	private final String face;//face of a card	
	private final String suit;//suit of a card
    
	//two-argument constructor initializes card's face and suits
	public Card (String cardFace, String cardSuit) {
		this.face = cardFace;
		this.suit = cardSuit;
	}//end of Card Constructor

    public String getFace(){
        return face;// return face separately
    }

    public String getSuit(){// return suit separately
        return suit;
    }

	public String toString() {
		return face + " of " + suit;//return them as a part of a string
	}
}//end of Card class	