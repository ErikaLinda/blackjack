package impl;
import api.Dealer;
import api.Player;
import api.Hand;
import api.Card;
import impl.BlackJackPlayer;

import java.util.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;

public class BlackJackDealer extends BlackJackPlayer implements Dealer{
    
    //initialize the dealer's deck  
    private static List<Card> deck = new ArrayList<>();

    //constructor
    public BlackJackDealer() {
        this.addDeck();
    }

    // remove cards from the deck and add to the player
	public void dealCard(Player player) {
        //add deck whenever dealer runs out of cards
        if(deck.isEmpty()){
            this.addDeck();
            this.shuffle();
        }
        Iterator<Card> it = deck.iterator();
        Card tmp = it.next();
        player.receive(tmp);
        it.remove();
	}

    // remove cards from the palyer and add to the deck
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
    }

    /*
     * Adds a full deck of 52 cards to the dealers "deck" variable.
     */
    private void addDeck(){
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Value value : Card.Value.values()) {
                Card card = new Card(value, suit);
                deck.add(card);
            }
        }
    }



    
}