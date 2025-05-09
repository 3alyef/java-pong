package com.alef.pong.core;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameCanvas extends Canvas {
	private final GraphicsContext gc;
	private Color bgColor = Color.BLACK;
	private double realHeight, realWidth;

	public GameCanvas(int WIDTH, int HEIGHT) {
		super(WIDTH, HEIGHT);
		this.gc = this.getGraphicsContext2D();

	}

	public GameCanvas(int WIDTH, int HEIGHT, Color bgColor) {
		super(WIDTH, HEIGHT);
		this.gc = this.getGraphicsContext2D();
		this.bgColor = bgColor;
	}

	public void updateRealMeasures(Stage stage) {
		Scene scene = stage.getScene();
		this.realWidth = scene.widthProperty().get();
		this.realHeight = scene.heightProperty().get();
	}

	public GraphicsContext getGC() {
		return this.gc;
	}

	public void clear() {
		this.gc.setFill(bgColor);
		this.gc.fillRect(0, 0, getWidth(), getHeight());
	}

	public void configureResizeListener(Stage stage) {
		Scene scene = stage.getScene();
		// scene pega so as medidas da área utilizavel;
		scene.widthProperty().addListener((obs, oldWidth, newWidth) -> {
			double width = newWidth.doubleValue();
			setWidth(width);
			this.realWidth = width;
		});

		scene.heightProperty().addListener((obs, oldHeight, newHeight) -> {
			double height = newHeight.doubleValue();
			setHeight(height);
			this.realHeight = height;
		});
	}

	public void setStageBGC(Color bgColor) {
		this.bgColor = bgColor;
	}

	public double getRealWidth() {
		return this.realWidth;
	}

	public double getRealHeight() {
		return this.realHeight;
	}
}
