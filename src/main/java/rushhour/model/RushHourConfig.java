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

    public RushHourConfig(RushHour rh){
        this.rh = rh;
        this.pastboards = new HashSet<>();
    }

    public RushHourConfig(RushHour rh, Set<String> pastboards){
        try {
            this.rh = new RushHour(rh);
            this.pastboards = new HashSet<>(pastboards);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public RushHour getRushHour(){return this.rh;}

    @Override
    public Collection<RushHourConfig> getSuccessors() {
        pastboards.add(this.toString());
        Collection<RushHourConfig> rhc = new LinkedList<>();
        try {
            RushHour temp = new RushHour(rh);
            Collection<Move> newmoves = temp.getPossibleMoves();
            for(Move m: newmoves){
                System.out.println(m);
                try {
                    RushHour other = new RushHour(rh);
                    try {
                        other.moveVehicle(m);
                        System.out.println(other);
                        if(!other.equals(rh)){
                            rhc.add(new RushHourConfig(other, pastboards));
                            System.out.println("Added!");
                        }
                    } catch(RushHourException e){}
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e){}
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

    public static void main(String[] args){
        try {
            RushHour rh = new RushHour("03_00.csv");
            RushHourConfig rhc = new RushHourConfig(rh);
            Backtracker<RushHourConfig> bt = new Backtracker<>(true);
            RushHourConfig solution = bt.solve(rhc);
            System.out.println(solution);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
