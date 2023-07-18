package rushhour.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class RushHour {
    public static final int BOARD_DIM = 6;
    public static final char RED_SYMBOL = 'R';
    public static final char EMPTY_SYMBOL = '-';
    public static final Position EXIT_POS = new Position (2, 5);
    private int moveCount;
    private String filename;
    
    /*
     * creating a map with the vehicles will allow for the moveVehicle method to move the car
     * we want it to move. An instance of Move will have a char, which looking at the different csv files,
     * each char has a unique character. When moveVehicle is called, it will pull the Vehicle associated
     * with the char in the Move move peram.
     */
    private Map<Character, Vehicle> cars;

    public RushHour (String filename) throws IOException {
        this.filename = filename;
        FileReader read = new FileReader("data/" + filename);
        BufferedReader reader = new BufferedReader(read);
        String[] numCars = filename.split("_");
        int n;
        if(numCars[0].charAt(0) == 0){
            String a = "" + numCars[0].charAt(1);
            n = Integer.parseInt(a);
        } else {
            n = Integer.parseInt(numCars[0]);
        }
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

    //this helper function will create a list of strings in a format like 12R, where 1 is the row, 2 is the column, and R is the Symbol
    /*
    private List<String> carLocations(){
        Set<Character> ca = cars.keySet();
        LinkedList<Vehicle> l = new LinkedList<>();
        for(Character c: ca){
            l.add(cars.get(c));
        }
        LinkedList<String> list = new LinkedList<>();
        for(Vehicle a: l){
            Position front = a.getFront();
            Position back = a.getBack();
            if(front.getRow() == back.getRow()){
                if(front.getCol() > back.getCol()){
                    for(int i = front.getCol(); i >= back.getCol(); i--){
                        list.add(Integer.toString(front.getRow()) + Integer.toString(i) + a.getSymbol());
                    }
                } else {
                    for(int i = back.getCol(); i >= front.getCol(); i--){
                        list.add(Integer.toString(front.getRow()) + Integer.toString(i) + a.getSymbol());
                    }
                }
            } else {
                if(front.getRow() > back.getRow()){
                    for(int i = front.getCol(); i >= back.getCol(); i--){
                        list.add(Integer.toString(front.getCol()) + Integer.toString(i) + a.getSymbol());
                    }
                } else {
                    for(int i = back.getCol(); i >= front.getCol(); i--){
                        list.add(Integer.toString(front.getCol()) + Integer.toString(i) + a.getSymbol());
                    }
                }
            }
        }
        return list;
    }


    private List<String> carLocations(){
        List<String> list = new LinkedList<>();
        try{
            FileReader read = new FileReader("data/" + filename);
            BufferedReader reader = new BufferedReader(read);
            String line = reader.readLine();
            while(line != ""){
                String[] helper = line.split(",");

            }
            reader.close();
        } catch (IOException e){
            System.out.println("IOException 2");
        }
    }
    */


    // Idea for carLocations is to find the locations of all existing cars and to map any row-column locations that should be considered
    // To do this, I need to find the front and back of every car. For a given car, if the front and back rows are equal, it would be
    // horizontal. If the columns are equal, then it's vertical. The key of the Map is going to be the rows, and the elements are going to be
    // a List of columns that are being taken up by a car. If the difference between the two non-equal factors (meaning if row is
    // equal then we're looking at column) is greater than one, then all values in between must also be added (to account for cars greater
    // than length 2). Return that, and create a string of a 6x6 board, looking at each (row,col) to see if a car is taking up that space.

    private Map<Integer, List<Integer>> carLocations(){
        Map<Integer, List<Integer>> locations = new HashMap<>();
        Set<Character> cs = cars.keySet();
        for(char ch: cs){
            Vehicle c = cars.get(ch);
            int frontr = c.getFront().getRow();
            int frontc = c.getFront().getCol();
            int backr = c.getBack().getRow();
            int backc = c.getBack().getCol();
            if(frontr == backr){
                if(frontc > backc){
                    List<Integer> l = new LinkedList<>();
                    for(int i = backc; i <= frontc; i++){
                        l.add(i);
                    }
                } else {

                }
            }
        }
        return locations;
    }


    @Override
    public String toString(){
        List<String> locations = this.carLocations();
        
        String board = "";
        for(int row = 0; row < BOARD_DIM; row++){
            for(int col = 0; col < BOARD_DIM; col++){
                boolean carfound = false;
                for(char a: cars.keySet()){
                    if(locations.contains(Integer.toString(row) + Integer.toString(col) + a)){
                        board += a;
                        carfound = true;
                        break;
                    }
                }
                if(!carfound){
                    board += EMPTY_SYMBOL;
                }
            }
            if(row + 1 < BOARD_DIM){
                board += "\n";
            }
        }
        return board;
    }

    public static void main(String[] args){
        try{
            RushHour rh = new RushHour("03_00.csv");
            System.out.println(rh);
        } catch(IOException e){
            System.out.println("IOException");
        }
    }
}