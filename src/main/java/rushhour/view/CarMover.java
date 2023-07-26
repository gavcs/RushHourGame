package rushhour.view;

import java.util.Collection;

import javafx.scene.image.ImageView;
import rushhour.model.Move;
import rushhour.model.RushHour;
import rushhour.model.RushHourObserver;
import rushhour.model.Vehicle;

public class CarMover implements RushHourObserver {
    public Collection<Vehicle> vehicles;
    public RushHour rh;

    public CarMover(Collection<Vehicle> vehicles, RushHour rh){
        this.vehicles = vehicles;
        this.rh = rh;
    }


    @Override
    public void vehicleMoved(Vehicle vehicle) {
        
    }
}
