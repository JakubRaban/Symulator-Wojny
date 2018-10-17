import org.junit.Test;
import static org.junit.Assert.*;

public class GameplayTest {

    @Test
    public void constructorTest() {
        Gameplay g = new Gameplay();
        assertEquals(g.player1Deck.size(), g.player2Deck.size());
        assertEquals(g.player1Deck.size(), 26);
    }

    @Test
    public void testTakeTurn() {
        Gameplay g = new Gameplay();
        g.player1Deck.clear();
        g.player2Deck.clear();
        g.player1Deck.add(new Card(CardValue.ACE, CardSuit.SPADES), new Card(CardValue.QUEEN, CardSuit.HEART));
        g.player2Deck.add(new Card(CardValue.EIGHT, CardSuit.SPADES), new Card(CardValue.TEN, CardSuit.HEART));
        CardCollection tieCards = new CardCollection(new Card(CardValue.FIVE, CardSuit.CLUBS), new Card(CardValue.TWO, CardSuit.HEART));
        g.takeTurn(tieCards);
        assertEquals(1, g.player1Deck.size());
        assertEquals(1, g.player2Deck.size());
        assertEquals(4, g.player1CardsWon.size());
        assertEquals(0, g.player2CardsWon.size());
    }

    @Test
    public void testCheckDeckSizes() {
        Gameplay g = new Gameplay();
        g.player1Deck.clear();
        g.player2Deck.clear();
        g.player1Deck.add(new Card(CardValue.ACE, CardSuit.SPADES), new Card(CardValue.QUEEN, CardSuit.HEART));
        g.player2Deck.add(new Card(CardValue.EIGHT, CardSuit.SPADES));
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 2; j++) {
                g.player2CardsWon.add(new Card(CardValue.values()[i], CardSuit.values()[j]));
            }
        }
        assertTrue(g.checkDeckSizes(2));
        assertEquals(7, g.player2Deck.size());
        assertEquals(2, g.player1Deck.size());
        assertEquals(0, g.player2CardsWon.size());
    }



}
