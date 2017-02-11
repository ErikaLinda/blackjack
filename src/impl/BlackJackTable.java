package impl;
import api.Table;
import api.Dealer;
import api.Player;
import impl.BlackJackDealer;

import java.util.*;
//import java.util.Collections;
// import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Iterator;

public class BlackJackTable extends Table{
    private double bank = 0;

 
	public BlackJackTable(int numOfPlayers){
        this.dealer = new BlackJackDealer();
        this.players =  new ArrayList<>();

        
        System.out.printf("Number of players %d%n", numOfPlayers);

        for( int i = 0; i < numOfPlayers; i++){
            players.add ( new BlackJackPlayer() );
            System.out.printf("Player i: %d added.%n", i);
        }

        
	}
   
     /*
     * A game is over when there are no players, or no players with
     * money to bet
     */
	public boolean isGameOver(){
        Iterator<Player> it = players.iterator();
        while(it.hasNext()){
            Player tmp = it.next();
            double money= tmp.getMoney();
            System.out.printf("Players money: %f%n.", money);
            if(money < 1){
                it.remove();
                System.out.println("Player removed.");
            }
        }

		return (players.isEmpty()) ? true : false;  //check players bets
        // return false;
	}

    /*
     * A string representation of the table
     */
    public String toString(){
    	String s = " ";
    	return s;

    }

    /*
     * Collect cards from all players at the table. Note that in Black
     * Jack, that also means the dealer themself!
     */
    protected void collectCards(){
        Iterator<Player> it = players.iterator();
        while(it.hasNext()){
            dealer.collectCards(it.next());
        }

        dealer.collectCards((Player)dealer);

        System.out.println("Cards have been collected.");
    }

    /*
     * Deal cards to all players at the table. Note that in Black
     * Jack, that also means the dealer themself!
     */
    protected void dealTable(){
        //deal two cards to each player
        
        Iterator<Player> it = players.iterator();
        while(it.hasNext()){
            Player tmp = it.next();
            dealer.dealCard(tmp);  
            dealer.dealCard(tmp);
        }

        //deal two cards to dealer
        dealer.dealCard((Player)dealer);
        dealer.dealCard((Player)dealer);

        System.out.println("Cards have been dealt.");

    }
    
    /*
     * Collect bets from all players at the table
     */
    protected void collectBets(){
        Iterator<Player> it = players.iterator();
        while(it.hasNext()){
            Player tmp = it.next();
            bank += tmp.wager();
        }
        System.out.printf("Bets have been collected. Bank: %f%n.", bank);
    }

    /*
     * Give each player a turn. Note that in Black Jack, the dealer
     * should have a turn as well!
     */
    protected void playerTurns(){

    }

    /*
     * Evaluate each players hand with respect the rules of the game,
     * and to the dealer. If a player has a winning hand, they should
     * be paid based on their respective information in the wager
     * table.
     */
    protected void playerEvaluations(){

    }
}