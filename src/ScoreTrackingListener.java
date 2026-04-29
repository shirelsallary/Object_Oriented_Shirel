/**
 * ScoreTrackingListener updates the score counter when blocks are hit.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // הוספת 5 נקודות למונה עבור כל פגיעה בבלוק
        this.currentScore.increase(5);
    }
}