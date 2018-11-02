package pl.jakubraban.warsimulator;

import java.io.IOException;
import java.util.Scanner;

public class Gameplay {

    CardCollection player1Deck = new CardCollection(false);
    CardCollection player2Deck = new CardCollection(false);
    CardCollection player1CardsWon = new CardCollection(false);
    CardCollection player2CardsWon = new CardCollection(false);
    private Turn currentTurn;
    private int turnCounter = 0, warCounter = 0;
    private GameStatusPrinter printer = new GameStatusPrinter();
    private String n = System.getProperty("line.separator");
    private String name1, name2;
    private Scanner sc = new Scanner(System.in);

    public Gameplay() throws IOException {
        CardCollection startingDeck = new CardCollection(true);
        int size = startingDeck.size();
        for(int i = 0; i < size; i++) {
            if(i % 2 == 0) player1Deck.add(startingDeck.play());
            else           player2Deck.add(startingDeck.play());
        }
    }

    public void playTheGame() throws IOException {
        if(Settings.getSettings("getPlayersNames")) {
            System.out.print("Imię gracza 1: ");
            name1 = sc.nextLine();
            System.out.print("Imie gracza 2: ");
            name2 = sc.nextLine();
            System.out.println();
        }
        while(checkDeckSizes(1))
            takeTurn(new CardCollection(false));
        win();
    }

    void takeTurn(CardCollection cardsFromTieResolving) throws IOException {
        if(currentTurn == null) currentTurn = new Turn();
        if(cardsFromTieResolving.size() == 0) turnCounter++;
        if(cardsFromTieResolving.size() > 0) warCounter++;
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
            printer.printToFileAndConsole(currentTurn.toString());
            printer.printToFileAndConsole(" " + getScore() + n);
            currentTurn = null;
        }
    }

    void resolveTie(CardCollection cards) throws IOException {
        if(!checkDeckSizes(2)) {
            win();
            printer.printToFileAndConsole(currentTurn + n);
        }
        else {
            Card c1 = player1Deck.play();
            Card c2 = player2Deck.play();
            cards.add(c1, c2);
            currentTurn.addCoveredCards(c1, c2);
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

    private void win() throws IOException {
        StringBuilder result = new StringBuilder(n);
        boolean useNames = Settings.getSettings("getPlayersNames");
        if (player2Deck.size() + player2CardsWon.size() < player1CardsWon.size() + player1Deck.size()) {
            if(useNames) result.append("Wygrywa " + name1);
            else result.append("Wygrywa gracz 1");
        }
        else {
            if(useNames) result.append("Wygrywa " + name2);
            else result.append("Wygrywa gracz 2");
        }
        printer.printToFileAndConsole(result.toString());
        if(Settings.getSettings("printStatsOnGameEnd")) printGameStats();
        printer.close();
        Main.gameMenu();
    }

    private void printGameStats() {
        StringBuilder result = new StringBuilder(n);
        result.append(n).append("Ilość tur: ").append(turnCounter).append(n).append("Ilość wojen: ").append(warCounter);
        printer.printToFileAndConsole(result + n);
    }

    int getTurnCount() {
        return turnCounter;
    }

}
