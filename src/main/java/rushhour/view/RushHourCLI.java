package rushhour.view;

/* java imports */
import java.io.IOException;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;

/* module imports */
import rushhour.model.RushHour;
import rushhour.model.RushHourException;
import rushhour.model.Move;
import rushhour.model.Direction;

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

                    /* displays help menu */
                    case "help": 
                        System.out.println (" === Help ===\n"+
                        "'help' | displays a list of commands\n"+
                        "'reset' | resets the current filename board\n"+
                        "'hint' | display an available move\n"+
                        "'quit' | quits the game\n"+
                        "'move' | vehicle(symbol) + direction(up, down, left, right) - Moves the vehicle with the corresponding symbol in the specified direction\n"+
                        "------------------");
                        break;

                    /* hint command */
                    case "hint":
                        if (!rushHour.gameOver()) {
                            Collection <Move> moves =  rushHour.getPossibleMoves();
                            int hint = Ran.nextInt(moves.size());
                            System.out.println("Hint: "+moves.toArray()[hint]);
                        } else {
                            System.out.println("Game Over. Reset or quit.");
                        }

                        break;

                    /* reset command */
                    case "reset":
                        System.out.println ("Resetting Level");
                        rushHour = new RushHour(filename);
                        System.out.println(rushHour);

                        break;

                    /* quit command */
                    case "quit":
                        System.out.println ("Quitting");
                        forceQuit = true;

                        break;       
                    
                        default:
                        if (!rushHour.gameOver()) {
                            String[] inputSplit = input.split(" ");
                            if(inputSplit.length != 2) {
                                System.out.println ("Invalid command");
                            } else {
                                char symbol;
                                if(inputSplit[0].length() > 1) {
                                    System.out.println("Invalid symbol");
                                } else {
                                    symbol = inputSplit[0].toUpperCase().charAt(0);
                                    boolean hasDirection = false;
                                    for(Direction direction : Direction.values()) {
                                        if(direction.toString().toLowerCase().equals(inputSplit[1].toLowerCase())) {
                                            hasDirection = true;
                                        }
                                    }
                                    
                                    if (hasDirection) {
                                        Direction direction = Direction.valueOf(inputSplit[1].toUpperCase());
                                        try {
                                            rushHour.moveVehicle(new Move(symbol, direction));
                                        } catch (RushHourException e) {
                                            e.printStackTrace();
                                        }
                                        
                                    } else {
                                        System.out.println("Invalid direction, please enter Up, Down, Left, or Right");
                                    }
                                }
                            }
                        } else {
                            System.out.println("No moves left to be made. Please reset or quit.");
                        }
                        break;
                }
                System.out.println(rushHour);

                if (rushHour.gameOver()) {
                    System.out.println("You beat the level! It took "+rushHour.getMoveCount()+" moves!");
                }

            }    
        }catch(IOException e){
            
        System.out.println("IOException, check filename format (ex: 03_00.csv).");

    }
    scanner.close(); 
    }

}

    

