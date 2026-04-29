import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * @author Shirel Sallary
 * The Game class holds the sprites and the environment, and is in charge of the animation.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;

    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        this.gui = new GUI("Arkanoid - Shirel Sallary", 800, 600);
        this.sleeper = new Sleeper();

        // 1. יצירת כדור
        Ball ball = new Ball(new Point(400, 300), 5, Color.BLUE, this.environment);
        ball.setVelocity(Velocity.fromAngleAndSpeed(0, 5));
        ball.addToGame(this);

        // 2. יצירת פאדל (המחבט)
        // שימי לב: הוא משתמש ב-KeyboardSensor של ה-GUI שיצרנו
        Rectangle paddleRect = new Rectangle(new Point(350, 560), 100, 20);
        Paddle paddle = new Paddle(paddleRect, Color.ORANGE, gui.getKeyboardSensor());
        paddle.addToGame(this);

        // 3. יצירת גבולות (קירות)
// קיר עליון - נשאר אותו דבר
        Block topWall = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.GRAY);
        topWall.addToGame(this);

// קיר שמאלי - חייב להתחיל ב-X=0!
        Block leftWall = new Block(new Rectangle(new Point(0, 0), 20, 600), Color.GRAY);
        leftWall.addToGame(this);

// קיר ימני - מתחיל ב-780 (כדי שעובי של 20 יכנס בתוך ה-800)
        Block rightWall = new Block(new Rectangle(new Point(780, 0), 20, 600), Color.GRAY);
        rightWall.addToGame(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (true) {
            long startTime = System.currentTimeMillis();

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}