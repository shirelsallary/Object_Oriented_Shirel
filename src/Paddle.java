import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * @author Shirel Sallary
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle rect;
    private Color color;

    public Paddle(Rectangle rect, Color color, KeyboardSensor keyboard) {
        this.rect = rect;
        this.color = color;
        this.keyboard = keyboard;
    }

    public void moveLeft() {
        if (rect.getUpperLeft().getX() > 0) {
            double newX = rect.getUpperLeft().getX() - 5;
            this.rect = new Rectangle(new Point(newX, rect.getUpperLeft().getY()), rect.getWidth(), rect.getHeight());
        }
    }

    public void moveRight() {
        if (rect.getUpperLeft().getX() + rect.getWidth() < 800) {
            double newX = rect.getUpperLeft().getX() + 5;
            this.rect = new Rectangle(new Point(newX, rect.getUpperLeft().getY()), rect.getWidth(), rect.getHeight());
        }
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        // Check if hit left or right side
        if (Math.abs(collisionPoint.getX() - rect.getUpperLeft().getX()) < 1E-10 ||
                Math.abs(collisionPoint.getX() - (rect.getUpperLeft().getX() + rect.getWidth())) < 1E-10) {
            dx = -dx;
        } else {
            // Hit top or bottom, flip dy
            dy = -dy;
        }

        return new Velocity(dx, dy);
    }

    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

}