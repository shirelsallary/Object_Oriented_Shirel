/**
 * The Collidable interface will be used by things that can be collided with.
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint The point where the collision occurred.
     * @param currentVelocity The velocity of the object that hit the collidable.
     * @return The new velocity expected after the hit.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}