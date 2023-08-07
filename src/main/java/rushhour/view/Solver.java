package rushhour.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import rushhour.model.RushHourConfig;
import backtracker.Backtracker;

public class Solver implements EventHandler<ActionEvent> {
    private RushHourGUI gui;

    public Solver(RushHourGUI gui){
        this.gui = gui;
    }

    @Override
    public void handle(ActionEvent event) {
        RushHourConfig rhc = new RushHourConfig(gui.getRH());
        Backtracker<RushHourConfig> bt = new Backtracker<>(false);
        RushHourConfig solution = bt.solve(rhc);
        gui.solveHandle(solution.numMoves(), solution.rh());
    }
    
}
