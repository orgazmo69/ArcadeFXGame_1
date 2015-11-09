package application;

import javafx.scene.canvas.GraphicsContext;

public class Laser extends Sprite {

	private boolean isRecharged = true;
	private boolean isVisible = false;
	private double timePassed = 0;
	private final double SECONDS_VISIBLE = 0.2;

	public Laser(String imagePath, double x, double y, double width, double height) {
		super(imagePath, x, y, width, height);
	}


	@Override
	public void render(GraphicsContext graphicsContext) {
		if(isVisible) {
			super.render(graphicsContext);
		}
	}


	public void update(double deltaTime) {
		if (isVisible) {
			timePassed+= deltaTime;
			if (timePassed >= SECONDS_VISIBLE) {
				isVisible = false;
				timePassed = 0;
			}
		}
	}

	public void fire() {
		if (isRecharged) {
			isVisible = true;
			isRecharged = false;
		}
	}


	public void recharge() {
		isRecharged = true;
	}


	public boolean isVisible() {
		return isVisible;
	}


}
