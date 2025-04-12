package com.alef.pong.game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class GameCanvas extends Canvas {
	public static double WIDTH = 800;
	public static double HEIGHT = 600;

	private final GraphicsContext gc;

	public GameCanvas() {
		super(WIDTH, HEIGHT); // define o tamanho do canvas
		this.gc = this.getGraphicsContext2D(); // obtém o contexto de desenho
	}

	public GraphicsContext getGraphicsContext() {
		return this.gc;
	}

	public void clear() {
		this.gc.clearRect(0, 0, WIDTH, HEIGHT);
	}

	public void startScreenMonitoring(Stage stage) {
		stage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
			WIDTH = newWidth.doubleValue();
			this.setWidth(WIDTH);
		});

		stage.heightProperty().addListener((obs, oldHeight, newHeight) -> {
			HEIGHT = newHeight.doubleValue();
			this.setHeight(HEIGHT);
		});
	}
}
