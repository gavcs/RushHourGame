package rushhour.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PositionTest {
    @Test
    public void positionTest(){
        Position p1 = new Position(0,0);
        int a1 = p1.getRow();
        int a2 = p1.getCol();
        boolean a3 = p1.equals(new Position(0,0));
        assertEquals(a1, 0);
        assertEquals(a2, 0);
        assertEquals(true, a3);
    }
}
