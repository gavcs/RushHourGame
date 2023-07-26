package rushhour.view;

import java.util.Map;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import rushhour.model.RushHourObserver;
import rushhour.model.Vehicle;

public class CarMover implements RushHourObserver {
    public Map<Character, Node> vehicles;
    public GridPane gp;

    public CarMover(Map<Character, Node> vehicles, GridPane gp){
        this.vehicles = vehicles;
        this.gp = gp;
    }

    @Override
    public void vehicleMoved(Vehicle vehicle) {
        //unimplemented
    }
}
