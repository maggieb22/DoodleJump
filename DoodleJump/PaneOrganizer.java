package DoodleJump;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PaneOrganizer {
	private BorderPane _root;
	private VBox _quit;
	private VBox _labelPane;
	private Label _myLabel;
	
	/**
	 * In my paneorganizer class i made a new borderpane and gave it a size.
	 * I then added my Game to the borderpane and put it in the center
	 * I then called the methods to set the background, create the label on the bottom
	 * and the quit button
	 */
	public PaneOrganizer() {
		_root = new BorderPane();
		Pane myPane = new Pane();
		
		this.setBackground();
		this.createLabel();
		
		myPane.setPrefSize(Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
		new Game(myPane);
		myPane.requestFocus();
		_root.setCenter(myPane);
		this.createQuit();
	}
/**
 * This method sets the background to be a picture I found on the web.
 * It is the original doodle jump background :)
 */
	private void setBackground() {
		Image image = new Image(
				"https://d14nx13ylsx7x8.cloudfront.net/lesson_images/images/000/001/443/original/temp1406589445.png");
		ImageView iv1 = new ImageView();
		iv1.setImage(image);
		iv1.setFitWidth(Constants.IMAGE_WIDTH);
		iv1.setFitHeight(Constants.IMAGE_HEIGHT);
		_root.getChildren().addAll(iv1);
	}
	/**
	 * Here I have a method that returns _root which is my borderpane
	 * I call this in the App class to show the game.
	 */
	public BorderPane getRoot() {
		return _root;
	}
	/**
	 * This is my quit button where I basically make a button, align it
	 * and then set on the action of ClickHandler(right below this method)
	 * to quit the system.
	 */
	public void createQuit() {
		_quit = new VBox();
		Button b1 = new Button("Quit");
		_quit.getChildren().addAll(b1);
		_root.setTop(_quit);
		_quit.setAlignment(Pos.TOP_CENTER);
		b1.setOnAction(new ClickHandler());
		_quit.setFocusTraversable(false);
		b1.setFocusTraversable(false);

	}

	private class ClickHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			System.exit(0);
		}
	}
	/**
	 * This is my label that shows up right below the doodle and it tells
	 * the person playing the game how to move the doodle with keys.
	 */
	public void createLabel() {
		_labelPane = new VBox();
		_myLabel = new Label("Move Doodle With Left and Right Arrows");
		_labelPane.getChildren().addAll(_myLabel);
		_root.setBottom(_labelPane);
		_labelPane.setAlignment(Pos.CENTER);

	}
}
