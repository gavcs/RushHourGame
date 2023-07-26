package rushhour.view;

import java.util.Map;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import rushhour.model.Move;
import rushhour.model.RushHour;
import rushhour.model.RushHourObserver;
import rushhour.model.Vehicle;

public class CarMover implements RushHourObserver {
    public Map<Character, Node> vehicles;
    public GridPane gp;
    public RushHourGUI gui;

    public CarMover(Map<Character, Node> vehicles, GridPane gp, RushHourGUI gui){
        this.vehicles = vehicles;
        this.gp = gp;
        this.gui = gui;
    }

    @Override
    public void vehicleMoved(Vehicle vehicle) {
        char c = vehicle.getSymbol();
        Node node = vehicles.get(c);
        gp.getChildren().remove(node);
        vehicles.remove(c);

        int row = vehicle.getBack().getRow();
        int col = vehicle.getBack().getCol();
        int row2 = vehicle.getFront().getRow() - row + 1;
        int col2 = vehicle.getFront().getCol() - col + 1;

        if(vehicle.vert()){
            VBox vroom = gui.verticalVehicle(vehicle, CarColor.getColor(vehicle.getSymbol()), 100);
            gp.add(vroom, col, row, col2, row2);
            vehicles.put(vehicle.getSymbol(), vroom);
        } else {
            HBox vroom = gui.horizontalVehicle(vehicle, CarColor.getColor(vehicle.getSymbol()), 100);
            gp.add(vroom, col, row, col2, row2);
            vehicles.put(vehicle.getSymbol(), vroom);
        }
    }
}
