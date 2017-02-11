package impl;
import api.Table;
import api.Dealer;
import api.Player;
import impl.BlackJackDealer;

import java.util.Iterator;

public class BlackJackTable extends Table{


	public BlackJackTable(int numOfPlayers){

        Dealer dealer = new BlackJackDealer();
        dealer.shuffle();
        
        System.out.printf("Number of players %d%n", numOfPlayers);

        for( int i = 0; i < numOfPlayers; i++){
            System.out.printf("i: %d%n", i);
            Player player = new BlackJackPlayer();
            players.add ( player );
        }

        
	}
   
     /*
     * A game is over when there are no players, or no players with
     * money to bet
     */
	public boolean isGameOver(){

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

    }

    /*
     * Deal cards to all players at the table. Note that in Black
     * Jack, that also means the dealer themself!
     */
    protected void dealTable(){
        Iterator<Player> it = players.iterator();
        while(it.hasNext()){
            Player tmp = it.next();
            dealer.dealCard(tmp);
            dealer.dealCard(tmp);
        }

        dealer.dealCard((Player)dealer);
        dealer.dealCard((Player)dealer);

    }
    
    /*
     * Collect bets from all players at the table
     */
    protected void collectBets(){

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