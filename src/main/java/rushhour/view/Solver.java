package rushhour.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import rushhour.model.RushHour;
import rushhour.model.RushHourConfig;

public class Solver implements EventHandler<ActionEvent> {
    private RushHour rushHour;

    public Solver(RushHour rushHour){
        this.rushHour = rushHour;
    }

    @Override
    public void handle(ActionEvent event) {
        RushHourConfig rhc = new RushHourConfig(rushHour);
        Backrac
    }
    
}
