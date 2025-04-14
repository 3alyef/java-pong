package com.alef.pong.entities.base;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
	protected double startX, startY;
	protected EntityCoordinates x, y;
	private double width, height;
	private double dx, dy;

	public Entity(double x, double y, double width, double height, double dx, double dy) {
		this.x = new EntityCoordinates(x);
		this.y = new EntityCoordinates(y);
		this.startX = x;
		this.startY = y;
		this.width = width;
		this.height = height;
		this.dx = dx;
		this.dy = dy;
	}

	public abstract void update();

	public abstract void draw(GraphicsContext gc);

	public double getWidth() {
		return this.width;
	};

	public double getHeight() {
		return this.height;
	};

	public double getDy() {
		return this.dy;
	}

	public double getDx() {
		return this.dx;
	}

	public void setDy(double newDy) {
		this.dy = newDy;
	}

	public void setDx(double newDx) {
		this.dx = newDx;
	}

	public Coordinates getCoordinates() {
		return new Coordinates(x.getValue(), y.getValue());
	}
}
