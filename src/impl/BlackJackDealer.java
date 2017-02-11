package impl;
import api.Dealer;
import api.Player;
import api.Hand;
import api.Card;
import impl.BlackJackPlayer;
import impl.BlackJackHand;

import java.util.*;
import java.util.Collections;
// import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Iterator;

public class BlackJackDealer extends BlackJackPlayer implements Dealer{
    

    //initialize the deck  
    private static List<Card> deck = new ArrayList<>();

    public BlackJackDealer() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Value value : Card.Value.values()) {
                // System.out.printf("Value: %s  suit: %s%n", value, suit);
                Card tmp = new Card(value, suit);
                deck.add(tmp);
            }
        }

        this.shuffle();
    }


    
	public void dealCard(Player player) {
        Iterator<Card> it = deck.iterator();
        Card tmp = it.next();
        player.receive(tmp);
        // System.out.println("BJD: Cards have not been dealt.");
        it.remove();
	}

    public void collectCards(Player player) {
        Hand hand = player.getHand();
        Iterator<Card> itr = hand.getCards().iterator();
        while(itr.hasNext()){
            deck.add(itr.next());
            itr.remove();
        }
    }

    public Hand getHand(){
        //making a call to dealer as a BlackJackPlayer and its getHand() method
     	return super.getHand(); 
    }

    /*
     * Shuffle the cards.
     */
    public void shuffle(){
        Collections.shuffle(deck);

        // Iterator<Card> itr = deck.iterator();
        
        // Card tmp;
        // for(int i =0; i<52; i++){
        //     tmp = itr.next();
        //     System.out.printf("ValueShuffled: %s  suit: %s%n", tmp.getValue(), tmp.getSuit());
        // }
        

    }



    
}