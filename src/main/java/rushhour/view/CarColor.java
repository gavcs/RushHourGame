package rushhour.view;

import javafx.scene.paint.Color;

public class CarColor {
    public static Color getColor(char c){
        if(c == 'O'){
            return Color.ORANGE;
        } else if(c == 'A'){
            return Color.AQUA;
        } else if (c == 'B'){
            return Color.BLACK;
        } else if (c == 'C'){
            return Color.CRIMSON;
        } else if (c == 'D'){
            return Color.DARKBLUE;
        } else if (c == 'P'){
            return Color.PURPLE;
        } else if (c == 'R'){
            return Color.RED;
        } else if (c == 'Q'){
            return Color.YELLOW;
        } else if (c == 'E'){
            return Color.GREY;
        } else if (c == 'F'){
            return Color.FIREBRICK;
        } else if (c == 'G'){
            return Color.GREEN;
        } else if (c == 'H'){
            return Color.HOTPINK;
        } else {
            return Color.INDIGO;
        }
    }
}
