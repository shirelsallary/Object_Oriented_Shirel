import biuoop.GUI;
import java.util.ArrayList;
import java.util.List;

/**
 * Ass4Game is the main class for Assignment 4.
 */
public class Ass4Game {
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui);

        GameFlow gameFlow = new GameFlow(animationRunner);

        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new DirectHit());
        levels.add(new WideEasy());
        levels.add(new Green3());
        levels.add(new FinalFour());
        levels.add(new OriginalLevel());

        gameFlow.runLevels(levels);

        gui.close();
    }
}
