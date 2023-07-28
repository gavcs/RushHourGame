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
    private RushHourGUI gui;

    public MoveHandler(Vehicle vehicle, Direction direction, RushHour rushHour, RushHourGUI gui){
        this.vehicle = vehicle;
        this.direction = direction;
        this.rushHour = rushHour;
        this.gui = gui;
    }

    @Override
    public void handle(ActionEvent event){
        if(!rushHour.gameOver()){
            Move move = new Move(vehicle.getSymbol(), direction);
            boolean valid = rushHour.safeMove(move);
            int fr = vehicle.getFront().getCol() + 1;
            int bl = vehicle.getBack().getCol();
            int bu = vehicle.getBack().getRow();
            int fd = vehicle.getFront().getRow() + 1;
            if(move.getDirect() == Direction.RIGHT){
                if(fr >= RushHour.BOARD_DIM){
                    gui.setStatus("edgeerr");
                } else {
                    gui.setStatus("crasherr");
                }
            } else if(move.getDirect() == Direction.LEFT){
                if(bl <= 0){
                    gui.setStatus("edgeerr");
                } else {
                    gui.setStatus("crasherr");
                }
            } else if(move.getDirect() == Direction.UP){
                if(bu <= 0){
                    gui.setStatus("edgeerr");
                } else {
                    gui.setStatus("crasherr");
                }
            } else if(move.getDirect() == Direction.DOWN){
                if(fd >= RushHour.BOARD_DIM){
                    gui.setStatus("edgeerr");
                } else {
                    gui.setStatus("crasherr");
                }
            }
            try {
                rushHour.moveVehicle(move);
                if(rushHour.gameOver()){
                    gui.setStatus("over");
                } else {
                    if(valid){
                        String m = CarColor.colorToString(CarColor.getColor(move.getSymbol())) + " moved " + move.getDirect().toString();
                        gui.statusMove(m);
                    }
                }
            } catch (RushHourException e) {}
        } else {
            gui.setStatus("gameoverr");
        }
    }
    
}
