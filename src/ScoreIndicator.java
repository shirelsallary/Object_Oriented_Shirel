import biuoop.DrawSurface;
import java.awt.Color;

/**
 * ScoreIndicator is a sprite that displays the current score.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // ציור רקע קטן או טקסט בראש המסך
        d.setColor(Color.BLACK);
        d.drawText(350, 15, "Score: " + this.score.getValue(), 15);
    }

    @Override
    public void timePassed() {
        // אין צורך בלוגיקה של זמן עבור התצוגה
    }

    public void addToGame(Game g) {
        g.addSprite(this);
    }
}