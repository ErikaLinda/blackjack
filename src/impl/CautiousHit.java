package impl;

import api.HittingStrategy;
import api.Hand;

// player hits if the value of her/his hand is below 14
public class CautiousHit implements HittingStrategy{
	public boolean hit(Hand h){
		return h.valueOf() < 14;
	}
}