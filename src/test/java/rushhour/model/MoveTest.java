package rushhour.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MoveTest {
    @Test
    public void move1(){
        Move m = new Move('a', Direction.UP);
    }
}
