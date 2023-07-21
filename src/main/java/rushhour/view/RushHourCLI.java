package rushhour.view;

/* java imports */
import java.io.IOException;
import java.util.Scanner;

/* module imports */
import rushhour.model.RushHour;

public class RushHourCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a Filename: ");
        String filename = scanner.nextLine();
        RushHour rushHour;
        try{
            rushHour = new RushHour(filename);
            System.out.println(rushHour);
            boolean forceQuit =  false;
                System.out.println (" === List of Commands ===\n" +
                    "'help' | displays a list of commands\n" +
                    "'symbol + direction' | moves the vehicle(symbol) one space in the given direction\n" +
                    "'hint' | display an available move\n" +
                    "'reset' | resets the current filename board\n" +
                    "'quit' | quits the game\n" +
                    "-------------------------------------");
            while(!forceQuit){
                System.out.print("Enter command: ");
                String input = scanner.nextLine();
                switch(input){
                    case "help": 
                        System.out.println(" === Help ===\n"+
                        "'help' | displays a list of commands\n"+
                        "'reset' | resets the current filename board\n"+
                        "'hint' | display an available move\n"+
                        "'quit' | quits the game\n"+
                        "'move' | vehicle(symbol) + direction(up, down, left, right) - Moves the vehicle with the corresponding symbol in the specified direction\n"+
                        "'solve' | The game will solve itself\n"+
                        "------------------");
                        break;

                    case "hint":

                    case "reset":

                    case "quit":
                        

                }
            }    
        }catch(IOException e){
            
        System.out.println("IOException, check filename format (ex: 03_00.csv).");

    }
    scanner.close(); 
    }

}

    

