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
    private RushHourGUI gui;

    public MoveHandler(Vehicle vehicle, Direction direction, RushHour rushHour, boolean gameOver, RushHourGUI gui){
        this.vehicle = vehicle;
        this.direction = direction;
        this.gameOver = gameOver;
        this.rushHour = rushHour;
        this.gui = gui;
    }

    @Override
    public void handle(ActionEvent event) {
        if(gameOver == false){
            try {
                Move move = new Move(vehicle.getSymbol(), direction);
                rushHour.moveVehicle(move);
                if(!rushHour.gameOver()){
                    String m = CarColor.colorToString(CarColor.getColor(move.getSymbol())) + " moved " + move.getDirect().toString();
                    gui.statusMove(m);
                } else {
                    gui.setStatus("over");
                }
            } catch (RushHourException e) {
                e.printStackTrace();
            }
        }
    }
    
}
