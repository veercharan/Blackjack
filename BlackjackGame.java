/*programmer: Charanvir Singh
Class: CS 145
Date 01/29/2022
purpose - This has the main method
the istructions are also in this class
it ask what amount the player wants to play for and then it acts accordingly
it also calls the reset method to reset the game
there is a do while loop to play the game again and again
*/
import java.util.Scanner;
import java.util.InputMismatchException;

public class  BlackjackGame {

	public static void main(String[] args) {
        final int MIN_TO_PLAY = 50;//minimum amount to play
        final int REWARD = 50;//if you win you get $50 
        int cash;//amount of money you play for
        String userInput;
        Scanner input = new Scanner(System.in);
        displayBlackjackInstructions();//prints out the instructions
        System.out.println("How much cash do you want to play for?(minimum cash is $" + MIN_TO_PLAY + ")");
        cash = input.nextInt();
        input.nextLine();
        if (cash < MIN_TO_PLAY) {//if amount you play is less than $50 you cant play
            System.out.println("Sorry! May not play the game with that amount.");
            System.exit(1);//you exit out 
        } 
        Blackjack blackjack = new Blackjack();
        System.out.println("Your balance is " + cash);//you balance changes after each game

        do {
            blackjack.reset();//reset the game after each play
            playBlackjack(blackjack);
            if (blackjack.isPlayerWon()){//if the player wins add $50 to their cash amount as reward
                cash += REWARD;
                System.out.println("Congratulation! You won $" + REWARD);
                System.out.println("Your new balance is " + cash);
            } else {
                cash -= REWARD;//if they lose takes $50 out
                System.out.println("You lost $" + REWARD);
                System.out.println("Your new balance is " + cash);
            }
            if (MIN_TO_PLAY > cash){//if they dont have the minimum balance to play, stop the game
                System.out.println("Not enough balance to continue another round!");
                break;
            } 
            do {
                System.out.println("Do you want to play a round of Blackjack game (Y/N)?");
                userInput = input.nextLine();
            } while (!userInput.equalsIgnoreCase("Y") && !userInput.equalsIgnoreCase("N"));//prompt again if invalid input
        } while(userInput.equalsIgnoreCase("Y"));//keep playing a if user input is yes
        
        //thanks for playing, end of the game
        System.out.println("Thank you for playing the Blackjack game!");

	}

    public static void displayBlackjackInstructions(){
    	//this method will print out the instructions for the game    	
    	System.out.println("INSTRUCTIONS");
        System.out.println("Welcome to the Blackjack game!");
        System.out.println("The rules are simple.");
        System.out.println("Cards are shuffled and you get five cards and house gets five cards.");
        System.out.println("The game starts by revealing two cards from player hand.");
        System.out.println("The value of the numeric cards is the same as the face value.");
        System.out.println("King, Queen and Jack have 10 value");
        System.out.println("Ace value varies between 1 and 11 depending on which value helps the hand the most.");
        System.out.println("You lose if Your hand value exceeds 21. or ");
        System.out.println("The dealers hand has a greater value than yours at the end of the round");
        System.out.println("You can win by drawing a hand value of 21 on your first two cards, when the dealer does not.");
        System.out.println("If the dealer hand value is greater than 21, you win.");
        System.out.println("So lets begin the game.\n");
    }

    public static void playBlackjack(Blackjack blackjack){//playBlackjack begins
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (!blackjack.isGameEnd()){
            System.out.println();
            System.out.println(blackjack);//print out the cards that are currently revealed
            System.out.println();
            do {
                System.out.println("Please select an option (1 to stay, 2 to hit):");
                try {
                    choice = input.nextInt();
                } catch(InputMismatchException e){//if the input is not an integer
                    continue;
                }
                input.nextLine();    
            } while (choice != 1 && choice != 2);
            if (choice == 1){//if choice 1 they house plays
                blackjack.stay();
            } 
            else{//else reveal another card
                blackjack.hit();
            }
        }
        System.out.println(blackjack);// print cards again after new card is revealed
        System.out.println();
    } //end of playBlackjack 
}

