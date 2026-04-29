/**
 * The Collidable interface will be used by things that can be collided with.
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();


    // בתוך Collidable.java
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
