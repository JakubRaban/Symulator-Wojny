package pl.jakubraban.warsimulator;

public class Card implements Comparable<Card> {

    private CardValue value;
    private CardSuit suit;

    Card(CardValue value, CardSuit suit) {
        this.value = value;
        this.suit = suit;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.value.toString()).append(" ").append(this.suit.toString());
        return s.toString();
    }

    public int compareTo(Card card) {
        return this.value.ordinal() - card.value.ordinal();
    }

}
