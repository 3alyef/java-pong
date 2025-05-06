package com.alef.pong.core;

import com.alef.pong.entities.Ball;
import com.alef.pong.entities.Paddle;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameEngine {
	private final Stage stage;
	private static final String TITLE = "Pong Game";
	private final GameCanvas canvas;
	private final Ball ball;
	// private final Paddle playerPaddle, enemyPaddle;

	public GameEngine(Stage stage) {
		this.stage = stage;
		this.canvas = new GameCanvas(800, 600);
		this.ball = new Ball(canvas.getWidth(), canvas.getHeight());
		// this.playerPaddle = new Paddle(canvas, true);
		// this.enemyPaddle = new Paddle(canvas, false);
	}

	public void startGame() {
		setupStage();

		new AnimationTimer() {
			private long lastUpdate = -1;

			@Override
			public void handle(long now) {
				if (lastUpdate == -1) {
					lastUpdate = now;
					return;
				}

				double deltaTime = (now - lastUpdate) / 1_000_000_000.0; // Converte para segundos

				lastUpdate = now;
				deltaTime = Math.min(deltaTime, 0.1); // Limita a 100ms

				checkCollisions();
				update(deltaTime);
				render();

				if (Math.random() < 0.0075) { // Mostra apenas 1% dos frames
					System.out.printf("Delta: %.6fs | FPS: %.1f%n",
							deltaTime,
							1.0 / deltaTime);
				}
			}
		}.start();
	}

	private void update(double deltaTime) {

		ball.update(deltaTime);
		// playerPaddle.update(deltaTime);
		// enemyPaddle.update(deltaTime);

		// Lógica de colisão aqui
	}

	private void render() {
		canvas.clear();
		ball.draw(canvas.getGC());
		/*
		 * 
		 * playerPaddle.draw();
		 * enemyPaddle.draw();
		 */
	}

	private void setupStage() {
		Scene scene = new Scene(new Group(this.canvas));

		this.stage.setTitle(TITLE);
		this.stage.setScene(scene);
		this.canvas.configureResizeListener(stage);
		this.stage.show();
	}

	private void checkCollisions() {
		ball.handleWallCollision(canvas.getWidth(), canvas.getHeight());
	}
}
