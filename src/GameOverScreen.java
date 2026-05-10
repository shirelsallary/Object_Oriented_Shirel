import biuoop.DrawSurface;
import java.awt.Color;

/**
 * GameOverScreen animation.
 */
public class GameOverScreen implements Animation {
    private Counter score;
    private boolean stop;

    public GameOverScreen(Counter score) {
        this.score = score;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.RED);
        d.drawText(250, 250, "Game Over", 50);
        d.setColor(Color.WHITE);
        d.drawText(250, 350, "Your final score is " + this.score.getValue(), 30);
        // Do not stop immediately, wait for key press
    }

    @Override
    public boolean shouldStop() {
        return false; // Stops via KeyPressStoppableAnimation
    }
}
