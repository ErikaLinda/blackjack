package impl;

import api.HittingStrategy;
import api.Hand;

// player hits only if the value of her/his hand is below 16 (dealer's standard)
public class DealersHit implements HittingStrategy{
	public boolean hit(Hand h){
		return h.valueOf() < 16;
	}
}