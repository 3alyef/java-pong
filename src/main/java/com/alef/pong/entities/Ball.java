package com.alef.pong.entities;

import com.alef.pong.entities.base.Entity;
import com.alef.pong.game.GameCanvas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends Entity {
	private static final double DEFAULT_RADIUS = 10;

	public Ball(double startX, double startY) {
		super(
				startX,
				startY,
				DEFAULT_RADIUS * 2,
				DEFAULT_RADIUS * 2,
				4,
				3);
	}

	public void update() {
		this.move();
		this.checkColision();
	}

	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillOval(this.position.getX(), this.position.getY(), this.getWidth(), this.getHeight());
	}

	private void move() {
		this.position = this.position.add(this.velocity);
		/*
		 * double newX = x.getValue() + getDx();
		 * x.setValue(newX);
		 * double newY = y.getValue() + getDy();
		 * y.setValue(newY);
		 */

	}

	private void checkColision() {
		if (this.position.getY() <= 0 || this.position.getY() + this.getHeight() >= GameCanvas.HEIGHT) {
			this.velocity.setY(this.velocity.getY() * -1);
		}

		if (this.position.getX() <= 0 || this.position.getX() + this.getWidth() >= GameCanvas.WIDTH) {
			this.velocity.setX(this.velocity.getX() * -1);
		}
	}

	public void reset() {
		this.position.set(this.startX, this.startY);
		this.velocity.set(this.startVx, this.startVy);
	}

}
