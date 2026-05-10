import biuoop.DrawSurface;
import java.awt.Color;

/**
 * BuildingBackground for Green3 level.
 */
public class BuildingBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GREEN);
        d.fillRectangle(0, 0, 800, 600);
        // Building
        d.setColor(Color.GRAY);
        d.fillRectangle(50, 100, 100, 400);
        // Windows
        d.setColor(Color.CYAN);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 5; col++) {
                d.fillRectangle(60 + col * 15, 110 + row * 35, 10, 20);
            }
        }
    }

    @Override
    public void timePassed() {
        // No animation
    }
}
