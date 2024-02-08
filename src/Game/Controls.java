package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Controls extends StackPane {
	
	/**
	 * Name:        David Alvarado
	 * Description: The Tile class to use the grass sprite tile for map
	 */

    private Character piece;
    private Enemy[] enemies;
    private List<Sword> swords;
    private int boardWidth;
    private int boardHeight;
    private Group pieceGroup;
    private Group playerGroup;
    private int collectedSwords = 0;

    public boolean movementEnabled = true; // Added variable to track movement enabled/disabled state
    private boolean debugMode = false; // Added variable to track debug mode state

    public Controls(Character piece, Enemy[] enemies, Sword[] swords, int boardWidth, int boardHeight,
            Group pieceGroup, Group playerGroup) {
        this.piece = piece;
        this.enemies = enemies;
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.swords = new ArrayList<>(Arrays.asList(swords));
        this.pieceGroup = pieceGroup;
        this.playerGroup = playerGroup;

        pieceGroup.setVisible(false);
        getChildren().add(piece);

        setOnKeyPressed(e -> {
            if (!movementEnabled) return; // Movement disabled, ignore key press

            KeyCode kc = e.getCode();
            switch (kc) {
                case W:
                    handleSwordCollision();
                    if (checkCollisionEnemy()) {
                        teleportCharacter();
                    } else {
                        piece.moveUp();
                    }
                    break;

                case A:
                    handleSwordCollision();
                    piece.moveLeft();
                    break;

                case S:
                    handleSwordCollision();
                    piece.moveDown();
                    break;

                case D:
                    handleSwordCollision();
                    piece.moveRight();
                    break;

                default:
                    break;
            }
        });

        setFocusTraversable(true);

        // debug mode button
        Button debugButton = new Button("Debug Mode: OFF");
        debugButton.setLayoutX(10);
        debugButton.setLayoutY(10);
        getChildren().add(debugButton);

        //debug mode when the button is pressed
        debugButton.setOnAction(e -> {
            debugMode = !debugMode;
            if (debugMode) {
            	debugButton.setText("Debug Mode: ON");
                showDebugInformation();
            } else {
            	debugButton.setText("Debug Mode: OFF");
                hideDebugInformation();
                
            }
        });
    }

    private boolean checkCollisionEnemy() {
        for (Enemy enemy : enemies) {
            if (piece.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }

    private void teleportCharacter() {
        int newX = (int) (Math.random() * (boardWidth - 3));
        int newY = (int) (Math.random() * (boardHeight - 3));

        piece.setTranslateX(newX * Main.TILE_SIZE);
        piece.setTranslateY(newY * Main.TILE_SIZE);
    }

    private void handleSwordCollision() {
        for (Sword sword : swords) {
            if (piece.getBoundsInParent().intersects(sword.getBoundsInParent())) {
                if (!sword.isPickedUp()) {
                    sword.setPickedUp(true);
                    playerGroup.getChildren().remove(sword);
                    piece.pickUpSword();
                    collectedSwords++;

                    if (collectedSwords == 6) {
                    	
                        handleAllSwordsCollected();
                    }
                    break;
                }
            }
        }
    }

    private void handleAllSwordsCollected() {
        // Text node for the "YOU WIN" message
        Text youWinText = new Text("YOU WIN");
        youWinText.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        youWinText.setTextAlignment(TextAlignment.CENTER);
        youWinText.setY((boardHeight * Main.TILE_SIZE) / 2);

        // Add the "YOU WIN" message to the pieceGroup
        playerGroup.getChildren().add(youWinText);
        
    }

    private void showDebugInformation() {
        pieceGroup.setVisible(true);
        // Show any additional debug-related information or elements
    }

    private void hideDebugInformation() {
        pieceGroup.setVisible(false);
        // Hide any additional debug-related information or elements
    }
}
