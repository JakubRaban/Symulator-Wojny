import org.junit.Test;
import static org.junit.Assert.*;

public class GameplayTest {

    @Test
    public void constructorTest() {
        Gameplay g = new Gameplay();
        assertEquals(g.player1Deck.size(), g.player2Deck.size());
        assertEquals(g.player1Deck.size(), 26);
    }

}
