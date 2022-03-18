/*programmer: Charanvir Singh
Class: CS 145
Date 01/29/2022
purpose - This class is where the game is actually played
I used stacks to handle the cards given to both players
I used switch case to define the face values
there is an exception if somehow the result is request is requested before the game ends

*/
import java.util.Stack;//to use stack

public class Blackjack{
	//initialize values
    private DeckOfCards cards;
    private Stack<Card> houseCardsStack;
    private Stack<Card> playerCardsStack;
    private Card[] revealedHouseCards;
    private Card[] revealedPlayerCards;
    private int houseScore;
    private int playerScore;
    private boolean gameEnd;

    public Blackjack(){//Blackjack begins
        cards = new DeckOfCards();
        cards.shuffle();//shuffle the cards
        houseCardsStack = new Stack<>();//house stack
        playerCardsStack = new Stack<>();//player stack
        for (int i = 0; i < 5; i++){
        	//push five cards into the stack
            houseCardsStack.push(cards.dealCard());
            playerCardsStack.push(cards.dealCard());   
        }
        //five card array 
        revealedHouseCards = new Card[5];
        revealedPlayerCards = new Card[5];
        revealedPlayerCards[5 - playerCardsStack.size()] = playerCardsStack.pop();
        revealedPlayerCards[5 - playerCardsStack.size()] = playerCardsStack.pop();
        playerScore += getFaceValue(revealedPlayerCards[0]) + getFaceValue(revealedPlayerCards[1]);
        if (playerScore == 21){
            stay();
        } else if (playerScore == 22){  // If two Aces are added
            playerScore = 12;
        }
    }//end of Blackjack  
    
    public void hit(){//if the player says hit reveal another card
        if(!gameEnd){
            revealedPlayerCards[5 - playerCardsStack.size()] = playerCardsStack.pop();
            int faceValue = getFaceValue(revealedPlayerCards[4 - playerCardsStack.size()]);
            // If the face value is Ace of something
            if ((faceValue == 11) && ((playerScore + faceValue) > 21)){
                faceValue = 1;
            }
            playerScore += faceValue;
            if (playerScore > 21){
                gameEnd = true;    
            } 
            else if (playerScore == 21 || playerCardsStack.size() == 0){
                stay();
            }
        }
    }//end of hit method 
    
    public void stay() {//if the player says stay the house reveal cards
        if (!gameEnd){
            while(houseScore < playerScore && houseScore < 21 && houseCardsStack.size() != 0){
                revealedHouseCards[5 - houseCardsStack.size()] = houseCardsStack.pop();
                int faceValue = getFaceValue(revealedHouseCards[4 - houseCardsStack.size()]);   
                // If the face value is Ace of something
                if ((faceValue == 11) && ((houseScore + faceValue) > 21)){
                    faceValue = 1;
                }
                houseScore += faceValue;
            }
            gameEnd = true;
        }
    }  

    public boolean isGameEnd() {//isGameEnd begins
        return gameEnd;
    }//isGameEnd ends

    public boolean isPlayerWon()
    {//isPlayerWon begins
        if (!gameEnd){
            throw new IllegalStateException("Could not specify the winner if the game is not finished");
        }
        if (houseScore > 21 || (playerScore <= 21 && playerScore > houseScore) ){
        	//if the house score is greater than 21, the player wins
        	//if the house score is less than 21 and less than the player, the player wins
            return true;
            
        }
        return false;  
    }//end of isPlayerWon
    
    public void reset(){//reset method begins
    	//set the values to 0 
        houseScore = 0;
        playerScore = 0;
        gameEnd = false;
        cards.shuffle();//shuffle the cards again
        //clear the stacks
        houseCardsStack.clear();
        playerCardsStack.clear();
        for (int i = 0; i < 5; i++){
            houseCardsStack.push(cards.dealCard());
            playerCardsStack.push(cards.dealCard());   
            revealedHouseCards[i] = null;
            revealedPlayerCards[i] = null;
        }
        //only pop if possible 
        revealedPlayerCards[5 - playerCardsStack.size()] = playerCardsStack.pop();
        revealedPlayerCards[5 - playerCardsStack.size()] = playerCardsStack.pop();
        //add for player scores
        playerScore += getFaceValue(revealedPlayerCards[0]) + getFaceValue(revealedPlayerCards[1]);
        if (playerScore == 21){
            stay();
        } else if (playerScore == 22){ // If two Aces are added
            playerScore = 12;
        }
    }//end of reset method

    @Override 
    public String toString(){//this code return the string return
        String result = String.format("House Score = %d%nPlayer Score = %d%n%n<House Cards>%n"
         ,houseScore, playerScore);
        for (int i = 0; i < 5; i++){//for loop begins
            if(revealedHouseCards[i] == null){
                result += "[_________________] ";//print blank spaces if the card is not revealed
            } 
            else {//otherwise print out the name of the card
                result += String.format("[%-17s] ", revealedHouseCards[i]);
            }
        }//end of the for loop
        result += String.format("%n%n<Player Card>%n");
        for (int i = 0; i < 5; i++){//same loop as above but for player instead of house
            if(revealedPlayerCards[i] == null){
                result += "[_________________] ";
            } 
            else {
                result += String.format("[%-17s] ", revealedPlayerCards[i]);
            }
        }//end of the for loop
        return result;
    }

    private static int getFaceValue (Card card){
    	//this method defines the value of each card
        int faceValue;
        switch(card.getFace()){
            case "Ace":
            	//default value is 11 but is changed to 1 when needed
                faceValue = 11;
                break;
            case "Deuce":
                faceValue = 2;
                break;
            case "Three":
                faceValue = 3;
                break;
            case "Four":
                faceValue = 4;
                break;
            case "Five": 
                faceValue = 5;
                break;
            case "Six":
                faceValue = 6;
                break;
            case "Seven":
                faceValue = 7;
                break;
            case "Eight":
                faceValue = 8;
                break;
            case "Nine":
                faceValue = 9;
                break;
            default://default for 10, Jack, Queen and King
                faceValue = 10;
        }
        return faceValue;
    }
}
