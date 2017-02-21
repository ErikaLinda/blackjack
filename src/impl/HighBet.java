package impl;

import api.BettingStrategy;

//a player places a high constant bet or everything that is left
public class HighBet implements BettingStrategy{
	public double bet(double wallet){
		double bet = 400;
		return (wallet > bet) ? bet : wallet;
	}
}