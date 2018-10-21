
import java.util.Scanner;

public class Gameplay {

    CardCollection player1Deck = new CardCollection(false);
    CardCollection player2Deck = new CardCollection(false);
    CardCollection player1CardsWon = new CardCollection(false);
    CardCollection player2CardsWon = new CardCollection(false);
    private Turn currentTurn;

    Gameplay() {
        CardCollection startingDeck = new CardCollection(true);
        int size = startingDeck.size();
        for(int i = 0; i < size; i++) {
            if(i % 2 == 0) player1Deck.add(startingDeck.play());
            else           player2Deck.add(startingDeck.play());
        }
    }

    private void playTheGame() {
        while(checkDeckSizes(1))
            takeTurn(new CardCollection(false));
        win();
    }

    void takeTurn(CardCollection cardsFromTieResolving) {
        if(currentTurn == null) currentTurn = new Turn();
        Card player1Card = player1Deck.play();
        Card player2Card = player2Deck.play();
        currentTurn.add(player1Card, player2Card);
        int comparision = player1Card.compareTo(player2Card);
        if(comparision == 0) {
            cardsFromTieResolving.add(player1Card, player2Card);
            resolveTie(cardsFromTieResolving);
        } else {
            if(comparision > 0) {
                player1CardsWon.add(player1Card, player2Card);
                player1CardsWon.add(cardsFromTieResolving);
            } else {
                player2CardsWon.add(player1Card, player2Card);
                player2CardsWon.add(cardsFromTieResolving);
            }
            System.out.print(currentTurn);
            System.out.println(getScore());
            currentTurn = null;
        }
    }

    void resolveTie(CardCollection cards) {
        if(!checkDeckSizes(2)) win();
        else {
            Card c1 = player1Deck.play();
            Card c2 = player2Deck.play();
            cards.add(c1, c2);
            takeTurn(cards);
        }
    }

    private String getScore() {
        return "(" + player1Deck.size() + "+" + player1CardsWon.size() + ":"
                + player2Deck.size() + "+" + player2CardsWon.size() + ")";
    }

    boolean checkDeckSizes(int goodSize) {
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
        if (player2Deck.size() + player2CardsWon.size() < 2) {
            System.out.println("Wygrywa gracz 1");
        }
        else {
            System.out.println("Wygrywa gracz 2");
        }
    }


    public static void main(String ... args) {
        Scanner sc = new Scanner(System.in);
        Gameplay gameplay;
        System.out.println("\n" + "--- SYMULATOR WOJNY ---");
        System.out.println("1 = Zagraj w wojnę");
        System.out.println("2 = Wyjdź");
        boolean validInput = false;
        while(!validInput) {
            System.out.print("Wybór = _" + "\b");
            String choice = sc.nextLine();
            if(choice.equals("1")) {
                System.out.println();
                validInput = true;
                gameplay = new Gameplay();
                gameplay.playTheGame();
            } else if(choice.equals("2")) System.exit(0);
        }
    }

}
