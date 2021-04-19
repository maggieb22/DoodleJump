package DoodleJump;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Game {
	private Doodle _doodle;
	private Platform _platform;
	private ArrayList<Platform> _arrayList;
	private Pane _pane;
	private Timeline _timeline;
	private VBox _endLabelPane;
	private Label _myEndLabel;
	private KeyHandler _keyhandler;
	private Label _scoreLabel;
	private int _score;
 
	/**
	 * This is my game class where I make a new pane and a doodle and a platform and
	 * I put these in the pane. I then make an arraylist of platforms and add my
	 * platform to this arraylist. I then set my initial score to be equal to 0 and
	 * then make a new scorelabel to show score. I also make a keyhandler so that
	 * way I can move the doodle left and right. Here is also where I set up my
	 * timeline
	 */
	public Game(Pane pane) {
		_pane = pane;
		_doodle = new Doodle(pane);

		_platform = new Platform(pane);
		_arrayList = new ArrayList<Platform>();
		_arrayList.add(_platform);

		_score = 0;
		_scoreLabel = new Label("Score: " + Integer.toString(_score));
		_pane.getChildren().addAll(_scoreLabel);

		_keyhandler = new KeyHandler();
		pane.addEventHandler(KeyEvent.KEY_PRESSED, _keyhandler);
		pane.setFocusTraversable(true);
		this.setUpTimeline();
	}

	/**
	 * This is my Keyhandler where I take in the inputs of when the left and right
	 * keys are pressed when they are pressed, the doodle moves 10 left or right
	 * depending on what is pressed. if the location is off the edge of the screen
	 * (less than 0 or greater than 400) then the doodle will go to the other
	 * side(that is the left and right limit method).
	 */
	private class KeyHandler implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent e) {

			KeyCode keyPressed = e.getCode();
			if (keyPressed == KeyCode.LEFT) {
				_doodle.moveLeft();
				if (_doodle.getXLoc() <= Constants.LEFT_LIMIT) {
					_doodle.rightLimit();
				}
			} else if (keyPressed == KeyCode.RIGHT) {
				_doodle.moveRight();
				if (_doodle.getXLoc() >= Constants.RIGHT_LIMIT) {
					_doodle.leftLimit();
				}
				e.consume();
			}
		}
	}

	/**
	 * This is my timeline. My timeline duration is in 0.01 seconds. In my timeline
	 * I make a jumphandler which is what makes the doodle jump. I will explain the
	 * jumphandler in the next comment.
	 */
	private void setUpTimeline() {
		KeyFrame kf = new KeyFrame(Duration.seconds(0.01), new JumpHandler());
		_timeline = new Timeline(kf);
		_timeline.setCycleCount(Animation.INDEFINITE);
		_timeline.play();
	}

	/**
	 * In my jumphandler I call a bunch of methods that make the doodle move. The
	 * doodle has to bounce and update its velocity and position and then scroll and
	 * generate new platforms. I will explain how these methods work later on. Also
	 * this is where I call the method to end the game.
	 */
	private class JumpHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			Game.this.bounce();
			_doodle.updateVelocity();
			_doodle.updatePosition();
			Game.this.scrollPlatforms();
			Game.this.generatePlatforms(_pane);
			Game.this.endGame();

		}
	}

	/**
	 * I already made my arraylist in the game constructor so here i check the size
	 * of the arraylist and if the Y values are greater than 0, then I run the loop.
	 * This makes it so that the arraylist only has platforms on the screen. Inside
	 * the loop I make a new platform in the pane. I set the x and y of this
	 * platform to be random numbers between a high and a low constant. I use
	 * another method I wrote to generate this number. Then I add the new platform
	 * to the arraylist so that way it will show up. Next I check to make sure that
	 * the new platform isn't off the screen and if it is off the screen I set it to
	 * be on the edge of the screen.
	 */
	private void generatePlatforms(Pane pane) {
		while (_arrayList.get(_arrayList.size() - 1).getY() >= 0) {
			Platform newPlatform = new Platform(pane);

			newPlatform.setX(_arrayList.get(_arrayList.size() - 1).getX()
					- this.randomInt(Constants.MIN_OFFSET_X, Constants.MAX_OFFSET_X));
			newPlatform.setY(_arrayList.get(_arrayList.size() - 1).getY()
					- this.randomInt(Constants.MIN_OFFSET_Y, Constants.MAX_OFFSET_X));

			_arrayList.add(newPlatform);
			if (newPlatform.getX() > Constants.PANE_WIDTH - Constants.PLATFORM_WIDTH) {
				newPlatform.setX(Constants.PANE_WIDTH - Constants.PLATFORM_WIDTH);
			}

			if (newPlatform.getX() < Constants.LEFT_LIMIT) {
				newPlatform.setX(Constants.LEFT_LIMIT);
			}
		}
	}

	/**
	 * In my bounce method I check so that way it is only going to run platforms in
	 * the arraylist. Then if the velocity is greater than 0 and the doodle
	 * intersects a platform, I set the velocity to be the rebound velocity (this is
	 * a method that I wrote in the doodle class. Also when this runs the score is
	 * set to the score plus 10 and updates the scoreLabel. This way each time the
	 * doodle bounces off a platform, +10 is added to the score.
	 */
	private void bounce() {
		for (int i = 0; i < _arrayList.size(); i++) {
			if (_doodle.getVelocity() >= 0 && _doodle.intersects(_arrayList.get(i))) {
				_doodle.setVelocity();
				_score = _score + Constants.SCORE;
				_scoreLabel.setText("Score: " + Integer.toString(_score));
			}
		}
	}

	/*
	 * This is the method that I use in my generatePlatforms method to make a random
	 * number between the max high and low values.
	 */
	private int randomInt(int low, int high) {
		return low + (int) (Math.random() * (high - low + 1));

	}

	/*
	 * Here I check to make to see if the doodle is above the midline and the
	 * velocity is less than 0. then i set the difference to be the midpoint minus
	 * the doodle location to use later. Then I make a new platform in the arraylist
	 * and I set the Y value to be the difference that I stored before. This has the
	 * effect of scrolling the platforms. Then I check the y value of the platforms
	 * and if they are below the bottom of the screen then they are removed from the
	 * pane and from the arraylist (logically and graphically). Then I set the
	 * doodles location to be the midpoint.
	 */
	private void scrollPlatforms() {
		if (_doodle.getYLoc() < Constants.PANE_HEIGHT / 2 && _doodle.getVelocity() < 0) {
			double difference = Constants.PANE_HEIGHT / 2 - _doodle.getYLoc();

			for (int i = 0; i < _arrayList.size(); i++) {
				Platform newPlatform = _arrayList.get(i);
				newPlatform.setY(newPlatform.getY() + difference);

				if (newPlatform.getY() > Constants.PANE_HEIGHT) {
					_pane.getChildren().remove(newPlatform.getPlatform());
					_arrayList.remove(newPlatform);
				}
			}
			_doodle.setY(Constants.PANE_HEIGHT / 2);
		}
	}

	/*
	 * This is my endgame method that I call in the jumphandler. I first check to
	 * see if the doodle is below the screen if it is, then I stop the timeline and
	 * I create a label that says game over. I then remove the keyhandler so that
	 * way the doodle can't move.
	 */
	private void endGame() {
		if (_doodle.getYLoc() > Constants.PANE_HEIGHT) {
			_timeline.stop();

			_endLabelPane = new VBox();
			_myEndLabel = new Label("Game Over!");
			_endLabelPane.getChildren().addAll(_myEndLabel);
			_endLabelPane.setLayoutX(Constants.END_BUTTON);
			_endLabelPane.setLayoutY(Constants.END_BUTTON);
			_pane.getChildren().add(_endLabelPane);

			_pane.removeEventHandler(KeyEvent.KEY_PRESSED, _keyhandler);
		}
	}

}
