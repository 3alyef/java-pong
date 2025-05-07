package com.alef.pong.utils;

public class Vector2 {
	private double x, y;
	private final VectorType type;

	public Vector2(VectorType type, double x, double y) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public Vector2(VectorType type) {
		this(type, 0, 0);
	}

	public Vector2 withX(double x) {
		return new Vector2(this.type, x, this.y);
	}

	public Vector2 withY(double y) {
		return new Vector2(this.type, this.x, y);
	}

	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void set(Vector2 other) {
		this.x = other.x;
		this.y = other.y;
	}

	public Vector2 add(Vector2 other) {
		return new Vector2(this.type, this.x + other.getX(), this.y + other.getY());
	}

	public Vector2 addScaled(Vector2 other, double deltaTime) {
		return new Vector2(
				this.type,
				this.x + (other.getX() * deltaTime),
				this.y + (other.getY() * deltaTime));
	}

	// return => module (magnitude) == 1
	public Vector2 normalize() {
		double length = length();
		if (length == 0) {
			return new Vector2(this.type, 0, 0);
		}
		return new Vector2(this.type, x / length, y / length);
	}

	// return => module (magnitude)
	public double length() {
		return Math.sqrt(x * x + y * y);
	}

	public Vector2 multiplied(double scalar) {
		return new Vector2(this.type, x * scalar, y * scalar);
	}

	// --- Getters ---
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Vector2 copy() {
		return new Vector2(this.type, x, y);
	}

	public static double distance(Vector2 a, Vector2 b) {
		if (a.type != VectorType.POSITION || b.type != VectorType.POSITION) {
			throw new IllegalArgumentException(
					"Distance can only be calculated between position vectors!");
		}
		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
	}

	@Override
	public String toString() {
		return String.format("(%.2f, %.2f)", x, y);
	}
}
