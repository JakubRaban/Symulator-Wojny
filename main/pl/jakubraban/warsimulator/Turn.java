package pl.jakubraban.warsimulator;

import java.util.LinkedList;

public class Turn {

    private LinkedList<Card> player1Cards = new LinkedList<>();
    private LinkedList<Card> player2Cards = new LinkedList<>();
    private CardCollection coveredCards = new CardCollection();
    private String n = System.getProperty("line.separator");
    private StringBuilder toReturn = new StringBuilder();

    void add(Card c1, Card c2) {
        player1Cards.add(c1);
        player2Cards.add(c2);
    }

    void addCoveredCards(Card c1, Card c2) {
        coveredCards.add(c1, c2);
    }

    public String toString() {
        int tieLevel = 0;
        while(player1Cards.size() > 0) {
            if(Settings.getSettings("enumerateTurns") && tieLevel == 0)
                printTurnNumber();
            Card c1 = player1Cards.poll();
            Card c2 = player2Cards.poll();
            char comparisionSign = getComparisionChar(c1, c2);
            printTieArrows(tieLevel);
            if(tieLevel > 0) toReturn.append("Gracze dokładają zakrytą kartę").append(n);
            printTieArrows(tieLevel);
            toReturn.append(c1).append(" ").append(comparisionSign).append(" ").append(c2);
            if(tieLevel > 0 && player1Cards.size() == 0) {
                toReturn.append(n);
                printTieArrows(tieLevel);
                toReturn.append("Zakryte karty: ").append(coveredCards.toString());
            }
            if(player1Cards.size() > 0) toReturn.append(n);
            tieLevel++;
        }
        return toReturn.toString();
    }

    private void printTieArrows(int tieLevel) {
        for(int i = 0; i < tieLevel; i++) toReturn.append("-> ");
    }

    private char getComparisionChar(Card c1, Card c2) {
        int comparision = c1.compareTo(c2);
        if(comparision > 0) return '>';
        else if (comparision == 0) return '=';
        else return '<';
    }

    private void printTurnNumber() {
        toReturn.append(Main.gameplay.getTurnCount()).append(". ");
    }

}
