package DoodleJump;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platform {
	private Rectangle _platform;

	/*
	 * I make a new platform in my pane and I set the position and give it a random color
	 */
	public Platform(Pane pane) {
		_platform = new Rectangle(Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT);
		pane.getChildren().addAll(_platform);
		_platform.setX(Constants.PLATFORM_X);
		_platform.setY(Constants.PLATFORM_Y);
		
		this.changeColor();
	}
	
	/*
	 * Here I set the platform color to be a random color, I call this in the constructor
	 */
	private void changeColor() {
		int red = (int) (Math.random() * 256);
		int green = (int) (Math.random() * 256);
		int blue = (int) (Math.random() * 256);
		Color myColor = Color.rgb(red, green, blue);
		_platform.setFill(myColor);
	}

	/**
	 * Here I return the platform so that way when I need to remove the platforms from the game, I can 
	 * actually find them. I use this in the scroll platforms method of my game class
	 */
	public Rectangle getPlatform() {
		return _platform;
	}
/**
 * These next two methods return the x and y locations of the platform. I use these in my game class to generate
 * and scroll the platforms so that way I know the locations of the platforms
 */
	public double getX() {
		return _platform.getX();
	}

	public double getY() {
		return _platform.getY();
	}

	/**
	 * These next two methods set the x and y of the platforms to be a double so that way I can randomly 
	 * set the locations of the x and y in my generate platforms method of my game class. 
	 */
	public void setX(double x) {
		_platform.setX(x);
	}

	public void setY(double y) {
		_platform.setY(y);
	}

}
