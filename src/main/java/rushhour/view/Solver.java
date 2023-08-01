package rushhour.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import rushhour.model.RushHour;

public class Solver implements EventHandler<ActionEvent> {
    private RushHour rushHour;

    public Solver(RushHour rushHour){
        this.rushHour = rushHour;
    }

    @Override
    public void handle(ActionEvent event) {
        rushHour.solve();
    }
    
}
