package com.alef.pong.entities;

import com.alef.pong.core.GameCanvas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball {
	private double x, y;
	private double speedX, speedY;
	private double RADIUS = 7.5;
	private Color color = Color.WHITE;

	public Ball(double canvasWidth, double canvasHeight) {
		resetPosition(canvasWidth, canvasHeight);
	}

	public Ball(double canvasWidth, double canvasHeight, double RADIUS) {
		resetPosition(canvasWidth, canvasHeight);
		this.RADIUS = RADIUS;
	}

	public void update(double deltaTime) {
		x += speedX * deltaTime;
		y += speedY * deltaTime;
		// Lógica de colisão com paredes

	}

	public void draw(GraphicsContext getGC) {
		getGC.setFill(color);
		getGC.fillOval(x, y, RADIUS * 2, RADIUS * 2);
	}

	private void resetPosition(double canvasWidth, double canvasHeight) {
		x = canvasWidth / 2;
		y = canvasHeight / 2;
		speedX = 200;
		speedY = 200;
	}

	public void handleWallCollision(double canvasWidth, double canvasHeight) {
		if (((x + RADIUS * 2) >= canvasWidth) || x <= 0) {
			speedX *= -1;
		} else if (((y + RADIUS * 2) >= canvasHeight) || y <= 0) {
			// speedY *= -1;
			resetPosition(canvasWidth, canvasHeight);
		}
	}
}
