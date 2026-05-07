/**
 * A BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameInterface game;
    private Counter remainingBlocks;

    public BlockRemover(GameInterface game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * @param beingHit the block hit.
     * @param hitter the ball hitting the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // 1. הסרת המאזין מהבלוק כדי שלא יופעל שוב בזמן המחיקה
        beingHit.removeHitListener(this);
        // 2. הסרת הבלוק מהמשחק (מה-Environment ומה-Sprites)
        beingHit.removeFromGame(this.game);
        // 3. עדכון המונה שנשאר
        this.remainingBlocks.decrease(1);
    }
}