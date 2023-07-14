package rushhour.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RushHour {
    public static final int BOARD_DIM = 6;
    public static final char RED_SYMBOL = 'R';
    public static final char EMPTY_SYMBOL = '-';
    public static final Position EXIT_POS = new Position (2, 5);
    
    /*
     * creating a map with the vehicles will allow for the moveVehicle method to move the car
     * we want it to move. An instance of Move will have a char, which looking at the different csv files,
     * each char has a unique character. When moveVehicle is called, it will pull the Vehicle associated
     * with the char in the Move move peram.
     */
    private Map<Character, Vehicle> cars;

    public RushHour (String filename) throws IOException {
        FileReader read = new FileReader(filename);
        BufferedReader reader = new BufferedReader(read);
        String[] numCars = filename.split("_");
        int n = Integer.parseInt(numCars[0]);
        this.cars = new HashMap<>();
        for(int i = 0; i < n; i++){
            String car = reader.readLine();
            String[] details = car.split(",");
            Position back = new Position(Integer.parseInt(details[1]), Integer.parseInt(details[2]));
            Position front = new Position(Integer.parseInt(details[3]), Integer.parseInt(details[4]));
            this.cars.put(details[0].charAt(0), new Vehicle(details[0].charAt(0), back, front));
        }
        reader.close();
    }

    public void moveVehicle(Move move) throws RushHourException{
        
    }
}