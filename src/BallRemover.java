/**
 * A BallRemover is in charge of removing balls that hit the "death region" 
 * at the bottom of the screen.
 */
public class BallRemover implements HitListener {
    private GameInterface game;
    private Counter remainingBalls;

    public BallRemover(GameInterface game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // הסרת הכדור שפגע ב"אזור המוות" מהמשחק
        hitter.removeFromGame(this.game);
        // עדכון מונה הכדורים
        this.remainingBalls.decrease(1);
    }
}