package com.alef.pong.entities.base;

import com.alef.pong.math.Vector2D;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
	protected double startX, startY, startVx, startVy;
	private double width, height;
	protected Vector2D position, velocity, force;

	public Entity(double startX, double startY, double width, double height, double dx, double dy) {
		this.position = new Vector2D(startX, startY);
		this.velocity = new Vector2D(dx, dy);
		this.force = new Vector2D();
		this.startX = startX;
		this.startY = startY;
		this.startVx = dx;
		this.startVy = dy;
		this.width = width;
		this.height = height;
	}

	public abstract void update();

	public abstract void draw(GraphicsContext gc);

	public double getWidth() {
		return this.width;
	};

	public double getHeight() {
		return this.height;
	};

	public Coordinates getPosition() {
		return new Coordinates(this.position.getX(), this.position.getY());
	}

	public Coordinates getVelocity() {
		return new Coordinates(this.velocity.getX(), this.velocity.getY());
	}

	public void setVelocityX(double x) {
		this.velocity.setX(x);
	}

	public void setVelocityY(double y) {
		this.velocity.setY(y);
	}
}
