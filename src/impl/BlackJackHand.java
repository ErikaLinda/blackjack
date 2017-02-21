package impl;
import api.Hand;
import api.Card;

import java.util.HashSet;

public class BlackJackHand extends Hand{

    //constructor
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
    	
    	return (curValue < 22 && curValue > 0);
    }

    public boolean isWinner(){
    	return (this.valueOf() == 21);
    }

    /*
     * The value of the hand, as an integer.
     */
    public int valueOf(){
        int handValue = 0;
        
        for(Card c : cards){
            handValue += c.getValue().getValue();
        }

    	return handValue;
    }
}