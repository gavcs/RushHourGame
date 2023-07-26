package rushhour.view;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import rushhour.model.Direction;
import rushhour.model.Position;
import rushhour.model.RushHour;
import rushhour.model.Vehicle;

public class RushHourGUI extends Application {
    private static final Image UPARROW = new Image("file:assets/uparrow.jpeg");
    private static final Image DOWNARROW = new Image("file:assets/downarrow.jpeg");
    private static final Image LEFTARROW = new Image("file:assets/leftarrow.jpeg");
    private static final Image RIGHTARROW = new Image("file:assets/rightarrow.jpeg");
    private static final Color TILE = Color.rgb(255, 255, 255);

    private RushHour rushHour;
    private Map<Character, Node> vehicles;
    
    @Override
    public void start(Stage stage) throws Exception {
        rushHour = new RushHour("03_00.csv");
        vehicles = new HashMap<>();
        GridPane gp = new GridPane();
        rushHour.registerObserver(new CarMover(vehicles, gp));
        for(int row = 0; row < RushHour.BOARD_DIM; row++){
            for(int col = 0; col < RushHour.BOARD_DIM; col++){
                Position pos = new Position(row, col);
                gp.add(labelMaker(TILE, (pos.equals(RushHour.EXIT_POS))? "EXIT": "", true), row, col);
            }
        }

        Scene scene = new Scene(gp);
        stage.setTitle("Rush Hour Game");
        stage.setScene(scene);
        stage.show();
    
    }

    private Label labelMaker(Color color, String text, boolean border){
        Label label = new Label(text);
        label.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        if(border == true){
            label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));
        }
        label.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        label.setPrefSize(100, 100);
        label.setTextFill(Color.WHITE);
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("Arial", 25));
        return label;
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
