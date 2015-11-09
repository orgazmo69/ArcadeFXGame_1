package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Game extends BaseGame {

	private Sprite background;
	private Sprite topSide;
	private Sprite icecream;
	private ArrayList <IceCream> icecreams;
	private Laser zLaser;
	private Laser xLaser;

	private Laser oLaser;
	private Laser pLaser;

	private Set<Laser> lasers;

	private double timePassed = 0;
	private Random randomGenerator;
	Integer iceCreamHit = 0;

	protected void setupGame() {
		background = new Sprite ("Arcade.jpg", 0, 0,
				canvas.getWidth(), canvas.getHeight());

		topSide = new Sprite ("TopCover.jpg", 100, 0, 823, 104); //погледни детайлно!

		icecream = new IceCream ("icecream2.png", 0, 0, 121, 57); //погледни къде и кога да се появява сладоледа

		icecreams = new ArrayList<IceCream>();

		randomGenerator = new Random();

		lasers = new HashSet<>();
		zLaser = new Laser("greenLaserRay.png", 129, 475, 145, 37);
		lasers.add(zLaser);
		xLaser = new Laser("redLaserRay.png", 263, 475, 145, 37);
		lasers.add(xLaser);

		oLaser = new Laser("redLaserRay.png", 612, 475, 145, 37);
		lasers.add(oLaser);
		pLaser = new Laser("greenLaserRay.png", 748, 475, 145, 37);
		lasers.add(pLaser);
	}

	protected void updateGame (double deltaTime){

		zLaser.update(deltaTime);
		xLaser.update(deltaTime);
		oLaser.update(deltaTime);
		pLaser.update(deltaTime);

		for (int i = 0; i < icecreams.size(); i ++) {
			IceCream currentIceCream = icecreams.get(i);
			currentIceCream.update(deltaTime);
			boolean outOfSpace = !currentIceCream.getBoundery().intersects(134, 50, 615, 525);
			boolean isHitByALaser = isHitByLaser (currentIceCream);
			if (isHitByALaser) {
				iceCreamHit++;
			}
			if (outOfSpace || isHitByALaser){
				icecreams.remove(currentIceCream);
			}
		}

		timePassed += deltaTime;
		if (timePassed > 0.8) {
			generateIceCream();
			timePassed = 0;

		}
	}

	private boolean isHitByLaser (IceCream currentIceCream) {
		for (Laser laser : lasers) {
			if (laser.isVisible() && laser.getBoundery().intersects(currentIceCream.getBoundery())) {
				return true;
			}
		}

		return false;
	}

	private void generateIceCream() {
		IceCream icecream = new IceCream ("icecream2.png", 0, 0, 121, 57);
		int randomIcecrem = randomGenerator.nextInt(4);
		switch(randomIcecrem) {
		case 0 :
			icecream = new IceCream("icecream2.png", 129, 50, 145, 37);
			break;
		case 1:
			icecream = new IceCream("icecream2.png", 263, 50, 145, 37);
			break;
		case 2:
			icecream = new IceCream("icecream2.png", 612, 50, 145, 37);
			break;
		case 3:
			icecream = new IceCream("icecream2.png", 748, 50, 145, 37);
			break;
		}
		icecream.setVelocity(500);
		icecreams.add(icecream);
	}

	protected void drawGame() {
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		background.render(graphicsContext);
		icecream.render(graphicsContext);

		zLaser.render(graphicsContext);
		xLaser.render(graphicsContext);
		oLaser.render(graphicsContext);
		pLaser.render(graphicsContext);

		for (int i = 0; i < icecreams.size(); i ++) {
			IceCream currentIceCream = icecreams.get(i);
			currentIceCream.render(graphicsContext);
		}

		//topSide.render(graphicsContext);
		graphicsContext.setFill(Color.WHITE);
		Font font = new Font(30);
		graphicsContext.setFont(font);
		graphicsContext.fillText(iceCreamHit.toString(), 30, 30);

	}

	public void onKeyPressed(KeyEvent event) {
		if(event.getCode().equals(KeyCode.Z) ) {
			zLaser.fire();
		}

		if(event.getCode().equals(KeyCode.X) ) {
			xLaser.fire();
		}

		if(event.getCode().equals(KeyCode.O) ) {
			oLaser.fire();
		}

		if(event.getCode().equals(KeyCode.P) ) {
			pLaser.fire();
		}
	}

	public void onKeyReleased(KeyEvent event) {
		if(event.getCode().equals(KeyCode.Z)) {
			zLaser.recharge();
		}

		if(event.getCode().equals(KeyCode.X)) {
			xLaser.recharge();
		}

		if(event.getCode().equals(KeyCode.O)) {
			oLaser.recharge();
		}

		if(event.getCode().equals(KeyCode.P)) {
			pLaser.recharge();
		}
	}
}
