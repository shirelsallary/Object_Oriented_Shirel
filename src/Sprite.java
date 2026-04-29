import biuoop.DrawSurface;

/**
 * @author Shirel Sallary
 * The Sprite interface represents objects that can be drawn on the screen
 * and can change their state over time.
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     * @param d the draw surface.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}