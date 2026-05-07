import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * AnimationRunner runs animations at 60 FPS.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor for AnimationRunner.
     * @param gui the GUI to draw on
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * Runs the given animation.
     * @param animation the animation to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();

            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long sleepTime = millisecondsPerFrame - usedTime;
            if (sleepTime > 0) {
                this.sleeper.sleepFor(sleepTime);
            }
        }
    }

    /**
     * @return the GUI
     */
    public GUI getGui() {
        return this.gui;
    }
}
