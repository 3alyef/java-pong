package com.alef.pong.math;

// understood => Vector2D is used to: position and velocity
public class Vector2D {
	private double x, y;

	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D() {
		this(0, 0);
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void set(Vector2D other) {
		this.x = other.x;
		this.y = other.y;
	}

	// Soma vetorial
	public Vector2D add(Vector2D other) {
		return new Vector2D(this.x + other.x, this.y + other.y);
	} // Soma as propriedades do objeto com outro, e então retorna um novo obj, com
		// novos valores.

	// Multiplicação por escalar
	public Vector2D multiply(double scalar) {
		return new Vector2D(this.x * scalar, this.y * scalar);
	} // multiplica o vetor vf por scalar

	// Normalização (direção unitária)
	public Vector2D normalize() {
		double length = length();
		if (length == 0)
			return new Vector2D(0, 0);
		return new Vector2D(this.x / length, this.y / length);
	}

	// Módulo (magnitude)
	public double length() {
		return Math.sqrt(x * x + y * y);
	}

	@Override
	public String toString() {
		return "Vector2D(" + x + ", " + y + ")";
	}
}
