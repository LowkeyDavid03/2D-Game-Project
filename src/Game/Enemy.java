package Game;

import static Game.Main.TILE_SIZE;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Enemy extends StackPane {
	
	/**
	 * Name:        David Alvarado
	 * CIN:         401439516
	 * Course:      CS 2012
	 * Section:     31315
	 * Description: The Enemy class to design enemy sprite
	 */

	public Enemy(int x, int y) {

		Image enemy = new Image("file:src//enemy/monster-attack.gif");
		ImageView imageView = new ImageView(enemy);

		imageView.setFitWidth(TILE_SIZE * 2.0);
		imageView.setFitHeight(TILE_SIZE * 2.0);
		imageView.setTranslateX((TILE_SIZE - TILE_SIZE * 0.200) / 2);
		imageView.setTranslateY(TILE_SIZE);

		setTranslateX(x * TILE_SIZE);
		setTranslateY(y * TILE_SIZE);

		getChildren().addAll(imageView);
	}
	
	

}
