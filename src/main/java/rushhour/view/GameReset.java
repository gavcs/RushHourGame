package rushhour.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import rushhour.model.RushHour;

public class GameReset implements EventHandler<ActionEvent> {
    public RushHour rushHour;
    public RushHourGUI gui;

    public GameReset(RushHour rushHour, RushHourGUI gui){

    }

    @Override
    public void handle(ActionEvent event) {
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }
}
