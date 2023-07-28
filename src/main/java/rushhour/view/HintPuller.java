package rushhour.view;

import java.util.Collection;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import rushhour.model.Move;
import rushhour.model.RushHour;

public class HintPuller implements EventHandler<ActionEvent> {
    private RushHour rushHour;
    private RushHourGUI gui;

    public HintPuller(RushHour rushHour, RushHourGUI gui){
        this.rushHour = rushHour;
        this.gui = gui;
    }

    @Override
    public void handle(ActionEvent event) {
        Collection<Move> moves = rushHour.getPossibleMoves();
        Random rand = new Random();
        int i = rand.nextInt(moves.size());
        int num = 0;
        for(Move m: moves){
            if(num == i){
                gui.hint(m.hintHelper());
            }
        }
        gui.setStatus("hint");
    }
    
}
