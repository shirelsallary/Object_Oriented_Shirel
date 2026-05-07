import biuoop.DrawSurface;
import java.awt.Color;

/**
 * CountdownAnimation displays 3...2...1...GO on top of the game.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private long startTime;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // Draw the game screen
        this.gameScreen.drawAllOn(d);

        // Calculate current count
        long currentTime = System.currentTimeMillis();
        double elapsed = (currentTime - startTime) / 1000.0;
        int currentCount = countFrom - (int) (elapsed / (numOfSeconds / countFrom));

        if (currentCount <= 0) {
            this.stop = true;
            return;
        }

        // Draw the count
        d.setColor(Color.WHITE);
        d.drawText(400, 300, String.valueOf(currentCount), 50);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
