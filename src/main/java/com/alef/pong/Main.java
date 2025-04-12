package com.alef.pong;

import com.alef.pong.game.Game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		/*
		 * Label label = new Label("Hello World!");
		 * StackPane root = new StackPane(label);
		 * Scene scene = new Scene(root, 400, 300);
		 * primaryStage.setTitle("Pong Game - Test");
		 * primaryStage.setScene(scene);
		 * primaryStage.show();
		 */
		Game game = new Game();
		game.start(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}