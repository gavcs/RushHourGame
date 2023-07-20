package rushhour.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class RushHourTest {
    @Test
    public void toStringTest(){
        try {
            RushHour rh1 = new RushHour("03_00.csv");
            RushHour rh2 = new RushHour("03_01.csv");
            RushHour rh3 = new RushHour("04_00.csv");
            RushHour rh4 = new RushHour("05_00.csv");
            //RushHour rh5 = new RushHour("05_01.csv");
            //RushHour rh6 = new RushHour("06_00.csv");
            //RushHour rh7 = new RushHour("07_00.csv");
            //RushHour rh8 = new RushHour("07_01.csv");
            //RushHour rh9 = new RushHour("07_02.csv");
            //RushHour rh10 = new RushHour("08_00.csv");
            //RushHour rh11 = new RushHour("09_00.csv");
            //RushHour rh12 = new RushHour("10_00.csv");
            //RushHour rh13 = new RushHour("11_00.csv");
            //RushHour rh14 = new RushHour("12_00.csv");
            //RushHour rh15 = new RushHour("13_00.csv");

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
