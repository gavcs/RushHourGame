package rushhour.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import rushhour.model.Direction;
import rushhour.model.Move;
import rushhour.model.RushHour;
import rushhour.model.Vehicle;

public class MoveHandler implements EventHandler<ActionEvent> {
    private Vehicle vehicle;
    private Direction direction;
    private RushHour rushHour;

    public MoveHandler(Vehicle vehicle, Direction direction, RushHour rushHour){
        this.vehicle = vehicle;
        this.direction = direction;
        this.rushHour = rushHour;
    }

    @Override
    public void handle(ActionEvent event) {
        rushHour.moveVehicle(new Move(vehicle.getSymbol(), direction));
    }
    
}
