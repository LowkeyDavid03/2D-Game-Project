package Game;

import static Game.Main.TILE_SIZE;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Trees extends StackPane {
	
	/**
	 * Name:        David Alvarado
	 * Description: The Trees sprite design 
	 */

	public Trees(int x, int y) {

		Image tree = new Image("file:src//characters/tree1.png");
		ImageView imageView = new ImageView(tree);

		imageView.setFitWidth((TILE_SIZE  * 2) / 3);
		imageView.setFitHeight((TILE_SIZE * 2) / 3);
		imageView.setTranslateX((TILE_SIZE * 0.100) / 2);
		imageView.setTranslateY((TILE_SIZE * 0.100));

		setTranslateX(x * TILE_SIZE);
		setTranslateY(y * TILE_SIZE);

		getChildren().addAll(imageView);
	}

}