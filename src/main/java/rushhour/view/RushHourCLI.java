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
                    "'help' | display a list of commands\n" +
                    "'symbol + direction' | moves the vehicle(symbol) one space in the given direction\n" +
                    "'hint' | display an available move\n" +
                    "'reset' | resets the current filename board\n" +
                    "'quit' | quits the game\n" +
                    "-------------------------------------");
               
        }catch(IOException e){
            
        System.out.println("IOException, check filename format (ex: 03_00.csv).");

    }
    scanner.close(); 
    }

}

    

