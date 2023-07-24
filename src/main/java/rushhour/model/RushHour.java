package rushhour.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class RushHour {
    public static final int BOARD_DIM = 6;
    public static final char RED_SYMBOL = 'R';
    public static final char EMPTY_SYMBOL = '-';
    public static final Position EXIT_POS = new Position (2, 5);
    private int moveCount;
    private RushHourObserver observer;
    
    /*
     * creating a map with the vehicles will allow for the moveVehicle method to move the car
     * we want it to move. An instance of Move will have a char, which looking at the different csv files,
     * each char has a unique character. When moveVehicle is called, it will pull the Vehicle associated
     * with the char in the Move move peram.
     */
    public Map<Character, Vehicle> cars;

    public RushHour (String filename) throws IOException {
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
        this.observer = null;
    }

    public void moveVehicle(Move move) throws RushHourException{
        //get the car using the character in Move move as the key
        char key = move.getSymbol();
        Vehicle carmove = cars.get(key);

        //move it using Vehicle.move()
        carmove.move(move.getDirect());

        //must add to the moveCount
        this.moveCount++;

        notifyObserver(carmove);
    }

    public Vehicle getVehicle(char symbol){
        return cars.get(symbol);
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

    public void registerObserver(RushHourObserver observer){
        this.observer = observer;
    }

    private void notifyObserver(Vehicle vehicle){
        if(this.observer != null){
            this.observer.vehicleMoved(vehicle);
        }
    }

    public Collection<Move> getPossibleMoves(){
        //create a Collection to return, and iterate through the existing cars
        Collection<Move> posMoves = new LinkedList<>();

        Map<Integer, Map<Integer, Character>> locations = this.carLocations();
        Set<Integer> row = locations.keySet();
        for(char key: cars.keySet()){

            //get the Vehicle and create Position variables to work with
            Vehicle a = cars.get(key);
            Position front = a.getFront();
            Position back = a.getBack();
            //checking if the car can move left or right, but checking which way the car is facing first is important 
            if(front.getRow() == back.getRow()){
                Set<Integer> col = locations.get(front.getRow()).keySet();
                if(front.getCol() > back.getCol()){
                    if(front.getCol() + 1 < BOARD_DIM){
                        if(!col.contains(front.getCol() + 1)){
                            posMoves.add(new Move(a.getSymbol(), Direction.RIGHT));
                        } else {
                            if(!row.contains(front.getRow())){
                                posMoves.add(new Move(a.getSymbol(), Direction.RIGHT));
                            }
                        }
                    }
                    if(back.getCol() - 1 >= 0){
                        if(!col.contains(back.getCol() - 1)){
                            posMoves.add(new Move(a.getSymbol(), Direction.LEFT));
                        } else {
                            if(!row.contains(back.getRow())){
                                posMoves.add(new Move(a.getSymbol(), Direction.RIGHT));
                            }
                        }
                    }
                } else {
                    if(back.getCol() + 1 < BOARD_DIM){
                        if(!col.contains(back.getCol() + 1)){
                            posMoves.add(new Move(a.getSymbol(), Direction.RIGHT));
                        } else {
                            if(!row.contains(back.getRow())){
                                posMoves.add(new Move(a.getSymbol(), Direction.RIGHT));
                            }
                        }
                    }
                    if(front.getCol() - 1 >= 0){
                        if(!col.contains(front.getCol() - 1)){
                            posMoves.add(new Move(a.getSymbol(), Direction.LEFT));
                        } else {
                            if(!row.contains(front.getRow())){
                                posMoves.add(new Move(a.getSymbol(), Direction.LEFT));
                            }
                        }
                    }
                }
            }

            //now looking to see if the car could move up or down, but also need to check where it's facing again
            if(front.getCol() == back.getCol()){
                if(front.getRow() > back.getRow()){
                    if(front.getRow() + 1 < BOARD_DIM){
                        if(!row.contains(front.getRow() + 1)){
                            posMoves.add(new Move(a.getSymbol(), Direction.DOWN));
                        } else {
                            if(!locations.get(front.getRow() + 1).keySet().contains(front.getCol())){
                                posMoves.add(new Move(a.getSymbol(), Direction.DOWN));
                            }
                        }
                    }
                    if(back.getRow() - 1 >= 0){
                        if(!row.contains(back.getRow() - 1)){
                            posMoves.add(new Move(a.getSymbol(), Direction.UP));
                        } else {
                            if(!locations.get(back.getRow() - 1).keySet().contains(back.getCol())){
                                posMoves.add(new Move(a.getSymbol(), Direction.UP));
                            }
                        }
                    }
                } else {
                    if(back.getRow() + 1 < BOARD_DIM){
                        if(!row.contains(back.getRow() + 1)){
                            posMoves.add(new Move(a.getSymbol(), Direction.DOWN));
                        } else {
                            if(!locations.get(back.getRow() + 1).keySet().contains(back.getCol())){
                                posMoves.add(new Move(a.getSymbol(), Direction.DOWN));
                            }
                        }
                    }
                    if(front.getRow() - 1 >= 0){
                        if(!row.contains(front.getRow() - 1)){
                            posMoves.add(new Move(a.getSymbol(), Direction.UP));
                        } else {
                            if(!locations.get(front.getRow() - 1).keySet().contains(front.getCol())){
                                posMoves.add(new Move(a.getSymbol(), Direction.UP));
                            }
                        }
                    }
                }
            }
        }
        return posMoves;
    }

    public int getMoveCount(){
        return this.moveCount;
    }

    // The idea for carLocations() is to create a map with the keys being the rows, and the elements being
    // another map with the keys as the columns that contain cars in those rows. Those are important values, but
    // in order for toString() to work, we also need the character associated with the row-column value. In
    // order to do this, the element in the map contained in the map is the character (or the getSymbol())
    // value of the car in that row/column.
    private Map<Integer, Map<Integer, Character>> carLocations(){
        Map<Integer, Map<Integer, Character>> locations = new HashMap<>();
        Set<Character> cs = cars.keySet();
        for(char ch: cs){
            Vehicle c = cars.get(ch);
            int frontr = c.getFront().getRow();
            int frontc = c.getFront().getCol();
            int backr = c.getBack().getRow();
            int backc = c.getBack().getCol();
            if(frontr == backr){
                if(!locations.containsKey(frontr)){
                    locations.put(frontr, new HashMap<>());
                }
                if(frontc > backc){
                    for(int i = backc; i <= frontc; i++){
                        locations.get(frontr).put(i, c.getSymbol());
                    }
                } else {
                    for(int i = frontc; i <= backc; i++){
                        locations.get(frontr).put(i, c.getSymbol());
                    }
                }
            } else {
                if(frontr > backr){
                    for(int i = backr; i <= frontr; i++){
                        if(!locations.containsKey(i)){
                            locations.put(i, new HashMap<>());
                            locations.get(i).put(backc, c.getSymbol());
                        } else {
                            locations.get(i).put(backc, c.getSymbol());
                        }
                    }
                } else {
                    for(int i = frontr; i <= backr; i++){
                        if(!locations.containsKey(i)){
                            locations.put(i, new HashMap<>());
                            locations.get(i).put(backc, c.getSymbol());
                        } else {
                            locations.get(i).put(backc, c.getSymbol());
                        }
                    }
                }
            }
        }
        return locations;
    }

    // The toString() utilizes the Map created in carLocations() to check every row-column to see
    // if the map contains them. If it does, it will get the character at that location, and add it
    // to the String that will eventually be returned.
    @Override
    public String toString(){
        Map<Integer, Map<Integer, Character>> locations = this.carLocations();
        
        String board = "";
        for(int row = 0; row < BOARD_DIM; row++){
            boolean containsRow = false;
            if(locations.keySet().contains(row)){
                containsRow = true;
            }
            for(int col = 0; col < BOARD_DIM; col++){
                if(containsRow == false){
                    board += "-";
                } else {
                    if(locations.get(row).containsKey(col)){
                        board += locations.get(row).get(col);
                    } else {
                        board += "-";
                    }
                }
            }
            if(row + 1 != BOARD_DIM){
                board += "\n";
            }
            
        }

        return board;
    }

    public static void main(String[] args){
        try{
            RushHour rh = new RushHour("03_00.csv");
            try {
                Set<Character> keyset = rh.cars.keySet();
                for(char c: keyset){
                    System.out.println(rh.cars.get(c));
                }
                System.out.println("\n" + rh.toString() + "\n");
                rh.moveVehicle(new Move('A', Direction.DOWN));
                Set<Character> keyset2 = rh.cars.keySet();
                for(char c: keyset2){
                    System.out.println(rh.cars.get(c));
                }
                System.out.println("\n" + rh.toString());
                
            } catch (RushHourException e) {
                e.printStackTrace();
            }
            
        } catch(IOException e){
            System.out.println("IOException");
        }
    }
}