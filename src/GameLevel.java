import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Shirel Sallary
 * The GameLevel class represents a single level in the game, implementing Animation.
 */
public class GameLevel implements Animation, GameInterface {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private LevelInformation levelInfo;

    // Counters for game management
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;

    private boolean stopped;

    public GameLevel(GUI gui, LevelInformation levelInfo, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.score = score;
        this.gui = gui;
        this.levelInfo = levelInfo;
        this.stopped = false;
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
     * Initialize a new level: create the Blocks, Ball, Paddle and Listeners.
     */
    public void initialize() {
        // Add background
        this.addSprite(levelInfo.getBackground());

        // 1. Add Score Indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, levelInfo.levelName());
        scoreIndicator.addToGame(this);

        // 2. Create Listeners
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);

        // 3. Create Borders and Death Region
        this.createBorders(ballRemover);

        // 4. Create Blocks from levelInfo
        for (Block b : levelInfo.blocks()) {
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreListener);
            this.remainingBlocks.increase(1);
        }

        // 5. Create Balls
        int i = 0;
        for (Velocity v : levelInfo.initialBallVelocities()) {
            Ball ball = new Ball(new Point(400, 500), 5, Color.WHITE, this.environment);
            ball.setVelocity(v);
            ball.addToGame(this);
            this.remainingBalls.increase(1);
            i++;
        }

        // 6. Create Paddle
        Rectangle paddleRect = new Rectangle(new Point(350, 560), levelInfo.paddleWidth(), 20);
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
        Block deathRegion = new Block(new Rectangle(new Point(0, 600), 800, 30), Color.GRAY);
        deathRegion.addHitListener(ballRemover);
        deathRegion.addToGame(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);

        // Game Over Conditions
        if (this.remainingBlocks.getValue() == 0) {
            this.score.increase(100); // Bonus for clearing all blocks
            this.stopped = true;
        }

        if (this.remainingBalls.getValue() == 0) {
            this.stopped = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stopped;
    }

    public boolean isWon() {
        return this.remainingBlocks.getValue() == 0;
    }

    public boolean isLost() {
        return this.remainingBalls.getValue() == 0;
    }

    public SpriteCollection getSprites() {
        return this.sprites;
    }
}
