import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Shirel Sallary
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private Color color;
    private Velocity velocity;
    private GameEnvironment environment;

    // --- Constructors (Overloading) ---

    // אפשרות 1: עבור המשחק החדש (עם סביבה)
    public Ball(Point center, int r, Color color, GameEnvironment environment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.environment = environment;
        this.velocity = new Velocity(0, 0);
    }

    // אפשרות 2: עבור הטסטים (עם אובייקט Point אבל בלי סביבה)
    public Ball(Point center, int r, Color color) {
        this(center, r, color, null);
    }

    // אפשרות 3: עבור האנימציות הישנות (מקבל x ו-y כמספרים)
    // זה יפתור את השגיאה שרואים בתמונה שלך!
    public Ball(double x, double y, int r, Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.environment = null;
    }

    // --- Accessors ---
    public int getX() { return (int) this.center.getX(); }
    public int getY() { return (int) this.center.getY(); }
    public int getSize() { return this.r; }
    public Color getColor() { return this.color; }
    public Velocity getVelocity() { return this.velocity; }

    public void setVelocity(Velocity v) { this.velocity = v; }
    public void setVelocity(double dx, double dy) { this.velocity = new Velocity(dx, dy); }

    // --- Drawing ---
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.r);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    // --- Movement ---
    public void moveOneStep() {
        if (this.environment == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }

        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
        CollisionInfo info = this.environment.getClosestCollision(trajectory);

        if (info == null) {
            this.center = trajectory.end();
        } else {
            // הזזה קלה אחורה מנקודת הפגיעה כדי שלא ייתקע בתוך הקיר
            double moveBackX = (this.velocity.getDx() > 0) ? -0.1 : 0.1;
            double moveBackY = (this.velocity.getDy() > 0) ? -0.1 : 0.1;

            this.center = new Point(info.collisionPoint().getX() + moveBackX,
                    info.collisionPoint().getY() + moveBackY);

            this.velocity = info.collisionObject().hit(info.collisionPoint(), this.velocity);
        }
    }

    // מתודה לאנימציות ישנות (Multiple Frames)
    public void moveOneStep(double startX, double startY, double endX, double endY) {
        double nextX = this.center.getX() + this.velocity.getDx();
        double nextY = this.center.getY() + this.velocity.getDy();

        if (nextX + this.r > endX || nextX - this.r < startX) {
            this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
        }
        if (nextY + this.r > endY || nextY - this.r < startY) {
            this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    public void addToGame(Game g) {
        g.addSprite(this);
    }
}