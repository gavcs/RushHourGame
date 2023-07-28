package rushhour.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import rushhour.model.RushHour;

public class FileChanger implements EventHandler<ActionEvent> {
    public RushHour rushHour;
    public String filename;
    public GridPane gp;
    public RushHourGUI gui;

    public FileChanger(RushHour rushHour, String filename, GridPane gp, RushHourGUI gui){
        this.rushHour = rushHour;
        this.filename = filename;
        this.gp = gp;
        this.gui = gui;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            rushHour = new RushHour(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
