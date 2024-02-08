package Game;

import static Game.Main.TILE_SIZE;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Sword extends StackPane {
	
	/**
	 * Name:        David Alvarado
	 * CIN:         401439516
	 * Course:      CS 2012
	 * Section:     31315
	 * Description: The Sword class to display sprite design and make sword false as default
	 */

	public boolean pickedUp;

	public Sword(int x, int y) {

		Image sword = new Image("file:src//weapons/best.png");
		ImageView imageView = new ImageView(sword);

		imageView.setFitWidth((TILE_SIZE) / 2);
		imageView.setFitHeight((TILE_SIZE * 2) / 3);
		imageView.setTranslateX((TILE_SIZE * 0.100) / 2);
		imageView.setTranslateY((TILE_SIZE * 0.100));

		setTranslateX(x * TILE_SIZE);
		setTranslateY(y * TILE_SIZE);

		getChildren().addAll(imageView);

		this.pickedUp = false;  // The sword has not been picked up initially
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}
	
	
}
