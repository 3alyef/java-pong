package com.alef.pong.entities;

import com.alef.pong.entities.base.Entity;
import com.alef.pong.game.GameCanvas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Penddle extends Entity {
	private final static double FEAR_DEEP = 25;
	private boolean isAuto;
	private boolean moveLeft, moveRight = false;

	public Penddle(double startX, boolean isAuto) {
		super(
				startX,
				GameCanvas.HEIGHT - FEAR_DEEP,
				60,
				12,
				2,
				0);
		this.isAuto = isAuto;
	}

	public void update() {
		if (isAuto) {
			this.checkScreenColision(GameCanvas.WIDTH);
			this.move();
		} else {
			this.setDirection(GameCanvas.WIDTH);
		}

	}

	private boolean checkScreenColision(double displayWidth) {
		if (this.position.getX() <= 0 || this.position.getX() + getWidth() >= displayWidth) {
			this.position.setX(this.position.getX() * -1);
		}
		return false;
	}

	private boolean checkScreenColision(double displayWidth, boolean leftToRight) {
		if (leftToRight) {
			if (this.position.getX() + getWidth() >= displayWidth)
				return true;
		} else {
			if (this.position.getX() <= 0)
				return true;
		}
		return false;
	}

	public void draw(GraphicsContext gc) {
		// y.setValue(GameCanvas.HEIGHT - FEAR_DEEP);
		this.position.setY(GameCanvas.HEIGHT - FEAR_DEEP);
		// GameCanvas.HEIGHT - getHeight()

		gc.setFill(Color.WHITE);
		gc.fillRect(this.position.getX(), this.position.getY(), this.getWidth(), this.getHeight());
	}

	private void move() {
		this.position = this.position.add(this.velocity);
	}

	public void reset() {
		this.position.set(this.startX, this.startY);
		this.velocity.set(this.startVx, this.startVy);
	}

	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}

	public boolean getMoveLeft() {
		return this.moveLeft;
	}

	public boolean getMoveRight() {
		return this.moveRight;
	}

	private void setDirection(double displayWidth) {
		if (moveRight && !moveLeft && !checkScreenColision(displayWidth, true)) {
			this.position.setX(this.position.getX() + this.velocity.getX());
		} else if (moveLeft && !moveRight && !checkScreenColision(displayWidth, false)) {
			this.position.setX(this.position.getX() - this.velocity.getX());
		}
	}

}
