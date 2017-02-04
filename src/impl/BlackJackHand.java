package impl;
import api.Hand;

public class BlackJackHand extends Hand{

	public int compareTo(Hand other){
		int value = this.valueOf();

        return (value == 0) ? value : other.valueOf();
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
    	return handValue;
    }
}