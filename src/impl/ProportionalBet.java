package impl;

import api.BettingStrategy;

//player places 30% of the wallet or everything if wallet has less than 10
public class ProportionalBet implements BettingStrategy{
	public double bet(double wallet){
		return (wallet > 10) ? wallet * 0.3 : wallet;
	}
}