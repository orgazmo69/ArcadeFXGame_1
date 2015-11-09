package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameAplication extends Application {

	@Override
	public void start(Stage primaryStage) {
		BaseGame game = new Game();
		game.start(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
