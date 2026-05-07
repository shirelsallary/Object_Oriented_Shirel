import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * OriginalLevel level.
 */
public class OriginalLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(40, 5));
        velocities.add(Velocity.fromAngleAndSpeed(0, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-40, 5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 200;
    }

    @Override
    public String levelName() {
        return "Original Level";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600), Color.BLACK);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 12; col++) {
                blocks.add(new Block(new Rectangle(new Point(780 - (col + 1) * 50, 100 + row * 20), 50, 20), colors[row]));
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 72; // 12*6
    }
}
