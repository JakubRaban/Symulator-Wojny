public class Card implements Comparable<Card> {

    private CardValue value;
    private CardSuit suit;

    public Card(CardValue value, CardSuit suit) {
        this.value = value;
        this.suit = suit;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        switch(this.value) {
            case JACK:
                s.append("J");
                break;
            case QUEEN:
                s.append("Q");
                break;
            case KING:
                s.append("K");
                break;
            case ACE:
                s.append("A");
                break;
                default:
                    s.append(this.value.ordinal() + 2);
        }
        s.append(" " + this.suit.toString());
        return s.toString();
    }

    public int compareTo(Card card) {
        return (int) Math.signum(this.value.ordinal() - card.value.ordinal());
    }

}
