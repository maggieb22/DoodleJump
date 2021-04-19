package DoodleJump;

/**
 * This is your Constants class. It defines some constants you will need in
 * DoodleJump, using the default values from the demo--you shouldn't need to
 * change any of these values unless you want to experiment. Feel free to add
 * more constants to this class!
 *
 * A NOTE ON THE GRAVITY CONSTANT: Because our y-position is in pixels rather
 * than meters, we'll need our gravity to be in units of pixels/sec^2 rather
 * than the usual 9.8m/sec^2. There's not an exact conversion from pixels to
 * meters since different monitors have varying numbers of pixels per inch, but
 * assuming a fairly standard 72 pixels per inch, that means that one meter is
 * roughly 2800 pixels. However, a gravity of 2800 pixels/sec2 might feel a bit
 * fast. We suggest you use a gravity of about 1000 pixels/sec2. Feel free to
 * change this value, but make sure your game is playable with the value you
 * choose.
 */


/**
 * I have already explained the purpose of all my constants within my other classes. 
 */
public class Constants {

	public static final int GRAVITY = 1000; // acceleration constant (UNITS: pixels/s^2)
	public static final double REBOUND_VELOCITY = -700.0; // initial jump velocity -900.0(UNITS: pixels/s)
	public static final double DURATION = 0.016; // KeyFrame duration (UNITS: s)

	public static final int PLATFORM_WIDTH = 40; // (UNITS: pixels)
	public static final int PLATFORM_HEIGHT = 10; // (UNITS: pixels)
	public static final int DOODLE_WIDTH = 20; // (UNITS: pixels)
	public static final int DOODLE_HEIGHT = 40; // (UNITS: pixels)

	public static final int PANE_WIDTH = 400;
	public static final int PANE_HEIGHT = 600;
	public static final int IMAGE_WIDTH = 400;
	public static final int IMAGE_HEIGHT = 700;
	public static final int PLATFORM_X = 170;
	public static final int PLATFORM_Y = 592;
	
	public static final int JUMP = 10;
	public static final int LEFT_LIMIT = 0;
	public static final int RIGHT_LIMIT = 400;
	public static final int LEFT_LIMIT_HEAD = 9;
	public static final int RIGHT_LIMIT_HEAD = 409;
	
	public static final int CIRCLE_RADIUS = 15;
	public static final int BODY_X = 180;
	public static final int BODY_Y = 550;
	public static final int HEAD_X = 189;
	public static final int HEAD_Y = 540;
	public static final double VELOCITY = 0.0;
	
	public static final int MIN_OFFSET_X = -60;
	public static final int MAX_OFFSET_X = 60;
	public static final int MIN_OFFSET_Y = 200;	
	public static final int MAX_OFFSET_Y = 500;	
	
	public static final int SCORE = 10;
	
	public static final int END_BUTTON = 200;
}
