import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * FinalFour level.
 */
public class FinalFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(85, 5));
        velocities.add(Velocity.fromAngleAndSpeed(90, 5));
        velocities.add(Velocity.fromAngleAndSpeed(95, 5));
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600), Color.GRAY);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.CYAN, Color.PINK, Color.WHITE, Color.GREEN};
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 15; col++) {
                if (row < 4) {
                    blocks.add(new Block(new Rectangle(new Point(20 + col * 50, 100 + row * 20), 50, 20), colors[row]));
                }
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 60; // 15*4
    }
}
