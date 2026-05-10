import biuoop.DrawSurface;
import java.awt.Color;

/**
 * ScoreIndicator is a sprite that displays the current score and level.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private String levelName;

    public ScoreIndicator(Counter score, String levelName) {
        this.score = score;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(350, 15, "Level: " + this.levelName + "  Score: " + this.score.getValue(), 15);
    }

    @Override
    public void timePassed() {
        // No animation
    }

    public void addToGame(GameInterface g) {
        g.addSprite(this);
    }
}