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
				5,
				5);
	}

	public void update() {
		this.move();
		this.checkColision();
	}

	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillOval(x.getValue(), y.getValue(), DEFAULT_RADIUS * 2, DEFAULT_RADIUS * 2);
	}

	private void move() {
		/*
		 * x += dx;
		 * y += dy;
		 */

		double newX = x.getValue() + getDx();
		x.setValue(newX);
		double newY = y.getValue() + getDy();
		y.setValue(newY);

	}

	private void checkColision() {
		if (y.getValue() <= 0 || y.getValue() + DEFAULT_RADIUS * 2 >= GameCanvas.HEIGHT) {
			double newDy = getDy() * -1;
			setDy(newDy);
		}

		if (x.getValue() <= 0 || x.getValue() + DEFAULT_RADIUS * 2 >= GameCanvas.WIDTH) {
			double newDx = getDx() * -1;
			setDx(newDx);
		}
	}

	public void reset() {
		this.x.setValue(this.startX);
		this.y.setValue(this.startY);
		setDx(5);
		setDy(5);
	}

}
