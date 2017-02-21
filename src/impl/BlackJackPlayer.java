package impl;
import api.Player;
import api.Hand;
import api.Card;
import api.BettingStrategy;
import api.HittingStrategy;

import impl.BlackJackHand;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;


public class BlackJackPlayer implements Player{
	private Hand playersHand = new BlackJackHand();
    private String name;

    //initial available money
    private double wallet = ThreadLocalRandom.current().nextDouble(200, 2000);

    //betting and hitting strategy
    protected BettingStrategy bettingStrategy;
    protected HittingStrategy hittingStrategy;
    

    //constructor for dealer's player
    public BlackJackPlayer(){
        name = "Dealer";
    }

    //bot constructor
    public BlackJackPlayer(int num, BettingStrategy b, HittingStrategy h){
        name = "Player " + num;
        bettingStrategy = b;
        hittingStrategy = h;
    }

    //constructor with name prompt
    public BlackJackPlayer(boolean writeName, BettingStrategy b, HittingStrategy h){
        this.askName();
        bettingStrategy = b;
        hittingStrategy = h;
    }


    // get user's name
    private void askName(){
        System.out.println("Enter your name: ");
        Scanner scanner = new Scanner(System.in);
        name = scanner.next();
        // System.out.println(String.format("%s, you are in the game", name ));
    }

	public int compareTo(Player other){
        return ( (Integer) other.getHand().valueOf() ).compareTo( (Integer) this.getHand().valueOf() );
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
        return hittingStrategy.hit(playersHand);
    }
}