package rushhour.view;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
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
import javafx.scene.layout.Priority;
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
    public static final Image RIGHTARROW = new Image("file:assets/rightarrow.jpg");
    private static final Color TILE = Color.rgb(255, 255, 255);

    private RushHour rushHour;
    private Map<Character, Node> vehicles;
    private GridPane gp;
    private Label moves;
    private Label status;
    private Label hint;
    private Button getHint;
    private boolean gameOver;
    private Button button;
    private Collection<Vehicle> cars;
    private Button solve;
    
    @Override
    public void start(Stage stage) throws Exception {
        gameOver = false;
        rushHour = new RushHour("03_00.csv");
        vehicles = new HashMap<>();
        gp = new GridPane();
        rushHour.registerObserver(new CarMover(vehicles, gp, this, rushHour));
        for(int row = 0; row < RushHour.BOARD_DIM; row++){
            for(int col = 0; col < RushHour.BOARD_DIM; col++){
                Position pos = new Position(col, row);
                gp.add(labelMaker(TILE, (pos.equals(RushHour.EXIT_POS))? "EXIT": "", true), row, col);
            }
        }
        gp.setVgap(2);
        gp.setHgap(2);


        cars = rushHour.getVehicles();
        for(Vehicle car: cars){
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

        VBox gamenmoves = new VBox();
        HBox bottom = new HBox();

        moves = new Label("Moves Made: " + rushHour.getMoveCount());
        moves.setAlignment(Pos.CENTER_LEFT);
        moves.setPrefHeight(30);
        moves.setFont(new Font("Arial", 20));
        moves.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

        solve = new Button("Solve Game");
        solve.setAlignment(Pos.CENTER_RIGHT);
        solve.setPrefHeight(30);
        solve.setFont(new Font("Arial", 20));
        solve.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

        button = new Button("Reset Game");
        button.setAlignment(Pos.CENTER_RIGHT);
        button.setPrefHeight(30);
        button.setFont(new Font("Arial", 20));
        button.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        button.setOnAction(new GameReset(this));

        bottom.getChildren().addAll(moves, button);
        bottom.setPrefHeight(30);
        bottom.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));
        bottom.setPrefWidth(600);
        bottom.setMaxSize(620, 30);
        HBox.setHgrow(moves, Priority.ALWAYS);
        gamenmoves.getChildren().addAll(gp, bottom);

        HBox hintbox = new HBox();

        getHint = new Button("Hint");
        getHint.setPrefHeight(30);
        getHint.setFont(new Font("Arial", 20));
        getHint.setAlignment(Pos.CENTER_LEFT);
        getHint.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        getHint.setOnAction(new HintPuller(rushHour, this, gameOver));

        hint = new Label("...");
        hint.setPrefHeight(30);
        hint.setFont(new Font("Arial", 20));
        hint.setAlignment(Pos.CENTER_LEFT);
        hint.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        HBox.setHgrow(hint, Priority.ALWAYS);

        hintbox.getChildren().addAll(getHint, hint);
        hintbox.setMaxSize(620, 30);
        hintbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));

        VBox all = new VBox();
        status = new Label("Game Start");
        status.setFont(new Font("Arial", 25));
        status.setPrefHeight(40);
        status.setAlignment(Pos.CENTER);
        status.setMaxSize(620, 40);
        status.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));
        all.getChildren().addAll(status, hintbox, gamenmoves);

        Scene scene = new Scene(all);
        stage.setTitle("Rush Hour Game");
        stage.setScene(scene);
        stage.show();
    }

    public void hint(String thehint){
        if(!gameOver){
            hint.setText(thehint);
        }
    }

    public void reset() throws Exception {
        Collection<Node> oldcars = new LinkedList<>();
        for(char c: vehicles.keySet()){
            oldcars.add(vehicles.get(c));
        }
        vehicles = new HashMap<>();
        rushHour = new RushHour("03_00.csv");
        cars = rushHour.getVehicles();
        setStatus("reset");
        moveCars(cars, oldcars);
        rushHour.registerObserver(new CarMover(vehicles, gp, this, rushHour));
    }

    public void moveCars(Collection<Vehicle> cars, Collection<Node> oldcars){
        for(Node n: oldcars){
            gp.getChildren().remove(n);
        }
        for(Vehicle car: cars){
            int row = car.getBack().getRow();
            int col = car.getBack().getCol();
            int row2 = car.getFront().getRow() - row + 1;
            int col2 = car.getFront().getCol() - col + 1;
            char c = car.getSymbol();

            if(car.vert()){
                VBox vroom = verticalVehicle(car, CarColor.getColor(c), row2 - 1);
                vehicles.put(car.getSymbol(), vroom);
            } else {
                HBox vroom = horizontalVehicle(car, CarColor.getColor(c), col2 - 1);
                vehicles.put(car.getSymbol(), vroom);
            }
        }
        for(Vehicle vehicle: cars){
            char c = vehicle.getSymbol();
            Node node = vehicles.get(c);
            gp.getChildren().remove(node);
            vehicles.remove(c);
    
            int row = vehicle.getBack().getRow();
            int col = vehicle.getBack().getCol();
            int row2 = vehicle.getFront().getRow() - row + 1;
            int col2 = vehicle.getFront().getCol() - col + 1;
    
            if(vehicle.vert()){
                gp.add(node, col, row, col2, row2);
                vehicles.put(vehicle.getSymbol(), node);
            } else {
                gp.add(node, col, row, col2, row2);
                vehicles.put(vehicle.getSymbol(), node);
            }
        }
    }

    public void moveMade(){
        moves.setText("Moves Made: " + rushHour.getMoveCount());
    }

    public void statusMove(String move){
        status.setText(move);
    }

    public void setStatus(String state){
        if(state.equals("over")){
            status.setText("Game over. You won in " + rushHour.getMoveCount() + " moves!");
            hint.setText("Great job!");
            gameOver = true;
        } else if(state.equals("reset")){
            moves.setText("Moves Made: " + rushHour.getMoveCount());
            status.setText("Game Reset");
            hint.setText("...");
            getHint.setOnAction(new HintPuller(rushHour, this, gameOver));
            gameOver = false;
        } else if(state.equals("gameoverr")){
            status.setText("Game is finished, you can't move anymore.");
            hint.setText("Try hitting reset to play again.");
        } else if(state.equals("edgeerr")){
            status.setText("Cannot move car outside of bounds.");
        } else if(state.equals("crasherr")){
            status.setText("Cannot move car into another car");
        }
    }

    private Label labelMaker(Color color, String text, boolean border){
        Label label = new Label(text);
        label.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        if(border == true){
            label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));
        }
        label.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        label.setPrefSize(100, 100);
        label.setTextFill(Color.BLACK);
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
        button.setOnAction(new MoveHandler(vehicle, direction, rushHour, this));
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