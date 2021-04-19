package DoodleJump;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Doodle {
	private Rectangle _body;
	private Circle _head;
	private Double _velocity;

	/*
	 * Here I make a doodle in a pane, which consists of a head and a body. Then I set locations for them
	 * and add them to the pane. I also tell the doodle to change color so that way each time the game is run
	 * the doodle changes color. Here i also set the velocity to be 0.
	 */
	public Doodle(Pane pane) {
		_body = new Rectangle(Constants.DOODLE_WIDTH, Constants.DOODLE_HEIGHT);
		_head = new Circle(Constants.CIRCLE_RADIUS);
		_body.setX(Constants.BODY_X);
		_body.setY(Constants.BODY_Y);
		_head.setCenterX(Constants.HEAD_X);
		_head.setCenterY(Constants.HEAD_Y);
		pane.getChildren().addAll(_body, _head);
		this.changeColor();
		
		_velocity = Constants.VELOCITY;
	}
/*
 * These two methods(move left and right) are what I use in my keyhandler to move my doodle to the left and right
 * I sets the x to be where it was before +/-10
 */
	public void moveLeft() {
		_body.setX(_body.getX() - Constants.JUMP);
		_head.setCenterX(_head.getCenterX() - Constants.JUMP);

	}

	public void moveRight() {
		_body.setX(_body.getX() + Constants.JUMP);
		_head.setCenterX(_head.getCenterX() + Constants.JUMP);
	}
/*
 * This method does not need to be explained. It sets the head and the body to be randomly generated colors.
 * I call this in the constructor
 */
	private void changeColor() {
		int red = (int) (Math.random() * 256);
		int green = (int) (Math.random() * 256);
		int blue = (int) (Math.random() * 256);
		int red2 = (int) (Math.random() * 256);
		int green2 = (int) (Math.random() * 256);
		int blue2 = (int) (Math.random() * 256);
		Color myColor = Color.rgb(red, green, blue);
		Color myColor2 = Color.rgb(red2, green2, blue2);
		_body.setFill(myColor);
		_head.setFill(myColor2);
	}
/**
 * get x and y location return where the body is located. I use these methods on the keyhandler in the game
 * class so that way I know where the doodle is so that way I can move it
 */
	public double getXLoc() {
		return _body.getX();
	}

	public double getYLoc() {
		return _body.getY();
	}

	/**
	 * Left and right limit set the body and head to be on the edge of the screen so that way when they are 
	 * called in the game class and the doodle leaves the edge of the pane, it appears on the other side. 
	 */
	public void leftLimit() {
		_body.setX(Constants.LEFT_LIMIT);
		_head.setCenterX(Constants.LEFT_LIMIT_HEAD);
	}

	public void rightLimit() {
		_body.setX(Constants.RIGHT_LIMIT);
		_head.setCenterX(Constants.RIGHT_LIMIT_HEAD);
	}

	/**
	 * This method is what updates the velocity due to gravity. I call this method in the jumphandler 
	 * in the game class.
	 */
	public void updateVelocity() {
		_velocity = _velocity + Constants.GRAVITY * Constants.DURATION;
	}

	/**
	 * This method updates the doodles position based off of where it was before and the velocity and duration
	 * I call this method in the jumphandler of my game class.
	 */
	public void updatePosition() {
		_body.setY(_body.getY() + _velocity * Constants.DURATION);
		_head.setCenterY(_head.getCenterY() + _velocity * Constants.DURATION);
	}

	/**
	 * In my intersects method it takes in the platform as a parameter and it returns true or false if the 
	 * doodle intersects with the platforms location and witdth/height. 
	 */
	public boolean intersects(Platform platform) {
		return _body.intersects(platform.getX(), platform.getY(), Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT);
	}

	/**
	 * This method returns the value of the velocity as a double. I use it in my scroll/bounce method to see if
	 * velocity is below 0. 
	 */
	public Double getVelocity() {
		return _velocity;
	}

	/**
	 * Here I set the rebound velocity to be rebound velocity. I use this method in the bounce method of the
	 * game class. 
	 */
	public void setVelocity() {
		_velocity = Constants.REBOUND_VELOCITY;
	}

	/*
	 * I use this method in my scrolling method of my game class so that way I can set my doodles position to
	 * be in the center
	 */
	public void setY(double y) {
		_body.setY(y);
		_head.setCenterY(y);

	}

}
