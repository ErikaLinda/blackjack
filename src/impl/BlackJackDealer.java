package impl;
import api.Dealer;
import api.Player;
import api.Hand;
import api.Card;
import impl.BlackJackPlayer;

import java.util.*;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class BlackJackDealer extends BlackJackPlayer implements Dealer{
    
    //initialize the deck  
    private static Card[] deck = new Card[52];
    public BlackJackDealer() {
        int i = 0;
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Value value : Card.Value.values()) {
                deck[i++] = new Card(value, suit);
                // System.out.printf("Value: %s  suit: %s%n", value, suit);
            }
        }

    }

    
	public void dealCard(Player player) {
	}

    public void collectCards(Player player) {
    }

    public Hand getHand(){
     	return this.getHand();
    }

    /*
     * Shuffle the cards.
     */
    public void shuffle(){
        Collections.shuffle(Arrays.asList(deck));
        
        for (int i = 0 ; i < deck.length; i++ ) {
            System.out.printf("ValueShuffled: %s  suit: %s%n", deck[i].getValue(), deck[i].getSuit());
        }

    }



    
}