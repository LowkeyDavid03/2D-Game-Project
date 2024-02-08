package Game;

import static Game.Main.TILE_SIZE;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Character extends StackPane {
	
	/**
	 * Name:        David Alvarado
	 * Description: The Character class to move and design
	 */


	private int boardWidth;
	private int boardHeight;
	public boolean pickUpSword;


	public Character(int x, int y, int boardWidth, int boardHeight) {
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		this.pickUpSword = false;

		// Initialize imageviews for the character
		Image gif = new Image("file:src//characters/idle.gif");
		ImageView imageView = new ImageView(gif);

		// Set the dimensions and position of the character
		imageView.setFitWidth(TILE_SIZE * 0.5 / 2);
		imageView.setFitHeight(TILE_SIZE * 0.8);
		imageView.setTranslateX((TILE_SIZE - TILE_SIZE ));
		imageView.setTranslateY((TILE_SIZE - TILE_SIZE));

		// Set the initial position of the character
		setTranslateX(x * TILE_SIZE);
		setTranslateY(y * TILE_SIZE);

		// Add the character to the stack pane
		getChildren().addAll(imageView);

		this.pickUpSword = false;  // The sword has not been picked up initially

	}

	public boolean pickUpSword() {
		return pickUpSword;
	}

	public void moveDown() {
		double y = getTranslateY();
		if (y + TILE_SIZE < boardHeight * TILE_SIZE) {
			setTranslateY(y + TILE_SIZE);
		}
	}

	public void moveUp() {
		double y = getTranslateY();
		if (y - TILE_SIZE >= 0) {
			setTranslateY(y - TILE_SIZE);
		}
	}

	public void moveLeft() {
		double x = getTranslateX();
		if (x - TILE_SIZE >= 0) {
			if (getScaleX() == 1) { // if facing right
				setScaleX(-1); // flip horizontally to face left
			} else { // if already facing left
				setTranslateX(x - TILE_SIZE); // move left
			}
		}
	}

	public void moveRight() {
		double x = getTranslateX();
		if (x + TILE_SIZE < boardWidth * TILE_SIZE) {
			if (getScaleX() == -1) { // if facing left
				setScaleX(1); // flip horizontally to face right
			} else { // if already facing right
				setTranslateX(x + TILE_SIZE); // move right
			}
		}
	}
}