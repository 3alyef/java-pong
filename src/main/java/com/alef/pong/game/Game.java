package com.alef.pong.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game {
	private static final String TITLE = "Pong Game";
	private final GameCanvas canvas;

	public Game() {
		this.canvas = new GameCanvas();
	}

	public void start(Stage stage) {
		this.canvas.startScreenMonitoring(stage);
		Scene scene = new Scene(new Group(this.canvas));

		stage.setTitle(TITLE);
		stage.setScene(scene);
		stage.show();

		new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
			}
		}.start();
	}

	private void update() {
		GraphicsContext gc = this.canvas.getGraphicsContext();

		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, GameCanvas.WIDTH, GameCanvas.HEIGHT);
		gc.setFill(Color.WHITE);
		gc.fillOval(390, 290, 20, 20);
	}
}
