package rushhour.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import rushhour.model.Direction;
import rushhour.model.Move;
import rushhour.model.RushHour;
import rushhour.model.RushHourException;
import rushhour.model.Vehicle;

public class MoveHandler implements EventHandler<ActionEvent> {
    private Vehicle vehicle;
    private Direction direction;
    private RushHour rushHour;
    private boolean gameOver;

    public MoveHandler(Vehicle vehicle, Direction direction, RushHour rushHour, boolean gameOver){
        this.vehicle = vehicle;
        this.direction = direction;
        this.gameOver = gameOver;
        this.rushHour = rushHour;
    }

    @Override
    public void handle(ActionEvent event) {
        if(gameOver == false){
            try {
                rushHour.moveVehicle(new Move(vehicle.getSymbol(), direction));
            } catch (RushHourException e) {
                e.printStackTrace();
            }
        }
    }
    
}
