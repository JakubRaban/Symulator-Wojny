package pl.jakubraban.warsimulator;

import java.util.LinkedList;

public class Turn {

    private LinkedList<Card> player1Cards = new LinkedList<>();
    private LinkedList<Card> player2Cards = new LinkedList<>();
    private CardCollection coveredCards = new CardCollection();
    private String n = System.getProperty("line.separator");

    void add(Card c1, Card c2) {
        player1Cards.add(c1);
        player2Cards.add(c2);
    }

    void addCoveredCards(Card c1, Card c2) {
        coveredCards.add(c1, c2);
    }

    public String toString() {
        int tieLevel = 0;
        StringBuilder toReturn = new StringBuilder();
        while(player1Cards.size() > 0) {
            Card c1 = player1Cards.poll();
            Card c2 = player2Cards.poll();
            int comparision = c1.compareTo(c2);
            char comparisionSign = comparision > 0 ? '>' : (comparision == 0 ? '=' : '<');
            for(int i = 0; i < tieLevel; i++) toReturn.append("-> ");
            if(tieLevel > 0) toReturn.append("Gracze dokładają zakrytą kartę").append(n);
            for(int i = 0; i < tieLevel; i++) toReturn.append("-> ");
            toReturn.append(c1).append(" ").append(comparisionSign).append(" ").append(c2);
            if(tieLevel > 0 && player1Cards.size() == 0) {
                toReturn.append(n);
                for(int i = 0; i < tieLevel; i++) toReturn.append("-> ");
                toReturn.append("Zakryte karty: ").append(coveredCards.toString());
            }
            if(player1Cards.size() > 0) toReturn.append(n);
            tieLevel++;
        }
        return toReturn.toString();
    }

}
