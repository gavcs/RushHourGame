package rushhour.model;

import rushhour.view.CarColor;

public class Move {
    private char symbol;
    private Direction dir;

    public Move (char symbol, Direction dir) {
        this.dir = dir;
        this.symbol = symbol;
    }

    public char getSymbol () {
        return this.symbol;
    }

    public Direction getDirect () {
        return this.dir;
    }

    public String hintHelper(){
        return "Move the " + CarColor.colorToString(CarColor.getColor(this.getSymbol())) + " vehicle " + this.getDirect().toString();
    }

    @Override
    public String toString(){
        return this.getSymbol() + ": " + this.getDirect();
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Move){
            Move o = (Move)other;
            return (this.getSymbol() == o.getSymbol()) && (this.getDirect() == o.getDirect());
        } else {
            return false;
        }
    }
}
