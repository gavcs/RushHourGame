package rushhour.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class VehicleTest {
    @Test
    public void accessorsTest(){
        Vehicle v = Vehicle.makeVehicle();
        char a1 = v.getSymbol();
        Position a2 = v.getBack();
        Position a3 = v.getFront();
        assertEquals('A', a1);
        assertEquals(new Position(0,0), a2);
        assertEquals(new Position(0,3), a3);
    }

    @Test
    public void vehicleCopyTest(){
        Vehicle v = Vehicle.makeVehicle();
        Vehicle other = new Vehicle(v);
        boolean actual = v.equals(other);
        assertEquals(v, other);
        assertEquals(true, actual);
    }

    @Test
    public void moveTest(){
        Vehicle v = Vehicle.makeVehicle();
        try {
            v.move(Direction.RIGHT);
            v.move(Direction.RIGHT);
            Position a1 = v.getFront();
            Position a2 = v.getBack();
            assertEquals(new Position(0, 5), a1);
            assertEquals(new Position(0, 2), a2);
        } catch (RushHourException e) {
            e.printStackTrace();
        }
    }
}
