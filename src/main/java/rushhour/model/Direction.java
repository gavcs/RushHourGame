package rushhour.model;

public enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    @Override
    public String toString(){
        if(this == Direction.UP){
            return "up";
        } else if(this == Direction.RIGHT){
            return "right";
        } else if(this == Direction.DOWN){
            return "down";
        } else {
            return "left";
        }
    }
}
