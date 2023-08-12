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
    private int nummoves;

    public RushHourConfig(RushHour rh){
        this.rh = rh;
        this.pastboards = new HashSet<>();
        this.moves = new LinkedList<>();
        this.nummoves = 0;
    }

    public RushHourConfig(RushHour rh, Set<String> pastboards, Collection<Move> moves, int nummoves){
        try {
            this.rh = new RushHour(rh, moves);
            this.moves = moves;
            this.pastboards = new HashSet<>(pastboards);
            this.nummoves = nummoves;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int numMoves(){return this.nummoves;}
    public Collection<Move> moves(){return this.moves;}
    public RushHour rh(){return this.rh;}

    @Override
    public Collection<RushHourConfig> getSuccessors() {
        pastboards.add(this.toString());
        Collection<RushHourConfig> rhc = new LinkedList<>();
        Collection<Move> newmoves = rh.getPossibleMoves();
        for(Move m: newmoves){
            Collection<Move> nmove = new LinkedList<>();
            for(Move m2: moves){
                nmove.add(m2);
            }
            nmove.add(m);
            try {
                RushHour other = new RushHour(rh, nmove);
                try {
                    other.moveVehicle(m);
                    rhc.add(new RushHourConfig(other, pastboards, nmove, (nummoves + 1)));
                } catch(RushHourException e){}
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return rhc;
    }

    @Override
    public boolean isValid() {
        return !pastboards.contains(this.rh.toString());
    }

    @Override
    public boolean isGoal() {
        return rh.gameOver();
    }

    @Override
    public String toString(){
        return this.rh.toString();
    }

    public static RushHourConfig solve(RushHour rushHour){
        RushHourConfig rhc = new RushHourConfig(rushHour);
        Backtracker<RushHourConfig> bt = new Backtracker<>(false);
        return bt.solve(rhc);
    }

    public static void main(String[] args){
        try {
            RushHour rh = new RushHour("03_00.csv");
            RushHourConfig rhc = new RushHourConfig(rh);
            Backtracker<RushHourConfig> bt = new Backtracker<>(false);
            RushHourConfig solution = bt.solve(rhc);
            System.out.println(solution + "\n" + solution.nummoves);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
