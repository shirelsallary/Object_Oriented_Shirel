import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Shirel Sallary
 * A Block is a rectangular obstacle on the screen.
 */
public class Block implements Collidable, Sprite {
    private Rectangle rect;
    private Color color;

    /**
     * Constructor for Block.
     * @param rect the rectangle shape of the block.
     * @param color the color of the block.
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
    }

    /**
     * @return the rectangle shape of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Logic for changing velocity upon hit.
     * If hit on vertical edges, horizontal direction flips.
     * If hit on horizontal edges, vertical direction flips.
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        // Check horizontal boundaries (left and right edges)
        if (Math.abs(collisionPoint.getX() - rect.getUpperLeft().getX()) < 1E-10 ||
                Math.abs(collisionPoint.getX() - (rect.getUpperLeft().getX() + rect.getWidth())) < 1E-10) {
            dx = -dx;
        }

        // Check vertical boundaries (top and bottom edges)
        if (Math.abs(collisionPoint.getY() - rect.getUpperLeft().getY()) < 1E-10 ||
                Math.abs(collisionPoint.getY() - (rect.getUpperLeft().getY() + rect.getHeight())) < 1E-10) {
            dy = -dy;
        }

        return new Velocity(dx, dy);
    }

    /**
     * Draws the block on the given DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());

        // Draw black border around the block for professional look
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    /**
     * Currently, blocks don't do anything when time passes.
     */
    @Override
    public void timePassed() {
        // To be implemented in later assignments (e.g. animations)
    }
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}