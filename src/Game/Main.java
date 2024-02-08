package Game;

import java.util.Optional;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {

	/**
	 * Name:        David Alvarado
	 * Description: The Main class which sets up the World/Map
	 */

	public static final int TILE_SIZE = 100;
	public static Map[][] board;

	private Group tileGroup = new Group();
	private Group pieceGroup = new Group();
	private Group playerGroup = new Group();
	private Character player;
	private Boss boss;
	private int collectedItemCount = 0;
	private Controls controls; // Add a reference to the Controls object


	@Override
	public void start(Stage primaryStage) {
		// Prompt the user to enter the board height and width
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("MAP SIZE");
		dialog.setHeaderText(null);
		dialog.setContentText("Enter size for map (e.g., 5x7): ");
		Optional<String> result = dialog.showAndWait();

		// Parse the user input and create the board
		int width = 0;
		int height = 0;

		if (result.isPresent()) {
			String[] tokens = result.get().split("x");
			if (tokens.length == 2) {
				try {
					width = Integer.parseInt(tokens[0].trim());
					height = Integer.parseInt(tokens[1].trim());
				} catch (NumberFormatException e) {
					System.out.println("Error: " + e.getMessage());
				}
			}
		}

		// Create the board
		board = new Map[width][height];
		Pane root = new Pane();
		root.setPrefSize(width * TILE_SIZE, height * TILE_SIZE);
		root.getChildren().addAll(tileGroup, pieceGroup, playerGroup);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Map tile = new Map(x, y);
				board[x][y] = tile;
				tileGroup.getChildren().add(tile);
			}
		}

		player = makeCharacter(width, height);
		boss = makeBoss(width, height);
		Enemy enemy1 = makeEnemy(width, height);
		Enemy enemy2 = makeEnemy(width, height);
		Enemy enemy3 = makeEnemy(width, height);
		Enemy enemy4 = makeEnemy(width, height);
		Enemy enemy5 = makeEnemy(width, height);
		Enemy enemy6 = makeEnemy(width, height);
		Enemy[] enemies = { enemy1, enemy2, enemy3, enemy4,enemy5, enemy6 };
		Sword sword1 = makeSword(width, height);
		Sword sword2 = makeSword(width, height);
		Sword sword3 = makeSword(width, height);
		Sword sword4 = makeSword(width, height);
		Sword sword5 = makeSword(width, height);
		Sword sword6 = makeSword(width, height);
		Sword[] swords = { sword1, sword2, sword3 ,sword4,sword5,sword6};
		Trees tree1 = makeTree(width, height);
		Trees tree2 = makeTree(width, height);
		Trees tree3 = makeTree(width, height);
		Trees tree4 = makeTree(width, height);
		Trees tree5 = makeTree(width, height);
		Trees tree6 = makeTree(width, height);
		Trees tree7 = makeTree(width, height);
		Trees tree8 = makeTree(width, height);
		Trees tree9 = makeTree(width, height);

		pieceGroup.getChildren().addAll(enemy1, enemy2, enemy3, enemy4,enemy5, enemy6);
		playerGroup.getChildren().addAll(boss,sword1, sword2,sword3 ,sword4,sword5,sword6,tree1,
				tree2, tree3, tree4, tree5,tree6,tree7,tree8,tree9);


		controls = new Controls(player,enemies, swords, width, height, pieceGroup,playerGroup);
		
		root.getChildren().add(controls);

		Scene scene = new Scene(root);

		primaryStage.setTitle("TRAVEL TO THE FUTURE");
		primaryStage.setScene(scene);
		primaryStage.show();



		// Game loop
		AnimationTimer gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (checkCollisionBoss()) {
					stop(); // Stop the game loop
					handleGameOver();
				} else {
					boss.chasePlayer(player.getTranslateX(), player.getTranslateY());
				}
			}
		};
		gameLoop.start();
	}


	private boolean checkCollisionBoss() {
		if (player.getBoundsInParent().intersects(boss.getBoundsInParent())) {
			return true;
		}
		return false;
	}

	private void handleGameOver() {
		if (collectedItemCount != 3) {
			// Create a Text node for the "YOU WIN" message
			Text gameOverText = new Text("Game Over");
			gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, 60));
			gameOverText.setTextAlignment(TextAlignment.CENTER);
			gameOverText.setX((board.length * TILE_SIZE) / 2 - gameOverText.getLayoutBounds().getWidth() / 2);
			gameOverText.setY((board[0].length * TILE_SIZE) / 2);

			// Add the "Game Over" message to the pieceGroup
			playerGroup.getChildren().add(gameOverText);
		}
	}

	private Character makeCharacter(int boardWidth, int boardHeight) {
		Random rand = new Random();
		int x = rand.nextInt(boardWidth);
		int y = rand.nextInt(boardHeight);
		return new Character(x, y, boardWidth, boardHeight);
	}

	private Boss makeBoss(int boardWidth, int boardHeight) {
		Random rand = new Random();
		int x = rand.nextInt(boardWidth);
		int y = rand.nextInt(boardHeight);
		return new Boss(x, y);
	}

	private Enemy makeEnemy(int boardWidth, int boardHeight) {
		Random rand = new Random();
		int x = rand.nextInt(boardWidth - 3);
		int y = rand.nextInt(boardHeight - 3);
		return new Enemy(x, y);
	}

	private Sword makeSword(int boardWidth, int boardHeight) {
		Random rand = new Random();
		int x = rand.nextInt(boardWidth);
		int y = rand.nextInt(boardHeight);
		return new Sword(x, y);
	}

	private Trees makeTree(int boardWidth, int boardHeight) {
		Random rand = new Random();
		int x = rand.nextInt(boardWidth);
		int y = rand.nextInt(boardHeight);
		return new Trees(x, y);
	}

	public static void main(String[] args) {
		launch(args);
	}
}


