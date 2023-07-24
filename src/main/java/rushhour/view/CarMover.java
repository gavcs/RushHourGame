package rushhour.view;

import javafx.scene.image.ImageView;
import rushhour.model.Move;
import rushhour.model.RushHour;
import rushhour.model.RushHourObserver;
import rushhour.model.Vehicle;

public class CarMover implements RushHourObserver {
    public ImageView view;
    public RushHour rh;

    public CarMover(ImageView view, RushHour rh){
        this.view = view;
        this.rh = rh;
    }


    @Override
    public void vehicleMoved(Vehicle vehicle) {
        //unimplemented, still figuring out, just putting a few things in
    }
}
