import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * @author Shirel Sallary
 * The Game class manages the game flow, sprites, and listeners.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;

    // Counters for game management
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;

    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.score = new Counter(0);
    }

    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    // Methods to remove objects from the game
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks, Ball, Paddle and Listeners.
     */
    public void initialize() {
        this.gui = new GUI("Arkanoid - Shirel Sallary", 800, 600);
        this.sleeper = new Sleeper();

        // 1. Add Score Indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);

        // 2. Create Listeners
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);

        // 3. Create Borders and Death Region
        this.createBorders(ballRemover);

        // 4. Create Blocks in a pattern
        this.createBlocksGrid(blockRemover, scoreListener);

        // 5. Create Balls
        this.createBalls();

        // 6. Create Paddle
        Rectangle paddleRect = new Rectangle(new Point(350, 560), 100, 20);
        Paddle paddle = new Paddle(paddleRect, Color.ORANGE, gui.getKeyboardSensor());
        paddle.addToGame(this);
    }

    private void createBorders(BallRemover ballRemover) {
        int wallSize = 20;
        // Top, Left, Right walls (normal blocks)
        new Block(new Rectangle(new Point(0, 20), 800, wallSize), Color.GRAY).addToGame(this);
        new Block(new Rectangle(new Point(0, 20), wallSize, 600), Color.GRAY).addToGame(this);
        new Block(new Rectangle(new Point(780, 20), wallSize, 600), Color.GRAY).addToGame(this);

        // Bottom wall - The DEATH REGION
        Block deathRegion = new Block(new Rectangle(new Point(0, 600), 800, wallSize), Color.GRAY);
        deathRegion.addHitListener(ballRemover);
        deathRegion.addToGame(this);
    }

    private void createBlocksGrid(BlockRemover br, ScoreTrackingListener sl) {
        // Create 6 rows of blocks
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 12; col++) {
                Block b = new Block(new Rectangle(new Point(780 - (col + 1) * 50, 100 + row * 20), 50, 20), colors[row]);
                b.addToGame(this);
                b.addHitListener(br); // Notifies remover when hit
                b.addHitListener(sl); // Notifies score listener when hit
                this.remainingBlocks.increase(1);
            }
        }
    }

    private void createBalls() {
        for (int i = 0; i < 3; i++) {
            Ball ball = new Ball(new Point(400, 500), 5, Color.WHITE, this.environment);
            ball.setVelocity(Velocity.fromAngleAndSpeed(40 + (i * 20), 5));
            ball.addToGame(this);
            this.remainingBalls.increase(1);
        }
    }

    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (true) {
            long startTime = System.currentTimeMillis();

            DrawSurface d = gui.getDrawSurface();
            this.sprites.notifyAllTimePassed();
            this.sprites.drawAllOn(d);
            gui.show(d);

            // Game Over Conditions
            if (this.remainingBlocks.getValue() == 0) {
                this.score.increase(100); // Bonus for clearing all blocks

                // הדפסת הודעת ניצחון
                System.out.println("You Win! Final Score: " + this.score.getValue());

                gui.close();
                return;
            }

            if (this.remainingBalls.getValue() == 0) {
                // הדפסת הודעת הפסד
                System.out.println("Game Over. Final Score: " + this.score.getValue());

                gui.close();
                return;
            }

            long usedTime = System.currentTimeMillis() - startTime;
            long sleepTime = millisecondsPerFrame - usedTime;
            if (sleepTime > 0) {
                this.sleeper.sleepFor(sleepTime);
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}