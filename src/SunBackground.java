import biuoop.DrawSurface;
import java.awt.Color;

/**
 * SunBackground for WideEasy level.
 */
public class SunBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 600);
        // Sun with glow effect
        d.setColor(Color.YELLOW);
        d.fillCircle(100, 100, 50);
        d.setColor(Color.ORANGE);
        d.fillCircle(100, 100, 40);
        d.setColor(Color.YELLOW);
        d.fillCircle(100, 100, 30);
    }

    @Override
    public void timePassed() {
        // No animation
    }
}
