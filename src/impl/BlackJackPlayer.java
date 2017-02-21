package impl;
import api.Player;
import api.Hand;
import api.Card;
import api.BettingStrategy;
import api.HittingStrategy;

import impl.BlackJackHand;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;
import java.util.Collections;


public class BlackJackPlayer implements Player{
	private Hand playersHand = new BlackJackHand();
    private String name;
    //value of hand at which player stops requesting additional cards
    private int threshold = ThreadLocalRandom.current().nextInt(13, 18);
    //initial available money
    private double wallet = ThreadLocalRandom.current().nextDouble(200, 2000);

    //betting strategy
    BettingStrategy bettingStrategy;
    //hitting strategy
    HittingStrategy hittingStrategy;
    

    //constructor for dealer's player
    public BlackJackPlayer(){
        this.name = "Dealer";
    }

    //bot constructor
    public BlackJackPlayer(int num, BettingStrategy b){
        this.name = "Player " + num;
        this.bettingStrategy = b;
    }

    //constructor with name prompt
    public BlackJackPlayer(boolean writeName, BettingStrategy b){
        this.askName();
        this.bettingStrategy = b;
    }

    

    // get user's name
    private void askName(){
        System.out.println("Enter your name: ");
        Scanner scanner = new Scanner(System.in);
        this.name = scanner.next();
        // System.out.println(String.format("%s, you are in the game", name ));
    }

	public int compareTo(Player other){
        return ( (Integer) other.getHand().valueOf() ).compareTo((Integer)this.getHand().valueOf());
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
        double bet = bettingStrategy.bet(wallet);
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
    	return (playersHand.valueOf() < threshold);
    }
}