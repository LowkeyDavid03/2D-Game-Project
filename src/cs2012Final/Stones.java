package cs2012Final;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Stones extends Rectangle {

    public Stones(int x, int y) {
        Image image = new Image("file:src/floors/floor_1.png");
        setWidth(Main.TILE_SIZE);
        setHeight(Main.TILE_SIZE);
        setFill(new ImagePattern(image));
        relocate(x * Main.TILE_SIZE, y * Main.TILE_SIZE);
    }
}
