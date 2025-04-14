package com.alef.pong.game;

import com.alef.pong.entities.Ball;
import com.alef.pong.entities.Penddle;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game {
	private static final String TITLE = "Pong Game";
	private final GameCanvas canvas;
	private final Ball ball;
	private final Penddle penddle;

	public Game() {
		this.canvas = new GameCanvas();
		this.ball = new Ball(20, 20);
		this.penddle = new Penddle(10, false);
	}

	public void start(Stage stage) {

		Scene scene = new Scene(new Group(this.canvas));

		stage.setTitle(TITLE);
		stage.setScene(scene);
		this.canvas.startScreenMonitoring(stage);
		this.startPenddleListeners(scene);
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

		gc.fillRect(0, 0, this.getDisplayWidth(), this.getDisplayHeight());

		// gc.setFill(Color.WHITE);
		// gc.fillOval(390, 290, 20, 20);

		this.ball.draw(gc);
		this.ball.update();

		this.penddle.draw(gc);
		this.penddle.update();

	}

	private double getDisplayHeight() {
		return this.canvas.getHeight();
	}

	private double getDisplayWidth() {
		return this.canvas.getWidth();
	}

	private void startPenddleListeners(Scene scene) {
		scene.setOnKeyPressed(event -> {
			switch (event.getCode()) {
				case LEFT, A:
					this.penddle.setMoveLeft(true);
					break;
				case RIGHT, D:
					this.penddle.setMoveRight(true);
					break;
				default:
					break;
			}
		});
		scene.setOnKeyReleased(event -> {
			switch (event.getCode()) {
				case LEFT, A:
					this.penddle.setMoveLeft(false);
					break;
				case RIGHT, D:
					this.penddle.setMoveRight(false);
					break;
				default:
					break;
			}
		});
	}
}
