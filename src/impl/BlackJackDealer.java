package impl;
import api.Dealer;
import api.Player;
import api.Hand;
import api.Card;
import impl.BlackJackPlayer;

import java.util.*;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Iterator;

public class BlackJackDealer extends BlackJackPlayer implements Dealer{
    

    //initialize the deck  
    // private static Card[] deck = new Card[52];

    private static List<Card> deck = new ArrayList<>();

    public BlackJackDealer() {
        int i = 0;
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Value value : Card.Value.values()) {

                // deck[i++] = new Card(value, suit);
                // System.out.printf("Value: %s  suit: %s%n", value, suit);
                Card tmp = new Card(value, suit);
                deck.add(tmp);
            }
        }

    }

    //initialize the hand
    private static Hand dealersHand = new BlackJackHand();

    
	public void dealCard(Player player) {
        // Card tmp = deck.remove(1);
        // Card tmp = deck[1];
        Iterator<Card> it = deck.iterator();
        player.receive(it.next());
        it.remove();
	}

    public void collectCards(Player player) {
    }

    public Hand getHand(){
     	return dealersHand;
    }

    /*
     * Shuffle the cards.
     */
    public void shuffle(){
        Collections.shuffle(deck);

        Iterator<Card> itr = deck.iterator();
        
        // Card tmp;
        // for(int i =0; i<52; i++){
        //     tmp = itr.next();
        //     System.out.printf("ValueShuffled: %s  suit: %s%n", tmp.getValue(), tmp.getSuit());
        // }
        

    }



    
}