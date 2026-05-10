import biuoop.DrawSurface;
import java.awt.Color;

/**
 * LevelCompleteScreen animation.
 */
public class LevelCompleteScreen implements Animation {
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.WHITE);
        d.drawText(200, 300, "Level Complete! Press Space to continue", 30);
    }

    @Override
    public boolean shouldStop() {
        return false; // Stops via KeyPressStoppableAnimation
    }
}
