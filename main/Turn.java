
public class Turn {

    private CardCollection player1Cards = new CardCollection();
    private CardCollection player2Cards = new CardCollection();

    public void add(Card c1, Card c2) {
        player1Cards.add(c1);
        player2Cards.add(c2);
    }

    public String toString() {
        int tieLevel = 0;
        StringBuilder toReturn = new StringBuilder();
        while(player1Cards.size() > 0) {
            Card c1 = player1Cards.play();
            Card c2 = player2Cards.play();
            int comparision = c1.compareTo(c2);
            char comparisionSign = comparision > 0 ? '>' : (comparision == 0 ? '=' : '<');
            for(int i = 0; i < tieLevel; i++) toReturn.append("-> ");
            if(tieLevel > 0) toReturn.append("Gracze dokładają kartę" + "\n");
            toReturn.append(c1 + " " + comparisionSign + " " + c2);
            if(player1Cards.size() > 0) toReturn.append("\n");
            tieLevel++;
        }
        return toReturn.toString();
    }

}
