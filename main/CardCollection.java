import java.util.*;

public class CardCollection {

    private LinkedList<Card> cards = new LinkedList<>();

    CardCollection(boolean fillTheDeck) {
        if(fillTheDeck) {
            for(int i = 0; i < 13; i++) {
                for(int j = 0; j < 4; j++) {
                    this.cards.add(new Card(CardValue.values()[i], CardSuit.values()[j]));
                }
            }
        }
        Collections.shuffle(this.cards);
    }

    CardCollection(Card ... cards) {
        Collections.addAll(this.cards, cards);
        Collections.shuffle(this.cards);
    }

    void add(Card ... cards) {
        Collections.addAll(this.cards, cards);
        Collections.shuffle(this.cards);
    }

    void add(CardCollection cards) {
        int size = cards.size();
        for(int i = 0; i < size; i++) {
            this.cards.add(cards.play());
        }
        Collections.shuffle(this.cards);
    }

    void moveFrom(CardCollection collection) {
        this.cards.addAll(collection.cards);
        Collections.shuffle(this.cards);
    }

    int size() {
        return this.cards.size();
    }

    Card play() {
        return this.cards.poll();
    }

    void clear() {
        this.cards.clear();
    }

    public String toString() {
        return this.cards.toString();
    }

}
