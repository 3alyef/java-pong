package com.alef.pong.entities;

import com.alef.pong.utils.Vector2;
import com.alef.pong.utils.VectorType;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball {
	private Vector2 position, velocity;
	private double RADIUS = 7.5;
	private double width, height;
	private Color color = Color.WHITE;

	public Ball(double canvasWidth, double canvasHeight) {
		resetPosition(canvasWidth, canvasHeight);
		defineSize();
	}

	public Ball(double canvasWidth, double canvasHeight, double RADIUS, Color color) {
		resetPosition(canvasWidth, canvasHeight);
		this.RADIUS = RADIUS;
		this.color = color;
		defineSize();
	}

	public Ball(double canvasWidth, double canvasHeight, double RADIUS, Color color, Vector2 position, Vector2 velocity) {
		this.RADIUS = RADIUS;
		this.color = color;
		this.position = position;
		this.velocity = velocity;
		defineSize();
	}

	public void update(double deltaTime) {
		position = position.addScaled(velocity, deltaTime);

	}

	public void draw(GraphicsContext getGC) {
		getGC.setFill(color);
		getGC.fillOval(position.getX(), position.getY(), width, height);
	}

	private void defineSize() {
		this.height = RADIUS * 2;
		this.width = RADIUS * 2;
	}

	private void resetPosition(double canvasWidth, double canvasHeight) {

		this.position = new Vector2(
				VectorType.POSITION,
				RADIUS + Math.random() * (canvasWidth - width),
				RADIUS + Math.random() * (canvasHeight - height));
		this.velocity = new Vector2(
				VectorType.VELOCITY, 200, 200);
	}

	public void handleWallCollision(double canvasWidth, double canvasHeight) {
		double newX = position.getX();
		double newY = position.getY();

		if (position.getX() <= 0) {
			velocity = velocity.withX(Math.abs(-velocity.getX()));
			newX = 0;
		} else if (position.getX() + width >= canvasWidth) {
			velocity = velocity.withX(-Math.abs(velocity.getX()));
			newX = canvasWidth - width;
		}

		if (position.getY() <= 0) {
			velocity = velocity.withY(Math.abs(-velocity.getY()));
			newY = 0;
		} else if (position.getY() + height >= canvasHeight) {
			velocity = velocity.withY(-Math.abs(velocity.getY()));
			newY = canvasHeight - height;
		}

		position = new Vector2(VectorType.POSITION, newX, newY);
	}
}
