import biuoop.GUI;
import java.util.List;

/**
 * GameFlow manages the flow of levels.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private Counter score;
    private Counter lives;

    public GameFlow(AnimationRunner animationRunner) {
        this.animationRunner = animationRunner;
        this.score = new Counter(0);
        this.lives = new Counter(3); // Assume 3 lives
    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(animationRunner.getGui(), levelInfo, score);
            level.initialize();

            // Run countdown
            CountdownAnimation countdown = new CountdownAnimation(2, 3, level.getSprites());
            animationRunner.run(countdown);

            // Run level
            animationRunner.run(level);

            if (level.isLost()) {
                lives.decrease(1);
                if (lives.getValue() == 0) {
                    // Game over
                    GameOverScreen gameOver = new GameOverScreen(score);
                    animationRunner.run(gameOver);
                    return;
                }
            } else if (level.isWon()) {
                // Continue to next
            }
        }
        // All levels won
        YouWinScreen youWin = new YouWinScreen(score);
        animationRunner.run(youWin);
    }
}
