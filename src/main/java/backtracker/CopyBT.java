package backtracker;

import java.util.Collection;

/**
 * This class represents the classic recursive backtracking algorithm.
 * It has a solver that can take a valid configuration and return a
 * solution, if one exists.
 * 
 * @author GCCIS Faculty
 */
public class CopyBT<C extends Configuration<C>> {
    /*
     * Should debug output be enabled?
     */
    private boolean debug;
    
    /**
     * Initialize a new backtracker
     * 
     * @param debug Is debugging output enabled?
     */
    public CopyBT(boolean debug) {
        this.debug = debug;
        if (this.debug) {
            System.out.println("backtracker.Backtracker debugging enabled...");
        }
    }
    
    /**
     * A utility routine for printing out various debug messages.
     * 
     * @param msg The type of config being looked at (current, goal, 
     *  successor, e.g.)
     * @param config The config to display
     */
    private void debugPrint(String msg, C config) {
        if (this.debug) {
            System.out.println(msg + ":\n" + config);
        }
    }
    
    /**
     * Try find a solution, if one exists, for a given configuration.
     * 
     * @param config A valid configuration
     * @return A solution config, or null if no solution
     */
    public C solve(C config) {
        long start = System.currentTimeMillis();
        debugPrint("Current config", config);
        if (config.isGoal()) {
            debugPrint("\tGoal config", config);
            return config;
        } else {
            Collection<C> successors = config.getSuccessors();
            for (C child : successors) {
                if((int)(System.currentTimeMillis() - start)/1000 <= 30){
                    if (child.isValid()) {
                        debugPrint("\tValid successor", child);
                        C sol = solve(child);
                        if(sol != null) {
                            return sol;
                        }
                    } else {
                        debugPrint("\tInvalid successor", child);
                    }
                } else {
                    return null;
                }
            }
            // implicit backtracking happens here
        } 
        return null;
    }
}
