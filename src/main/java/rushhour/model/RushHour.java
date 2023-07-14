package rushhour.model;

import java.io.IOException;

public class RushHour {
    public static final int BOARD_DIM = 6;
    public static final char RED_SYMBOL = 'R';
    public static final char EMPTY_SYMBOL = '-';
    public static final Position EXIT_POS = new Position (2, 5);

    private final String filename;

    public RushHour (String filename) throws IOException {
        this.filename = filename;
    }
}

   