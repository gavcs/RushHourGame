package rushhour.model;

public class Vehicle {
    private char symbol;
    private Position back;
    private Position front;

    public Vehicle(char symbol, Position back, Position front){
        this.symbol = symbol;
        this.back = back;
        this.front = front;
    }

    public char getSymbol(){
        return this.symbol;
    }
    
    public Position getBack(){
        return this.back;
    }

    public Position getFront(){
        return this.front;
    }

    public void move(Direction dir) throws RushHourException{
        //if(back pos x == front pos y): car can only move horizontally: if dir up or down, throw new exception: else work
        if(back.getRow() == front.getRow()){
            if(dir == Direction.UP || dir == Direction.DOWN){
                throw new RushHourException("A horizonal car cannot move up nor down.");
            } else {
                //get col: if col + dir.getCol() > number of total columns: throw exception: else work
                if()
            }
        }
    }
}
