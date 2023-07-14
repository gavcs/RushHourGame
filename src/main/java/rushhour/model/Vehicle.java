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
        int backCol = back.getCol();
        int backRow = back.getRow();
        int frontCol = front.getCol();
        int frontRow = front.getRow();
        //if(back pos x == front pos y): car can only move horizontally: if dir up or down, throw new exception: else work
        if(backRow == frontRow){
            if(dir == Direction.UP || dir == Direction.DOWN){
                throw new RushHourException("A horizonal car cannot move up nor down.");
            } else {
                //checking which direction to move the cars, then redefining the front and back positions
                if(dir == Direction.LEFT){
                    this.back = new Position(backRow, backCol - 1);
                    this.front = new Position(frontRow, frontCol - 1);
                } else {
                    this.back = new Position(backRow, backCol + 1);
                    this.front = new Position(frontRow, frontCol + 1);
                }
            }
        //now need to do the opposite, to allow this to work for a vertical car
        } else {
            if(dir == Direction.LEFT || dir == Direction.RIGHT){
                throw new RushHourException("A vertical car cannot move left nor right.");
            } else {
                if(dir == Direction.UP){
                    this.back = new Position(backRow + 1, backCol);
                    this.front = new Position(frontRow + 1, frontCol);
                } else {
                    this.back = new Position(backRow - 1, backCol);
                    this.front = new Position(frontRow - 1, frontCol);
                }
            }
        }
    }
}
