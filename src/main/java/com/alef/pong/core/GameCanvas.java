package com.alef.pong.core;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameCanvas extends Canvas {
	private final GraphicsContext gc;
	private Color bgColor = Color.BLACK;

	public GameCanvas(int WIDTH, int HEIGHT) {
		super(WIDTH, HEIGHT);
		this.gc = this.getGraphicsContext2D();
	}

	public GameCanvas(int WIDTH, int HEIGHT, Color bgColor) {
		super(WIDTH, HEIGHT);
		this.gc = this.getGraphicsContext2D();
		this.bgColor = bgColor;
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
			// WIDTH = newWidth.doubleValue();
			this.setWidth(newWidth.doubleValue());
		});

		scene.heightProperty().addListener((obs, oldHeight, newHeight) -> {
			// HEIGHT = newHeight.doubleValue();
			this.setHeight(newHeight.doubleValue());
		});
	}

	public void setStageBGC(Color bgColor) {
		this.bgColor = bgColor;
	}
}
