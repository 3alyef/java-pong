package com.alef.pong.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alef.pong.entities.Ball;
import com.alef.pong.utils.Vector2;
import com.alef.pong.utils.VectorType;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BallManager {
	private List<Ball> balls;
	private final double initialCanvasWidth;
	private final double initialCanvasHeight;

	public BallManager(double initialCanvasWidth, double initialCanvasHeight, int initialBallCount) {
		this.initialCanvasWidth = initialCanvasWidth;
		this.initialCanvasHeight = initialCanvasHeight;
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
				radius + Math.random() * (initialCanvasWidth - 2 * radius),
				radius + Math.random() * (initialCanvasHeight - 2 * radius));

		double speed = 150 + Math.random() * 100;
		double angle = Math.random() * Math.PI * 2;
		Vector2 velocity = new Vector2(
				VectorType.VELOCITY,
				Math.cos(angle) * speed,
				Math.sin(angle) * speed);

		Ball newBall = new Ball(initialCanvasWidth, initialCanvasHeight, radius, color, position, velocity);
		return newBall;
	}

	public List<Ball> getBalls() {
		return balls; // Retorna a lista imutável
	}

	private void updateBall(Ball ball, double deltaTime, double canvasWidth, double canvasHeight) {
		ball.update(deltaTime);
		ball.handleWallCollision(canvasWidth, canvasHeight);

	}

	public void updateAll(double deltaTime, double canvasWidth, double canvasHeight) {
		for (Ball ball : balls) {
			updateBall(ball, deltaTime, canvasWidth, canvasHeight);
		}
	}

	public void renderBalls(GraphicsContext gc) {
		for (Ball ball : balls) {
			ball.draw(gc);
		}
	}

}
