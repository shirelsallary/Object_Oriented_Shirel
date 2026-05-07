import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation is a decorator that stops the animation on key press,
 * but only if the key was not pressed when the animation started.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation animation;
    private KeyboardSensor keyboard;
    private String key;
    private boolean stop;
    private boolean isAlreadyPressed;

    public KeyPressStoppableAnimation(KeyboardSensor keyboard, String key, Animation animation) {
        this.animation = animation;
        this.keyboard = keyboard;
        this.key = key;
        this.stop = false;
        this.isAlreadyPressed = keyboard.isPressed(key);
    }

    @Override
    public void doOneFrame(biuoop.DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboard.isPressed(key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop || this.animation.shouldStop();
    }
}
