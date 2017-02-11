package impl;
import api.Player;
import api.Hand;
import api.Card;

import impl.BlackJackHand;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;


public class BlackJackPlayer implements Player{
	private Hand playersHand = new BlackJackHand();
    private String name;
    //value of hand at which player stops requesting cards
    private int threshold = ThreadLocalRandom.current().nextInt(13, 18);
    //initial available money
    private double wallet = ThreadLocalRandom.current().nextDouble(100, 2000);
    
    //constructor
    public BlackJackPlayer(){
        // prompt user to enter their name
        // this.askName();
    }
    

    // get user's name
    private void askName(){
        System.out.println("Enter your name: ");
        Scanner scanner = new Scanner(System.in);
        this.name = scanner.next();
        System.out.println(String.format("%s, you are in the game", name ));
    }

	public int compareTo(Player other){
		if (this.getMoney() > other.getMoney()){
			return 1;
		}else if (this.getMoney() < other.getMoney()){
			return -1;
		}
		else{
			return 0;
		}
    }

	 /*
     * Receive a card
     */
    public void receive(Card card){
    	playersHand.addCard(card);	
    }

    /*
     * Return the current hand to the caller
     */
    public Hand getHand(){
    	return playersHand;
    }

    /*
     * Place a wager
     */
    public double wager(){
    	double bet = wallet * 0.3;
    	wallet -= bet;
    	return bet;
    }

    /*
     * Give a player money (upon winning a round)
     */
    public void payOut(double money){
    	wallet += money;
    }

    /*
     * Return the amount of money currently available to the player
     */
    public double getMoney(){
    	return wallet;
    }

    /*
     * Return the name of the player
     */
    public String getName(){
    	return name;
    }

    /*
     * Whether the player would like to request a card: true indicates
     * that they would (equivalent to a 'hit' in Black Jack); false
     * indicates they would not.
     */
    public boolean requestCard(){
    	return (playersHand.valueOf() < threshold) ? true : false;
    }
}