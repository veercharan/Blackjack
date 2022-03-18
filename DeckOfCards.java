/*programmer: Charanvir Singh
Class: CS 145
Date 01/29/2022
purpose - shuffle the cards
this method has the face and suits arrays 
*/

import java.security.SecureRandom;

public class DeckOfCards {
		//random number generator
		private static final SecureRandom randomNumbers = new SecureRandom();
		private static final int NUMBER_OF_CARDS = 52;
	
		private Card[] deck = new Card[NUMBER_OF_CARDS]; //cards referances
		private int currentCard = 0;//index of next cards to be dealt (0-51)
		
		//construtor fills decks of Cards
		public DeckOfCards() {
			String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six",
				"Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
			String[] suits = {"Hearts", "Diamonds","Clubs", "Spades"};
			
			for (int count = 0; count < deck.length; count++) {//for loop begins
				deck[count] = 
					new Card(faces[count % 13], suits [count / 13]);
			}// end of the for loop
		}//end of DeckOfCards constructor
		
		//shuffle deck of Cards with one-pass algorithm
		public void  shuffle() {
			//next class dealCard should start at deck[0] again
			currentCard = 0;
			
			//for each Card, pick another random Car (0-51) and swap them
			
			for (int first = 0; first < deck.length; first++) {
				//select a random number between 0 and 51
				int second = randomNumbers.nextInt(NUMBER_OF_CARDS);
				
				//swap current Card with randomly selected Card
				Card temp = deck[first];
				deck[first]= deck[second];
				deck[second] = temp;				
			}//for loop ends
		}//end of shuffle method
		
		//deal one card
		public Card dealCard() {
			if (currentCard < deck.length) {
				return deck[currentCard++];
			} 
			else {
				return null;
			}
		}
}//end of class DeckOfCards
