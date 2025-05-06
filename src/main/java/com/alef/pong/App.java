package com.alef.pong;

import com.alef.pong.core.GameEngine;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		new GameEngine(primaryStage).startGame();
	}

}
