package impl;
import api.Dealer;
import api.Player;
import api.Hand;
import impl.BlackJackPlayer;

public class BlackJackDealer extends BlackJackPlayer implements Dealer{


	public void dealCard(Player player){
	}

    public void collectCards(Player player){
    }

    public Hand getHand(){
     	return this.getHand();
    }

    /*
     * Shuffle the cards.
     */
    public void shuffle(){
    }
}