package com.alef.pong.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alef.pong.entities.Ball;
import com.alef.pong.utils.Vector2;
import com.alef.pong.utils.VectorType;

import javafx.scene.paint.Color;

public class BallManager {
	private List<Ball> balls;
	private final double canvasWidth;
	private final double canvasHeight;

	public BallManager(double canvasWidth, double canvasHeight, int initialBallCount) {
		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;
		this.balls = createBalls(initialBallCount);
	}

	private List<Ball> createBalls(int count) {
		List<Ball> newBalls = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			newBalls.add(createRandomBall());
		}
		return Collections.unmodifiableList(newBalls);
	}

	private Ball createRandomBall() {
		double radius = 7.5 + Math.random() * 5;
		Color color = Color.color(Math.random(), Math.random(), Math.random(), 0.8);

		Vector2 position = new Vector2(
				VectorType.POSITION,
				radius + Math.random() * (canvasWidth - 2 * radius),
				radius + Math.random() * (canvasHeight - 2 * radius));

		double speed = 150 + Math.random() * 100;
		double angle = Math.random() * Math.PI * 2;
		Vector2 velocity = new Vector2(
				VectorType.VELOCITY,
				Math.cos(angle) * speed,
				Math.sin(angle) * speed);

		Ball newBall = new Ball(canvasWidth, canvasHeight, radius, color, position, velocity);
		return newBall;
	}

	public List<Ball> getBalls() {
		return balls; // Retorna a lista imutável
	}

	public void updateAll(double deltaTime) {
		for (Ball ball : balls) {
			updateBall(ball, deltaTime);
		}
	}

	private void updateBall(Ball ball, double deltaTime) {
		ball.update(deltaTime);
		ball.handleWallCollision(canvasWidth, canvasHeight);

	}

}
