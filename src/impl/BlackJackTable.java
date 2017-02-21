/******************
Erika Linda Zogla
Software Engineering
Prof. Jerome White
February 13, 2016

Blackjack

This implementation of blackjack can run for any number of players.
It can also run with automatically generated player names or with 
names entered by the user in the command line (see BlackJackTable constructor below).

********************/

package impl;
import api.Table;
import api.Player;
import api.Hand;
import api.BettingStrategy;
import api.HittingStrategy;

import impl.BlackJackDealer;
import impl.ProportionalBet;
import impl.HighBet;
import impl.DealersHit;
import impl.CautiousHit;
import impl.OptimisticHit;

import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;

public class BlackJackTable extends Table{
    // list of all available betting strategies
    private List<BettingStrategy> betStr = new ArrayList<BettingStrategy>() {{
        add(new ProportionalBet());
        add(new HighBet());
    }};

    // list of all available hitting strategies
    private List<HittingStrategy> hitStr = new ArrayList<HittingStrategy>() {{
        add(new DealersHit());
        add(new CautiousHit());
        add(new OptimisticHit());
    }};

    // constructor
	public BlackJackTable(int numOfPlayers){
        
        this.players = new ArrayList<Player>();
        this.dealer = new BlackJackDealer();
        this.wagers = new HashMap<>();


        for( int i = 0; i < numOfPlayers; i++){
            BettingStrategy bs = betStr.get( (Integer) (i % betStr.size()) );
            HittingStrategy hs = hitStr.get( (Integer) (i % hitStr.size()) );

            //construct bot players with betting and hitting strategies 
            players.add ( new BlackJackPlayer(i, bs, hs));

            // users enter player names
            // players.add ( new BlackJackPlayer(true, bs, hs) ); 
        }

        System.out.printf("Number of players %d.%n", numOfPlayers);
	}
   
     /*
     * A game is over when there are no players, or no players with
     * money to bet
     */
	public boolean isGameOver(){
        if(players.size() == 0){
            return true;
        }else {
            Iterator<Player> it = players.iterator();
            while(it.hasNext()){
                Player tmp = it.next();
                if(tmp.getMoney() < 1){
                    it.remove();}
            }

    		return (players.isEmpty()) ? true : false;  //check players' bets
        }
    }

    /*
     * A string representation of the table
     */
    public String toString(){
        String nl = System.getProperty("line.separator");
        int playersLeft = players.size();
        String table = "";

        if(playersLeft == 1){
            table += "There is " + playersLeft + " player at the table with the following money: ";
            for(Player p : players){
                table += nl + p.getName() + ": " + p.getMoney();
            }
        }else if (playersLeft > 1){
            table += "There are " + playersLeft + " players at the table with the following money: ";
            for(Player p : players){
                table += nl + p.getName() + ": " + p.getMoney();
            }
        }
        
    	return table += "." + nl;
 
    }

    /*
     * Collect cards from all players at the table. Note that in Black
     * Jack, that also means the dealer themself!
     */
    protected void collectCards(){
        for (Player player : players) {
            dealer.collectCards(player);
        } 

        dealer.collectCards((Player)dealer);

        System.out.println("Cards have been collected.");
    }

    /*
     * Deal cards to all players at the table. Note that in Black
     * Jack, that also means the dealer themself!
     */
    protected void dealTable(){
        // shuffle the deck
        dealer.shuffle();

        //deal two cards to each player
        for(Player player : players){
            dealer.dealCard(player); 
            dealer.dealCard(player);
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
        for(Player player : players){
            wagers.put(player, player.wager());
        }
        System.out.println("Bets have been collected.");
    }

    /*
     * Give each player a turn. Note that in Black Jack, the dealer
     * should have a turn as well!
     */
    protected void playerTurns(){
        // players take turns and add as many cards as they wish
        for (Player player : players){
            while(player.requestCard()){
                dealer.dealCard(player);
            }
        }
        
        // dealer takes turn
        while( ((Player)dealer).requestCard() ){
            dealer.dealCard((Player)dealer); 
        }
        System.out.println("Players and dealer took turns.");
    }

    /*
     * Evaluate each players hand with respect the rules of the game,
     * and to the dealer. If a player has a winning hand, they should
     * be paid based on their respective information in the wager
     * table.
     */
    protected void playerEvaluations(){        
        Hand dealersHand = dealer.getHand();

        //sort players by their hands in descending order
        Collections.sort(players);
        
        // print info
        System.out.printf("Dealer's final hand: %d.%n", dealersHand.valueOf());
        for(Player p : players){
            System.out.printf("%s's final hand: %d.%n", p.getName(), p.getHand().valueOf());
        }

        List<Player> winners = findBestHands(players);
        
        // determine how much the player(s) won and add it to their wallets
        for(Player w : winners){
            double winAmount = this.wonAmount(w, dealersHand);
            System.out.printf("Player %s won %f.%n", w.getName(), winAmount); 
            w.payOut(winAmount);
        }
        
        return;
    }

    /*
     * Determines which players have the highest hands that is below 22. 
     */
    private List<Player> findBestHands(List<Player> players){
        boolean flag = true;
        Iterator<Player> it = players.iterator();
        List<Player> winners = new ArrayList<>();

        while(it.hasNext() && flag){
            Player p = it.next();
            if(p.getHand().isValid()){
                winners.add(p);

                //check for multiple winners
                if(it.hasNext()){
                    int winningValue = p.getHand().valueOf();
                    Player tmp = it.next();
                    while( winningValue == tmp.getHand().valueOf() ){
                        winners.add(tmp);
                        if(it.hasNext()){ 
                            tmp = it.next();
                        }else{
                            winningValue = -1;
                        }
                    }
                }
                flag = false;    
            }
        }
        return winners;
    }

     /*
     * Determines how much player(s) with the best hand(s) should be paid
     * considering the dealer's hand, their initial bet and blackjack's rules.
     */
    private double wonAmount(Player player, Hand dealer){
        double gain = 0;
        Hand hand = player.getHand();

        //blackjack
        if(hand.isWinner()){
            // player is paid 3:2 if dealer doesn't have blackjack
            // and 1:1 if dealer also has blackjack
            gain += (dealer.isWinner()) ? wagers.get(player) * 2 : wagers.get(player) * 2.5;    

        }
        //better hand than the dealer's
        else if( !dealer.isValid() || dealer.valueOf() < hand.valueOf()){
            gain += wagers.get(player) * 2; 
        }
        //if dealer and player have the same hand
        else if(dealer.valueOf() == hand.valueOf()){
            gain += wagers.get(player); 
        }

        return gain;
    }


}