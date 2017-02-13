package impl;
import api.Player;
import api.Hand;
import api.Card;

import impl.BlackJackHand;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;
import java.util.Collections;


public class BlackJackPlayer implements Player{
	private Hand playersHand = new BlackJackHand();
    private String name;
    //value of hand at which player stops requesting cards
    private int threshold = ThreadLocalRandom.current().nextInt(13, 18);
    //initial available money
    private double wallet = ThreadLocalRandom.current().nextDouble(100, 2000);
    

    //automatic constructor for dealer's player
    public BlackJackPlayer(){
        this.name = "Delaer";
    }

    //automatic constructor
    public BlackJackPlayer(int num){
        this.name = "Player " + num;
    }

    //constructor with name prompt
    public BlackJackPlayer(boolean writeName){
        this.askName();
    }

    

    // get user's name
    private void askName(){
        System.out.println("Enter your name: ");
        Scanner scanner = new Scanner(System.in);
        this.name = scanner.next();
        System.out.println(String.format("%s, you are in the game", name ));
    }

	public int compareTo(Player other){
        return ( (Integer) other.getHand().valueOf() ).compareTo((Integer)this.getHand().valueOf());

		// if (this.getHand().valueOf() > other.getHand().valueOf()){
		// 	return 1;
		// }else if (this.getHand().valueOf() < other.getHand().valueOf()){
		// 	return -1;
		// }
		// else{
		// 	return 0;
		// }
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
    	return this.playersHand;
    }

    /*
     * Place a wager
     */
    public double wager(){
        //player places 30% of the wallet or everything if wallet has less than 10
    	double bet = (wallet > 10) ? wallet * 0.3 : wallet;
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
    	return this.wallet;
    }

    /*
     * Return the name of the player
     */
    public String getName(){
    	return this.name;
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