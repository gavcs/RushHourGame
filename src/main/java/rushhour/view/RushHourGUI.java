package rushhour.view;

import java.util.Collection;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import rushhour.model.Direction;
import rushhour.model.RushHour;
import rushhour.model.Vehicle;

public class RushHourGUI extends Application {
    private static final Image UPARROW = new Image("file:assets/uparrow.jpeg");
    private static final Image DOWNARROW = new Image("file:assets/downarrow.jpeg");
    private static final Image LEFTARROW = new Image("file:assets/leftarrow.jpeg");
    private static final Image RIGHTARROW = new Image("file:assets/rightarrow.jpeg");
    private static final Color TILE = Color.rgb(255, 255, 255);

    private RushHour rushHour;
    
    @Override
    public void start(Stage stage) throws Exception {
        rushHour = new RushHour("03_00.csv");
        Collection<Vehicle> vehicles = rushHour.getVehicles();
        rushHour.registerObserver(new CarMover(vehicles, rushHour));
        GridPane gp = new GridPane();
        gp.setVgap(2);
        gp.setHgap(2);

        Scene scene = new Scene(gp);
        stage.setTitle("Rush Hour Game");
        stage.setScene(scene);
        stage.show();
    
    }

    private VBox verticalVehicle(Vehicle vehicle, Color color, int scale){
        VBox vb = new VBox();
        vb.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        vb.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        vb.setMinSize(100, 100);
        return vb;
    }

    public static void main(String[] args){
        launch(args);
    }
}
