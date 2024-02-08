package Game;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Map extends Rectangle {

	/**
	 * Name:        David Alvarado
	 * Description: The Tile class to use the grass sprite tile for map
	 */
	
    public Map(int x, int y) {
        Image image = new Image("file:src/characters/grass2.png");
        setWidth(Main.TILE_SIZE);
        setHeight(Main.TILE_SIZE);
        setFill(new ImagePattern(image));
        relocate(x * Main.TILE_SIZE, y * Main.TILE_SIZE);
    }
    
}
