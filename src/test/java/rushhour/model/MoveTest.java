package rushhour.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MoveTest {
    @Test
    public void move1(){
        Move m = new Move('A', Direction.UP);
        char a1 = m.getSymbol();
        Direction a2 = m.getDirect();
        String a3 = m.toString();
        assertEquals('A', a1);
        assertEquals(Direction.UP, a2);
        assertEquals("A: UP", a3);

    }
}
