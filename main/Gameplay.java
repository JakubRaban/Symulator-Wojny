public class Gameplay {

    CardCollection startingDeck = new CardCollection(true);
    CardCollection player1Deck = new CardCollection(false);
    CardCollection player2Deck = new CardCollection(false);
    CardCollection player1CardsWon = new CardCollection(false);
    CardCollection player2CardsWon = new CardCollection(false);
    int winner;

    public Gameplay() {
        for(int i = 0; i < 52; i++) {
            if(i % 2 == 0) player1Deck.add(startingDeck.play());
            else           player2Deck.add(startingDeck.play());
        }
    }

    public void playTheGame() {
        while(checkDeckSizes(1))
            takeTurn(new CardCollection(false));
        win();
    }

    public void takeTurn(CardCollection cardsFromTieResolving) {
        Card player1Card = player1Deck.play();
        Card player2Card = player2Deck.play();
        int comparision = player1Card.compareTo(player2Card);
        if(comparision > 0) {
            player1CardsWon.add(player1Card, player2Card);
            player1CardsWon.add(cardsFromTieResolving);
        } else if(comparision < 0) {
            player2CardsWon.add(player1Card, player2Card);
            player2CardsWon.add(cardsFromTieResolving);
        } else {
            cardsFromTieResolving.add(player1Card, player2Card);
            resolveTie(cardsFromTieResolving);
        }
    }

    public void resolveTie(CardCollection cards) {
        CardCollection cardsWon = cards;
        if(!checkDeckSizes(2)) win();
        else {
            cardsWon.add(player1Deck.play(), player2Deck.play());
            takeTurn(cardsWon);
        }
    }

    public boolean checkDeckSizes(int goodSize) {
        if(player1Deck.size() < goodSize) {
            player1Deck.moveFrom(player1CardsWon);
            player1CardsWon.clear();
        }
        if(player2Deck.size() < goodSize) {
            player2Deck.moveFrom(player2CardsWon);
            player2CardsWon.clear();
        }
        return player2Deck.size() >= goodSize && player1Deck.size() >= goodSize;

    }

    private void win() {
        if (player2Deck.size() + player2CardsWon.size() < 2) winner = 1;
        else winner = 2;
    }

}
