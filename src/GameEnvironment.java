import java.util.ArrayList;
import java.util.List;

/**
 * @author Shirel Sallary
 * GameEnvironment is a collection of objects the ball can collide with.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructor for GameEnvironment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Add the given collidable to the environment.
     * @param c the collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**
     * Removes a collidable from the environment.
     * @param c the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
    /**
     * Assume an object is moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     * @param trajectory the movement line of the object.
     * @return information about the closest collision, or null if no collision occurs.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo closestCollision = null;
        double minDistance = Double.MAX_VALUE;

        // Iterate through all collidables to find the nearest intersection
        for (Collidable c : new ArrayList<>(this.collidables)) {
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());

            if (p != null) {
                double distance = trajectory.start().distance(p);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestCollision = new CollisionInfo(p, c);
                }
            }
        }
        return closestCollision;
    }
}