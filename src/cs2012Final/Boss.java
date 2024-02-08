package cs2012Final;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import static cs2012Final.Main.TILE_SIZE;

public class Boss extends StackPane {
	
	/**
	 * Name:        David Alvarado
	 * CIN:         401439516
	 * Course:      CS 2012
	 * Section:     31315
	 * Description: The Boss class to design the boss sprite
	 */

    public Boss(int x, int y) {
        Image bossImage = new Image("file:src//enemy//boss.gif");
        ImageView imageView = new ImageView(bossImage);

        // Set the size and position of the boss image within the boss node
        imageView.setFitWidth((TILE_SIZE ));
        imageView.setFitHeight((TILE_SIZE ));
        imageView.setTranslateX((TILE_SIZE * 0.100) / 2);
        imageView.setTranslateY((TILE_SIZE * 0.100));

        // Set the initial position of the boss node based on the given coordinates
        setTranslateX(x * TILE_SIZE);
        setTranslateY(y * TILE_SIZE);

        // Add the boss image view to the boss node
        getChildren().add(imageView);
    }

    public void chasePlayer(double playerX, double playerY) {
        // Calculate the distance between the boss and the player
        double dx = playerX - getTranslateX();
        double dy = playerY - getTranslateY();
        double magnitude = Math.sqrt(dx * dx + dy * dy);

        if (magnitude > 0) {
            double speed = 0.1; // Adjust the Boss's speed 

            // Calculate the velocity components based on the distance and speed
            double vx = speed * dx / magnitude;
            double vy = speed * dy / magnitude;

            // Update the boss position by adding the velocity components
            setTranslateX(getTranslateX() + vx);
            setTranslateY(getTranslateY() + vy);
        }
    }
}
