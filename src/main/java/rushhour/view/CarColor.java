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

    public static String colorToString(Color color){
        if(color == Color.ORANGE){
            return "orange";
        } else if(color == Color.AQUA){
            return "aqua";
        } else if(color == Color.BLACK){
            return "black";
        } else if(color == Color.CRIMSON){
            return "crimson";
        } else if(color == Color.DARKBLUE){
            return "dark blue";
        } else if(color == Color.PURPLE){
            return "purple";
        } else if(color == Color.RED){
            return "red";
        } else if(color == Color.YELLOW){
            return "yellow";
        } else if(color == Color.GREY){
            return "grey";
        } else if(color == Color.FIREBRICK){
            return "firebrick";
        } else if(color == Color.GREEN){
            return "green";
        } else if(color == Color.HOTPINK){
            return "hot pink";
        } else if(color == Color.INDIGO){
            return "indigo";
        } else {
            return "no color to return";
        }
    }
}
