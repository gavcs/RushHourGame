package rushhour.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RushHour {
    public static final int BOARD_DIM = 6;
    public static final char RED_SYMBOL = 'R';
    public static final char EMPTY_SYMBOL = '-';
    public static final Position EXIT_POS = new Position (2, 5);

    public RushHour (String filename) throws IOException {
        FileReader read = new FileReader(filename);
        BufferedReader reader = new BufferedReader(read);
        String[] numCars = filename.split("_");
        int cars = Integer.parseInt(numCars[0]);

        reader.close();
    }
}