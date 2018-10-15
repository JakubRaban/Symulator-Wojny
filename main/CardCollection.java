import java.util.*;

public class CardCollection {

    private LinkedList<Card> cards = new LinkedList<>();
    Random r = new Random();

    public CardCollection(boolean fillTheDeck) {
        if(fillTheDeck) {
            for(int i = 0; i < 13; i++) {
                for(int j = 0; j < 4; j++) {
                    this.cards.add(new Card(CardValue.values()[i], CardSuit.values()[j]));
                }
            }
        }
        Collections.shuffle(this.cards);
    }

    public CardCollection(Card ... cards) {
        for(int i = 0; i < cards.length; i++) {
            this.cards.add(cards[i]);
        }
        Collections.shuffle(this.cards);
    }

    public void add(Card ... cards) {
        for(int i = 0; i < cards.length; i++) {
            this.cards.add(cards[i]);
        }
        Collections.shuffle(this.cards);
    }

    public void add(CardCollection cards) {
        for(int i = 0; i < cards.size(); i++) {
            this.cards.add(cards.get(i));
        }
        Collections.shuffle(this.cards);
    }

    public void moveFrom(CardCollection collection) {
        this.cards.addAll(collection.cards);
        Collections.shuffle(this.cards);
    }

    public int size() {
        return this.cards.size();
    }

    public Card play() {
        return this.cards.poll();
    }

    public Card get(int index) {
        return this.cards.get(index);
    }

    public void clear() {
        this.cards.clear();
    }

}
