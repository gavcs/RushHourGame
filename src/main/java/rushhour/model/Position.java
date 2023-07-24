package rushhour.model;

public class Position {
    private int row;
    private int col;

    public Position (int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow () {
        return row;
    }

    public int getCol () {
        return col;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Position){
            Position o = (Position)other;
            return (this.getRow() == o.getRow()) && (this.getCol() == o.getCol());
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        return "(" + this.getRow() + ", " + this.getCol() + ")";
    }

}
