package rushhour.model;

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

    @Override
    public String toString(){
        return this.getSymbol() + ": " + this.getDirect();
    }
}
