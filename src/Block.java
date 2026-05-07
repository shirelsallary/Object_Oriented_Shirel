import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shirel Sallary
 * A Block is a rectangular obstacle on the screen.
 */
public class Block implements Collidable, Sprite,HitNotifier {
    private Rectangle rect;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructor for Block.
     * @param rect the rectangle shape of the block.
     * @param color the color of the block.
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @return the rectangle shape of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }


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
    public void addToGame(GameInterface g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    // Method to notify all listeners about a hit
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Logic for changing velocity upon hit and notifying listeners.
     * @param hitter the ball that hit the block.
     * @param collisionPoint the point where the hit occurred.
     * @param currentVelocity the velocity of the ball before the hit.
     * @return the new velocity after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // 1. לוקחים את המהירות הנוכחית
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        // 2. הלוגיקה הפיזיקלית הקיימת שלך: בדיקת פגיעה בדפנות הבלוק

        // בדיקת פגיעה בצדדים (שמאל או ימין) - הופכים את ה-dx
        if (Math.abs(collisionPoint.getX() - rect.getUpperLeft().getX()) < 1E-10 ||
                Math.abs(collisionPoint.getX() - (rect.getUpperLeft().getX() + rect.getWidth())) < 1E-10) {
            dx = -dx;
        }

        // בדיקת פגיעה למעלה או למטה - הופכים את ה-dy
        if (Math.abs(collisionPoint.getY() - rect.getUpperLeft().getY()) < 1E-10 ||
                Math.abs(collisionPoint.getY() - (rect.getUpperLeft().getY() + rect.getHeight())) < 1E-10) {
            dy = -dy;
        }

        // 3. יצירת אובייקט המהירות החדש
        Velocity newVelocity = new Velocity(dx, dy);

        // 4. הדבר החדש: שליחת הודעה לכל המאזינים שקרתה פגיעה
        // אנחנו מעבירים להם את הכדור שפגע (hitter) ואת הבלוק הנוכחי (this)
        this.notifyHit(hitter);

        // 5. מחזירים את המהירות החדשה למנוע של המשחק
        return newVelocity;
    }

    /**
     * Removes this block from the game.
     * @param game the game to remove from.
     */
    public void removeFromGame(GameInterface game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}