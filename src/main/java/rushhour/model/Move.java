package rushhour.model;

public class Move {
    private char symbol;
    private Direction direct;

    public Move (char symbol, Direction direct) {
        this.direct = direct;
        this.symbol = symbol;
    }

    public char getSymbol () {
        return this.symbol;
    }

    public Direction getDirect () {
        return this.direct;
    }
}
