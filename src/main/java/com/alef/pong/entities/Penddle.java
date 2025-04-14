package com.alef.pong.entities;

import com.alef.pong.entities.base.Entity;
import com.alef.pong.game.GameCanvas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Penddle extends Entity {
	private boolean isAuto;
	private boolean moveLeft, moveRight = false;

	public Penddle(double startX, boolean isAuto) {
		super(
				startX,
				GameCanvas.HEIGHT - 10,
				25,
				10,
				2,
				0);
		this.isAuto = isAuto;
	}

	public void update() {
		if (isAuto) {
			this.checkColision(GameCanvas.WIDTH);
			this.move();
		} else {
			this.setDirection(GameCanvas.WIDTH);
		}

	}

	private boolean checkColision(double displayWidth) {
		if (x.getValue() <= 0 || x.getValue() + getWidth() >= displayWidth) {
			double newDx = getDx() * -1;
			setDx(newDx);
		}
		return false;
	}

	private boolean checkColision(double displayWidth, boolean leftToRight) {
		if (leftToRight) {
			if (x.getValue() + getWidth() >= displayWidth)
				return true;
		} else {
			if (x.getValue() <= 0)
				return true;
		}
		return false;
	}

	public void draw(GraphicsContext gc) {

		y.setValue(GameCanvas.HEIGHT - getHeight());
		gc.setFill(Color.WHITE);
		gc.fillRect(x.getValue(), y.getValue(), getWidth(), getHeight());
	}

	private void move() {
		double newX = x.getValue() + getDx();
		x.setValue(newX);
	}

	public void reset() {
		this.x.setValue(this.startX);
		this.setDx(5);
	}

	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}

	private void setDirection(double displayWidth) {
		if (moveRight && !moveLeft && !checkColision(displayWidth, true)) {
			double newX = x.getValue() + getDx();
			x.setValue(newX);
		} else if (moveLeft && !moveRight && !checkColision(displayWidth, false)) {
			double newX = x.getValue() - getDx();
			x.setValue(newX);
		}
	}

}
