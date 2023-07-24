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

    public Vehicle (Vehicle other) {
        this.symbol = other.getSymbol();
        Position otherFront = other.getFront();
        Position otherBack = other.getBack();
        this.front = new Position(otherFront.getRow(), otherFront.getCol());
        this.back = new Position(otherBack.getRow(), otherBack.getCol());

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
                    if(backCol - 1 >= 0 && frontCol - 1 >= 0){
                        this.back = new Position(backRow, backCol - 1);
                        this.front = new Position(frontRow, frontCol - 1);
                    } else {
                        throw new RushHourException("Cannot move outside of bounds.");
                    }
                } else {
                    if(backCol + 1 < RushHour.BOARD_DIM && frontCol + 1 < RushHour.BOARD_DIM){
                        this.back = new Position(backRow, backCol + 1);
                        this.front = new Position(frontRow, frontCol + 1);
                    } else {
                        throw new RushHourException("Cannot move outside of bounds.");
                    }
                }
            }
        //now need to do the opposite, to allow this to work for a vertical car
        } else {
            if(dir == Direction.LEFT || dir == Direction.RIGHT){
                throw new RushHourException("A vertical car cannot move left nor right.");
            } else {
                if(dir == Direction.UP){
                    if(backRow - 1 >= 0 && frontRow - 1 >= 0) {
                        this.back = new Position(backRow - 1, backCol);
                        this.front = new Position(frontRow - 1, frontCol);
                    } else {
                        throw new RushHourException("Cannot move outside of bounds.");
                    }
                } else {
                    if(backRow + 1 < RushHour.BOARD_DIM && frontRow + 1 < RushHour.BOARD_DIM){
                        this.back = new Position(backRow + 1, backCol);
                        this.front = new Position(frontRow + 1, frontCol);
                    } else {
                        throw new RushHourException("Cannot move outside of bounds.");
                    }
                }
            }
        }
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Vehicle){
            Vehicle o = (Vehicle)other;
            return (this.getSymbol() == (o.getSymbol())) && (this.getFront().equals(o.getFront())) && (this.getBack().equals(o.getBack()));
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        return "Vehicle{char = " + this.getSymbol() + ", front = " + this.getFront() + ", back = " + this.getBack() + "}";
    }

    public static Vehicle makeVehicle(){
        return new Vehicle('A', new Position(0,0), new Position(0, 3)); 
    }
}
