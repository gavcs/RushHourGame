package rushhour.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RushHour {
    public static final int BOARD_DIM = 6;
    public static final char RED_SYMBOL = 'R';
    public static final char EMPTY_SYMBOL = '-';
    public static final Position EXIT_POS = new Position (2, 5);
    private int moveCount;
    
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
        this.moveCount = 0;
    }

    public void moveVehicle(Move move) throws RushHourException{
        //get the car using the character in Move move as the key
        char key = move.getSymbol();
        Vehicle carmove = cars.get(key);

        //move it using Vehicle.move()
        carmove.move(move.getDirect());

        //must add to the moveCount
        this.moveCount++;
    }

    public boolean gameOver(){
        Vehicle car = cars.get('R');
        Position front = car.getFront();
        if(front.equals(EXIT_POS)){
            return true;
        } else {
            return false;
        }
    }

    public Collection<Move> getPossibleMoves(){
        //create a Collection to return, and iterate through the existing cars
        Collection<Move> posMoves = new LinkedList<>();
        for(char key: cars.keySet()){

            //get the Vehicle and create Position variables to work with
            Vehicle a = cars.get(key);
            Position front = a.getFront();
            Position back = a.getBack();
            //checking if the car can move left or right, but checking which way the car is facing first is important 
            if(front.getCol() > back.getCol()){
                if(front.getCol() < BOARD_DIM){
                    posMoves.add(new Move(a.getSymbol(), Direction.RIGHT));
                }
                if(back.getCol() > 0){
                    posMoves.add(new Move(a.getSymbol(), Direction.LEFT));
                }
            } else {
                if(back.getCol() < BOARD_DIM){
                    posMoves.add(new Move(a.getSymbol(), Direction.RIGHT));
                }
                if(front.getCol() > 0){
                    posMoves.add(new Move(a.getSymbol(), Direction.LEFT));
                }
            }

            //now looking to see if the car could move up or down, but also need to check where it's facing again
            if(front.getRow() > back.getRow()){
                if(front.getRow() < BOARD_DIM){
                    posMoves.add(new Move(a.getSymbol(), Direction.UP));
                }
                if(back.getRow() > 0){
                    posMoves.add(new Move(a.getSymbol(), Direction.DOWN));
                }
            } else {
                if(back.getRow() < BOARD_DIM){
                    posMoves.add(new Move(a.getSymbol(), Direction.UP));
                }
                if(front.getRow() > 0){
                    posMoves.add(new Move(a.getSymbol(), Direction.DOWN));
                }
            }
        }
        return posMoves;
    }

    public int getMoveCount(){
        return this.moveCount;
    }
}