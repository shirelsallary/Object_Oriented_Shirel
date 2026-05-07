import biuoop.DrawSurface;

/**
 * The Animation interface represents an animation that can be run in the game.
 */
public interface Animation {
    /**
     * Performs one frame of the animation.
     * @param d the DrawSurface to draw on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks if the animation should stop.
     * @return true if the animation should stop, false otherwise
     */
    boolean shouldStop();
}
