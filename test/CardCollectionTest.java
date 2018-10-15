import org.junit.Test;
import static org.junit.Assert.*;

public class CardCollectionTest {

    @Test
    public void constructorTest () {
        CardCollection c = new CardCollection(true);
        assertEquals(c.size(), 52);
    }

    @Test
    public void addTest() {
        CardCollection c = new CardCollection(false);
        c.add(new Card(CardValue.EIGHT, CardSuit.DIAMOND), new Card(CardValue.JACK, CardSuit.HEART));
        assertEquals(c.size(), 2);
    }

    @Test
    public void testPlay() {
        CardCollection c = new CardCollection(false);
        c.add(new Card(CardValue.ACE, CardSuit.SPADES));
        assertEquals(c.play().toString(), "A Pik");
        assertEquals(c.size(), 0);
    }

    @Test
    public void testClear() {
        CardCollection c = new CardCollection(true);
        c.clear();
        assertEquals(c.size(), 0);
    }

    @Test
    public void testMoveFrom() {
        CardCollection c = new CardCollection(true);
        CardCollection d = new CardCollection(false);
        d.moveFrom(c);
        assertEquals(d.size(), 52);
    }


}
