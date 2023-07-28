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
    private boolean gameOver;

    public HintPuller(RushHour rushHour, RushHourGUI gui, boolean gameOver){
        this.rushHour = rushHour;
        this.gui = gui;
        this.gameOver = gameOver;
    }

    @Override
    public void handle(ActionEvent event) {
        if(gameOver == false){
            Collection<Move> moves = rushHour.getPossibleMoves();
            Random rand = new Random();
            int i = rand.nextInt(moves.size());
            int n = 0;
            for(Move move: moves){
                if(i == n){
                    gui.hint(move.hintHelper());
                    break;
                }
                n++;
            }
        }
    }
}
