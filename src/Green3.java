import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Green3 level.
 */
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(30, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-30, 5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600), Color.GREEN);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col <= row; col++) {
                blocks.add(new Block(new Rectangle(new Point(700 - col * 50, 100 + row * 20), 50, 20), Color.GRAY));
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15; // 5+4+3+2+1
    }
}
