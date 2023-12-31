package rushhour.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Collection;

import org.junit.jupiter.api.Test;

public class RushHourTest {
    @Test
    public void toStringTest(){
        try {
            RushHour rh1 = new RushHour("03_00.csv");
            RushHour rh2 = new RushHour("03_01.csv");
            RushHour rh3 = new RushHour("04_00.csv");
            RushHour rh4 = new RushHour("05_00.csv");
            RushHour rh5 = new RushHour("05_01.csv");
            RushHour rh6 = new RushHour("06_00.csv");
            RushHour rh7 = new RushHour("07_00.csv");
            RushHour rh8 = new RushHour("07_01.csv");
            RushHour rh9 = new RushHour("07_02.csv");
            RushHour rh10 = new RushHour("08_00.csv");
            RushHour rh11 = new RushHour("09_00.csv");
            RushHour rh12 = new RushHour("10_00.csv");
            RushHour rh13 = new RushHour("11_00.csv");
            RushHour rh14 = new RushHour("12_00.csv");
            RushHour rh15 = new RushHour("13_00.csv");

            String a1 = rh1.toString();
            assertEquals("--O---\n" +
                         "--OA--\n" +
                         "RROA--\n" +
                         "------\n" +
                         "------\n" +
                         "------", a1);
            
            String a2 = rh2.toString();
            assertEquals("--O---\n" +
                        "--O---\n" +
                        "RROAA-\n" +
                        "------\n" + 
                        "------\n" +
                        "------", a2);

            String a3 = rh3.toString();
            assertEquals("AA--O-\n" +
                         "----O-\n" +
                         "RR--OB\n" +
                         "-----B\n" +
                         "------\n" +
                         "------", a3);

            String a4 = rh4.toString();
            assertEquals("----A-\n" +
                         "----A-\n" +
                         "--RRB-\n" +
                         "----B-\n" +
                         "---CDD\n" +
                         "---C--", a4);

            String a5 = rh5.toString();
            assertEquals("------\n" +
                         "-AA---\n" +
                         "RRB---\n" +
                         "C-B---\n" +
                         "C-DD--\n" +
                         "------", a5);

            String a6 = rh6.toString();
            assertEquals("------\n" +
                         "---OOO\n" +
                         "RR-AB-\n" +
                         "P--AB-\n" +
                         "PQQQ--\n" +
                         "P-----", a6);

            String a7 = rh7.toString();
            assertEquals("-AAO--\n" +
                         "-BBO--\n" +
                         "RRCO--\n" +
                         "--C---\n" +
                         "--D---\n" +
                         "--DEE-", a7);

            String a8 = rh8.toString();
            assertEquals("--OOOP\n" +
                         "----AP\n" +
                         "RR--AP\n" +
                         "-BBQQQ\n" +
                         "----CC\n" +
                         "------", a8);

            String a9 = rh9.toString();
            assertEquals("--OOOP\n" +
                         "----AP\n" +
                         "RR--AP\n" +
                         "-BB---\n" +
                         "CC---Q\n" +
                         "-----Q", a9);

            String a10 = rh10.toString();
            assertEquals("---OOO\n" +
                         "--AABB\n" +
                         "RR--CD\n" +
                         "-P--CD\n" +
                         "-PQQQ-\n" +
                         "-P----", a10);

            String a11 = rh11.toString();
            assertEquals("-OA-BB\n" +
                         "-OA---\n" +
                         "-ORRCD\n" +
                         "----CD\n" +
                         "--EPPP\n" +
                         "FFE---", a11);

            String a12 = rh12.toString();
            assertEquals("A-OPPP\n" +
                         "A-OBB-\n" +
                         "RRO---\n" +
                         "------\n" +
                         "QQQCDE\n" +
                         "-FFCDE", a12);

            String a13 = rh13.toString();
            assertEquals("AABCCD\n" +
                         "E-B-FD\n" +
                        "E-RRF-\n" +
                        "OOOPG-\n" +
                        "---PG-\n" +
                        "---PHH", a13);

            String a14 = rh14.toString();
            assertEquals("-AAOOO\n" +
                         "BBCCDE\n" +
                         "RR-FDE\n" +
                         "PPPF-G\n" +
                         "H-QQQG\n" +
                         "H-----", a14);

            String a15 = rh15.toString();
            assertEquals("OABBC-\n" +
                         "OAD-CP\n" +
                         "O-DRRP\n" +
                         "QQQE-P\n" +
                         "--FEGG\n" +
                         "HHFII-", a15);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void moveVehicleTest(){
        try {
            RushHour rh = new RushHour("03_00");
            try {
                rh.moveVehicle(new Move('A', Direction.DOWN));
                Vehicle test = rh.getVehicle('A');
                Position front = test.getFront();
                Position back = test.getBack();
                assertEquals(new Position(3, 3), front);
                assertEquals(new Position(4, 3), back);
            } catch (RushHourException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPossibleMovesTest(){
        try {
            RushHour rh1 = new RushHour("03_00.csv");
            Collection<Move> c1 = rh1.getPossibleMoves();
            String a1 = c1.toString();
            assertEquals("[A: DOWN, A: UP, O: DOWN]", a1);

            RushHour rh2 = new RushHour("05_00.csv");
            Collection<Move> c2 = rh2.getPossibleMoves();
            String a2 = c2.toString();
            assertEquals("[R: LEFT, C: UP]", a2);

            RushHour rh3 = new RushHour("13_00.csv");
            Collection<Move> c3 = rh3.getPossibleMoves();
            String a3 = c3.toString();
            assertEquals("[A: DOWN, I: RIGHT, P: UP]", a3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getVehicleTest(){
        try {
            RushHour rh = new RushHour("03_01.csv");
            Vehicle actual = rh.getVehicle('A');
            assertEquals(new Vehicle('A', new Position(2, 3), new Position(2, 4)), actual);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void gameOverTest() throws RushHourException{
        try {
            RushHour rh = new RushHour("03_00.csv");
            rh.moveVehicle(new Move('A', Direction.DOWN));
            rh.moveVehicle(new Move('A', Direction.DOWN));
            rh.moveVehicle(new Move('O', Direction.DOWN));
            rh.moveVehicle(new Move('O', Direction.DOWN));
            rh.moveVehicle(new Move('O', Direction.DOWN));
            rh.moveVehicle(new Move('R', Direction.RIGHT));
            rh.moveVehicle(new Move('R', Direction.RIGHT));
            rh.moveVehicle(new Move('R', Direction.RIGHT));
            rh.moveVehicle(new Move('R', Direction.RIGHT));
            boolean actual = rh.gameOver();
            assertEquals(true, actual);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMoveCountTest(){
        try {
            RushHour rh = new RushHour("03_00.csv");
            try {
                rh.moveVehicle(new Move('A', Direction.DOWN));
                rh.moveVehicle(new Move('A', Direction.DOWN));
                rh.moveVehicle(new Move('O', Direction.DOWN));
                rh.moveVehicle(new Move('O', Direction.DOWN));
                rh.moveVehicle(new Move('O', Direction.DOWN));
                rh.moveVehicle(new Move('R', Direction.RIGHT));
                rh.moveVehicle(new Move('R', Direction.RIGHT));
                rh.moveVehicle(new Move('R', Direction.RIGHT));
                rh.moveVehicle(new Move('R', Direction.RIGHT));
                int actual = rh.getMoveCount();
                assertEquals(9, actual);
            } catch (RushHourException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
