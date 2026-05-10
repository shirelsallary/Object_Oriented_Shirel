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
        for (int i = 0; i < levels.size(); i++) {
            LevelInformation levelInfo = levels.get(i);
            GameLevel level = new GameLevel(animationRunner.getGui(), levelInfo, score);
            level.initialize();

            // Run countdown
            CountdownAnimation countdown = new CountdownAnimation(2, 3, level.getSprites());
            animationRunner.run(countdown);

            // Run level
            animationRunner.run(level);

            if (level.isWon()) {
                if (i < levels.size() - 1) {
                    // Show level complete screen
                    Animation levelComplete = new KeyPressStoppableAnimation(animationRunner.getGui().getKeyboardSensor(), biuoop.KeyboardSensor.SPACE_KEY, new LevelCompleteScreen());
                    animationRunner.run(levelComplete);
                }
            } else if (level.isLost()) {
                // Game over
                Animation gameOver = new KeyPressStoppableAnimation(animationRunner.getGui().getKeyboardSensor(), biuoop.KeyboardSensor.SPACE_KEY, new GameOverScreen(score));
                animationRunner.run(gameOver);
                return;
            }
        }
        // All levels won
        Animation youWin = new KeyPressStoppableAnimation(animationRunner.getGui().getKeyboardSensor(), biuoop.KeyboardSensor.SPACE_KEY, new YouWinScreen(score));
        animationRunner.run(youWin);
    }
}
