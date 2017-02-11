package impl;
import api.Table;
import api.Dealer;
import api.Player;
import api.Hand;
import impl.BlackJackDealer;

import java.util.*;
//import java.util.Collections;
// import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public class BlackJackTable extends Table{
    private Map<Player, Double> bank = new HashMap<>();
    

 
	public BlackJackTable(int numOfPlayers){
        
        this.players =  new ArrayList<>();
        this.dealer = new BlackJackDealer();

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
        for (Player player : players) {
            dealer.collectCards(player);
            // System.out.println(s);
        }  

        // Iterator<Player> it = players.iterator();
        // while(it.hasNext()){
        //     dealer.collectCards(it.next());
        // }

        dealer.collectCards((Player)dealer);

        System.out.println("Cards have been collected.");
    }

    /*
     * Deal cards to all players at the table. Note that in Black
     * Jack, that also means the dealer themself!
     */
    protected void dealTable(){
        //deal two cards to each player
        for(Player player : players){
            dealer.dealCard(player);  
            dealer.dealCard(player);
        }
        // Iterator<Player> it = players.iterator();
        // while(it.hasNext()){
        //     Player tmp = it.next();
        //     dealer.dealCard(tmp);  
        //     dealer.dealCard(tmp);
        // }

        //deal two cards to dealer
        dealer.dealCard((Player)dealer);
        dealer.dealCard((Player)dealer);

        System.out.println("Cards have been dealt.");

    }
    
    /*
     * Collect bets from all players at the table
     */
    protected void collectBets(){
        for(Player player : players){
            bank.put(player, player.wager());
            double tmp = bank.get(player);
            System.out.printf("Current wager for a player: %f.%n", tmp);
        }
        // System.out.println("Size of the map: "+ bank.size());
        
        // Iterator<Player> it = players.iterator();
        // while(it.hasNext()){
        //     Player player = it.next();
        //     double tmp =0;//need to take it out
        //     tmp+= player.wager();
        // }
        // System.out.printf("Bets have been collected. Bank: %f.%n", bank);
    }

    /*
     * Give each player a turn. Note that in Black Jack, the dealer
     * should have a turn as well!
     */
    protected void playerTurns(){
        // players take turns
        for (Player player : players){
            while(player.requestCard()){
                dealer.dealCard(player);
                System.out.println("A player added extra card.");
            }
        }
        // Iterator<Player> it = players.iterator();
        // while(it.hasNext()){
        //     Player player = it.next();
        //     if(player.requestCard()){
                
        //     }
        // }
        // dealer takes turn
        while( ((Player)dealer).requestCard() ){
            dealer.dealCard((Player)dealer); 
            System.out.println("The dealer added extra card.");
        }
        System.out.println("Players took turns.");

    }

    /*
     * Evaluate each players hand with respect the rules of the game,
     * and to the dealer. If a player has a winning hand, they should
     * be paid based on their respective information in the wager
     * table.
     */
    protected void playerEvaluations(){
        // for(Player p : players){
        //     System.out.printf("NoSort Player: %s, hand: %d.%n", p.getName(), p.getHand().valueOf());
        // }

        //check case for multiple winners

        //sort players by their hands in descending order
        Collections.sort(players);
        
        for(Player p : players){
            System.out.printf("Player: %s, hand: %d.%n", p.getName(), p.getHand().valueOf());
        }

        Hand dealersHand = dealer.getHand();
        System.out.printf("Dealers final hand: %d.%n", dealersHand.valueOf());

        for(Player p : players){
            Hand h = p.getHand();
            if(h.isValid()){
                System.out.printf("Player: %s bet: %f, recived: %f.%n", p.getName(), bank.get(p), this.wonAmount(h, dealersHand));
                p.payOut(this.wonAmount(h, dealersHand));
                return;
            }
        }
        return;
    }

    private double wonAmount(Hand player, Hand dealer){
        double gain = 0;

        //blackjack
        if(player.isWinner()){
            // player is paid 3:2 if dealer doesn't have blackjack
            // and 1:1 if dealer also has blackjack
            gain += (dealer.isWinner()) ? bank.get(player) * 2 : bank.get(player) * 2.5;    

        }
        //better hand thna the dealer's
        else if( !dealer.isValid() || dealer.valueOf() < player.valueOf()){
            // return;
        }

        return gain;

    }


}