package rushhour.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GameReset implements EventHandler<ActionEvent> {
    public RushHourGUI gui;

    public GameReset(RushHourGUI gui){
        this.gui = gui;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            gui.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
