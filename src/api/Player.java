package api;

/*
 * A card player.
 */
public interface Player extends Comparable<Player> {
    /*
     * Receive a card
     */
    public void receive(Card card);

    /*
     * Return the current hand to the caller
     */
    public Hand getHand();

    /*
     * Place a wager
     */
    public double wager();

    /*
     * Give a player money (upon winning a round)
     */
    public void payOut(double money);

    /*
     * Return the amount of money currently available to the player
     */
    public double getMoney();

    /*
     * Return the name of the player
     */
    public String getName();

    /*
     * Whether the player would like to request a card: true indicates
     * that they would (equivalent to a 'hit' in Black Jack); false
     * indicates they would not.
     */
    public boolean requestCard();
}
