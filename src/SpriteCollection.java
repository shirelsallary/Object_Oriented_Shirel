import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shirel Sallary
 * A collection of sprites that can be managed together.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite s : new ArrayList<>(this.sprites)) {
            s.timePassed();
        }
    }

    /**
     * Call drawOn(d) on all sprites.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }
}