package impl;

import api.HittingStrategy;
import api.Hand;

// player hits even if the value of her/his hand reaches 17
public class OptimisticHit implements HittingStrategy{
	public boolean hit(Hand h){
		return h.valueOf() < 18;
	}
}