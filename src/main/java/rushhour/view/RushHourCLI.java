package rushhour.view;

/* java imports */
import java.io.IOException;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;

import rushhour.model.Move;
/* module imports */
import rushhour.model.RushHour;

public class RushHourCLI {
    /* used for hint */
    private static final Random Ran = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a Filename: ");
        String filename = scanner.nextLine();
        RushHour rushHour;
        try {
            rushHour = new RushHour(filename);
            System.out.println(rushHour);
            boolean forceQuit = false;
                /* Displays commands after board is printed */
                System.out.println (" === List of Commands ===\n" +
                    "'help' | displays a list of commands\n" +
                    "'symbol + direction' | moves the vehicle(symbol) one space in the given direction\n" +
                    "'hint' | display an available move\n" +
                    "'reset' | resets the current filename board\n" +
                    "'quit' | quits the game\n" +
                    "-------------------------------------");
            while(!forceQuit) {
                System.out.print("Enter command: ");
                String input = scanner.nextLine();
                switch(input) {
                    case "help": 
                        System.out.println (" === Help ===\n"+
                        "'help' | displays a list of commands\n"+
                        "'reset' | resets the current filename board\n"+
                        "'hint' | display an available move\n"+
                        "'quit' | quits the game\n"+
                        "'move' | vehicle(symbol) + direction(up, down, left, right) - Moves the vehicle with the corresponding symbol in the specified direction\n"+
                        "'solve' | The game will solve itself\n"+
                        "------------------");
                        break;

                    case "hint":
                        if (!rushHour.gameOver()) {
                            Collection <Move> moves =  rushHour.getPossibleMoves();
                            int hint = Ran.nextInt(moves.size());
                            System.out.println("Hint: "+moves.toArray()[hint]);
                        } else {
                            System.out.println("Game Over. Reset or quit.");
                        }

                        break;

                    case "reset":
                        System.out.println ("Resetting Level");
                        rushHour = new RushHour(filename);
                        System.out.println(rushHour);

                        break;

                    case "quit":
                        System.out.println("Quitting");
                        forceQuit = true;

                        break;                     
                }
            }    
        }catch(IOException e){
            
        System.out.println("IOException, check filename format (ex: 03_00.csv).");

    }
    scanner.close(); 
    }

}

    

