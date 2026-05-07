import biuoop.DrawSurface;
import java.awt.Color;

/**
 * YouWinScreen animation.
 */
public class YouWinScreen implements Animation {
    private Counter score;
    private boolean stop;

    public YouWinScreen(Counter score) {
        this.score = score;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.GREEN);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(250, 250, "You Win!", 50);
        d.setColor(Color.WHITE);
        d.drawText(250, 350, "Your score is " + this.score.getValue(), 30);
        this.stop = true;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
