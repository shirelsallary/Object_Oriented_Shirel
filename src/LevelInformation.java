import java.util.List;

/**
 * LevelInformation interface.
 */
public interface LevelInformation {
    int numberOfBalls();
    List<Velocity> initialBallVelocities();
    int paddleSpeed();
    int paddleWidth();
    String levelName();
    Sprite getBackground();
    List<Block> blocks();
    int numberOfBlocksToRemove();
}
