package DoodleJump;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class where your DoodleJump game will start.
 * The main method of this application calls launch, a JavaFX method
 * which eventually calls the start method below. You will need to fill
 * in the start method to start your game!
 *
 * Class comments here...
 */
public class App extends Application {

	/**
	 * Here I instantiate top-level object, set up the scene, and show the stage
	 */
	
    @Override
    public void start(Stage stage) {
    	stage.setTitle("Doodle Jump!! :D");
    	stage.show();
    	PaneOrganizer organizer = new PaneOrganizer();
    	Scene scene = new Scene(organizer.getRoot());
    	stage.setScene(scene);
        
    }

    /*
     * Here is the mainline! No need to change this.
     */
    public static void main(String[] argv) {
        // launch is a static method inherited from Application.
        launch(argv);
    }
}
