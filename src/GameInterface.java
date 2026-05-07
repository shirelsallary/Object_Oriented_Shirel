/**
 * GameInterface for game objects.
 */
public interface GameInterface {
    void addCollidable(Collidable c);
    void addSprite(Sprite s);
    void removeCollidable(Collidable c);
    void removeSprite(Sprite s);
}
