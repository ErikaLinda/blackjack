package impl;
import api.Hand;
import api.Card;

import java.util.*;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
// import java.util.ArraySet;
import java.lang.Comparable;

public class BlackJackHand extends Hand{

    

    public BlackJackHand() {
        this.cards = new HashSet<Card>();
    }


	public int compareTo(Hand other) {
        return ( (Integer) this.valueOf() ).compareTo((Integer)other.valueOf());

    }

	  /*
     * Determination of valid and winning hands, respectively. Your
     * implementation should answer that question with respect to the
     * rules of Black Jack, but irrespective of what other players at
     * the table have (including the dealer).
     */
    public boolean isValid(){
    	int curValue = this.valueOf();
    	
    	return (curValue < 22 && curValue > 0) ? true : false;
    }

    public boolean isWinner(){
    	return (this.valueOf() == 21) ? true : false;
    }

    /*
     * The value of the hand, as an integer.
     */
    public int valueOf(){
        int handValue = 0;

        for(Card c : cards){
            handValue += c.getValue().getValue();
        }

        // Iterator<Card> it = cards.iterator();
        // while(it.hasNext()){
        //     handValue += it.next().getValue().getValue();
        // }
        
    	// System.out.printf("Hand value: %d.%n", handValue);
    	return handValue;
    }
}