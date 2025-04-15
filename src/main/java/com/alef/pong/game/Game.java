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
		this.ball = new Ball(125, 20);
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

		this.checkPenddleBallColision(penddle, ball);

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

	private void checkPenddleBallColision(Penddle penddle, Ball ball) {
		/*
		 * Horizontal Colision:
		 * ballX + ballWidth >= penddleX
		 * && ballX <= penddleX + penddleWidth
		 */
		double ballX = ball.getCoordinates().x();
		double ballHorArea = ballX + ball.getWidth();
		double penddleX = penddle.getCoordinates().x();
		double penddleHorArea = penddleX + penddle.getWidth();

		double ballY = ball.getCoordinates().y();
		double ballVerArea = ballY + ball.getHeight();
		double penddleY = penddle.getCoordinates().y();
		double penddleVerArea = penddleY + penddle.getHeight();

		double ballDx = ball.getDx();
		double penddleDx = penddle.getDx();

		if (ballHorArea >= penddleX && ballX <= penddleHorArea) {
			if (ballVerArea >= penddleY && ballY <= penddleVerArea) {
				/*
				 * double newDx = ball.getDx() * -1;
				 * ball.setDx(newDx);
				 * double newDy = ball.getDy() * -1;
				 * ball.setDy(newDy);
				 */

				if (!(penddle.getMoveRight() && penddle.getMoveLeft())) {
					if (ballDx >= 0 && penddle.getMoveRight()) {
						double newDx = (ballDx + penddleDx);
						ball.setDx(newDx);
					}
					if (ballDx >= 0 && penddle.getMoveLeft()) {
						double newDx = (ballDx - penddleDx);
						ball.setDx(newDx);
					}
				}

				double newDy = ball.getDy() * -1;
				ball.setDy(newDy);
			}
		}
	}
}
