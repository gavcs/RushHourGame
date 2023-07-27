package rushhour.view;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import rushhour.model.Direction;
import rushhour.model.Position;
import rushhour.model.RushHour;
import rushhour.model.Vehicle;

public class RushHourGUI extends Application {
    public static final Image UPARROW = new Image("file:assets/uparrow.jpeg");
    public static final Image DOWNARROW = new Image("file:assets/downarrow.jpeg");
    public static final Image LEFTARROW = new Image("file:assets/leftarrow.jpeg");
    public static final Image RIGHTARROW = new Image("file:assets/rightarrow.jpeg");
    private static final Color TILE = Color.rgb(255, 255, 255);

    public RushHour rushHour;
    private Map<Character, Node> vehicles;
    private GridPane gp;
    
    @Override
    public void start(Stage stage) throws Exception {
        rushHour = new RushHour("03_00.csv");
        vehicles = new HashMap<>();
        gp = new GridPane();
        //CarMover is not functional
        rushHour.registerObserver(new CarMover(vehicles, gp, this));
        for(int row = 0; row < RushHour.BOARD_DIM; row++){
            for(int col = 0; col < RushHour.BOARD_DIM; col++){
                Position pos = new Position(row, col);
                gp.add(labelMaker(TILE, (pos.equals(RushHour.EXIT_POS))? "EXIT": "", true), row, col);
            }
        }
        gp.setVgap(2);
        gp.setHgap(2);

        Collection<Vehicle> v = rushHour.getVehicles();
        for(Vehicle car: v){
            int row = car.getBack().getRow();
            int col = car.getBack().getCol();
            int row2 = car.getFront().getRow() - row + 1;
            int col2 = car.getFront().getCol() - col + 1;
            char c = car.getSymbol();

            if(car.vert()){
                VBox vroom = verticalVehicle(car, CarColor.getColor(c), row2 - 1);
                gp.add(vroom, col, row, col2, row2);
                vehicles.put(car.getSymbol(), vroom);
            } else {
                HBox vroom = horizontalVehicle(car, CarColor.getColor(c), col2 - 1);
                gp.add(vroom, col, row, col2, row2);
                vehicles.put(car.getSymbol(), vroom);
            }
        }

        //start working on hints/UI stuff

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

    public Button carButtonMaker(Vehicle vehicle, Direction direction, Image image){
        ImageView i = new ImageView(image);
        i.setFitHeight(35);
        i.setFitWidth(35);
        Button button = new Button("", i);
        button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        button.setPrefSize(35, 35);
        //setOnAction is not functional
        button.setOnAction(new MoveHandler(vehicle, direction, rushHour));
        return button;
    }

    private VBox verticalVehicle(Vehicle vehicle, Color color, int scale){
        VBox vb = new VBox();
        vb.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        vb.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        vb.setMinSize(100, 100);
        Button up = carButtonMaker(vehicle, Direction.UP, UPARROW);
        Button down = carButtonMaker(vehicle, Direction.DOWN, DOWNARROW);
        vb.getChildren().addAll(up, down);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(100*scale);
        return vb;
    }

    public VBox verticalVehicle(Vehicle vehicle, Color color, int scale, Button up, Button down){
        VBox vb = new VBox();
        vb.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        vb.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        vb.setMinSize(100, 100);
        vb.getChildren().addAll(up, down);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(100*scale);
        return vb;
    }

    public HBox horizontalVehicle(Vehicle vehicle, Color color, int scale){
        HBox hb = new HBox();
        hb.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        hb.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        hb.setMinSize(100, 100);
        Button left = carButtonMaker(vehicle, Direction.LEFT, LEFTARROW);
        Button right = carButtonMaker(vehicle, Direction.RIGHT, RIGHTARROW);
        hb.getChildren().addAll(left, right);
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(100*scale);
        return hb;
    }

    public static void main(String[] args){
        launch(args);
    }
}
