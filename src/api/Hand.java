package api;



import java.util.Set;
import java.lang.Comparable;


/*
 * A hand is a collection of cards.
 */
public abstract class Hand implements Comparable<Hand> {
    /*
     * The set of cards currently in a hand
     */
    protected Set<Card> cards;

    /*
     * Adds a card to the hand
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /*
     * Returns the set of cards 
     */
    public Set<Card> getCards() {
        return cards;
    }

    /*
     * Determination of valid and winning hands, respectively. Your
     * implementation should answer that question with respect to the
     * rules of Black Jack, but irrespective of what other players at
     * the table have (including the dealer).
     */
    public abstract boolean isValid();
    public abstract boolean isWinner();

    /*
     * The value of the hand, as an integer.
     */
    public abstract int valueOf();
}