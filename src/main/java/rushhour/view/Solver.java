package rushhour.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import rushhour.model.RushHourConfig;

public class Solver implements EventHandler<ActionEvent> {
    private RushHourGUI gui;

    public Solver(RushHourGUI gui){
        this.gui = gui;
    }

    @Override
    public void handle(ActionEvent event) {
        RushHourConfig solution = RushHourConfig.solve(gui.getRH());
        if(solution == null){
            gui.solveHandle(0, null, false);
        } else {
            gui.solveHandle(solution.numMoves(), solution.rh(), true);
        }
    }
    
}
