package rushhour.model;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import backtracker.Backtracker;
import backtracker.Configuration;

public class RushHourConfig implements Configuration<RushHourConfig> {
    private RushHour rh;
    private Set<String> pastboards;
    private Collection<Move> moves;

    public RushHourConfig(RushHour rh){
        this.rh = rh;
        this.pastboards = new HashSet<>();

        this.moves = new LinkedList<>();
    }

    public RushHourConfig(RushHour rh, Set<String> pastboards, Collection<Move> moves){
        this.rh = rh;
        this.pastboards = pastboards;
        this.moves = moves;
    }

    @Override
    public Collection<RushHourConfig> getSuccessors() {
        Collection<RushHourConfig> rhc = new LinkedList<>();
        Collection<Move> newmoves = this.rh.getPossibleMoves();
        for(Move m: newmoves){
            RushHour other = new RushHour(rh);
            LinkedList<Move> omoves = new LinkedList<>(moves);
            HashSet<String> oboards = new HashSet<>(pastboards);
            try {
                other.moveVehicle(m);
                if(!pastboards.contains(other.toString())){
                    omoves.add(m);
                    oboards.add(other.toString());
                    rhc.add(new RushHourConfig(other, oboards, omoves));
                }
            } catch (RushHourException e) {
                e.printStackTrace();
            }
        }
        return rhc;
    }
    @Override
    public boolean isValid() {
        return true;
    }
    @Override
    public boolean isGoal() {
        return rh.gameOver();
    }

    public static void main(String[] args){
        try {
            RushHour rh = new RushHour("03_00.csv");
            RushHourConfig rhc = new RushHourConfig(rh);
            Backtracker<RushHourConfig> bt = new Backtracker<>(true);
            bt.solve(rhc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
