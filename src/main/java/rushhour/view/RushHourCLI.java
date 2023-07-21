package rushhour.view;

/* java imports */
import java.io.IOException;
import java.util.Scanner;

/* module imports */
import rushhour.model.RushHour;

public class RushHourCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Filename: ");
        String filename = scanner.nextLine();
        RushHour rushHour;
        try{
            rushHour = new RushHour(filename);
            System.out.println(rushHour);
        }catch(IOException e){
        System.out.println("IOException occured, check filename.");

    }
    scanner.close(); 

    }

}

    

